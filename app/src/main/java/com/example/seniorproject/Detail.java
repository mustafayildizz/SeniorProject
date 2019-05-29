package com.example.seniorproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.seniorproject.Retrofit.Models.GetField;
import com.example.seniorproject.Retrofit.Models.GetOutput;
import com.example.seniorproject.Retrofit.Models.Result;
import com.example.seniorproject.Retrofit.Models.ThingSpeak.ThingSpeak;
import com.example.seniorproject.Retrofit.Models.ThingSpeak.ThingSpeakHum.ThingSpeakHum;
import com.example.seniorproject.Retrofit.Models.ThingSpeak.ThingSpeakOut.ThingSpeakOut;
import com.example.seniorproject.Retrofit.Rest.ManagerAll;
import com.google.gson.Gson;

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
    ArrayList<String> product_list;
    String temp, hum, output;
    int size;
    Handler handler;
    Runnable runnable;
    static boolean count_down_activity = true;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setTitle("Tarlalarınız");
        init();
        fill_list();
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Tarlalarınız");

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
                handler.postDelayed(runnable, 2000);
            }
        };
        handler.postDelayed(runnable, 2000);
    }

    public void fill_list() {
        getFieldList = new ArrayList<>();
        String user_id = singleton.getUser_id();
        Call<List<GetField>> call = ManagerAll.getInstance().getField(user_id);
        call.enqueue(new Callback<List<GetField>>() {
            @Override
            public void onResponse(Call<List<GetField>> call, Response<List<GetField>> response) {
                if (response.isSuccessful()) {
                    getFieldList = response.body();
                    size = getFieldList.size();
                    if (size > 0) {
                        writeDataToDatabase();
                        MyThread myThread = new MyThread();
                        thread = new Thread(myThread);
                        thread.start();
                    }


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
        if (item.getItemId() == R.id.add_field1) {
            Intent AddFieldActivity = new Intent(getApplicationContext(), AddFieldActivity.class);
            startActivity(AddFieldActivity);
        }
        if (item.getItemId() == R.id.logout_user) {
            if (runnable != null)
                handler.removeCallbacks(runnable);
            LoginActivity.prefConfig.writeLoginStatus(false);
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

                tempAndHum();
            }

            @Override
            public void onFailure(Call<ThingSpeakHum> call, Throwable t) {

            }
        });
    }


    public void tempAndHum() {
        Call<Result> call = ManagerAll.getInstance().tempAndHum(temp, hum, singleton.getGetFiedlId(), singleton.getUser_id());
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                String word = "success";
                if (word.equals(response.body().getResult())) {
                    System.out.println("kayıt başarılı");
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
                System.out.println("alınangetoutput: " + response.body().getResult());
                output = response.body().getResult();
                setAppropriatedProduct(Float.parseFloat(output), Float.parseFloat(temp), Float.parseFloat(hum));
                if(product_list.contains("Ürün bulunamadı")) {
                    Toast.makeText(getApplicationContext(), "Ürün bulunamadı...", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), DetailedActivity.class);
                    Toast.makeText(getApplicationContext(), "Lütfen ürüm seçimini yapınız.", Toast.LENGTH_LONG).show();
                    //startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<GetOutput> call, Throwable t) {
                System.out.println("gethatalar burada: " + t.getMessage());
            }
        });
    }

    public class MyThread implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                System.out.println("thread çalıştı: " + thread.currentThread().getName());
                getOutput();
                Intent intent = new Intent(getApplicationContext(), DetailedActivity.class);
                //  startActivity(intent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setAppropriatedProduct(float output, float temperature, float humidity) {
        String[] grup1 = {"Bugday", "Patates", "Arpa"};
        String[] grup2 = {"Nohut", "Biber"};
        String[] grup3 = {"Arpa", "Buğday", "Domates"};
        String[] grup4 = {"Mısır", "Nohut", "Soðan"};
        String[] grup5 = {"Domates", "Arpa"};
        String[] grup6 = {"Biber", "Nohut", "Mısır"};
        String[] grup7 = {"Patates", "Buðday"};
        String[] grup8 = {"Soðan"};
        String[] grup9 = {"Pamuk", "Arpa", "Domates"};

        int counter = 0;

        product_list = new ArrayList<>();

        if (output > 0.0 && output <= 2.0) {
            if (temperature <= 10 && temperature >= 8 && humidity >= 43) {
                counter++;
                System.out.println("1.ürün= " + grup1[1]);
                System.out.println("2.ürün= " + grup1[0]);
                System.out.println("3.ürün= " + grup1[2]);

                product_list.add(grup1[1]);
                product_list.add(grup1[0]);
                product_list.add(grup1[2]);
            }
            if (temperature > 10 && humidity >= 60) {
                counter++;
                System.out.println("1.ürün= " + grup1[2]);
                System.out.println("2.ürün= " + grup1[0]);

                product_list.add(grup1[2]);
                product_list.add(grup1[0]);
            }
            if (temperature > 10 && humidity < 60 && humidity >= 43) {
                counter++;
                System.out.println("1.ürün= " + grup1[0]);
                System.out.println("2.ürün= " + grup1[1]);

                product_list.add(grup1[0]);
                product_list.add(grup1[1]);
            }
        }
        if (output >= 1 && output <= 3) {
            if (temperature <= 18 && humidity <= 20) {
                counter++;
                System.out.println("1.ürün= " + grup2[1]);
                System.out.println("2.ürün= " + grup2[0]);

                product_list.add(grup2[1]);
                product_list.add(grup2[0]);
            }
            if (temperature <= 18 && humidity >= 20 && humidity <= 30) {
                counter++;
                System.out.println("1.ürün= " + grup2[0]);
                System.out.println("2.ürün= " + grup2[1]);

                product_list.add(grup2[0]);
                product_list.add(grup2[1]);
            }

        }
        if (output >= 2 && output <= 4) {
            if (temperature < 14 && temperature >= 8 && humidity > 58 && humidity <= 63) {
                counter++;
                System.out.println("1.ürün= " + grup3[1]);
                System.out.println("2.ürün= " + grup3[0]);
                System.out.println("3.ürün= " + grup3[2]);

                product_list.add(grup3[1]);
                product_list.add(grup3[0]);
                product_list.add(grup3[2]);
            }
            if (temperature > 15 && humidity > 58 && humidity <= 70) {
                counter++;
                System.out.println("1.ürün= " + grup3[0]);
                System.out.println("2.ürün= " + grup3[2]);

                product_list.add(grup3[0]);
                product_list.add(grup3[2]);
            }
        }
        if (output >= 3 && output <= 5) {

            if (temperature >= 15 && temperature <= 26 && humidity >= 20 && humidity <= 30) {
                counter++;
                System.out.println("1.ürün= " + grup4[0]);
                System.out.println("2.ürün= " + grup4[1]);

                product_list.add(grup4[0]);
                product_list.add(grup4[1]);
            }
            if (temperature >= 15 && temperature < 27 && humidity > 30 && humidity <= 40) {
                counter++;
                System.out.println("1.ürün= " + grup4[2]);
                System.out.println("2.ürün= " + grup4[0]);
                System.out.println("3.ürün= " + grup4[1]);

                product_list.add(grup4[2]);
                product_list.add(grup4[0]);
                product_list.add(grup4[1]);
            }
            if (temperature >= 27 && humidity > 20 && humidity <= 40) {
                counter++;
                System.out.println("1.ürün= " + grup4[0]);

                product_list.add(grup4[0]);
            }

        }
        if (output >= 4 && output <= 6) {
            if (temperature >= 14 && humidity >= 60 && humidity <= 70) {
                counter++;
                System.out.println("1.ürün= " + grup5[0]);
                System.out.println("2.ürün= " + grup5[1]);

                product_list.add(grup5[0]);
                product_list.add(grup5[1]);
            }
            if (temperature < 14 && humidity >= 60 && humidity <= 80) {
                counter++;
                System.out.println("1.ürün= " + grup5[1]);

                product_list.add(grup5[1]);
            }
        }
        if (output >= 5 && output <= 7) {
            if (temperature < 18 && humidity >= 10 && humidity <= 30) {
                counter++;
                System.out.println("1.ürün= " + grup6[1]);
                System.out.println("2.ürün= " + grup6[2]);

                product_list.add(grup6[1]);
                product_list.add(grup6[2]);
            }
            if (temperature >= 18 && humidity < 20) {
                counter++;
                System.out.println("1.ürün= " + grup6[0]);
                System.out.println("2.ürün= " + grup6[1]);

                product_list.add(grup6[0]);
                product_list.add(grup6[1]);
            }
            if (temperature > 18 && humidity == 20) {
                counter++;
                System.out.println("1.ürün= " + grup6[1]);
                System.out.println("2.ürün= " + grup6[0]);
                System.out.println("3.ürün= " + grup6[2]);

                product_list.add(grup6[1]);
                product_list.add(grup6[0]);
                product_list.add(grup6[2]);
            }
        }
        if (output >= 6 && output <= 8) {
            if (temperature <= 10 && temperature >= 8 && humidity >= 58 && humidity <= 63) {
                counter++;
                System.out.println("1.ürün= " + grup7[0]);
                System.out.println("2.ürün= " + grup7[1]);

                product_list.add(grup7[0]);
                product_list.add(grup7[1]);
            }
            if (temperature > 10 && humidity > 58) {
                counter++;
                System.out.println("1.ürün= " + grup7[1]);

                product_list.add(grup7[1]);
            }
            if (temperature < 8 && humidity < 58) {
                counter++;
                System.out.println("1.ürün= " + grup7[0]);

                product_list.add(grup7[0]);
            }
        }
        if (output >= 7 && output <= 9) {
            if (temperature <= 21 && temperature <= 27 && humidity >= 40 && humidity <= 55) {
                counter++;
                System.out.println("1.ürün= " + grup8[0]);
                product_list.add(grup8[0]);
            }
        }
        if (output >= 8 && output <= 10) {
            if (temperature <= 20 && humidity >= 60 && humidity < 70) {
                counter++;
                System.out.println("1.ürün= " + grup9[2]);
                System.out.println("1.ürün= " + grup9[1]);

                product_list.add(grup9[2]);
                product_list.add(grup9[1]);
            }
            if (temperature >= 14 && temperature <= 20 && humidity == 70) {
                counter++;
                System.out.println("1.ürün= " + grup9[1]);
                System.out.println("1.ürün= " + grup9[2]);
                System.out.println("1.ürün= " + grup9[0]);

                product_list.add(grup9[1]);
                product_list.add(grup9[2]);
                product_list.add(grup9[0]);
            }

        }

        if (counter == 0) {
            System.out.println("ürün bulunamadı...");

            product_list.add("Ürün bulunamadı");
            product_list.add("Buğday");
        }


        SharedPreferences sharedPreferences = getSharedPreferences("product_list", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(product_list);
        editor.putString("list", json);
        editor.apply();

    }
}

