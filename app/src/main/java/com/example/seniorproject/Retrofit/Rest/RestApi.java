package com.example.seniorproject.Retrofit.Rest;

import com.example.seniorproject.Retrofit.Models.DarkSky.DarkSky;
import com.example.seniorproject.Retrofit.Models.GetField;
import com.example.seniorproject.Retrofit.Models.GetId;
import com.example.seniorproject.Retrofit.Models.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

import com.example.seniorproject.Retrofit.Models.ThingSpeak.ThingSpeak;
import com.example.seniorproject.Retrofit.Models.ThingSpeak.ThingSpeakHum.ThingSpeakHum;
import com.example.seniorproject.Singleton;


public interface RestApi {

    String url = "41.249217,32.683193";
    @FormUrlEncoded
    @POST("login_page.php")
    Call<GetId> loginUser(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("add_user_page.php")
    Call<Result> addUser(@Field("username") String username, @Field("password") String password,  @Field("phone") String phone);

    @GET(url)
    Call<DarkSky> getData();

    @FormUrlEncoded
    @POST("all_field.php")
    Call<List<GetField>> getField(@Field("userid") String user_id);

    @FormUrlEncoded
    @POST("add_field_page.php")
    Call<Result> addField(@Field("fieldname") String fieldname, @Field("desiredproduct") String desiredProduct, @Field("region") String region, @Field("userid") String userid);

    @GET("1.json")
    Call<ThingSpeak> thingSpeak();

    @GET("2.json")
    Call<ThingSpeakHum> thingSpeakHum();

    @FormUrlEncoded
    @POST("temp_hum.php")
    Call<Result> tempAndHum(@Field("temperature") String temp, @Field("humidity") String hum);

    @FormUrlEncoded
    @POST("temp_hum_output.php")
    Call<Result> tempAndHumAndOut(@Field("temp") String temp, @Field("hum") String hum, @Field("output") String output);
}
