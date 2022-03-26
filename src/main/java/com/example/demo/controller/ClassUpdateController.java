package com.example.demo.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClassUpdateController {
	
	@Autowired
	ClassListingController classList;
	
	@RequestMapping(path = { "/class_update_redirect" }, method = RequestMethod.POST)
	public String postUpdateClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "selectedClassBatch", required = false) String classBatchId,
			@RequestParam(name = "buttonType", required = false) String buttonType) {

		if (Objects.isNull(classBatchId) ||  classBatchId.isBlank() || classBatchId.isEmpty()) {
			model.addAttribute("errorString", "Please select a class first.");
			return classList.getClassListingPage(model, "Please select a class first.", Optional.ofNullable(null), Optional.ofNullable(null));
		}
		
		else {
//			Đi tới trang class update
			return "trang_class_update";
		}
	}
	
	

}
