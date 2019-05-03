package com.example.seniorproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.seniorproject.Retrofit.Models.Result;
import com.example.seniorproject.Retrofit.Rest.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateUserActivity extends AppCompatActivity {

    EditText username, password, phone;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        init();
        setRegister();
    }

    public void init() {
        username = findViewById(R.id.user_name_createActivity);
        password = findViewById(R.id.password_createActivity);
        phone = findViewById(R.id.phone_createActivity);
        register = findViewById(R.id.register_createActivity);
    }

    public void request() {
        Call<Result> call = ManagerAll.getInstance().addUser(username.getText().toString(), password.getText().toString(), phone.getText().toString());
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }

    public void setRegister() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });
    }
}
