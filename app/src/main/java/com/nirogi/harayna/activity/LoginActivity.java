package com.nirogi.harayna.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.nirogi.harayna.R;
import com.nirogi.harayna.model.request.LoginModelRequest;
import com.nirogi.harayna.model.response.LoginModelResponse;
import com.nirogi.harayna.network.APIInterface;
import com.nirogi.harayna.network.ApiClient;
import com.nirogi.harayna.utils.BaseActivity;
import com.nirogi.harayna.utils.EncryptionHelper;
import com.nirogi.harayna.utils.JWTUtils;
import com.nirogi.harayna.utils.NIROGI;
import com.nirogi.harayna.utils.SharedParams;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private RelativeLayout relMain;
    private AppCompatEditText enEmailUser;
    private LinearLayout llPassword;
    private AppCompatEditText enPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = NIROGI.getInstance().getPreferences();
        setContentView(R.layout.activity_login);
        initView();
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogin) {
            if (isNetworkAvailable()) {
                if (!TextUtils.isEmpty(enEmailUser.getText()) && !TextUtils.isEmpty(enPassword.getText())) {
                    loginCheck();
                } else {
                    mShowToast("Please enter valid Credentials !");
                }
            } else {
                mShowToast(getString(R.string.no_internet));
            }
        }

    }

    private void mSendIntent()
    {
        Intent mIntent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(mIntent);
    }

    private void loginCheck() {
        if (isNetworkAvailable()) {
            try {
                createProgressBar(R.id.relMain);
                APIInterface apiInterface = ApiClient.getClientAuthentication().create(APIInterface.class);
                LoginModelRequest modelRequest= new LoginModelRequest();
                modelRequest.setUsername(enEmailUser.getText().toString());
                modelRequest.setPassword(enPassword.getText().toString());
                Call<LoginModelResponse> call = apiInterface.getLogin(modelRequest);
                call.enqueue(new Callback<LoginModelResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<LoginModelResponse> call, @NonNull Response<LoginModelResponse> response) {
                        try {
                            if (response.isSuccessful()) {
                                ArrayList<JSONObject> returnedObject=JWTUtils.parseJWT(response.body().getToken());
                                mSetDataToShared(returnedObject,response.body().getToken());

                            }else {
                                mShowToast(""+response.errorBody().string());
                            }
                            disableProgressBar();

                        } catch (Exception exp) {
                            Log.e(" Exception ", "" + exp.getMessage());
                            disableProgressBar();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<LoginModelResponse> call, @NonNull Throwable throwable) {
                        Log.e(" Exception ", "" + throwable.getMessage());
                        mShowToast("Api Failure");
                        disableProgressBar();
                    }
                });
            } catch (Exception exp) {
                Log.e(" Exception", "" + exp.getMessage());
                mShowToast("User does not exists or invalid username");
            }


        }
        else mShowToast(getString(R.string.no_internet));
    }




    private void initView() {


        relMain = findViewById(R.id.relMain);
        enEmailUser = findViewById(R.id.enEmailUser);
        llPassword = findViewById(R.id.ll_password);
        enPassword = findViewById(R.id.enPassword);
        AppCompatButton btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

    }

    private void mSetDataToShared(ArrayList<JSONObject> returnedObjectList,String authToken)
    {
        try {
            JSONObject decodedHeaderJson=returnedObjectList.get(0);
            JSONObject decodedPayloadJson=returnedObjectList.get(1);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(SharedParams.AUTH_TOKEN,authToken);
            editor.putString(SharedParams.FNAME,decodedHeaderJson.getString(SharedParams.FNAME));
            if(decodedHeaderJson.getString(SharedParams.LNAME)!=null || !decodedHeaderJson.getString(SharedParams.LNAME).equalsIgnoreCase("null"))
            {
                editor.putString(SharedParams.LNAME,decodedHeaderJson.getString(SharedParams.LNAME));
            }else {
                editor.putString(SharedParams.LNAME,"");
            }
            editor.putString(SharedParams.DISTRICT,decodedHeaderJson.getString(SharedParams.DISTRICT));
            editor.putString(SharedParams.FACILITY,decodedHeaderJson.getString(SharedParams.FACILITY));
            editor.putString(SharedParams.FACTYPE,decodedHeaderJson.getString(SharedParams.FACTYPE));
            editor.putString(SharedParams.ROLE,decodedHeaderJson.getString(SharedParams.ROLE));
            editor.putString(SharedParams.ALG,decodedHeaderJson.getString(SharedParams.ALG));
            editor.putString(SharedParams.SUB,decodedPayloadJson.getString(SharedParams.SUB));
            editor.putString(SharedParams.IAT,decodedPayloadJson.getString(SharedParams.IAT));
            editor.putString(SharedParams.EXP,decodedPayloadJson.getString(SharedParams.EXP));
            editor.apply();

            Intent mIntent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(mIntent);
        }catch (Exception _e)
        {
            Log.e(" Exception ",""+_e.getMessage());
        }

    }



}
