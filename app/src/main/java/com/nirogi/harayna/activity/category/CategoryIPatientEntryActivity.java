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
import com.nirogi.harayna.model.request.PostDataForCategoryIRequest;
import com.nirogi.harayna.model.request.PostMandatoryDataRequest;
import com.nirogi.harayna.model.response.PatientListModelResponse;
import com.nirogi.harayna.model.response.ReferredSurveyDataResponse;
import com.nirogi.harayna.model.response.SubmitPatientData;
import com.nirogi.harayna.model.response.SubmitPatientMandatoryData;
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

public class CategoryIPatientEntryActivity extends BaseActivity implements View.OnClickListener {

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
    private SharedPreferences sharedPreferences;
    private AppCompatSpinner mCIdropModeDelHistory;
    private AppCompatSpinner mCIdropCryBirthHistory;
    private AppCompatSpinner mCIdropPhysIllHistory;
    private AppCompatSpinner mCIdropSignHistory;
    private AppCompatSpinner mCIdropImmunizationHistory;
    private AppCompatSpinner mCIdropFeedingHistory;
    private AppCompatSpinner mCIdropTbPatientHistory;
    private AppCompatSpinner mCIdropSocialSmileHistory;
    private AppCompatSpinner mCIdropNeckHoldingHistory;
    private AppCompatSpinner mCIdropSittingSupportHistory;
    private AppCompatEditText mCIinputWeightGenPhy,cIViewDiagnosis;
    private AppCompatEditText mCIinputHeightGenPhy;
    private AppCompatEditText mCIinputHeadCRF;
    private AppCompatSpinner mCIdropPallorGenPhy;
    private AppCompatSpinner mCIdropJaundiceGenPhy;
    private AppCompatSpinner mCIdropCyanosisGenPhy;
    private AppCompatEditText mCIinputHRGenPhy;
    private AppCompatEditText mCIinputRRGenPhy;
    private AppCompatEditText mCIinputCapRefilGenPhy;
    private AppCompatEditText mCIinputOxySatGenPhy;
    private AppCompatSpinner mCIdropCongAnGenPhy;
    private AppCompatSpinner mCIdropAnterFontGenPhy;
    private AppCompatSpinner mCIdropSkinLesGenPhy;
    private AppCompatSpinner mCIdropChestSysExa;
    private AppCompatSpinner mCIdropCVSSysExa;
    private AppCompatSpinner mCIdropPAbdomenSysExa;
    private AppCompatSpinner mCIdropCNSSysExa;
    private AppCompatSpinner mCIdropHearingSysExa;
    private AppCompatSpinner mCIdropEyeCongenitalSysExa;
    private AppCompatSpinner mCIdropColourBlindnesSysExa;
    private AppCompatEditText mCIinputHBMandatoryInvest;
    private AppCompatEditText mCIinputTLCMandatoryInvest;
    private AppCompatSpinner mCIdropDiagnosis;
    private AppCompatCheckBox mCIchkDAlreadyKnown;
    private AppCompatEditText mCIinputPrescription;
    private MultiSpinner multiSpinner;
    private String selectedDiagnosis="";
    private String doctorName;

