package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.ClassStatus;
import com.example.demo.entity.Location;
import com.example.demo.entity.Status;
import com.example.demo.repository.ClassStatusRepository;
import com.example.demo.repository.LocationRepository;
import com.example.demo.repository.StatusRepository;

@Controller
public class DashboardController {
	
	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	ClassStatusRepository classStatusRepository;
	
	@Autowired
	StatusRepository statusRepository;
	
	@RequestMapping(path = { "/dashboard" }, method = RequestMethod.GET)
	public String getDashboardPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString) {
		
		List<Location> listOfLocations = locationRepository.findAll();
		List<ClassStatus> listOfClassStatuses = classStatusRepository.findAll();
		List<Status> listOfStatuses = statusRepository.findAll();
		
		model.addAttribute(errorString, listOfStatuses);
		
		
		model.addAttribute("errorString", errorString);
		return "dashboard";
	}

}
