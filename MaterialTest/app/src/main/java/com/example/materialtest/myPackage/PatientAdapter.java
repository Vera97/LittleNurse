package com.example.materialtest.myPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.materialtest.R;

import java.util.List;

public class PatientAdapter extends ArrayAdapter<Patient> {

    private int resourceId;

    public PatientAdapter(Context context, int textViewResourceId,
                          List<Patient> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Patient patient = getItem(position); // 获取当前项的patient实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.patientImage = (ImageView) view.findViewById (R.id.patient_image);
            viewHolder.patientName = (TextView) view.findViewById (R.id.patient_name);
            view.setTag(viewHolder); // 将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }
        viewHolder.patientImage.setImageResource(patient.getImageId());
        viewHolder.patientName.setText(patient.getName());
        return view;
    }

    class ViewHolder {

        ImageView patientImage;

        TextView patientName;

    }

}

