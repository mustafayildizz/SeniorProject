package com.example.seniorproject.Retrofit.Models.ThingSpeak.ThingSpeakHum;

import java.util.List;

public class ThingSpeakHum{
	private Channel channel;
	private List<FeedsItem> feeds;

	public void setChannel(Channel channel){
		this.channel = channel;
	}

	public Channel getChannel(){
		return channel;
	}

	public void setFeeds(List<FeedsItem> feeds){
		this.feeds = feeds;
	}

	public List<FeedsItem> getFeeds(){
		return feeds;
	}

	@Override
 	public String toString(){
		return 
			"ThingSpeakHum{" + 
			"channel = '" + channel + '\'' + 
			",feeds = '" + feeds + '\'' + 
			"}";
		}
}