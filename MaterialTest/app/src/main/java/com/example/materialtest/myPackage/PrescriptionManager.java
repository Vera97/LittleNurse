package com.example.materialtest.myPackage;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PrescriptionManager {
   // private int maxCount=30;
    private List<Prescription> prescriptionList=new ArrayList<>();

    public PrescriptionManager(){
        List<com.example.materialtest.db.Prescription>prescriptions= DataSupport.findAll(com.example.materialtest.db.Prescription.class);
        for(com.example.materialtest.db.Prescription prescription:prescriptions){
            Prescription p=new Prescription();
            p.setPrescriptionNum(prescription.getId());
            p.setPrescriptionDate(prescription.getDate());
            prescriptionList.add(p);
        }
        /*
        for(int i=0;i<maxCount;i++){
            Prescription p=new Prescription();
            p.setPrescriptionNum(i);
            p.setPrescriptionDate((new Date()).toString());
            prescriptionList.add(p);
        }*/
    }

    public List<Prescription> getPatientList() {
        return prescriptionList;
    }
}
