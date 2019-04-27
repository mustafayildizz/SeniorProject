package com.example.seniorproject.Retrofit.Models;

public class GetOutput{
	private String result;

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

	@Override
 	public String toString(){
		return 
			"GetOutput{" + 
			"result = '" + result + '\'' + 
			"}";
		}
}
