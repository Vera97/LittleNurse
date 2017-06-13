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

import com.example.materialtest.PrescriptionsActivity;
import com.example.materialtest.R;
import com.example.materialtest.RecordActivity;

import org.litepal.crud.DataSupport;

import java.util.List;

public class PrescriptionsAdapter extends ArrayAdapter<Prescriptions> {

    private int resourceId;
    private Context context;

    public PrescriptionsAdapter(Context context, int textViewResourceId,
                                List<Prescriptions> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final Prescriptions prescriptions = getItem(position); // 获取当前项的patient实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.prescriptionImage = (ImageView) view.findViewById (R.id.prescription_image);
            viewHolder.prescriptionDate = (TextView) view.findViewById (R.id.date);
            view.setTag(viewHolder); // 将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }
        viewHolder.prescriptionImage.setImageResource(prescriptions.getImageId());
        viewHolder.prescriptionDate.setText(prescriptions.getDate());
        viewHolder.bAction1=(View)view.findViewById(R.id.delete_button);
        viewHolder.prescriptionImage.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int currentID=prescriptions.getId();
                Intent instent = new Intent(MyApplication.getContext(), RecordActivity.class);
                instent.putExtra("extra_data",currentID);
                context.startActivity(instent);
            }
        });
        viewHolder.bAction1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(MyApplication.getContext(), "you click delete", Toast.LENGTH_SHORT).show();
                int currentID=prescriptions.getId();
                DataSupport.deleteAll(com.example.materialtest.db.Prescription.class,"id=?",""+currentID);
                Intent intent=new Intent(MyApplication.getContext(), PrescriptionsActivity.class);
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

        ImageView prescriptionImage;
        TextView prescriptionDate;
        View bAction1;
    }

}

