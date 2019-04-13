package com.example.seniorproject;

public class Detail_List {
    String fieldName;
    String weather;
    String region;
    String desiredProduct;

    public Detail_List(String fieldName, String weather, String region, String desiredProduct) {
        this.fieldName = fieldName;
        this.weather = weather;
        this.region = region;
        this.desiredProduct = desiredProduct;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDesiredProduct() {
        return desiredProduct;
    }

    public void setDesiredProduct(String desiredProduct) {
        this.desiredProduct = desiredProduct;
    }

}
