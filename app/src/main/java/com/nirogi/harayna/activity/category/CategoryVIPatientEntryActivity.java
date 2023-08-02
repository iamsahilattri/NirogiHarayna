package com.nirogi.harayna.activity.category;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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
import com.nirogi.harayna.model.request.PostDataForCategoryVIRequest;
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

public class CategoryVIPatientEntryActivity extends BaseActivity implements View.OnClickListener {

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

    private LinearLayout cVIlySysCompValue;
    private AppCompatImageView cVIiconSysComp;
    private PatientListModelResponse memberData;
    private SharedPreferences sharedPreferences;

    private MultiSpinner multiSpinner;
    private String selectedDiagnosis="";
    private String doctorName;

    private boolean mClickOne = true, mClickTwo = true, mClickThree = true, mClickFour = true, mClickFive = true, mClickSix = true;
    private AppCompatEditText mCVIinputWeightGenPhy;
    private AppCompatEditText mCVIinputHeightGenPhy;
    private AppCompatEditText mCVIinputBMIGenPhy;
    private AppCompatEditText mCVIinputPulseGenPhy;
    private AppCompatEditText mCVIinputRRPMGenPhy;
    private AppCompatEditText mCVIinputBPGenPhy;
    private AppCompatSpinner mCVIdropChestSysExa;
    private AppCompatSpinner mCVIdropCVSSysExa;
    private AppCompatSpinner mCVIdropPAbdomenSysExa;
    private AppCompatSpinner mCVIdropCNSSysExa;
    private AppCompatSpinner mCVIdropHearingSysExa;
    private AppCompatEditText mCVIinputRightEyeSysExa;
    private AppCompatEditText mCVIinputLeftEyeSysExa;
    private AppCompatSpinner mCVIdropColourBlindnesSysExa;
    private AppCompatSpinner mCVIdropDentalSysExa;
    private AppCompatSpinner mCVIdropGenitalSysExa;
    private AppCompatEditText mCVIinputBreastSysExa;
    private AppCompatSpinner mCVIdropJointExSysExa;
    private AppCompatSpinner mCVIdropOralExmSysExa;
    private AppCompatImageView mCVIiconSysComp;
    private LinearLayout mCVIlySysCompValue;
    private AppCompatSpinner mCVIdropPollerSysComp;
    private AppCompatSpinner mCVIdropJaundiceSysComp;
    private AppCompatSpinner mCVIdropClubbingSysComp;
    private AppCompatSpinner mCVIdropLymphadenopathySysComp;
    private AppCompatSpinner mCVIdropPedaloedemaSysExa;
    private AppCompatCheckBox mCVIchkHBMandatoryInvest;
    private AppCompatEditText mCVIinputHBMandatoryInvest;
    private AppCompatCheckBox mCVIchkTLCMandatoryInvest;
    private AppCompatEditText mCVIinputTLCMandatoryInvest;
    private AppCompatCheckBox mCVIchkDLCMandatoryInvest;
    private AppCompatEditText mCVIinputDLCNeutrophilsMandatoryInvest;
    private AppCompatEditText mCVIinputLymphocytesMandatoryInvest;
    private AppCompatEditText mCVIinputDLCMonocytesMandatoryInvest;
    private AppCompatEditText mCVIinputDLCEosinophilsMandatoryInvest;
    private AppCompatEditText mCVIinputDLCBasophilsMandatoryInvest;
    private AppCompatCheckBox mCVIchkPackedCellMandatoryInvest;
    private AppCompatEditText mCVIinputPackedCellMandatoryInvest;
    private AppCompatCheckBox mCVIchkCorpuscularMandatoryInvest;
    private AppCompatEditText mCVIinputCorpuscularMandatoryInvest;
    private AppCompatCheckBox mCVIchkCorpuscularHBMandatoryInvest;
    private AppCompatEditText mCVIinputCorpuscularHBMandatoryInvest;
    private AppCompatCheckBox mCVIchkHBConcentrationMandatoryInvest;
    private AppCompatEditText mCVIinputHBConcentrationMandatoryInvest;
    private AppCompatCheckBox mCVIchkPlateletMandatoryInvest;
    private AppCompatEditText mCVIinputPlateletMandatoryInvest;
    private AppCompatCheckBox mCVIchkRDWMandatoryInvest;
    private AppCompatEditText mCVIinputRDWMandatoryInvest;
    private AppCompatCheckBox mCVIchkRDWSDMandatoryInvest;
    private AppCompatEditText mCVIinputRDWSDMandatoryInvest;
    private AppCompatCheckBox mCVIchkRbcCountMandatoryInvest;
    private AppCompatEditText mCVIinputRbcCountMandatoryInvest;
    private AppCompatCheckBox mCVIchkRBSMandatoryInvest;
    private AppCompatEditText mCVIinputRBSMandatoryInvest;
    private AppCompatCheckBox mCVIchkCholesterolMandatoryInvest;
    private AppCompatEditText mCVIinputCholesterolMandatoryInvest;
    private AppCompatCheckBox mCVIchkBloodUreaMandatoryInvest;
    private AppCompatEditText mCVIinputBloodUreaMandatoryInvest;
    private AppCompatCheckBox mCVIchkCreatinineMandatoryInvest;
    private AppCompatEditText mCVIinputCreatinineMandatoryInvest,cVIViewDiagnosis;
    private AppCompatCheckBox mCVIchkTSHMandatoryInvest;
    private AppCompatEditText mCVIinputTSHMandatoryInvest;
    private AppCompatCheckBox mCVIchkPSAMandatoryInvest;
    private AppCompatEditText mCVIinputPSAMandatoryInvest;
    private AppCompatCheckBox mCVIchkUrineMandatoryInvest;
    private AppCompatEditText mCVIinputUrineMandatoryInvest;
    private AppCompatCheckBox mCVIchkVIAPAPMandatoryInvest;
    private AppCompatEditText mCVIinputVIAPAPMandatoryInvest;
    private AppCompatCheckBox mCVIchkAdvisedMandatoryInvest;
    private AppCompatEditText mCVIinputAdvisedMandatoryInvest;
    private AppCompatSpinner mCVIdropDiagnosis;
    private AppCompatCheckBox mCVIchkDAlreadyKnown;
    private AppCompatEditText mCVIinputPrescription;
    private AppCompatCheckBox cVIchkSelectAllandatoryInvest;
    private ReferredSurveyDataResponse intentRecorderRefData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = NIROGI.getInstance().getPreferences();
        setContentView(R.layout.activity_patient_input_cat_vi);
        initView();
        mSetValuesToViews();
        initDataToView();
        mSetValidationListeners();
        checkMandatoryAll();
    }

    @Override
    public void onBackPressed() {
        performBackPress(CategoryVIPatientEntryActivity.this);
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

                //General Physical Examination - Done
                if(dataModel.getTitle().equals(IntentParams.TITLE_GEN_EXAM))
                {
                    if(dataEntity.getWeight()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getWeight(),mCVIinputWeightGenPhy);
                    }
                    else {
                        mSetServerValuesToEditTextELSE(mCVIinputWeightGenPhy);
                    }
                    if(dataEntity.getHeight()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getHeight(),mCVIinputHeightGenPhy);
                    }else {
                        mSetServerValuesToEditTextELSE(mCVIinputHeightGenPhy);
                    }
                    if(dataEntity.getBmi()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getBmi(),mCVIinputBMIGenPhy);
                    }else {
                        mSetServerValuesToEditTextELSE(mCVIinputBMIGenPhy);
                    }
                    if(dataEntity.getPulseRate()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getPulseRate(),mCVIinputPulseGenPhy);
                    }else {
                        mSetServerValuesToEditTextELSE(mCVIinputPulseGenPhy);
                    }
                    if(dataEntity.getBloodPressure()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getBloodPressure(),mCVIinputBPGenPhy);
                    }else {
                        mSetServerValuesToEditTextELSE(mCVIinputBPGenPhy);
                    }
                    if(dataEntity.getRespiratoryRate()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getRespiratoryRate(),mCVIinputRRPMGenPhy);
                    }else {
                        mSetServerValuesToEditTextELSE(mCVIinputRRPMGenPhy);
                    }




                }

                //Systemic Investigation - Done
                if(dataModel.getTitle().equals(IntentParams.TITLE_SYS_EXAM))
                {
                    if(dataEntity.getChest()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getChest(),mCVIdropChestSysExa,R.array.arr_chest);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCVIdropChestSysExa);
                    }
                    if(dataEntity.getCvs()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getCvs(),mCVIdropCVSSysExa,R.array.arr_cvs);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCVIdropCVSSysExa);
                    }
                    if(dataEntity.getPerAbdomen()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getPerAbdomen(),mCVIdropPAbdomenSysExa,R.array.arr_per_abdomen);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCVIdropPAbdomenSysExa);
                    }

                    if(dataEntity.getCns()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getCns(),mCVIdropCNSSysExa,R.array.arr_cns);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCVIdropCNSSysExa);
                    }

                    if(dataEntity.getHearing()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getHearing(),mCVIdropHearingSysExa,R.array.arr_hearing);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCVIdropHearingSysExa);
                    }

                    if(dataEntity.getLeftEye()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getLeftEye(),mCVIinputLeftEyeSysExa);
                    }else {
                        mSetServerValuesToEditTextELSE(mCVIinputLeftEyeSysExa);
                    }

                    if(dataEntity.getRightEye()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getRightEye(),mCVIinputRightEyeSysExa);
                    }else {
                        mSetServerValuesToEditTextELSE(mCVIinputRightEyeSysExa);
                    }


                    if(dataEntity.getColorVision()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getColorVision(),mCVIdropColourBlindnesSysExa,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCVIdropColourBlindnesSysExa);
                    }


                    if(dataEntity.getDentalExam()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getDentalExam(),mCVIdropDentalSysExa,R.array.arr_dental);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCVIdropDentalSysExa);
                    }
                    if(dataEntity.getGenitalExam()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getGenitalExam(),mCVIdropGenitalSysExa,R.array.arr_genital);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCVIdropGenitalSysExa);
                    }
                    if(dataEntity.getBreastExam()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getGenitalExam(),mCVIinputBreastSysExa);
                    }else {
                        mSetServerValuesToEditTextELSE(mCVIinputBreastSysExa);
                    }

                    if(dataEntity.getJointExam()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getJointExam(),mCVIdropJointExSysExa,R.array.arr_genital);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCVIdropJointExSysExa);
                    }

                    if(dataEntity.getOralExam()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getOralExam(),mCVIdropOralExmSysExa,R.array.arr_orl_exm);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCVIdropOralExmSysExa);
                    }
                }


