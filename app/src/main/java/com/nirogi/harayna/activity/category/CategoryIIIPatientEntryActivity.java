package com.nirogi.harayna.activity.category;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;

import com.nirogi.harayna.R;
import com.nirogi.harayna.activity.HomeActivity;
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

    private boolean mClickOne = true, mClickTwo = true, mClickThree = true, mClickFour = true, mClickFive = true, mClickSix = true;

    private MultiSpinner multiSpinner;
    private String selectedDiagnosis="";
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
    private CheckBox cIIIchkAllMandatoryInvest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = NIROGI.getInstance().getPreferences();
        setContentView(R.layout.activity_patient_input_cat_iii);
        initView();
        mSetValuesToViews();
        initDataToView();
        mSetValidationListeners();
        checkMandatoryAll();
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
        mIconGenPhy = findViewById(R.id.cIIIiconGenPhy);
        mLyGenPhysicalxValue = findViewById(R.id.cIIIlyGenPhysicalxValue);
        mIconSysExa = findViewById(R.id.cIIIiconSysExa);
        mLySysExaValue = findViewById(R.id.cIIIlySysExaValue);

        mIconMandatoryInvest = findViewById(R.id.cIIIiconMandatoryInvest);
        mLyMandatoryInvestValue = findViewById(R.id.cIIIlyMandatoryInvestValue);
        findViewById(R.id.cIIIlyGenPhysicalEx).setOnClickListener(this);
        findViewById(R.id.cIIIlySysExa).setOnClickListener(this);
        findViewById(R.id.cIIIlyDiagnosis).setOnClickListener(this);
        findViewById(R.id.cIIIlyMandatoryInvest).setOnClickListener(this);
        findViewById(R.id.cIIIlyPrescription).setOnClickListener(this);

        mIconDiagnosis = findViewById(R.id.cIIIiconDiagnosis);
        mLyDiagnosisValue = findViewById(R.id.cIIIlyDiagnosisValue);

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
        multiSpinner =  findViewById(R.id.cIIIdropDiagnosis);
        mSetSpinnerData(Arrays.asList(getResources().getStringArray(R.array.arr_diagnosis_cat_1)));

        mChkDAlreadyKnown = findViewById(R.id.cIIIchkDAlreadyKnown);
        mInputPrescription = findViewById(R.id.cIIIinputPrescription);
        findViewById(R.id.cIIIlyHistoryEx).setOnClickListener(this);
        iconHistory =findViewById(R.id.cIIIiconHistory);
        lyHistoryExValue =findViewById(R.id.cIIIlyHistoryExValue);
        mCIIdropHosAdminHistory =findViewById(R.id.cIIIdropHosAdminHistory);
        mCIIdropFamilyHistory =findViewById(R.id.cIIIdropFamilyHistory);
        mCIIdropImmunizationHistory =findViewById(R.id.cIIIdropImmunizationHistory);
        mCIIdropTbPatientHistory =findViewById(R.id.cIIIdropTbPatientHistory);
        mCIIdropDewormingPatientHistory =findViewById(R.id.cIIIdropDewormingPatientHistory);
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
        cIIIchkAllMandatoryInvest=findViewById(R.id.cIIIchkAllMandatoryInvest);
        findViewById(R.id.cIIIsubmitPatientInput).setOnClickListener(this);
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
        if (view.getId() == R.id.cIIIlyHistoryEx) {
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
//                postDataForCategories();
                mShowToast("POST");
            }

        }
    }

    private void checkMandatoryAll()
    {
        cIIIchkAllMandatoryInvest.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if(checked)
                {
                    mChkHBMandatoryInvest.setChecked(true);
                    mChkTLCMandatoryInvest.setChecked(true);
                    mChkDLCMandatoryInvest.setChecked(true);
                    mChkPackedCellMandatoryInvest.setChecked(true);
                    mChkCorpuscularMandatoryInvest.setChecked(true);
                    mChkCorpuscularHBMandatoryInvest.setChecked(true);
                    mChkHBConcentrationMandatoryInvest.setChecked(true);
                    mChkPlateletMandatoryInvest.setChecked(true);
                    mChkRDWMandatoryInvest.setChecked(true);
                    mChkRDWSDMandatoryInvest.setChecked(true);
                    mChkRbcCountMandatoryInvest.setChecked(true);
                    mChkRBSMandatoryInvest.setChecked(true);
                    mChkUrineMandatoryInvest.setChecked(true);
                    mChkAdvisedMandatoryInvest.setChecked(true);
                }else {
                    mChkHBMandatoryInvest.setChecked(false);
                    mChkTLCMandatoryInvest.setChecked(false);
                    mChkDLCMandatoryInvest.setChecked(false);
                    mChkPackedCellMandatoryInvest.setChecked(false);
                    mChkCorpuscularMandatoryInvest.setChecked(false);
                    mChkCorpuscularHBMandatoryInvest.setChecked(false);
                    mChkHBConcentrationMandatoryInvest.setChecked(false);
                    mChkPlateletMandatoryInvest.setChecked(false);
                    mChkRDWMandatoryInvest.setChecked(false);
                    mChkRDWSDMandatoryInvest.setChecked(false);
                    mChkRbcCountMandatoryInvest.setChecked(false);
                    mChkRBSMandatoryInvest.setChecked(false);
                    mChkUrineMandatoryInvest.setChecked(false);
                    mChkAdvisedMandatoryInvest.setChecked(false);
                }
            }
        });
    }

    private void updateBMI()
    {

        if(!TextUtils.isEmpty(mInputWeightGenPhy.getText())&& !TextUtils.isEmpty(mInputHeightGenPhy.getText()))
        {
            double weight = Double.parseDouble(mInputWeightGenPhy.getText().toString());
            double height = Double.parseDouble(mInputHeightGenPhy.getText().toString()) / 100.0;

            // Calculating BMI
            double calculatedBmi = weight / (height * height);
            Log.e(" calculatedBmi ",""+calculatedBmi);
            String formattedBmi = String.format("%.2f", calculatedBmi);
            mInputBMIGenPhy.setText(formattedBmi);
        }

    }
    private void mSetValidationListeners()
    {
        mInputWeightGenPhy.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() > 0)
                {
                    String inputString=mInputWeightGenPhy.getText().toString();

                    int input=Integer.parseInt(inputString);
                    if(!(input >= 5 && input <= 120))
                    {
                        mInputWeightGenPhy.setError("*should be between 5-120");
                    }
                    updateBMI();
                }
            }
        });
        mInputHeightGenPhy.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() > 0)
                {
                    String inputString=mInputHeightGenPhy.getText().toString();
                    int input=Integer.parseInt(inputString);
                    if(!(input >= 90 && input <= 215))
                    {
                        mInputHeightGenPhy.setError("*should be between 90-215");
                    }


                    updateBMI();
                }
            }
        });
        mInputPulseGenPhy.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() != 0)
                {
                    String inputString=mInputPulseGenPhy.getText().toString();
                    int input=Integer.parseInt(inputString);
                    if(!(input >= 30 && input <= 250))
                    {
                        mInputPulseGenPhy.setError("*should be between 30-250");
                    }
                }
            }
        });
        cIIIinputRRGenPhy.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() != 0)
                {
                    String inputString=cIIIinputRRGenPhy.getText().toString();
                    int input=Integer.parseInt(inputString);
                    if(!(input >= 5 && input <= 30))
                    {
                        mInputPulseGenPhy.setError("*should be between 5-30");
                    }
                }
            }
        });
        mInputBPGenPhy.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() != 0)
                {
                    String inputString=mInputBPGenPhy.getText().toString();
                    if(inputString.contains("/"))
                    {
                        String[] inputValues=inputString.split("/");
                        int inputOne=Integer.parseInt(inputValues[0]);
                        if(!(inputOne >= 40 && inputOne <= 300))
                        {
                            mInputBPGenPhy.setError("*should be like 40-300");
                        }
                        if(inputValues.length>1 && inputValues[1]!=null)
                        {
                            int inputTwo=Integer.parseInt(inputValues[1]);
                            if(!(inputTwo >= 40 && inputTwo <= 300))
                            {
                                mInputBPGenPhy.setError("*should be like 40-300");
                            }
                        }
                    }
                    else
                    {
                        int inputOne=Integer.parseInt(inputString);
                        if(!(inputOne >= 40 && inputOne <= 300))
                        {
                            mInputBPGenPhy.setError("*should be like (34/33)");
                        }
                    }

                }
            }
        });
    }

    private boolean validateDataToPost() {

        if (TextUtils.isEmpty(mInputWeightGenPhy.getText())) {

            mShowToast("Please enter Weight ");
            return false;
        }
        if (!TextUtils.isEmpty(mInputWeightGenPhy.getText())) {
            String inputString = mInputWeightGenPhy.getText().toString();
            int input = Integer.parseInt(inputString);
            if (!(input >= 5 && input <= 120)) {
                mShowToast("Weight should be between 5-120");
                return false;
            }
        }
        if (TextUtils.isEmpty(cIIIinputRRGenPhy.getText())) {
            mShowToast("Please enter Respiratory rate (per min) ");
            return false;
        }
        if (!TextUtils.isEmpty(cIIIinputRRGenPhy.getText())) {
            String inputString = cIIIinputRRGenPhy.getText().toString();
            int input = Integer.parseInt(inputString);
            if (!(input >= 5 && input <= 30)) {
                mShowToast("Respiratory rate (per min) should be between 5-30");
                return false;
            }
        }
        if (TextUtils.isEmpty(mInputHeightGenPhy.getText())) {
            mShowToast("Please enter Height ");
            return false;
        }
        if (!TextUtils.isEmpty(mInputHeightGenPhy.getText())) {
            String inputString = mInputHeightGenPhy.getText().toString();
            int input = Integer.parseInt(inputString);
            if (!(input >= 90 && input <= 215)) {
                mShowToast("Height should be between 90-215");
                return false;
            }
        }
        if (TextUtils.isEmpty(mInputPulseGenPhy.getText())) {
            mShowToast("Please enter Pulse(per min) ");
            return false;
        }
        if (!TextUtils.isEmpty(mInputPulseGenPhy.getText())) {
            String inputString = mInputPulseGenPhy.getText().toString();
            int input = Integer.parseInt(inputString);
            if (!(input >= 30 && input <= 250)) {
                mShowToast("Pulse(per min) should be between 30-250");
                return false;
            }
        }
        if (TextUtils.isEmpty(mInputBPGenPhy.getText())) {
            mShowToast("Please enter BP (mmHg) ");
            return false;
        }
        if (!TextUtils.isEmpty(mInputBPGenPhy.getText())) {
            String inputString = mInputBPGenPhy.getText().toString();
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
        if (TextUtils.isEmpty(mInputRightEyeSysExa.getText())) {
            mShowToast("Please enter Right eye - Refraction ");
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
        if (clicked == 0)
        {
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
        }
        else if (clicked == 1)
        {
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
        }
        else if (clicked == 2)
        {
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
        }
        else if (clicked == 3)
        {
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
        }
        else if (clicked == 4)
        {
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
        }
        else if (clicked == 5)
        {
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
                            } else {
                                mHandleApiErrorCode(response.code(),response.errorBody().string(), CategoryIIIPatientEntryActivity.this);
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
