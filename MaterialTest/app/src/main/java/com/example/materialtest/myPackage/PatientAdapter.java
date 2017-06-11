package com.example.materialtest.myPackage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.materialtest.PatientActivity;
import com.example.materialtest.PatientInfoActivity;
import com.example.materialtest.R;

import java.util.List;

public class PatientAdapter extends ArrayAdapter<Patient> {

    private int resourceId;
    private Context context;

    public PatientAdapter(Context context, int textViewResourceId,
                          List<Patient> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final Patient patient = getItem(position); // 获取当前项的patient实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.patientImage = (ImageView) view.findViewById (R.id.patient_image);
            viewHolder.patientName = (TextView) view.findViewById (R.id.patient_name);
            viewHolder.bAction1 = (View) view.findViewById(R.id.delete_button);
            view.setTag(viewHolder); // 将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }
        viewHolder.patientImage.setImageResource(patient.getImageId());
        viewHolder.patientName.setText(patient.getName());
        viewHolder.patientImage.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent instent = new Intent(MyApplication.getContext(), PatientInfoActivity.class);
                context.startActivity(instent);
            }
        });
        viewHolder.bAction1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(MyApplication.getContext(), "you click delete", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    class ViewHolder {
        ImageView patientImage;
        TextView patientName;
        View bAction1;
    }

}

