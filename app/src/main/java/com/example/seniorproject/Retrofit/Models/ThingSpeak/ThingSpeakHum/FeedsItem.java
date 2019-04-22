package com.example.seniorproject.Retrofit.Models.ThingSpeak.ThingSpeakHum;

public class FeedsItem{
	private String createdAt;
	private int entryId;
	private String field2;

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setEntryId(int entryId){
		this.entryId = entryId;
	}

	public int getEntryId(){
		return entryId;
	}

	public void setField2(String field2){
		this.field2 = field2;
	}

	public String getField2(){
		return field2;
	}

	@Override
 	public String toString(){
		return 
			"FeedsItem{" + 
			"created_at = '" + createdAt + '\'' + 
			",entry_id = '" + entryId + '\'' + 
			",field2 = '" + field2 + '\'' + 
			"}";
		}
}
