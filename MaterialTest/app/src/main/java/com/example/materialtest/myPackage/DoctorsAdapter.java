package com.example.materialtest.myPackage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.materialtest.DoctorMainActivity;
import com.example.materialtest.DoctorsActivity;
import com.example.materialtest.DoctorsInfoActivity;
import com.example.materialtest.PatientActivity;
import com.example.materialtest.PatientInfoActivity;
import com.example.materialtest.R;
import com.example.materialtest.db.Doctor;

import org.litepal.crud.DataSupport;

import java.util.List;

public class DoctorsAdapter extends ArrayAdapter<Doctors> {

    private int resourceId;
    private Context context;

    public DoctorsAdapter(Context context, int textViewResourceId,
                          List<Doctors> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final Doctors doctors = getItem(position); // 获取当前项的patient实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.doctorsImage = (ImageView) view.findViewById (R.id.patient_image);
            viewHolder.doctorsName = (TextView) view.findViewById (R.id.patient_name);
            view.setTag(viewHolder); // 将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }
        viewHolder.doctorsImage.setImageResource(doctors.getImageId());
        viewHolder.doctorsName.setText(doctors.getName());
        viewHolder.bAction1=(View)view.findViewById(R.id.delete_button);
        viewHolder.doctorsImage.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String currentName=doctors.getName();
                Intent instent = new Intent(MyApplication.getContext(), DoctorsInfoActivity.class);
                instent.putExtra("extra_data",currentName);
                context.startActivity(instent);
            }
        });
        viewHolder.bAction1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(MyApplication.getContext(), "you click delete", Toast.LENGTH_SHORT).show();
                String currentName=doctors.getName();
                DataSupport.deleteAll(com.example.materialtest.db.Doctor.class,"name=?",currentName);
                Intent intent=new Intent(MyApplication.getContext(), DoctorsActivity.class);
                context.startActivity(intent);
                /*AlertDialog.Builder dialog = new AlertDialog.Builder(MyApplication.getContext());
                dialog.setTitle("你确定要删除这个条目吗？")；
                dialog.setMessage("删除这项后将不再显示，无法找回")；
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogI)*/
            }
        });

        return view;
    }

    class ViewHolder {

        ImageView doctorsImage;
        TextView doctorsName;
        View bAction1;
    }

}

