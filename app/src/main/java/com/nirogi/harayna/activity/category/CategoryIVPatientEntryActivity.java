package com.nirogi.harayna.activity.category;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.nirogi.harayna.model.request.PostDataForCategoryIVRequest;
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

public class CategoryIVPatientEntryActivity extends BaseActivity implements View.OnClickListener {


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
    private AppCompatEditText mCIVinputWeightGenPhy;
    private AppCompatEditText mCIVinputHeightGenPhy;
    private AppCompatEditText mCIVinputBMIGenPhy;
    private AppCompatEditText mCIVinputPulseGenPhy;
    private AppCompatEditText mCIVinputBPGenPhy;
    private AppCompatSpinner mCIVdropPallorGenPhy;
    private AppCompatSpinner mCIVdropJaundiceGenPhy;
    private AppCompatSpinner mCIVdropClubbingGenPhy;
    private AppCompatSpinner mCIVdropLymphadenopathyGenPhy;
    private AppCompatSpinner mCIVdropPOedemaGenPhy;
    private AppCompatSpinner mCIVdropChestSysExa;
    private AppCompatSpinner mCIVdropCVSSysExa;
    private AppCompatSpinner mCIVdropPAbdomenSysExa;
    private AppCompatSpinner mCIVdropCNSSysExa;
    private AppCompatSpinner mCIVdropHearingSysExa;
    private AppCompatEditText mCIVinputRightEyeSysExa;
    private AppCompatEditText mCIVinputLeftEyeSysExa;
    private AppCompatSpinner mCIVdropColourBlindnesSysExa;
    private AppCompatSpinner mCIVdropDentalSysExa;
    private AppCompatSpinner mCIVdropGenitalSysExa;
    private AppCompatEditText mCIVinputBreastSysExa;
    private AppCompatCheckBox mCIVchkHBMandatoryInvest;
    private AppCompatEditText mCIVinputHBMandatoryInvest;
    private AppCompatCheckBox mCIVchkTLCMandatoryInvest;
    private AppCompatEditText mCIVinputTLCMandatoryInvest;
    private AppCompatCheckBox mCIVchkDLCMandatoryInvest;
    private AppCompatEditText mCIVinputDLCNeutrophilsMandatoryInvest;
    private AppCompatEditText mCIVinputLymphocytesMandatoryInvest;
    private AppCompatEditText mCIVinputDLCMonocytesMandatoryInvest;
    private AppCompatEditText mCIVinputDLCEosinophilsMandatoryInvest;
    private AppCompatEditText mCIVinputDLCBasophilsMandatoryInvest;
    private AppCompatCheckBox mCIVchkPackedCellMandatoryInvest;
    private AppCompatEditText mCIVinputPackedCellMandatoryInvest;
    private AppCompatCheckBox mCIVchkCorpuscularMandatoryInvest;
    private AppCompatEditText mCIVinputCorpuscularMandatoryInvest;
    private AppCompatCheckBox mCIVchkCorpuscularHBMandatoryInvest;
    private AppCompatEditText mCIVinputCorpuscularHBMandatoryInvest;
    private AppCompatCheckBox mCIVchkHBConcentrationMandatoryInvest;
    private AppCompatEditText mCIVinputHBConcentrationMandatoryInvest;
    private AppCompatCheckBox mCIVchkPlateletMandatoryInvest;
    private AppCompatEditText mCIVinputPlateletMandatoryInvest;
    private AppCompatCheckBox mCIVchkRDWMandatoryInvest;
    private AppCompatEditText mCIVinputRDWMandatoryInvest;
    private AppCompatCheckBox mCIVchkRDWSDMandatoryInvest;
    private AppCompatEditText mCIVinputRDWSDMandatoryInvest;
    private AppCompatCheckBox mCIVchkRbcCountMandatoryInvest;
    private AppCompatEditText mCIVinputRbcCountMandatoryInvest;
    private AppCompatCheckBox mCIVchkRBSMandatoryInvest;
    private AppCompatEditText mCIVinputRBSMandatoryInvest;
    private AppCompatCheckBox mCIVchkCholesterolMandatoryInvest;
    private AppCompatEditText mCIVinputCholesterolMandatoryInvest;
    private AppCompatCheckBox mCIVchkBloodUreaMandatoryInvest;
    private AppCompatEditText mCIVinputBloodUreaMandatoryInvest;
    private AppCompatCheckBox mCIVchkCreatinineMandatoryInvest;
    private AppCompatEditText mCIVinputCreatinineMandatoryInvest;
    private AppCompatCheckBox mCIVchkUrineMandatoryInvest;
    private AppCompatEditText mCIVinputUrineMandatoryInvest;
    private AppCompatCheckBox mCIVchkAdvisedMandatoryInvest;
    private AppCompatEditText mCIVinputAdvisedMandatoryInvest;
    private AppCompatSpinner mCIVdropDiagnosis;
    private AppCompatCheckBox mCIVchkDAlreadyKnown;
    private AppCompatEditText mCIVinputPrescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences=NIROGI.getInstance().getPreferences();
        setContentView(R.layout.activity_patient_input_cat_iv);
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
        mTxtPatientPPPID = findViewById(R.id.cIVtxtPatientPPPID);
        mTxtPatientName = findViewById(R.id.cIVtxtPatientName);
        mTxtPatientGenderAge = findViewById(R.id.cIVtxtPatientGenderAge);
        mTxtPatientMobile = findViewById(R.id.cIVtxtPatientMobile);
        mTxtPatientAddress = findViewById(R.id.cIVtxtPatientAddress);
        mTxtPatientDistrict = findViewById(R.id.cIVtxtPatientDistrict);
        mTxtFacilityName = findViewById(R.id.cIVtxtFacilityName);
        mTxtFacilityIncharge = findViewById(R.id.cIVtxtFacilityIncharge);
        mTxtDate = findViewById(R.id.cIVtxtDate);
        LinearLayout mLyGenPhysicalEx = findViewById(R.id.cIVlyGenPhysicalEx);
        mLyGenPhysicalEx.setOnClickListener(this);
        mIconGenPhy = findViewById(R.id.cIViconGenPhy);
        mLyGenPhysicalxValue = findViewById(R.id.cIVlyGenPhysicalxValue);

