package com.example.seniorproject.Retrofit.Models.ThingSpeak.ThingSpeakHum;

public class Channel{
	private String field1;
	private String updatedAt;
	private int lastEntryId;
	private String latitude;
	private String name;
	private String createdAt;
	private int id;
	private String field3;
	private String field2;
	private String longitude;

	public void setField1(String field1){
		this.field1 = field1;
	}

	public String getField1(){
		return field1;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setLastEntryId(int lastEntryId){
		this.lastEntryId = lastEntryId;
	}

	public int getLastEntryId(){
		return lastEntryId;
	}

	public void setLatitude(String latitude){
		this.latitude = latitude;
	}

	public String getLatitude(){
		return latitude;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setField3(String field3){
		this.field3 = field3;
	}

	public String getField3(){
		return field3;
	}

	public void setField2(String field2){
		this.field2 = field2;
	}

	public String getField2(){
		return field2;
	}

	public void setLongitude(String longitude){
		this.longitude = longitude;
	}

	public String getLongitude(){
		return longitude;
	}

	@Override
 	public String toString(){
		return 
			"Channel{" + 
			"field1 = '" + field1 + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",last_entry_id = '" + lastEntryId + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",name = '" + name + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",field3 = '" + field3 + '\'' + 
			",field2 = '" + field2 + '\'' + 
			",longitude = '" + longitude + '\'' + 
			"}";
		}
}
