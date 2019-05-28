package com.example.seniorproject.Retrofit.Models;

public class ProductInfo{
	private String info;

	public void setInfo(String info){
		this.info = info;
	}

	public String getInfo(){
		return info;
	}

	@Override
 	public String toString(){
		return 
			"ProductInfo{" + 
			"info = '" + info + '\'' + 
			"}";
		}
}
