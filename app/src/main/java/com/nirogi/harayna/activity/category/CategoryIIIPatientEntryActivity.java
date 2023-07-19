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
import com.nirogi.harayna.model.request.PostDataForCategoryIIIRequest;
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

public class CategoryIIIPatientEntryActivity extends BaseActivity implements View.OnClickListener {

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

    private boolean mClickOne = true, mClickTwo = true, mClickThree = true, mClickFour = true, mClickFive = true, mClickSix = true;

    private MultiSpinner multiSpinner;
    private String selectedDiagnosis;
    private String doctorName;
    private SharedPreferences sharedPreferences;
    private AppCompatEditText mInputWeightGenPhy;
    private AppCompatEditText mInputHeightGenPhy;
    private AppCompatEditText mInputBMIGenPhy;
    private AppCompatEditText mInputPulseGenPhy;
    private AppCompatEditText mInputBPGenPhy;
    private AppCompatSpinner mDropPallorGenPhy;
    private AppCompatSpinner mDropJaundiceGenPhy;
    private AppCompatSpinner mDropPOedemaGenPhy;
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

    private AppCompatCheckBox mChkUrineMandatoryInvest;
    private AppCompatEditText mInputUrineMandatoryInvest;
    private AppCompatCheckBox mChkAdvisedMandatoryInvest;
    private AppCompatEditText mInputAdvisedMandatoryInvest;
    private AppCompatSpinner mDropDiagnosis;
    private AppCompatCheckBox mChkDAlreadyKnown;
    private AppCompatEditText mInputPrescription,cIIIinputRRGenPhy;
    private PatientListModelResponse memberData;
    private LinearLayout mCIIlyHistoryEx;
    private AppCompatImageView iconHistory;
    private LinearLayout lyHistoryExValue;
    private AppCompatSpinner mCIIdropHosAdminHistory;
    private AppCompatSpinner mCIIdropFamilyHistory;
    private AppCompatSpinner mCIIdropImmunizationHistory;
    private AppCompatSpinner mCIIdropTbPatientHistory;
    private AppCompatSpinner mCIIdropDewormingPatientHistory;
    private AppCompatSpinner mCIIIdropConjectionAnGenPhy;
    private AppCompatSpinner mCIIIdropSkinTugaGenPhy;
    private AppCompatSpinner mCIIIdropSkinLeGenPhy;
    private AppCompatSpinner mCIIIdropWristWGenPhy;
    private AppCompatSpinner mCIIIdropVitDefGenPhy;
    private AppCompatSpinner mCIIIdropGondalExGenPhy;
    private AppCompatSpinner mCIIIdropEarDisGenPhy;
    private AppCompatSpinner mCIIIdropSpeechGenPhy;
    private AppCompatSpinner mCIIIdropIQGenPhy,cIIIdropCynosGenPhy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = NIROGI.getInstance().getPreferences();
        setContentView(R.layout.activity_patient_input_cat_iii);
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
                    mSetBackToolbar("Patient Details", true, "Category III (5-18 Years)");
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

        mTxtPatientPPPID = findViewById(R.id.cIIItxtPatientPPPID);
        mTxtPatientName = findViewById(R.id.cIIItxtPatientName);
        mTxtPatientGenderAge = findViewById(R.id.cIIItxtPatientGenderAge);
        mTxtPatientMobile = findViewById(R.id.cIIItxtPatientMobile);
        mTxtPatientAddress = findViewById(R.id.cIIItxtPatientAddress);
        mTxtPatientDistrict = findViewById(R.id.cIIItxtPatientDistrict);
        mTxtFacilityName = findViewById(R.id.cIIItxtFacilityName);
        mTxtFacilityIncharge = findViewById(R.id.cIIItxtFacilityIncharge);
        mTxtDate = findViewById(R.id.cIIItxtDate);
        mLyInput = findViewById(R.id.cIIIlyInput);
        mLyGenPhysicalEx = findViewById(R.id.cIIIlyGenPhysicalEx);
        mLyGenPhysicalEx.setOnClickListener(this);
        mIconGenPhy = findViewById(R.id.cIIIiconGenPhy);
        mLyGenPhysicalxValue = findViewById(R.id.cIIIlyGenPhysicalxValue);