    private  ReferredSurveyDataResponse intentRecorderRefData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_input_cat_i);
        sharedPreferences = NIROGI.getInstance().getPreferences();
        initView();
        mSetBackToolbar(CategoryIPatientEntryActivity.this,"Patient Details", true, "Category I (0-18 Months)");
        mSetValuesToViews();
        initDataToView();
        mSetValidationListeners();
    }

    private void initDataToView() {
        doctorName=sharedPreferences.getString(SharedParams.FNAME, "")+""+ sharedPreferences.getString(SharedParams.LNAME, "");
        mTxtFacilityName.setText(sharedPreferences.getString(SharedParams.FACTYPE, "")+" "+sharedPreferences.getString(SharedParams.FACILITY, ""));
        mTxtFacilityIncharge.setText("Dr . "+doctorName);
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

    private void initView() {

        cIViewDiagnosis= findViewById(R.id.cIViewDiagnosis);
        mTxtPatientPPPID = findViewById(R.id.cItxtPatientPPPID);
        mTxtPatientName = findViewById(R.id.cItxtPatientName);
        mTxtPatientGenderAge = findViewById(R.id.cItxtPatientGenderAge);
        mTxtPatientMobile = findViewById(R.id.cItxtPatientMobile);
        mTxtPatientAddress = findViewById(R.id.cItxtPatientAddress);
        mTxtPatientDistrict = findViewById(R.id.cItxtPatientDistrict);
        mTxtFacilityName = findViewById(R.id.cItxtFacilityName);
        mTxtFacilityIncharge = findViewById(R.id.cItxtFacilityIncharge);
        mTxtDate = findViewById(R.id.cItxtDate);
        LinearLayout mLyGenPhysicalEx = findViewById(R.id.cIlyGenPhysicalEx);
        mLyGenPhysicalEx.setOnClickListener(this);
        mIconGenPhy = findViewById(R.id.cIiconGenPhy);
        mLyGenPhysicalxValue = findViewById(R.id.cIlyGenPhysicalxValue);

        LinearLayout mLySysExa = findViewById(R.id.cIlySysExa);
        mLySysExa.setOnClickListener(this);
        mIconSysExa = findViewById(R.id.cIiconSysExa);
        mLySysExaValue = findViewById(R.id.cIlySysExaValue);

        LinearLayout mLyMandatoryInvest = findViewById(R.id.cIlyMandatoryInvest);
        mLyMandatoryInvest.setOnClickListener(this);
        mIconMandatoryInvest = findViewById(R.id.cIiconMandatoryInvest);
        mLyMandatoryInvestValue = findViewById(R.id.cIlyMandatoryInvestValue);

        LinearLayout mLyDiagnosis = findViewById(R.id.cIlyDiagnosis);
        mLyDiagnosis.setOnClickListener(this);
        mIconDiagnosis = findViewById(R.id.cIiconDiagnosis);
        mLyDiagnosisValue = findViewById(R.id.cIlyDiagnosisValue);

        LinearLayout mLyPrescription = findViewById(R.id.cIlyPrescription);
        mLyPrescription.setOnClickListener(this);
        mIconPrescription = findViewById(R.id.cIiconPrescription);
        mLyPrescriptionValue = findViewById(R.id.cIlyPrescriptionValue);


        LinearLayout lyHistoryEx = findViewById(R.id.cIlyHistoryEx);
        lyHistoryEx.setOnClickListener(this);
        iconHistory = findViewById(R.id.cIiconHistory);
        lyHistoryExValue = findViewById(R.id.cIlyHistoryExValue);

        mCIdropModeDelHistory = findViewById(R.id.cIdropModeDelHistory);
        mCIdropCryBirthHistory = findViewById(R.id.cIdropCryBirthHistory);
        mCIdropPhysIllHistory = findViewById(R.id.cIdropPhysIllHistory);
        mCIdropSignHistory = findViewById(R.id.cIdropSignHistory);
        mCIdropImmunizationHistory = findViewById(R.id.cIdropImmunizationHistory);
        mCIdropFeedingHistory = findViewById(R.id.cIdropFeedingHistory);
        mCIdropTbPatientHistory = findViewById(R.id.cIdropTbPatientHistory);
        mCIdropSocialSmileHistory = findViewById(R.id.cIdropSocialSmileHistory);
        mCIdropNeckHoldingHistory = findViewById(R.id.cIdropNeckHoldingHistory);
        mCIdropSittingSupportHistory = findViewById(R.id.cIdropSittingSupportHistory);
        mCIinputWeightGenPhy =findViewById(R.id.cIinputWeightGenPhy);
        mCIinputHeightGenPhy =findViewById(R.id.cIinputHeightGenPhy);
        mCIinputHeadCRF =findViewById(R.id.cIinputHeadCRF);
        mCIdropPallorGenPhy = findViewById(R.id.cIdropPallorGenPhy);
        mCIdropJaundiceGenPhy = findViewById(R.id.cIdropJaundiceGenPhy);
        mCIdropCyanosisGenPhy = findViewById(R.id.cIdropCyanosisGenPhy);
        mCIinputHRGenPhy =findViewById(R.id.cIinputHRGenPhy);
        mCIinputRRGenPhy =findViewById(R.id.cIinputRRGenPhy);
        mCIinputCapRefilGenPhy =findViewById(R.id.cIinputCapRefilGenPhy);
        mCIinputOxySatGenPhy =findViewById(R.id.cIinputOxySatGenPhy);
        mCIdropCongAnGenPhy = findViewById(R.id.cIdropCongAnGenPhy);
        mCIdropAnterFontGenPhy = findViewById(R.id.cIdropAnterFontGenPhy);
        mCIdropSkinLesGenPhy = findViewById(R.id.cIdropSkinLesGenPhy);
        mCIdropChestSysExa = findViewById(R.id.cIdropChestSysExa);
        mCIdropCVSSysExa = findViewById(R.id.cIdropCVSSysExa);
        mCIdropPAbdomenSysExa = findViewById(R.id.cIdropPAbdomenSysExa);
        mCIdropCNSSysExa = findViewById(R.id.cIdropCNSSysExa);
        mCIdropHearingSysExa = findViewById(R.id.cIdropHearingSysExa);
        mCIdropEyeCongenitalSysExa = findViewById(R.id.cIdropEyeCongenitalSysExa);
        mCIdropColourBlindnesSysExa = findViewById(R.id.cIdropColourBlindnesSysExa);
        mCIinputHBMandatoryInvest =findViewById(R.id.cIinputHBMandatoryInvest);
        mCIinputTLCMandatoryInvest =findViewById(R.id.cIinputTLCMandatoryInvest);
        multiSpinner = findViewById(R.id.cIdropDiagnosis);
        mSetSpinnerData(Arrays.asList(getResources().getStringArray(R.array.arr_diagnosis_cat_1)));


        mCIchkDAlreadyKnown = findViewById(R.id.cIchkDAlreadyKnown);
        mCIinputPrescription =findViewById(R.id.cIinputPrescription);
        LinearLayout mCIsubmitPatientInput = findViewById(R.id.cIsubmitPatientInput);
        mCIsubmitPatientInput.setOnClickListener(this);
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
                        mSetServerValuesToSpinner(dataEntity.getDeliverytype(),mCIdropModeDelHistory,R.array.arr_mode_del);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIdropModeDelHistory);
                    }
                    if(dataEntity.getCryafterbirth()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getCryafterbirth(),mCIdropCryBirthHistory,R.array.arr_cry);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIdropCryBirthHistory);
                    }
                    if(dataEntity.getHistroyadmillness()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getHistroyadmillness(),mCIdropPhysIllHistory,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIdropPhysIllHistory);
                    }
                    if(dataEntity.getHistoryfamily()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getHistoryfamily(),mCIdropSignHistory,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIdropSignHistory);
                    }
                    if(dataEntity.getImmunstatus()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getImmunstatus(),mCIdropImmunizationHistory,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIdropImmunizationHistory);
                    }
                    if(dataEntity.getHistoryfeeding()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getHistoryfeeding(),mCIdropFeedingHistory,R.array.arr_feeding);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIdropFeedingHistory);
                    }

                    if(dataEntity.getContactwithtb()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getContactwithtb(),mCIdropTbPatientHistory,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIdropTbPatientHistory);
                    }









                }

                if(dataModel.getTitle().equals(IntentParams.TITLE_MILESTONE)) {
                    if (dataEntity.getSocialSmile() != null) {
                        mSetServerValuesToSpinner(dataEntity.getSocialSmile(), mCIdropSocialSmileHistory, R.array.arr_y_n);
                    } else {
                        mSetServerValuesToSpinnerELSE(mCIdropSocialSmileHistory);
                    }

                    if (dataEntity.getNeckHolding() != null) {
                        mSetServerValuesToSpinner(dataEntity.getNeckHolding(), mCIdropNeckHoldingHistory, R.array.arr_y_n);
                    } else {
                        mSetServerValuesToSpinnerELSE(mCIdropNeckHoldingHistory);
                    }

                    if (dataEntity.getSittingWithSupport() != null) {
                        mSetServerValuesToSpinner(dataEntity.getSittingWithSupport(), mCIdropSittingSupportHistory, R.array.arr_y_n);
                    } else {
                        mSetServerValuesToSpinnerELSE(mCIdropSittingSupportHistory);
                    }
                }
                if(dataModel.getTitle().equals(IntentParams.TITLE_GEN_EXAM))
                {
                    if(dataEntity.getWeight()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getWeight(),mCIinputWeightGenPhy);
                    }else {
                        mSetServerValuesToEditTextELSE(mCIinputWeightGenPhy);
                    }
                    if(dataEntity.getHeight()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getHeight(),mCIinputHeightGenPhy);
                    }else {
                        mSetServerValuesToEditTextELSE(mCIinputHeightGenPhy);
                    }
                    if(dataEntity.getHeadCircum()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getHeadCircum(),mCIinputHeadCRF);
                    }else {
                        mSetServerValuesToEditTextELSE(mCIinputHeadCRF);
                    }
                    if(dataEntity.getPallor()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getPallor(),mCIdropPallorGenPhy,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIdropPallorGenPhy);
                    }

                    if(dataEntity.getJaundice()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getJaundice(),mCIdropJaundiceGenPhy,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIdropJaundiceGenPhy);
                    }
                    if(dataEntity.getCyanosis()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getCyanosis(),mCIdropCyanosisGenPhy,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIdropCyanosisGenPhy);
                    }
                    if(dataEntity.getHeartRate()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getHeartRate(),mCIinputHRGenPhy);
                    }else {
                        mSetServerValuesToEditTextELSE(mCIinputHRGenPhy);
                    }
                    if(dataEntity.getRespiratoryRate()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getRespiratoryRate(),mCIinputRRGenPhy);
                    }else {
                        mSetServerValuesToEditTextELSE(mCIinputRRGenPhy);
                    }
                    if(dataEntity.getCappillaryRefillTime()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getCappillaryRefillTime(),mCIinputCapRefilGenPhy);
                    }else {
                        mSetServerValuesToEditTextELSE(mCIinputCapRefilGenPhy);
                    }

                    if(dataEntity.getOxygenSaturation()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getOxygenSaturation(),mCIinputOxySatGenPhy);
                    }else {
                        mSetServerValuesToEditTextELSE(mCIinputOxySatGenPhy);
                    }


                    if(dataEntity.getCongenitalAnomalies()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getCongenitalAnomalies(),mCIdropCongAnGenPhy,R.array.arr_congestion_an);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIdropCongAnGenPhy);
                    }
                    if(dataEntity.getAnteriorFontanelle()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getAnteriorFontanelle(),mCIdropAnterFontGenPhy,R.array.arr_anterior_);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIdropAnterFontGenPhy);
                    }

                    if(dataEntity.getSkinLesions()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getSkinLesions(),mCIdropSkinLesGenPhy,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIdropSkinLesGenPhy);
                    }


                }

                if(dataModel.getTitle().equals(IntentParams.TITLE_SYS_EXAM))
                {
                    if(dataEntity.getChest()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getChest(),mCIdropChestSysExa,R.array.arr_chest);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIdropChestSysExa);
                    }
                    if(dataEntity.getCvs()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getCvs(),mCIdropCVSSysExa,R.array.arr_cvs);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIdropCVSSysExa);
                    }
                    if(dataEntity.getPerAbdomen()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getPerAbdomen(),mCIdropPAbdomenSysExa,R.array.arr_per_abdomen_);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIdropPAbdomenSysExa);
                    }

                    if(dataEntity.getCns()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getCns(),mCIdropCNSSysExa,R.array.arr_cns);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIdropCNSSysExa);
                    }

                    if(dataEntity.getHearing()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getHearing(),mCIdropHearingSysExa,R.array.arr_hearing);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIdropHearingSysExa);
                    }

                    if(dataEntity.getEyeCongenitalCataract()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getEyeCongenitalCataract(),mCIdropEyeCongenitalSysExa,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIdropEyeCongenitalSysExa);
                    }
                    if(dataEntity.getMovementOfEyeWithLight()!=null)
                    {
                        mSetServerValuesToSpinner(dataEntity.getMovementOfEyeWithLight(),mCIdropColourBlindnesSysExa,R.array.arr_yes_no);
                    }else {
                        mSetServerValuesToSpinnerELSE(mCIdropColourBlindnesSysExa);
                    }
                }
                if(dataModel.getTitle().equals(IntentParams.TITLE_MAN_INVEST))
                {
                    if(dataEntity.getHb()!=null)
                    {
                        mCIinputHBMandatoryInvest.setText(dataEntity.getHb());
                    }
                    if(dataEntity.getRelevantInvestigation()!=null)
                    {
                        mCIinputTLCMandatoryInvest.setText(dataEntity.getRelevantInvestigation());
                    }
                }
                if(dataModel.getTitle().equals(IntentParams.TITLE_DIAGNOSIS))
                {
                    if(dataEntity.getAlreadyKnown()!=null)
                    {
                        mCIchkDAlreadyKnown.setChecked(!dataEntity.getAlreadyKnown().equalsIgnoreCase("No"));
                    }
                    mCIchkDAlreadyKnown.setEnabled(false);
                    if(dataEntity.getPrescription()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getPrescription(),mCIinputPrescription);
                    }

                    if(dataEntity.getDiagnosed()!=null)
                    {
                        mSetServerValuesToEditText(dataEntity.getDiagnosed(),cIViewDiagnosis);
                        cIViewDiagnosis.setVisibility(View.VISIBLE);
                        multiSpinner.setVisibility(View.GONE);
                        multiSpinner.setEnabled(false);
                    }else {
                        multiSpinner.setVisibility(View.GONE);
                        cIViewDiagnosis.setVisibility(View.GONE);
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
                Log.e(" onItemsSelected "," onItemsSelected ");
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

    private void mSetValidationListeners()
    {
          mCIinputWeightGenPhy.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() > 0)
                {
                    String inputString=  mCIinputWeightGenPhy.getText().toString();

                    int input=Integer.parseInt(inputString);
                    if(!(input >= 2 && input <= 10))
                    {
                          mCIinputWeightGenPhy.setError("*should be between 2-10");
                    }
                }
            }
        });

            mCIinputCapRefilGenPhy.addTextChangedListener(new TextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {}
                @Override
                public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                    if(charSequence.length() > 0)
                    {
                        String inputString=  mCIinputCapRefilGenPhy.getText().toString();

                        int input=Integer.parseInt(inputString);
                        if(!(input >= 1 && input <= 3))
                        {
                            mCIinputCapRefilGenPhy.setError("*should be between 1-3");
                        }
                    }
                }
            });
          mCIinputHeightGenPhy.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() > 0)
                {
                    String inputString=  mCIinputHeightGenPhy.getText().toString();
                    int input=Integer.parseInt(inputString);
                    if(!(input >= 20 && input <= 80))
                    {
                          mCIinputHeightGenPhy.setError("*should be between 20-80");
                    }
                }
            }
        });
          mCIinputHeadCRF.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() != 0)
                {
                    String inputString=  mCIinputHeadCRF.getText().toString();
                    int input=Integer.parseInt(inputString);
                    if(!(input >= 20 && input <= 50))
                    {
                          mCIinputHeadCRF.setError("*should be between 20-50");
                    }
                }
            }
        });

          mCIinputHRGenPhy.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() != 0)
                {
                    String inputString=  mCIinputHRGenPhy.getText().toString();
                    int input=Integer.parseInt(inputString);
                    if(!(input >= 50 && input <= 200))
                    {
                          mCIinputHRGenPhy.setError("*should be between 50-200");
                    }

                }
            }
        });
          mCIinputRRGenPhy.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() != 0)
                {
                    String inputString=  mCIinputRRGenPhy.getText().toString();
                    int input=Integer.parseInt(inputString);
                    if(!(input >= 20 && input <= 30))
                    {
                          mCIinputRRGenPhy.setError("*should be between 20-30");
                    }

                }
            }
        });
          mCIinputOxySatGenPhy.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() != 0)
                {
                    String inputString=  mCIinputOxySatGenPhy.getText().toString();
                    int input=Integer.parseInt(inputString);
                    if(!(input >= 70 && input <= 100))
                    {
                          mCIinputOxySatGenPhy.setError("*should be between 70% -100%");
                    }

                }
            }
        });
    }

    private boolean validateDataToPost() {

        if (TextUtils.isEmpty(  mCIinputWeightGenPhy.getText())) {
            mShowToast("Please enter Weight ");
            return false;
        }
        if (!TextUtils.isEmpty(  mCIinputWeightGenPhy.getText())) {
            String inputString =   mCIinputWeightGenPhy.getText().toString();
            int input = Integer.parseInt(inputString);
            if (!(input >= 2 && input <= 10)) {
                mShowToast("Weight should be between 5-120");
                return false;
            }
        }
        if (TextUtils.isEmpty(  mCIinputHeightGenPhy.getText())) {
            mShowToast("Please enter Height ");
            return false;
        }
        if (!TextUtils.isEmpty(  mCIinputHeightGenPhy.getText())) {
            String inputString =   mCIinputHeightGenPhy.getText().toString();
            int input = Integer.parseInt(inputString);
            if (!(input >= 20 && input <= 80)) {
                mShowToast("Height should be between 20-80");
                return false;
            }
        }
        if (TextUtils.isEmpty(  mCIinputCapRefilGenPhy.getText())) {
            mShowToast("Please enter Capillary Refill Time ");
            return false;
        }
        if (!TextUtils.isEmpty(  mCIinputCapRefilGenPhy.getText())) {
            String inputString =   mCIinputCapRefilGenPhy.getText().toString();
            int input = Integer.parseInt(inputString);
            if (!(input >= 1 && input <= 3)) {
                mShowToast("Capillary Refill Time should be between 1-3");
                return false;
            }
        }
        if (TextUtils.isEmpty(  mCIinputHeadCRF.getText())) {
            mShowToast("Please enter Head circumference (in cm) ");
            return false;
        }
        if (!TextUtils.isEmpty(  mCIinputHeadCRF.getText())) {
            String inputString =   mCIinputHeadCRF.getText().toString();
            int input = Integer.parseInt(inputString);
            if (!(input >= 20 && input <= 50)) {
                mShowToast("Head circumference (in cm) should be between 20-50");
                return false;
            }
        }




        if (TextUtils.isEmpty(  mCIinputHRGenPhy.getText())) {
            mShowToast("Please enter Heart rate (per min) ");
            return false;
        }
        if (!TextUtils.isEmpty(  mCIinputHRGenPhy.getText())) {
            String inputString =   mCIinputHRGenPhy.getText().toString();
            int input = Integer.parseInt(inputString);
            if (!(input >= 50 && input <= 200)) {
                mShowToast("Heart rate (per min) should be between 50-200");
                return false;
            }
        }

        if (TextUtils.isEmpty(  mCIinputRRGenPhy.getText())) {
            mShowToast("Please enter Respiratory rate (per min) ");
            return false;
        }
        if (!TextUtils.isEmpty(  mCIinputRRGenPhy.getText())) {
            String inputString =   mCIinputRRGenPhy.getText().toString();
            int input = Integer.parseInt(inputString);
            if (!(input >= 20 && input <= 30)) {
                mShowToast("Respiratory rate (per min) should be between 20-30");
                return false;
            }
        }

        if (TextUtils.isEmpty(  mCIinputOxySatGenPhy.getText())) {
            mShowToast("Please enter Oxygen Saturation(> 93 per) ");
            return false;
        }
        if (!TextUtils.isEmpty(  mCIinputOxySatGenPhy.getText())) {
            String inputString =   mCIinputOxySatGenPhy.getText().toString();
            int input = Integer.parseInt(inputString);
            if (!(input >= 70 && input <= 100)) {
                mShowToast("Oxygen Saturation(> 93 per) should be between 70%-100%");
                return false;
            }
        }


        if (selectedDiagnosis.length()==0) {
            mShowToast("Please select an option for Diagnosis");
            return false;
        }
        return true;
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.cIlyGenPhysicalEx) {
            if (mClickOne) {
                mShowHideLayouts(0);
                mClickOne = false;
            } else {
                mShowHideLayoutsAll();
                mClickOne = true;
            }
        }
        if (view.getId() == R.id.cIlySysExa) {
            if (mClickTwo) {
                mShowHideLayouts(1);
                mClickTwo = false;
            } else {
                mShowHideLayoutsAll();
                mClickTwo = true;
            }
        }
        if (view.getId() == R.id.cIlyMandatoryInvest) {
            if (mClickThree) {
                mShowHideLayouts(2);
                mClickThree = false;
            } else {
                mShowHideLayoutsAll();
                mClickThree = true;
            }
        }
        if (view.getId() == R.id.cIlyDiagnosis) {
            if (mClickFour) {
                mShowHideLayouts(3);
                mClickFour = false;
            } else {
                mShowHideLayoutsAll();
                mClickFour = true;
            }
        }
        if (view.getId() == R.id.cIlyPrescription) {
            if (mClickFive) {
                mShowHideLayouts(4);
                mClickFive = false;
            } else {
                mShowHideLayoutsAll();
                mClickFive = true;
            }
        }
        if (view.getId() == R.id.cIlyHistoryEx) {
            if (mClickSix) {
                mShowHideLayouts(5);
                mClickSix = false;
            } else {
                mShowHideLayoutsAll();
                mClickSix = true;
            }
        }
        if (view.getId() == R.id.cIsubmitPatientInput) {
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

    @Override
    public void onBackPressed() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                performBackPress(CategoryIPatientEntryActivity.this);
            }
        });
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
            if(isNetworkAvailable()) {
                createProgressBar(R.id.cIrelMain);
                APIInterface apiInterface = ApiClient.getClientAuthenticationWithAuth(preferences.getString(SharedParams.AUTH_TOKEN,"")).create(APIInterface.class);
                PostDataForCategoryIRequest request = new PostDataForCategoryIRequest();
                request.setCreatedBy(doctorName);
                request.setCreatedDate(getDateToSend());
                request.setPatientId(memberData.getMemberid());
                request.setDistrict(preferences.getString(SharedParams.DISTRICT, null));
                request.setFacility(sharedPreferences.getString(SharedParams.FACTYPE, "") + "/" + sharedPreferences.getString(SharedParams.FACILITY, ""));
                request.setCategory("1");
                request.setUserId(preferences.getString(SharedParams.SUB, null));
                request.setDeliveryType(mCIdropModeDelHistory.getSelectedItem() + "");
                request.setCryAfterBirth(mCIdropCryBirthHistory.getSelectedItem() + "");
                request.setHistroyAdmIllness(mCIdropPhysIllHistory.getSelectedItem() + "");
                request.setHistoryFamily(mCIdropSignHistory.getSelectedItem() + "");
                request.setFamilyHistoryIfYes("");
                request.setImmunStatus(mCIdropImmunizationHistory.getSelectedItem() + "");
                request.setHistoryFeeding(mCIdropFeedingHistory.getSelectedItem() + "");
                request.setContactWithTB(mCIdropTbPatientHistory.getSelectedItem() + "");
                request.setHistoryDeworming("");
                request.setNeckHolding(mCIdropNeckHoldingHistory.getSelectedItem() + "");
                request.setSocialSmile(mCIdropSocialSmileHistory.getSelectedItem() + "");
                request.setSittingWithSupport(mCIdropSittingSupportHistory.getSelectedItem() + "");

                // Step 2
                request.setWeight(mCIinputWeightGenPhy.getText().toString());
                request.setHeight(mCIinputHeightGenPhy.getText().toString());
                request.setHeadCircum(mCIinputHeadCRF.getText().toString());
                request.setOxygenSaturation(mCIinputOxySatGenPhy.getText().toString());
                request.setPallor(mCIdropPallorGenPhy.getSelectedItem() + "");
                request.setJaundice(mCIdropJaundiceGenPhy.getSelectedItem() + "");
                request.setCyanosis(mCIdropCyanosisGenPhy.getSelectedItem() + "");
                request.setHeartRate(mCIinputHRGenPhy.getText().toString());
                request.setRespiratoryRate(mCIinputRRGenPhy.getText().toString());
                request.setCappillaryRefillTime(mCIinputCapRefilGenPhy.getText().toString());
                request.setCongenitalAnomalies(mCIdropCongAnGenPhy.getSelectedItem() + "");
                request.setAnteriorFontanelle(mCIdropAnterFontGenPhy.getSelectedItem() + "");
                request.setSkinLesions(mCIdropSkinLesGenPhy.getSelectedItem() + "");

                // Step 3
                request.setChest(mCIdropChestSysExa.getSelectedItem() + "");
                request.setCvs(mCIdropCVSSysExa.getSelectedItem() + "");
                request.setPerAbdomen(mCIdropPAbdomenSysExa.getSelectedItem() + "");
                request.setCns(mCIdropCNSSysExa.getSelectedItem() + "");
                request.setHearing(mCIdropHearingSysExa.getSelectedItem() + "");
                request.setEyeCongenital(mCIdropEyeCongenitalSysExa.getSelectedItem() + "");
                request.setMovementOfEyeBallWithLight(mCIdropColourBlindnesSysExa.getSelectedItem() + "");

                // step 4
                request.sethB(mCIinputHBMandatoryInvest.getText().toString());
                request.setRelevantInvestigation(mCIinputTLCMandatoryInvest.getText().toString());

                //step 5
                request.setDiagnosed(selectedDiagnosis);
                if (mCIchkDAlreadyKnown.isChecked()) {
                    request.setAlreadyKnown("yes");
                } else {
                    request.setAlreadyKnown("no");
                }
                request.setPrescription(mCIinputPrescription.getText().toString());
                Log.e(" request ", "" + request.toString());
                Call<SubmitPatientData> call = apiInterface.submitDataForSurveyCatI(request);
                call.enqueue(new Callback<SubmitPatientData>() {
                    @Override
                    public void onResponse(Call<SubmitPatientData> call, Response<SubmitPatientData> response) {
                        try {
                            if (response.isSuccessful()) {
                                referenceGeneratedPopup(CategoryIPatientEntryActivity.this,response.body().getRefernceId());
//                                mShowToast("Submitted Successfully with reference id "+response.body().getRefernceId());
                            } else {
                                mHandleApiErrorCode(response.code(),response.errorBody().string(), CategoryIPatientEntryActivity.this);
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
                createProgressBar(R.id.cIrelMain);
                APIInterface apiInterface = ApiClient.getClientAuthenticationWithAuth(preferences.getString(SharedParams.AUTH_TOKEN,"")).create(APIInterface.class);
                PostMandatoryDataRequest request = new PostMandatoryDataRequest();
                request.setReferenceId(intentRecorderRefData.getData().get(0).getData().getReferenceid());

                request.setReferenceId(intentRecorderRefData.getData().get(0).getData().getReferenceid());
                request.setCreatedBy(sharedPreferences.getString(SharedParams.FNAME, "") + "" + sharedPreferences.getString(SharedParams.LNAME, ""));
                request.setCreatedDate(getDateToSend());
                request.setPatientId(intentRecorderRefData.getPatient().get(0).getData().getMemberId());
                request.setDistrict(preferences.getString(SharedParams.DISTRICT, null));
                request.setFacility(sharedPreferences.getString(SharedParams.FACTYPE, "") + "/" + sharedPreferences.getString(SharedParams.FACILITY, ""));
                request.setCategory("1");
                request.setUserId(preferences.getString(SharedParams.SUB, null));


                request.setHb(mCIinputHBMandatoryInvest.getText().toString());
                request.setRelevantInvestigation(mCIinputTLCMandatoryInvest.getText().toString());
                Log.e(" request ",""+request.toString());
                Call<SubmitPatientMandatoryData> call = apiInterface.submitMandatoryInvestigationReference(request);
                call.enqueue(new Callback<SubmitPatientMandatoryData>() {
                    @Override
                    public void onResponse(Call<SubmitPatientMandatoryData> call, Response<SubmitPatientMandatoryData> response) {
                        try {
                            if (response.isSuccessful()) {
                                mShowToast("Submitted Successfully !");
                            } else {
                                mHandleApiErrorCode(response.code(),response.errorBody().string(), CategoryIPatientEntryActivity.this);
                            }
                            disableProgressBar();

                        } catch (Exception e) {
                            Log.e(" Exception ", "" + e.getMessage());
                            disableProgressBar();
                        }


                    }

                    @Override
                    public void onFailure(Call<SubmitPatientMandatoryData> call, Throwable t) {
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
