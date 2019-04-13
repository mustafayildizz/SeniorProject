package com.example.seniorproject.Retrofit.Models.DarkSky;

import java.util.List;

public class Flags{
	private double nearestStation;
	private List<String> sources;
	private String units;

	public void setNearestStation(double nearestStation){
		this.nearestStation = nearestStation;
	}

	public double getNearestStation(){
		return nearestStation;
	}

	public void setSources(List<String> sources){
		this.sources = sources;
	}

	public List<String> getSources(){
		return sources;
	}

	public void setUnits(String units){
		this.units = units;
	}

	public String getUnits(){
		return units;
	}

	@Override
 	public String toString(){
		return 
			"Flags{" + 
			"nearest-station = '" + nearestStation + '\'' + 
			",sources = '" + sources + '\'' + 
			",units = '" + units + '\'' + 
			"}";
		}
}