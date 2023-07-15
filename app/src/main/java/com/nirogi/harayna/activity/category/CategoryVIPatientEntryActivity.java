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

public class CategoryVIPatientEntryActivity extends BaseActivity implements View.OnClickListener {

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

    private LinearLayout cVIlySystomComplainExa,cVIlySysCompValue;
    private AppCompatImageView cVIiconSysComp;

    private boolean mClickOne=true,mClickTwo=true,mClickThree=true,mClickFour=true,mClickFive=true,mClickSix=true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_input_cat_vi);
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
                    mSetBackToolbar("Patient Details",true,"Category VI (Over 60 Years)");
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
        mRelMain = findViewById(R.id.cVIrelMain);
        mSwipeDown = findViewById(R.id.cVIswipeDown);
        mInfoLy =findViewById(R.id.cVIinfoLy);
        mTxtPatientPPPID =findViewById(R.id.cVItxtPatientPPPID);
        mTxtPatientName =findViewById(R.id.cVItxtPatientName);
        mTxtPatientGenderAge =findViewById(R.id.cVItxtPatientGenderAge);
        mTxtPatientMobile =findViewById(R.id.cVItxtPatientMobile);
        mTxtPatientAddress =findViewById(R.id.cVItxtPatientAddress);
        mTxtPatientDistrict =findViewById(R.id.cVItxtPatientDistrict);
        mTxtFacilityName =findViewById(R.id.cVItxtFacilityName);
        mTxtFacilityIncharge =findViewById(R.id.cVItxtFacilityIncharge);
        mTxtDate =findViewById(R.id.cVItxtDate);
        mLyInput =findViewById(R.id.cVIlyInput);
        mLyGenPhysicalEx =findViewById(R.id.cVIlyGenPhysicalEx);
        mLyGenPhysicalEx.setOnClickListener(this);
        mIconGenPhy = findViewById(R.id.cVIiconGenPhy);
        mLyGenPhysicalxValue =findViewById(R.id.cVIlyGenPhysicalxValue);
        mInputWeightGenPhy =findViewById(R.id.cVIinputWeightGenPhy);
        mInputHeightGenPhy =findViewById(R.id.cVIinputHeightGenPhy);
        mInputBMIGenPhy =findViewById(R.id.cVIinputBMIGenPhy);
        mInputPulseGenPhy =findViewById(R.id.cVIinputPulseGenPhy);
        mInputBPGenPhy =findViewById(R.id.cVIinputBPGenPhy);
     
        mLySysExa =findViewById(R.id.cVIlySysExa);
        mLySysExa.setOnClickListener(this);
        mIconSysExa = findViewById(R.id.cVIiconSysExa);
        mLySysExaValue =findViewById(R.id.cVIlySysExaValue);
        mDropChestSysExa = findViewById(R.id.cVIdropChestSysExa);
        mDropCVSSysExa = findViewById(R.id.cVIdropCVSSysExa);
        mDropPAbdomenSysExa = findViewById(R.id.cVIdropPAbdomenSysExa);
        mDropCNSSysExa = findViewById(R.id.cVIdropCNSSysExa);
        mDropHearingSysExa = findViewById(R.id.cVIdropHearingSysExa);
        mInputRightEyeSysExa =findViewById(R.id.cVIinputRightEyeSysExa);
        mInputLeftEyeSysExa =findViewById(R.id.cVIinputLeftEyeSysExa);
        mDropColourBlindnesSysExa = findViewById(R.id.cVIdropColourBlindnesSysExa);
        mDropDentalSysExa = findViewById(R.id.cVIdropDentalSysExa);
        mDropGenitalSysExa = findViewById(R.id.cVIdropGenitalSysExa);
        mInputBreastSysExa =findViewById(R.id.cVIinputBreastSysExa);
        mLyMandatoryInvest =findViewById(R.id.cVIlyMandatoryInvest);
        mLyMandatoryInvest.setOnClickListener(this);
        mIconMandatoryInvest = findViewById(R.id.cVIiconMandatoryInvest);
        mLyMandatoryInvestValue =findViewById(R.id.cVIlyMandatoryInvestValue);
        mChkHBMandatoryInvest = findViewById(R.id.cVIchkHBMandatoryInvest);
        mInputHBMandatoryInvest =findViewById(R.id.cVIinputHBMandatoryInvest);
        mChkTLCMandatoryInvest = findViewById(R.id.cVIchkTLCMandatoryInvest);
        mInputTLCMandatoryInvest =findViewById(R.id.cVIinputTLCMandatoryInvest);
        mChkDLCMandatoryInvest = findViewById(R.id.cVIchkDLCMandatoryInvest);
        mInputDLCNeutrophilsMandatoryInvest =findViewById(R.id.cVIinputDLCNeutrophilsMandatoryInvest);
        mInputLymphocytesMandatoryInvest =findViewById(R.id.cVIinputLymphocytesMandatoryInvest);
        mInputDLCMonocytesMandatoryInvest =findViewById(R.id.cVIinputDLCMonocytesMandatoryInvest);
        mInputDLCEosinophilsMandatoryInvest =findViewById(R.id.cVIinputDLCEosinophilsMandatoryInvest);
        mInputDLCBasophilsMandatoryInvest =findViewById(R.id.cVIinputDLCBasophilsMandatoryInvest);
        mChkPackedCellMandatoryInvest = findViewById(R.id.cVIchkPackedCellMandatoryInvest);
        mInputPackedCellMandatoryInvest =findViewById(R.id.cVIinputPackedCellMandatoryInvest);
        mChkCorpuscularMandatoryInvest = findViewById(R.id.cVIchkCorpuscularMandatoryInvest);
        mInputCorpuscularMandatoryInvest =findViewById(R.id.cVIinputCorpuscularMandatoryInvest);
        mChkCorpuscularHBMandatoryInvest = findViewById(R.id.cVIchkCorpuscularHBMandatoryInvest);
        mInputCorpuscularHBMandatoryInvest =findViewById(R.id.cVIinputCorpuscularHBMandatoryInvest);
        mChkHBConcentrationMandatoryInvest = findViewById(R.id.cVIchkHBConcentrationMandatoryInvest);
        mInputHBConcentrationMandatoryInvest =findViewById(R.id.cVIinputHBConcentrationMandatoryInvest);
        mChkPlateletMandatoryInvest = findViewById(R.id.cVIchkPlateletMandatoryInvest);
        mInputPlateletMandatoryInvest =findViewById(R.id.cVIinputPlateletMandatoryInvest);
        mChkRDWMandatoryInvest = findViewById(R.id.cVIchkRDWMandatoryInvest);
        mInputRDWMandatoryInvest =findViewById(R.id.cVIinputRDWMandatoryInvest);
        mChkRDWSDMandatoryInvest = findViewById(R.id.cVIchkRDWSDMandatoryInvest);
        mInputRDWSDMandatoryInvest =findViewById(R.id.cVIinputRDWSDMandatoryInvest);
        mChkRbcCountMandatoryInvest = findViewById(R.id.cVIchkRbcCountMandatoryInvest);
        mInputRbcCountMandatoryInvest =findViewById(R.id.cVIinputRbcCountMandatoryInvest);
        mChkRBSMandatoryInvest = findViewById(R.id.cVIchkRBSMandatoryInvest);
        mInputRBSMandatoryInvest =findViewById(R.id.cVIinputRBSMandatoryInvest);
        mChkCholesterolMandatoryInvest = findViewById(R.id.cVIchkCholesterolMandatoryInvest);
        mInputCholesterolMandatoryInvest =findViewById(R.id.cVIinputCholesterolMandatoryInvest);
        mChkBloodUreaMandatoryInvest = findViewById(R.id.cVIchkBloodUreaMandatoryInvest);
        mInputBloodUreaMandatoryInvest =findViewById(R.id.cVIinputBloodUreaMandatoryInvest);
        mChkCreatinineMandatoryInvest = findViewById(R.id.cVIchkCreatinineMandatoryInvest);
        mInputCreatinineMandatoryInvest =findViewById(R.id.cVIinputCreatinineMandatoryInvest);
        mChkUrineMandatoryInvest = findViewById(R.id.cVIchkUrineMandatoryInvest);
        mInputUrineMandatoryInvest =findViewById(R.id.cVIinputUrineMandatoryInvest);
        mChkAdvisedMandatoryInvest = findViewById(R.id.cVIchkAdvisedMandatoryInvest);
        mInputAdvisedMandatoryInvest =findViewById(R.id.cVIinputAdvisedMandatoryInvest);
        mLyDiagnosis =findViewById(R.id.cVIlyDiagnosis);
        mLyDiagnosis.setOnClickListener(this);
        mIconDiagnosis = findViewById(R.id.cVIiconDiagnosis);
        mLyDiagnosisValue =findViewById(R.id.cVIlyDiagnosisValue);
        mDropDiagnosis = findViewById(R.id.cVIdropDiagnosis);
        mChkDAlreadyKnown = findViewById(R.id.cVIchkDAlreadyKnown);
        mLyPrescription =findViewById(R.id.cVIlyPrescription);
        mLyPrescription.setOnClickListener(this);
        mIconPrescription = findViewById(R.id.cVIiconPrescription);
        mLyPrescriptionValue =findViewById(R.id.cVIlyPrescriptionValue);
        mInputPrescription =findViewById(R.id.cVIinputPrescription);

        cVIlySystomComplainExa=findViewById(R.id.cVIlySystomComplainExa);
        cVIlySystomComplainExa.setOnClickListener(this);
        cVIiconSysComp=findViewById(R.id.cVIiconSysComp);
        cVIlySysCompValue=findViewById(R.id.cVIlySysCompValue);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.cVIlyGenPhysicalEx) {
            if(mClickOne)
            {
                mShowHideLayouts(0);
                mClickOne=false;
            }else {
                mShowHideLayoutsAll();
                mClickOne=true;
            }
        }
        if (view.getId() == R.id.cVIlySysExa) {
            if(mClickTwo)
            {
                mShowHideLayouts(1);
                mClickTwo=false;
            }else {
                mShowHideLayoutsAll();
                mClickTwo=true;
            }
        }
        if (view.getId() == R.id.cVIlyMandatoryInvest) {
            if(mClickThree)
            {
                mShowHideLayouts(2);
                mClickThree=false;
            }else {
                mShowHideLayoutsAll();
                mClickThree=true;
            }
        }
        if (view.getId() == R.id.cVIlyDiagnosis) {
            if(mClickFour)
            {
                mShowHideLayouts(3);
                mClickFour=false;
            }else {
                mShowHideLayoutsAll();
                mClickFour=true;
            }
        }
        if (view.getId() == R.id.cVIlyPrescription) {
            if(mClickFive)
            {
                mShowHideLayouts(4);
                mClickFive=false;
            }else {
                mShowHideLayoutsAll();
                mClickFive=true;
            }
        }
        if (view.getId() == R.id.cVIlySystomComplainExa) {
            if(mClickFive)
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
            cVIiconSysComp.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
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
            cVIlySysCompValue.setVisibility(View.GONE);

        }else if(clicked==1)
        {
            cVIiconSysComp.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
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
            cVIlySysCompValue.setVisibility(View.GONE);
        }
        else if(clicked==2)
        {
            cVIiconSysComp.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
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
            cVIlySysCompValue.setVisibility(View.GONE);
        }
        else if(clicked==3)
        {
            cVIiconSysComp.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
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
            cVIlySysCompValue.setVisibility(View.GONE);
        }
        else if(clicked==4)
        {
            cVIiconSysComp.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
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
            cVIlySysCompValue.setVisibility(View.GONE);
        }
        else if(clicked==5)
        {
            cVIiconSysComp.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_up));
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
            cVIlySysCompValue.setVisibility(View.VISIBLE);
        }

    }

    private void mShowHideLayoutsAll()
    {
        cVIiconSysComp.setBackgroundDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down));
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
        cVIlySysCompValue.setVisibility(View.GONE);

    }
}
