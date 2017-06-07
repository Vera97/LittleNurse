package com.example.materialtest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张欣蕾 on 2017/5/22.
 */

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
