package com.nirogi.harayna.activity;

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
import com.nirogi.harayna.adapter.PatientDetailRefrenceIdAdapter;
import com.nirogi.harayna.model.request.SearchReferenceIDRRequest;
import com.nirogi.harayna.model.response.ReferenceIdResponse;
import com.nirogi.harayna.network.APIInterface;
import com.nirogi.harayna.network.ApiClient;
import com.nirogi.harayna.utils.BaseActivity;
import com.nirogi.harayna.utils.NIROGI;
import com.nirogi.harayna.utils.RecyclerItemClickListener;
import com.nirogi.harayna.utils.SharedParams;

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
            referenceId=getIntent().getStringExtra("referenceId");
            ArrayList<ReferenceIdResponse> intentDataList =(ArrayList<ReferenceIdResponse>)getIntent().getSerializableExtra("mData");
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
            createProgressBar(R.id.relMain);
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
//                Log.e("AGE GROUP",""+populateData.get(position).getAge());
//                int ageValue=Integer.parseInt(populateData.get(position).getAge());
//                if(ageValue == 0)
//                {
//                    Intent mIntent= new Intent(SearchedReferenceIdDetails.this, CategoryIPatientEntryActivity.class);
//                    mIntent.putExtra("memberData",populateData.get(position));
//                    startActivity(mIntent);
//                }else if(ageValue >=1 && ageValue <5)
//                {
//                    Intent mIntent= new Intent(SearchedReferenceIdDetails.this, CategoryIIPatientEntryActivity.class);
//                    mIntent.putExtra("memberData",populateData.get(position));
//                    startActivity(mIntent);
//                }else if(ageValue>=5 && ageValue<=18)
//                {
//                    Intent mIntent= new Intent(SearchedReferenceIdDetails.this, CategoryIIIPatientEntryActivity.class);
//                    mIntent.putExtra("memberData",populateData.get(position));
//                    startActivity(mIntent);
//                }
//                else if(ageValue>18 && ageValue<=40)
//                {
//                    Intent mIntent= new Intent(SearchedReferenceIdDetails.this, CategoryIVPatientEntryActivity.class);
//                    mIntent.putExtra("memberData",populateData.get(position));
//                    startActivity(mIntent);
//                }else if(ageValue>40 && ageValue<=60)
//                {
//                    Intent mIntent= new Intent(SearchedReferenceIdDetails.this, CategoryVPatientEntryActivity.class);
//                    mIntent.putExtra("memberData",populateData.get(position));
//                    startActivity(mIntent);
//                }else if(ageValue>60)
//                {
//                    Intent mIntent= new Intent(SearchedReferenceIdDetails.this, CategoryVIPatientEntryActivity.class);
//                    mIntent.putExtra("memberData",populateData.get(position));
//                    startActivity(mIntent);
//                }

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

    }

}
