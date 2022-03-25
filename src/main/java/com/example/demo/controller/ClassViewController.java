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
import com.example.demo.entity.Trainee;
import com.example.demo.model.ClassBatchViewModel;
import com.example.demo.model.TraineeCriteriaModel;
import com.example.demo.repository.ClassBatchRepository;
import com.example.demo.service.ClassBatchServiceImpl;
import com.example.demo.utils.PaginationUtils;

@Controller
public class ClassViewController {

	@Autowired
	ClassBatchRepository classBatchRepository;

	@Autowired
	ClassBatchServiceImpl classBatchService;

	@RequestMapping(path = { "/class_view" }, method = RequestMethod.GET)
	public String getViewClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "resultString", required = false) String resultString,
			@RequestParam(name = "classBatchId", required = false) String classBatchId,
			@RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "size", required = false) Optional<Integer> size) {

		Integer defaultPageSize = 2;

		System.out.println("page: " + page);
		System.out.println("size: " + size);
		Integer currentPage = page.orElse(1);
		Integer pageSize = size.orElse(defaultPageSize);
		
		System.out.println(classBatchId);
		ClassBatch classBatch = classBatchRepository.findById(Integer.valueOf(classBatchId)).get();
		System.out.println(classBatch.toString());
		ClassBatchViewModel classBatchViewModel = classBatchService.convertToViewModel(classBatch);
		
		List<Trainee> listOfTrainee = new ArrayList<Trainee>(classBatch.getSetOfTrainees());
		Page<Trainee> traineePage = PaginationUtils.findPaginated(listOfTrainee, PageRequest.of(currentPage - 1, pageSize));
		
		List<Integer> pageNumbers = new ArrayList<Integer>();
		Integer totalPages = traineePage.getTotalPages();
		Integer totalNumberOfTrainees = 0;
		Integer startNumberOfCurrentPage = 0;
		Integer endNumberOfCurrentPage = 0;

		if (totalPages > 0) {
			pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			totalNumberOfTrainees = listOfTrainee.size();
			startNumberOfCurrentPage = currentPage*pageSize + 1 - pageSize;
			endNumberOfCurrentPage = startNumberOfCurrentPage + traineePage.getNumberOfElements() - 1;
		} else {
			model.addAttribute("errorString", "Nothing found.");
		}
		model.addAttribute("pageNumbers", pageNumbers);
		model.addAttribute("traineePage", traineePage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalNumberOfTrainees", totalNumberOfTrainees);
		model.addAttribute("startNumberOfCurrentPage", startNumberOfCurrentPage);
		model.addAttribute("endNumberOfCurrentPage", endNumberOfCurrentPage);
		

		System.out.println(classBatchViewModel.toString());
		model.addAttribute("classBatchViewModel", classBatchViewModel);
		model.addAttribute("traineeCriteriaModel", new TraineeCriteriaModel());
		model.addAttribute("resultString", resultString);
		return "class_view_paged";
	}

}