        mLySysExa = findViewById(R.id.cIIIlySysExa);
        mLySysExa.setOnClickListener(this);
        mIconSysExa = findViewById(R.id.cIIIiconSysExa);
        mLySysExaValue = findViewById(R.id.cIIIlySysExaValue);

        mLyMandatoryInvest = findViewById(R.id.cIIIlyMandatoryInvest);
        mLyMandatoryInvest.setOnClickListener(this);
        mIconMandatoryInvest = findViewById(R.id.cIIIiconMandatoryInvest);
        mLyMandatoryInvestValue = findViewById(R.id.cIIIlyMandatoryInvestValue);

        mLyDiagnosis = findViewById(R.id.cIIIlyDiagnosis);
        mLyDiagnosis.setOnClickListener(this);
        mIconDiagnosis = findViewById(R.id.cIIIiconDiagnosis);
        mLyDiagnosisValue = findViewById(R.id.cIIIlyDiagnosisValue);

        mLyPrescription = findViewById(R.id.cIIIlyPrescription);
        mLyPrescription.setOnClickListener(this);
        mIconPrescription = findViewById(R.id.cIIIiconPrescription);
        mLyPrescriptionValue = findViewById(R.id.cIIIlyPrescriptionValue);
        mInputWeightGenPhy = findViewById(R.id.cIIIinputWeightGenPhy);
        mInputHeightGenPhy = findViewById(R.id.cIIIinputHeightGenPhy);
        mInputBMIGenPhy = findViewById(R.id.cIIIinputBMIGenPhy);
        mInputPulseGenPhy = findViewById(R.id.cIIIinputPulseGenPhy);
        mInputBPGenPhy = findViewById(R.id.cIIIinputBPGenPhy);
        mDropPallorGenPhy = findViewById(R.id.cIIIdropPallorGenPhy);
        mDropJaundiceGenPhy = findViewById(R.id.cIIIdropJaundiceGenPhy);

        mDropPOedemaGenPhy = findViewById(R.id.cIIIdropPOedemaGenPhy);
        mDropChestSysExa = findViewById(R.id.cIIIdropChestSysExa);
        mDropCVSSysExa = findViewById(R.id.cIIIdropCVSSysExa);
        mDropPAbdomenSysExa = findViewById(R.id.cIIIdropPAbdomenSysExa);
        mDropCNSSysExa = findViewById(R.id.cIIIdropCNSSysExa);
        mDropHearingSysExa = findViewById(R.id.cIIIdropHearingSysExa);
        mInputRightEyeSysExa = findViewById(R.id.cIIIinputRightEyeSysExa);
        mInputLeftEyeSysExa = findViewById(R.id.cIIIinputLeftEyeSysExa);
        mDropColourBlindnesSysExa = findViewById(R.id.cIIIdropColourBlindnesSysExa);
        mDropDentalSysExa = findViewById(R.id.cIIIdropDentalSysExa);
        mDropGenitalSysExa = findViewById(R.id.cIIIdropGenitalSysExa);
        mChkHBMandatoryInvest = findViewById(R.id.cIIIchkHBMandatoryInvest);
        mInputHBMandatoryInvest = findViewById(R.id.cIIIinputHBMandatoryInvest);
        mChkTLCMandatoryInvest = findViewById(R.id.cIIIchkTLCMandatoryInvest);
        mInputTLCMandatoryInvest = findViewById(R.id.cIIIinputTLCMandatoryInvest);
        mChkDLCMandatoryInvest = findViewById(R.id.cIIIchkDLCMandatoryInvest);
        mInputDLCNeutrophilsMandatoryInvest = findViewById(R.id.cIIIinputDLCNeutrophilsMandatoryInvest);
        mInputLymphocytesMandatoryInvest = findViewById(R.id.cIIIinputLymphocytesMandatoryInvest);
        mInputDLCMonocytesMandatoryInvest = findViewById(R.id.cIIIinputDLCMonocytesMandatoryInvest);
        mInputDLCEosinophilsMandatoryInvest = findViewById(R.id.cIIIinputDLCEosinophilsMandatoryInvest);
        mInputDLCBasophilsMandatoryInvest = findViewById(R.id.cIIIinputDLCBasophilsMandatoryInvest);
        mChkPackedCellMandatoryInvest = findViewById(R.id.cIIIchkPackedCellMandatoryInvest);
        mInputPackedCellMandatoryInvest = findViewById(R.id.cIIIinputPackedCellMandatoryInvest);
        mChkCorpuscularMandatoryInvest = findViewById(R.id.cIIIchkCorpuscularMandatoryInvest);
        mInputCorpuscularMandatoryInvest = findViewById(R.id.cIIIinputCorpuscularMandatoryInvest);
        mChkCorpuscularHBMandatoryInvest = findViewById(R.id.cIIIchkCorpuscularHBMandatoryInvest);
        mInputCorpuscularHBMandatoryInvest = findViewById(R.id.cIIIinputCorpuscularHBMandatoryInvest);
        mChkHBConcentrationMandatoryInvest = findViewById(R.id.cIIIchkHBConcentrationMandatoryInvest);
        mInputHBConcentrationMandatoryInvest = findViewById(R.id.cIIIinputHBConcentrationMandatoryInvest);
        mChkPlateletMandatoryInvest = findViewById(R.id.cIIIchkPlateletMandatoryInvest);
        mInputPlateletMandatoryInvest = findViewById(R.id.cIIIinputPlateletMandatoryInvest);
        mChkRDWMandatoryInvest = findViewById(R.id.cIIIchkRDWMandatoryInvest);
        mInputRDWMandatoryInvest = findViewById(R.id.cIIIinputRDWMandatoryInvest);
        mChkRDWSDMandatoryInvest = findViewById(R.id.cIIIchkRDWSDMandatoryInvest);
        mInputRDWSDMandatoryInvest = findViewById(R.id.cIIIinputRDWSDMandatoryInvest);
        mChkRbcCountMandatoryInvest = findViewById(R.id.cIIIchkRbcCountMandatoryInvest);
        mInputRbcCountMandatoryInvest = findViewById(R.id.cIIIinputRbcCountMandatoryInvest);
        mChkRBSMandatoryInvest = findViewById(R.id.cIIIchkRBSMandatoryInvest);
        mInputRBSMandatoryInvest = findViewById(R.id.cIIIinputRBSMandatoryInvest);

