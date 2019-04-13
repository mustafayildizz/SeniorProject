package com.example.seniorproject.Retrofit.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetId {
    @SerializedName("userid")  private String user_id;

    @SerializedName("result") private String result;

    @Override
    public String toString() {
        return "GetId{" +
                "user_id='" + user_id + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

}
