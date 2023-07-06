package com.nirogi.harayna.network;



import com.nirogi.harayna.model.request.LoginModelRequest;
import com.nirogi.harayna.model.request.SearchPPPIDRequest;
import com.nirogi.harayna.model.response.DistrictModel;
import com.nirogi.harayna.model.response.LoginModelResponse;
import com.nirogi.harayna.model.response.PatientListModelResponse;

import java.util.ArrayList;
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

       @GET("api/v1/distList")
       Call<ArrayList<DistrictModel>> getDistList();

       @POST("api/v1/PppMembersSearch")
       Call<ArrayList<PatientListModelResponse>> getSearchedPPPIDPatients(@Body SearchPPPIDRequest modelRequest);
}
