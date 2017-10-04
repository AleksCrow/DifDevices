package com.alexvoronkov.difdevices.Interface;

import com.alexvoronkov.difdevices.ObjectClass.AndroidVersion;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {

    @GET("android/jsonandroid")
    Call<AndroidVersion> getJSON();
}
