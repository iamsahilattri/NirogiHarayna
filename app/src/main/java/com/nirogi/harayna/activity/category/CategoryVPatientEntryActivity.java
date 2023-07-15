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

public class CategoryVPatientEntryActivity extends BaseActivity implements View.OnClickListener {

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
    private AppCompatEditText mInputWeightGenPhy;
    private AppCompatEditText mInputHeightGenPhy;
    private AppCompatEditText mInputBMIGenPhy;
    private AppCompatEditText mInputPulseGenPhy;
    private AppCompatEditText mInputBPGenPhy;
    private AppCompatSpinner mDropPallorGenPhy;
    private AppCompatSpinner mDropJaundiceGenPhy;
    private AppCompatSpinner mDropClubbingGenPhy;
    private AppCompatSpinner mDropLymphadenopathyGenPhy;
    private AppCompatSpinner mDropPOedemaGenPhy;
    private LinearLayout mLySysExa;
    private AppCompatImageView mIconSysExa;
    private LinearLayout mLySysExaValue;
    private AppCompatSpinner mDropChestSysExa;
    private AppCompatSpinner mDropCVSSysExa;
    private AppCompatSpinner mDropPAbdomenSysExa;
    private AppCompatSpinner mDropCNSSysExa;
    private AppCompatSpinner mDropHearingSysExa;
    private AppCompatEditText mInputRightEyeSysExa;
    private AppCompatEditText mInputLeftEyeSysExa;
    private AppCompatSpinner mDropColourBlindnesSysExa;
    private AppCompatSpinner mDropDentalSysExa;
    private AppCompatSpinner mDropGenitalSysExa;
    private AppCompatEditText mInputBreastSysExa;
    private LinearLayout mLyMandatoryInvest;
    private AppCompatImageView mIconMandatoryInvest;
    private LinearLayout mLyMandatoryInvestValue;
    private AppCompatCheckBox mChkHBMandatoryInvest;
    private AppCompatEditText mInputHBMandatoryInvest;
    private AppCompatCheckBox mChkTLCMandatoryInvest;
    private AppCompatEditText mInputTLCMandatoryInvest;
    private AppCompatCheckBox mChkDLCMandatoryInvest;
    private AppCompatEditText mInputDLCNeutrophilsMandatoryInvest;
    private AppCompatEditText mInputLymphocytesMandatoryInvest;
    private AppCompatEditText mInputDLCMonocytesMandatoryInvest;
    private AppCompatEditText mInputDLCEosinophilsMandatoryInvest;
    private AppCompatEditText mInputDLCBasophilsMandatoryInvest;
    private AppCompatCheckBox mChkPackedCellMandatoryInvest;
    private AppCompatEditText mInputPackedCellMandatoryInvest;
    private AppCompatCheckBox mChkCorpuscularMandatoryInvest;
    private AppCompatEditText mInputCorpuscularMandatoryInvest;
    private AppCompatCheckBox mChkCorpuscularHBMandatoryInvest;
    private AppCompatEditText mInputCorpuscularHBMandatoryInvest;
    private AppCompatCheckBox mChkHBConcentrationMandatoryInvest;
    private AppCompatEditText mInputHBConcentrationMandatoryInvest;
    private AppCompatCheckBox mChkPlateletMandatoryInvest;
    private AppCompatEditText mInputPlateletMandatoryInvest;
    private AppCompatCheckBox mChkRDWMandatoryInvest;
    private AppCompatEditText mInputRDWMandatoryInvest;
    private AppCompatCheckBox mChkRDWSDMandatoryInvest;
    private AppCompatEditText mInputRDWSDMandatoryInvest;
    private AppCompatCheckBox mChkRbcCountMandatoryInvest;
    private AppCompatEditText mInputRbcCountMandatoryInvest;
    private AppCompatCheckBox mChkRBSMandatoryInvest;
    private AppCompatEditText mInputRBSMandatoryInvest;
    private AppCompatCheckBox mChkCholesterolMandatoryInvest;
    private AppCompatEditText mInputCholesterolMandatoryInvest;
    private AppCompatCheckBox mChkBloodUreaMandatoryInvest;
    private AppCompatEditText mInputBloodUreaMandatoryInvest;
    private AppCompatCheckBox mChkCreatinineMandatoryInvest;
    private AppCompatEditText mInputCreatinineMandatoryInvest;
    private AppCompatCheckBox mChkUrineMandatoryInvest;
    private AppCompatEditText mInputUrineMandatoryInvest;
    private AppCompatCheckBox mChkAdvisedMandatoryInvest;
    private AppCompatEditText mInputAdvisedMandatoryInvest;
    private LinearLayout mLyDiagnosis;
    private AppCompatImageView mIconDiagnosis;
    private LinearLayout mLyDiagnosisValue;
    private AppCompatSpinner mDropDiagnosis;
    private AppCompatCheckBox mChkDAlreadyKnown;
    private AppCompatImageView mIconPrescription;
    private LinearLayout mLyPrescriptionValue;
    private AppCompatEditText mInputPrescription;

