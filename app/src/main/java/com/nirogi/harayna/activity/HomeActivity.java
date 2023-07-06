package com.nirogi.harayna.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.nirogi.harayna.R;
import com.nirogi.harayna.utils.BaseActivity;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;
import org.honorato.multistatetogglebutton.ToggleButton;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initListeners();
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
                    if(mCheckType)
                    {
                        if(!TextUtils.isEmpty(enPPPID.getText()))
                        {

                        }else {
                            mShowToast("Please Enter PPPID to search");
                        }

                    }else {
                        if(!TextUtils.isEmpty(enRefID.getText()))
                        {

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
}
