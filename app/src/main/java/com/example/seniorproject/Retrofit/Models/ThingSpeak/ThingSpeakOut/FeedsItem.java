package com.example.seniorproject.Retrofit.Models.ThingSpeak.ThingSpeakOut;

public class FeedsItem{
	private String createdAt;
	private String field3;
	private int entryId;

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setField3(String field3){
		this.field3 = field3;
	}

	public String getField3(){
		return field3;
	}

	public void setEntryId(int entryId){
		this.entryId = entryId;
	}

	public int getEntryId(){
		return entryId;
	}

	@Override
 	public String toString(){
		return 
			"FeedsItem{" + 
			"created_at = '" + createdAt + '\'' + 
			",field3 = '" + field3 + '\'' + 
			",entry_id = '" + entryId + '\'' + 
			"}";
		}
}
