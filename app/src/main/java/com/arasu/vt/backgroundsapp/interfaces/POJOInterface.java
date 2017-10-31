package com.arasu.vt.backgroundsapp.interfaces;

import com.arasu.vt.backgroundsapp.pojo.PhotoResponse;
import com.arasu.vt.backgroundsapp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by kyros on 31-10-2017.
 */

public interface POJOInterface {
    @Headers("Authorization: Client-ID "+ Utils.Application_ID)
    @GET("/photos")
    Call<ArrayList<PhotoResponse>> getPhotosList(@Query("per_page")int page,@Query("order_By")String order_By);
}
