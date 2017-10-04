package com.alexvoronkov.difdevices;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Санек on 03.10.2017.
 */

public interface RequestInterface {

    @GET("android/jsonandroid")
    Call<AndroidVersion> getJSON();
}
