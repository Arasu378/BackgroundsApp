package com.arasu.vt.backgroundsapp.interfaces;

import com.arasu.vt.backgroundsapp.pojo.PhotoResponse;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by kyros on 02-11-2017.
 */

public interface PhotoViewInterface {
    void onCompleted();
    void onError(String message);
    void onPictures(ArrayList<PhotoResponse> photoResponses);
    Observable<ArrayList<PhotoResponse>>getPhotos();
}
