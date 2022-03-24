package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.ClassBatch;
import com.example.demo.entity.ClassStatus;
import com.example.demo.entity.Location;
import com.example.demo.model.ClassBatchCriteriaModel;
import com.example.demo.repository.ClassBatchRepository;
import com.example.demo.repository.ClassStatusRepository;
import com.example.demo.repository.LocationRepository;
import com.example.demo.service.ClassBatchServiceImpl;

@Controller
public class ClassListingController {

	@Autowired
	ClassBatchRepository classBatchRepository;

	@Autowired
	LocationRepository locationRepository;

	@Autowired
	ClassStatusRepository classStatusRepository;

	@Autowired
	ClassBatchServiceImpl classBatchService;

	@RequestMapping(path = { "/class_management" }, method = RequestMethod.GET)
	public String getClassListingPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "size", required = false) Optional<Integer> size) {

		Integer defaultPageSize = 2;
		
		System.out.println("page: " + page);
		System.out.println("size: " + size);
		Integer currentPage = page.orElse(1);
		Integer pageSize = size.orElse(defaultPageSize);
		ClassBatchCriteriaModel classBatchCriteriaModel = new ClassBatchCriteriaModel();
		
		List<Location> listOfLocations = locationRepository.findAll();
		List<ClassStatus> listOfClassStatuses = classStatusRepository.findAll();
		List<ClassBatch> listOfClassBatches = classBatchRepository.findAll();
		List<String> listOfClassNames = new ArrayList<String>();
		for (ClassBatch clazz : listOfClassBatches) {
			listOfClassNames.add(clazz.getClassName());
		}
		//Paging
		Page<ClassBatch> classBatchPage = classBatchService.findPaginated(listOfClassBatches,
				PageRequest.of(currentPage - 1, pageSize));
		
		List<Integer> pageNumbers = new ArrayList<Integer>();
		Integer totalPages = classBatchPage.getTotalPages();
		Integer totalNumberOfClassBatches = 0;
		Integer startNumberOfCurrentPage = 0;
		Integer endNumberOfCurrentPage = 0;

		if (totalPages > 0) {
			pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			totalNumberOfClassBatches = listOfClassBatches.size();
			startNumberOfCurrentPage = currentPage*pageSize + 1 - pageSize;
			endNumberOfCurrentPage = startNumberOfCurrentPage + classBatchPage.getNumberOfElements() - 1;
		} else {
			model.addAttribute("errorString", "Nothing found.");
		}

		model.addAttribute("pageNumbers", pageNumbers);
		model.addAttribute("classBatchPage", classBatchPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalNumberOfClassBatches", totalNumberOfClassBatches);
		model.addAttribute("startNumberOfCurrentPage", startNumberOfCurrentPage);
		model.addAttribute("endNumberOfCurrentPage", endNumberOfCurrentPage);

		model.addAttribute("listOfClassBatches", listOfClassBatches);
		model.addAttribute("listOfLocations", listOfLocations);
		model.addAttribute("listOfClassStatuses", listOfClassStatuses);
		model.addAttribute("listOfClassNames", listOfClassNames);
		
		model.addAttribute("classBatchCriteriaModel", classBatchCriteriaModel);
		return "class_listing_paged.html";
	}
}
