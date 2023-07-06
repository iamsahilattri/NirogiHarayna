package com.nirogi.harayna.activity;

import android.content.Intent;
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
import com.nirogi.harayna.utils.Nirogi;

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
        preferences = Nirogi.getInstance().getPreferences();
        setContentView(R.layout.activity_login);
        initView();
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogin) {
            if (isNetworkAvailable()) {
                mSendIntent();

//                if (!TextUtils.isEmpty(enEmailUser.getText()) && !TextUtils.isEmpty(enPassword.getText())) {
////                    loginCheck();
////                    mSendIntent();
//                } else {
//                    mShowToast("Please enter valid Credentials !");
//                }
//            } else {
//                mShowToast(getString(R.string.no_internet));
//            }
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
                modelRequest.setUsername("");
                modelRequest.setPassword("");
                Call<LoginModelResponse> call = apiInterface.getLogin(modelRequest);
                call.enqueue(new Callback<LoginModelResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<LoginModelResponse> call, @NonNull Response<LoginModelResponse> response) {
                        try {
//                            if (response.body().getOutput().equalsIgnoreCase("success")) {
//                                Intent mIntent = new Intent(LoginActivity.this, HomeActivity.class);
//                                mIntent.putExtra("token", "" + enMobileNumber.getText().toString());
//                                startActivity(mIntent);
//                            }
                            disableProgressBar();

                        } catch (Exception exp) {
                            Log.e(" Exception ", "" + exp.getMessage());
                            disableProgressBar();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<LoginModelResponse> call, @NonNull Throwable throwable) {
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


    private void initView() {


        relMain = findViewById(R.id.relMain);
        enEmailUser = findViewById(R.id.enEmailUser);
        llPassword = findViewById(R.id.ll_password);
        enPassword = findViewById(R.id.enPassword);
        AppCompatButton btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

    }

    private String encryptData(String valueToEncrypt) {
        return "";
//        this.encryptObj.username=CryptoJS.AES.encrypt(JSON.stringify(this.credential.username), "EDUNPS").toString();
//        this.encryptObj.password=CryptoJS.AES.encrypt(JSON.stringify(this.credential.password), "EDUNPS").toString();

    }
}
