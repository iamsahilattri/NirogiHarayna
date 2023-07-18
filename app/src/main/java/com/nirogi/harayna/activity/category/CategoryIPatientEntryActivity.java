package com.nirogi.harayna.activity.category;

import android.content.SharedPreferences;
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
import com.nirogi.harayna.model.request.PostDataForCategoryIRequest;
import com.nirogi.harayna.model.response.PatientListModelResponse;
import com.nirogi.harayna.model.response.SubmitPatientData;
import com.nirogi.harayna.network.APIInterface;
import com.nirogi.harayna.network.ApiClient;
import com.nirogi.harayna.utils.BaseActivity;
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

    private LinearLayout mLyPrescription;
    private AppCompatImageView mIconPrescription;
    private LinearLayout mLyPrescriptionValue;
    private LinearLayout lyHistoryEx;
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
    private AppCompatEditText mCIinputWeightGenPhy;
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
    private LinearLayout mCIbackPatientInput;
    private LinearLayout mCIsubmitPatientInput;
    private MultiSpinner multiSpinner;
    private String selectedDiagnosis;

    private String doctorName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_input_cat_i);
        sharedPreferences = NIROGI.getInstance().getPreferences();
        initView();
        mSetValuesToViews();
        initDataToView();
    }

    private void initDataToView() {
        doctorName=sharedPreferences.getString(SharedParams.FNAME, "")+""+ sharedPreferences.getString(SharedParams.LNAME, "");
        mTxtFacilityName.setText(sharedPreferences.getString(SharedParams.FACTYPE, "")+" "+sharedPreferences.getString(SharedParams.FACILITY, ""));
        mTxtFacilityIncharge.setText("Dr . "+doctorName);
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
        mRelMain = findViewById(R.id.cIrelMain);
        mSwipeDown = findViewById(R.id.cIswipeDown);
        mInfoLy = findViewById(R.id.cIinfoLy);
        mTxtPatientPPPID = findViewById(R.id.cItxtPatientPPPID);
        mTxtPatientName = findViewById(R.id.cItxtPatientName);
        mTxtPatientGenderAge = findViewById(R.id.cItxtPatientGenderAge);
        mTxtPatientMobile = findViewById(R.id.cItxtPatientMobile);
        mTxtPatientAddress = findViewById(R.id.cItxtPatientAddress);
        mTxtPatientDistrict = findViewById(R.id.cItxtPatientDistrict);
        mTxtFacilityName = findViewById(R.id.cItxtFacilityName);
        mTxtFacilityIncharge = findViewById(R.id.cItxtFacilityIncharge);
        mTxtDate = findViewById(R.id.cItxtDate);
        mLyInput = findViewById(R.id.cIlyInput);
        mLyGenPhysicalEx = findViewById(R.id.cIlyGenPhysicalEx);
        mLyGenPhysicalEx.setOnClickListener(this);
        mIconGenPhy = findViewById(R.id.cIiconGenPhy);
        mLyGenPhysicalxValue = findViewById(R.id.cIlyGenPhysicalxValue);

        mLySysExa = findViewById(R.id.cIlySysExa);
        mLySysExa.setOnClickListener(this);
        mIconSysExa = findViewById(R.id.cIiconSysExa);
        mLySysExaValue = findViewById(R.id.cIlySysExaValue);

        mLyMandatoryInvest = findViewById(R.id.cIlyMandatoryInvest);
        mLyMandatoryInvest.setOnClickListener(this);
        mIconMandatoryInvest = findViewById(R.id.cIiconMandatoryInvest);
        mLyMandatoryInvestValue = findViewById(R.id.cIlyMandatoryInvestValue);

        mLyDiagnosis = findViewById(R.id.cIlyDiagnosis);
        mLyDiagnosis.setOnClickListener(this);
        mIconDiagnosis = findViewById(R.id.cIiconDiagnosis);
        mLyDiagnosisValue = findViewById(R.id.cIlyDiagnosisValue);

        mLyPrescription = findViewById(R.id.cIlyPrescription);
        mLyPrescription.setOnClickListener(this);
        mIconPrescription = findViewById(R.id.cIiconPrescription);
        mLyPrescriptionValue = findViewById(R.id.cIlyPrescriptionValue);


        lyHistoryEx = findViewById(R.id.cIlyHistoryEx);
        lyHistoryEx.setOnClickListener(this);
        iconHistory = findViewById(R.id.cIiconHistory);
        lyHistoryExValue = findViewById(R.id.cIlyHistoryExValue);

        mCIdropModeDelHistory = (AppCompatSpinner) findViewById(R.id.cIdropModeDelHistory);
        mCIdropCryBirthHistory = (AppCompatSpinner) findViewById(R.id.cIdropCryBirthHistory);
        mCIdropPhysIllHistory = (AppCompatSpinner) findViewById(R.id.cIdropPhysIllHistory);
        mCIdropSignHistory = (AppCompatSpinner) findViewById(R.id.cIdropSignHistory);
        mCIdropImmunizationHistory = (AppCompatSpinner) findViewById(R.id.cIdropImmunizationHistory);
        mCIdropFeedingHistory = (AppCompatSpinner) findViewById(R.id.cIdropFeedingHistory);
        mCIdropTbPatientHistory = (AppCompatSpinner) findViewById(R.id.cIdropTbPatientHistory);
        mCIdropSocialSmileHistory = (AppCompatSpinner) findViewById(R.id.cIdropSocialSmileHistory);
        mCIdropNeckHoldingHistory = (AppCompatSpinner) findViewById(R.id.cIdropNeckHoldingHistory);
        mCIdropSittingSupportHistory = (AppCompatSpinner) findViewById(R.id.cIdropSittingSupportHistory);
        mCIinputWeightGenPhy = (AppCompatEditText) findViewById(R.id.cIinputWeightGenPhy);
        mCIinputHeightGenPhy = (AppCompatEditText) findViewById(R.id.cIinputHeightGenPhy);
        mCIinputHeadCRF = (AppCompatEditText) findViewById(R.id.cIinputHeadCRF);
        mCIdropPallorGenPhy = (AppCompatSpinner) findViewById(R.id.cIdropPallorGenPhy);
        mCIdropJaundiceGenPhy = (AppCompatSpinner) findViewById(R.id.cIdropJaundiceGenPhy);
        mCIdropCyanosisGenPhy = (AppCompatSpinner) findViewById(R.id.cIdropCyanosisGenPhy);
        mCIinputHRGenPhy = (AppCompatEditText) findViewById(R.id.cIinputHRGenPhy);
        mCIinputRRGenPhy = (AppCompatEditText) findViewById(R.id.cIinputRRGenPhy);
        mCIinputCapRefilGenPhy = (AppCompatEditText) findViewById(R.id.cIinputCapRefilGenPhy);
        mCIinputOxySatGenPhy = (AppCompatEditText) findViewById(R.id.cIinputOxySatGenPhy);
        mCIdropCongAnGenPhy = (AppCompatSpinner) findViewById(R.id.cIdropCongAnGenPhy);
        mCIdropAnterFontGenPhy = (AppCompatSpinner) findViewById(R.id.cIdropAnterFontGenPhy);
        mCIdropSkinLesGenPhy = (AppCompatSpinner) findViewById(R.id.cIdropSkinLesGenPhy);
        mCIdropChestSysExa = (AppCompatSpinner) findViewById(R.id.cIdropChestSysExa);
        mCIdropCVSSysExa = (AppCompatSpinner) findViewById(R.id.cIdropCVSSysExa);
        mCIdropPAbdomenSysExa = (AppCompatSpinner) findViewById(R.id.cIdropPAbdomenSysExa);
        mCIdropCNSSysExa = (AppCompatSpinner) findViewById(R.id.cIdropCNSSysExa);
        mCIdropHearingSysExa = (AppCompatSpinner) findViewById(R.id.cIdropHearingSysExa);
        mCIdropEyeCongenitalSysExa = (AppCompatSpinner) findViewById(R.id.cIdropEyeCongenitalSysExa);
        mCIdropColourBlindnesSysExa = (AppCompatSpinner) findViewById(R.id.cIdropColourBlindnesSysExa);
        mCIinputHBMandatoryInvest = (AppCompatEditText) findViewById(R.id.cIinputHBMandatoryInvest);
        mCIinputTLCMandatoryInvest = (AppCompatEditText) findViewById(R.id.cIinputTLCMandatoryInvest);
        multiSpinner = findViewById(R.id.cIdropDiagnosis);
        mSetSpinnerData(Arrays.asList(getResources().getStringArray(R.array.arr_diagnosis_cat_1)));


        mCIchkDAlreadyKnown = (AppCompatCheckBox) findViewById(R.id.cIchkDAlreadyKnown);
        mCIinputPrescription = (AppCompatEditText) findViewById(R.id.cIinputPrescription);
        mCIbackPatientInput = (LinearLayout) findViewById(R.id.cIbackPatientInput);
        mCIsubmitPatientInput = (LinearLayout) findViewById(R.id.cIsubmitPatientInput);
        mCIsubmitPatientInput.setOnClickListener(this);
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
            createProgressBar(R.id.cIrelMain);
            APIInterface apiInterface = ApiClient.getClientAuthenticationWithAuth(NIROGI.token).create(APIInterface.class);
            PostDataForCategoryIRequest request = new PostDataForCategoryIRequest();
            request.setCreatedBy(doctorName);
            request.setPatientId(memberData.getMemberid());
            request.setDistrict(preferences.getString(SharedParams.DISTRICT,null));
            request.setFacility(sharedPreferences.getString(SharedParams.FACTYPE, "")+"/"+sharedPreferences.getString(SharedParams.FACILITY, ""));
            request.setCategory("1");
            request.setUserId(preferences.getString(SharedParams.SUB,null));
            request.setDeliveryType(mCIdropModeDelHistory.getSelectedItem()+"");
            request.setCryAfterBirth(mCIdropCryBirthHistory.getSelectedItem()+"");
            request.setHistroyAdmIllness(mCIdropPhysIllHistory.getSelectedItem()+"");
            request.setHistoryFamily(mCIdropSignHistory.getSelectedItem()+"");
            request.setFamilyHistoryIfYes("");
            request.setImmunStatus(mCIdropImmunizationHistory.getSelectedItem()+"");
            request.setHistoryFeeding(mCIdropFeedingHistory.getSelectedItem()+"");
            request.setContactWithTB(mCIdropTbPatientHistory.getSelectedItem()+"");
            request.setHistoryDeworming("");
            request.setNeckHolding(mCIdropNeckHoldingHistory.getSelectedItem()+"");
            request.setSocialSmile(mCIdropSocialSmileHistory.getSelectedItem()+"");
            request.setSittingWithSupport(mCIdropSittingSupportHistory.getSelectedItem()+"");

            // Step 2
            request.setWeight(mCIinputWeightGenPhy.getText().toString());
            request.setHeight(mCIinputHeightGenPhy.getText().toString());
            request.setHeadCircum(mCIinputHeadCRF.getText().toString());
            request.setOxygenSaturation(mCIinputOxySatGenPhy.getText().toString());
            request.setPallor(mCIdropPallorGenPhy.getSelectedItem()+"");
            request.setJaundice(mCIdropJaundiceGenPhy.getSelectedItem()+"");
            request.setCyanosis(mCIdropCyanosisGenPhy.getSelectedItem()+"");
            request.setHeartRate(mCIinputHRGenPhy.getText().toString());
            request.setRespiratoryRate(mCIinputRRGenPhy.getText().toString());
            request.setCappillaryRefillTime(mCIinputCapRefilGenPhy.getText().toString());
            request.setCongenitalAnomalies(mCIdropCongAnGenPhy.getSelectedItem()+"");
            request.setAnteriorFontanelle(mCIdropAnterFontGenPhy.getSelectedItem()+"");
            request.setSkinLesions(mCIdropSkinLesGenPhy.getSelectedItem()+"");

            // Step 3
            request.setChest(mCIdropChestSysExa.getSelectedItem()+"");
            request.setCvs(mCIdropCVSSysExa.getSelectedItem()+"");
            request.setPerAbdomen(mCIdropPAbdomenSysExa.getSelectedItem()+"");
            request.setCns(mCIdropCNSSysExa.getSelectedItem()+"");
            request.setHearing(mCIdropHearingSysExa.getSelectedItem()+"");
            request.setEyeCongenital(mCIdropEyeCongenitalSysExa.getSelectedItem()+"");
            request.setMovementOfEyeBallWithLight(mCIdropColourBlindnesSysExa.getSelectedItem()+"");

            // step 4
            request.sethB(mCIinputHBMandatoryInvest.getText().toString());
            request.setRelevantInvestigation(mCIinputTLCMandatoryInvest.getText().toString());

            //step 5
            request.setDiagnosed(selectedDiagnosis);
            if(mCIchkDAlreadyKnown.isChecked())
            {
                request.setAlreadyKnown("yes");
            }else {
                request.setAlreadyKnown("no");
            }
            request.setPrescription(mCIinputPrescription.getText().toString());
            Log.e(" request ",""+request.toString());
            Call<SubmitPatientData> call = apiInterface.submitDataForSurvey(request);
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


        } catch (Exception ee) {
            Log.e(" Exception ", "" + ee.getMessage());
        }

    }
}
