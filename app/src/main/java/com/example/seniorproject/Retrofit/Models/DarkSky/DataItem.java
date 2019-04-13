package com.example.seniorproject.Retrofit.Models.DarkSky;

public class DataItem{
	private float precipProbability;
	private float precipIntensity;
	private double precipIntensityError;
	private String precipType;
	private int time;

	public void setPrecipProbability(int precipProbability){
		this.precipProbability = precipProbability;
	}

	public float getPrecipProbability(){
		return precipProbability;
	}

	public void setPrecipIntensity(int precipIntensity){
		this.precipIntensity = precipIntensity;
	}

	public float getPrecipIntensity(){
		return precipIntensity;
	}

	public void setPrecipIntensityError(double precipIntensityError){
		this.precipIntensityError = precipIntensityError;
	}

	public double getPrecipIntensityError(){
		return precipIntensityError;
	}

	public void setPrecipType(String precipType){
		this.precipType = precipType;
	}

	public String getPrecipType(){
		return precipType;
	}

	public void setTime(int time){
		this.time = time;
	}

	public int getTime(){
		return time;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"precipProbability = '" + precipProbability + '\'' + 
			",precipIntensity = '" + precipIntensity + '\'' + 
			",precipIntensityError = '" + precipIntensityError + '\'' + 
			",precipType = '" + precipType + '\'' + 
			",time = '" + time + '\'' + 
			"}";
		}
}
