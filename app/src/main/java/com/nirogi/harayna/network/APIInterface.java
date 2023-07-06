package com.nirogi.harayna.network;



import com.nirogi.harayna.model.request.LoginModelRequest;
import com.nirogi.harayna.model.response.LoginModelResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface APIInterface
{
       @POST("login")
       Call<LoginModelResponse> getLogin(@Body LoginModelRequest modelRequest);


}
