package com.example.seniorproject;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.seniorproject.Retrofit.Models.GetField;

import java.util.List;

public class Adapter extends BaseAdapter {

    Activity activity;
    Context context;
    List<GetField> getFieldList;
    Singleton singleton;

    public Adapter(Activity activity, Context context, List<GetField> getFieldList) {
        this.activity = activity;
        this.context = context;
        this.getFieldList = getFieldList;
    }


    @Override
    public int getCount() {
        return getFieldList.size();
    }

    @Override
    public Object getItem(int position) {
        return getFieldList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.detail_layout, parent, false);
        singleton = Singleton.getInstance();

        TextView field_name = convertView.findViewById(R.id.field_name);
        TextView weather = convertView.findViewById(R.id.weather);
        TextView desiredProduct = convertView.findViewById(R.id.desired_product_detail_layout);
        TextView region = convertView.findViewById(R.id.region);
        field_name.setText(getFieldList.get(position).getFieldname());
        weather.setText(singleton.getWeather());
        desiredProduct.setText(getFieldList.get(position).getDesiredproduct());
        region.setText(getFieldList.get(position).getRegion());

        LinearLayout linearLayout;
        linearLayout = convertView.findViewById(R.id.detail_layout_listview);

        final String region_intent = getFieldList.get(position).getRegion();
        final String fieldName_intent = getFieldList.get(position).getFieldname();

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity.getApplicationContext(), DetailedActivity.class);
                intent.putExtra("field_name", fieldName_intent);
                intent.putExtra("region", region_intent);
                activity.startActivity(intent);
            }
        });
        return convertView;
    }
}