//                if(dataEntity.getPallor()!=null)
//                {
//                    mSetServerValuesToSpinner(dataEntity.getPallor(),mCVIdropPollerSysComp,R.array.arr_yes_no);
//                }else {
//                    mSetServerValuesToSpinnerELSE(mCVIdropPollerSysComp);
//                }
//
//                if(dataEntity.getJaundice()!=null)
//                {
//                    mSetServerValuesToSpinner(dataEntity.getJaundice(),mCVIdropJaundiceSysComp,R.array.arr_yes_no);
//                }else {
//                    mSetServerValuesToSpinnerELSE(mCVIdropJaundiceSysComp);
//                }
//                if(dataEntity.getClubbing()!=null)
//                {
//                    mSetServerValuesToSpinner(dataEntity.getClubbing(),mCVIdropClubbingSysComp,R.array.arr_yes_no);
//                }else {
//                    mSetServerValuesToSpinnerELSE(mCVIdropClubbingSysComp);
//                }
//                if(dataEntity.getLymphadenopathy()!=null)
//                {
//                    mSetServerValuesToSpinner(dataEntity.getLymphadenopathy(),mCVIdropLymphadenopathySysComp,R.array.arr_yes_no);
//                }else {
//                    mSetServerValuesToSpinnerELSE(mCVIdropLymphadenopathySysComp);
//                }
//                if(dataEntity.getPedalOedema()!=null)
//                {
//                    mSetServerValuesToSpinner(dataEntity.getPedalOedema(),mCVIdropPedaloedemaSysExa,R.array.arr_yes_no);
//                }else {
//                    mSetServerValuesToSpinnerELSE(mCVIdropPedaloedemaSysExa);
//                }


                if(dataModel.getTitle().equals(IntentParams.TITLE_DIAGNOSIS))
                {
                    if(dataEntity.getAlreadyKnown()!=null)
                    {
                        mCVIchkDAlreadyKnown.setChecked(!dataEntity.getAlreadyKnown().equals("No"));
                    }
                    mCVIchkDAlreadyKnown.setEnabled(false);
                    if(dataEntity.getPrescription()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getPrescription(),mCVIinputPrescription);
                    }

                    if(dataEntity.getDiagnosed()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getDiagnosed(),cVIViewDiagnosis);
                        cVIViewDiagnosis.setVisibility(View.VISIBLE);
                        multiSpinner.setVisibility(View.GONE);
                        multiSpinner.setEnabled(false);
                    }else {
                        multiSpinner.setVisibility(View.GONE);
                        cVIViewDiagnosis.setVisibility(View.GONE);
                        multiSpinner.setEnabled(false);
                    }

                }
            }


        }
    }

    private void initView() {
        cVIViewDiagnosis=findViewById(R.id.cVIViewDiagnosis);

        mTxtPatientPPPID = findViewById(R.id.cVItxtPatientPPPID);
        mTxtPatientName = findViewById(R.id.cVItxtPatientName);
        mTxtPatientGenderAge = findViewById(R.id.cVItxtPatientGenderAge);
        mTxtPatientMobile = findViewById(R.id.cVItxtPatientMobile);
        mTxtPatientAddress = findViewById(R.id.cVItxtPatientAddress);
        mTxtPatientDistrict = findViewById(R.id.cVItxtPatientDistrict);
        mTxtFacilityName = findViewById(R.id.cVItxtFacilityName);
        mTxtFacilityIncharge = findViewById(R.id.cVItxtFacilityIncharge);
        mTxtDate = findViewById(R.id.cVItxtDate);
        LinearLayout mLyGenPhysicalEx = findViewById(R.id.cVIlyGenPhysicalEx);
        mLyGenPhysicalEx.setOnClickListener(this);
        mIconGenPhy = findViewById(R.id.cVIiconGenPhy);
        mLyGenPhysicalxValue = findViewById(R.id.cVIlyGenPhysicalxValue);


        LinearLayout mLySysExa = findViewById(R.id.cVIlySysExa);
        mLySysExa.setOnClickListener(this);
        mIconSysExa = findViewById(R.id.cVIiconSysExa);
        mLySysExaValue = findViewById(R.id.cVIlySysExaValue);

        LinearLayout mLyMandatoryInvest = findViewById(R.id.cVIlyMandatoryInvest);
        mLyMandatoryInvest.setOnClickListener(this);
        mIconMandatoryInvest = findViewById(R.id.cVIiconMandatoryInvest);
        mLyMandatoryInvestValue = findViewById(R.id.cVIlyMandatoryInvestValue);

        LinearLayout mLyDiagnosis = findViewById(R.id.cVIlyDiagnosis);
        mLyDiagnosis.setOnClickListener(this);
        mIconDiagnosis = findViewById(R.id.cVIiconDiagnosis);
        mLyDiagnosisValue = findViewById(R.id.cVIlyDiagnosisValue);

        LinearLayout mLyPrescription = findViewById(R.id.cVIlyPrescription);
        mLyPrescription.setOnClickListener(this);
        mIconPrescription = findViewById(R.id.cVIiconPrescription);
        mLyPrescriptionValue = findViewById(R.id.cVIlyPrescriptionValue);
        LinearLayout cVIlySystomComplainExa = findViewById(R.id.cVIlySystomComplainExa);
        cVIlySystomComplainExa.setOnClickListener(this);
        cVIiconSysComp = findViewById(R.id.cVIiconSysComp);
        cVIlySysCompValue = findViewById(R.id.cVIlySysCompValue);
        mCVIinputWeightGenPhy = findViewById(R.id.cVIinputWeightGenPhy);
        mCVIinputHeightGenPhy = findViewById(R.id.cVIinputHeightGenPhy);
        mCVIinputBMIGenPhy = findViewById(R.id.cVIinputBMIGenPhy);
        mCVIinputPulseGenPhy = findViewById(R.id.cVIinputPulseGenPhy);
        mCVIinputRRPMGenPhy = findViewById(R.id.cVIinputRRPMGenPhy);
        mCVIinputBPGenPhy = findViewById(R.id.cVIinputBPGenPhy);
        mCVIdropChestSysExa =findViewById(R.id.cVIdropChestSysExa);
        mCVIdropCVSSysExa =findViewById(R.id.cVIdropCVSSysExa);
        mCVIdropPAbdomenSysExa =findViewById(R.id.cVIdropPAbdomenSysExa);
        mCVIdropCNSSysExa =findViewById(R.id.cVIdropCNSSysExa);
        mCVIdropHearingSysExa =findViewById(R.id.cVIdropHearingSysExa);
        mCVIinputRightEyeSysExa = findViewById(R.id.cVIinputRightEyeSysExa);
        mCVIinputLeftEyeSysExa = findViewById(R.id.cVIinputLeftEyeSysExa);
        mCVIdropColourBlindnesSysExa =findViewById(R.id.cVIdropColourBlindnesSysExa);
        mCVIdropDentalSysExa =findViewById(R.id.cVIdropDentalSysExa);
        mCVIdropGenitalSysExa =findViewById(R.id.cVIdropGenitalSysExa);
        mCVIinputBreastSysExa = findViewById(R.id.cVIinputBreastSysExa);
        mCVIdropJointExSysExa =findViewById(R.id.cVIdropJointExSysExa);
        mCVIdropOralExmSysExa =findViewById(R.id.cVIdropOralExmSysExa);
        mCVIdropPollerSysComp =findViewById(R.id.cVIdropPollerSysComp);
        mCVIdropJaundiceSysComp =findViewById(R.id.cVIdropJaundiceSysComp);
        mCVIdropClubbingSysComp =findViewById(R.id.cVIdropClubbingSysComp);
        mCVIdropLymphadenopathySysComp =findViewById(R.id.cVIdropLymphadenopathySysComp);
        mCVIdropPedaloedemaSysExa =findViewById(R.id.cVIdropPedaloedemaSysExa);
        mCVIchkHBMandatoryInvest =findViewById(R.id.cVIchkHBMandatoryInvest);
        mCVIinputHBMandatoryInvest=findViewById(R.id.cVIinputHBMandatoryInvest);
        mCVIchkTLCMandatoryInvest =findViewById(R.id.cVIchkTLCMandatoryInvest);
        mCVIinputTLCMandatoryInvest = findViewById(R.id.cVIinputTLCMandatoryInvest);
        mCVIchkDLCMandatoryInvest =findViewById(R.id.cVIchkDLCMandatoryInvest);
        mCVIinputDLCNeutrophilsMandatoryInvest = findViewById(R.id.cVIinputDLCNeutrophilsMandatoryInvest);
        mCVIinputLymphocytesMandatoryInvest = findViewById(R.id.cVIinputLymphocytesMandatoryInvest);
        mCVIinputDLCMonocytesMandatoryInvest = findViewById(R.id.cVIinputDLCMonocytesMandatoryInvest);
        mCVIinputDLCEosinophilsMandatoryInvest = findViewById(R.id.cVIinputDLCEosinophilsMandatoryInvest);
        mCVIinputDLCBasophilsMandatoryInvest = findViewById(R.id.cVIinputDLCBasophilsMandatoryInvest);
        mCVIchkPackedCellMandatoryInvest =findViewById(R.id.cVIchkPackedCellMandatoryInvest);
        mCVIinputPackedCellMandatoryInvest = findViewById(R.id.cVIinputPackedCellMandatoryInvest);
        mCVIchkCorpuscularMandatoryInvest =findViewById(R.id.cVIchkCorpuscularMandatoryInvest);
        mCVIinputCorpuscularMandatoryInvest = findViewById(R.id.cVIinputCorpuscularMandatoryInvest);
        mCVIchkCorpuscularHBMandatoryInvest =findViewById(R.id.cVIchkCorpuscularHBMandatoryInvest);
        mCVIinputCorpuscularHBMandatoryInvest = findViewById(R.id.cVIinputCorpuscularHBMandatoryInvest);
        mCVIchkHBConcentrationMandatoryInvest =findViewById(R.id.cVIchkHBConcentrationMandatoryInvest);
        mCVIinputHBConcentrationMandatoryInvest = findViewById(R.id.cVIinputHBConcentrationMandatoryInvest);
        mCVIchkPlateletMandatoryInvest =findViewById(R.id.cVIchkPlateletMandatoryInvest);
        mCVIinputPlateletMandatoryInvest = findViewById(R.id.cVIinputPlateletMandatoryInvest);
        mCVIchkRDWMandatoryInvest =findViewById(R.id.cVIchkRDWMandatoryInvest);
        mCVIinputRDWMandatoryInvest = findViewById(R.id.cVIinputRDWMandatoryInvest);
        mCVIchkRDWSDMandatoryInvest =findViewById(R.id.cVIchkRDWSDMandatoryInvest);
        mCVIinputRDWSDMandatoryInvest = findViewById(R.id.cVIinputRDWSDMandatoryInvest);
        mCVIchkRbcCountMandatoryInvest =findViewById(R.id.cVIchkRbcCountMandatoryInvest);
        mCVIinputRbcCountMandatoryInvest = findViewById(R.id.cVIinputRbcCountMandatoryInvest);
        mCVIchkRBSMandatoryInvest =findViewById(R.id.cVIchkRBSMandatoryInvest);
        mCVIinputRBSMandatoryInvest = findViewById(R.id.cVIinputRBSMandatoryInvest);
        mCVIchkCholesterolMandatoryInvest =findViewById(R.id.cVIchkCholesterolMandatoryInvest);
        mCVIinputCholesterolMandatoryInvest = findViewById(R.id.cVIinputCholesterolMandatoryInvest);
        mCVIchkBloodUreaMandatoryInvest =findViewById(R.id.cVIchkBloodUreaMandatoryInvest);
        mCVIinputBloodUreaMandatoryInvest = findViewById(R.id.cVIinputBloodUreaMandatoryInvest);
        mCVIchkCreatinineMandatoryInvest =findViewById(R.id.cVIchkCreatinineMandatoryInvest);
        mCVIinputCreatinineMandatoryInvest = findViewById(R.id.cVIinputCreatinineMandatoryInvest);
        mCVIchkTSHMandatoryInvest =findViewById(R.id.cVIchkTSHMandatoryInvest);
        mCVIinputTSHMandatoryInvest = findViewById(R.id.cVIinputTSHMandatoryInvest);
        mCVIchkPSAMandatoryInvest =findViewById(R.id.cVIchkPSAMandatoryInvest);
        mCVIinputPSAMandatoryInvest = findViewById(R.id.cVIinputPSAMandatoryInvest);
        mCVIchkUrineMandatoryInvest =findViewById(R.id.cVIchkUrineMandatoryInvest);
        mCVIinputUrineMandatoryInvest = findViewById(R.id.cVIinputUrineMandatoryInvest);
        mCVIchkVIAPAPMandatoryInvest =findViewById(R.id.cVIchkVIAPAPMandatoryInvest);
        mCVIinputVIAPAPMandatoryInvest = findViewById(R.id.cVIinputVIAPAPMandatoryInvest);
        mCVIchkAdvisedMandatoryInvest =findViewById(R.id.cVIchkAdvisedMandatoryInvest);
        mCVIinputAdvisedMandatoryInvest = findViewById(R.id.cVIinputAdvisedMandatoryInvest);
        multiSpinner =findViewById(R.id.cVIdropDiagnosis);
        mSetSpinnerData(Arrays.asList(getResources().getStringArray(R.array.arr_diagnosis_cat_1)));

        mCVIchkDAlreadyKnown =findViewById(R.id.cVIchkDAlreadyKnown);
        mCVIinputPrescription = findViewById(R.id.cVIinputPrescription);
        LinearLayout cVIsubmitPatientInput=findViewById(R.id.cVIsubmitPatientInput);
        cVIsubmitPatientInput.setOnClickListener(this);
        cVIchkSelectAllandatoryInvest=findViewById(R.id.cVIchkSelectAllandatoryInvest);

    }

    private void checkMandatoryAll()
    {
        cVIchkSelectAllandatoryInvest.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if(checked)
                {
                    mCVIchkHBMandatoryInvest.setChecked(true);
                    mCVIchkTLCMandatoryInvest.setChecked(true);
                    mCVIchkDLCMandatoryInvest.setChecked(true);
                    mCVIchkPackedCellMandatoryInvest.setChecked(true);
                    mCVIchkCorpuscularMandatoryInvest.setChecked(true);
                    mCVIchkCorpuscularHBMandatoryInvest.setChecked(true);
                    mCVIchkHBConcentrationMandatoryInvest.setChecked(true);
                    mCVIchkPlateletMandatoryInvest.setChecked(true);
                    mCVIchkRDWMandatoryInvest.setChecked(true);
                    mCVIchkRDWSDMandatoryInvest.setChecked(true);
                    mCVIchkRbcCountMandatoryInvest.setChecked(true);
                    mCVIchkRBSMandatoryInvest.setChecked(true);
                    mCVIchkCholesterolMandatoryInvest.setChecked(true);
                    mCVIchkBloodUreaMandatoryInvest.setChecked(true);
                    mCVIchkUrineMandatoryInvest.setChecked(true);
                    mCVIchkAdvisedMandatoryInvest.setChecked(true);
                    mCVIchkCreatinineMandatoryInvest.setChecked(true);
                }else {
                    mCVIchkHBMandatoryInvest.setChecked(false);
                    mCVIchkTLCMandatoryInvest.setChecked(false);
                    mCVIchkDLCMandatoryInvest.setChecked(false);
                    mCVIchkPackedCellMandatoryInvest.setChecked(false);
                    mCVIchkCorpuscularMandatoryInvest.setChecked(false);
                    mCVIchkCorpuscularHBMandatoryInvest.setChecked(false);
                    mCVIchkHBConcentrationMandatoryInvest.setChecked(false);
                    mCVIchkPlateletMandatoryInvest.setChecked(false);
                    mCVIchkRDWMandatoryInvest.setChecked(false);
                    mCVIchkRDWSDMandatoryInvest.setChecked(false);
                    mCVIchkRbcCountMandatoryInvest.setChecked(false);
                    mCVIchkRBSMandatoryInvest.setChecked(false);
                    mCVIchkCholesterolMandatoryInvest.setChecked(false);
                    mCVIchkBloodUreaMandatoryInvest.setChecked(false);
                    mCVIchkUrineMandatoryInvest.setChecked(false);
                    mCVIchkAdvisedMandatoryInvest.setChecked(false);
                    mCVIchkCreatinineMandatoryInvest.setChecked(false);
                }
            }
        });
    }


    private void updateBMI()
    {
        if(!TextUtils.isEmpty(mCVIinputWeightGenPhy.getText())&& !TextUtils.isEmpty(mCVIinputHeightGenPhy.getText()))
        {
            double weight = Double.parseDouble(mCVIinputWeightGenPhy.getText().toString());
            double height = Double.parseDouble(mCVIinputHeightGenPhy.getText().toString()) / 100.0;

            // Calculating BMI
            double calculatedBmi = weight / (height * height);
            Log.e(" calculatedBmi ",""+calculatedBmi);
            String formattedBmi = String.format("%.2f", calculatedBmi);
            mCVIinputBMIGenPhy.setText(formattedBmi);
        }

    }
    private void mSetValidationListeners()
    {
        mCVIinputWeightGenPhy.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() > 0)
                {
                    String inputString=mCVIinputWeightGenPhy.getText().toString();

                    int input=Integer.parseInt(inputString);
                    if(!(input >= 5 && input <= 120))
                    {
                        mCVIinputWeightGenPhy.setError("*be between 5-120");
                    }
                    updateBMI();
                }
            }
        });
        mCVIinputHeightGenPhy.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() > 0)
                {
                    String inputString=mCVIinputHeightGenPhy.getText().toString();
                    int input=Integer.parseInt(inputString);
                    if(!(input >= 90 && input <= 215))
                    {
                        mCVIinputHeightGenPhy.setError("*should be between 90-215");
                    }
                    updateBMI();

                }
            }
        });
        mCVIinputPulseGenPhy.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() != 0)
                {
                    String inputString=mCVIinputPulseGenPhy.getText().toString();
                    int input=Integer.parseInt(inputString);
                    if(!(input >= 30 && input <= 250))
                    {
                        mCVIinputPulseGenPhy.setError("*should be between 30-250");
                    }
                }
            }
        });
        mCVIinputRRPMGenPhy.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() != 0)
                {
                    String inputString=mCVIinputRRPMGenPhy.getText().toString();
                    int input=Integer.parseInt(inputString);
                    if(!(input >= 5 && input <= 30))
                    {
                        mCVIinputRRPMGenPhy.setError("*should be between 5-30");
                    }
                }
            }
        });
        mCVIinputBPGenPhy.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() != 0)
                {
                    String inputString=mCVIinputBPGenPhy.getText().toString();
                    if(inputString.contains("/"))
                    {
                        String[] inputValues=inputString.split("/");
                        int inputOne=Integer.parseInt(inputValues[0]);
                        if(!(inputOne >= 40 && inputOne <= 300))
                        {
                            mCVIinputBPGenPhy.setError("*should be like 40-300");
                        }
                        if(inputValues.length>1 && inputValues[1]!=null)
                        {
                            int inputTwo=Integer.parseInt(inputValues[1]);
                            if(!(inputTwo >= 40 && inputTwo <= 300))
                            {
                                mCVIinputBPGenPhy.setError("*should be like 40-300");
                            }
                        }
                    }
                    else
                    {
                        int inputOne=Integer.parseInt(inputString);
                        if(!(inputOne >= 40 && inputOne <= 300))
                        {
                            mCVIinputBPGenPhy.setError("*should be like (34/33)");
                        }
                    }

                }
            }
        });
    }

    private boolean validateDataToPost() {

        if (TextUtils.isEmpty(mCVIinputWeightGenPhy.getText())) {

            mShowToast("Please enter Weight ");
            return false;
        }
        if (!TextUtils.isEmpty(mCVIinputWeightGenPhy.getText())) {
            String inputString = mCVIinputWeightGenPhy.getText().toString();
            int input = Integer.parseInt(inputString);
            if (!(input >= 5 && input <= 120)) {
                mShowToast("Weight should be between 5-120");
                return false;
            }
        }
        if (TextUtils.isEmpty(mCVIinputHeightGenPhy.getText())) {
            mShowToast("Please enter Height ");
            return false;
        }
        if (!TextUtils.isEmpty(mCVIinputHeightGenPhy.getText())) {
            String inputString = mCVIinputHeightGenPhy.getText().toString();
            int input = Integer.parseInt(inputString);
            if (!(input >= 90 && input <= 215)) {
                mShowToast("Height should be between 90-215");
                return false;
            }
        }
        if (TextUtils.isEmpty(mCVIinputPulseGenPhy.getText())) {
            mShowToast("Please enter Pulse(per min) ");
            return false;
        }
        if (!TextUtils.isEmpty(mCVIinputPulseGenPhy.getText())) {
            String inputString = mCVIinputPulseGenPhy.getText().toString();
            int input = Integer.parseInt(inputString);
            if (!(input >= 30 && input <= 250)) {
                mShowToast("Pulse(per min) should be between 30-250");
                return false;
            }
        }
        if (TextUtils.isEmpty(mCVIinputBPGenPhy.getText())) {
            mShowToast("Please enter BP (mmHg) ");
            return false;
        }
        if (!TextUtils.isEmpty(mCVIinputRRPMGenPhy.getText())) {
            String inputString = mCVIinputRRPMGenPhy.getText().toString();
            int input = Integer.parseInt(inputString);
            if (!(input >= 5 && input <= 30)) {
                mShowToast("Respiratory rate should be between 5-30");
                return false;
            }
        }
        if (TextUtils.isEmpty(mCVIinputRRPMGenPhy.getText())) {
            mShowToast("Please enter Respiratory rate ");
            return false;
        }
        if (!TextUtils.isEmpty(mCVIinputBPGenPhy.getText())) {
            String inputString = mCVIinputBPGenPhy.getText().toString();
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
            }

        }
        if (TextUtils.isEmpty(mCVIinputRightEyeSysExa.getText())) {
            mShowToast("Please enter Right eye - Refraction ");
            return false;
        }
        if (TextUtils.isEmpty(mCVIinputLeftEyeSysExa.getText())) {
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
        if (view.getId() == R.id.cVIlyGenPhysicalEx) {
            if (mClickOne) {
                mShowHideLayouts(0);
                mClickOne = false;
            } else {
                mShowHideLayoutsAll();
                mClickOne = true;
            }
        }
        if (view.getId() == R.id.cVIlySysExa) {
            if (mClickTwo) {
                mShowHideLayouts(1);
                mClickTwo = false;
            } else {
                mShowHideLayoutsAll();
                mClickTwo = true;
            }
        }
        if (view.getId() == R.id.cVIlyMandatoryInvest) {
            if (mClickThree) {
                mShowHideLayouts(2);
                mClickThree = false;
            } else {
                mShowHideLayoutsAll();
                mClickThree = true;
            }
        }
        if (view.getId() == R.id.cVIlyDiagnosis) {
            if (mClickFour) {
                mShowHideLayouts(3);
                mClickFour = false;
            } else {
                mShowHideLayoutsAll();
                mClickFour = true;
            }
        }
        if (view.getId() == R.id.cVIlyPrescription) {
            if (mClickFive) {
                mShowHideLayouts(4);
                mClickFive = false;
            } else {
                mShowHideLayoutsAll();
                mClickFive = true;
            }
        }
        if (view.getId() == R.id.cVIlySystomComplainExa) {
            if (mClickSix) {
                mShowHideLayouts(5);
                mClickSix = false;
            } else {
                mShowHideLayoutsAll();
                mClickSix = true;
            }
        }
        if (view.getId() == R.id.cVIsubmitPatientInput) {
            if (validateDataToPost()) {
                postDataForCategories();
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
            mClickSix= true;
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

        } else if (clicked == 1) {
            mClickOne = true;
            mClickTwo = false;
            mClickThree = true;
            mClickFour = true;
            mClickFive = true;
            mClickSix= true;
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
        } else if (clicked == 2) {
            mClickOne = true;
            mClickTwo = true;
            mClickThree = false;
            mClickFour = true;
            mClickFive = true;
            mClickSix= true;
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
        } else if (clicked == 3) {
            mClickOne = true;
            mClickTwo = true;
            mClickThree = true;
            mClickFour = false;
            mClickFive = true;
            mClickSix= true;
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
        } else if (clicked == 4) {
            mClickOne = true;
            mClickTwo = true;
            mClickThree = true;
            mClickFour = true;
            mClickFive = false;
            mClickSix= true;
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
        } else if (clicked == 5) {
            mClickOne = true;
            mClickTwo = true;
            mClickThree = true;
            mClickFour = true;
            mClickFive = true;
            mClickSix= false;
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

    private void mShowHideLayoutsAll() {
        mClickOne = true;
        mClickTwo = true;
        mClickThree = true;
        mClickFour = true;
        mClickFive = true;
        mClickSix= true;
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

    public void postDataForCategories() {
        try {
            if (isNetworkAvailable()) {
                createProgressBar(R.id.cVIrelMain);
                APIInterface apiInterface = ApiClient.getClientAuthenticationWithAuth(preferences.getString(SharedParams.AUTH_TOKEN,"")).create(APIInterface.class);
                PostDataForCategoryVIRequest request = new PostDataForCategoryVIRequest();
                request.setCreatedBy(doctorName);
                request.setCreatedDate(getDateToSend());
                request.setPatientId(memberData.getMemberid());
                request.setDistrict(preferences.getString(SharedParams.DISTRICT, null));
                request.setFacility(sharedPreferences.getString(SharedParams.FACTYPE, "") + "/" + sharedPreferences.getString(SharedParams.FACILITY, ""));
                request.setCategory("5");
                request.setUserId(preferences.getString(SharedParams.SUB, null));


                // Step 1
                request.setWeight(mCVIinputWeightGenPhy.getText().toString());
                request.setHeight(mCVIinputHeightGenPhy.getText().toString());
                request.setBmi(mCVIinputBMIGenPhy.getText().toString() + "");
                request.setPulseRate(mCVIinputPulseGenPhy.getText().toString());
                request.setBloodPressure(mCVIinputBPGenPhy.getText().toString());
                request.setRespiratoryRate(mCVIinputRRPMGenPhy.getText().toString());

//                // Step 2
                request.setPallor(mCVIdropPollerSysComp.getSelectedItem() + "");
                request.setJaundiceCyanosis(mCVIdropJaundiceSysComp.getSelectedItem() + "");
                request.setClubbing(mCVIdropClubbingSysComp.getSelectedItem() + "");
                request.setLymphocytes(mCVIdropLymphadenopathySysComp.getSelectedItem() + "");
                request.setPedalOedema(mCVIdropPedaloedemaSysExa.getSelectedItem() + "");

                // Step 3
                request.setChest(mCVIdropChestSysExa.getSelectedItem() + "");
                request.setCvs(mCVIdropCVSSysExa.getSelectedItem() + "");
                request.setPerAbdomen(mCVIdropPAbdomenSysExa.getSelectedItem() + "");
                request.setCns(mCVIdropCNSSysExa.getSelectedItem() + "");
                request.setHearing(mCVIdropHearingSysExa.getSelectedItem() + "");
                request.setRightEye(mCVIinputRightEyeSysExa.getText().toString() + "");
                request.setLeftEye(mCVIinputLeftEyeSysExa.getText().toString() + "");
                request.setColorVision(mCVIdropColourBlindnesSysExa.getSelectedItem() + "");
                request.setDentalExaminationAbnormality(mCVIdropDentalSysExa.getSelectedItem() + "");
                request.setGenitalExamination(mCVIdropGenitalSysExa.getSelectedItem() + "");
                request.setBreastExamination(mCVIinputBreastSysExa.getText().toString() + "");
                request.setJointExamination(mCVIdropJointExSysExa.getSelectedItem() + "");
                request.setOralExamination(mCVIdropOralExmSysExa.getSelectedItem() + "");

                // step 4
                request.sethB(mCVIinputHBMandatoryInvest.getText().toString());
                request.setTlc(mCVIinputTLCMandatoryInvest.getText().toString());
                request.setNeutrophils(mCVIinputDLCNeutrophilsMandatoryInvest.getText().toString());
                request.setLymphocytes(mCVIinputLymphocytesMandatoryInvest.getText().toString());
                request.setMonocytes(mCVIinputDLCMonocytesMandatoryInvest.getText().toString());
                request.setEosinophils(mCVIinputDLCEosinophilsMandatoryInvest.getText().toString());
                request.setBasophils(mCVIinputDLCBasophilsMandatoryInvest.getText().toString());
                request.setPackedCellVolume(mCVIinputPackedCellMandatoryInvest.getText().toString());
                request.setMeanCorpusVolume(mCVIinputCorpuscularMandatoryInvest.getText().toString());
                request.setMeanCorpusHemoglobin(mCVIinputCorpuscularHBMandatoryInvest.getText().toString());
                request.setMeanCorpusHemoglobinConcentration(mCVIinputHBConcentrationMandatoryInvest.getText().toString());
                request.setPlatletCount(mCVIinputPlateletMandatoryInvest.getText().toString());
                request.setRdwCv(mCVIinputRDWMandatoryInvest.getText().toString());
                request.setRdwSd(mCVIinputRDWSDMandatoryInvest.getText().toString());
                request.setRbcCount(mCVIinputRbcCountMandatoryInvest.getText().toString());
                request.setRbs(mCVIinputRBSMandatoryInvest.getText().toString());
                request.setSerumCholestrol(mCVIinputCholesterolMandatoryInvest.getText().toString());
                request.setBloodUrea(mCVIinputBloodUreaMandatoryInvest.getText().toString());
                request.setSerumCreatinine(mCVIinputCreatinineMandatoryInvest.getText().toString());
                request.setTsh(mCVIinputTSHMandatoryInvest.getText().toString());
                request.setPsaForMales(mCVIinputPSAMandatoryInvest.getText().toString());
                request.setPapSmear(mCVIinputVIAPAPMandatoryInvest.getText().toString());
                request.setUrineRoutineExamination(mCVIinputUrineMandatoryInvest.getText().toString());
                request.setRelevantInvestigation(mCVIinputAdvisedMandatoryInvest.getText().toString());


                //step 5
                request.setDiagnosed(selectedDiagnosis);
                if (mCVIchkDAlreadyKnown.isChecked()) {
                    request.setAlreadyKnown("yes");
                } else {
                    request.setAlreadyKnown("no");
                }
                //step 6
                request.setPrescription(mCVIinputPrescription.getText().toString());
                Log.e(" request ", "" + request.toString());
                Call<SubmitPatientData> call = apiInterface.submitDataForSurveyCatVI(request);
                call.enqueue(new Callback<SubmitPatientData>() {
                    @Override
                    public void onResponse(Call<SubmitPatientData> call, Response<SubmitPatientData> response) {
                        try {
                            if (response.isSuccessful()) {
                                refrenceGenratedPopup(CategoryVIPatientEntryActivity.this,response.body().getRefernceId());

//                                mShowToast("Submitted Successfully with reference id "+response.body().getRefernceId());
                            } else {
                                mHandleApiErrorCode(response.code(),response.errorBody().string(), CategoryVIPatientEntryActivity.this);
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
