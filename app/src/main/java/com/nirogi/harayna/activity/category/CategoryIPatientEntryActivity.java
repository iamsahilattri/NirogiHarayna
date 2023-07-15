package com.nirogi.harayna.activity.category;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.nirogi.harayna.R;
import com.nirogi.harayna.model.response.PatientListModelResponse;
import com.nirogi.harayna.utils.BaseActivity;

public class CategoryIPatientEntryActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout mRelMain;
    private SwipeRefreshLayout mSwipeDown;
    private LinearLayout mInfoLy;
    private AppCompatTextView mTxtPatientPPPID;
    private AppCompatTextView mTxtPatientName;
    private AppCompatTextView mTxtPatientGenderAge;
    private AppCompatTextView mTxtPatientMobile;
    private AppCompatTextView mTxtPatientAddress;
    private AppCompatTextView mTxtPatientDistrict;
    private AppCompatTextView mTxtFacilityName;
    private AppCompatTextView mTxtFacilityIncharge;
    private AppCompatTextView mTxtDate;
    private LinearLayout mLyInput;
    private LinearLayout mLyGenPhysicalEx;
    private AppCompatImageView mIconGenPhy;
    private LinearLayout mLyGenPhysicalxValue;

    private LinearLayout mLySysExa;
    private AppCompatImageView mIconSysExa;
    private LinearLayout mLySysExaValue;

    private LinearLayout mLyMandatoryInvest;
    private AppCompatImageView mIconMandatoryInvest;
    private LinearLayout mLyMandatoryInvestValue;
 
    private LinearLayout mLyDiagnosis;
    private AppCompatImageView mIconDiagnosis;
    private LinearLayout mLyDiagnosisValue;
    private AppCompatSpinner mDropDiagnosis;
    private AppCompatCheckBox mChkDAlreadyKnown;
    private LinearLayout mLyPrescription;
    private AppCompatImageView mIconPrescription;
    private LinearLayout mLyPrescriptionValue;
    private AppCompatEditText mInputPrescription;

    private LinearLayout lyHistoryEx;
    private AppCompatImageView iconHistory;
    private LinearLayout lyHistoryExValue;


    private boolean mClickOne=true,mClickTwo=true,mClickThree=true,mClickFour=true,mClickFive=true,mClickSix=true;

    PatientListModelResponse memberData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_input_cat_i);
        initView();
        mSetValuesToViews();
    }

    private void mSetValuesToViews()
    {
        try {
            if(getIntent()!=null)
            {
                memberData=(PatientListModelResponse)getIntent().getSerializableExtra("memberData");
                if(memberData!=null)
                {
                    mSetBackToolbar("Patient Details",true,"Category I (0-6 Months)");
                    mTxtPatientPPPID.setText(memberData.getPppid()+"");
                    mTxtPatientName.setText(memberData.getFirstname()+" "+memberData.getLastname());
                    mTxtPatientGenderAge.setText(memberData.getGender()+"");
                    mTxtPatientMobile.setText(memberData.getMobileno()+"");
                    mTxtPatientAddress.setText(memberData.getAddress()+"");
                    mTxtPatientDistrict.setText(memberData.getDistrict()+"");
                }
            }
        }catch (Exception e)
        {
            Log.e(" Exception memberData",""+e.getMessage());
        }
    }

    private void initView() {
        mRelMain = findViewById(R.id.cIrelMain);
        mSwipeDown = findViewById(R.id.cIswipeDown);
        mInfoLy =findViewById(R.id.cIinfoLy);
        mTxtPatientPPPID =findViewById(R.id.cItxtPatientPPPID);
        mTxtPatientName =findViewById(R.id.cItxtPatientName);
        mTxtPatientGenderAge =findViewById(R.id.cItxtPatientGenderAge);
        mTxtPatientMobile =findViewById(R.id.cItxtPatientMobile);
        mTxtPatientAddress =findViewById(R.id.cItxtPatientAddress);
        mTxtPatientDistrict =findViewById(R.id.cItxtPatientDistrict);
        mTxtFacilityName =findViewById(R.id.cItxtFacilityName);
        mTxtFacilityIncharge =findViewById(R.id.cItxtFacilityIncharge);
        mTxtDate =findViewById(R.id.cItxtDate);
        mLyInput =findViewById(R.id.cIlyInput);
        mLyGenPhysicalEx =findViewById(R.id.cIlyGenPhysicalEx);
        mLyGenPhysicalEx.setOnClickListener(this);
        mIconGenPhy =  findViewById(R.id.cIiconGenPhy);
        mLyGenPhysicalxValue =findViewById(R.id.cIlyGenPhysicalxValue);

        mLySysExa =findViewById(R.id.cIlySysExa);
        mLySysExa.setOnClickListener(this);
        mIconSysExa =  findViewById(R.id.cIiconSysExa);
        mLySysExaValue =findViewById(R.id.cIlySysExaValue);
      
        mLyMandatoryInvest =findViewById(R.id.cIlyMandatoryInvest);
        mLyMandatoryInvest.setOnClickListener(this);
        mIconMandatoryInvest = findViewById(R.id.cIiconMandatoryInvest);
        mLyMandatoryInvestValue =findViewById(R.id.cIlyMandatoryInvestValue);

        mLyDiagnosis =findViewById(R.id.cIlyDiagnosis);
        mLyDiagnosis.setOnClickListener(this);
        mIconDiagnosis =  findViewById(R.id.cIiconDiagnosis);
        mLyDiagnosisValue =findViewById(R.id.cIlyDiagnosisValue);
        mDropDiagnosis = findViewById(R.id.cIdropDiagnosis);
        mChkDAlreadyKnown = findViewById(R.id.cIchkDAlreadyKnown);
        mLyPrescription =findViewById(R.id.cIlyPrescription);
        mLyPrescription.setOnClickListener(this);
        mIconPrescription =  findViewById(R.id.cIiconPrescription);
        mLyPrescriptionValue =findViewById(R.id.cIlyPrescriptionValue);
        mInputPrescription =findViewById(R.id.cIinputPrescription);


        lyHistoryEx =  findViewById(R.id.cIlyHistoryEx);
        lyHistoryEx.setOnClickListener(this);
        iconHistory =findViewById(R.id.cIiconHistory);
        lyHistoryExValue =findViewById(R.id.cIlyHistoryExValue);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.cIlyGenPhysicalEx) {
            if(mClickOne)
            {
                mShowHideLayouts(0);
                mClickOne=false;
            }else {
                mShowHideLayoutsAll();
                mClickOne=true;
            }
        }
        if (view.getId() == R.id.cIlySysExa) {
            if(mClickTwo)
            {
                mShowHideLayouts(1);
                mClickTwo=false;
            }else {
                mShowHideLayoutsAll();
                mClickTwo=true;
            }
        }
        if (view.getId() == R.id.cIlyMandatoryInvest) {
            if(mClickThree)
            {
                mShowHideLayouts(2);
                mClickThree=false;
            }else {
                mShowHideLayoutsAll();
                mClickThree=true;
            }
        }
        if (view.getId() == R.id.cIlyDiagnosis) {
            if(mClickFour)
            {
                mShowHideLayouts(3);
                mClickFour=false;
            }else {
                mShowHideLayoutsAll();
                mClickFour=true;
            }
        }
        if (view.getId() == R.id.cIlyPrescription) {
            if(mClickFive)
            {
                mShowHideLayouts(4);
                mClickFive=false;
            }else {
                mShowHideLayoutsAll();
                mClickFive=true;
            }
        }
        if (view.getId() == R.id.cIlyHistoryEx) {
            if(mClickSix)
            {
                mShowHideLayouts(5);
                mClickSix=false;
            }else {
                mShowHideLayoutsAll();
                mClickSix=true;
            }
        }

    }

    private void mShowHideLayouts(int clicked)
    {
        if(clicked==0)
        {
            iconHistory.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconGenPhy.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_up));
            mIconSysExa.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconMandatoryInvest.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconDiagnosis.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconPrescription.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));

            lyHistoryExValue.setVisibility(View.GONE);
            mLyGenPhysicalxValue.setVisibility(View.VISIBLE);
            mLySysExaValue.setVisibility(View.GONE);
            mLyMandatoryInvestValue.setVisibility(View.GONE);
            mLyDiagnosisValue.setVisibility(View.GONE);
            mLyPrescriptionValue.setVisibility(View.GONE);
        }else if(clicked==1)
        {
            iconHistory.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconGenPhy.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconSysExa.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_up));
            mIconMandatoryInvest.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconDiagnosis.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconPrescription.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));

            lyHistoryExValue.setVisibility(View.GONE);
            mLyGenPhysicalxValue.setVisibility(View.GONE);
            mLySysExaValue.setVisibility(View.VISIBLE);
            mLyMandatoryInvestValue.setVisibility(View.GONE);
            mLyDiagnosisValue.setVisibility(View.GONE);
            mLyPrescriptionValue.setVisibility(View.GONE);
        }
        else if(clicked==2)
        {
            iconHistory.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconGenPhy.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconSysExa.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconMandatoryInvest.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_up));
            mIconDiagnosis.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconPrescription.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));

            lyHistoryExValue.setVisibility(View.GONE);
            mLyGenPhysicalxValue.setVisibility(View.GONE);
            mLySysExaValue.setVisibility(View.GONE);
            mLyMandatoryInvestValue.setVisibility(View.VISIBLE);
            mLyDiagnosisValue.setVisibility(View.GONE);
            mLyPrescriptionValue.setVisibility(View.GONE);
        }
        else if(clicked==3)
        {
            iconHistory.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconGenPhy.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconSysExa.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconMandatoryInvest.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconDiagnosis.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_up));
            mIconPrescription.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));

            lyHistoryExValue.setVisibility(View.GONE);
            mLyGenPhysicalxValue.setVisibility(View.GONE);
            mLySysExaValue.setVisibility(View.GONE);
            mLyMandatoryInvestValue.setVisibility(View.GONE);
            mLyDiagnosisValue.setVisibility(View.VISIBLE);
            mLyPrescriptionValue.setVisibility(View.GONE);
        }
        else if(clicked==4)
        {
            iconHistory.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconGenPhy.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconSysExa.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconMandatoryInvest.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconDiagnosis.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconPrescription.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_up));

            lyHistoryExValue.setVisibility(View.GONE);
            mLyGenPhysicalxValue.setVisibility(View.GONE);
            mLySysExaValue.setVisibility(View.GONE);
            mLyMandatoryInvestValue.setVisibility(View.GONE);
            mLyDiagnosisValue.setVisibility(View.GONE);
            mLyPrescriptionValue.setVisibility(View.VISIBLE);
        }
        else if(clicked==5)
        {
            iconHistory.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_up));
            mIconGenPhy.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconSysExa.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconMandatoryInvest.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconDiagnosis.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconPrescription.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));

            lyHistoryExValue.setVisibility(View.VISIBLE);
            mLyGenPhysicalxValue.setVisibility(View.GONE);
            mLySysExaValue.setVisibility(View.GONE);
            mLyMandatoryInvestValue.setVisibility(View.GONE);
            mLyDiagnosisValue.setVisibility(View.GONE);
            mLyPrescriptionValue.setVisibility(View.GONE);
        }

    }

    private void mShowHideLayoutsAll()
    {
        iconHistory.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
        mIconGenPhy.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
        mIconSysExa.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
        mIconMandatoryInvest.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
        mIconDiagnosis.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
        mIconPrescription.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
        mLyGenPhysicalxValue.setVisibility(View.GONE);
        mLySysExaValue.setVisibility(View.GONE);
        mLyMandatoryInvestValue.setVisibility(View.GONE);
        mLyDiagnosisValue.setVisibility(View.GONE);
        mLyPrescriptionValue.setVisibility(View.GONE);
        lyHistoryExValue.setVisibility(View.GONE);

    }
}
