package com.example.seniorproject.Retrofit.Models.DarkSky;

public class Currently{
	private String summary;
	private float precipProbability;
	private double visibility;
	private double windGust;
	private float precipIntensity;
	private String icon;
	private float cloudCover;
	private int windBearing;
	private double apparentTemperature;
	private double pressure;
	private double dewPoint;
	private double ozone;
	private int nearestStormBearing;
	private int nearestStormDistance;
	private double temperature;
	private double humidity;
	private double time;
	private double windSpeed;
	private int uvIndex;

	public void setSummary(String summary){
		this.summary = summary;
	}

	public String getSummary(){
		return summary;
	}

	public void setPrecipProbability(int precipProbability){
		this.precipProbability = precipProbability;
	}

	public float getPrecipProbability(){
		return precipProbability;
	}

	public void setVisibility(double visibility){
		this.visibility = visibility;
	}

	public double getVisibility(){
		return visibility;
	}

	public void setWindGust(double windGust){
		this.windGust = windGust;
	}

	public double getWindGust(){
		return windGust;
	}

	public void setPrecipIntensity(int precipIntensity){
		this.precipIntensity = precipIntensity;
	}

	public float getPrecipIntensity(){
		return precipIntensity;
	}

	public void setIcon(String icon){
		this.icon = icon;
	}

	public String getIcon(){
		return icon;
	}

	public void setCloudCover(int cloudCover){
		this.cloudCover = cloudCover;
	}

	public float getCloudCover(){
		return cloudCover;
	}

	public void setWindBearing(int windBearing){
		this.windBearing = windBearing;
	}

	public int getWindBearing(){
		return windBearing;
	}

	public void setApparentTemperature(double apparentTemperature){
		this.apparentTemperature = apparentTemperature;
	}

	public double getApparentTemperature(){
		return apparentTemperature;
	}

	public void setPressure(double pressure){
		this.pressure = pressure;
	}

	public double getPressure(){
		return pressure;
	}

	public void setDewPoint(double dewPoint){
		this.dewPoint = dewPoint;
	}

	public double getDewPoint(){
		return dewPoint;
	}

	public void setOzone(double ozone){
		this.ozone = ozone;
	}

	public double getOzone(){
		return ozone;
	}

	public void setNearestStormBearing(int nearestStormBearing){
		this.nearestStormBearing = nearestStormBearing;
	}

	public int getNearestStormBearing(){
		return nearestStormBearing;
	}

	public void setNearestStormDistance(int nearestStormDistance){
		this.nearestStormDistance = nearestStormDistance;
	}

	public int getNearestStormDistance(){
		return nearestStormDistance;
	}

	public void setTemperature(double temperature){
		this.temperature = temperature;
	}

	public double getTemperature(){
		return temperature;
	}

	public void setHumidity(double humidity){
		this.humidity = humidity;
	}

	public double getHumidity(){
		return humidity;
	}

	public void setTime(int time){
		this.time = time;
	}

	public double getTime(){
		return time;
	}

	public void setWindSpeed(double windSpeed){
		this.windSpeed = windSpeed;
	}

	public double getWindSpeed(){
		return windSpeed;
	}

	public void setUvIndex(int uvIndex){
		this.uvIndex = uvIndex;
	}

	public int getUvIndex(){
		return uvIndex;
	}

	@Override
 	public String toString(){
		return 
			"Currently{" + 
			"summary = '" + summary + '\'' + 
			",precipProbability = '" + precipProbability + '\'' + 
			",visibility = '" + visibility + '\'' + 
			",windGust = '" + windGust + '\'' + 
			",precipIntensity = '" + precipIntensity + '\'' + 
			",icon = '" + icon + '\'' + 
			",cloudCover = '" + cloudCover + '\'' + 
			",windBearing = '" + windBearing + '\'' + 
			",apparentTemperature = '" + apparentTemperature + '\'' + 
			",pressure = '" + pressure + '\'' + 
			",dewPoint = '" + dewPoint + '\'' + 
			",ozone = '" + ozone + '\'' + 
			",nearestStormBearing = '" + nearestStormBearing + '\'' + 
			",nearestStormDistance = '" + nearestStormDistance + '\'' + 
			",temperature = '" + temperature + '\'' + 
			",humidity = '" + humidity + '\'' + 
			",time = '" + time + '\'' + 
			",windSpeed = '" + windSpeed + '\'' + 
			",uvIndex = '" + uvIndex + '\'' + 
			"}";
		}
}
