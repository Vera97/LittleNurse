package com.example.materialtest.myPackage;

import java.util.ArrayList;
import java.util.List;


public class PrescriptionDetailManager {
    private List<PrescriptionDetail> mPrescriptionDetailList=new ArrayList<>();
    private int count=30;
    public PrescriptionDetailManager(){
        for(int i=0;i<count;i++){
            PrescriptionDetail p=new PrescriptionDetail();
            p.setId(i);
            p.setName("病人"+i);
            mPrescriptionDetailList.add(p);
        }
    }

    public List<PrescriptionDetail> getPrescriptionDetailList() {
        return mPrescriptionDetailList;
    }
}
