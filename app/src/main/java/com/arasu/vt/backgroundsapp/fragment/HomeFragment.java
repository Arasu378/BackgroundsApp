package com.arasu.vt.backgroundsapp.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.os.health.PackageHealthStats;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arasu.vt.backgroundsapp.R;
import com.arasu.vt.backgroundsapp.adapters.GetPhotosAdapter;
import com.arasu.vt.backgroundsapp.application.ApiClient;
import com.arasu.vt.backgroundsapp.application.EndlessRecyclerViewScrollListener;
import com.arasu.vt.backgroundsapp.interfaces.POJOInterface;
import com.arasu.vt.backgroundsapp.pojo.PhotoResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kyros on 31-10-2017.
 */

public class HomeFragment extends android.support.v4.app.Fragment {
    private RecyclerView recycler_home;
    private GetPhotosAdapter adapter;
    private int perPage=30;
    private int page=1;
    private String order_By="latest";
    //oldest,latest,popular,trending
    private   ArrayList<PhotoResponse> originalList=new ArrayList<PhotoResponse>();
    private EndlessRecyclerViewScrollListener scrollListener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        recycler_home=(RecyclerView)view.findViewById(R.id.recycler_home);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycler_home.setLayoutManager(linearLayoutManager);
        getPhotosList(perPage,order_By,page);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                page=page+1;
                getPhotosList(perPage,order_By,page);
            }
        };
        // Adds the scroll listener to RecyclerView
        recycler_home.addOnScrollListener(scrollListener);
        adapter=new GetPhotosAdapter(getContext(),originalList);
        recycler_home.setAdapter(adapter);
        return view;
    }
    private void getPhotosList(int per_page,String order_By,int page){
        Log.d("PageNo : ",""+page);
        POJOInterface apiService= ApiClient.getRetrofit().create(POJOInterface.class);
        Call<ArrayList<PhotoResponse>>call=apiService.getPhotosList(per_page,order_By,page);
       call.enqueue(new Callback<ArrayList<PhotoResponse>>() {
           @SuppressLint("NewApi")
           @Override
           public void onResponse(Call<ArrayList<PhotoResponse>> call, Response<ArrayList<PhotoResponse>> response) {
               Log.d("Response code : ",""+response.headers().toString());
               ArrayList<PhotoResponse>list=response.body();
               originalList.addAll(list);

               adapter.notifyDataSetChanged();


           }

           @SuppressLint("NewApi")
           @Override
           public void onFailure(Call<ArrayList<PhotoResponse>> call, Throwable t) {
               Toast.makeText(getContext(),"Failure",Toast.LENGTH_SHORT).show();
               try{
                   Log.e("Error: ","arasu  : "+t.getMessage());
                   t.getMessage();
                   t.printStackTrace();
               }catch (Exception e){
                   e.printStackTrace();
               }
           }
       });
    }
}
