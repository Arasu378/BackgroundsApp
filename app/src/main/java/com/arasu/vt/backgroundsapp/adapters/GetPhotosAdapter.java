package com.arasu.vt.backgroundsapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arasu.vt.backgroundsapp.R;
import com.arasu.vt.backgroundsapp.pojo.PhotoResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by kyros on 31-10-2017.
 */

public class GetPhotosAdapter extends RecyclerView.Adapter<GetPhotosAdapter.PhotoViewHolder> {
    private Context mContext;
    private ArrayList<PhotoResponse>photosList;
    public GetPhotosAdapter(Context mContext,ArrayList<PhotoResponse>photosList){
        this.mContext=mContext;
        this.photosList=photosList;
    }
    public class PhotoViewHolder extends RecyclerView.ViewHolder{
            ImageView image_id;
        public PhotoViewHolder(View itemView) {
            super(itemView);
            image_id=(ImageView)itemView.findViewById(R.id.image_id);
        }
    }
    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_photo_list_item,parent,false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        PhotoResponse photoResponse=photosList.get(position);
        String regular=photoResponse.getUrls().getRegular();
        if(regular!=null){
            Picasso.with(mContext).load(regular).into(holder.image_id);
        }
    }

    @Override
    public int getItemCount() {
        if(photosList!=null && photosList.size()!=0){
            return photosList.size();
        }else {
            return 0;
        }

    }
}
