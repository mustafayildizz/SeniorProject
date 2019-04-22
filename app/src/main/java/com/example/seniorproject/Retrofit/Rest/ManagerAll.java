package com.example.seniorproject.Retrofit.Rest;


import com.example.seniorproject.Retrofit.Models.DarkSky.Currently;
import com.example.seniorproject.Retrofit.Models.DarkSky.DarkSky;
import com.example.seniorproject.Retrofit.Models.GetField;
import com.example.seniorproject.Retrofit.Models.GetId;
import com.example.seniorproject.Retrofit.Models.GetUser;
import com.example.seniorproject.Retrofit.Models.Result;
import com.example.seniorproject.Retrofit.Models.ThingSpeak.ThingSpeak;
import com.example.seniorproject.Retrofit.Models.ThingSpeak.ThingSpeakHum.ThingSpeakHum;

import java.util.List;

import retrofit2.Call;

public class ManagerAll extends BaseManager {
    private static ManagerAll ourInstance = new ManagerAll();

    public static synchronized ManagerAll getInstance() {
        return ourInstance;
    }

    public Call<Result> addUser(String username, String password, String phone) {
        Call<Result> addUser = getRestApi().addUser(username, password, phone);
        return addUser;
    }

    public Call<GetId> loginUser(String username, String password) {
        Call<GetId> loginUser = getRestApi().loginUser(username, password);
        return loginUser;
    }

    public Call<DarkSky> getData() {
        Call<DarkSky> getData = getDarkSky().getData();
        return getData;
    }

    public Call<List<GetField>> getField(String user_id) {
        Call<List<GetField>> getField = getRestApi().getField(user_id);
        return getField;
    }

    public Call<Result> addField(String fieldname, String desiredProduct, String region, String userid) {
        Call<Result> addField = getRestApi().addField(fieldname, desiredProduct, region, userid);
        return addField;
    }

    public Call<ThingSpeak> thingSpeak() {
        Call<ThingSpeak> thingSpeakCall = getDataThingSpeak().thingSpeak();
        return thingSpeakCall;
    }

    public Call<Result> tempAndHum(String temp, String hum) {
        Call<Result> tempAndHum = getRestApi().tempAndHum(temp, hum);
        return tempAndHum;
    }

    public Call<ThingSpeakHum> thingSpeakHum() {
        Call<ThingSpeakHum> thingSpeakHum = getDataThingSpeak().thingSpeakHum();
        return thingSpeakHum;
    }

}
