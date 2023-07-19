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
import com.nirogi.harayna.model.request.PostDataForCategoryVRequest;
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
    private String selectedDiagnosis;
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
    private AppCompatEditText mCVinputUrineMandatoryInvest;
    private AppCompatCheckBox mCVchkVIAPAPMandatoryInvest;
    private AppCompatEditText mCVinputVIAPAPMandatoryInvest;
    private AppCompatCheckBox mCVchkAdvisedMandatoryInvest;
    private AppCompatEditText mCVinputAdvisedMandatoryInvest;
    private MultiSpinner mCVdropDiagnosis;
    private AppCompatCheckBox mCVchkDAlreadyKnown;
    private AppCompatEditText mCVinputPrescription;
    private LinearLayout mCVsubmitPatientInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = NIROGI.getInstance().getPreferences();
        setContentView(R.layout.activity_patient_input_cat_v);
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
                    mSetBackToolbar("Patient Details", true, "Category IV (18-40 Years)");
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
        mCVsubmitPatientInput =  findViewById(R.id.cVsubmitPatientInput);
        mCVsubmitPatientInput.setOnClickListener(this);
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
                request.setLymphocytes(mCVdropLymphadenopathyGenPhy.getSelectedItem() + "");
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
                Log.e(" request ", "" + request.toString());
                Call<SubmitPatientData> call = apiInterface.submitDataForSurveyCatV(request);
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
