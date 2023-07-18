package com.nirogi.harayna.network;



import com.nirogi.harayna.model.request.LoginModelRequest;
import com.nirogi.harayna.model.request.PostDataForCategoryIRequest;
import com.nirogi.harayna.model.request.SearchPPPIDRequest;
import com.nirogi.harayna.model.request.SearchPatientFromDataRequest;
import com.nirogi.harayna.model.request.SearchReferenceIDRRequest;
import com.nirogi.harayna.model.response.DistrictModel;
import com.nirogi.harayna.model.response.LoginModelResponse;
import com.nirogi.harayna.model.response.PatientListModelResponse;
import com.nirogi.harayna.model.response.ReferenceIdResponse;
import com.nirogi.harayna.model.response.SubmitPatientData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface
{
       @POST("login")
       Call<LoginModelResponse> getLogin(@Body LoginModelRequest modelRequest);

       @GET("api/v1/distList")
       Call<ArrayList<DistrictModel>> getDistList();

       @POST("api/v1/PppMembersSearch")
       Call<ArrayList<PatientListModelResponse>> getSearchedPPPIDPatients(@Body SearchPPPIDRequest modelRequest);

       @POST("api/v1/historyRefDataSearch")
       Call<ArrayList<ReferenceIdResponse>> getSearchedRefIDPatients(@Body SearchReferenceIDRRequest modelRequest);

       @POST("api/v1/getPatientData")
       Call<ArrayList<PatientListModelResponse>> getSearchedPatientsFromData(@Body SearchPatientFromDataRequest modelRequest);

       @POST("api/v1/submitCatag2")
       Call<SubmitPatientData> submitDataForSurvey(@Body PostDataForCategoryIRequest modelRequest);


}
