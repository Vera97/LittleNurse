package com.example.materialtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class PatientActivity extends AppCompatActivity {

    private PatientManager mPatientManager=new PatientManager();
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        //recyclerview
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.patient_recyclerview);
        mLinearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setAdapter(new PatientAdapter(mPatientManager.getPatientList()));


    }

    private class PatientHolder extends RecyclerView.ViewHolder{

        public TextView mPatientId;
        public TextView mPatientName;

        public PatientHolder(View itemView) {
            super(itemView);
            mPatientId=(TextView)itemView.findViewById(R.id.patient_id);
            mPatientName=(TextView)itemView.findViewById(R.id.patient_name);
        }
    }

    private class PatientAdapter extends RecyclerView.Adapter<PatientHolder>{

        private List<Patient> mPatientList;
        public PatientAdapter(List<Patient> patientlist){
            mPatientList=patientlist;
        }

        @Override
        public PatientHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_item,parent,false);
            return new PatientHolder(view);
        }

        @Override
        public void onBindViewHolder(PatientHolder holder, int position) {
            Patient p=mPatientList.get(position);
            holder.mPatientId.setText(String.valueOf(p.getId()));
            holder.mPatientName.setText(p.getName());

        }

        @Override
        public int getItemCount() {
            return  mPatientList.size();
        }
    }
}
