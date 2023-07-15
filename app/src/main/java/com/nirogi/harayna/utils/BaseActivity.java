package com.nirogi.harayna.utils;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nirogi.harayna.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseActivity extends AppCompatActivity {

    public SharedPreferences preferences;
    ProgressBar progressBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = NIROGI.getInstance().getPreferences();

    }

    public void mSetBackToolbar(String mValue,boolean mCheck,String desText)
    {
        TextView title= findViewById(R.id.title);
        if(mCheck)
        {
            TextView titleDesc= findViewById(R.id.titleDesc);
            titleDesc.setText(desText);
            titleDesc.setVisibility(View.VISIBLE);
        }

        title.setText(mValue);
        findViewById(R.id.backBTN).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void mSetSharedPref(String mData)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SharedParams.LANG,mData+"");
        editor.apply();

    }

    public  boolean isNetworkAvailable() {

        final ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            if (Build.VERSION.SDK_INT < 23) {
                final NetworkInfo ni = cm.getActiveNetworkInfo();
                if (ni != null) {
                    return (ni.isConnected() && (ni.getType() == ConnectivityManager.TYPE_WIFI || ni.getType() == ConnectivityManager.TYPE_MOBILE));
                }
            } else {
                final Network n = cm.getActiveNetwork();
                if (n != null) {
                    final NetworkCapabilities nc = cm.getNetworkCapabilities(n);
                    return (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
                }
            }
        }
        return false;
    }

    public void faliureExecptions(Activity mContext,Throwable throwable)
    {
        if(throwable!=null)
        {
            if(throwable.getMessage().contains("JsonReader"))
            {
                mShowToast("Please try After Some Time ! ");

            }else if(throwable.getMessage().contains("failed to connect"))
            {
                mShowToast("Please Check you internet Connection & Try again !");

            }
            else if(throwable.getMessage().contains("Failed to connect"))
            {
                mShowToast("Please Check you internet Connection & Try again !");

            }
            else if(throwable.getMessage().contains("unexpected end of stream on"))
            {
                mShowToast("Connectivity Issue & Please Retry  !");
            }else
            {
                mShowToast(getString(R.string.api_failure));
            }
        }

    }

    public void mShowToast(String mShowMsg)
    {
        Toast.makeText(BaseActivity.this,mShowMsg,Toast.LENGTH_LONG).show();
    }


    public  boolean isValidMobile(String strMobile)
    {
        // The given argument to compile() method
        // is regular expression. With the help of
        // regular expression we can validate mobile
        // number.
        // 1) Begins with 0 or 91
        // 2) Then contains 7 or 8 or 9.
        // 3) Then contains 9 digits
        Pattern p = Pattern.compile("(0/91)?[6-9][0-9]{9}");

        // Pattern class contains matcher() method
        // to find matching between given number
        // and regular expression
        Matcher m = p.matcher(strMobile);
        return (m.find() && m.group().equals(strMobile));
    }


    public void createProgressBar(int layoutID)
    {
        RelativeLayout layout= findViewById(layoutID);
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, 200);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        layout.addView(progressBar, params);
    }

    public void disableProgressBar()
    {
        if(progressBar!=null)
        {
            progressBar.setVisibility(View.GONE);
        }
    }




}
