package com.example.seniorproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.seniorproject.Retrofit.Models.GetField;
import com.example.seniorproject.Retrofit.Models.Result;
import com.example.seniorproject.Retrofit.Models.ThingSpeak.ThingSpeak;
import com.example.seniorproject.Retrofit.Models.ThingSpeak.ThingSpeakHum.ThingSpeakHum;
import com.example.seniorproject.Retrofit.Models.ThingSpeak.ThingSpeakOut.ThingSpeakOut;
import com.example.seniorproject.Retrofit.Rest.ManagerAll;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail extends AppCompatActivity {

    ListView listView;
    Singleton singleton;
    List<GetField> getFieldList;
    String temp, hum, out, tempTemp, tempHum;
    Runnable runnable;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();
        fill_list();
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Tarlalarınız");
        writeDataToDatabase();
    }

    public void init() {
        listView = findViewById(R.id.main_listview);
        singleton = Singleton.getSingleton();
    }

    public void writeDataToDatabase() {
        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                thingSpeak();
                handler.postDelayed(runnable, 1000);
            }
        };

        handler.post(runnable);
    }

    public void fill_list() {
        getFieldList = new ArrayList<>();
        String user_id = singleton.getUser_id();
        Call<List<GetField>> call = ManagerAll.getInstance().getField(user_id);
        call.enqueue(new Callback<List<GetField>>() {
            @Override
            public void onResponse(Call<List<GetField>> call, Response<List<GetField>> response) {
                if(response.isSuccessful()) {
                    getFieldList = response.body();
                    Adapter adapter = new Adapter(Detail.this, getApplicationContext(), getFieldList);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<GetField>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_field, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add_field1) {
            Intent AddFieldActivity = new Intent(getApplicationContext(), AddFieldActivity.class);
            startActivity(AddFieldActivity);
        }
        if(item.getItemId() == R.id.logout_user) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void thingSpeak() {
        Call<ThingSpeak> thingSpeakCall = ManagerAll.getInstance().thingSpeak();
        thingSpeakCall.enqueue(new Callback<ThingSpeak>() {
            @Override
            public void onResponse(Call<ThingSpeak> call, Response<ThingSpeak> response) {
                temp = response.body().getFeeds().get(99).getField1();
                tempTemp = response.body().getFeeds().get(98).getField1();
                System.out.println("alınan temp: " + temp);
                System.out.println("alınan temptemp: " + tempTemp);
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
               hum = response.body().getFeeds().get(98).getField2();
               tempHum = response.body().getFeeds().get(99).getField2();
               if (temp.equals(tempHum) && hum.equals(tempHum)) {
                   System.out.println("aynı veri geldi...");
               } else {
                   System.out.println("xxxalınan hum: " + hum   );
                   System.out.println("xxxalınan temphum: " + tempHum);
                   System.out.println("xxxalınan temp: " + temp);
                   System.out.println("xxxalınan temptemp: " + tempTemp);
                   thingSpeakOut();
               }
           }
           @Override
           public void onFailure(Call<ThingSpeakHum> call, Throwable t) {

           }
       });
    }

    public void thingSpeakOut() {
        Call<ThingSpeakOut> thingSpeakOutCall = ManagerAll.getInstance().thingSpeakOut();
        thingSpeakOutCall.enqueue(new Callback<ThingSpeakOut>() {
            @Override
            public void onResponse(Call<ThingSpeakOut> call, Response<ThingSpeakOut> response) {
                out = response.body().getFeeds().get(99).getField3();
                System.out.println("alınan output: " + out);
                tempAndHumAndOut();
            }

            @Override
            public void onFailure(Call<ThingSpeakOut> call, Throwable t) {

            }
        });
    }

    public void tempAndHum() {
        Call<Result> call = ManagerAll.getInstance().tempAndHum(temp, hum);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                 String word = "success";
                 if(word.equals(response.body().getResult())) {
                     System.out.println("kayıt başarılı");
                 }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                System.out.println("problem: " + t.getMessage());
            }
        });
    }

    public void tempAndHumAndOut() {
        Call<Result> call = ManagerAll.getInstance().tempAndHumAndOut(temp, hum, out);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                String word = "success";
                if(word.equals(response.body().getResult())) {
                    System.out.println("kayıt başarılı");
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                System.out.println("hata var: " + t.getMessage());
            }
        });

    }

}
