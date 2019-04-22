package com.example.seniorproject.Retrofit.Models.ThingSpeak;

public class FeedsItem{
	private String field1;
	private String createdAt;
	private int entryId;

	public void setField1(String field1){
		this.field1 = field1;
	}

	public String getField1(){
		return field1;
	}

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

	@Override
 	public String toString(){
		return 
			"FeedsItem{" + 
			"field1 = '" + field1 + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",entry_id = '" + entryId + '\'' + 
			"}";
		}
}
