package com.example.seniorproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.seniorproject.Retrofit.Models.Result;
import com.example.seniorproject.Retrofit.Rest.ManagerAll;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFieldActivity extends AppCompatActivity {

    Singleton singleton;
    Spinner spinner;
    View addFieldButton;
    long parent1;
    int parent2;
    EditText fieldName, desiredProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_field);

        init();
        setAddFieldButton();
        setSpinner();


    }

    public void init() {
        fieldName = findViewById(R.id.field_name);
        desiredProduct = findViewById(R.id.desired_product);
        spinner = findViewById(R.id.spinner_region);
        addFieldButton = findViewById(R.id.add_field_button);
        ArrayAdapter<CharSequence> spinner_array = ArrayAdapter.createFromResource(this, R.array.region, android.R.layout.simple_spinner_item);
        spinner_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinner_array);
        singleton = Singleton.getSingleton();
    }

    public void setAddFieldButton() {
        addFieldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parent1 == 0) {
                    Toast.makeText(getApplicationContext(), "Lütfen bölge seçiniz", Toast.LENGTH_LONG).show();
                } else {
                    Call<Result> call = ManagerAll.getInstance().addField(fieldName.getText().toString(), desiredProduct.getText().toString(), singleton.getRegion(), singleton.getUser_id());
                    call.enqueue(new Callback<Result>() {
                        @Override
                        public void onResponse(Call<Result> call, Response<Result> response) {
                            String word = "success";
                            if(word.equals(response.body().getResult())) {
                                Intent intent = new Intent(getApplicationContext(), Detail.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<Result> call, Throwable t) {
                            System.out.println("hay arkadaş yaa: " + t.getMessage());
                        }
                    });
                }
            }
        });
    }

    public void setSpinner() {
      spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              parent1 = id;
              singleton.setRegion(parent.getItemAtPosition(position).toString());
          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {

          }
      });
    }

}
