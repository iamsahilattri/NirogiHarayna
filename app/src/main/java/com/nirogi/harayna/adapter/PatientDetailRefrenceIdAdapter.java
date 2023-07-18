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
import com.nirogi.harayna.model.response.ReferenceIdResponse;

import java.util.ArrayList;

public class PatientDetailRefrenceIdAdapter extends RecyclerView.Adapter<PatientDetailRefrenceIdAdapter.PPPViewHolder> {
    private final Activity mContext;
    private final ArrayList<ReferenceIdResponse> dataList;


    public PatientDetailRefrenceIdAdapter(Activity mContext, ArrayList<ReferenceIdResponse> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;

    }

    @NonNull
    @Override
    public PPPViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_patient_list_ref_id, parent, false);
        return new PPPViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PPPViewHolder viewHolder, int position) {
        ReferenceIdResponse bean = dataList.get(position);
        viewHolder.patientRefID.setText(bean.getReferenceid());
        viewHolder.patientDistrict.setText(bean.getDistrict());
        viewHolder.patientFacility.setText(bean.getFacility()+"");


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

 

    public static class PPPViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mainLy;
        private final AppCompatTextView patientRefID;
        private final AppCompatTextView patientDistrict;
        private final AppCompatTextView patientFacility;

        public PPPViewHolder(View itemView) {
            super(itemView);
            mainLy = itemView.findViewById(R.id.mainLy);
            patientRefID =itemView.findViewById(R.id.patientRefID);
            patientDistrict =itemView.findViewById(R.id.patientDistrict);
            patientFacility =itemView.findViewById(R.id.patientFacility);

        }
    }


}
