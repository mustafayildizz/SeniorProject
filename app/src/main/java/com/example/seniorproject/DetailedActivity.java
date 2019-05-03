package com.example.seniorproject;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.seniorproject.Retrofit.Models.GetField;

import java.util.ArrayList;
import java.util.List;

public class DetailedActivity extends AppCompatActivity {
    Singleton singleton;
    TextView fieldName, region, desiredProduct;
    List<GetField> getFieldList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        init();
        setView();
    }



    public void init() {
        fieldName = findViewById(R.id.field_name);
        region = findViewById(R.id.region);
        desiredProduct = findViewById(R.id.desired_product);
        getFieldList = new ArrayList<>();
        singleton = Singleton.getInstance();
    }

    public void setView() {
       // fieldName.setText(getFieldList.get(0).getFieldname());
    }

}
