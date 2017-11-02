package com.arasu.vt.backgroundsapp.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.health.PackageHealthStats;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arasu.vt.backgroundsapp.R;
import com.arasu.vt.backgroundsapp.activity.FullScreenActivity;
import com.arasu.vt.backgroundsapp.adapters.GetPhotosAdapter;
import com.arasu.vt.backgroundsapp.application.ApiClient;
import com.arasu.vt.backgroundsapp.application.EndlessRecyclerViewScrollListener;
import com.arasu.vt.backgroundsapp.application.PhotoApplication;
import com.arasu.vt.backgroundsapp.base.PhotosPresenter;
import com.arasu.vt.backgroundsapp.interfaces.POJOInterface;
import com.arasu.vt.backgroundsapp.interfaces.PhotoViewInterface;
import com.arasu.vt.backgroundsapp.pojo.PhotoResponse;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Observable;


/**
 * Created by kyros on 31-10-2017.
 */

public class HomeFragment extends android.support.v4.app.Fragment implements PhotoViewInterface,GetPhotosAdapter.PhotoClickListener{
  @Inject
     POJOInterface mService;
  private PhotosPresenter mPresenter;
  private GetPhotosAdapter mAdapter;
  private ProgressDialog mDialog;

    private RecyclerView recycler_home;
    private GetPhotosAdapter adapter;
    private int perPage=30;
    private int page=1;
    private String order_By="latest";
    //oldest,latest,popular,trending
    private POJOInterface pojoInterface;

    private   ArrayList<PhotoResponse> originalList=new ArrayList<PhotoResponse>();
    private EndlessRecyclerViewScrollListener scrollListener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
                resolveDependency();
        recycler_home=(RecyclerView)view.findViewById(R.id.recycler_home);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recycler_home.setLayoutManager(mLayoutManager);
        recycler_home.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));

      //  getPhotosList(perPage,order_By,page);
        pojoInterface=new ApiClient().getPOJOInterface();
        scrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                page=page+1;
               // getPhotosList(perPage,order_By,page);
                Toast.makeText(getContext(),"Load More",Toast.LENGTH_SHORT).show();
                mService.getPhotosList(perPage,order_By,page);
            }
        };
        // Adds the scroll listener to RecyclerView
        recycler_home.addOnScrollListener(scrollListener);
        mAdapter = new GetPhotosAdapter(this, getLayoutInflater());
        recycler_home.setAdapter(mAdapter);
        mPresenter=new PhotosPresenter(HomeFragment.this);
        mPresenter.onCreate();
        return view;
    }

    private void resolveDependency() {
        ((PhotoApplication)getActivity().getApplication())
                .getApiComponent()
                .inject(HomeFragment.this);


    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
        mPresenter.fetchPhotos();
        mDialog=new ProgressDialog(getContext());
        mDialog.setIndeterminate(true);
        mDialog.setMessage("Please wait...");
        mDialog.show();

    }



    @Override
    public void onCompleted() {
mDialog.dismiss();
    }

    @Override
    public void onError(String message) {
    mDialog.dismiss();
    Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPictures(ArrayList<PhotoResponse> photoResponses) {
            mAdapter.addPhotos(photoResponses);
    }

    @Override
    public Observable<ArrayList<PhotoResponse>> getPhotos() {
        return mService.getPhotosList(perPage,order_By,page);
    }

    @Override
    public void onClick(int position, String name) {
//        Intent intent=new Intent(getContext(), FullScreenActivity.class);
//                intent.putExtra("url",name.get(position).getUrls().getFull());
//                startActivity(intent);
        Toast.makeText(getContext(), "You clicked on " + name, Toast.LENGTH_SHORT).show();

    }

    //    private void getPhotosList(int per_page,String order_By,int page){
//        Log.d("PageNo : ",""+page);
//        POJOInterface apiService= ApiClient.getRetrofit().create(POJOInterface.class);
//        Call<ArrayList<PhotoResponse>>call=apiService.getPhotosList(per_page,order_By,page);
//       call.enqueue(new Callback<ArrayList<PhotoResponse>>() {
//           @SuppressLint("NewApi")
//           @Override
//           public void onResponse(Call<ArrayList<PhotoResponse>> call, Response<ArrayList<PhotoResponse>> response) {
//               Log.d("Response code : ",""+response.headers().toString());
//               ArrayList<PhotoResponse>list=response.body();
//               originalList.addAll(list);
//
//               adapter.notifyDataSetChanged();
//
//
//           }
//
//           @SuppressLint("NewApi")
//           @Override
//           public void onFailure(Call<ArrayList<PhotoResponse>> call, Throwable t) {
//               Toast.makeText(getContext(),"Failure",Toast.LENGTH_SHORT).show();
//               try{
//                   Log.e("Error: ","arasu  : "+t.getMessage());
//                   t.getMessage();
//                   t.printStackTrace();
//               }catch (Exception e){
//                   e.printStackTrace();
//               }
//           }
//       });
//    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        //compositeDisposable.clear();
        super.onDestroy();
    }
}
