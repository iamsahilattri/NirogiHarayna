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

public class CategoryIVPatientEntryActivity extends BaseActivity implements View.OnClickListener {

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
    private LinearLayout mLyPrescription;
    private AppCompatImageView mIconPrescription;
    private LinearLayout mLyPrescriptionValue;
    private AppCompatEditText mInputPrescription;

    private boolean mClickOne=true,mClickTwo=true,mClickThree=true,mClickFour=true,mClickFive=true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_input_cat_iv);
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
                    mSetBackToolbar("Patient Details",true,"Category IV (18-40 Years)");
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
        mRelMain =  findViewById(R.id.cIVrelMain);
        mSwipeDown = findViewById(R.id.cIVswipeDown);
        mInfoLy =findViewById(R.id.cIVinfoLy);
        mTxtPatientPPPID =findViewById(R.id.cIVtxtPatientPPPID);
        mTxtPatientName =findViewById(R.id.cIVtxtPatientName);
        mTxtPatientGenderAge =findViewById(R.id.cIVtxtPatientGenderAge);
        mTxtPatientMobile =findViewById(R.id.cIVtxtPatientMobile);
        mTxtPatientAddress =findViewById(R.id.cIVtxtPatientAddress);
        mTxtPatientDistrict =findViewById(R.id.cIVtxtPatientDistrict);
        mTxtFacilityName =findViewById(R.id.cIVtxtFacilityName);
        mTxtFacilityIncharge =findViewById(R.id.cIVtxtFacilityIncharge);
        mTxtDate =findViewById(R.id.cIVtxtDate);
        mLyInput =findViewById(R.id.cIVlyInput);
        mLyGenPhysicalEx =findViewById(R.id.cIVlyGenPhysicalEx);
        mLyGenPhysicalEx.setOnClickListener(this);
        mIconGenPhy =  findViewById(R.id.cIViconGenPhy);
        mLyGenPhysicalxValue =findViewById(R.id.cIVlyGenPhysicalxValue);
        mInputWeightGenPhy =findViewById(R.id.cIVinputWeightGenPhy);
        mInputHeightGenPhy =findViewById(R.id.cIVinputHeightGenPhy);
        mInputBMIGenPhy =findViewById(R.id.cIVinputBMIGenPhy);
        mInputPulseGenPhy =findViewById(R.id.cIVinputPulseGenPhy);
        mInputBPGenPhy =findViewById(R.id.cIVinputBPGenPhy);
        mDropPallorGenPhy = findViewById(R.id.cIVdropPallorGenPhy);
        mDropJaundiceGenPhy = findViewById(R.id.cIVdropJaundiceGenPhy);
        mDropClubbingGenPhy = findViewById(R.id.cIVdropClubbingGenPhy);
        mDropLymphadenopathyGenPhy = findViewById(R.id.cIVdropLymphadenopathyGenPhy);
        mDropPOedemaGenPhy = findViewById(R.id.cIVdropPOedemaGenPhy);
        mLySysExa =findViewById(R.id.cIVlySysExa);
        mLySysExa.setOnClickListener(this);
        mIconSysExa =  findViewById(R.id.cIViconSysExa);
        mLySysExaValue =findViewById(R.id.cIVlySysExaValue);
        mDropChestSysExa = findViewById(R.id.cIVdropChestSysExa);
        mDropCVSSysExa = findViewById(R.id.cIVdropCVSSysExa);
        mDropPAbdomenSysExa = findViewById(R.id.cIVdropPAbdomenSysExa);
        mDropCNSSysExa = findViewById(R.id.cIVdropCNSSysExa);
        mDropHearingSysExa = findViewById(R.id.cIVdropHearingSysExa);
        mInputRightEyeSysExa =findViewById(R.id.cIVinputRightEyeSysExa);
        mInputLeftEyeSysExa =findViewById(R.id.cIVinputLeftEyeSysExa);
        mDropColourBlindnesSysExa = findViewById(R.id.cIVdropColourBlindnesSysExa);
        mDropDentalSysExa = findViewById(R.id.cIVdropDentalSysExa);
        mDropGenitalSysExa = findViewById(R.id.cIVdropGenitalSysExa);
        mInputBreastSysExa =findViewById(R.id.cIVinputBreastSysExa);
        mLyMandatoryInvest =findViewById(R.id.cIVlyMandatoryInvest);
        mLyMandatoryInvest.setOnClickListener(this);
        mIconMandatoryInvest = findViewById(R.id.cIViconMandatoryInvest);
        mLyMandatoryInvestValue =findViewById(R.id.cIVlyMandatoryInvestValue);
        mChkHBMandatoryInvest = findViewById(R.id.cIVchkHBMandatoryInvest);
        mInputHBMandatoryInvest =findViewById(R.id.cIVinputHBMandatoryInvest);
        mChkTLCMandatoryInvest = findViewById(R.id.cIVchkTLCMandatoryInvest);
        mInputTLCMandatoryInvest =findViewById(R.id.cIVinputTLCMandatoryInvest);
        mChkDLCMandatoryInvest = findViewById(R.id.cIVchkDLCMandatoryInvest);
        mInputDLCNeutrophilsMandatoryInvest =findViewById(R.id.cIVinputDLCNeutrophilsMandatoryInvest);
        mInputLymphocytesMandatoryInvest =findViewById(R.id.cIVinputLymphocytesMandatoryInvest);
        mInputDLCMonocytesMandatoryInvest =findViewById(R.id.cIVinputDLCMonocytesMandatoryInvest);
        mInputDLCEosinophilsMandatoryInvest =findViewById(R.id.cIVinputDLCEosinophilsMandatoryInvest);
        mInputDLCBasophilsMandatoryInvest =findViewById(R.id.cIVinputDLCBasophilsMandatoryInvest);
        mChkPackedCellMandatoryInvest = findViewById(R.id.cIVchkPackedCellMandatoryInvest);
        mInputPackedCellMandatoryInvest =findViewById(R.id.cIVinputPackedCellMandatoryInvest);
        mChkCorpuscularMandatoryInvest = findViewById(R.id.cIVchkCorpuscularMandatoryInvest);
        mInputCorpuscularMandatoryInvest =findViewById(R.id.cIVinputCorpuscularMandatoryInvest);
        mChkCorpuscularHBMandatoryInvest = findViewById(R.id.cIVchkCorpuscularHBMandatoryInvest);
        mInputCorpuscularHBMandatoryInvest =findViewById(R.id.cIVinputCorpuscularHBMandatoryInvest);
        mChkHBConcentrationMandatoryInvest = findViewById(R.id.cIVchkHBConcentrationMandatoryInvest);
        mInputHBConcentrationMandatoryInvest =findViewById(R.id.cIVinputHBConcentrationMandatoryInvest);
        mChkPlateletMandatoryInvest = findViewById(R.id.cIVchkPlateletMandatoryInvest);
        mInputPlateletMandatoryInvest =findViewById(R.id.cIVinputPlateletMandatoryInvest);
        mChkRDWMandatoryInvest = findViewById(R.id.cIVchkRDWMandatoryInvest);
        mInputRDWMandatoryInvest =findViewById(R.id.cIVinputRDWMandatoryInvest);
        mChkRDWSDMandatoryInvest = findViewById(R.id.cIVchkRDWSDMandatoryInvest);
        mInputRDWSDMandatoryInvest =findViewById(R.id.cIVinputRDWSDMandatoryInvest);
        mChkRbcCountMandatoryInvest = findViewById(R.id.cIVchkRbcCountMandatoryInvest);
        mInputRbcCountMandatoryInvest =findViewById(R.id.cIVinputRbcCountMandatoryInvest);
        mChkRBSMandatoryInvest = findViewById(R.id.cIVchkRBSMandatoryInvest);
        mInputRBSMandatoryInvest =findViewById(R.id.cIVinputRBSMandatoryInvest);
        mChkCholesterolMandatoryInvest = findViewById(R.id.cIVchkCholesterolMandatoryInvest);
        mInputCholesterolMandatoryInvest =findViewById(R.id.cIVinputCholesterolMandatoryInvest);
        mChkBloodUreaMandatoryInvest = findViewById(R.id.cIVchkBloodUreaMandatoryInvest);
        mInputBloodUreaMandatoryInvest =findViewById(R.id.cIVinputBloodUreaMandatoryInvest);
        mChkCreatinineMandatoryInvest = findViewById(R.id.cIVchkCreatinineMandatoryInvest);
        mInputCreatinineMandatoryInvest =findViewById(R.id.cIVinputCreatinineMandatoryInvest);
        mChkUrineMandatoryInvest = findViewById(R.id.cIVchkUrineMandatoryInvest);
        mInputUrineMandatoryInvest =findViewById(R.id.cIVinputUrineMandatoryInvest);
        mChkAdvisedMandatoryInvest = findViewById(R.id.cIVchkAdvisedMandatoryInvest);
        mInputAdvisedMandatoryInvest =findViewById(R.id.cIVinputAdvisedMandatoryInvest);
        mLyDiagnosis =findViewById(R.id.cIVlyDiagnosis);
        mLyDiagnosis.setOnClickListener(this);
        mIconDiagnosis =  findViewById(R.id.cIViconDiagnosis);
        mLyDiagnosisValue =findViewById(R.id.cIVlyDiagnosisValue);
        mDropDiagnosis = findViewById(R.id.cIVdropDiagnosis);
        mChkDAlreadyKnown = findViewById(R.id.cIVchkDAlreadyKnown);
        mLyPrescription =findViewById(R.id.cIVlyPrescription);
        mLyPrescription.setOnClickListener(this);
        mIconPrescription =  findViewById(R.id.cIViconPrescription);
        mLyPrescriptionValue =findViewById(R.id.cIVlyPrescriptionValue);
        mInputPrescription =findViewById(R.id.cIVinputPrescription);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.lyGenPhysicalEx) {
            if(mClickOne)
            {
                mShowHideLayouts(0);
                mClickOne=false;
            }else {
                mShowHideLayoutsAll();
                mClickOne=true;
            }
        }
        if (view.getId() == R.id.lySysExa) {
            if(mClickTwo)
            {
                mShowHideLayouts(1);
                mClickTwo=false;
            }else {
                mShowHideLayoutsAll();
                mClickTwo=true;
            }
        }
        if (view.getId() == R.id.lyMandatoryInvest) {
            if(mClickThree)
            {
                mShowHideLayouts(2);
                mClickThree=false;
            }else {
                mShowHideLayoutsAll();
                mClickThree=true;
            }
        }
        if (view.getId() == R.id.lyDiagnosis) {
            if(mClickFour)
            {
                mShowHideLayouts(3);
                mClickFour=false;
            }else {
                mShowHideLayoutsAll();
                mClickFour=true;
            }
        }
        if (view.getId() == R.id.lyPrescription) {
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
