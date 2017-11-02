package com.arasu.vt.backgroundsapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arasu.vt.backgroundsapp.R;
import com.arasu.vt.backgroundsapp.activity.FullScreenActivity;
import com.arasu.vt.backgroundsapp.pojo.PhotoResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by kyros on 31-10-2017.
 */

public class GetPhotosAdapter extends RecyclerView.Adapter<GetPhotosAdapter.PhotoViewHolder> {

    private ArrayList<PhotoResponse>photosList;
    private final LayoutInflater mInflater;
    private PhotoClickListener mListener;
    public GetPhotosAdapter(PhotoClickListener listener,LayoutInflater inflater){
        mListener=listener;
        mInflater=inflater;
        photosList=new ArrayList<PhotoResponse>();
    }
    public class PhotoViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
            ImageView image_id;
        public PhotoViewHolder(View itemView) {
            super(itemView);
            image_id=(ImageView)itemView.findViewById(R.id.image_id);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(getLayoutPosition(),photosList.get(getAdapterPosition()).getUser().getName());
        }
    }
    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_photo_list_item,parent,false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        final PhotoResponse photoResponse=photosList.get(position);
        String regular=photoResponse.getUrls().getRegular();
        if(regular!=null){
            Picasso.with(holder.itemView.getContext()).load(regular).into(holder.image_id);
        }
//        holder.image_id.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(mContext, FullScreenActivity.class);
//                intent.putExtra("url",photoResponse.getUrls().getFull());
//                mContext.startActivity(intent);
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if(photosList!=null && photosList.size()!=0){
            return photosList.size();
        }else {
            return 0;
        }

    }
    public void addPhotos(ArrayList<PhotoResponse>photoResponses){
        photosList.addAll(photoResponses);
        notifyDataSetChanged();
    }
    public interface PhotoClickListener{
        void onClick(int position,String name);
    }
}
