package com.example.tracnghiem2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class check_answer_adapter extends BaseAdapter {

    ArrayList lsData;
    LayoutInflater inflater;

    public check_answer_adapter(ArrayList lsData, Context context){
        this.lsData =  lsData;
        inflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return lsData.size();
    }

    @Override
    public Object getItem(int position) {
        return lsData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Question data = (Question) getItem(position);
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView= inflater.inflate(R.layout.item_gridview_list,null);
            holder.txtdapan= (TextView) convertView.findViewById(R.id.txtdapan);
            holder.txtcauhoi= (TextView) convertView.findViewById(R.id.txtcauhoi);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        int i  = position +1;
        holder.txtcauhoi.setText("Cau "+i+":");
        holder.txtdapan.setText(data.getTraloi());

        return convertView;

    }

    private static class ViewHolder{
        TextView txtdapan, txtcauhoi;
    }
}
