package com.nirogi.harayna.activity.category;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;

import com.nirogi.harayna.R;
import com.nirogi.harayna.model.request.PostDataForCategoryVRequest;
import com.nirogi.harayna.model.request.PostMandatoryDataRequest;
import com.nirogi.harayna.model.response.PatientListModelResponse;
import com.nirogi.harayna.model.response.ReferredSurveyDataResponse;
import com.nirogi.harayna.model.response.SubmitPatientData;
import com.nirogi.harayna.network.APIInterface;
import com.nirogi.harayna.network.ApiClient;
import com.nirogi.harayna.utils.BaseActivity;
import com.nirogi.harayna.utils.IntentParams;
import com.nirogi.harayna.utils.MultiSpinner;
import com.nirogi.harayna.utils.NIROGI;
import com.nirogi.harayna.utils.SharedParams;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryVPatientEntryActivity extends BaseActivity implements View.OnClickListener {

    private AppCompatTextView mTxtPatientPPPID;
    private AppCompatTextView mTxtPatientName;
    private AppCompatTextView mTxtPatientGenderAge;
    private AppCompatTextView mTxtPatientMobile;
    private AppCompatTextView mTxtPatientAddress;
    private AppCompatTextView mTxtPatientDistrict;
    private AppCompatTextView mTxtFacilityName;
    private AppCompatTextView mTxtFacilityIncharge;
    private AppCompatTextView mTxtDate;
    private AppCompatImageView mIconGenPhy;
    private LinearLayout mLyGenPhysicalxValue;

    private AppCompatImageView mIconSysExa;
    private LinearLayout mLySysExaValue;

    private AppCompatImageView mIconMandatoryInvest;
    private LinearLayout mLyMandatoryInvestValue;

    private AppCompatImageView mIconDiagnosis;
    private LinearLayout mLyDiagnosisValue;
    private AppCompatImageView mIconPrescription;
    private LinearLayout mLyPrescriptionValue;

    private boolean mClickOne = true, mClickTwo = true, mClickThree = true, mClickFour = true, mClickFive = true;

    PatientListModelResponse memberData;
    private SharedPreferences sharedPreferences;

    private MultiSpinner multiSpinner;
    private String selectedDiagnosis="";
    private String doctorName;
    private AppCompatEditText mCVinputWeightGenPhy;
    private AppCompatEditText mCVinputHeightGenPhy;
    private AppCompatEditText mCVinputBMIGenPhy;
    private AppCompatEditText mCVinputPulseGenPhy;
    private AppCompatEditText mCVinputBPGenPhy;
    private AppCompatSpinner mCVdropPallorGenPhy;
    private AppCompatSpinner mCVdropJaundiceGenPhy;
    private AppCompatSpinner mCVdropClubbingGenPhy;
    private AppCompatSpinner mCVdropLymphadenopathyGenPhy;
    private AppCompatSpinner mCVdropPOedemaGenPhy;
    private AppCompatSpinner mCVdropChestSysExa;
    private AppCompatSpinner mCVdropCVSSysExa;
    private AppCompatSpinner mCVdropPAbdomenSysExa;
    private AppCompatSpinner mCVdropCNSSysExa;
    private AppCompatSpinner mCVdropHearingSysExa;
    private AppCompatEditText mCVinputRightEyeSysExa;
    private AppCompatEditText mCVinputLeftEyeSysExa;
    private AppCompatSpinner mCVdropColourBlindnesSysExa;
    private AppCompatSpinner mCVdropDentalSysExa;
    private AppCompatSpinner mCVdropGenitalSysExa;
    private AppCompatEditText mCVinputBreastSysExa;
    private AppCompatSpinner mCVdropJointExSysExa;
    private AppCompatSpinner mCVdropOralExamSysExa;
    private AppCompatCheckBox mCVchkHBMandatoryInvest;
    private AppCompatEditText mCVinputHBMandatoryInvest;
    private AppCompatCheckBox mCVchkTLCMandatoryInvest;
    private AppCompatEditText mCVinputTLCMandatoryInvest;
    private AppCompatCheckBox mCVchkDLCMandatoryInvest;
    private AppCompatEditText mCVinputDLCNeutrophilsMandatoryInvest;
    private AppCompatEditText mCVinputLymphocytesMandatoryInvest;
    private AppCompatEditText mCVinputDLCMonocytesMandatoryInvest;
    private AppCompatEditText mCVinputDLCEosinophilsMandatoryInvest;
    private AppCompatEditText mCVinputDLCBasophilsMandatoryInvest;
    private AppCompatCheckBox mCVchkPackedCellMandatoryInvest;
    private AppCompatEditText mCVinputPackedCellMandatoryInvest;
    private AppCompatCheckBox mCVchkCorpuscularMandatoryInvest;
    private AppCompatEditText mCVinputCorpuscularMandatoryInvest;
    private AppCompatCheckBox mCVchkCorpuscularHBMandatoryInvest;
    private AppCompatEditText mCVinputCorpuscularHBMandatoryInvest;
    private AppCompatCheckBox mCVchkHBConcentrationMandatoryInvest;
    private AppCompatEditText mCVinputHBConcentrationMandatoryInvest;
    private AppCompatCheckBox mCVchkPlateletMandatoryInvest;
    private AppCompatEditText mCVinputPlateletMandatoryInvest;
    private AppCompatCheckBox mCVchkRDWMandatoryInvest;
    private AppCompatEditText mCVinputRDWMandatoryInvest;
    private AppCompatCheckBox mCVchkRDWSDMandatoryInvest;
    private AppCompatEditText mCVinputRDWSDMandatoryInvest;
    private AppCompatCheckBox mCVchkRbcCountMandatoryInvest;
    private AppCompatEditText mCVinputRbcCountMandatoryInvest;
    private AppCompatCheckBox mCVchkRBSMandatoryInvest;
    private AppCompatEditText mCVinputRBSMandatoryInvest;
    private AppCompatCheckBox mCVchkCholesterolMandatoryInvest;
    private AppCompatEditText mCVinputCholesterolMandatoryInvest;
    private AppCompatCheckBox mCVchkBloodUreaMandatoryInvest;
    private AppCompatEditText mCVinputBloodUreaMandatoryInvest;
    private AppCompatCheckBox mCVchkCreatinineMandatoryInvest;
    private AppCompatEditText mCVinputCreatinineMandatoryInvest;
    private AppCompatCheckBox mCVchkTSHMandatoryInvest;
    private AppCompatEditText mCVinputTSHMandatoryInvest;
    private AppCompatCheckBox mCVchkPSAMandatoryInvest;
    private AppCompatEditText mCVinputPSAMandatoryInvest;
    private AppCompatCheckBox mCVchkUrineMandatoryInvest;
    private AppCompatEditText mCVinputUrineMandatoryInvest,cVViewDiagnosis;
    private AppCompatCheckBox mCVchkVIAPAPMandatoryInvest;
    private AppCompatEditText mCVinputVIAPAPMandatoryInvest;
    private AppCompatCheckBox mCVchkAdvisedMandatoryInvest;
    private AppCompatEditText mCVinputAdvisedMandatoryInvest;
    private MultiSpinner mCVdropDiagnosis;
    private AppCompatCheckBox mCVchkDAlreadyKnown;
    private AppCompatEditText mCVinputPrescription;
    private ReferredSurveyDataResponse intentRecorderRefData;

    private  CheckBox cVchkAll;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = NIROGI.getInstance().getPreferences();
        setContentView(R.layout.activity_patient_input_cat_v);
        initView();
        mSetBackToolbar(CategoryVPatientEntryActivity.this,"Patient Details", true, "Category V (40-60 Years)");
        mSetValuesToViews();
        initDataToView();
        mSetValidationListeners();
        checkMandatoryAll();
    }

    @Override
    public void onBackPressed() {
        performBackPress(CategoryVPatientEntryActivity.this);
    }

    private void initDataToView() {
        doctorName = sharedPreferences.getString(SharedParams.FNAME, "") + "" + sharedPreferences.getString(SharedParams.LNAME, "");
        mTxtFacilityName.setText(sharedPreferences.getString(SharedParams.FACTYPE, "") + " " + sharedPreferences.getString(SharedParams.FACILITY, ""));
        mTxtFacilityIncharge.setText("Dr . " + doctorName);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(Calendar.getInstance().getTime());
        mTxtDate.setText(formattedDate);
    }

 

    private void mSetValuesToViews() {
        try {
            if (getIntent() != null) {
                if(getIntent().getSerializableExtra(IntentParams.MEMBER_TYPE).equals("1"))
                {
                    memberData = (PatientListModelResponse) getIntent().getSerializableExtra(IntentParams.MEMBER_DATA);
                    if (memberData != null) {
                        mTxtPatientPPPID.setText(memberData.getPppid() + "");
                        mTxtPatientName.setText(memberData.getFirstname() + " " + memberData.getLastname());
                        mTxtPatientGenderAge.setText(memberData.getGender() + "");
                        mTxtPatientMobile.setText(memberData.getMobileno() + "");
                        mTxtPatientAddress.setText(memberData.getAddress() + "");
                        mTxtPatientDistrict.setText(memberData.getDistrict() + "");
                    }
                }else {
                    intentRecorderRefData = (ReferredSurveyDataResponse) getIntent().getSerializableExtra(IntentParams.SCREENED_DATA);
                    mSetScreenedDataToViews();
                }
            }
        } catch (Exception e) {
            Log.e(" Exception memberData", "" + e.getMessage());
        }
    }

    private void mSetServerValuesToSpinner(String serverValue, Spinner mSpinnerID, int staticArrayForSpinner)
    {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, staticArrayForSpinner, R.layout.spinner_text);
        mSpinnerID.setAdapter(adapter);
        if (serverValue != null) {
            int spinnerPosition = adapter.getPosition(serverValue);
            mSpinnerID.setSelection(spinnerPosition);
            mSpinnerID.setEnabled(false);
        }
    }
    private void mSetServerValuesToSpinnerELSE(Spinner mSpinnerID)
    {
        mSpinnerID.setEnabled(false);
    }
    private void mSetServerValuesToEditText(String serverValue, TextView editTextID)
    {
        editTextID.setText(serverValue);
        editTextID.setEnabled(false);
    }

    private void mSetServerValuesToEditTextELSE(TextView editTextID)
    {
        editTextID.setEnabled(false);
    }

    private void mSetScreenedDataToViews()
    {
        if(intentRecorderRefData!=null)
        {
            mHideAllMandatoryCheckBox();
            if(intentRecorderRefData.getPatient()!=null)
            {
                ArrayList<ReferredSurveyDataResponse.DataPatientEntity> memberDataList=intentRecorderRefData.getPatient();
                ReferredSurveyDataResponse.DataPaitentEntity memberData=memberDataList.get(0).getData();
                mTxtPatientPPPID.setText(memberData.getPppId() + "");
                mTxtPatientName.setText(memberData.getFirstname() + " " + memberData.getLastname());
                mTxtPatientGenderAge.setText(memberData.getGender() + "");
                mTxtPatientMobile.setText(memberData.getMobileNo() + "");
                mTxtPatientAddress.setText(memberData.getAddress() + "");
                mTxtPatientDistrict.setText(memberData.getDistrict() + "");
            }

            ArrayList<ReferredSurveyDataResponse.DataOpenEntity> dataIndexEntity= intentRecorderRefData.getData();
            for (ReferredSurveyDataResponse.DataOpenEntity dataModel:dataIndexEntity) {
                ReferredSurveyDataResponse.DataEntity dataEntity=dataModel.getData();

                if(dataModel.getTitle().equals(IntentParams.TITLE_GEN_EXAM))
                {
                    if(dataEntity.getWeight()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getWeight(),mCVinputWeightGenPhy);
                    }
                    else {
                        mSetServerValuesToEditTextELSE(mCVinputWeightGenPhy);
                    }
                    if(dataEntity.getHeight()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getHeight(),mCVinputHeightGenPhy);
                    }else {
                        mSetServerValuesToEditTextELSE(mCVinputHeightGenPhy);
                    }
                    if(dataEntity.getBmi()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getBmi(),mCVinputBMIGenPhy);
                    }else {
                        mSetServerValuesToEditTextELSE(mCVinputBMIGenPhy);
                    }
                    if(dataEntity.getPulseRate()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getPulseRate(),mCVinputPulseGenPhy);
                    }else {
                        mSetServerValuesToEditTextELSE(mCVinputPulseGenPhy);
                    }
                    if(dataEntity.getBloodPressure()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getBloodPressure(),mCVinputBPGenPhy);
                    }else {
                        mSetServerValuesToEditTextELSE(mCVinputBPGenPhy);
                    }
                    if(dataEntity.getPallor()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getPallor(),mCVdropPallorGenPhy,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCVdropPallorGenPhy);
                    }

                    if(dataEntity.getJaundice()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getJaundice(),mCVdropJaundiceGenPhy,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCVdropJaundiceGenPhy);
                    }
                    if(dataEntity.getClubbing()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getClubbing(),mCVdropClubbingGenPhy,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCVdropClubbingGenPhy);
                    }

                    if(dataEntity.getLymphadenopathy()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getLymphadenopathy(),mCVdropLymphadenopathyGenPhy,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCVdropLymphadenopathyGenPhy);
                    }
                    if(dataEntity.getPedalOedema()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getPedalOedema(),mCVdropPOedemaGenPhy,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCVdropPOedemaGenPhy);
                    }


                }
                if(dataModel.getTitle().equals(IntentParams.TITLE_MILESTONE))
                {

                }
                if(dataModel.getTitle().equals(IntentParams.TITLE_SYS_EXAM))
                {
                    if(dataEntity.getChest()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getChest(),mCVdropChestSysExa,R.array.arr_chest);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCVdropChestSysExa);
                    }
                    if(dataEntity.getCvs()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getCvs(),mCVdropCVSSysExa,R.array.arr_cvs);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCVdropCVSSysExa);
                    }
                    if(dataEntity.getPerAbdomen()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getPerAbdomen(),mCVdropPAbdomenSysExa,R.array.arr_per_abdomen);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCVdropPAbdomenSysExa);
                    }

                    if(dataEntity.getCns()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getCns(),mCVdropCNSSysExa,R.array.arr_cns);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCVdropCNSSysExa);
                    }

                    if(dataEntity.getHearing()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getHearing(),mCVdropHearingSysExa,R.array.arr_hearing);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCVdropHearingSysExa);
                    }

                    if(dataEntity.getLeftEye()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getLeftEye(),mCVinputLeftEyeSysExa);
                    }else {
                        mSetServerValuesToEditTextELSE(mCVinputLeftEyeSysExa);
                    }

                    if(dataEntity.getRightEye()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getRightEye(),mCVinputRightEyeSysExa);
                    }else {
                        mSetServerValuesToEditTextELSE(mCVinputRightEyeSysExa);
                    }


                    if(dataEntity.getColorVision()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getColorVision(),mCVdropColourBlindnesSysExa,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCVdropColourBlindnesSysExa);
                    }


                    if(dataEntity.getDentalExam()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getDentalExam(),mCVdropDentalSysExa,R.array.arr_dental);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCVdropDentalSysExa);
                    }
                    if(dataEntity.getGenitalExam()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getGenitalExam(),mCVdropGenitalSysExa,R.array.arr_genital);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCVdropGenitalSysExa);
                    }
                    if(dataEntity.getBreastExam()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getBreastExam(),mCVinputBreastSysExa);
                    }else {
                        mSetServerValuesToEditTextELSE(mCVinputBreastSysExa);
                    }

                    if(dataEntity.getJointExam()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getJointExam(),mCVdropJointExSysExa,R.array.arr_genital);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCVdropJointExSysExa);
                    }

                    if(dataEntity.getOralExam()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getOralExam(),mCVdropOralExamSysExa,R.array.arr_orl_exm);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCVdropOralExamSysExa);
                    }
                }
                if(dataModel.getTitle().equals(IntentParams.TITLE_MAN_INVEST))
                {

                }
                if(dataModel.getTitle().equals(IntentParams.TITLE_DIAGNOSIS))
                {
                    if(dataEntity.getAlreadyKnown()!=null)
                    {
                        mCVchkDAlreadyKnown.setChecked(!dataEntity.getAlreadyKnown().equalsIgnoreCase("No"));
                    }
                    mCVchkDAlreadyKnown.setEnabled(false);
                    if(dataEntity.getPrescription()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getPrescription(),mCVinputPrescription);
                    }

                    if(dataEntity.getDiagnosed()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getDiagnosed(),cVViewDiagnosis);
                        cVViewDiagnosis.setVisibility(View.VISIBLE);
                        multiSpinner.setVisibility(View.GONE);
                        multiSpinner.setEnabled(false);
                    }else {
                        multiSpinner.setVisibility(View.GONE);
                        cVViewDiagnosis.setVisibility(View.GONE);
                        multiSpinner.setEnabled(false);
                    }

                }
            }


        }
    }

    private void initView() {
        cVViewDiagnosis=findViewById(R.id.cVViewDiagnosis);

        mTxtPatientPPPID = findViewById(R.id.cVtxtPatientPPPID);
        mTxtPatientName = findViewById(R.id.cVtxtPatientName);
        mTxtPatientGenderAge = findViewById(R.id.cVtxtPatientGenderAge);
        mTxtPatientMobile = findViewById(R.id.cVtxtPatientMobile);
        mTxtPatientAddress = findViewById(R.id.cVtxtPatientAddress);
        mTxtPatientDistrict = findViewById(R.id.cVtxtPatientDistrict);
        mTxtFacilityName = findViewById(R.id.cVtxtFacilityName);
        mTxtFacilityIncharge = findViewById(R.id.cVtxtFacilityIncharge);
        mTxtDate = findViewById(R.id.cVtxtDate);
        LinearLayout mLyGenPhysicalEx = findViewById(R.id.cVlyGenPhysicalEx);
        mLyGenPhysicalEx.setOnClickListener(this);
        mIconGenPhy = findViewById(R.id.cViconGenPhy);
        mLyGenPhysicalxValue = findViewById(R.id.cVlyGenPhysicalxValue);

        LinearLayout mLySysExa = findViewById(R.id.cVlySysExa);
        mLySysExa.setOnClickListener(this);
        mIconSysExa = findViewById(R.id.cViconSysExa);
        mLySysExaValue = findViewById(R.id.cVlySysExaValue);

        LinearLayout mLyMandatoryInvest = findViewById(R.id.cVlyMandatoryInvest);
        mLyMandatoryInvest.setOnClickListener(this);
        mIconMandatoryInvest = findViewById(R.id.cViconMandatoryInvest);
        mLyMandatoryInvestValue = findViewById(R.id.cVlyMandatoryInvestValue);

        LinearLayout mLyDiagnosis = findViewById(R.id.cVlyDiagnosis);
        mLyDiagnosis.setOnClickListener(this);
        mIconDiagnosis = findViewById(R.id.cViconDiagnosis);
        mLyDiagnosisValue = findViewById(R.id.cVlyDiagnosisValue);

        LinearLayout mLyPrescription = findViewById(R.id.cVlyPrescription);
        mLyPrescription.setOnClickListener(this);
        mIconPrescription = findViewById(R.id.cViconPrescription);
        mLyPrescriptionValue = findViewById(R.id.cVlyPrescriptionValue);
        mCVinputWeightGenPhy =  findViewById(R.id.cVinputWeightGenPhy);
        mCVinputHeightGenPhy =  findViewById(R.id.cVinputHeightGenPhy);
        mCVinputBMIGenPhy =  findViewById(R.id.cVinputBMIGenPhy);
        mCVinputPulseGenPhy =  findViewById(R.id.cVinputPulseGenPhy);
        mCVinputBPGenPhy =  findViewById(R.id.cVinputBPGenPhy);
        mCVdropPallorGenPhy =findViewById(R.id.cVdropPallorGenPhy);
        mCVdropJaundiceGenPhy =findViewById(R.id.cVdropJaundiceGenPhy);
        mCVdropClubbingGenPhy =findViewById(R.id.cVdropClubbingGenPhy);
        mCVdropLymphadenopathyGenPhy =findViewById(R.id.cVdropLymphadenopathyGenPhy);
        mCVdropPOedemaGenPhy =findViewById(R.id.cVdropPOedemaGenPhy);
        mCVdropChestSysExa =findViewById(R.id.cVdropChestSysExa);
        mCVdropCVSSysExa =findViewById(R.id.cVdropCVSSysExa);
        mCVdropPAbdomenSysExa =findViewById(R.id.cVdropPAbdomenSysExa);
        mCVdropCNSSysExa =findViewById(R.id.cVdropCNSSysExa);
        mCVdropHearingSysExa =findViewById(R.id.cVdropHearingSysExa);
        mCVinputRightEyeSysExa =  findViewById(R.id.cVinputRightEyeSysExa);
        mCVinputLeftEyeSysExa =  findViewById(R.id.cVinputLeftEyeSysExa);
        mCVdropColourBlindnesSysExa =findViewById(R.id.cVdropColourBlindnesSysExa);
        mCVdropDentalSysExa =findViewById(R.id.cVdropDentalSysExa);
        mCVdropGenitalSysExa =findViewById(R.id.cVdropGenitalSysExa);
        mCVinputBreastSysExa =  findViewById(R.id.cVinputBreastSysExa);
        mCVdropJointExSysExa =findViewById(R.id.cVdropJointExSysExa);
        mCVdropOralExamSysExa =findViewById(R.id.cVdropOralExamSysExa);
        mCVchkHBMandatoryInvest =findViewById(R.id.cVchkHBMandatoryInvest);
        mCVinputHBMandatoryInvest =  findViewById(R.id.cVinputHBMandatoryInvest);
        mCVchkTLCMandatoryInvest =findViewById(R.id.cVchkTLCMandatoryInvest);
        mCVinputTLCMandatoryInvest =  findViewById(R.id.cVinputTLCMandatoryInvest);
        mCVchkDLCMandatoryInvest =findViewById(R.id.cVchkDLCMandatoryInvest);
        mCVinputDLCNeutrophilsMandatoryInvest =  findViewById(R.id.cVinputDLCNeutrophilsMandatoryInvest);
        mCVinputLymphocytesMandatoryInvest =  findViewById(R.id.cVinputLymphocytesMandatoryInvest);
        mCVinputDLCMonocytesMandatoryInvest =  findViewById(R.id.cVinputDLCMonocytesMandatoryInvest);
        mCVinputDLCEosinophilsMandatoryInvest =  findViewById(R.id.cVinputDLCEosinophilsMandatoryInvest);
        mCVinputDLCBasophilsMandatoryInvest =  findViewById(R.id.cVinputDLCBasophilsMandatoryInvest);
        mCVchkPackedCellMandatoryInvest =findViewById(R.id.cVchkPackedCellMandatoryInvest);
        mCVinputPackedCellMandatoryInvest =  findViewById(R.id.cVinputPackedCellMandatoryInvest);
        mCVchkCorpuscularMandatoryInvest =findViewById(R.id.cVchkCorpuscularMandatoryInvest);
        mCVinputCorpuscularMandatoryInvest =  findViewById(R.id.cVinputCorpuscularMandatoryInvest);
        mCVchkCorpuscularHBMandatoryInvest =findViewById(R.id.cVchkCorpuscularHBMandatoryInvest);
        mCVinputCorpuscularHBMandatoryInvest =  findViewById(R.id.cVinputCorpuscularHBMandatoryInvest);
        mCVchkHBConcentrationMandatoryInvest =findViewById(R.id.cVchkHBConcentrationMandatoryInvest);
        mCVinputHBConcentrationMandatoryInvest =  findViewById(R.id.cVinputHBConcentrationMandatoryInvest);
        mCVchkPlateletMandatoryInvest =findViewById(R.id.cVchkPlateletMandatoryInvest);
        mCVinputPlateletMandatoryInvest =  findViewById(R.id.cVinputPlateletMandatoryInvest);
        mCVchkRDWMandatoryInvest =findViewById(R.id.cVchkRDWMandatoryInvest);
        mCVinputRDWMandatoryInvest =  findViewById(R.id.cVinputRDWMandatoryInvest);
        mCVchkRDWSDMandatoryInvest =findViewById(R.id.cVchkRDWSDMandatoryInvest);
        mCVinputRDWSDMandatoryInvest =  findViewById(R.id.cVinputRDWSDMandatoryInvest);
        mCVchkRbcCountMandatoryInvest =findViewById(R.id.cVchkRbcCountMandatoryInvest);
        mCVinputRbcCountMandatoryInvest =  findViewById(R.id.cVinputRbcCountMandatoryInvest);
        mCVchkRBSMandatoryInvest =findViewById(R.id.cVchkRBSMandatoryInvest);
        mCVinputRBSMandatoryInvest =  findViewById(R.id.cVinputRBSMandatoryInvest);
        mCVchkCholesterolMandatoryInvest =findViewById(R.id.cVchkCholesterolMandatoryInvest);
        mCVinputCholesterolMandatoryInvest =  findViewById(R.id.cVinputCholesterolMandatoryInvest);
        mCVchkBloodUreaMandatoryInvest =findViewById(R.id.cVchkBloodUreaMandatoryInvest);
        mCVinputBloodUreaMandatoryInvest =  findViewById(R.id.cVinputBloodUreaMandatoryInvest);
        mCVchkCreatinineMandatoryInvest =findViewById(R.id.cVchkCreatinineMandatoryInvest);
        mCVinputCreatinineMandatoryInvest =  findViewById(R.id.cVinputCreatinineMandatoryInvest);
        mCVchkTSHMandatoryInvest =findViewById(R.id.cVchkTSHMandatoryInvest);
        mCVinputTSHMandatoryInvest =  findViewById(R.id.cVinputTSHMandatoryInvest);
        mCVchkPSAMandatoryInvest =findViewById(R.id.cVchkPSAMandatoryInvest);
        mCVinputPSAMandatoryInvest =  findViewById(R.id.cVinputPSAMandatoryInvest);
        mCVchkUrineMandatoryInvest =findViewById(R.id.cVchkUrineMandatoryInvest);
        mCVinputUrineMandatoryInvest =  findViewById(R.id.cVinputUrineMandatoryInvest);
        mCVchkVIAPAPMandatoryInvest =findViewById(R.id.cVchkVIAPAPMandatoryInvest);
        mCVinputVIAPAPMandatoryInvest =  findViewById(R.id.cVinputVIAPAPMandatoryInvest);
        mCVchkAdvisedMandatoryInvest =findViewById(R.id.cVchkAdvisedMandatoryInvest);
        mCVinputAdvisedMandatoryInvest =  findViewById(R.id.cVinputAdvisedMandatoryInvest);
        multiSpinner = findViewById(R.id.cVdropDiagnosis);
        mSetSpinnerData(Arrays.asList(getResources().getStringArray(R.array.arr_diagnosis_cat_1)));
        mCVchkDAlreadyKnown =findViewById(R.id.cVchkDAlreadyKnown);
        mCVinputPrescription =  findViewById(R.id.cVinputPrescription);
        LinearLayout mCVsubmitPatientInput = findViewById(R.id.cVsubmitPatientInput);
        mCVsubmitPatientInput.setOnClickListener(this);
        cVchkAll=findViewById(R.id.cVchkAll);

    }
    private void mSetCheckBoxValesForServer(PostDataForCategoryVRequest request)
    {

        // Step CheckBox
        if(mCVchkHBMandatoryInvest.isChecked())
        {
            request.setHbChecks(IntentParams.STRING_SENT);
        }else {
            request.setHbChecks(IntentParams.STRING_NOT_SENT);
        }
        if(mCVchkTLCMandatoryInvest.isChecked())
        {
            request.setTlcChecks(IntentParams.STRING_SENT);
        }else {
            request.setTlcChecks(IntentParams.STRING_NOT_SENT);
        }
        if(mCVchkDLCMandatoryInvest.isChecked())
        {
            request.setRelevantInvestigationChecks(IntentParams.STRING_SENT);
        }else {
            request.setRelevantInvestigationChecks(IntentParams.STRING_NOT_SENT);
        }
        if(mCVchkPackedCellMandatoryInvest.isChecked())
        {
            request.setPackedCellVolumeChecks(IntentParams.STRING_SENT);
        }else {
            request.setPackedCellVolumeChecks(IntentParams.STRING_NOT_SENT);
        }
        if(mCVchkCorpuscularMandatoryInvest.isChecked())
        {
            request.setMeanCorpuscularVolumeChecks(IntentParams.STRING_SENT);
        }else {
            request.setMeanCorpuscularVolumeChecks(IntentParams.STRING_NOT_SENT);
        }
        if(mCVchkCorpuscularHBMandatoryInvest.isChecked())
        {
            request.setMeanCorpuscularHemoglobinChecks(IntentParams.STRING_SENT);
        }else {
            request.setMeanCorpuscularHemoglobinChecks(IntentParams.STRING_NOT_SENT);
        }
        if(mCVchkHBConcentrationMandatoryInvest.isChecked())
        {
            request.setMeanCorpuscularHemoglobinConcentrationChecks(IntentParams.STRING_SENT);
        }else {
            request.setMeanCorpuscularHemoglobinConcentrationChecks(IntentParams.STRING_NOT_SENT);
        }
        if(mCVchkPlateletMandatoryInvest.isChecked())
        {
            request.setPleteletChecks(IntentParams.STRING_SENT);
        }else {
            request.setPleteletChecks(IntentParams.STRING_NOT_SENT);
        }
        if(mCVchkRDWMandatoryInvest.isChecked())
        {
            request.setRdwCvChecks(IntentParams.STRING_SENT);
        }else {
            request.setRdwCvChecks(IntentParams.STRING_NOT_SENT);
        }
        if(mCVchkRDWSDMandatoryInvest.isChecked())
        {
            request.setRdwSdChecks(IntentParams.STRING_SENT);
        }else {
            request.setRdwSdChecks(IntentParams.STRING_NOT_SENT);
        }

        if(mCVchkRbcCountMandatoryInvest.isChecked())
        {
            request.setRbcChecks(IntentParams.STRING_SENT);
        }else {
            request.setRbcChecks(IntentParams.STRING_NOT_SENT);
        }
        if(mCVchkRBSMandatoryInvest.isChecked())
        {
            request.setRbsChecks(IntentParams.STRING_SENT);
        }else {
            request.setRbsChecks(IntentParams.STRING_NOT_SENT);
        }

        if(mCVchkUrineMandatoryInvest.isChecked())
        {
            request.setUrineRoutineExamChecks(IntentParams.STRING_SENT);
        }else {
            request.setUrineRoutineExamChecks(IntentParams.STRING_NOT_SENT);
        }
        if(mCVchkAdvisedMandatoryInvest.isChecked())
        {
            request.setRelevantInvestigationChecks(IntentParams.STRING_SENT);
        }else {
            request.setRelevantInvestigationChecks(IntentParams.STRING_NOT_SENT);
        }

        if(mCVchkCholesterolMandatoryInvest.isChecked())
        {
            request.setSerumCholesterolChecks(IntentParams.STRING_SENT);
        }else {
            request.setSerumCholesterolChecks(IntentParams.STRING_NOT_SENT);
        }

        if(mCVchkBloodUreaMandatoryInvest.isChecked())
        {
            request.setBloodUreaChecks(IntentParams.STRING_SENT);
        }else {
            request.setBloodUreaChecks(IntentParams.STRING_NOT_SENT);
        }

        if(mCVchkCreatinineMandatoryInvest.isChecked())
        {
            request.setSerumCreatinineChecks(IntentParams.STRING_SENT);
        }else {
            request.setSerumCreatinineChecks(IntentParams.STRING_NOT_SENT);
        }
        if(mCVchkTSHMandatoryInvest.isChecked())
        {
            request.setTshChecks(IntentParams.STRING_SENT);
        }else {
            request.setTshChecks(IntentParams.STRING_NOT_SENT);
        }
        if(mCVchkPSAMandatoryInvest.isChecked())
        {
            request.setPsaChecks(IntentParams.STRING_SENT);
        }else {
            request.setPsaChecks(IntentParams.STRING_NOT_SENT);
        }
        if(mCVchkVIAPAPMandatoryInvest.isChecked())
        {
            request.setPapSmaearChecks(IntentParams.STRING_SENT);
        }else {
            request.setPapSmaearChecks(IntentParams.STRING_NOT_SENT);
        }

    }

    private void mHideAllMandatoryCheckBox()
    {
        cVchkAll.setVisibility(View.GONE);
        mCVchkHBMandatoryInvest.setButtonDrawable(null);
        mCVchkTLCMandatoryInvest.setButtonDrawable(null);
        mCVchkDLCMandatoryInvest.setButtonDrawable(null);
        mCVchkPackedCellMandatoryInvest.setButtonDrawable(null);
        mCVchkCorpuscularMandatoryInvest.setButtonDrawable(null);
        mCVchkCorpuscularHBMandatoryInvest.setButtonDrawable(null);
        mCVchkHBConcentrationMandatoryInvest.setButtonDrawable(null);
        mCVchkPlateletMandatoryInvest.setButtonDrawable(null);
        mCVchkRDWMandatoryInvest.setButtonDrawable(null);
        mCVchkRDWSDMandatoryInvest.setButtonDrawable(null);
        mCVchkRbcCountMandatoryInvest.setButtonDrawable(null);
        mCVchkRBSMandatoryInvest.setButtonDrawable(null);
        mCVchkCholesterolMandatoryInvest.setButtonDrawable(null);
        mCVchkBloodUreaMandatoryInvest.setButtonDrawable(null);
        mCVchkUrineMandatoryInvest.setButtonDrawable(null);
        mCVchkAdvisedMandatoryInvest.setButtonDrawable(null);
        mCVchkCreatinineMandatoryInvest.setButtonDrawable(null);
        mCVchkTSHMandatoryInvest.setButtonDrawable(null);
        mCVchkPSAMandatoryInvest.setButtonDrawable(null);
        mCVchkVIAPAPMandatoryInvest.setButtonDrawable(null);
    }
    private void checkMandatoryAll()
    {

        cVchkAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if(checked)
                {
                    mCVchkHBMandatoryInvest.setChecked(true);
                    mCVchkTLCMandatoryInvest.setChecked(true);
                    mCVchkDLCMandatoryInvest.setChecked(true);
                    mCVchkPackedCellMandatoryInvest.setChecked(true);
                    mCVchkCorpuscularMandatoryInvest.setChecked(true);
                    mCVchkCorpuscularHBMandatoryInvest.setChecked(true);
                    mCVchkHBConcentrationMandatoryInvest.setChecked(true);
                    mCVchkPlateletMandatoryInvest.setChecked(true);
                    mCVchkRDWMandatoryInvest.setChecked(true);
                    mCVchkRDWSDMandatoryInvest.setChecked(true);
                    mCVchkRbcCountMandatoryInvest.setChecked(true);
                    mCVchkRBSMandatoryInvest.setChecked(true);
                    mCVchkCholesterolMandatoryInvest.setChecked(true);
                    mCVchkBloodUreaMandatoryInvest.setChecked(true);
                    mCVchkUrineMandatoryInvest.setChecked(true);
                    mCVchkAdvisedMandatoryInvest.setChecked(true);
                    mCVchkCreatinineMandatoryInvest.setChecked(true);
                    mCVchkTSHMandatoryInvest.setChecked(true);
                    mCVchkPSAMandatoryInvest.setChecked(true);
                    mCVchkVIAPAPMandatoryInvest.setChecked(true);
                }else {
                    mCVchkHBMandatoryInvest.setChecked(false);
                    mCVchkTLCMandatoryInvest.setChecked(false);
                    mCVchkDLCMandatoryInvest.setChecked(false);
                    mCVchkPackedCellMandatoryInvest.setChecked(false);
                    mCVchkCorpuscularMandatoryInvest.setChecked(false);
                    mCVchkCorpuscularHBMandatoryInvest.setChecked(false);
                    mCVchkHBConcentrationMandatoryInvest.setChecked(false);
                    mCVchkPlateletMandatoryInvest.setChecked(false);
                    mCVchkRDWMandatoryInvest.setChecked(false);
                    mCVchkRDWSDMandatoryInvest.setChecked(false);
                    mCVchkRbcCountMandatoryInvest.setChecked(false);
                    mCVchkRBSMandatoryInvest.setChecked(false);
                    mCVchkCholesterolMandatoryInvest.setChecked(false);
                    mCVchkBloodUreaMandatoryInvest.setChecked(false);
                    mCVchkUrineMandatoryInvest.setChecked(false);
                    mCVchkAdvisedMandatoryInvest.setChecked(false);
                    mCVchkCreatinineMandatoryInvest.setChecked(false);
                    mCVchkTSHMandatoryInvest.setChecked(false);
                    mCVchkPSAMandatoryInvest.setChecked(false);
                    mCVchkVIAPAPMandatoryInvest.setChecked(false);
                }
            }
        });
    }

    private void updateBMI()
    {
        if(!TextUtils.isEmpty(mCVinputWeightGenPhy.getText())&& !TextUtils.isEmpty(mCVinputHeightGenPhy.getText()))
        {
            double weight = Double.parseDouble(mCVinputWeightGenPhy.getText().toString());
            double height = Double.parseDouble(mCVinputHeightGenPhy.getText().toString()) / 100.0;

            // Calculating BMI
            double calculatedBmi = weight / (height * height);
            Log.e(" calculatedBmi ",""+calculatedBmi);
            String formattedBmi = String.format("%.2f", calculatedBmi);
            mCVinputBMIGenPhy.setText(formattedBmi);
        }

    }
    private void mSetValidationListeners()
    {
        mCVinputWeightGenPhy.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() > 0)
                {
                    String inputString=mCVinputWeightGenPhy.getText().toString();

                    int input=Integer.parseInt(inputString);
                    if(!(input >= 5 && input <= 120))
                    {
                        mCVinputWeightGenPhy.setError("*be between 5-120");
                    }
                    updateBMI();
                }
            }
        });
        mCVinputHeightGenPhy.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() > 0)
                {
                    String inputString=mCVinputHeightGenPhy.getText().toString();
                    int input=Integer.parseInt(inputString);
                    if(!(input >= 90 && input <= 215))
                    {
                        mCVinputHeightGenPhy.setError("*should be between 90-215");
                    }
                    updateBMI();

                }
            }
        });
        mCVinputPulseGenPhy.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() != 0)
                {
                    String inputString=mCVinputPulseGenPhy.getText().toString();
                    int input=Integer.parseInt(inputString);
                    if(!(input >= 30 && input <= 250))
                    {
                        mCVinputPulseGenPhy.setError("*should be between 30-250");
                    }
                }
            }
        });
        mCVinputBPGenPhy.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() != 0)
                {
                    String inputString=mCVinputBPGenPhy.getText().toString();
                    if(inputString.contains("/"))
                    {
                        String[] inputValues=inputString.split("/");
                        int inputOne=Integer.parseInt(inputValues[0]);
                        if(!(inputOne >= 40 && inputOne <= 300))
                        {
                            mCVinputBPGenPhy.setError("*should be like 40-300");
                        }
                        if(inputValues.length>1 && inputValues[1]!=null)
                        {
                            int inputTwo=Integer.parseInt(inputValues[1]);
                            if(!(inputTwo >= 40 && inputTwo <= 300))
                            {
                                mCVinputBPGenPhy.setError("*should be like 40-300");
                            }
                        }
                    }
                    else
                    {
                        int inputOne=Integer.parseInt(inputString);
                        if(!(inputOne >= 40 && inputOne <= 300))
                        {
                            mCVinputBPGenPhy.setError("*should be like (34/33)");
                        }
                    }

                }
            }
        });
    }

    private boolean validateDataToPost() {

        if (TextUtils.isEmpty(mCVinputWeightGenPhy.getText())) {

            mShowToast("Please enter Weight ");
            return false;
        }
        if (!TextUtils.isEmpty(mCVinputWeightGenPhy.getText())) {
            String inputString = mCVinputWeightGenPhy.getText().toString();
            int input = Integer.parseInt(inputString);
            if (!(input >= 5 && input <= 120)) {
                mShowToast("Weight should be between 5-120");
                return false;
            }
        }
        if (TextUtils.isEmpty(mCVinputHeightGenPhy.getText())) {
            mShowToast("Please enter Height ");
            return false;
        }
        if (!TextUtils.isEmpty(mCVinputHeightGenPhy.getText())) {
            String inputString = mCVinputHeightGenPhy.getText().toString();
            int input = Integer.parseInt(inputString);
            if (!(input >= 90 && input <= 215)) {
                mShowToast("Height should be between 90-215");
                return false;
            }
        }
        if (TextUtils.isEmpty(mCVinputPulseGenPhy.getText())) {
            mShowToast("Please enter Pulse(per min) ");
            return false;
        }
        if (!TextUtils.isEmpty(mCVinputPulseGenPhy.getText())) {
            String inputString = mCVinputPulseGenPhy.getText().toString();
            int input = Integer.parseInt(inputString);
            if (!(input >= 30 && input <= 250)) {
                mShowToast("Pulse(per min) should be between 30-250");
                return false;
            }
        }
        if (TextUtils.isEmpty(mCVinputBPGenPhy.getText())) {
            mShowToast("Please enter BP (mmHg) ");
            return false;
        }

        if (!TextUtils.isEmpty(mCVinputBPGenPhy.getText())) {
            String inputString = mCVinputBPGenPhy.getText().toString();
            if (inputString.contains("/")) {
                String[] inputValues = inputString.split("/");
                int inputOne = Integer.parseInt(inputValues[0]);
                if (!(inputOne >= 40 && inputOne <= 300)) {
                    mShowToast("BP should be between 40-300");
                    return false;
                }
                if (inputValues.length > 1 && inputValues[1] != null) {
                    int inputTwo = Integer.parseInt(inputValues[1]);
                    if (!(inputTwo >= 40 && inputTwo <= 300)) {
                        mShowToast("BP should be between 40-300");
                        return false;
                    }
                }
            } else {
                mShowToast("BP should be like (34/33)");
                return false;
            }

        }
        if (TextUtils.isEmpty(mCVinputRightEyeSysExa.getText())) {
            mShowToast("Please enter Right eye - Refraction ");
            return false;
        }
        if (TextUtils.isEmpty(mCVinputLeftEyeSysExa.getText())) {
            mShowToast("Please enter Left eye - Refraction ");
            return false;
        }
        if (selectedDiagnosis.length()==0) {
            mShowToast("Please select an option for Diagnosis");
            return false;
        }
        return true;
    }
    private void mSetSpinnerData(List<String> mList) {
        multiSpinner.setItems(mList, "Select Diagnosis", new MultiSpinner.MultiSpinnerListener() {
            @Override
            public void onItemsSelected(boolean[] selected) {
                Log.e(" onItemsSelected ", " onItemsSelected ");
                StringBuilder spinnerBuffer = new StringBuilder();
                StringBuilder spinnerBufferID = new StringBuilder();
                boolean someUnselected = false;
                for (int i = 0; i < mList.size(); i++) {
                    if (selected[i]) {
                        spinnerBufferID.append(i + 1);
                        spinnerBufferID.append(", ");
                        spinnerBuffer.append(mList.get(i));
                        spinnerBuffer.append(", ");
                    } else {
                        someUnselected = true;
                    }
                }
                if (someUnselected) {
                    selectedDiagnosis = spinnerBuffer.toString();
                    if (selectedDiagnosis.length() > 2)
                        selectedDiagnosis = selectedDiagnosis.substring(0, selectedDiagnosis.length() - 2);
                }


            }
        });

    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.cVlyGenPhysicalEx) {
            if (mClickOne) {
                mShowHideLayouts(0);
                mClickOne = false;
            } else {
                mShowHideLayoutsAll();
                mClickOne = true;
            }
        }
        if (view.getId() == R.id.cVlySysExa) {
            if (mClickTwo) {
                mShowHideLayouts(1);
                mClickTwo = false;
            } else {
                mShowHideLayoutsAll();
                mClickTwo = true;
            }
        }
        if (view.getId() == R.id.cVlyMandatoryInvest) {
            if (mClickThree) {
                mShowHideLayouts(2);
                mClickThree = false;
            } else {
                mShowHideLayoutsAll();
                mClickThree = true;
            }
        }
        if (view.getId() == R.id.cVlyDiagnosis) {
            if (mClickFour) {
                mShowHideLayouts(3);
                mClickFour = false;
            } else {
                mShowHideLayoutsAll();
                mClickFour = true;
            }
        }
        if (view.getId() == R.id.cVlyPrescription) {
            if (mClickFive) {
                mShowHideLayouts(4);
                mClickFive = false;
            } else {
                mShowHideLayoutsAll();
                mClickFive = true;
            }
        }
        if (view.getId() == R.id.cVsubmitPatientInput) {
            if(getIntent().getSerializableExtra(IntentParams.MEMBER_TYPE).equals("1"))
            {
                if (validateDataToPost()) {
                    postDataForCategories();
                }
            }else {
                postDataForReferenceCategories();
            }
        }
    }



    private void mShowHideLayouts(int clicked) {
        if (clicked == 0) {
            mClickOne = false;
            mClickTwo = true;
            mClickThree = true;
            mClickFour = true;
            mClickFive = true;
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
        } else if (clicked == 1) {
            mClickOne = true;
            mClickTwo = false;
            mClickThree = true;
            mClickFour = true;
            mClickFive = true;
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
        } else if (clicked == 2) {
            mClickOne = true;
            mClickTwo = true;
            mClickThree = false;
            mClickFour = true;
            mClickFive = true;
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
        } else if (clicked == 3) {
            mClickOne = true;
            mClickTwo = true;
            mClickThree = true;
            mClickFour = false;
            mClickFive = true;
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
        } else if (clicked == 4) {
            mClickOne = true;
            mClickTwo = true;
            mClickThree = true;
            mClickFour = true;
            mClickFive = false;
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

    private void mShowHideLayoutsAll() {
        mClickOne = true;
        mClickTwo = true;
        mClickThree = true;
        mClickFour = true;
        mClickFive = true;
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

    public void postDataForCategories() {
        try {
            if (isNetworkAvailable()) {
                createProgressBar(R.id.cVrelMain);
                APIInterface apiInterface = ApiClient.getClientAuthenticationWithAuth(preferences.getString(SharedParams.AUTH_TOKEN,"")).create(APIInterface.class);
                PostDataForCategoryVRequest request = new PostDataForCategoryVRequest();
                request.setCreatedBy(doctorName);
                request.setCreatedDate(getDateToSend());
                request.setPatientId(memberData.getMemberid());
                request.setDistrict(preferences.getString(SharedParams.DISTRICT, null));
                request.setFacility(sharedPreferences.getString(SharedParams.FACTYPE, "") + "/" + sharedPreferences.getString(SharedParams.FACILITY, ""));
                request.setCategory("5");
                request.setUserId(preferences.getString(SharedParams.SUB, null));


                // Step 1
                request.setWeight(mCVinputWeightGenPhy.getText().toString());
                request.setHeight(mCVinputHeightGenPhy.getText().toString());
                request.setBmi(mCVinputBMIGenPhy.getText().toString() + "");
                request.setPulseRate(mCVinputPulseGenPhy.getText().toString());
                request.setBloodPressure(mCVinputBPGenPhy.getText().toString());
                request.setPallor(mCVdropPallorGenPhy.getSelectedItem() + "");
                request.setJaundiceCyanosis(mCVdropJaundiceGenPhy.getSelectedItem() + "");
                request.setClubbing(mCVdropClubbingGenPhy.getSelectedItem() + "");
                request.setLymphadenopathy(mCVdropLymphadenopathyGenPhy.getSelectedItem() + "");
                request.setPedalOedema(mCVdropPOedemaGenPhy.getSelectedItem() + "");

                // Step 2
                request.setChest(mCVdropChestSysExa.getSelectedItem() + "");
                request.setCvs(mCVdropCVSSysExa.getSelectedItem() + "");
                request.setPerAbdomen(mCVdropPAbdomenSysExa.getSelectedItem() + "");
                request.setCns(mCVdropCNSSysExa.getSelectedItem() + "");
                request.setHearing(mCVdropHearingSysExa.getSelectedItem() + "");
                request.setRightEye(mCVinputRightEyeSysExa.getText().toString() + "");
                request.setLeftEye(mCVinputLeftEyeSysExa.getText().toString() + "");
                request.setColorVision(mCVdropColourBlindnesSysExa.getSelectedItem() + "");
                request.setDentalExaminationAbnormality(mCVdropDentalSysExa.getSelectedItem() + "");
                request.setGenitalExamination(mCVdropGenitalSysExa.getSelectedItem() + "");
                request.setBreastExamination(mCVinputBreastSysExa.getText().toString() + "");
                request.setJointExamination(mCVdropJointExSysExa.getSelectedItem() + "");
                request.setOralExamination(mCVdropOralExamSysExa.getSelectedItem()  + "");

                // step 3
                request.sethB(mCVinputHBMandatoryInvest.getText().toString());
                request.setTlc(mCVinputTLCMandatoryInvest.getText().toString());
                request.setNeutrophils(mCVinputDLCNeutrophilsMandatoryInvest.getText().toString());
                request.setLymphocytes(mCVinputLymphocytesMandatoryInvest.getText().toString());
                request.setMonocytes(mCVinputDLCMonocytesMandatoryInvest.getText().toString());
                request.setEosinophils(mCVinputDLCEosinophilsMandatoryInvest.getText().toString());
                request.setBasophils(mCVinputDLCBasophilsMandatoryInvest.getText().toString());
                request.setPackedCellVolume(mCVinputPackedCellMandatoryInvest.getText().toString());
                request.setMeanCorpusVolume(mCVinputCorpuscularMandatoryInvest.getText().toString());
                request.setMeanCorpusHemoglobin(mCVinputCorpuscularHBMandatoryInvest.getText().toString());
                request.setMeanCorpusHemoglobinConcentration(mCVinputHBConcentrationMandatoryInvest.getText().toString());
                request.setPlatletCount(mCVinputPlateletMandatoryInvest.getText().toString());
                request.setRdwCv(mCVinputRDWMandatoryInvest.getText().toString());
                request.setRdwSd(mCVinputRDWSDMandatoryInvest.getText().toString());
                request.setRbcCount(mCVinputRbcCountMandatoryInvest.getText().toString());
                request.setRbs(mCVinputRBSMandatoryInvest.getText().toString());
                request.setSerumCholestrol(mCVinputCholesterolMandatoryInvest.getText().toString());
                request.setBloodUrea(mCVinputBloodUreaMandatoryInvest.getText().toString());
                request.setSerumCreatinine(mCVinputCreatinineMandatoryInvest.getText().toString());
                request.setTsh(mCVinputTSHMandatoryInvest.getText().toString());
                request.setPsaForMales(mCVinputPSAMandatoryInvest.getText().toString());
                request.setPapSmear(mCVinputVIAPAPMandatoryInvest.getText().toString());
                request.setUrineRoutineExamination(mCVinputUrineMandatoryInvest.getText().toString());
                request.setRelevantInvestigation(mCVinputAdvisedMandatoryInvest.getText().toString());


                //step 4
                request.setDiagnosed(selectedDiagnosis);
                if (mCVchkDAlreadyKnown.isChecked()) {
                    request.setAlreadyKnown("yes");
                } else {
                    request.setAlreadyKnown("no");
                }
                //step 5
                request.setPrescription(mCVinputPrescription.getText().toString());
                mSetCheckBoxValesForServer(request);
                Log.e(" request ", "" + request.toString());
                Call<SubmitPatientData> call = apiInterface.submitDataForSurveyCatV(request);
                call.enqueue(new Callback<SubmitPatientData>() {
                    @Override
                    public void onResponse(Call<SubmitPatientData> call, Response<SubmitPatientData> response) {
                        try {
                            if (response.isSuccessful()) {
                                referenceGeneratedPopup(CategoryVPatientEntryActivity.this,response.body().getRefernceId());
//                                mShowToast("Submitted Successfully with reference id "+response.body().getRefernceId());
                            } else {
                                mHandleApiErrorCode(response.code(),response.errorBody().string(), CategoryVPatientEntryActivity.this);
                            }
                            disableProgressBar();

                        } catch (Exception e) {
                            Log.e(" Exception ", "" + e.getMessage());
                            disableProgressBar();
                        }


                    }

                    @Override
                    public void onFailure(Call<SubmitPatientData> call, Throwable t) {
                        mShowToast(getString(R.string.api_failure));
                        disableProgressBar();
                    }
                });
            } 

        } catch (Exception ee) {
            Log.e(" Exception ", "" + ee.getMessage());
        }

    }

    public void postDataForReferenceCategories() {
        try {
            if(isNetworkAvailable()) {
                createProgressBar(R.id.cVrelMain);
                APIInterface apiInterface = ApiClient.getClientAuthenticationWithAuth(preferences.getString(SharedParams.AUTH_TOKEN,"")).create(APIInterface.class);
                PostMandatoryDataRequest request = new PostMandatoryDataRequest();
                request.setReferenceId(intentRecorderRefData.getData().get(0).getData().getReferenceid());
                request.setPatientId(memberData.getMemberid());
                request.setHb(mCVinputHBMandatoryInvest.getText().toString());
                request.setTlc(mCVinputTLCMandatoryInvest.getText().toString());
                request.setNeutrophils(mCVinputDLCNeutrophilsMandatoryInvest.getText().toString());
                request.setLymphocytes(mCVinputLymphocytesMandatoryInvest.getText().toString());
                request.setMonocytes(mCVinputDLCMonocytesMandatoryInvest.getText().toString());
                request.setEosinophils(mCVinputDLCEosinophilsMandatoryInvest.getText().toString());
                request.setBasophils(mCVinputDLCBasophilsMandatoryInvest.getText().toString());
                request.setPackedCellVolume(mCVinputPackedCellMandatoryInvest.getText().toString());
                request.setMeanCorpusVolume(mCVinputCorpuscularMandatoryInvest.getText().toString());
                request.setMeanCorpusHemoglobin(mCVinputCorpuscularHBMandatoryInvest.getText().toString());
                request.setMeanCorpusHemoglobinConcentration(mCVinputHBConcentrationMandatoryInvest.getText().toString());
                request.setPlatletCount(mCVinputPlateletMandatoryInvest.getText().toString());
                request.setRdwCv(mCVinputRDWMandatoryInvest.getText().toString());
                request.setRdwSd(mCVinputRDWSDMandatoryInvest.getText().toString());
                request.setRbcCount(mCVinputRbcCountMandatoryInvest.getText().toString());
                request.setRbs(mCVinputRBSMandatoryInvest.getText().toString());
                request.setSerumCholestrol(mCVinputCholesterolMandatoryInvest.getText().toString());
                request.setBloodUrea(mCVinputBloodUreaMandatoryInvest.getText().toString());
                request.setSerumCreatinine(mCVinputCreatinineMandatoryInvest.getText().toString());
                request.setTsh(mCVinputTSHMandatoryInvest.getText().toString());
                request.setPsaForMales(mCVinputPSAMandatoryInvest.getText().toString());
                request.setPapSmear(mCVinputVIAPAPMandatoryInvest.getText().toString());
                request.setUrineRoutineExamination(mCVinputUrineMandatoryInvest.getText().toString());
                request.setRelevantInvestigation(mCVinputAdvisedMandatoryInvest.getText().toString());

                Call<SubmitPatientData> call = apiInterface.submitMandatoryInvestigationReference(request);
                call.enqueue(new Callback<SubmitPatientData>() {
                    @Override
                    public void onResponse(Call<SubmitPatientData> call, Response<SubmitPatientData> response) {
                        try {
                            if (response.isSuccessful()) {
                                mShowToast("Submitted Successfully !");
                            } else {
                                mHandleApiErrorCode(response.code(),response.errorBody().string(), CategoryVPatientEntryActivity.this);
                            }
                            disableProgressBar();

                        } catch (Exception e) {
                            Log.e(" Exception ", "" + e.getMessage());
                            disableProgressBar();
                        }


                    }

                    @Override
                    public void onFailure(Call<SubmitPatientData> call, Throwable t) {
                        mShowToast(getString(R.string.api_failure));
                        disableProgressBar();
                    }
                });
            }

        } catch (Exception ee) {
            Log.e(" Exception ", "" + ee.getMessage());
        }

    }
}
