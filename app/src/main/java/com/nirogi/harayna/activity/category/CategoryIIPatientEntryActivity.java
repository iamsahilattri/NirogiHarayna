package com.nirogi.harayna.activity.category;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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
import com.nirogi.harayna.model.request.PostDataForCategoryIIRequest;
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

public class CategoryIIPatientEntryActivity extends BaseActivity implements View.OnClickListener {

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
    private AppCompatImageView iconHistory;
    private LinearLayout lyHistoryExValue;


    private boolean mClickOne = true, mClickTwo = true, mClickThree = true, mClickFour = true, mClickFive = true, mClickSix = true;

    PatientListModelResponse memberData;
    private MultiSpinner multiSpinner;
    private String selectedDiagnosis="";
    private String doctorName;
    private SharedPreferences sharedPreferences;
    private AppCompatSpinner mCIIdropModeDelHistory;
    private AppCompatSpinner mCIIdropCryBirthHistory;
    private AppCompatSpinner mCIIdropPhysIllHistory;
    private AppCompatSpinner mCIIdropSignHistory;
    private AppCompatSpinner mCIIdropImmunizationHistory;
    private AppCompatSpinner mCIIdropFeedingHistory;
    private AppCompatSpinner mCIIdropTbPatientHistory;
    private AppCompatSpinner mCIIdropDewormingPatientHistory;
    private AppCompatSpinner mCIIdropSittingHistory;
    private AppCompatSpinner mCIIdropSandHistory;
    private AppCompatSpinner mCIIdropStandigingWithHistory;
    private AppCompatSpinner mCIIdropPincerGraspHistory;
    private AppCompatSpinner mCIIdropStrangerAnxietyHistory;
    private AppCompatSpinner mCIIdropSpeachHistory;
    private AppCompatEditText mCIIinputWeightGenPhy;
    private AppCompatEditText mCIIinputHeightGenPhy;
    private AppCompatEditText mCIIinputHeadCRF;
    private AppCompatEditText mCIIinputMidArm;
    private AppCompatSpinner mCIIdropPallorGenPhy;
    private AppCompatSpinner mCIIdropJaundiceGenPhy;
    private AppCompatSpinner mCIIdropCyanosisGenPhy;
    private AppCompatEditText mCIIinputHRGenPhy,cIIViewDiagnosis;
    private AppCompatEditText mCIIinputRRGenPhy;
    private AppCompatSpinner mCIIdropPedalOeGenPhy;
    private AppCompatEditText mCIIinputOxySatGenPhy;
    private AppCompatSpinner mCIIdropCongAnGenPhy;
    private AppCompatSpinner mCIIdropAnterFontGenPhy;
    private AppCompatSpinner mCIIdropHairTexGenPhy;
    private AppCompatSpinner mCIIdropSkinTexGenPhy;
    private AppCompatSpinner mCIIdropSkinTurGenPhy;
    private AppCompatSpinner mCIIdropSkinLeaionsGenPhy;
    private AppCompatSpinner mCIIdropChestSysExa;
    private AppCompatSpinner mCIIdropCVSSysExa;
    private AppCompatSpinner mCIIdropPAbdomenSysExa;
    private AppCompatSpinner mCIIdropCNSSysExa;
    private AppCompatSpinner mCIIdropHearingSysExa;
    private AppCompatEditText mInputRightEyeSysExa;
    private AppCompatEditText mInputLeftEyeSysExa;
    private AppCompatSpinner mCIIdropColorBlindSSysExa;
    private AppCompatSpinner mCIIdropDentalExSysExa;
    private AppCompatEditText mCIIinputHBMandatoryInvest;
    private AppCompatEditText mCIIinputTLCMandatoryInvest;

    private MultiSpinner mCIIdropDiagnosis;
    private AppCompatCheckBox mCIIchkDAlreadyKnown;
    private AppCompatEditText mCIIinputPrescription;
    private AppCompatSpinner mCIIdropWalkSupportHistory;
    private AppCompatSpinner mCIIdropSpeakHistory;
    private AppCompatSpinner mCIIdropFDirectHistory;
    private AppCompatSpinner mCIIdropWalkUPDownHistory;
    private AppCompatSpinner mCIIdropJumpHistory;
    private AppCompatSpinner mCIIdropScrabblingHistory;
    private AppCompatSpinner mCIIdropSpeak50History;

