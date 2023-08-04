package com.nirogi.harayna.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.nirogi.harayna.model.request.SearchPatientFromDataRequest;
import com.nirogi.harayna.model.request.SearchReferenceIDRRequest;
import com.nirogi.harayna.model.response.DistrictModel;
import com.nirogi.harayna.model.response.PatientListModelResponse;
import com.nirogi.harayna.model.response.ReferenceIdResponse;
import com.nirogi.harayna.network.APIInterface;
import com.nirogi.harayna.network.ApiClient;
import com.nirogi.harayna.utils.BaseActivity;
import com.nirogi.harayna.utils.IntentParams;
import com.nirogi.harayna.utils.NIROGI;
import com.nirogi.harayna.utils.SharedParams;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;
import org.honorato.multistatetogglebutton.ToggleButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends BaseActivity {

    private AppCompatTextView homeUserName;
    private Spinner spinDistrict,spinOtherInfoDistrict;
    private MultiStateToggleButton mstbSearchType;
    private LinearLayout lyPPPID,lyOtherInfo;
    private AppCompatEditText enPPPID;
    private LinearLayout lyRefID;
    private AppCompatEditText enRefID,enOtherMobileNo,enOtherName;
    private AppCompatTextView submitSearch;

    private boolean mCheckType;
    String selDistrict,selOtherDistrict;
    private ArrayList<String> mDistrictList, mDistrictListNames;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sharedPreferences=NIROGI.getInstance().getPreferences();
        initView();
        initDataToView();
        initListeners();
        getDistricts();

    }


    private void initDataToView()
    {
        homeUserName.setText(sharedPreferences.getString(SharedParams.FNAME,"")+" "+sharedPreferences.getString(SharedParams.LNAME,""));;
    }

    private void initView() {
        homeUserName = findViewById(R.id.homeUserName);
        spinDistrict = findViewById(R.id.spinDistrict);
        spinOtherInfoDistrict= findViewById(R.id.spinOtherInfoDistrict);
        mstbSearchType = findViewById(R.id.mstbSearchType);
        lyPPPID = findViewById(R.id.lyPPPID);
        enPPPID = findViewById(R.id.enPPPID);
        lyRefID = findViewById(R.id.lyRefID);
        enRefID = findViewById(R.id.enRefID);
        submitSearch = findViewById(R.id.submitSearch);
        mstbSearchType.setValue(0);
        lyPPPID.setVisibility(View.VISIBLE);
        lyOtherInfo= findViewById(R.id.lyOtherInfo);
        enOtherMobileNo = findViewById(R.id.enOtherMobileNo);
        enOtherName = findViewById(R.id.enOtherName);

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
                    lyOtherInfo.setVisibility(View.GONE);
                }else if(position==1) {
                    lyRefID.setVisibility(View.VISIBLE);
                    lyOtherInfo.setVisibility(View.GONE);
                    lyPPPID.setVisibility(View.GONE);
                }else {
                    lyRefID.setVisibility(View.GONE);
                    lyOtherInfo.setVisibility(View.VISIBLE);
                    lyPPPID.setVisibility(View.GONE);
                }
            }
        });
        submitSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(mstbSearchType.getValue()==0)
                    {
                        if(!TextUtils.isEmpty(enPPPID.getText()))
                        {
                            getPatientListWithInput(0);

                        }else {
                            mShowToast("Please Enter PPPID to search");
                        }

                    }else if(mstbSearchType.getValue()==1) {
                        if(!TextUtils.isEmpty(enRefID.getText()))
                        {
                            getPatientListWithRefID(enRefID.getText().toString());
                        }else {
                            mShowToast("Please Enter Reference to search");
                        }
                    }else {
                        if(validateOtherSearch())
                        {
                            getPatientListWithInput(2);

                        }
                    }
            }
        });

        findViewById(R.id.lyLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogout(HomeActivity.this,true);

            }
        });
    }

    private boolean validateOtherSearch()
    {
        if(spinOtherInfoDistrict.getSelectedItemPosition()==0)
        {
            mShowToast("Please select District!");
            return false;
        }else if (TextUtils.isEmpty(enOtherName.getText()))
        {
            mShowToast("Please enter first name !");
            return false;
        }else if(TextUtils.isEmpty(enOtherMobileNo.getText()))
        {
            mShowToast("Please enter mobile number !");
            return false;
        }else {
            return true;
        }

    }

    private void getDistricts() {
        if (isNetworkAvailable())
        {
            try {
                createProgressBar(R.id.relHMain);
                APIInterface apiInterface = ApiClient.getClientAuthenticationWithAuth(preferences.getString(SharedParams.AUTH_TOKEN,"")).create(APIInterface.class);
                Call<ArrayList<DistrictModel>> call = apiInterface.getDistList();
                call.enqueue(new Callback<ArrayList<DistrictModel>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<DistrictModel>> call, @NonNull Response<ArrayList<DistrictModel>> response) {
                        try {
                            try {
                                if (response.isSuccessful())
                                {
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
                                        ArrayAdapter<String> adapter =new ArrayAdapter<String>(HomeActivity.this,R.layout.spinner_text, mDistrictListNames);
                                        adapter.setDropDownViewResource(R.layout.spinner_text);
                                        spinDistrict.setAdapter(adapter);
                                        spinOtherInfoDistrict.setAdapter(adapter);
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
                                        spinOtherInfoDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                if(position>0)
                                                {
                                                    selOtherDistrict=mDistrictList.get(position);
                                                }

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });
                                    }
                                }
                                else
                                {
                                    mHandleApiErrorCode(response.code(),response.errorBody().string(),HomeActivity.this);
                                    disableProgressBar();
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
            }
            catch (Exception exp) {
                Log.e(" Exception", "" + exp.getMessage());
            }
        }

    }

    public void getPatientListWithInput(int type)
    {
        try {
            createProgressBar(R.id.relHMain);
            APIInterface apiInterface = ApiClient.getClientAuthenticationWithAuth(preferences.getString(SharedParams.AUTH_TOKEN,"")).create(APIInterface.class);
            Call<ArrayList<PatientListModelResponse>> call = null;
            if(type==0)
            {
                SearchPPPIDRequest request= new SearchPPPIDRequest();
                request.setPppId(enPPPID.getText().toString());
                call = apiInterface.getSearchedPPPIDPatients(request);
            }else if(type==2)
            {
                SearchPatientFromDataRequest request= new SearchPatientFromDataRequest();
                request.setDistrict(spinOtherInfoDistrict.getSelectedItem()+"");
                request.setMobileNo(enOtherMobileNo.getText().toString());
                request.setFirstname(enOtherName.getText().toString());
                call = apiInterface.getSearchedPatientsFromData(request);
            }
            call.enqueue(new Callback<ArrayList<PatientListModelResponse>>() {
                @Override
                public void onResponse(Call<ArrayList<PatientListModelResponse>> call, Response<ArrayList<PatientListModelResponse>> response) {
                    try {
                        if (response.isSuccessful()) {

                            if(response.body().size()>0)
                            {
                                ArrayList<PatientListModelResponse> mDataList= new ArrayList<>(response.body());
                                Intent mIntent= new Intent(HomeActivity.this, SearchedPPPIDDetails.class);
                                mIntent.putExtra("mData",mDataList);
                                startActivity(mIntent);
                            }else {
                                mShowToast("No Record Found !");
                            }
                            disableProgressBar();
                        }else{
                            disableProgressBar();
                            mHandleApiErrorCode(response.code(),response.errorBody().string(),HomeActivity.this);

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

    public void getPatientListWithRefID(String inputTextPPP)
    {
        try {
            createProgressBar(R.id.relHMain);
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
                                ArrayList<ReferenceIdResponse> mDataList= new ArrayList<>(response.body());
                                Intent mIntent= new Intent(HomeActivity.this, SearchedReferenceIdDetails.class);
                                mIntent.putExtra(IntentParams.M_DATA,mDataList);
                                mIntent.putExtra(IntentParams.REFERENCE_ID,inputTextPPP);
                                startActivity(mIntent);
                            }else {
                                mShowToast("No Record Found !");
                            }
                           disableProgressBar();


                        }else{
                            mHandleApiErrorCode(response.code(),response.errorBody().string(),HomeActivity.this);
                            disableProgressBar();
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




}
