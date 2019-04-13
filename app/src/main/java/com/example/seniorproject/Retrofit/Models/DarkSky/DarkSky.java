package com.example.seniorproject.Retrofit.Models.DarkSky;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DarkSky{
	@SerializedName("currently")
	private Currently currently;
	private int offset;
	private String timezone;
	private double latitude;
	private Daily daily;
	private Flags flags;
	private Hourly hourly;
	private Minutely minutely;
	private double longitude;

	public void setCurrently(Currently currently){
		this.currently = currently;
	}

	public Currently getCurrently(){
		return currently;
	}

	public void setOffset(int offset){
		this.offset = offset;
	}

	public int getOffset(){
		return offset;
	}

	public void setTimezone(String timezone){
		this.timezone = timezone;
	}

	public String getTimezone(){
		return timezone;
	}

	public void setLatitude(double latitude){
		this.latitude = latitude;
	}

	public double getLatitude(){
		return latitude;
	}

	public void setDaily(Daily daily){
		this.daily = daily;
	}

	public Daily getDaily(){
		return daily;
	}

	public void setFlags(Flags flags){
		this.flags = flags;
	}

	public Flags getFlags(){
		return flags;
	}

	public void setHourly(Hourly hourly){
		this.hourly = hourly;
	}

	public Hourly getHourly(){
		return hourly;
	}

	public void setMinutely(Minutely minutely){
		this.minutely = minutely;
	}

	public Minutely getMinutely(){
		return minutely;
	}

	public void setLongitude(double longitude){
		this.longitude = longitude;
	}

	public double getLongitude(){
		return longitude;
	}

	@Override
 	public String toString(){
		return 
			"DarkSky{" + 
			"currently = '" + currently + '\'' + 
			",offset = '" + offset + '\'' + 
			",timezone = '" + timezone + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",daily = '" + daily + '\'' + 
			",flags = '" + flags + '\'' + 
			",hourly = '" + hourly + '\'' + 
			",minutely = '" + minutely + '\'' + 
			",longitude = '" + longitude + '\'' + 
			"}";
		}
}
