package com.nirogi.harayna.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.nirogi.harayna.R;
import com.nirogi.harayna.activity.category.CategoryIIIPatientEntryActivity;
import com.nirogi.harayna.activity.category.CategoryIIPatientEntryActivity;
import com.nirogi.harayna.activity.category.CategoryIPatientEntryActivity;
import com.nirogi.harayna.activity.category.CategoryIVPatientEntryActivity;
import com.nirogi.harayna.activity.category.CategoryVIPatientEntryActivity;
import com.nirogi.harayna.activity.category.CategoryVPatientEntryActivity;
import com.nirogi.harayna.adapter.PatientDetailRefrenceIdAdapter;
import com.nirogi.harayna.model.request.SearchReferenceIDRRequest;
import com.nirogi.harayna.model.response.PatientListModelResponse;
import com.nirogi.harayna.model.response.ReferenceIdResponse;
import com.nirogi.harayna.model.response.ReferredSurveyDataResponse;
import com.nirogi.harayna.network.APIInterface;
import com.nirogi.harayna.network.ApiClient;
import com.nirogi.harayna.utils.BaseActivity;
import com.nirogi.harayna.utils.IntentParams;
import com.nirogi.harayna.utils.NIROGI;
import com.nirogi.harayna.utils.RecyclerItemClickListener;
import com.nirogi.harayna.utils.SharedParams;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchedReferenceIdDetails extends BaseActivity {

    private String referenceId;

    private RecyclerView recyclerPatientList;
    private SharedPreferences preferences;
    private SwipeRefreshLayout swipeDown;
    private TextView noDataLy;
//    private  ArrayList<ReferredSurveyDataResponse> recorderRefData;
    private  ReferredSurveyDataResponse recorderRefData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_pppid);
        preferences=NIROGI.getInstance().getPreferences();
        mSetBackToolbar(SearchedReferenceIdDetails.this, "Patient",false,"");
        initViews();
        setRecyclerFromIntentData();

    }


    private void setRecyclerFromIntentData()
    {
        if(getIntent()!=null)
        {
            referenceId=getIntent().getStringExtra(IntentParams.REFERENCE_ID);
            ArrayList<ReferenceIdResponse> intentDataList =(ArrayList<ReferenceIdResponse>)getIntent().getSerializableExtra(IntentParams.M_DATA);
            Log.e(" mDataList Size ",""+intentDataList.size());
            mSetRecyclerData(intentDataList);
        }
    }

    private void initViews()
    {
        recyclerPatientList=findViewById(R.id.recyclerPatientList);
        noDataLy=findViewById(R.id.noDataLy);
        swipeDown = findViewById(R.id.swipeDown);
        swipeDown.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(isNetworkAvailable())
                {
                    getPatientListWithRefID(referenceId);
                }
                swipeDown.setRefreshing(false);

            }
        });
        // Configure the refreshing colors
        swipeDown.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    public void getPatientListWithRefID(String inputTextPPP)
    {
        try {
            createProgressBar(R.id.relSPMain);
            APIInterface apiInterface = ApiClient.getClientAuthenticationWithAuth(preferences.getString(SharedParams.AUTH_TOKEN,"")).create(APIInterface.class);
            SearchReferenceIDRRequest request= new SearchReferenceIDRRequest();
            request.setReferenceId(inputTextPPP);
            Call<ArrayList<ReferenceIdResponse>> call = apiInterface.getSearchedRefIDPatients(request);
            call.enqueue(new Callback<ArrayList<ReferenceIdResponse>>() {
                @Override
                public void onResponse(Call<ArrayList<ReferenceIdResponse>> call, Response<ArrayList<ReferenceIdResponse>> response) {
                    try {
                        if (response.isSuccessful()) {

                            if(response.body().size()>0)
                            {
                                disableProgressBar();
                            }else
                            {
                                disableProgressBar();
                            }

                        }else{
                            disableProgressBar();
                            mHandleApiErrorCode(response.code(),response.errorBody().string(), SearchedReferenceIdDetails.this);
                        }
                    }catch (Exception e)
                    {
                        Log.e(" Exception ",""+e.getMessage());
                        disableProgressBar();
                    }


                }

                @Override
                public void onFailure(Call<ArrayList<ReferenceIdResponse>> call, Throwable t) {
                    mShowToast(getString(R.string.api_failure));
                    disableProgressBar();
                }
            });


        }catch (Exception ee)
        {
            Log.e(" Exception ",""+ee.getMessage());
        }

    }

    public void getPatientDataWithRefID(String inputTextPPP)
    {
        try {
            createProgressBar(R.id.relSPMain);
            APIInterface apiInterface = ApiClient.getClientAuthenticationWithAuth(preferences.getString(SharedParams.AUTH_TOKEN,"")).create(APIInterface.class);
            SearchReferenceIDRRequest request= new SearchReferenceIDRRequest();
            request.setReferenceId(inputTextPPP);
            Call<ReferredSurveyDataResponse> call = apiInterface.getPatientScreenedData(inputTextPPP);
            call.enqueue(new Callback<ReferredSurveyDataResponse>() {
                @Override
                public void onResponse(Call<ReferredSurveyDataResponse> call, Response<ReferredSurveyDataResponse> response) {
                    try {
                        if (response.isSuccessful()) {
                            recorderRefData= response.body();

                            String catValue=recorderRefData.getData().get(0).getData().getCategory();
                            Log.e("catValue",""+catValue);
                            Intent mIntent = null;
                            switch (catValue) {
                                case "1": {
                                    mIntent = new Intent(SearchedReferenceIdDetails.this, CategoryIPatientEntryActivity.class);
                                    break;
                                }
                                case "2": {
                                    mIntent = new Intent(SearchedReferenceIdDetails.this, CategoryIIPatientEntryActivity.class);
                                    break;
                                }
                                case "3": {
                                    mIntent = new Intent(SearchedReferenceIdDetails.this, CategoryIIIPatientEntryActivity.class);
                                    break;
                                }
                                case "4": {
                                    mIntent = new Intent(SearchedReferenceIdDetails.this, CategoryIVPatientEntryActivity.class);
                                    break;
                                }
                                case "5": {
                                    mIntent = new Intent(SearchedReferenceIdDetails.this, CategoryVPatientEntryActivity.class);
                                    break;
                                }
                                case "6": {
                                    mIntent = new Intent(SearchedReferenceIdDetails.this, CategoryVIPatientEntryActivity.class);
                                    break;
                                }
                            }

                            assert mIntent != null;
                            mIntent.putExtra(IntentParams.SCREENED_DATA, recorderRefData);
                            mIntent.putExtra(IntentParams.MEMBER_DATA, new PatientListModelResponse());
                            mIntent.putExtra(IntentParams.MEMBER_TYPE, "2");
                            startActivity(mIntent);
                            disableProgressBar();


                        }else{
                            disableProgressBar();
                            mHandleApiErrorCode(response.code(),response.errorBody().string(), SearchedReferenceIdDetails.this);
                        }
                    }catch (Exception e)
                    {
                        Log.e(" Exception ",""+e.getMessage());
                        disableProgressBar();
                    }


                }

                @Override
                public void onFailure(Call<ReferredSurveyDataResponse> call, Throwable t) {
                    Log.e(" Throwable ",""+t.getMessage());
                    Log.e(" call ",""+call.toString());
                    mShowToast(getString(R.string.api_failure));
                    disableProgressBar();
                }
            });


        }catch (Exception ee)
        {
            Log.e(" Exception ",""+ee.getMessage());
        }

    }
    private void mSetRecyclerData(ArrayList<ReferenceIdResponse> populateData)
    {
        noDataLy.setVisibility(View.GONE);
        recyclerPatientList.setVisibility(View.VISIBLE);
        PatientDetailRefrenceIdAdapter mAdapter = new PatientDetailRefrenceIdAdapter(SearchedReferenceIdDetails.this, populateData);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerPatientList.setLayoutManager(layoutManager);
        recyclerPatientList.setItemAnimator(new DefaultItemAnimator());
        recyclerPatientList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        recyclerPatientList.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerPatientList, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if (isNetworkAvailable())
                {
                    getPatientDataWithRefID(populateData.get(0).getPatientid());
                }




            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

    }

}
