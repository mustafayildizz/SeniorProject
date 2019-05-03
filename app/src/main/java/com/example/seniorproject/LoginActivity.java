package com.example.seniorproject;

import android.Manifest;
import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.seniorproject.Retrofit.Models.DarkSky.DarkSky;
import com.example.seniorproject.Retrofit.Models.GetId;
import com.example.seniorproject.Retrofit.Models.Result;
import com.example.seniorproject.Retrofit.Rest.ManagerAll;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;

import java.io.IOException;
import java.nio.channels.InterruptedByTimeoutException;
import java.security.Permission;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    public static PrefConfig prefConfig;

    View loginButton, signUpButton;
    EditText username, password;
    LocationManager locationManager;
    LocationListener locationListener;
    double latitude, longitude;
    Singleton singleton;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        prefConfig = new PrefConfig(this);
        if (prefConfig.readLoginStatus()) {
            Intent detail = new Intent(getApplicationContext(), Detail.class);
            startActivity(detail);
        } else {
            init();
            setLoginButton();
            setSignUpButton();
            getDataWithDarkSky();
        }
    }

    public void init() {
        username = findViewById(R.id.user_email);
        password = findViewById(R.id.user_password);
        loginButton = findViewById(R.id.login_button);
        signUpButton = findViewById(R.id.sign_up_button);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//            latitude = location.getLatitude();
//            longitude = location.getLongitude();
            System.out.println("konum: " + latitude + " " + longitude);
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && requestCode == 1) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    singleton = Singleton.getInstance();
                    singleton.setLocation(latitude + "," + longitude);
                    System.out.println("konum: " + latitude + " " + longitude);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Lütfen bölge seçiniz", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Lütfen bölge seçiniz alt else", Toast.LENGTH_LONG).show();
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void request() {
        Call<GetId> call = ManagerAll.getInstance().loginUser(username.getText().toString(), password.getText().toString());
        call.enqueue(new Callback<GetId>() {
            @Override
            public void onResponse(Call<GetId> call, Response<GetId> response) {
                String word = "fail";
                singleton = Singleton.getInstance();
                if (word.equals(response.body().getResult())) {

                    Toast.makeText(LoginActivity.this, "Lütfen bilgilerinizi kontrol edin", Toast.LENGTH_SHORT).show();

                } else {
                    prefConfig.writeLoginStatus(true);
                    singleton.setUser_id(response.body().getUser_id());
                    Intent intent = new Intent(getApplicationContext(), Detail.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<GetId> call, Throwable t) {
                System.out.println("baba geçmiş olsun uygulama çöktü" + t.getMessage());
            }
        });
    }

    public void setLoginButton() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });
    }

    public void setSignUpButton() {
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateUserActivity.class);
                startActivity(intent);
            }
        });
    }

    public void getDataWithDarkSky() {
        Call<DarkSky> call = ManagerAll.getInstance().getData();
        call.enqueue(new Callback<DarkSky>() {
            @Override
            public void onResponse(Call<DarkSky> call, Response<DarkSky> response) {
                singleton = Singleton.getSingleton();
                double fahrenheit = response.body().getCurrently().getTemperature();
                int tempFahrenheit = (int) Math.round(fahrenheit);
                float celcius = (tempFahrenheit - 32) * 5 / 9;
                int tempCelcius = (int) celcius;
                singleton.setWeather(String.valueOf(tempCelcius));
                Toast.makeText(getApplicationContext(), "Bulunduğunuz yerde sıcaklık: " + tempCelcius + " C", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<DarkSky> call, Throwable t) {
                System.out.println("hata: " + t.getMessage());
            }
        });
    }


}

