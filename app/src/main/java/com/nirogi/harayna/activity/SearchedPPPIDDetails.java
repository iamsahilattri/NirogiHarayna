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
import com.nirogi.harayna.adapter.PatientListAdapter;
import com.nirogi.harayna.model.request.SearchPPPIDRequest;
import com.nirogi.harayna.model.response.PatientListModelResponse;
import com.nirogi.harayna.network.APIInterface;
import com.nirogi.harayna.network.ApiClient;
import com.nirogi.harayna.utils.BaseActivity;
import com.nirogi.harayna.utils.NIROGI;
import com.nirogi.harayna.utils.RecyclerItemClickListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchedPPPIDDetails extends BaseActivity {

    private RecyclerView recyclerPatientList;
    private SharedPreferences preferences;
    private SwipeRefreshLayout swipeDown;
    private ArrayList<PatientListModelResponse> mDataList;
    private EditText searchItem;
    private TextView noDataLy;
    private String PPPID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_pppid);
        preferences= NIROGI.getInstance().getPreferences();
        mSetBackToolbar("Patient List");
        if(getIntent()!=null)
        {
            PPPID=getIntent().getStringExtra("PPPID");
        }
        initViews();
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

    @Override
    protected void onStart() {
        super.onStart();
        if(isNetworkAvailable())
        {
            getPatientList();
        }
    }

    public void getPatientList()
    {
        try {
            createProgressBar(R.id.relMain);
            APIInterface apiInterface = ApiClient.getClientAuthenticationWithAuth(NIROGI.token).create(APIInterface.class);
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
                                mDataList= new ArrayList<>(response.body());
                                PatientListAdapter mAdapter = new PatientListAdapter(SearchedPPPIDDetails.this, mDataList);
                                LinearLayoutManager layoutManager= new LinearLayoutManager(getApplicationContext());
                                recyclerPatientList.setLayoutManager(layoutManager);
                                recyclerPatientList.setItemAnimator(new DefaultItemAnimator());
                                recyclerPatientList.setAdapter(mAdapter);
                                mAdapter.notifyDataSetChanged();
                                recyclerPatientList.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerPatientList, new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        Intent mIntent= new Intent(SearchedPPPIDDetails.this, CategoryIIIPatientEntryActivity.class);
                                        startActivity(mIntent);
                                    }

                                    @Override
                                    public void onLongItemClick(View view, int position) {

                                    }
                                }));
                                noDataLy.setVisibility(View.GONE);
                                recyclerPatientList.setVisibility(View.VISIBLE);
                                disableProgressBar();
                            }else
                            {
                                noDataLy.setVisibility(View.VISIBLE);
                                recyclerPatientList.setVisibility(View.GONE);
                                disableProgressBar();
                            }

                        }else{
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





}
