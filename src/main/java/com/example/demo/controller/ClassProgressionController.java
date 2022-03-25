package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.example.demo.service.ClassBatchServiceImpl;

@Controller
public class ClassProgressionController {

	@Autowired
	ClassBatchRepository classBatchRepository;

	@Autowired
	ClassStatusRepository classStatusRepository;

	@Autowired
	ClassBatchServiceImpl classBatchService;

	@Autowired
	ClassViewController classView;

	@RequestMapping(path = { "/class_submit" }, method = RequestMethod.GET)
	public String getSubmitClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "classBatchId", required = false) String classBatchId,
			@SessionAttribute(name = "userFullName", required = false) String userFullName) {

		List<String> requiredStatus = new ArrayList<String>();
		requiredStatus.add("Draft");
		requiredStatus.add("Rejected");
		requiredStatus.add("Declined");
		String newStatus = "Submitted";
		String completeMessage = "Submitted";

		if (classBatchService.changeClassProgression(classBatchId, requiredStatus, newStatus, userFullName,
				completeMessage)) {
			return classView.getViewClassPage(model, errorString, completeMessage + " successfully.", classBatchId,
					Optional.ofNullable(null), Optional.ofNullable(null));
		}

		return classView.getViewClassPage(
				model, errorString, "This class's status must be '" + requiredStatus.toString() + "' to be "
						+ completeMessage.toLowerCase() + ".",
				classBatchId, Optional.ofNullable(null), Optional.ofNullable(null));
	}

	@RequestMapping(path = { "/class_approve" }, method = RequestMethod.GET)
	public String getApproveClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "classBatchId", required = false) String classBatchId,
			@SessionAttribute(name = "userFullName", required = false) String userFullName) {

		List<String> requiredStatus = new ArrayList<String>();
		requiredStatus.add("Submitted");
		String newStatus = "Planning";
		String completeMessage = "Approved";

		if (classBatchService.changeClassProgression(classBatchId, requiredStatus, newStatus, userFullName,
				completeMessage)) {
			return classView.getViewClassPage(model, errorString, completeMessage + " successfully.", classBatchId,
					Optional.ofNullable(null), Optional.ofNullable(null));
		}

		return classView.getViewClassPage(
				model, errorString, "This class's status must be '" + requiredStatus.toString() + "' to be "
						+ completeMessage.toLowerCase() + ".",
				classBatchId, Optional.ofNullable(null), Optional.ofNullable(null));
	}

	@RequestMapping(path = { "/class_accept" }, method = RequestMethod.GET)
	public String getAcceptClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "classBatchId", required = false) String classBatchId,
			@SessionAttribute(name = "userFullName", required = false) String userFullName) {

		List<String> requiredStatus = new ArrayList<String>();
		requiredStatus.add("Planning");
		String newStatus = "Planned";
		String completeMessage = "Accepted";

		if (classBatchService.changeClassProgression(classBatchId, requiredStatus, newStatus, userFullName,
				completeMessage)) {
			return classView.getViewClassPage(model, errorString, completeMessage + " successfully.", classBatchId,
					Optional.ofNullable(null), Optional.ofNullable(null));
		}

		return classView.getViewClassPage(
				model, errorString, "This class's status must be '" + requiredStatus.toString() + "' to be "
						+ completeMessage.toLowerCase() + ".",
				classBatchId, Optional.ofNullable(null), Optional.ofNullable(null));
	}

	@RequestMapping(path = { "/class_start" }, method = RequestMethod.GET)
	public String getStartClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "classBatchId", required = false) String classBatchId,
			@SessionAttribute(name = "userFullName", required = false) String userFullName) {

		List<String> requiredStatus = new ArrayList<String>();
		requiredStatus.add("Planned");
		String newStatus = "In-progress";
		String completeMessage = "Started";

		if (classBatchService.changeClassProgression(classBatchId, requiredStatus, newStatus, userFullName,
				completeMessage)) {
			return classView.getViewClassPage(model, errorString, completeMessage + " successfully.", classBatchId,
					Optional.ofNullable(null), Optional.ofNullable(null));
		}

		return classView.getViewClassPage(
				model, errorString, "This class's status must be '" + requiredStatus.toString() + "' to be "
						+ completeMessage.toLowerCase() + ".",
				classBatchId, Optional.ofNullable(null), Optional.ofNullable(null));
	}

	@RequestMapping(path = { "/class_finish" }, method = RequestMethod.GET)
	public String getFinishClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "classBatchId", required = false) String classBatchId,
			@SessionAttribute(name = "userFullName", required = false) String userFullName) {

		List<String> requiredStatus = new ArrayList<String>();
		requiredStatus.add("In-progress");
		requiredStatus.add("Waiting for more information");
		String newStatus = "Pending for review";
		String completeMessage = "Finished";

		if (classBatchService.changeClassProgression(classBatchId, requiredStatus, newStatus, userFullName,
				completeMessage)) {
			return classView.getViewClassPage(model, errorString, completeMessage + " successfully.", classBatchId,
					Optional.ofNullable(null), Optional.ofNullable(null));
		}

		return classView.getViewClassPage(
				model, errorString, "This class's status must be '" + requiredStatus.toString() + "' to be "
						+ completeMessage.toLowerCase() + ".",
				classBatchId, Optional.ofNullable(null), Optional.ofNullable(null));
	}

	@RequestMapping(path = { "/class_close" }, method = RequestMethod.GET)
	public String getCloseClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "classBatchId", required = false) String classBatchId,
			@SessionAttribute(name = "userFullName", required = false) String userFullName) {

		List<String> requiredStatus = new ArrayList<String>();
		requiredStatus.add("Pending for review");
		String newStatus = "Closed";
		String completeMessage = "Closed";

		if (classBatchService.changeClassProgression(classBatchId, requiredStatus, newStatus, userFullName,
				completeMessage)) {
			return classView.getViewClassPage(model, errorString, completeMessage + " successfully.", classBatchId,
					Optional.ofNullable(null), Optional.ofNullable(null));
		}

		return classView.getViewClassPage(
				model, errorString, "This class's status must be '" + requiredStatus.toString() + "' to be "
						+ completeMessage.toLowerCase() + ".",
				classBatchId, Optional.ofNullable(null), Optional.ofNullable(null));
	}

	@RequestMapping(path = { "/class_cancel" }, method = RequestMethod.GET)
	public String getCancelClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "classBatchId", required = false) String classBatchId,
			@SessionAttribute(name = "userFullName", required = false) String userFullName) {

		List<String> requiredStatus = new ArrayList<String>();
		requiredStatus.add("Draft");
		requiredStatus.add("Submitted");
		String newStatus = "Cancelled";
		String completeMessage = "Cancelled";

		if (classBatchService.changeClassProgression(classBatchId, requiredStatus, newStatus, userFullName,
				completeMessage)) {
			return classView.getViewClassPage(model, errorString, completeMessage + " successfully.", classBatchId,
					Optional.ofNullable(null), Optional.ofNullable(null));
		}

		return classView.getViewClassPage(
				model, errorString, "This class's status must be '" + requiredStatus.toString() + "' to be "
						+ completeMessage.toLowerCase() + ".",
				classBatchId, Optional.ofNullable(null), Optional.ofNullable(null));
	}

	@RequestMapping(path = { "/class_reject" }, method = RequestMethod.POST)
	public String postRejectClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "rejectClassBatchId", required = false) String classBatchId,
			@SessionAttribute(name = "userFullName", required = false) String userFullName,
			@RequestParam(name = "inputRemarks", required = false) String inputRemarks) {

		List<String> requiredStatus = new ArrayList<String>();
		requiredStatus.add("Submitted");
		String newStatus = "Rejected";
		String completeMessage = "Rejected";

		if (classBatchService.changeClassProgression(classBatchId, requiredStatus, newStatus, userFullName,
				completeMessage, inputRemarks)) {
			return classView.getViewClassPage(model, errorString, completeMessage + " successfully.", classBatchId,
					Optional.ofNullable(null), Optional.ofNullable(null));
		}

		return classView.getViewClassPage(model, errorString,
				"This class's status must be '" + requiredStatus + "' to be " + completeMessage.toLowerCase() + ".",
				classBatchId, Optional.ofNullable(null), Optional.ofNullable(null));
	}

	@RequestMapping(path = { "/class_decline" }, method = RequestMethod.POST)
	public String postDeclineClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "declineClassBatchId", required = false) String classBatchId,
			@SessionAttribute(name = "userFullName", required = false) String userFullName,
			@RequestParam(name = "inputRemarks", required = false) String inputRemarks) {

		List<String> requiredStatus = new ArrayList<String>();
		requiredStatus.add("Planning");
		String newStatus = "Declined";
		String completeMessage = "Declined";

		if (classBatchService.changeClassProgression(classBatchId, requiredStatus, newStatus, userFullName,
				completeMessage, inputRemarks)) {
			return classView.getViewClassPage(model, errorString, completeMessage + " successfully.", classBatchId,
					Optional.ofNullable(null), Optional.ofNullable(null));
		}

		return classView.getViewClassPage(model, errorString,
				"This class's status must be '" + requiredStatus + "' to be " + completeMessage.toLowerCase() + ".",
				classBatchId, Optional.ofNullable(null), Optional.ofNullable(null));
	}

	@RequestMapping(path = { "/class_requestInfo" }, method = RequestMethod.POST)
	public String postRequestInfoClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "requestInfoClassBatchId", required = false) String classBatchId,
			@SessionAttribute(name = "userFullName", required = false) String userFullName,
			@RequestParam(name = "inputRemarks", required = false) String inputRemarks) {

		List<String> requiredStatus = new ArrayList<String>();
		requiredStatus.add("Pending for review");
		String newStatus = "Waiting for more information";
		String completeMessage = "Requested";

		if (classBatchService.changeClassProgression(classBatchId, requiredStatus, newStatus, userFullName,
				completeMessage, inputRemarks)) {
			return classView.getViewClassPage(model, errorString, completeMessage + " successfully.", classBatchId,
					Optional.ofNullable(null), Optional.ofNullable(null));
		}

		return classView.getViewClassPage(model, errorString,
				"This class's status must be '" + requiredStatus + "' to be " + completeMessage.toLowerCase() + ".",
				classBatchId, Optional.ofNullable(null), Optional.ofNullable(null));

	}

}
