package com.example.seniorproject.Retrofit.Models;

public class GetField{
	private String fieldname;
	private String desiredproduct;
	private String region;
	private String fieldid;

	public void setFieldname(String fieldname){
		this.fieldname = fieldname;
	}

	public String getFieldname(){
		return fieldname;
	}

	public void setDesiredproduct(String desiredproduct){
		this.desiredproduct = desiredproduct;
	}

	public String getDesiredproduct(){
		return desiredproduct;
	}

	public void setRegion(String region){
		this.region = region;
	}

	public String getRegion(){
		return region;
	}

	public void setFieldid(String fieldid){
		this.fieldid = fieldid;
	}

	public String getFieldid(){
		return fieldid;
	}

	@Override
 	public String toString(){
		return 
			"GetField{" + 
			"fieldname = '" + fieldname + '\'' + 
			",desiredproduct = '" + desiredproduct + '\'' + 
			",region = '" + region + '\'' + 
			",fieldid = '" + fieldid + '\'' + 
			"}";
		}
}
