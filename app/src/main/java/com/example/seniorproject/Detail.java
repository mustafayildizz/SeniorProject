package com.example.seniorproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.seniorproject.Retrofit.Models.GetField;
import com.example.seniorproject.Retrofit.Rest.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail extends AppCompatActivity {

    ListView listView;
    Singleton singleton;
    List<GetField> getFieldList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();
        fill_list();
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Tarlalarınız");
    }

    public void init() {
        listView = findViewById(R.id.main_listview);
        singleton = Singleton.getSingleton();
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
}
