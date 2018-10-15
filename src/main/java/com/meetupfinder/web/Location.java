package com.meetupfinder.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
	
	private String city;
	private String topic;
	private Double lat;
	private Double lon;
	
	@JsonProperty("city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	@JsonProperty("lat")
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	@JsonProperty("lon")
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
	
	@Override
	public String toString() {
		return "Location [city=" + city + ", topic=" + topic + ", lat=" + lat + ", lon=" + lon + "]";
	}
	

}
