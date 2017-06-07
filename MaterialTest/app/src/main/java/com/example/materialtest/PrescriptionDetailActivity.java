package com.example.materialtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PrescriptionDetailActivity extends AppCompatActivity {

    LinearLayoutManager mLinearLayoutManager=new LinearLayoutManager(this);
    PrescriptionDetailManager mPrescriptionDetailManager = new PrescriptionDetailManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_detail);

        RecyclerView mRecyclerView = (RecyclerView) this.findViewById(R.id.prescription_detail_recyclerview);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(new PrescriptionDetailAdapter(mPrescriptionDetailManager.getPrescriptionDetailList()));
    }

    private class PrescriptionDetailHolder extends RecyclerView.ViewHolder {
       private  TextView prescriptionId;
       private TextView prescriptionName;
        public PrescriptionDetailHolder(View itemView) {
            super(itemView);
            prescriptionId=(TextView)itemView.findViewById(R.id.prescription_detail_id);
            prescriptionName=(TextView)itemView.findViewById(R.id.prescription_detail_name);
        }
    }

    public class PrescriptionDetailAdapter extends RecyclerView.Adapter<PrescriptionDetailHolder>{

        private List<PrescriptionDetail> mPrescriptionDetailList;
        public PrescriptionDetailAdapter(List<PrescriptionDetail> prescriptionDetails){
            mPrescriptionDetailList=prescriptionDetails;
        }
        @Override
        public PrescriptionDetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.prescription_detail_item,parent,false);
            return new PrescriptionDetailHolder(view);
        }

        @Override
        public void onBindViewHolder(PrescriptionDetailHolder holder, int position) {
            PrescriptionDetail p=mPrescriptionDetailList.get(position);
            holder.prescriptionId.setText(String.valueOf(p.getId()));
            holder.prescriptionName.setText(p.getName());

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }


}
