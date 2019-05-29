package com.example.seniorproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seniorproject.Retrofit.Models.GetField;
import com.example.seniorproject.Retrofit.Models.ProductInfo;
import com.example.seniorproject.Retrofit.Rest.ManagerAll;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailedActivity extends AppCompatActivity {
    Singleton singleton;
    TextView fieldName, region, desiredProduct, product_info;
    Button refresh_button;
    List<GetField> getFieldList;
    String fieldName_intent, region_intent, desiredProductString;
    SharedPreferences sharedPreferences, sharedDesiredProduct, sharedProductInfo;
    ArrayList<String> product_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        setTitle("Tarla Bilgileri");
        init();
        setView();
        getProductList();
        setDesiredProductClick();
        setRefreshButton();
        productInfo();
        sharedProductInfo = getSharedPreferences("product_info", MODE_PRIVATE);
        if(sharedProductInfo.getBoolean("icClickTamam", false)) {

        } else {
            refresh_button.setText("Ürün Yenile");
        }
    }


    public void init() {
        fieldName = findViewById(R.id.field_name);
        region = findViewById(R.id.region);
        desiredProduct = findViewById(R.id.desired_product);
        getFieldList = new ArrayList<>();
        singleton = Singleton.getInstance();
        product_info = findViewById(R.id.product_info);
        refresh_button = findViewById(R.id.refresh_product);
        productInfo();
    }

    public void setView() {
        fieldName_intent = getIntent().getStringExtra("field_name");
        region_intent = getIntent().getStringExtra("region");
        sharedDesiredProduct = getSharedPreferences("desiredProduct", MODE_PRIVATE);

        desiredProductString = sharedDesiredProduct.getString("desired", "Ürün seçiniz..");
        desiredProduct.setText(desiredProductString);
        fieldName.setText(fieldName_intent);
        region.setText(region_intent);
    }

    public void getProductList() {
        sharedPreferences = getSharedPreferences("product_list", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("list", null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        product_list = new ArrayList<>();
        product_list = gson.fromJson(json, type);
    }

    public void setDesiredProductClick() {
        desiredProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(DetailedActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_spinner, null);
                mBuilder.setTitle("Ürün seçiniz");
                final Spinner spinner = mView.findViewById(R.id.dialog_spinner_x);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DetailedActivity.this, android.R.layout.simple_spinner_item, product_list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                mBuilder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!spinner.getSelectedItem().toString().equalsIgnoreCase("Ürün bulunamadı")) {
                            desiredProductString = spinner.getSelectedItem().toString();
                            desiredProduct.setText(desiredProductString);
                            sharedDesiredProduct = getSharedPreferences("desiredProduct", MODE_PRIVATE);
                            SharedPreferences.Editor sharedDesiredProductEditor = sharedDesiredProduct.edit();
                            sharedDesiredProductEditor.putString("desired", spinner.getSelectedItem().toString());
                            sharedDesiredProductEditor.apply();
                            productInfo();
                            sharedProductInfo = getSharedPreferences("product_info", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedProductInfo.edit();
                            editor.putBoolean("isClickTamam", true);
                            editor.apply();
                            dialog.dismiss();
                        } else {
                            desiredProduct.setText("Ürün bulunamadı");
                            sharedDesiredProduct = getSharedPreferences("desiredProduct", MODE_PRIVATE);
                            SharedPreferences.Editor sharedDesiredProductEditor = sharedDesiredProduct.edit();
                            sharedDesiredProductEditor.putString("desired", "Ürün bulunamadı");
                            sharedDesiredProductEditor.apply();
                        }
                    }
                });

                mBuilder.setNegativeButton("İptal Et", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }

    public void productInfo() {
        if (desiredProductString == null) {
            product_info.setText("Ürün bilgisi bulunamadı...");
        } else {
            Call<ProductInfo> productInfoCall = ManagerAll.getInstance().productInfo(desiredProductString);
            productInfoCall.enqueue(new Callback<ProductInfo>() {
                @Override
                public void onResponse(Call<ProductInfo> call, Response<ProductInfo> response) {
                    if (response.isSuccessful()) {
                        if (!response.body().getInfo().isEmpty()) {
                            String productInfo = response.body().getInfo();
                            product_info.setText(productInfo);
                            Toast.makeText(getApplicationContext(), "Ürün bulundu..", Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ProductInfo> call, Throwable t) {
                    System.out.println("product info da hata var: " + t.getLocalizedMessage());
                }
            });
        }
    }

    public void setRefreshButton() {
        refresh_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(DetailedActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_spinner, null);
                mBuilder.setTitle("Ürün seçiniz");
                final Spinner spinner = mView.findViewById(R.id.dialog_spinner_x);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DetailedActivity.this, android.R.layout.simple_spinner_item, product_list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                mBuilder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!spinner.getSelectedItem().toString().equalsIgnoreCase("Ürün bulunamadı")) {
                            desiredProductString = spinner.getSelectedItem().toString();
                            desiredProduct.setText(desiredProductString);
                            sharedDesiredProduct = getSharedPreferences("desiredProduct", MODE_PRIVATE);
                            SharedPreferences.Editor sharedDesiredProductEditor = sharedDesiredProduct.edit();
                            sharedDesiredProductEditor.putString("desired", spinner.getSelectedItem().toString());
                            sharedDesiredProductEditor.apply();
                            productInfo();
                            sharedProductInfo = getSharedPreferences("product_info", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedProductInfo.edit();
                            editor.putBoolean("isClickTamam", true);
                            editor.apply();
                            dialog.dismiss();
                        } else {
                            desiredProduct.setText("Ürün bulunamadı");
                            sharedDesiredProduct = getSharedPreferences("desiredProduct", MODE_PRIVATE);
                            SharedPreferences.Editor sharedDesiredProductEditor = sharedDesiredProduct.edit();
                            sharedDesiredProductEditor.putString("desired", "Ürün bulunamadı");
                            sharedDesiredProductEditor.apply();
                        }
                    }
                });

                mBuilder.setNegativeButton("İptal Et", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }

}
