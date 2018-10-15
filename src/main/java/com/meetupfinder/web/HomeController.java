package com.meetupfinder.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

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
}
