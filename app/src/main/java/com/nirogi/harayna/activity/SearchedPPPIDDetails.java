package com.nirogi.harayna.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import com.nirogi.harayna.adapter.PatientListAdapter;
import com.nirogi.harayna.model.request.SearchPPPIDRequest;
import com.nirogi.harayna.model.response.PatientListModelResponse;
import com.nirogi.harayna.model.response.ReferredSurveyDataResponse;
import com.nirogi.harayna.network.APIInterface;
import com.nirogi.harayna.network.ApiClient;
import com.nirogi.harayna.utils.BaseActivity;
import com.nirogi.harayna.utils.IntentParams;
import com.nirogi.harayna.utils.NIROGI;
import com.nirogi.harayna.utils.RecyclerItemClickListener;
import com.nirogi.harayna.utils.SharedParams;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchedPPPIDDetails extends BaseActivity {

    private RecyclerView recyclerPatientList;
    private SharedPreferences preferences;
    private SwipeRefreshLayout swipeDown;
    private TextView noDataLy;
    private String PPPID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_pppid);
        preferences= NIROGI.getInstance().getPreferences();
        mSetBackToolbar(SearchedPPPIDDetails.this,"Patient List",false,"");
        initViews();
        setRecyclerFromIntentData();
    }

    private void setRecyclerFromIntentData()
    {
        if(getIntent()!=null)
        {
            PPPID=getIntent().getStringExtra("PPPID");
            ArrayList<PatientListModelResponse> intentDataList =(ArrayList<PatientListModelResponse>)getIntent().getSerializableExtra("mData");
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
                    getPatientList();
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


    public void getPatientList()
    {
        try {
            createProgressBar(R.id.relMain);
            APIInterface apiInterface = ApiClient.getClientAuthenticationWithAuth(preferences.getString(SharedParams.AUTH_TOKEN,"")).create(APIInterface.class);
            SearchPPPIDRequest request= new SearchPPPIDRequest();
            request.setPppId(PPPID);
            Call<ArrayList<PatientListModelResponse>> call = apiInterface.getSearchedPPPIDPatients(request);
            call.enqueue(new Callback<ArrayList<PatientListModelResponse>>() {
                @Override
                public void onResponse(Call<ArrayList<PatientListModelResponse>> call, Response<ArrayList<PatientListModelResponse>> response) {
                    try {
                        if (response.isSuccessful()) {

                            if(response.body().size()>0)
                            {
                                ArrayList<PatientListModelResponse> mDataList= new ArrayList<>(response.body());
                                mSetRecyclerData(mDataList);
                            }else
                            {
                                noDataLy.setVisibility(View.VISIBLE);
                                recyclerPatientList.setVisibility(View.GONE);
                            }
                            disableProgressBar();
                        }else{
                            mHandleApiErrorCode(response.code(),response.errorBody().string(), SearchedPPPIDDetails.this);
                            disableProgressBar();
                        }
                    }catch (Exception e)
                    {
                        Log.e(" Exception ",""+e.getMessage());
                        disableProgressBar();
                    }


                }

                @Override
                public void onFailure(Call<ArrayList<PatientListModelResponse>> call, Throwable t) {
                    mShowToast(getString(R.string.api_failure));
                    disableProgressBar();
                }
            });
        }catch (Exception ee)
        {
            Log.e(" Exception ",""+ee.getMessage());
        }

    }

    private void mSetRecyclerData(ArrayList<PatientListModelResponse> populateData)
    {
        noDataLy.setVisibility(View.GONE);
        recyclerPatientList.setVisibility(View.VISIBLE);
        PatientListAdapter mAdapter = new PatientListAdapter(SearchedPPPIDDetails.this, populateData);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerPatientList.setLayoutManager(layoutManager);
        recyclerPatientList.setItemAnimator(new DefaultItemAnimator());
        recyclerPatientList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        recyclerPatientList.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerPatientList, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(populateData.get(position).getFlagcheck()==null)
                {
                    int ageValue=Integer.parseInt(populateData.get(position).getAge());
                    Intent mIntent = null;
                    if(ageValue == 0)
                    {
                        mIntent= new Intent(SearchedPPPIDDetails.this, CategoryIPatientEntryActivity.class);

                    }
                    else if(ageValue >=1 && ageValue <5)
                    {
                        mIntent= new Intent(SearchedPPPIDDetails.this, CategoryIIPatientEntryActivity.class);

                    }
                    else if(ageValue>=5 && ageValue<=18)
                    {
                        mIntent= new Intent(SearchedPPPIDDetails.this, CategoryIIIPatientEntryActivity.class);

                    }
                    else if(ageValue>18 && ageValue<=40)
                    {
                        mIntent= new Intent(SearchedPPPIDDetails.this, CategoryIVPatientEntryActivity.class);
                    }
                    else if(ageValue>40 && ageValue<=60)
                    {
                        mIntent= new Intent(SearchedPPPIDDetails.this, CategoryVPatientEntryActivity.class);
                    }
                    else if(ageValue>60)
                    {
                        mIntent= new Intent(SearchedPPPIDDetails.this, CategoryVIPatientEntryActivity.class);
                    }

                    assert mIntent != null;
                    mIntent.putExtra(IntentParams.MEMBER_DATA, populateData.get(position));
                    mIntent.putExtra(IntentParams.RECORDER_DATA,new ReferredSurveyDataResponse());
                    mIntent.putExtra(IntentParams.MEMBER_TYPE, "1");
                    startActivity(mIntent);
                }
                else {
                    mShowToast("Already Submitted !");
                }


            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

    }





}
