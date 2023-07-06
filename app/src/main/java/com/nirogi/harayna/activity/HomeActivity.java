package com.nirogi.harayna.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.nirogi.harayna.R;
import com.nirogi.harayna.model.request.SearchPPPIDRequest;
import com.nirogi.harayna.model.response.DistrictModel;
import com.nirogi.harayna.network.APIInterface;
import com.nirogi.harayna.network.ApiClient;
import com.nirogi.harayna.utils.BaseActivity;
import com.nirogi.harayna.utils.NIROGI;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;
import org.honorato.multistatetogglebutton.ToggleButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends BaseActivity {

    private AppCompatTextView homeUserName;
    private Spinner spinDistrict;
    private MultiStateToggleButton mstbSearchType;
    private LinearLayout lyPPPID;
    private AppCompatEditText enPPPID;
    private LinearLayout lyRefID;
    private AppCompatEditText enRefID;
    private AppCompatTextView submitSearch;

    private boolean mCheckType;
    String selDistrict;
    private ArrayList<String> mDistrictList, mDistrictListNames;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initListeners();
        getDistricts();
    }

    private void initView() {
        homeUserName = findViewById(R.id.homeUserName);
        spinDistrict = findViewById(R.id.spinDistrict);
        mstbSearchType = findViewById(R.id.mstbSearchType);
        lyPPPID =  findViewById(R.id.lyPPPID);
        enPPPID = findViewById(R.id.enPPPID);
        lyRefID =  findViewById(R.id.lyRefID);
        enRefID = findViewById(R.id.enRefID);
        submitSearch = findViewById(R.id.submitSearch);
        mstbSearchType.setValue(0);
        lyPPPID.setVisibility(View.VISIBLE);
    }
    private void initListeners()
    {
        mstbSearchType.setOnValueChangedListener(new ToggleButton.OnValueChangedListener() {
            @Override
            public void onValueChanged(int position) {
                if(position==0)
                {
                    lyPPPID.setVisibility(View.VISIBLE);
                    lyRefID.setVisibility(View.GONE);
                }else {
                    lyRefID.setVisibility(View.VISIBLE);
                    lyPPPID.setVisibility(View.GONE);

                }
            }
        });
        submitSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spinDistrict.getSelectedItemPosition()>0)
                {
                    if(mstbSearchType.getValue()==0)
                    {
                        if(!TextUtils.isEmpty(enPPPID.getText()))
                        {
                            Intent mIntent= new Intent(HomeActivity.this, SearchedPPPIDDetails.class);
                            mIntent.putExtra("PPPID",enPPPID.getText().toString());
                            startActivity(mIntent);
                        }else {
                            mShowToast("Please Enter PPPID to search");
                        }

                    }else {
                        if(!TextUtils.isEmpty(enRefID.getText()))
                        {
                            Intent mIntent= new Intent(HomeActivity.this, SearchedReferenceIDDetails.class);
                            startActivity(mIntent);
                        }else {
                            mShowToast("Please Enter Reference to search");
                        }
                    }
                }else {
                    mShowToast("Please Select district to search");
                }

            }
        });
    }

    private void getDistricts() {
        if (isNetworkAvailable()) {
            try {
                createProgressBar(R.id.relMain);
                APIInterface apiInterface = ApiClient.getClientAuthenticationWithAuth(NIROGI.token).create(APIInterface.class);
                Call<ArrayList<DistrictModel>> call = apiInterface.getDistList();
                call.enqueue(new Callback<ArrayList<DistrictModel>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<DistrictModel>> call, @NonNull Response<ArrayList<DistrictModel>> response) {
                        try {
                            try {
                                if (response.isSuccessful()) {

                                    mDistrictList = new ArrayList<>();
                                    mDistrictListNames = new ArrayList<>();
                                    mDistrictList.add(getString(R.string.sel_district));
                                    mDistrictListNames.add(getString(R.string.sel_district));
                                    if(response.body().size()>0)
                                    {
                                        for (DistrictModel model:response.body())
                                        {
                                            mDistrictList.add(model.getDistid()+"");
                                            mDistrictListNames.add(model.getDistname());
                                        }
                                        ArrayAdapter<String> adapter =new ArrayAdapter<String>(HomeActivity.this,android.R.layout.simple_spinner_item, mDistrictListNames);
                                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                        spinDistrict.setAdapter(adapter);
                                        spinDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                if(position>0)
                                                {
                                                    selDistrict=mDistrictList.get(position);

                                                }

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });
                                    }
                                }
                            }catch (Exception e)
                            {
                                mShowToast(getString(R.string.api_failure));
                            }

                            disableProgressBar();

                        } catch (Exception exp) {
                            Log.e(" Exception ", "" + exp.getMessage());
                            disableProgressBar();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<DistrictModel>> call, @NonNull Throwable throwable) {
                        Log.e(" Exception ", "" + throwable.getMessage());
                        disableProgressBar();
                    }
                });
            } catch (Exception exp) {
                Log.e(" Exception", "" + exp.getMessage());
            }


        }
        else mShowToast(getString(R.string.no_internet));
    }
}
