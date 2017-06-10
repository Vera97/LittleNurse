package com.example.materialtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.example.materialtest.myPackage.Patient;
import com.example.materialtest.myPackage.Prescription;
import com.example.materialtest.myPackage.PrescriptionManager;

import java.util.List;

public class PrescriptionActivity extends AppCompatActivity {

    LinearLayoutManager mLinearLayoutManager=new LinearLayoutManager(this);
    PrescriptionManager mPrescriptionManager=new PrescriptionManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.prescription_recyclerview);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setAdapter(new PrescriptionAdapter(mPrescriptionManager.getPatientList()));

    }


    private class PrescriptionHolder extends RecyclerView.ViewHolder{
        private TextView mPrescriptionDate;
        private TextView mPrescriptionNum;
        public PrescriptionHolder(View itemView) {
            super(itemView);
            mPrescriptionDate=(TextView) itemView.findViewById(R.id.prescription_date);
            mPrescriptionNum=(TextView) itemView.findViewById(R.id.prescription_num);

        }
    }

        private class PrescriptionAdapter extends RecyclerView.Adapter<PrescriptionHolder>{
        private List<Prescription> mPrescriptionList;
        public PrescriptionAdapter(List<Prescription> prescriptions){
            mPrescriptionList=prescriptions;
        }

        @Override
        public PrescriptionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prescription_item,parent,false);
            final PrescriptionHolder holder = new PrescriptionHolder(view);
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int position = holder.getAdapterPosition();
                    Intent intent = new Intent(PrescriptionActivity.this, RecordActivity.class);
                    startActivity(intent);
                }
            });
            return new PrescriptionHolder(view);
        }

            @Override
        public void onBindViewHolder(PrescriptionHolder holder, int position) {
        Prescription p=mPrescriptionList.get(position);
            holder.mPrescriptionDate.setText(p.getPrescriptionDate());
            holder.mPrescriptionNum.setText(String.valueOf(p.getPrescriptionNum()));
        }

        @Override
        public int getItemCount() {
            return mPrescriptionList.size();
        }
    }
}
