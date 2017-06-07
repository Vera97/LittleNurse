package com.example.materialtest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 张欣蕾 on 2017/5/28.
 */

public class PrescriptionManager {
    private int maxCount=30;
    private List<Prescription> prescriptionList=new ArrayList<>();

    public PrescriptionManager(){
        for(int i=0;i<maxCount;i++){
            Prescription p=new Prescription();
            p.setPrescriptionNum(i);
            p.setPrescriptionDate((new Date()).toString());
            prescriptionList.add(p);
        }
    }

    public List<Prescription> getPatientList() {
        return prescriptionList;
    }
}
