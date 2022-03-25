package com.example.demo.controller;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.demo.entity.ClassBatch;
import com.example.demo.repository.ClassBatchRepository;
import com.example.demo.repository.ClassStatusRepository;

@Controller
public class ClassSubmitController {

	@Autowired
	ClassBatchRepository classBatchRepository;

	@Autowired
	ClassStatusRepository classStatusRepository;

	@Autowired
	ClassViewController classView;

	@RequestMapping(path = { "/class_submit" }, method = RequestMethod.GET)
	public String getSubmitClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "classBatchId", required = false) String classBatchId,
			@SessionAttribute(name = "userFullName", required = false) String userFullName) {

		ClassBatch classBatch = classBatchRepository.findById(Integer.valueOf(classBatchId)).get();

		if (Objects.equals(classBatch.getClassStatus().getClassStatusName(), "Draft")) {
			classBatch.setClassStatus(classStatusRepository.findByClassStatusName("Submitted"));
			classBatch.setHistory(String.valueOf(new Date()) + " - Submitted by - " + userFullName);
			System.out.println("Submitted by " + userFullName);
			return classView.getViewClassPage(model, errorString, "Successfully submitted.", classBatchId,
					Optional.ofNullable(null), Optional.ofNullable(null));
		}
		
		return classView.getViewClassPage(model, errorString, "This class must be a Draft in order to submit.", classBatchId,
				Optional.ofNullable(null), Optional.ofNullable(null));

	}

}
