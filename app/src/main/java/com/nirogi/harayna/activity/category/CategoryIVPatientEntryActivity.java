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
        mRelMain = (RelativeLayout) findViewById(R.id.relMain);
        mSwipeDown = (SwipeRefreshLayout) findViewById(R.id.swipeDown);
        mInfoLy =findViewById(R.id.infoLy);
        mTxtPatientPPPID =findViewById(R.id.txtPatientPPPID);
        mTxtPatientName =findViewById(R.id.txtPatientName);
        mTxtPatientGenderAge =findViewById(R.id.txtPatientGenderAge);
        mTxtPatientMobile =findViewById(R.id.txtPatientMobile);
        mTxtPatientAddress =findViewById(R.id.txtPatientAddress);
        mTxtPatientDistrict =findViewById(R.id.txtPatientDistrict);
        mTxtFacilityName =findViewById(R.id.txtFacilityName);
        mTxtFacilityIncharge =findViewById(R.id.txtFacilityIncharge);
        mTxtDate =findViewById(R.id.txtDate);
        mLyInput =findViewById(R.id.lyInput);
        mLyGenPhysicalEx =findViewById(R.id.lyGenPhysicalEx);
        mLyGenPhysicalEx.setOnClickListener(this);
        mIconGenPhy = (AppCompatImageView) findViewById(R.id.iconGenPhy);
        mLyGenPhysicalxValue =findViewById(R.id.lyGenPhysicalxValue);
        mInputWeightGenPhy =findViewById(R.id.inputWeightGenPhy);
        mInputHeightGenPhy =findViewById(R.id.inputHeightGenPhy);
        mInputBMIGenPhy =findViewById(R.id.inputBMIGenPhy);
        mInputPulseGenPhy =findViewById(R.id.inputPulseGenPhy);
        mInputBPGenPhy =findViewById(R.id.inputBPGenPhy);
        mDropPallorGenPhy = findViewById(R.id.dropPallorGenPhy);
        mDropJaundiceGenPhy = findViewById(R.id.dropJaundiceGenPhy);
        mDropClubbingGenPhy = findViewById(R.id.dropClubbingGenPhy);
        mDropLymphadenopathyGenPhy = findViewById(R.id.dropLymphadenopathyGenPhy);
        mDropPOedemaGenPhy = findViewById(R.id.dropPOedemaGenPhy);
        mLySysExa =findViewById(R.id.lySysExa);
        mLySysExa.setOnClickListener(this);
        mIconSysExa = (AppCompatImageView) findViewById(R.id.iconSysExa);
        mLySysExaValue =findViewById(R.id.lySysExaValue);
        mDropChestSysExa = findViewById(R.id.dropChestSysExa);
        mDropCVSSysExa = findViewById(R.id.dropCVSSysExa);
        mDropPAbdomenSysExa = findViewById(R.id.dropPAbdomenSysExa);
        mDropCNSSysExa = findViewById(R.id.dropCNSSysExa);
        mDropHearingSysExa = findViewById(R.id.dropHearingSysExa);
        mInputRightEyeSysExa =findViewById(R.id.inputRightEyeSysExa);
        mInputLeftEyeSysExa =findViewById(R.id.inputLeftEyeSysExa);
        mDropColourBlindnesSysExa = findViewById(R.id.dropColourBlindnesSysExa);
        mDropDentalSysExa = findViewById(R.id.dropDentalSysExa);
        mDropGenitalSysExa = findViewById(R.id.dropGenitalSysExa);
        mInputBreastSysExa =findViewById(R.id.inputBreastSysExa);
        mLyMandatoryInvest =findViewById(R.id.lyMandatoryInvest);
        mLyMandatoryInvest.setOnClickListener(this);
        mIconMandatoryInvest = findViewById(R.id.iconMandatoryInvest);
        mLyMandatoryInvestValue =findViewById(R.id.lyMandatoryInvestValue);
        mChkHBMandatoryInvest = findViewById(R.id.chkHBMandatoryInvest);
        mInputHBMandatoryInvest =findViewById(R.id.inputHBMandatoryInvest);
        mChkTLCMandatoryInvest = findViewById(R.id.chkTLCMandatoryInvest);
        mInputTLCMandatoryInvest =findViewById(R.id.inputTLCMandatoryInvest);
        mChkDLCMandatoryInvest = findViewById(R.id.chkDLCMandatoryInvest);
        mInputDLCNeutrophilsMandatoryInvest =findViewById(R.id.inputDLCNeutrophilsMandatoryInvest);
        mInputLymphocytesMandatoryInvest =findViewById(R.id.inputLymphocytesMandatoryInvest);
        mInputDLCMonocytesMandatoryInvest =findViewById(R.id.inputDLCMonocytesMandatoryInvest);
        mInputDLCEosinophilsMandatoryInvest =findViewById(R.id.inputDLCEosinophilsMandatoryInvest);
        mInputDLCBasophilsMandatoryInvest =findViewById(R.id.inputDLCBasophilsMandatoryInvest);
        mChkPackedCellMandatoryInvest = findViewById(R.id.chkPackedCellMandatoryInvest);
        mInputPackedCellMandatoryInvest =findViewById(R.id.inputPackedCellMandatoryInvest);
        mChkCorpuscularMandatoryInvest = findViewById(R.id.chkCorpuscularMandatoryInvest);
        mInputCorpuscularMandatoryInvest =findViewById(R.id.inputCorpuscularMandatoryInvest);
        mChkCorpuscularHBMandatoryInvest = findViewById(R.id.chkCorpuscularHBMandatoryInvest);
        mInputCorpuscularHBMandatoryInvest =findViewById(R.id.inputCorpuscularHBMandatoryInvest);
        mChkHBConcentrationMandatoryInvest = findViewById(R.id.chkHBConcentrationMandatoryInvest);
        mInputHBConcentrationMandatoryInvest =findViewById(R.id.inputHBConcentrationMandatoryInvest);
        mChkPlateletMandatoryInvest = findViewById(R.id.chkPlateletMandatoryInvest);
        mInputPlateletMandatoryInvest =findViewById(R.id.inputPlateletMandatoryInvest);
        mChkRDWMandatoryInvest = findViewById(R.id.chkRDWMandatoryInvest);
        mInputRDWMandatoryInvest =findViewById(R.id.inputRDWMandatoryInvest);
        mChkRDWSDMandatoryInvest = findViewById(R.id.chkRDWSDMandatoryInvest);
        mInputRDWSDMandatoryInvest =findViewById(R.id.inputRDWSDMandatoryInvest);
        mChkRbcCountMandatoryInvest = findViewById(R.id.chkRbcCountMandatoryInvest);
        mInputRbcCountMandatoryInvest =findViewById(R.id.inputRbcCountMandatoryInvest);
        mChkRBSMandatoryInvest = findViewById(R.id.chkRBSMandatoryInvest);
        mInputRBSMandatoryInvest =findViewById(R.id.inputRBSMandatoryInvest);
        mChkCholesterolMandatoryInvest = findViewById(R.id.chkCholesterolMandatoryInvest);
        mInputCholesterolMandatoryInvest =findViewById(R.id.inputCholesterolMandatoryInvest);
        mChkBloodUreaMandatoryInvest = findViewById(R.id.chkBloodUreaMandatoryInvest);
        mInputBloodUreaMandatoryInvest =findViewById(R.id.inputBloodUreaMandatoryInvest);
        mChkCreatinineMandatoryInvest = findViewById(R.id.chkCreatinineMandatoryInvest);
        mInputCreatinineMandatoryInvest =findViewById(R.id.inputCreatinineMandatoryInvest);
        mChkUrineMandatoryInvest = findViewById(R.id.chkUrineMandatoryInvest);
        mInputUrineMandatoryInvest =findViewById(R.id.inputUrineMandatoryInvest);
        mChkAdvisedMandatoryInvest = findViewById(R.id.chkAdvisedMandatoryInvest);
        mInputAdvisedMandatoryInvest =findViewById(R.id.inputAdvisedMandatoryInvest);
        mLyDiagnosis =findViewById(R.id.lyDiagnosis);
        mLyDiagnosis.setOnClickListener(this);
        mIconDiagnosis = (AppCompatImageView) findViewById(R.id.iconDiagnosis);
        mLyDiagnosisValue =findViewById(R.id.lyDiagnosisValue);
        mDropDiagnosis = findViewById(R.id.dropDiagnosis);
        mChkDAlreadyKnown = findViewById(R.id.chkDAlreadyKnown);
        mLyPrescription =findViewById(R.id.lyPrescription);
        mLyPrescription.setOnClickListener(this);
        mIconPrescription = (AppCompatImageView) findViewById(R.id.iconPrescription);
        mLyPrescriptionValue =findViewById(R.id.lyPrescriptionValue);
        mInputPrescription =findViewById(R.id.inputPrescription);
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