    private boolean mClickOne=true,mClickTwo=true,mClickThree=true,mClickFour=true,mClickFive=true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_input_cat_v);
        initView();
        mSetValuesToViews();
    }

    private void mSetValuesToViews()
    {
        try {
            if(getIntent()!=null)
            {
                PatientListModelResponse memberData = (PatientListModelResponse) getIntent().getSerializableExtra("memberData");
                if(memberData !=null)
                {
                    mSetBackToolbar("Patient Details",true,"Category V (40-60 Years)");
                    mTxtPatientPPPID.setText(memberData.getPppid()+"");
                    mTxtPatientName.setText(memberData.getFirstname()+" "+ memberData.getLastname());
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
        mRelMain =  findViewById(R.id.cVrelMain);
        mSwipeDown =  findViewById(R.id.cVswipeDown);
        mInfoLy =findViewById(R.id.cVinfoLy);
        mTxtPatientPPPID =findViewById(R.id.cVtxtPatientPPPID);
        mTxtPatientName =findViewById(R.id.cVtxtPatientName);
        mTxtPatientGenderAge =findViewById(R.id.cVtxtPatientGenderAge);
        mTxtPatientMobile =findViewById(R.id.cVtxtPatientMobile);
        mTxtPatientAddress =findViewById(R.id.cVtxtPatientAddress);
        mTxtPatientDistrict =findViewById(R.id.cVtxtPatientDistrict);
        mTxtFacilityName =findViewById(R.id.cVtxtFacilityName);
        mTxtFacilityIncharge =findViewById(R.id.cVtxtFacilityIncharge);
        mTxtDate =findViewById(R.id.cVtxtDate);
        mLyInput =findViewById(R.id.cVlyInput);
        mLyGenPhysicalEx =findViewById(R.id.cVlyGenPhysicalEx);
        mLyGenPhysicalEx.setOnClickListener(this);
        mIconGenPhy = findViewById(R.id.cViconGenPhy);
        mLyGenPhysicalxValue =findViewById(R.id.cVlyGenPhysicalxValue);
        mInputWeightGenPhy =findViewById(R.id.cVinputWeightGenPhy);
        mInputHeightGenPhy =findViewById(R.id.cVinputHeightGenPhy);
        mInputBMIGenPhy =findViewById(R.id.cVinputBMIGenPhy);
        mInputPulseGenPhy =findViewById(R.id.cVinputPulseGenPhy);
        mInputBPGenPhy =findViewById(R.id.cVinputBPGenPhy);
        mDropPallorGenPhy = findViewById(R.id.cVdropPallorGenPhy);
        mDropJaundiceGenPhy = findViewById(R.id.cVdropJaundiceGenPhy);
        mDropClubbingGenPhy = findViewById(R.id.cVdropClubbingGenPhy);
        mDropLymphadenopathyGenPhy = findViewById(R.id.cVdropLymphadenopathyGenPhy);
        mDropPOedemaGenPhy = findViewById(R.id.cVdropPOedemaGenPhy);
        mLySysExa =findViewById(R.id.cVlySysExa);
        mLySysExa.setOnClickListener(this);
        mIconSysExa = findViewById(R.id.cViconSysExa);
        mLySysExaValue =findViewById(R.id.cVlySysExaValue);
        mDropChestSysExa = findViewById(R.id.cVdropChestSysExa);
        mDropCVSSysExa = findViewById(R.id.cVdropCVSSysExa);
        mDropPAbdomenSysExa = findViewById(R.id.cVdropPAbdomenSysExa);
        mDropCNSSysExa = findViewById(R.id.cVdropCNSSysExa);
        mDropHearingSysExa = findViewById(R.id.cVdropHearingSysExa);
        mInputRightEyeSysExa =findViewById(R.id.cVinputRightEyeSysExa);
        mInputLeftEyeSysExa =findViewById(R.id.cVinputLeftEyeSysExa);
        mDropColourBlindnesSysExa = findViewById(R.id.cVdropColourBlindnesSysExa);
        mDropDentalSysExa = findViewById(R.id.cVdropDentalSysExa);
        mDropGenitalSysExa = findViewById(R.id.cVdropGenitalSysExa);
        mInputBreastSysExa =findViewById(R.id.cVinputBreastSysExa);
        mLyMandatoryInvest =findViewById(R.id.cVlyMandatoryInvest);
        mLyMandatoryInvest.setOnClickListener(this);
        mIconMandatoryInvest = findViewById(R.id.cViconMandatoryInvest);
        mLyMandatoryInvestValue =findViewById(R.id.cVlyMandatoryInvestValue);
        mChkHBMandatoryInvest = findViewById(R.id.cVchkHBMandatoryInvest);
        mInputHBMandatoryInvest =findViewById(R.id.cVinputHBMandatoryInvest);
        mChkTLCMandatoryInvest = findViewById(R.id.cVchkTLCMandatoryInvest);
        mInputTLCMandatoryInvest =findViewById(R.id.cVinputTLCMandatoryInvest);
        mChkDLCMandatoryInvest = findViewById(R.id.cVchkDLCMandatoryInvest);
        mInputDLCNeutrophilsMandatoryInvest =findViewById(R.id.cVinputDLCNeutrophilsMandatoryInvest);
        mInputLymphocytesMandatoryInvest =findViewById(R.id.cVinputLymphocytesMandatoryInvest);
        mInputDLCMonocytesMandatoryInvest =findViewById(R.id.cVinputDLCMonocytesMandatoryInvest);
        mInputDLCEosinophilsMandatoryInvest =findViewById(R.id.cVinputDLCEosinophilsMandatoryInvest);
        mInputDLCBasophilsMandatoryInvest =findViewById(R.id.cVinputDLCBasophilsMandatoryInvest);
        mChkPackedCellMandatoryInvest = findViewById(R.id.cVchkPackedCellMandatoryInvest);
        mInputPackedCellMandatoryInvest =findViewById(R.id.cVinputPackedCellMandatoryInvest);
        mChkCorpuscularMandatoryInvest = findViewById(R.id.cVchkCorpuscularMandatoryInvest);
        mInputCorpuscularMandatoryInvest =findViewById(R.id.cVinputCorpuscularMandatoryInvest);
        mChkCorpuscularHBMandatoryInvest = findViewById(R.id.cVchkCorpuscularHBMandatoryInvest);
        mInputCorpuscularHBMandatoryInvest =findViewById(R.id.cVinputCorpuscularHBMandatoryInvest);
        mChkHBConcentrationMandatoryInvest = findViewById(R.id.cVchkHBConcentrationMandatoryInvest);
        mInputHBConcentrationMandatoryInvest =findViewById(R.id.cVinputHBConcentrationMandatoryInvest);
        mChkPlateletMandatoryInvest = findViewById(R.id.cVchkPlateletMandatoryInvest);
        mInputPlateletMandatoryInvest =findViewById(R.id.cVinputPlateletMandatoryInvest);
        mChkRDWMandatoryInvest = findViewById(R.id.cVchkRDWMandatoryInvest);
        mInputRDWMandatoryInvest =findViewById(R.id.cVinputRDWMandatoryInvest);
        mChkRDWSDMandatoryInvest = findViewById(R.id.cVchkRDWSDMandatoryInvest);
        mInputRDWSDMandatoryInvest =findViewById(R.id.cVinputRDWSDMandatoryInvest);
        mChkRbcCountMandatoryInvest = findViewById(R.id.cVchkRbcCountMandatoryInvest);
        mInputRbcCountMandatoryInvest =findViewById(R.id.cVinputRbcCountMandatoryInvest);
        mChkRBSMandatoryInvest = findViewById(R.id.cVchkRBSMandatoryInvest);
        mInputRBSMandatoryInvest =findViewById(R.id.cVinputRBSMandatoryInvest);
        mChkCholesterolMandatoryInvest = findViewById(R.id.cVchkCholesterolMandatoryInvest);
        mInputCholesterolMandatoryInvest =findViewById(R.id.cVinputCholesterolMandatoryInvest);
        mChkBloodUreaMandatoryInvest = findViewById(R.id.cVchkBloodUreaMandatoryInvest);
        mInputBloodUreaMandatoryInvest =findViewById(R.id.cVinputBloodUreaMandatoryInvest);
        mChkCreatinineMandatoryInvest = findViewById(R.id.cVchkCreatinineMandatoryInvest);
        mInputCreatinineMandatoryInvest =findViewById(R.id.cVinputCreatinineMandatoryInvest);
        mChkUrineMandatoryInvest = findViewById(R.id.cVchkUrineMandatoryInvest);
        mInputUrineMandatoryInvest =findViewById(R.id.cVinputUrineMandatoryInvest);
        mChkAdvisedMandatoryInvest = findViewById(R.id.cVchkAdvisedMandatoryInvest);
        mInputAdvisedMandatoryInvest =findViewById(R.id.cVinputAdvisedMandatoryInvest);
        mLyDiagnosis =findViewById(R.id.cVlyDiagnosis);
        mLyDiagnosis.setOnClickListener(this);
        mIconDiagnosis = findViewById(R.id.cViconDiagnosis);
        mLyDiagnosisValue =findViewById(R.id.cVlyDiagnosisValue);
        mDropDiagnosis = findViewById(R.id.cVdropDiagnosis);
        mChkDAlreadyKnown = findViewById(R.id.cVchkDAlreadyKnown);
        LinearLayout mLyPrescription = findViewById(R.id.cVlyPrescription);
        mLyPrescription.setOnClickListener(this);
        mIconPrescription = findViewById(R.id.cViconPrescription);
        mLyPrescriptionValue =findViewById(R.id.cVlyPrescriptionValue);
        mInputPrescription =findViewById(R.id.cVinputPrescription);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.cVlyGenPhysicalEx) {
            if(mClickOne)
            {
                mShowHideLayouts(0);
                mClickOne=false;
            }else {
                mShowHideLayoutsAll();
                mClickOne=true;
            }
        }
        if (view.getId() == R.id.cVlySysExa) {
            if(mClickTwo)
            {
                mShowHideLayouts(1);
                mClickTwo=false;
            }else {
                mShowHideLayoutsAll();
                mClickTwo=true;
            }
        }
        if (view.getId() == R.id.cVlyMandatoryInvest) {
            if(mClickThree)
            {
                mShowHideLayouts(2);
                mClickThree=false;
            }else {
                mShowHideLayoutsAll();
                mClickThree=true;
            }
        }
        if (view.getId() == R.id.cVlyDiagnosis) {
            if(mClickFour)
            {
                mShowHideLayouts(3);
                mClickFour=false;
            }else {
                mShowHideLayoutsAll();
                mClickFour=true;
            }
        }
        if (view.getId() == R.id.cVlyPrescription) {
            if(mClickFive)
            {
                mShowHideLayouts(4);
                mClickFive=false;
            }else {
                mShowHideLayoutsAll();
                mClickFive=true;
            }
        }
    }

    private void mShowHideLayouts(int clicked)
    {
        if(clicked==0)
        {
            mIconGenPhy.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_up));
            mIconSysExa.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconMandatoryInvest.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconDiagnosis.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconPrescription.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));

            mLyGenPhysicalxValue.setVisibility(View.VISIBLE);
            mLySysExaValue.setVisibility(View.GONE);
            mLyMandatoryInvestValue.setVisibility(View.GONE);
            mLyDiagnosisValue.setVisibility(View.GONE);
            mLyPrescriptionValue.setVisibility(View.GONE);
        }else if(clicked==1)
        {
            mIconGenPhy.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconSysExa.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_up));
            mIconMandatoryInvest.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconDiagnosis.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconPrescription.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));

            mLyGenPhysicalxValue.setVisibility(View.GONE);
            mLySysExaValue.setVisibility(View.VISIBLE);
            mLyMandatoryInvestValue.setVisibility(View.GONE);
            mLyDiagnosisValue.setVisibility(View.GONE);
            mLyPrescriptionValue.setVisibility(View.GONE);
        }
        else if(clicked==2)
        {
            mIconGenPhy.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconSysExa.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconMandatoryInvest.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_up));
            mIconDiagnosis.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconPrescription.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));

            mLyGenPhysicalxValue.setVisibility(View.GONE);
            mLySysExaValue.setVisibility(View.GONE);
            mLyMandatoryInvestValue.setVisibility(View.VISIBLE);
            mLyDiagnosisValue.setVisibility(View.GONE);
            mLyPrescriptionValue.setVisibility(View.GONE);
        }
        else if(clicked==3)
        {
            mIconGenPhy.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconSysExa.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconMandatoryInvest.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconDiagnosis.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_up));
            mIconPrescription.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));

            mLyGenPhysicalxValue.setVisibility(View.GONE);
            mLySysExaValue.setVisibility(View.GONE);
            mLyMandatoryInvestValue.setVisibility(View.GONE);
            mLyDiagnosisValue.setVisibility(View.VISIBLE);
            mLyPrescriptionValue.setVisibility(View.GONE);
        }
        else if(clicked==4)
        {
            mIconGenPhy.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconSysExa.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconMandatoryInvest.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconDiagnosis.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
            mIconPrescription.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_up));

            mLyGenPhysicalxValue.setVisibility(View.GONE);
            mLySysExaValue.setVisibility(View.GONE);
            mLyMandatoryInvestValue.setVisibility(View.GONE);
            mLyDiagnosisValue.setVisibility(View.GONE);
            mLyPrescriptionValue.setVisibility(View.VISIBLE);
        }

    }

    private void mShowHideLayoutsAll()
    {
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

    }
}
