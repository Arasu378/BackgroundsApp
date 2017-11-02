package com.arasu.vt.backgroundsapp.base;

import com.arasu.vt.backgroundsapp.interfaces.PhotoViewInterface;
import com.arasu.vt.backgroundsapp.pojo.PhotoResponse;

import java.util.ArrayList;

import rx.Observable;
import rx.Observer;

/**
 * Created by kyros on 02-11-2017.
 */

public class PhotosPresenter extends BasePresenter implements Observer<ArrayList<PhotoResponse>> {
   private PhotoViewInterface mPhotoViewInterface;
    public PhotosPresenter(PhotoViewInterface viewInterface){
        mPhotoViewInterface=viewInterface;
    }

    @Override
    public void onCompleted() {
        mPhotoViewInterface.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        mPhotoViewInterface.onError(e.getMessage());
    }

    @Override
    public void onNext(ArrayList<PhotoResponse> photoResponses) {
        mPhotoViewInterface.onPictures(photoResponses);
    }
    public void fetchPhotos(){
        unSubscribeAll();
        subcribe(mPhotoViewInterface.getPhotos(),PhotosPresenter.this);
    }
}