        mChkUrineMandatoryInvest = findViewById(R.id.cIIIchkUrineMandatoryInvest);
        mInputUrineMandatoryInvest = findViewById(R.id.cIIIinputUrineMandatoryInvest);
        mChkAdvisedMandatoryInvest = findViewById(R.id.cIIIchkAdvisedMandatoryInvest);
        mInputAdvisedMandatoryInvest = findViewById(R.id.cIIIinputAdvisedMandatoryInvest);
        multiSpinner = (MultiSpinner) findViewById(R.id.cIIIdropDiagnosis);
        mSetSpinnerData(Arrays.asList(getResources().getStringArray(R.array.arr_diagnosis_cat_1)));

        mChkDAlreadyKnown = findViewById(R.id.cIIIchkDAlreadyKnown);
        mInputPrescription = findViewById(R.id.cIIIinputPrescription);
        mCIIlyHistoryEx =findViewById(R.id.cIIlyHistoryEx);
        iconHistory =findViewById(R.id.cIIiconHistory);
        lyHistoryExValue =findViewById(R.id.cIIlyHistoryExValue);
        mCIIdropHosAdminHistory =findViewById(R.id.cIIdropHosAdminHistory);
        mCIIdropFamilyHistory =findViewById(R.id.cIIdropFamilyHistory);
        mCIIdropImmunizationHistory =findViewById(R.id.cIIdropImmunizationHistory);
        mCIIdropTbPatientHistory =findViewById(R.id.cIIdropTbPatientHistory);
        mCIIdropDewormingPatientHistory =findViewById(R.id.cIIdropDewormingPatientHistory);
        mCIIIdropConjectionAnGenPhy =findViewById(R.id.cIIIdropConjectionAnGenPhy);
        mCIIIdropSkinTugaGenPhy =findViewById(R.id.cIIIdropSkinTugaGenPhy);
        mCIIIdropSkinLeGenPhy =findViewById(R.id.cIIIdropSkinLeGenPhy);
        mCIIIdropWristWGenPhy =findViewById(R.id.cIIIdropWristWGenPhy);
        mCIIIdropVitDefGenPhy =findViewById(R.id.cIIIdropVitDefGenPhy);
        mCIIIdropGondalExGenPhy =findViewById(R.id.cIIIdropGondalExGenPhy);
        mCIIIdropEarDisGenPhy =findViewById(R.id.cIIIdropEarDisGenPhy);
        mCIIIdropSpeechGenPhy =findViewById(R.id.cIIIdropSpeechGenPhy);
        mCIIIdropIQGenPhy =findViewById(R.id.cIIIdropIQGenPhy);
        cIIIdropCynosGenPhy=findViewById(R.id.cIIIdropCynosGenPhy);
        cIIIinputRRGenPhy=findViewById(R.id.cIIIinputRRGenPhy);
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
        if (view.getId() == R.id.cIIIlyGenPhysicalEx) {
            if (mClickOne) {
                mShowHideLayouts(0);
                mClickOne = false;
            } else {
                mShowHideLayoutsAll();
                mClickOne = true;
            }
        }
        if (view.getId() == R.id.cIIIlySysExa) {
            if (mClickTwo) {
                mShowHideLayouts(1);
                mClickTwo = false;
            } else {
                mShowHideLayoutsAll();
                mClickTwo = true;
            }
        }
        if (view.getId() == R.id.cIIIlyMandatoryInvest) {
            if (mClickThree) {
                mShowHideLayouts(2);
                mClickThree = false;
            } else {
                mShowHideLayoutsAll();
                mClickThree = true;
            }
        }
        if (view.getId() == R.id.cIIIlyDiagnosis) {
            if (mClickFour) {
                mShowHideLayouts(3);
                mClickFour = false;
            } else {
                mShowHideLayoutsAll();
                mClickFour = true;
            }
        }
        if (view.getId() == R.id.cIIIlyPrescription) {
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
        if (view.getId() == R.id.cIIIsubmitPatientInput) {
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
            if (isNetworkAvailable()) {
                createProgressBar(R.id.cIIIrelMain);
                APIInterface apiInterface = ApiClient.getClientAuthenticationWithAuth(preferences.getString(SharedParams.AUTH_TOKEN,"")).create(APIInterface.class);
                PostDataForCategoryIIIRequest request = new PostDataForCategoryIIIRequest();
                request.setCreatedBy(doctorName);
                request.setCreatedDate(getDateToSend());
                request.setPatientId(memberData.getMemberid());
                request.setDistrict(preferences.getString(SharedParams.DISTRICT, null));
                request.setFacility(sharedPreferences.getString(SharedParams.FACTYPE, "") + "/" + sharedPreferences.getString(SharedParams.FACILITY, ""));
                request.setCategory("3");
                request.setUserId(preferences.getString(SharedParams.SUB, null));

                //step 1
                request.setHistroyAdmIllness(mCIIdropHosAdminHistory.getSelectedItem() + "");
                request.setHistoryFamily(mCIIdropFamilyHistory.getSelectedItem() + "");
                request.setImmunStatus(mCIIdropImmunizationHistory.getSelectedItem() + "");
                request.setContactWithTB(mCIIdropTbPatientHistory.getSelectedItem() + "");
                request.setHistoryDeworming(mCIIdropDewormingPatientHistory.getSelectedItem() + "");


                //step 2
                request.setWeight(mInputWeightGenPhy.getText().toString());
                request.setHeight(mInputHeightGenPhy.getText().toString());
                request.setBmi(mInputBMIGenPhy.getText().toString() + "");
                request.setPallor(mDropPallorGenPhy.getSelectedItem() + "");
                request.setJaundice(mDropJaundiceGenPhy.getSelectedItem() + "");
                request.setCyanosis(cIIIdropCynosGenPhy.getSelectedItem() + "");
                request.setPulseRate(mInputPulseGenPhy.getText().toString());
                request.setRespiratoryRate(cIIIinputRRGenPhy.getText().toString());
                request.setBloodPressure(mInputBPGenPhy.getText().toString());
                request.setPedalOedema(mDropPOedemaGenPhy.getSelectedItem() + "");
                request.setCongenitalAnomalies(mCIIIdropConjectionAnGenPhy.getSelectedItem() + "");
                request.setSkinTurgor(mCIIIdropSkinTugaGenPhy.getSelectedItem() + "");
                request.setSkinLesions(mCIIIdropSkinLeGenPhy.getSelectedItem() + "");
                request.setWristWidening(mCIIIdropWristWGenPhy.getSelectedItem() + "");
                request.setVitADeficiency(mCIIIdropVitDefGenPhy.getSelectedItem() + "");
                request.setGonadalExamination(mCIIIdropGondalExGenPhy.getSelectedItem() + "");
                request.setEarDischarge(mCIIIdropEarDisGenPhy.getSelectedItem() + "");
                request.setSpeech(mCIIIdropSpeechGenPhy.getSelectedItem() + "");
                request.setIq(mCIIIdropIQGenPhy.getSelectedItem() + "");


                // Step 3
                request.setChest(mDropChestSysExa.getSelectedItem() + "");
                request.setCvs(mDropCVSSysExa.getSelectedItem() + "");
                request.setPerAbdomen(mDropPAbdomenSysExa.getSelectedItem() + "");
                request.setCns(mDropCNSSysExa.getSelectedItem() + "");
                request.setHearing(mDropHearingSysExa.getSelectedItem() + "");
                request.setRightEye(mInputRightEyeSysExa.getText().toString() + "");
                request.setLeftEye(mInputLeftEyeSysExa.getText().toString() + "");
                request.setColorVision(mDropColourBlindnesSysExa.getSelectedItem() + "");
                request.setDentalExaminationAbnormality(mDropDentalSysExa.getSelectedItem() + "");
                request.setGenitalExamination(mDropGenitalSysExa.getSelectedItem() + "");

                // step 4
                request.sethB(mInputHBMandatoryInvest.getText().toString());
                request.setTlc(mInputTLCMandatoryInvest.getText().toString());
                request.setNeutrophils(mInputDLCNeutrophilsMandatoryInvest.getText().toString());
                request.setLymphocytes(mInputLymphocytesMandatoryInvest.getText().toString());
                request.setMonocytes(mInputDLCMonocytesMandatoryInvest.getText().toString());
                request.setEosinophils(mInputDLCEosinophilsMandatoryInvest.getText().toString());
                request.setBasophils(mInputDLCBasophilsMandatoryInvest.getText().toString());
                request.setPackedCellVolume(mInputPackedCellMandatoryInvest.getText().toString());
                request.setMeanCorpusVolume(mInputCorpuscularMandatoryInvest.getText().toString());
                request.setMeanCorpusHemoglobin(mInputCorpuscularHBMandatoryInvest.getText().toString());
                request.setMeanCorpusHemoglobinConcentration(mInputHBConcentrationMandatoryInvest.getText().toString());
                request.setPlatletCount(mInputPlateletMandatoryInvest.getText().toString());
                request.setRdwCv(mInputRDWMandatoryInvest.getText().toString());
                request.setRdwSd(mInputRDWSDMandatoryInvest.getText().toString());
                request.setRbcCount(mInputRbcCountMandatoryInvest.getText().toString());
                request.setRbs(mInputRBSMandatoryInvest.getText().toString());
                request.setUrineRoutineExamination(mInputUrineMandatoryInvest.getText().toString());
                request.setRelevantInvestigation(mInputAdvisedMandatoryInvest.getText().toString());

                //step 5
                request.setDiagnosed(selectedDiagnosis);
                if (mChkDAlreadyKnown.isChecked()) {
                    request.setAlreadyKnown("yes");
                } else {
                    request.setAlreadyKnown("no");
                }
                request.setPrescription(mInputPrescription.getText().toString());
                Log.e(" request ", "" + request.toString());
                Call<SubmitPatientData> call = apiInterface.submitDataForSurveyCatIII(request);
                call.enqueue(new Callback<SubmitPatientData>() {
                    @Override
                    public void onResponse(Call<SubmitPatientData> call, Response<SubmitPatientData> response) {
                        try {
                            if (response.isSuccessful()) {
                                mShowToast("Submitted Successfully with reference id " + response.body().getRefernceId());
                                disableProgressBar();
                            } else {
                                disableProgressBar();
                                mShowToast(" Error : " + response.errorBody().string());

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
            } else {
                mShowToast(getString(R.string.no_internet));
            }

        } catch (Exception ee) {
            Log.e(" Exception ", "" + ee.getMessage());
        }

    }
}
