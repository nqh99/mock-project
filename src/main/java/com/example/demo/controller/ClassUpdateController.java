package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClassUpdateController {
	
	@RequestMapping(path = { "/class_update" }, method = RequestMethod.POST)
	public String postUpdateClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "selectedClassBatch", required = false) String selectedClassBatch,
			@RequestParam(name = "buttonType", required = false) String buttonType) {

		System.out.println(selectedClassBatch);
		System.out.println(buttonType);
		return null;
	}
	
	@RequestMapping(path = { "/class_cancel" }, method = RequestMethod.POST)
	public String postCancelClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "selectedClassBatch", required = false) String selectedClassBatch,
			@RequestParam(name = "buttonType", required = false) String buttonType) {

		System.out.println(selectedClassBatch);
		System.out.println(buttonType);
		return null;
	}

}
