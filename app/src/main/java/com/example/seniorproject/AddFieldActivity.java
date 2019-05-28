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

import com.example.seniorproject.Retrofit.Models.GetOutput;
import com.example.seniorproject.Retrofit.Models.Result;
import com.example.seniorproject.Retrofit.Models.ThingSpeak.ThingSpeak;
import com.example.seniorproject.Retrofit.Models.ThingSpeak.ThingSpeakHum.ThingSpeakHum;
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
    EditText fieldName, city;
    String temp, hum, getFieldId, check = "false";

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
        city = findViewById(R.id.city);
        spinner = findViewById(R.id.region_spinner);
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
                    Call<Result> call = ManagerAll.getInstance().addField(fieldName.getText().toString(), city.getText().toString(), singleton.getRegion(), singleton.getUser_id());
                    call.enqueue(new Callback<Result>() {
                        @Override
                        public void onResponse(Call<Result> call, Response<Result> response) {
                           if(response.isSuccessful()) {
                               getFieldId = response.body().getResult();
                               singleton.setGetFiedlId(getFieldId);
                               thingSpeak();
                               Intent intent = new Intent(getApplicationContext(), Detail.class);
                               check = "true";
                               intent.putExtra("check", check);
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

    public void thingSpeak() {

        Call<ThingSpeak> thingSpeakCall = ManagerAll.getInstance().thingSpeak();
        thingSpeakCall.enqueue(new Callback<ThingSpeak>() {
            @Override
            public void onResponse(Call<ThingSpeak> call, Response<ThingSpeak> response) {
                temp = response.body().getFeeds().get(99).getField1();
                System.out.println("alınan temp: " + temp);
                thingSpeakHum();
            }

            @Override
            public void onFailure(Call<ThingSpeak> call, Throwable t) {

            }
        });
    }


    public void thingSpeakHum() {
        Call<ThingSpeakHum> thingSpeakHumCall = ManagerAll.getInstance().thingSpeakHum();
        thingSpeakHumCall.enqueue(new Callback<ThingSpeakHum>() {
            @Override
            public void onResponse(Call<ThingSpeakHum> call, Response<ThingSpeakHum> response) {
                hum = response.body().getFeeds().get(99).getField2();

                System.out.println("xxxalınan hum: " + hum);
                System.out.println("xxxalınan temp: " + temp);

                tempAndHum();
            }

            @Override
            public void onFailure(Call<ThingSpeakHum> call, Throwable t) {

            }
        });
    }

    public void tempAndHum() {
        Call<Result> call = ManagerAll.getInstance().tempAndHum(temp, hum, getFieldId, singleton.getUser_id());
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                String word = "success";
                if (word.equals(response.body().getResult())) {
                    System.out.println("kayıt başarılı");
                    getOutput();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                System.out.println("problem: " + t.getMessage());
            }
        });
    }

    public void getOutput() {
        Call<GetOutput> getOutputCall = ManagerAll.getInstance().getOutput();
        getOutputCall.enqueue(new Callback<GetOutput>() {
            @Override
            public void onResponse(Call<GetOutput> call, Response<GetOutput> response) {
                System.out.println("getoutput: " + response.body().getResult());
            }

            @Override
            public void onFailure(Call<GetOutput> call, Throwable t) {
                System.out.println("gethatalar burada: " + t.getMessage());
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
