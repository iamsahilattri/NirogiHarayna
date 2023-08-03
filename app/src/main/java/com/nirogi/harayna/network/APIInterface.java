package com.nirogi.harayna.network;



import com.nirogi.harayna.model.request.LoginModelRequest;
import com.nirogi.harayna.model.request.PostDataForCategoryIIIRequest;
import com.nirogi.harayna.model.request.PostDataForCategoryIIRequest;
import com.nirogi.harayna.model.request.PostDataForCategoryIRequest;
import com.nirogi.harayna.model.request.PostDataForCategoryIVRequest;
import com.nirogi.harayna.model.request.PostDataForCategoryVIRequest;
import com.nirogi.harayna.model.request.PostDataForCategoryVRequest;
import com.nirogi.harayna.model.request.PostMandatoryDataRequest;
import com.nirogi.harayna.model.request.SearchPPPIDRequest;
import com.nirogi.harayna.model.request.SearchPatientFromDataRequest;
import com.nirogi.harayna.model.request.SearchReferenceIDRRequest;
import com.nirogi.harayna.model.response.DistrictModel;
import com.nirogi.harayna.model.response.LoginModelResponse;
import com.nirogi.harayna.model.response.PatientListModelResponse;
import com.nirogi.harayna.model.response.ReferenceIdResponse;
import com.nirogi.harayna.model.response.ReferredSurveyDataResponse;
import com.nirogi.harayna.model.response.SubmitPatientData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

       @GET("api/v1/getPatientScreenedData/{id}")
       Call<ReferredSurveyDataResponse> getPatientScreenedData(@Path("id") String id);


       @POST("api/v1/getPatientData")
       Call<ArrayList<PatientListModelResponse>> getSearchedPatientsFromData(@Body SearchPatientFromDataRequest modelRequest);


       @POST("api/v1/submitCatagByMobApp")
       Call<SubmitPatientData> submitDataForSurveyCatI(@Body PostDataForCategoryIRequest modelRequest);
       @POST("api/v1/submitCatagByMobApp")
       Call<SubmitPatientData> submitDataForSurveyCatII(@Body PostDataForCategoryIIRequest modelRequest);

       @POST("api/v1/submitCatagByMobApp")
       Call<SubmitPatientData> submitDataForSurveyCatIII(@Body PostDataForCategoryIIIRequest modelRequest);
       @POST("api/v1/submitCatagByMobApp")
       Call<SubmitPatientData> submitDataForSurveyCatIV(@Body PostDataForCategoryIVRequest modelRequest);
       @POST("api/v1/submitCatagByMobApp")
       Call<SubmitPatientData> submitDataForSurveyCatV(@Body PostDataForCategoryVRequest modelRequest);
       @POST("api/v1/submitCatagByMobApp")
       Call<SubmitPatientData> submitDataForSurveyCatVI(@Body PostDataForCategoryVIRequest modelRequest);

       @POST("api/v1/submitMandatoryInves")
       Call<SubmitPatientData> submitMandatoryInvestigationReference(@Body PostMandatoryDataRequest modelRequest);
}
