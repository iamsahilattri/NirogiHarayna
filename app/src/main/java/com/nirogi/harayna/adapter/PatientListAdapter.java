package com.nirogi.harayna.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.nirogi.harayna.R;
import com.nirogi.harayna.model.response.PatientListModelResponse;

import java.util.ArrayList;

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.PPPViewHolder> {
    private final Activity mContext;
    private final ArrayList<PatientListModelResponse> dataList;


    public PatientListAdapter(Activity mContext, ArrayList<PatientListModelResponse> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;

    }

    @NonNull
    @Override
    public PPPViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_patient_list, parent, false);
        PPPViewHolder viewHolder = new PPPViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull PPPViewHolder viewHolder, int position) {
        PatientListModelResponse bean = dataList.get(position);
        viewHolder.patientPPPID.setText(bean.getPppid());
        viewHolder.patientName.setText(bean.getFirstname() + " " + bean.getLastname());
        viewHolder.patientHusFather.setText(bean.getFatherhusbandfirstname() + " " + bean.getFatherhusbandlastname());
        viewHolder.patientAge.setText(bean.getAge()+"");
        viewHolder.patientGender.setText(bean.getGender());
        viewHolder.patientMobile.setText(bean.getMobileno()+"");
        viewHolder.patientAddress.setText(bean.getAddress());
        viewHolder.patientDistrict.setText(bean.getDistrict());
        viewHolder.patientIncome.setText(bean.getIncome()+"");


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

 

    public static class PPPViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mainLy;
        private final AppCompatTextView patientPPPID;
        private final AppCompatTextView patientName;
        private final AppCompatTextView patientHusFather;
        private final AppCompatTextView patientGender;
        private final AppCompatTextView patientAge;
        private final AppCompatTextView patientMobile;
        private final AppCompatTextView patientAddress;
        private final AppCompatTextView patientDistrict;
        private final AppCompatTextView patientIncome;

        public PPPViewHolder(View itemView) {
            super(itemView);
            mainLy = itemView.findViewById(R.id.mainLy);
            patientPPPID =itemView.findViewById(R.id.patientPPPID);
            patientName =itemView.findViewById(R.id.patientName);
            patientHusFather =itemView.findViewById(R.id.patientHusFather);
            patientGender =itemView.findViewById(R.id.patientGender);
            patientAge =itemView.findViewById(R.id.patientAge);
            patientMobile =itemView.findViewById(R.id.patientMobile);
            patientAddress =itemView.findViewById(R.id.patientAddress);
            patientDistrict =itemView.findViewById(R.id.patientDistrict);
            patientIncome =itemView.findViewById(R.id.patientIncome);
        }
    }


}
