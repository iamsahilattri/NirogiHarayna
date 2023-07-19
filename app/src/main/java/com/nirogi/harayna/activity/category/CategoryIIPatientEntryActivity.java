package com.nirogi.harayna.activity.category;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;

import com.nirogi.harayna.R;
import com.nirogi.harayna.model.request.PostDataForCategoryIIRequest;
import com.nirogi.harayna.model.response.PatientListModelResponse;
import com.nirogi.harayna.model.response.SubmitPatientData;
import com.nirogi.harayna.network.APIInterface;
import com.nirogi.harayna.network.ApiClient;
import com.nirogi.harayna.utils.BaseActivity;
import com.nirogi.harayna.utils.MultiSpinner;
import com.nirogi.harayna.utils.NIROGI;
import com.nirogi.harayna.utils.SharedParams;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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

    private LinearLayout mLySysExa;
    private AppCompatImageView mIconSysExa;
    private LinearLayout mLySysExaValue;
    private LinearLayout mLyMandatoryInvest;
    private AppCompatImageView mIconMandatoryInvest;
    private LinearLayout mLyMandatoryInvestValue;
    private LinearLayout mLyDiagnosis;
    private AppCompatImageView mIconDiagnosis;
    private LinearLayout mLyDiagnosisValue;
    private LinearLayout mLyPrescription;
    private AppCompatImageView mIconPrescription;
    private LinearLayout mLyPrescriptionValue;
    private LinearLayout lyHistoryEx;
    private AppCompatImageView iconHistory;
    private LinearLayout lyHistoryExValue;


    private boolean mClickOne = true, mClickTwo = true, mClickThree = true, mClickFour = true, mClickFive = true, mClickSix = true;

    PatientListModelResponse memberData;

    private MultiSpinner multiSpinner;
    private String selectedDiagnosis;
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
    private AppCompatEditText mCIIinputHRGenPhy;
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
    private AppCompatTextView mInputRightEyeSysExa;
    private AppCompatTextView mInputLeftEyeSysExa;
    private AppCompatSpinner mCIIdropColorBlindSSysExa;
    private AppCompatSpinner mCIIdropDentalExSysExa;
    private AppCompatEditText mCIIinputHBMandatoryInvest;
    private AppCompatEditText mCIIinputTLCMandatoryInvest;
    private MultiSpinner mCIIdropDiagnosis;
    private AppCompatCheckBox mCIIchkDAlreadyKnown;
    private AppCompatEditText mCIIinputPrescription;

    private LinearLayout cIIsubmitPatientInput;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = NIROGI.getInstance().getPreferences();
        setContentView(R.layout.activity_patient_input_cat_ii);
        initView();
        mSetValuesToViews();
        initDataToView();
    }

    private void initDataToView() {
        doctorName = sharedPreferences.getString(SharedParams.FNAME, "") + "" + sharedPreferences.getString(SharedParams.LNAME, "");
        mTxtFacilityName.setText(sharedPreferences.getString(SharedParams.FACTYPE, "") + " " + sharedPreferences.getString(SharedParams.FACILITY, ""));
        mTxtFacilityIncharge.setText("Dr . " + doctorName);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = dateFormat.format(Calendar.getInstance().getTime());
        mTxtDate.setText(formattedDate);
    }

    private void mSetValuesToViews() {
        try {
            if (getIntent() != null) {
                memberData = (PatientListModelResponse) getIntent().getSerializableExtra("memberData");
                if (memberData != null) {
                    mSetBackToolbar("Patient Details", true, "Category I (0-6 Months)");
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

        mLySysExa = findViewById(R.id.cIIlySysExa);
        mLySysExa.setOnClickListener(this);
        mIconSysExa = findViewById(R.id.cIIiconSysExa);
        mLySysExaValue = findViewById(R.id.cIIlySysExaValue);

        mLyMandatoryInvest = findViewById(R.id.cIIlyMandatoryInvest);
        mLyMandatoryInvest.setOnClickListener(this);
        mIconMandatoryInvest = findViewById(R.id.cIIiconMandatoryInvest);
        mLyMandatoryInvestValue = findViewById(R.id.cIIlyMandatoryInvestValue);

        mLyDiagnosis = findViewById(R.id.cIIlyDiagnosis);
        mLyDiagnosis.setOnClickListener(this);
        mIconDiagnosis = findViewById(R.id.cIIiconDiagnosis);
        mLyDiagnosisValue = findViewById(R.id.cIIlyDiagnosisValue);

        mLyPrescription = findViewById(R.id.cIIlyPrescription);
        mLyPrescription.setOnClickListener(this);
        mIconPrescription = findViewById(R.id.cIIiconPrescription);
        mLyPrescriptionValue = findViewById(R.id.cIIlyPrescriptionValue);

        lyHistoryEx = findViewById(R.id.cIIlyHistoryEx);
        lyHistoryEx.setOnClickListener(this);
        iconHistory = findViewById(R.id.cIIiconHistory);
        lyHistoryExValue = findViewById(R.id.cIIlyHistoryExValue);

        mCIIdropModeDelHistory = (AppCompatSpinner) findViewById(R.id.cIIdropModeDelHistory);
        mCIIdropCryBirthHistory = (AppCompatSpinner) findViewById(R.id.cIIdropCryBirthHistory);
        mCIIdropPhysIllHistory = (AppCompatSpinner) findViewById(R.id.cIIdropPhysIllHistory);
        mCIIdropSignHistory = (AppCompatSpinner) findViewById(R.id.cIIdropSignHistory);
        mCIIdropImmunizationHistory = (AppCompatSpinner) findViewById(R.id.cIIdropImmunizationHistory);
        mCIIdropFeedingHistory = (AppCompatSpinner) findViewById(R.id.cIIdropFeedingHistory);
        mCIIdropTbPatientHistory = (AppCompatSpinner) findViewById(R.id.cIIdropTbPatientHistory);
        mCIIdropDewormingPatientHistory = (AppCompatSpinner) findViewById(R.id.cIIdropDewormingPatientHistory);
        mCIIdropSittingHistory = (AppCompatSpinner) findViewById(R.id.cIIdropSittingHistory);
        mCIIdropSandHistory = (AppCompatSpinner) findViewById(R.id.cIIdropSandHistory);
        mCIIdropStandigingWithHistory = (AppCompatSpinner) findViewById(R.id.cIIdropStandigingWithHistory);
        mCIIdropPincerGraspHistory = (AppCompatSpinner) findViewById(R.id.cIIdropPincerGraspHistory);
        mCIIdropStrangerAnxietyHistory = (AppCompatSpinner) findViewById(R.id.cIIdropStrangerAnxietyHistory);
        mCIIdropSpeachHistory = (AppCompatSpinner) findViewById(R.id.cIIdropSpeachHistory);
        mCIIinputWeightGenPhy = (AppCompatEditText) findViewById(R.id.cIIinputWeightGenPhy);
        mCIIinputHeightGenPhy = (AppCompatEditText) findViewById(R.id.cIIinputHeightGenPhy);
        mCIIinputHeadCRF = (AppCompatEditText) findViewById(R.id.cIIinputHeadCRF);
        mCIIinputMidArm = (AppCompatEditText) findViewById(R.id.cIIinputMidArm);
        mCIIdropPallorGenPhy = (AppCompatSpinner) findViewById(R.id.cIIdropPallorGenPhy);
        mCIIdropJaundiceGenPhy = (AppCompatSpinner) findViewById(R.id.cIIdropJaundiceGenPhy);
        mCIIdropCyanosisGenPhy = (AppCompatSpinner) findViewById(R.id.cIIdropCyanosisGenPhy);
        mCIIinputHRGenPhy = (AppCompatEditText) findViewById(R.id.cIIinputHRGenPhy);
        mCIIinputRRGenPhy = (AppCompatEditText) findViewById(R.id.cIIinputRRGenPhy);
        mCIIdropPedalOeGenPhy = (AppCompatSpinner) findViewById(R.id.cIIdropPedalOeGenPhy);
        mCIIinputOxySatGenPhy = (AppCompatEditText) findViewById(R.id.cIIinputOxySatGenPhy);
        mCIIdropCongAnGenPhy = (AppCompatSpinner) findViewById(R.id.cIIdropCongAnGenPhy);
        mCIIdropAnterFontGenPhy = (AppCompatSpinner) findViewById(R.id.cIIdropAnterFontGenPhy);
        mCIIdropHairTexGenPhy = (AppCompatSpinner) findViewById(R.id.cIIdropHairTexGenPhy);
        mCIIdropSkinTexGenPhy = (AppCompatSpinner) findViewById(R.id.cIIdropSkinTexGenPhy);
        mCIIdropSkinTurGenPhy = (AppCompatSpinner) findViewById(R.id.cIIdropSkinTurGenPhy);
        mCIIdropSkinLeaionsGenPhy = (AppCompatSpinner) findViewById(R.id.cIIdropSkinLeaionsGenPhy);
        mCIIdropChestSysExa = (AppCompatSpinner) findViewById(R.id.cIIdropChestSysExa);
        mCIIdropCVSSysExa = (AppCompatSpinner) findViewById(R.id.cIIdropCVSSysExa);
        mCIIdropPAbdomenSysExa = (AppCompatSpinner) findViewById(R.id.cIIdropPAbdomenSysExa);
        mCIIdropCNSSysExa = (AppCompatSpinner) findViewById(R.id.cIIdropCNSSysExa);
        mCIIdropHearingSysExa = (AppCompatSpinner) findViewById(R.id.cIIdropHearingSysExa);
        mInputRightEyeSysExa = (AppCompatTextView) findViewById(R.id.inputRightEyeSysExa);
        mInputLeftEyeSysExa = (AppCompatTextView) findViewById(R.id.inputLeftEyeSysExa);
        mCIIdropColorBlindSSysExa = (AppCompatSpinner) findViewById(R.id.cIIdropColorBlindSSysExa);
        mCIIdropDentalExSysExa = (AppCompatSpinner) findViewById(R.id.cIIdropDentalExSysExa);
        mCIIinputHBMandatoryInvest = (AppCompatEditText) findViewById(R.id.cIIinputHBMandatoryInvest);
        mCIIinputTLCMandatoryInvest = (AppCompatEditText) findViewById(R.id.cIIinputTLCMandatoryInvest);
        multiSpinner = (MultiSpinner) findViewById(R.id.cIIdropDiagnosis);
        mSetSpinnerData(Arrays.asList(getResources().getStringArray(R.array.arr_diagnosis_cat_1)));

        mCIIchkDAlreadyKnown = (AppCompatCheckBox) findViewById(R.id.cIIchkDAlreadyKnown);
        mCIIinputPrescription = (AppCompatEditText) findViewById(R.id.cIIinputPrescription);

        cIIsubmitPatientInput=findViewById(R.id.cIIsubmitPatientInput);
        cIIsubmitPatientInput.setOnClickListener(this);
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

            if (validateDataToPost()) {
                postDataForCategories();
            }

        }


    }

    private boolean validateDataToPost() {
        return true;
    }

    private void mShowHideLayouts(int clicked) {
        if (clicked == 0) {
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
            if(isNetworkAvailable())
            {
                createProgressBar(R.id.cIIrelMain);
                APIInterface apiInterface = ApiClient.getClientAuthenticationWithAuth(preferences.getString(SharedParams.AUTH_TOKEN,"")).create(APIInterface.class);
                PostDataForCategoryIIRequest request = new PostDataForCategoryIIRequest();
                request.setPatientId(memberData.getMemberid());
                request.setDistrict(preferences.getString(SharedParams.DISTRICT,null));
                request.setCreatedDate(getDateToSend());
                request.setCreatedBy(doctorName);
                request.setUserId(preferences.getString(SharedParams.SUB,null));
                request.setFacility(sharedPreferences.getString(SharedParams.FACTYPE, "")+"/"+sharedPreferences.getString(SharedParams.FACILITY, ""));
                request.setCategory("2");

                //step 1
                request.setDeliveryType(mCIIdropModeDelHistory.getSelectedItem()+"");
                request.setCryAfterBirth(mCIIdropCryBirthHistory.getSelectedItem()+"");
                request.setHistroyAdmIllness(mCIIdropPhysIllHistory.getSelectedItem()+"");
                request.setHistoryFamily(mCIIdropSignHistory.getSelectedItem()+"");
                request.setImmunStatus(mCIIdropImmunizationHistory.getSelectedItem()+"");
                request.setHistoryFeeding(mCIIdropFeedingHistory.getSelectedItem()+"");
                request.setContactWithTB(mCIIdropTbPatientHistory.getSelectedItem()+"");
                request.setHistoryDeworming(mCIIdropDewormingPatientHistory.getSelectedItem()+"");
                request.setSittingWithoutSupport(mCIIdropSittingHistory.getSelectedItem()+"");
                request.setStandingWithoutSupport(mCIIdropStandigingWithHistory.getSelectedItem()+"");
                request.setStandingWithSupport(mCIIdropStandigingWithHistory.getSelectedItem()+"");
                request.setPincerGrasp(mCIIdropPincerGraspHistory.getSelectedItem()+"");
                request.setStrangerAnxiety(mCIIdropStrangerAnxietyHistory.getSelectedItem()+"");
                request.setBisyllSpeech(mCIIdropSpeachHistory.getSelectedItem()+"");

                // Step 2
                request.setWeight(mCIIinputWeightGenPhy.getText().toString());
                request.setHeight(mCIIinputHeightGenPhy.getText().toString());
                request.setHeadCircum(mCIIinputHeadCRF.getText().toString());
                request.setMidArmCircum(mCIIinputMidArm.getText().toString());
                request.setPallor(mCIIdropPallorGenPhy.getSelectedItem()+"");
                request.setJaundice(mCIIdropJaundiceGenPhy.getSelectedItem()+"");
                request.setCyanosis(mCIIdropCyanosisGenPhy.getSelectedItem()+"");
                request.setHeartRate(mCIIinputHRGenPhy.getText().toString());
                request.setRespiratoryRate(mCIIinputRRGenPhy.getText().toString());
                request.setOxygenSatur(mCIIinputOxySatGenPhy.getText().toString());
                request.setPedalOedema(mCIIdropPedalOeGenPhy.getSelectedItem()+"");
                request.setCongenitalAnomalies(mCIIdropCongAnGenPhy.getSelectedItem()+"");
                request.setAnteriorFontanelle(mCIIdropAnterFontGenPhy.getSelectedItem()+"");
                request.setHairTexture(mCIIdropHairTexGenPhy.getSelectedItem()+"");
                request.setSkinTexture(mCIIdropSkinTexGenPhy.getSelectedItem()+"");
                request.setSkinTurgor(mCIIdropSkinTurGenPhy.getSelectedItem()+"");
                request.setSkinLesion(mCIIdropSkinLeaionsGenPhy.getSelectedItem()+"");

                // Step 3
                request.setChest(mCIIdropChestSysExa.getSelectedItem()+"");
                request.setCvs(mCIIdropCVSSysExa.getSelectedItem()+"");
                request.setPerAbdomen(mCIIdropPAbdomenSysExa.getSelectedItem()+"");
                request.setCns(mCIIdropCNSSysExa.getSelectedItem()+"");
                request.setHearing(mCIIdropHearingSysExa.getSelectedItem()+"");
                request.setRightEye(mInputRightEyeSysExa.getText().toString()+"");
                request.setLeftEye(mInputLeftEyeSysExa.getText().toString()+"");
                request.setColorVision(mCIIdropColorBlindSSysExa.getSelectedItem()+"");
                request.setDentalExamination(mCIIdropDentalExSysExa.getSelectedItem()+"");

                // step 4
                request.sethB(mCIIinputHBMandatoryInvest.getText().toString());
                request.setRelevantInvestigation(mCIIinputTLCMandatoryInvest.getText().toString());

                //step 5
                request.setDiagnosed(selectedDiagnosis);
                if(mCIIchkDAlreadyKnown.isChecked())
                {
                    request.setAlreadyKnown("yes");
                }else {
                    request.setAlreadyKnown("no");
                }
                request.setPrescription(mCIIinputPrescription.getText().toString());
                Log.e(" request ",""+request.toString());
                Call<SubmitPatientData> call = apiInterface.submitDataForSurveyCatII(request);
                call.enqueue(new Callback<SubmitPatientData>() {
                    @Override
                    public void onResponse(Call<SubmitPatientData> call, Response<SubmitPatientData> response) {
                        try {
                            if (response.isSuccessful()) {
                                mShowToast("Submitted Successfully with reference id "+response.body().getRefernceId());
                                disableProgressBar();
                            } else {
                                disableProgressBar();
                                mShowToast(" Error : "+response.errorBody().string());

                            }
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
            }else {
                mShowToast(getString(R.string.no_internet));
            }



        } catch (Exception ee) {
            Log.e(" Exception ", "" + ee.getMessage());
        }

    }
}