    private  ReferredSurveyDataResponse intentRecorderRefData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = NIROGI.getInstance().getPreferences();
        setContentView(R.layout.activity_patient_input_cat_ii);
        initView();
        mSetValuesToViews();
        initDataToView();
        mSetValidationListeners();
    }

    @Override
    public void onBackPressed() {
        performBackPress(CategoryIIPatientEntryActivity.this);
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
                memberData = (PatientListModelResponse) getIntent().getSerializableExtra(IntentParams.MEMBER_DATA);
                if (memberData != null) {
                    mSetBackToolbar(CategoryIIPatientEntryActivity.this,"Patient Details", true, "Category II (07-59 Months)");
                    mTxtPatientPPPID.setText(memberData.getPppid() + "");
                    mTxtPatientName.setText(memberData.getFirstname() + " " + memberData.getLastname());
                    mTxtPatientGenderAge.setText(memberData.getGender() + "");
                    mTxtPatientMobile.setText(memberData.getMobileno() + "");
                    mTxtPatientAddress.setText(memberData.getAddress() + "");
                    mTxtPatientDistrict.setText(memberData.getDistrict() + "");
                }
            }
        } catch (Exception e) {
            Log.e(" Exception memberData", "" + e.getMessage());
        }
    }

    private void initView() {
        cIIViewDiagnosis= findViewById(R.id.cIIViewDiagnosis);
        mTxtPatientPPPID = findViewById(R.id.cIItxtPatientPPPID);
        mTxtPatientName = findViewById(R.id.cIItxtPatientName);
        mTxtPatientGenderAge = findViewById(R.id.cIItxtPatientGenderAge);
        mTxtPatientMobile = findViewById(R.id.cIItxtPatientMobile);
        mTxtPatientAddress = findViewById(R.id.cIItxtPatientAddress);
        mTxtPatientDistrict = findViewById(R.id.cIItxtPatientDistrict);
        mTxtFacilityName = findViewById(R.id.cIItxtFacilityName);
        mTxtFacilityIncharge = findViewById(R.id.cIItxtFacilityIncharge);
        mTxtDate = findViewById(R.id.cIItxtDate);
        LinearLayout mLyGenPhysicalEx = findViewById(R.id.cIIlyGenPhysicalEx);
        mLyGenPhysicalEx.setOnClickListener(this);
        mIconGenPhy = findViewById(R.id.cIIiconGenPhy);
        mLyGenPhysicalxValue = findViewById(R.id.cIIlyGenPhysicalxValue);

        LinearLayout mLySysExa = findViewById(R.id.cIIlySysExa);
        mLySysExa.setOnClickListener(this);
        mIconSysExa = findViewById(R.id.cIIiconSysExa);
        mLySysExaValue = findViewById(R.id.cIIlySysExaValue);

        LinearLayout mLyMandatoryInvest = findViewById(R.id.cIIlyMandatoryInvest);
        mLyMandatoryInvest.setOnClickListener(this);
        mIconMandatoryInvest = findViewById(R.id.cIIiconMandatoryInvest);
        mLyMandatoryInvestValue = findViewById(R.id.cIIlyMandatoryInvestValue);

        LinearLayout mLyDiagnosis = findViewById(R.id.cIIlyDiagnosis);
        mLyDiagnosis.setOnClickListener(this);
        mIconDiagnosis = findViewById(R.id.cIIiconDiagnosis);
        mLyDiagnosisValue = findViewById(R.id.cIIlyDiagnosisValue);

        LinearLayout mLyPrescription = findViewById(R.id.cIIlyPrescription);
        mLyPrescription.setOnClickListener(this);
        mIconPrescription = findViewById(R.id.cIIiconPrescription);
        mLyPrescriptionValue = findViewById(R.id.cIIlyPrescriptionValue);

        LinearLayout lyHistoryEx = findViewById(R.id.cIIlyHistoryEx);
        lyHistoryEx.setOnClickListener(this);
        iconHistory = findViewById(R.id.cIIiconHistory);
        lyHistoryExValue = findViewById(R.id.cIIlyHistoryExValue);

        mCIIdropModeDelHistory = findViewById(R.id.cIIdropModeDelHistory);
        mCIIdropCryBirthHistory = findViewById(R.id.cIIdropCryBirthHistory);
        mCIIdropPhysIllHistory = findViewById(R.id.cIIdropPhysIllHistory);
        mCIIdropSignHistory = findViewById(R.id.cIIdropSignHistory);
        mCIIdropImmunizationHistory = findViewById(R.id.cIIdropImmunizationHistory);
        mCIIdropFeedingHistory = findViewById(R.id.cIIdropFeedingHistory);
        mCIIdropTbPatientHistory = findViewById(R.id.cIIdropTbPatientHistory);
        mCIIdropDewormingPatientHistory = findViewById(R.id.cIIdropDewormingPatientHistory);
        mCIIdropSittingHistory = findViewById(R.id.cIIdropSittingHistory);
        mCIIdropSandHistory = findViewById(R.id.cIIdropSandHistory);
        mCIIdropStandigingWithHistory = findViewById(R.id.cIIdropStandigingWithHistory);
        mCIIdropPincerGraspHistory = findViewById(R.id.cIIdropPincerGraspHistory);
        mCIIdropStrangerAnxietyHistory = findViewById(R.id.cIIdropStrangerAnxietyHistory);
        mCIIdropSpeachHistory = findViewById(R.id.cIIdropSpeachHistory);
        mCIIinputWeightGenPhy = findViewById(R.id.cIIinputWeightGenPhy);
        mCIIinputHeightGenPhy = findViewById(R.id.cIIinputHeightGenPhy);
        mCIIinputHeadCRF = findViewById(R.id.cIIinputHeadCRF);
        mCIIinputMidArm = findViewById(R.id.cIIinputMidArm);
        mCIIdropPallorGenPhy = findViewById(R.id.cIIdropPallorGenPhy);
        mCIIdropJaundiceGenPhy = findViewById(R.id.cIIdropJaundiceGenPhy);
        mCIIdropCyanosisGenPhy = findViewById(R.id.cIIdropCyanosisGenPhy);
        mCIIinputHRGenPhy = findViewById(R.id.cIIinputHRGenPhy);
        mCIIinputRRGenPhy = findViewById(R.id.cIIinputRRGenPhy);
        mCIIdropPedalOeGenPhy = findViewById(R.id.cIIdropPedalOeGenPhy);
        mCIIinputOxySatGenPhy = findViewById(R.id.cIIinputOxySatGenPhy);
        mCIIdropCongAnGenPhy = findViewById(R.id.cIIdropCongAnGenPhy);
        mCIIdropAnterFontGenPhy = findViewById(R.id.cIIdropAnterFontGenPhy);
        mCIIdropHairTexGenPhy = findViewById(R.id.cIIdropHairTexGenPhy);
        mCIIdropSkinTexGenPhy = findViewById(R.id.cIIdropSkinTexGenPhy);
        mCIIdropSkinTurGenPhy = findViewById(R.id.cIIdropSkinTurGenPhy);
        mCIIdropSkinLeaionsGenPhy = findViewById(R.id.cIIdropSkinLeaionsGenPhy);
        mCIIdropChestSysExa = findViewById(R.id.cIIdropChestSysExa);
        mCIIdropCVSSysExa = findViewById(R.id.cIIdropCVSSysExa);
        mCIIdropPAbdomenSysExa = findViewById(R.id.cIIdropPAbdomenSysExa);
        mCIIdropCNSSysExa = findViewById(R.id.cIIdropCNSSysExa);
        mCIIdropHearingSysExa = findViewById(R.id.cIIdropHearingSysExa);
        mInputRightEyeSysExa =  findViewById(R.id.cIIinputRightEyeSysExa);
        mInputLeftEyeSysExa =  findViewById(R.id.cIIinputLeftEyeSysExa);
        mCIIdropColorBlindSSysExa = findViewById(R.id.cIIdropColorBlindSSysExa);
        mCIIdropDentalExSysExa = findViewById(R.id.cIIdropDentalExSysExa);
        mCIIinputHBMandatoryInvest = findViewById(R.id.cIIinputHBMandatoryInvest);
        mCIIinputTLCMandatoryInvest = findViewById(R.id.cIIinputTLCMandatoryInvest);
        multiSpinner = findViewById(R.id.cIIdropDiagnosis);
        mSetSpinnerData(Arrays.asList(getResources().getStringArray(R.array.arr_diagnosis_cat_1)));

        mCIIchkDAlreadyKnown = findViewById(R.id.cIIchkDAlreadyKnown);
        mCIIinputPrescription = findViewById(R.id.cIIinputPrescription);

        LinearLayout cIIsubmitPatientInput = findViewById(R.id.cIIsubmitPatientInput);
        cIIsubmitPatientInput.setOnClickListener(this);
        mCIIdropWalkSupportHistory = findViewById(R.id.cIIdropWalkSupportHistory);
        mCIIdropSpeakHistory = findViewById(R.id.cIIdropSpeakHistory);
        mCIIdropFDirectHistory = findViewById(R.id.cIIdropFDirectHistory);
        mCIIdropWalkUPDownHistory = findViewById(R.id.cIIdropWalkUPDownHistory);
        mCIIdropJumpHistory = findViewById(R.id.cIIdropJumpHistory);
        mCIIdropScrabblingHistory = findViewById(R.id.cIIdropScrabblingHistory);
        mCIIdropSpeak50History = findViewById(R.id.cIIdropSpeak50History);
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
                if(dataModel.getTitle().equals(IntentParams.TITLE_HISTORY))
                {
                    if(dataEntity.getDeliverytype()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getDeliverytype(),mCIIdropModeDelHistory,R.array.arr_mode_del);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropModeDelHistory);
                    }
                    if(dataEntity.getCryafterbirth()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getCryafterbirth(),mCIIdropCryBirthHistory,R.array.arr_cry);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropCryBirthHistory);
                    }
                    if(dataEntity.getHistroyadmillness()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getHistroyadmillness(),mCIIdropPhysIllHistory,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropPhysIllHistory);
                    }
                    if(dataEntity.getFamilyhistoryifyes()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getFamilyhistoryifyes(),mCIIdropSignHistory,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropSignHistory);
                    }
                    if(dataEntity.getImmunstatus()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getImmunstatus(),mCIIdropImmunizationHistory,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropImmunizationHistory);
                    }
                    if(dataEntity.getHistoryfeeding()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getHistoryfeeding(),mCIIdropFeedingHistory,R.array.arr_feeding);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropFeedingHistory);
                    }

                    if(dataEntity.getContactwithtb()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getContactwithtb(),mCIIdropTbPatientHistory,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropTbPatientHistory);
                    }

                    if(dataEntity.getSittingWithoutSupport()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getSittingWithoutSupport(),mCIIdropSittingHistory,R.array.arr_y_n);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropSittingHistory);
                    }

                    if(dataEntity.getStandingWithoutSupport()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getStandingWithoutSupport(),mCIIdropSandHistory,R.array.arr_y_n);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropSandHistory);
                    }

                    if(dataEntity.getStandingWithSupport()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getStandingWithSupport(),mCIIdropStandigingWithHistory,R.array.arr_y_n);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropStandigingWithHistory);
                    }


                    if(dataEntity.getPincerGrasp()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getSittingWithoutSupport(),mCIIdropPincerGraspHistory,R.array.arr_y_n);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropPincerGraspHistory);
                    }

                    if(dataEntity.getStrangerAnxiety()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getStrangerAnxiety(),mCIIdropStrangerAnxietyHistory,R.array.arr_y_n);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropStrangerAnxietyHistory);
                    }

                    if(dataEntity.getBisyllableSpeech()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getBisyllableSpeech(),mCIIdropSpeachHistory,R.array.arr_y_n);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropSpeachHistory);
                    }

                    if(dataEntity.getWalkingWithoutSupport()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getWalkingWithoutSupport(),mCIIdropWalkSupportHistory,R.array.arr_y_n);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropWalkSupportHistory);
                    }

                    if(dataEntity.getSpeakFiveToTenWords()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getSpeakFiveToTenWords(),mCIIdropSpeakHistory,R.array.arr_y_n);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropSpeakHistory);
                    }

                    if(dataEntity.getFollowSimpleDirection()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getFollowSimpleDirection(),mCIIdropFDirectHistory,R.array.arr_y_n);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropFDirectHistory);
                    }


                    if(dataEntity.getWalkStairsWithSupport()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getWalkStairsWithSupport(),mCIIdropWalkUPDownHistory,R.array.arr_y_n);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropWalkUPDownHistory);
                    }


                    if(dataEntity.getJumpWithBothFeet()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getJumpWithBothFeet(),mCIIdropJumpHistory,R.array.arr_y_n);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropFDirectHistory);
                    }


                    if(dataEntity.getScrabbling()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getScrabbling(),mCIIdropScrabblingHistory,R.array.arr_y_n);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropScrabblingHistory);
                    }


                    if(dataEntity.getSpeakAtleastFiftyWords()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getSpeakAtleastFiftyWords(),mCIIdropSpeak50History,R.array.arr_y_n);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropSpeak50History);
                    }






                }

                if(dataModel.getTitle().equals(IntentParams.TITLE_GEN_EXAM))
                {
                    if(dataEntity.getWeight()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getWeight(),mCIIinputWeightGenPhy);
                    }else {
                        mSetServerValuesToEditTextELSE(mCIIinputWeightGenPhy);
                    }
                    if(dataEntity.getHeight()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getHeight(),mCIIinputHeightGenPhy);
                    }else {
                        mSetServerValuesToEditTextELSE(mCIIinputHeightGenPhy);
                    }
                    if(dataEntity.getHeadCircum()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getHeadCircum(),mCIIinputHeadCRF);
                    }else {
                        mSetServerValuesToEditTextELSE(mCIIinputHeadCRF);
                    }
                    if(dataEntity.getMidArmCircum()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getMidArmCircum(),mCIIinputMidArm);
                    }else {
                        mSetServerValuesToEditTextELSE(mCIIinputMidArm);
                    }
                    if(dataEntity.getPallor()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getPallor(),mCIIdropPallorGenPhy,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropPallorGenPhy);
                    }

                    if(dataEntity.getJaundice()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getJaundice(),mCIIdropJaundiceGenPhy,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropJaundiceGenPhy);
                    }
                    if(dataEntity.getCyanosis()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getCyanosis(),mCIIdropCyanosisGenPhy,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropCyanosisGenPhy);
                    }
                    if(dataEntity.getHeartRate()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getHeartRate(),mCIIinputHRGenPhy);
                    }else {
                        mSetServerValuesToEditTextELSE(mCIIinputHRGenPhy);
                    }


                    if(dataEntity.getRespiratoryRate()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getRespiratoryRate(),mCIIinputRRGenPhy);
                    }else {
                        mSetServerValuesToEditTextELSE(mCIIinputRRGenPhy);
                    }


                    if(dataEntity.getPedalOedema()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getPedalOedema(),mCIIdropPedalOeGenPhy,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropPedalOeGenPhy);
                    }



                    if(dataEntity.getOxygenSaturation()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getOxygenSaturation(),mCIIinputOxySatGenPhy);
                    }else {
                        mSetServerValuesToEditTextELSE(mCIIinputOxySatGenPhy);
                    }

                    if(dataEntity.getCongenitalAnomalies()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getCongenitalAnomalies(),mCIIdropCongAnGenPhy,R.array.arr_congestion_an);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropCongAnGenPhy);
                    }
                    if(dataEntity.getAnteriorFontanelle()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getAnteriorFontanelle(),mCIIdropAnterFontGenPhy,R.array.arr_anterior);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropAnterFontGenPhy);
                    }

                    if(dataEntity.getHairTexture()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getHairTexture(),mCIIdropHairTexGenPhy,R.array.arr_hair_tex);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropHairTexGenPhy);
                    }
                    if(dataEntity.getSkinTexture()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getSkinTexture(),mCIIdropSkinTexGenPhy,R.array.arr_skin);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropSkinTexGenPhy);
                    }

                    if(dataEntity.getSkinLesions()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getSkinLesions(),mCIIdropSkinLeaionsGenPhy,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropSkinLeaionsGenPhy);
                    }

                    if(dataEntity.getSkinTurgor()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getSkinTurgor(),mCIIdropSkinTurGenPhy,R.array.arr_hearing);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropSkinTurGenPhy);
                    }



                }
                if(dataModel.getTitle().equals(IntentParams.TITLE_MILESTONE))
                {

                }
                if(dataModel.getTitle().equals(IntentParams.TITLE_SYS_EXAM))
                {
                    if(dataEntity.getChest()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getChest(),mCIIdropChestSysExa,R.array.arr_chest);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropChestSysExa);
                    }
                    if(dataEntity.getCvs()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getCvs(),mCIIdropCVSSysExa,R.array.arr_cvs);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropCVSSysExa);
                    }
                    if(dataEntity.getPerAbdomen()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getPerAbdomen(),mCIIdropPAbdomenSysExa,R.array.arr_per_abdomen);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropPAbdomenSysExa);
                    }

                    if(dataEntity.getCns()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getCns(),mCIIdropCNSSysExa,R.array.arr_cns);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropPAbdomenSysExa);
                    }

                    if(dataEntity.getHearing()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getHearing(),mCIIdropHearingSysExa,R.array.arr_hearing);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropHearingSysExa);
                    }

                    if(dataEntity.getLeftEye()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getLeftEye(),mInputLeftEyeSysExa);
                    }else {
                        mSetServerValuesToEditTextELSE(mInputLeftEyeSysExa);

                    }

                    if(dataEntity.getRightEye()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getRightEye(),mInputRightEyeSysExa);
                    }else {
                        mSetServerValuesToEditTextELSE(mInputRightEyeSysExa);

                    }

                    if(dataEntity.getColorVision()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getColorVision(),mCIIdropColorBlindSSysExa,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropColorBlindSSysExa);
                    }


                    if(dataEntity.getDentalExam()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getDentalExam(),mCIIdropDentalExSysExa,R.array.arr_dental_ex);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIIdropDentalExSysExa);
                    }

                }
                if(dataModel.getTitle().equals(IntentParams.TITLE_MAN_INVEST))
                {

                }
                if(dataModel.getTitle().equals(IntentParams.TITLE_DIAGNOSIS))
                {
                    if(dataEntity.getAlreadyKnown()!=null)
                    {
                        mCIIchkDAlreadyKnown.setChecked(!dataEntity.getAlreadyKnown().equals("No"));
                    }
                    mCIIchkDAlreadyKnown.setEnabled(false);
                    if(dataEntity.getPrescription()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getPrescription(),mCIIinputPrescription);
                    }

                    if(dataEntity.getDiagnosed()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getDiagnosed(),cIIViewDiagnosis);
                        cIIViewDiagnosis.setVisibility(View.VISIBLE);
                        multiSpinner.setVisibility(View.GONE);
                        multiSpinner.setEnabled(false);
                    }else {
                        multiSpinner.setVisibility(View.GONE);
                        cIIViewDiagnosis.setVisibility(View.GONE);
                        multiSpinner.setEnabled(false);
                    }

                }
            }


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
        if (view.getId() == R.id.cIIlyGenPhysicalEx) {
            if (mClickOne) {
                mShowHideLayouts(0);
                mClickOne = false;
            } else {
                mShowHideLayoutsAll();
                mClickOne = true;
            }
        }
        if (view.getId() == R.id.cIIlySysExa) {
            if (mClickTwo) {
                mShowHideLayouts(1);
                mClickTwo = false;
            } else {
                mShowHideLayoutsAll();
                mClickTwo = true;
            }
        }
        if (view.getId() == R.id.cIIlyMandatoryInvest) {
            if (mClickThree) {
                mShowHideLayouts(2);
                mClickThree = false;
            } else {
                mShowHideLayoutsAll();
                mClickThree = true;
            }
        }
        if (view.getId() == R.id.cIIlyDiagnosis) {
            if (mClickFour) {
                mShowHideLayouts(3);
                mClickFour = false;
            } else {
                mShowHideLayoutsAll();
                mClickFour = true;
            }
        }
        if (view.getId() == R.id.cIIlyPrescription) {
            if (mClickFive) {
                mShowHideLayouts(4);
                mClickFive = false;
            } else {
                mShowHideLayoutsAll();
                mClickFive = true;
            }
        }
        if (view.getId() == R.id.cIIlyHistoryEx) {
            if (mClickSix) {
                mShowHideLayouts(5);
                mClickSix = false;
            } else {
                mShowHideLayoutsAll();
                mClickSix = true;
            }
        }
        if (view.getId() == R.id.cIIsubmitPatientInput) {
            if(getIntent().getSerializableExtra(IntentParams.MEMBER_TYPE).equals("1"))
            {
                if (validateDataToPost()) {
                    postDataForCategories();
                }
            }else {
                mShowToast("Update Mandatory Fields !");
            }
        }
    }


    private void mSetValidationListeners()
    {
        mCIIinputWeightGenPhy.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() > 0)
                {
                    String inputString=mCIIinputWeightGenPhy.getText().toString();

                    int input=Integer.parseInt(inputString);
                    if(!(input >= 5 && input <= 120))
                    {
                        mCIIinputWeightGenPhy.setError("*should be between 5-120");
                    }
                }
            }
        });
        mCIIinputHeightGenPhy.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() > 0)
                {
                    String inputString=mCIIinputHeightGenPhy.getText().toString();
                    int input=Integer.parseInt(inputString);
                    if(!(input >= 50 && input <= 130))
                    {
                        mCIIinputHeightGenPhy.setError("*should be between 50-130");
                    }
                }
            }
        });
        mCIIinputHeadCRF.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() != 0)
                {
                    String inputString=mCIIinputHeadCRF.getText().toString();
                    int input=Integer.parseInt(inputString);
                    if(!(input >= 30 && input <= 60))
                    {
                        mCIIinputHeadCRF.setError("*should be between 30-60");
                    }
                }
            }
        });
        mCIIinputMidArm.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() != 0)
                {
                    String inputString=mCIIinputMidArm.getText().toString();
                    int input=Integer.parseInt(inputString);
                    if(!(input >= 5 && input <= 20))
                    {
                        mCIIinputMidArm.setError("*should be between 5-20");
                    }

                }
            }
        });
        mCIIinputHRGenPhy.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() != 0)
                {
                    String inputString=mCIIinputHRGenPhy.getText().toString();
                    int input=Integer.parseInt(inputString);
                    if(!(input >= 50 && input <= 200))
                    {
                        mCIIinputHRGenPhy.setError("*should be between 50-200");
                    }
                }
            }
        });
        mCIIinputRRGenPhy.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() != 0)
                {
                    String inputString=mCIIinputRRGenPhy.getText().toString();
                    int input=Integer.parseInt(inputString);
                    if(!(input >= 20 && input <= 30))
                    {
                        mCIIinputRRGenPhy.setError("*should be between 20-30");
                    }

                }
            }
        });
        mCIIinputOxySatGenPhy.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() != 0)
                {
                    String inputString=mCIIinputOxySatGenPhy.getText().toString();
                    int input=Integer.parseInt(inputString);
                    if(!(input >= 70 && input <= 100))
                    {
                        mCIIinputOxySatGenPhy.setError("*should be between 70% -100%");
                    }

                }
            }
        });
    }

    private boolean validateDataToPost() {

        if (TextUtils.isEmpty(mCIIinputWeightGenPhy.getText())) {
            mShowToast("Please enter Weight ");
            return false;
        }
        if (!TextUtils.isEmpty(mCIIinputWeightGenPhy.getText())) {
            String inputString = mCIIinputWeightGenPhy.getText().toString();
            int input = Integer.parseInt(inputString);
            if (!(input >= 5 && input <= 120)) {
                mShowToast("Weight should be between 5-120");
                return false;
            }
        }
        if (TextUtils.isEmpty(mCIIinputHeightGenPhy.getText())) {
            mShowToast("Please enter Height ");
            return false;
        }
        if (!TextUtils.isEmpty(mCIIinputHeightGenPhy.getText())) {
            String inputString = mCIIinputHeightGenPhy.getText().toString();
            int input = Integer.parseInt(inputString);
            if (!(input >= 50 && input <= 130)) {
                mShowToast("Height should be between 90-215");
                return false;
            }
        }
        if (TextUtils.isEmpty(mCIIinputHeadCRF.getText())) {
            mShowToast("Please enter Head circumference (in cm) ");
            return false;
        }
        if (!TextUtils.isEmpty(mCIIinputHeadCRF.getText())) {
            String inputString = mCIIinputHeadCRF.getText().toString();
            int input = Integer.parseInt(inputString);
            if (!(input >= 30 && input <= 60)) {
                mShowToast("Head circumference (in cm) should be between 30-60");
                return false;
            }
        }

        if (TextUtils.isEmpty(mCIIinputMidArm.getText())) {
            mShowToast("Please enter Mid-arm circumference (in cm) ");
            return false;
        }
        if (!TextUtils.isEmpty(mCIIinputMidArm.getText())) {
            String inputString = mCIIinputMidArm.getText().toString();
            int input = Integer.parseInt(inputString);
            if (!(input >= 5 && input <= 20)) {
                mShowToast("Mid-arm circumference (in cm) should be between 5-20");
                return false;
            }
        }


        if (TextUtils.isEmpty(mCIIinputHRGenPhy.getText())) {
            mShowToast("Please enter Heart rate (per min) ");
            return false;
        }
        if (!TextUtils.isEmpty(mCIIinputHRGenPhy.getText())) {
            String inputString = mCIIinputHRGenPhy.getText().toString();
            int input = Integer.parseInt(inputString);
            if (!(input >= 50 && input <= 200)) {
                mShowToast("Heart rate (per min) should be between 50-200");
                return false;
            }
        }

        if (TextUtils.isEmpty(mCIIinputRRGenPhy.getText())) {
            mShowToast("Please enter Respiratory rate (per min) ");
            return false;
        }
        if (!TextUtils.isEmpty(mCIIinputRRGenPhy.getText())) {
            String inputString = mCIIinputRRGenPhy.getText().toString();
            int input = Integer.parseInt(inputString);
            if (!(input >= 20 && input <= 30)) {
                mShowToast("Respiratory rate (per min) should be between 20-30");
                return false;
            }
        }

        if (TextUtils.isEmpty(mCIIinputOxySatGenPhy.getText())) {
            mShowToast("Please enter Oxygen Saturation(> 93 per) ");
            return false;
        }
        if (!TextUtils.isEmpty(mCIIinputOxySatGenPhy.getText())) {
            String inputString = mCIIinputOxySatGenPhy.getText().toString();
            int input = Integer.parseInt(inputString);
            if (!(input >= 70 && input <= 100)) {
                mShowToast("Oxygen Saturation(> 93 per) should be between 70%-100%");
                return false;
            }
        }

        if (TextUtils.isEmpty(mInputLeftEyeSysExa.getText())) {
            mShowToast("Please enter Left eye - Refraction ");
            return false;
        }
        if (TextUtils.isEmpty(mInputRightEyeSysExa.getText())) {
            mShowToast("Please enter Left eye - Refraction ");
            return false;
        }
        if (selectedDiagnosis.length()==0) {
            mShowToast("Please select an option for Diagnosis");
            return false;
        }
        return true;
    }

    private void mShowHideLayouts(int clicked) {
        if (clicked == 0) {
            mClickOne = false;
            mClickTwo = true;
            mClickThree = true;
            mClickFour = true;
            mClickFive = true;
            mClickSix = true;
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
        } else if (clicked == 1) {
            mClickOne = true;
            mClickTwo = false;
            mClickThree = true;
            mClickFour = true;
            mClickFive = true;
            mClickSix = true;
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
        } else if (clicked == 2) {
            mClickOne = true;
            mClickTwo = true;
            mClickThree = false;
            mClickFour = true;
            mClickFive = true;
            mClickSix = true;
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
        } else if (clicked == 3) {
            mClickOne = true;
            mClickTwo = true;
            mClickThree = true;
            mClickFour = false;
            mClickFive = true;
            mClickSix = true;
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
        } else if (clicked == 4) {
            mClickOne = true;
            mClickTwo = true;
            mClickThree = true;
            mClickFour = true;
            mClickFive = false;
            mClickSix = true;
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
        } else if (clicked == 5) {
            mClickOne = true;
            mClickTwo = true;
            mClickThree = true;
            mClickFour = true;
            mClickFive = true;
            mClickSix = false;
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

    private void mShowHideLayoutsAll() {
        mClickOne = true;
        mClickTwo = true;
        mClickThree = true;
        mClickFour = true;
        mClickFive = true;
        mClickSix = true;
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


    public void postDataForCategories() {
        try {
            if (isNetworkAvailable()) {
                createProgressBar(R.id.cIIrelMain);
                APIInterface apiInterface = ApiClient.getClientAuthenticationWithAuth(preferences.getString(SharedParams.AUTH_TOKEN, "")).create(APIInterface.class);
                PostDataForCategoryIIRequest request = new PostDataForCategoryIIRequest();
                request.setPatientId(memberData.getMemberid());
                request.setDistrict(preferences.getString(SharedParams.DISTRICT, null));
                request.setCreatedDate(getDateToSend());
                request.setCreatedBy(doctorName);
                request.setUserId(preferences.getString(SharedParams.SUB, null));
                request.setFacility(sharedPreferences.getString(SharedParams.FACTYPE, "") + "/" + sharedPreferences.getString(SharedParams.FACILITY, ""));
                request.setCategory("2");

                //step 1
                request.setDeliveryType(mCIIdropModeDelHistory.getSelectedItem() + "");
                request.setCryAfterBirth(mCIIdropCryBirthHistory.getSelectedItem() + "");
                request.setHistroyAdmIllness(mCIIdropPhysIllHistory.getSelectedItem() + "");
                request.setHistoryFamily(mCIIdropSignHistory.getSelectedItem() + "");
                request.setImmunStatus(mCIIdropImmunizationHistory.getSelectedItem() + "");
                request.setHistoryFeeding(mCIIdropFeedingHistory.getSelectedItem() + "");
                request.setContactWithTB(mCIIdropTbPatientHistory.getSelectedItem() + "");
                request.setHistoryDeworming(mCIIdropDewormingPatientHistory.getSelectedItem() + "");
                request.setSittingWithoutSupport(mCIIdropSittingHistory.getSelectedItem() + "");
                request.setStandingWithoutSupport(mCIIdropStandigingWithHistory.getSelectedItem() + "");
                request.setStandingWithSupport(mCIIdropStandigingWithHistory.getSelectedItem() + "");
                request.setPincerGrasp(mCIIdropPincerGraspHistory.getSelectedItem() + "");
                request.setStrangerAnxiety(mCIIdropStrangerAnxietyHistory.getSelectedItem() + "");
                request.setBisyllSpeech(mCIIdropSpeachHistory.getSelectedItem() + "");
                request.setWalkWithoutSupport(mCIIdropWalkSupportHistory.getSelectedItem() + "");
                request.setSpeakFiveToTenWords(mCIIdropSpeakHistory.getSelectedItem() + "");
                request.setFollowSimpleDirections(mCIIdropFDirectHistory.getSelectedItem() + "");
//                request.set(mCIIdropWalkUPDownHistory.getSelectedItem() + "");
                request.setJumpWithBothFeets(mCIIdropJumpHistory.getSelectedItem() + "");
                request.setScrabbling(mCIIdropScrabblingHistory.getSelectedItem() + "");
                request.setSpeakAleastFiftyWords(mCIIdropSpeak50History.getSelectedItem() + "");

                // Step 2
                request.setWeight(mCIIinputWeightGenPhy.getText().toString());
                request.setHeight(mCIIinputHeightGenPhy.getText().toString());
                request.setHeadCircum(mCIIinputHeadCRF.getText().toString());
                request.setMidArmCircum(mCIIinputMidArm.getText().toString());
                request.setPallor(mCIIdropPallorGenPhy.getSelectedItem() + "");
                request.setJaundice(mCIIdropJaundiceGenPhy.getSelectedItem() + "");
                request.setCyanosis(mCIIdropCyanosisGenPhy.getSelectedItem() + "");
                request.setHeartRate(mCIIinputHRGenPhy.getText().toString());
                request.setRespiratoryRate(mCIIinputRRGenPhy.getText().toString());
                request.setOxygenSatur(mCIIinputOxySatGenPhy.getText().toString());
                request.setPedalOedema(mCIIdropPedalOeGenPhy.getSelectedItem() + "");
                request.setCongenitalAnomalies(mCIIdropCongAnGenPhy.getSelectedItem() + "");
                request.setAnteriorFontanelle(mCIIdropAnterFontGenPhy.getSelectedItem() + "");
                request.setHairTexture(mCIIdropHairTexGenPhy.getSelectedItem() + "");
                request.setSkinTexture(mCIIdropSkinTexGenPhy.getSelectedItem() + "");
                request.setSkinTurgor(mCIIdropSkinTurGenPhy.getSelectedItem() + "");
                request.setSkinLesion(mCIIdropSkinLeaionsGenPhy.getSelectedItem() + "");

                // Step 3
                request.setChest(mCIIdropChestSysExa.getSelectedItem() + "");
                request.setCvs(mCIIdropCVSSysExa.getSelectedItem() + "");
                request.setPerAbdomen(mCIIdropPAbdomenSysExa.getSelectedItem() + "");
                request.setCns(mCIIdropCNSSysExa.getSelectedItem() + "");
                request.setHearing(mCIIdropHearingSysExa.getSelectedItem() + "");
                request.setRightEye(mInputRightEyeSysExa.getText().toString() + "");
                request.setLeftEye(mInputLeftEyeSysExa.getText().toString() + "");
                request.setColorVision(mCIIdropColorBlindSSysExa.getSelectedItem() + "");
                request.setDentalExamination(mCIIdropDentalExSysExa.getSelectedItem() + "");

                // step 4
                request.sethB(mCIIinputHBMandatoryInvest.getText().toString());
                request.setRelevantInvestigation(mCIIinputTLCMandatoryInvest.getText().toString());

                //step 5
                request.setDiagnosed(selectedDiagnosis);
                if (mCIIchkDAlreadyKnown.isChecked()) {
                    request.setAlreadyKnown("yes");
                } else {
                    request.setAlreadyKnown("no");
                }
                request.setPrescription(mCIIinputPrescription.getText().toString());
                Log.e(" request ", "" + request.toString());
                Call<SubmitPatientData> call = apiInterface.submitDataForSurveyCatII(request);
                call.enqueue(new Callback<SubmitPatientData>() {
                    @Override
                    public void onResponse(Call<SubmitPatientData> call, Response<SubmitPatientData> response) {
                        try {
                            if (response.isSuccessful()) {
                                refrenceGenratedPopup(CategoryIIPatientEntryActivity.this,response.body().getRefernceId());
//                                mShowToast("Submitted Successfully with reference id " + response.body().getRefernceId());
                            } else {
                                mHandleApiErrorCode(response.code(), response.errorBody().string(), CategoryIIPatientEntryActivity.this);
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
