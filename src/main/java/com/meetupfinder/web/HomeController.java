package com.meetupfinder.web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class HomeController {

	private Meetup [] meetings;
	
	@GetMapping("")
	public String homePage(ModelMap model) {
		Location location = new Location();
		model.put("location", location); 
		model.put("meetings", meetings);
		meetings = null;
		return "home";
	}
	
	
	@PostMapping("")
	public String homePage(@ModelAttribute Location location, ModelMap model) throws JsonParseException, JsonMappingException, IOException {
		if(location.getCity().trim().length() == 0 || location.getTopic().trim().length() == 0) {
			model.put("message", "you need to enter both parameters");
			return "home";
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		Location cityLoc = returnLocation(location, objectMapper);
		
		if(cityLoc == null) {
			model.put("message", "You did not enter a walid request");
			return "home";
		}
		
		cityLoc.setTopic(location.getTopic());
		System.out.println(cityLoc);
		
		meetings = returnMeetings(cityLoc, objectMapper);
		if(meetings == null) {
			model.put("message", "No topics matched your request");
			return "home";
		}
		else if(meetings.length == 0) {
			model.put("message", String.format("No %s meeting in %s", cityLoc.getTopic(),cityLoc.getCity()));
			return "home";
		}
		
		return "redirect:/";
	}
	
	private Location returnLocation( Location location, ObjectMapper objectMapper) throws IOException{
		String tempCity = location.getCity().trim().replaceAll(" ", "+");
		
		String city = "https://api.meetup.com/2/cities?offset=0&query="+ tempCity 
				+"&format=json&photo-host=public&page=10&radius=50&order=size&desc=false";
		
		URL urlClass = new URL(city);
		
		JsonNode arrNode = new ObjectMapper().readTree(urlClass).get("results").get(0);
		
		if(arrNode == null) return null;
		
		return objectMapper.readValue(arrNode.toString(), Location.class);
	}
	
	private Meetup[] returnMeetings(Location cityLoc, ObjectMapper objectMapper) throws JsonParseException, JsonMappingException, IOException{
		
		String meetingUrl = "https://api.meetup.com/2/groups?offset=0&format=json&lon="+cityLoc.getLon()+
				"&topic="+cityLoc.getTopic()+"&photo-host=public&page=20&radius=25.0&fields=&lat="+cityLoc.getLat()+
				"&order=id&desc=false&sig_id=246595295&sig=f6bda051287a4ffecf70cc6fefc70c1964827b09";
		
		URL urlClass = new URL(meetingUrl);
		JsonNode arrNode = null;
		
		try {
			arrNode = new ObjectMapper().readTree(urlClass).get("results");
		} catch (IOException e) {
			return null;
		}
		
		return objectMapper.readValue(arrNode.toString(), Meetup[].class);
	}
}
