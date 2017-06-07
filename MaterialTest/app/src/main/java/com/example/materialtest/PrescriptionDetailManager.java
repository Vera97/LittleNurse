package com.example.materialtest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张欣蕾 on 2017/5/28.
 */

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
