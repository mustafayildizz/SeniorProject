package com.example.seniorproject.Retrofit.Models;

public class GetUser{
	private String password;
	private String phone;
	private String userid;
	private String username;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setUserid(String userid){
		this.userid = userid;
	}

	public String getUserid(){
		return userid;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"GetUser{" + 
			"password = '" + password + '\'' + 
			",phone = '" + phone + '\'' + 
			",userid = '" + userid + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}
