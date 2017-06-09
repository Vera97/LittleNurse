package com.example.materialtest.myPackage;

import com.example.materialtest.myPackage.Patient;

import java.util.ArrayList;
import java.util.List;


public class PatientManager {
    private int maxCount=30;
    private List<Patient> patientList=new ArrayList<>();

    public PatientManager(){
        for(int i=0;i<maxCount;i++){
            Patient p=new Patient();
            p.setId(i);
            p.setName("病人"+i);
            patientList.add(p);
        }
    }

    public List<Patient> getPatientList() {
        return patientList;
    }
}
