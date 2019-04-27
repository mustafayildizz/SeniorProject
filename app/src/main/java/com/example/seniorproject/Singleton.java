package com.example.seniorproject;

public class Singleton {
    private String fieldName;
    private String region;
    private String desiredproduct;
    private String weather;
    private String user_id;
    private String location;
    private String getFiedlId;

    public String getGetFiedlId() {
        return getFiedlId;
    }

    public void setGetFiedlId(String getFiedlId) {
        this.getFiedlId = getFiedlId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDesiredproduct() {
        return desiredproduct;
    }

    public void setDesiredproduct(String desiredproduct) {
        this.desiredproduct = desiredproduct;
    }

    public static Singleton getInstance() {
        return instance;
    }

    public static void setInstance(Singleton instance) {
        Singleton.instance = instance;
    }

    private static Singleton instance;

    public Singleton(String fieldName, String region, String desiredproduct) {
        this.fieldName = fieldName;
        this.region = region;
        this.desiredproduct = desiredproduct;
    }


    public Singleton() {
    }

    public static Singleton getSingleton() {
        if (instance == null)
            instance = new Singleton();
        return instance;
    }
}