        LinearLayout mLySysExa = findViewById(R.id.cIVlySysExa);
        mLySysExa.setOnClickListener(this);
        mIconSysExa = findViewById(R.id.cIViconSysExa);
        mLySysExaValue = findViewById(R.id.cIVlySysExaValue);

        LinearLayout mLyMandatoryInvest = findViewById(R.id.cIVlyMandatoryInvest);
        mLyMandatoryInvest.setOnClickListener(this);
        mIconMandatoryInvest = findViewById(R.id.cIViconMandatoryInvest);
        mLyMandatoryInvestValue = findViewById(R.id.cIVlyMandatoryInvestValue);

        LinearLayout mLyDiagnosis = findViewById(R.id.cIVlyDiagnosis);
        mLyDiagnosis.setOnClickListener(this);
        mIconDiagnosis = findViewById(R.id.cIViconDiagnosis);
        mLyDiagnosisValue = findViewById(R.id.cIVlyDiagnosisValue);

        LinearLayout mLyPrescription = findViewById(R.id.cIVlyPrescription);
        mLyPrescription.setOnClickListener(this);
        mIconPrescription = findViewById(R.id.cIViconPrescription);
        mLyPrescriptionValue = findViewById(R.id.cIVlyPrescriptionValue);
        mCIVinputWeightGenPhy =findViewById(R.id.cIVinputWeightGenPhy);
        mCIVinputHeightGenPhy =findViewById(R.id.cIVinputHeightGenPhy);
        mCIVinputBMIGenPhy =findViewById(R.id.cIVinputBMIGenPhy);
        mCIVinputPulseGenPhy =findViewById(R.id.cIVinputPulseGenPhy);
        mCIVinputBPGenPhy =findViewById(R.id.cIVinputBPGenPhy);
        mCIVdropPallorGenPhy =findViewById(R.id.cIVdropPallorGenPhy);
        mCIVdropJaundiceGenPhy =findViewById(R.id.cIVdropJaundiceGenPhy);
        mCIVdropClubbingGenPhy =findViewById(R.id.cIVdropClubbingGenPhy);
        mCIVdropLymphadenopathyGenPhy =findViewById(R.id.cIVdropLymphadenopathyGenPhy);
        mCIVdropPOedemaGenPhy =findViewById(R.id.cIVdropPOedemaGenPhy);
        mCIVdropChestSysExa =findViewById(R.id.cIVdropChestSysExa);
        mCIVdropCVSSysExa =findViewById(R.id.cIVdropCVSSysExa);
        mCIVdropPAbdomenSysExa =findViewById(R.id.cIVdropPAbdomenSysExa);
        mCIVdropCNSSysExa =findViewById(R.id.cIVdropCNSSysExa);
        mCIVdropHearingSysExa =findViewById(R.id.cIVdropHearingSysExa);
        mCIVinputRightEyeSysExa =findViewById(R.id.cIVinputRightEyeSysExa);
        mCIVinputLeftEyeSysExa =findViewById(R.id.cIVinputLeftEyeSysExa);
        mCIVdropColourBlindnesSysExa =findViewById(R.id.cIVdropColourBlindnesSysExa);
        mCIVdropDentalSysExa =findViewById(R.id.cIVdropDentalSysExa);
        mCIVdropGenitalSysExa =findViewById(R.id.cIVdropGenitalSysExa);
        mCIVinputBreastSysExa =findViewById(R.id.cIVinputBreastSysExa);
        mCIVchkHBMandatoryInvest =findViewById(R.id.cIVchkHBMandatoryInvest);
        mCIVinputHBMandatoryInvest =findViewById(R.id.cIVinputHBMandatoryInvest);
        mCIVchkTLCMandatoryInvest =findViewById(R.id.cIVchkTLCMandatoryInvest);
        mCIVinputTLCMandatoryInvest =findViewById(R.id.cIVinputTLCMandatoryInvest);
        mCIVchkDLCMandatoryInvest =findViewById(R.id.cIVchkDLCMandatoryInvest);
        mCIVinputDLCNeutrophilsMandatoryInvest =findViewById(R.id.cIVinputDLCNeutrophilsMandatoryInvest);
        mCIVinputLymphocytesMandatoryInvest =findViewById(R.id.cIVinputLymphocytesMandatoryInvest);
        mCIVinputDLCMonocytesMandatoryInvest =findViewById(R.id.cIVinputDLCMonocytesMandatoryInvest);
        mCIVinputDLCEosinophilsMandatoryInvest =findViewById(R.id.cIVinputDLCEosinophilsMandatoryInvest);
        mCIVinputDLCBasophilsMandatoryInvest =findViewById(R.id.cIVinputDLCBasophilsMandatoryInvest);
        mCIVchkPackedCellMandatoryInvest =findViewById(R.id.cIVchkPackedCellMandatoryInvest);
        mCIVinputPackedCellMandatoryInvest =findViewById(R.id.cIVinputPackedCellMandatoryInvest);
        mCIVchkCorpuscularMandatoryInvest =findViewById(R.id.cIVchkCorpuscularMandatoryInvest);
        mCIVinputCorpuscularMandatoryInvest =findViewById(R.id.cIVinputCorpuscularMandatoryInvest);
        mCIVchkCorpuscularHBMandatoryInvest =findViewById(R.id.cIVchkCorpuscularHBMandatoryInvest);
        mCIVinputCorpuscularHBMandatoryInvest =findViewById(R.id.cIVinputCorpuscularHBMandatoryInvest);
        mCIVchkHBConcentrationMandatoryInvest =findViewById(R.id.cIVchkHBConcentrationMandatoryInvest);
        mCIVinputHBConcentrationMandatoryInvest =findViewById(R.id.cIVinputHBConcentrationMandatoryInvest);
        mCIVchkPlateletMandatoryInvest =findViewById(R.id.cIVchkPlateletMandatoryInvest);
        mCIVinputPlateletMandatoryInvest =findViewById(R.id.cIVinputPlateletMandatoryInvest);
        mCIVchkRDWMandatoryInvest =findViewById(R.id.cIVchkRDWMandatoryInvest);
        mCIVinputRDWMandatoryInvest =findViewById(R.id.cIVinputRDWMandatoryInvest);
        mCIVchkRDWSDMandatoryInvest =findViewById(R.id.cIVchkRDWSDMandatoryInvest);
        mCIVinputRDWSDMandatoryInvest =findViewById(R.id.cIVinputRDWSDMandatoryInvest);
        mCIVchkRbcCountMandatoryInvest =findViewById(R.id.cIVchkRbcCountMandatoryInvest);
        mCIVinputRbcCountMandatoryInvest =findViewById(R.id.cIVinputRbcCountMandatoryInvest);
        mCIVchkRBSMandatoryInvest =findViewById(R.id.cIVchkRBSMandatoryInvest);
        mCIVinputRBSMandatoryInvest =findViewById(R.id.cIVinputRBSMandatoryInvest);
        mCIVchkCholesterolMandatoryInvest =findViewById(R.id.cIVchkCholesterolMandatoryInvest);
        mCIVinputCholesterolMandatoryInvest =findViewById(R.id.cIVinputCholesterolMandatoryInvest);
        mCIVchkBloodUreaMandatoryInvest =findViewById(R.id.cIVchkBloodUreaMandatoryInvest);
        mCIVinputBloodUreaMandatoryInvest =findViewById(R.id.cIVinputBloodUreaMandatoryInvest);
        mCIVchkCreatinineMandatoryInvest =findViewById(R.id.cIVchkCreatinineMandatoryInvest);
        mCIVinputCreatinineMandatoryInvest =findViewById(R.id.cIVinputCreatinineMandatoryInvest);
        mCIVchkUrineMandatoryInvest =findViewById(R.id.cIVchkUrineMandatoryInvest);
        mCIVinputUrineMandatoryInvest =findViewById(R.id.cIVinputUrineMandatoryInvest);
        mCIVchkAdvisedMandatoryInvest =findViewById(R.id.cIVchkAdvisedMandatoryInvest);
        mCIVinputAdvisedMandatoryInvest =findViewById(R.id.cIVinputAdvisedMandatoryInvest);
        multiSpinner = findViewById(R.id.cIVdropDiagnosis);
        mSetSpinnerData(Arrays.asList(getResources().getStringArray(R.array.arr_diagnosis_cat_1)));
        mCIVchkDAlreadyKnown =findViewById(R.id.cIVchkDAlreadyKnown);
        mCIVinputPrescription =findViewById(R.id.cIVinputPrescription);
    }

    private void mSetValidationListners()
    {
        mCIVinputWeightGenPhy.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                if(charSequence.length() != 0)
                {
                    String inputString=mCIVinputWeightGenPhy.getText().toString();
                    int input=Integer.parseInt(inputString);
                    if(input >= 5 && input <= 120)
                    {
                        mCIVinputWeightGenPhy.setError("*should be between 5-120");
                    }
                }
            }
        });
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
        if (view.getId() == R.id.cIVlyGenPhysicalEx) {
            if (mClickOne) {
                mShowHideLayouts(0);
                mClickOne = false;
            } else {
                mShowHideLayoutsAll();
                mClickOne = true;
            }
        }
        if (view.getId() == R.id.cIVlySysExa) {
            if (mClickTwo) {
                mShowHideLayouts(1);
                mClickTwo = false;
            } else {
                mShowHideLayoutsAll();
                mClickTwo = true;
            }
        }
        if (view.getId() == R.id.cIVlyMandatoryInvest) {
            if (mClickThree) {
                mShowHideLayouts(2);
                mClickThree = false;
            } else {
                mShowHideLayoutsAll();
                mClickThree = true;
            }
        }
        if (view.getId() == R.id.cIVlyDiagnosis) {
            if (mClickFour) {
                mShowHideLayouts(3);
                mClickFour = false;
            } else {
                mShowHideLayoutsAll();
                mClickFour = true;
            }
        }
        if (view.getId() == R.id.cIVlyPrescription) {
            if (mClickFive) {
                mShowHideLayouts(4);
                mClickFive = false;
            } else {
                mShowHideLayoutsAll();
                mClickFive = true;
            }
        }
        if (view.getId() == R.id.cIVsubmitPatientInput) {
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
                createProgressBar(R.id.cIVrelMain);
                APIInterface apiInterface = ApiClient.getClientAuthenticationWithAuth(preferences.getString(SharedParams.AUTH_TOKEN,"")).create(APIInterface.class);
                PostDataForCategoryIVRequest request = new PostDataForCategoryIVRequest();
                request.setCreatedBy(doctorName);
                request.setCreatedDate(getDateToSend());
                request.setPatientId(memberData.getMemberid());
                request.setDistrict(preferences.getString(SharedParams.DISTRICT, null));
                request.setFacility(sharedPreferences.getString(SharedParams.FACTYPE, "") + "/" + sharedPreferences.getString(SharedParams.FACILITY, ""));
                request.setCategory("4");
                request.setUserId(preferences.getString(SharedParams.SUB, null));


                // Step 1
                request.setWeight(mCIVinputWeightGenPhy.getText().toString());
                request.setHeight(mCIVinputHeightGenPhy.getText().toString());
                request.setBmi(mCIVinputBMIGenPhy.getText().toString() + "");
                request.setPulseRate(mCIVinputPulseGenPhy.getText().toString());
                request.setBloodPressure(mCIVinputBPGenPhy.getText().toString());
                request.setPallor(mCIVdropPallorGenPhy.getSelectedItem() + "");
                request.setJaundiceCyanosis(mCIVdropJaundiceGenPhy.getSelectedItem() + "");
                request.setClubbing(mCIVdropClubbingGenPhy.getSelectedItem() + "");
                request.setLymphocytes(mCIVdropLymphadenopathyGenPhy.getSelectedItem() + "");
                request.setPedalOedema(mCIVdropPOedemaGenPhy.getSelectedItem() + "");

                // Step 2
                request.setChest(mCIVdropChestSysExa.getSelectedItem() + "");
                request.setCvs(mCIVdropCVSSysExa.getSelectedItem() + "");
                request.setPerAbdomen(mCIVdropPAbdomenSysExa.getSelectedItem() + "");
                request.setCns(mCIVdropCNSSysExa.getSelectedItem() + "");
                request.setHearing(mCIVdropHearingSysExa.getSelectedItem() + "");
                request.setRightEye(mCIVinputRightEyeSysExa.getText().toString()+"");
                request.setLeftEye(mCIVinputLeftEyeSysExa.getText().toString()+"");
                request.setColorVision(mCIVdropColourBlindnesSysExa.getSelectedItem()+"");
                request.setDentalExaminationAbnormality(mCIVdropDentalSysExa.getSelectedItem()+"");
                request.setGenitalExamination(mCIVdropGenitalSysExa.getSelectedItem() + "");
                request.setBreastExamination(mCIVinputBreastSysExa.getText().toString() + "");

                // step 3
                request.sethB(mCIVinputHBMandatoryInvest.getText().toString());
                request.setTlc(mCIVinputTLCMandatoryInvest.getText().toString());
                request.setNeutrophils(mCIVinputDLCNeutrophilsMandatoryInvest.getText().toString());
                request.setLymphocytes(mCIVinputLymphocytesMandatoryInvest.getText().toString());
                request.setMonocytes(mCIVinputDLCMonocytesMandatoryInvest.getText().toString());
                request.setEosinophils(mCIVinputDLCEosinophilsMandatoryInvest.getText().toString());
                request.setBasophils(mCIVinputDLCBasophilsMandatoryInvest.getText().toString());
                request.setPackedCellVolume(mCIVinputPackedCellMandatoryInvest.getText().toString());
                request.setMeanCorpusVolume(mCIVinputCorpuscularMandatoryInvest.getText().toString());
                request.setMeanCorpusHemoglobin(mCIVinputCorpuscularHBMandatoryInvest.getText().toString());
                request.setMeanCorpusHemoglobinConcentration(mCIVinputHBConcentrationMandatoryInvest.getText().toString());
                request.setPlatletCount(mCIVinputPlateletMandatoryInvest.getText().toString());
                request.setRdwCv(mCIVinputRDWMandatoryInvest.getText().toString());
                request.setRdwSd(mCIVinputRDWSDMandatoryInvest.getText().toString());
                request.setRbcCount(mCIVinputRbcCountMandatoryInvest.getText().toString());
                request.setRbs(mCIVinputRBSMandatoryInvest.getText().toString());
                request.setSerumCholestrol(mCIVinputCholesterolMandatoryInvest.getText().toString());
                request.setBloodUrea(mCIVinputBloodUreaMandatoryInvest.getText().toString());
                request.setSerumCreatinine(mCIVinputCreatinineMandatoryInvest.getText().toString());
                request.setUrineRoutineExamination(mCIVinputUrineMandatoryInvest.getText().toString());
                request.setRelevantInvestigation(mCIVinputAdvisedMandatoryInvest.getText().toString());
       

                //step 4
                request.setDiagnosed(selectedDiagnosis);
                if (mCIVchkDAlreadyKnown.isChecked()) {
                    request.setAlreadyKnown("yes");
                } else {
                    request.setAlreadyKnown("no");
                }
                //step 5
                request.setPrescription(mCIVinputPrescription.getText().toString());
                Log.e(" request ", "" + request.toString());
                Call<SubmitPatientData> call = apiInterface.submitDataForSurveyCatIV(request);
                call.enqueue(new Callback<SubmitPatientData>() {
                    @Override
                    public void onResponse(Call<SubmitPatientData> call, Response<SubmitPatientData> response) {
                        try {
                            if (response.isSuccessful()) {
                                mShowToast("Submitted Successfully with reference id "+response.body().getRefernceId());
                            } else {
                                mHandleApiErrorCode(response.code(),response.errorBody().string(), CategoryIVPatientEntryActivity.this);
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
