package com.meetupfinder.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Meetup {
	
	private String country;
	private String city;
	private String link;
	private Double rating;
	private String description;
	private String name;
	
	@JsonProperty("country") 
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@JsonProperty("city") 
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@JsonProperty("link") 
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@JsonProperty("rating") 
	public Double getRating() {
		return rating;
	}
	public void setRatings(Double rating) {
		this.rating = rating;
	}
	@JsonProperty("description") 
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@JsonProperty("name") 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ResultClass [country=" + country + ", city=" + city + ", link=" + link + ", rating=" + rating
				+ ", description=" + description + ", name=" + name + "]";
	}
}
