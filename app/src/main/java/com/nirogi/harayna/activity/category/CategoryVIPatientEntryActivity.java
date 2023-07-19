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
import com.nirogi.harayna.model.request.PostDataForCategoryVIRequest;
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
    private String selectedDiagnosis;
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
    private AppCompatEditText mCVIinputCreatinineMandatoryInvest;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = NIROGI.getInstance().getPreferences();
        setContentView(R.layout.activity_patient_input_cat_vi);
        initView();
        mSetValuesToViews();
        initDataToView();
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
                memberData = (PatientListModelResponse) getIntent().getSerializableExtra("memberData");
                if (memberData != null) {
                    mSetBackToolbar("Patient Details", true, "Category VI (Over 60 Years)");
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
            if (mClickFive) {
                mShowHideLayouts(5);
                mClickSix = false;
            } else {
                mShowHideLayoutsAll();
                mClickSix = true;
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
                                mShowToast("Submitted Successfully with reference id "+response.body().getRefernceId());
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
            } else {
                mShowToast(getString(R.string.no_internet));
            }

        } catch (Exception ee) {
            Log.e(" Exception ", "" + ee.getMessage());
        }

    }
}
