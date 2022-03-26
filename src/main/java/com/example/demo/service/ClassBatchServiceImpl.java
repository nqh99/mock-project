package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ClassBatch;
import com.example.demo.model.ClassBatchCriteriaModel;
import com.example.demo.model.ClassBatchViewModel;
import com.example.demo.repository.ClassBatchRepository;
import com.example.demo.repository.ClassStatusRepository;
import com.example.demo.utils.DateUtils;
import com.example.demo.utils.StringValidateUtils;

@Service
public class ClassBatchServiceImpl implements ClassBatchService {

	@Autowired
	ClassBatchRepository classBatchRepository;

	@Autowired
	ClassStatusRepository classStatusRepository;

	@Override
	public List<ClassBatch> filterClassSearchCriteria(ClassBatchCriteriaModel classBatchCriteriaModel) {
		Date toDate = null;
		Date fromDate = null;

		List<String> dateStrings = new ArrayList<String>();
		dateStrings.add(classBatchCriteriaModel.getFromDate());
		dateStrings.add(classBatchCriteriaModel.getToDate());
		System.out.println("model: " + classBatchCriteriaModel.toString());

		if (Objects.isNull(classBatchCriteriaModel.getFromDate()) || Objects.isNull(classBatchCriteriaModel.getToDate())
				|| StringValidateUtils.hasBadString(dateStrings)) {
			System.out.println("date is null");
		} else {
			toDate = DateUtils.parseDDMMYYYYDateFromString(classBatchCriteriaModel.getToDate());
			fromDate = DateUtils.parseDDMMYYYYDateFromString(classBatchCriteriaModel.getFromDate());
		}
		List<ClassBatch> listOfAllClassBatches = classBatchRepository.findAll();
		List<ClassBatch> listOfMatchedLocation = new ArrayList<ClassBatch>();
		List<ClassBatch> listOfMatchedClassStatus = new ArrayList<ClassBatch>();
		List<ClassBatch> listOfMatchedClassName = new ArrayList<ClassBatch>();
		List<ClassBatch> listOfMatchedDate = new ArrayList<ClassBatch>();

		if (Objects.equals(classBatchCriteriaModel.getLocationName(), "All")) {
			listOfMatchedLocation = listOfAllClassBatches;
		} else {
			listOfMatchedLocation = classBatchRepository
					.findAllByLocation_LocationName(classBatchCriteriaModel.getLocationName());
		}

		if (Objects.equals(classBatchCriteriaModel.getClassStatus(), "All")) {
			listOfMatchedClassStatus = listOfAllClassBatches;
		} else {
			listOfMatchedClassStatus = classBatchRepository
					.findAllByClassStatus_ClassStatusName(classBatchCriteriaModel.getClassStatus());
		}

		if (Objects.isNull(fromDate) || Objects.isNull(toDate) || StringValidateUtils.hasBadString(dateStrings)) {
			listOfMatchedDate = listOfAllClassBatches;
		} else {
			listOfMatchedDate = classBatchRepository.findAllByActualStartDateAfterAndActualEndDateBefore(fromDate,
					toDate);
		}

		if (Objects.equals(classBatchCriteriaModel.getClassName(), "All")) {
			listOfMatchedClassName = listOfAllClassBatches;
		} else {
			listOfMatchedClassName = classBatchRepository.findAllByClassName(classBatchCriteriaModel.getClassName());
		}

		Set<ClassBatch> searchResult = listOfAllClassBatches.stream().distinct().filter(listOfMatchedLocation::contains)
				.filter(listOfMatchedClassStatus::contains).filter(listOfMatchedDate::contains)
				.filter(listOfMatchedClassName::contains).collect(Collectors.toSet());

		return new ArrayList<ClassBatch>(searchResult);
	}

	@Override
	public boolean changeClassProgression(String classBatchId, List<String> requiredStatuses, String newStatus,
			String userFullName, String completeMessage) {

		ClassBatch classBatch = classBatchRepository.findById(Integer.valueOf(classBatchId)).get();
		for (String status : requiredStatuses) {
			if (Objects.equals(classBatch.getClassStatus().getClassStatusName(), status)) {
				classBatch.setClassStatus(classStatusRepository.findByClassStatusName(newStatus));
				classBatch.setHistory(String.valueOf(new Date()) + " - " + completeMessage + " by " + userFullName);
				if (Objects.equals(completeMessage, "Started")) {
					classBatch.setActualStartDate(new Date());
					System.out
							.println("Set class's actual start date to " + classBatch.getActualStartDate().toString());
				}
				if (Objects.equals(completeMessage, "Finished")) {
					classBatch.setActualEndDate(new Date());
					System.out.println("Set class's actual end date to " + classBatch.getActualEndDate().toString());
				}
				classBatchRepository.save(classBatch);
				System.out.println("Class " + classBatch.getClassCode() + completeMessage + " by " + userFullName);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean changeClassProgression(String classBatchId, List<String> requiredStatuses, String newStatus,
			String userFullName, String completeMessage, String remarksContent) {

		ClassBatch classBatch = classBatchRepository.findById(Integer.valueOf(classBatchId)).get();
		for (String status : requiredStatuses) {
			if (Objects.equals(classBatch.getClassStatus().getClassStatusName(), status)) {
				classBatch.setClassStatus(classStatusRepository.findByClassStatusName(newStatus));
				classBatch.setHistory(String.valueOf(new Date()) + " - " + completeMessage + " by " + userFullName
						+ " - " + remarksContent);
				if (Objects.equals(completeMessage, "Started")) {
					classBatch.setActualStartDate(new Date());
					System.out
							.println("Set class's actual start date to " + classBatch.getActualStartDate().toString());
				}
				if (Objects.equals(completeMessage, "Finished")) {
					classBatch.setActualEndDate(new Date());
					System.out.println("Set class's actual end date to " + classBatch.getActualEndDate().toString());
				}
				classBatchRepository.save(classBatch);
				System.out.println("Class " + classBatch.getClassCode() + completeMessage + " by " + userFullName);
				return true;
			}
		}
		return false;
	}

	@Override
	public ClassBatchViewModel convertToViewModel(ClassBatch b) {
		ClassBatchViewModel a = new ClassBatchViewModel();
		a.setId(b.getId());
		a.setClassCode(b.getClassCode());
		a.setClassName(b.getClassName());
		a.setClassStatus(b.getClassStatus().getClassStatusName());
		a.setPlannedTraineeNo(b.getPlannedTraineeNumber());
		a.setAcceptedTraineeNo(b.getAcceptedTraineeNumber());
		a.setActualTraineeNo(b.getActualTraineeNumber());
		a.setExpectedStartDate(b.getExpectedStartDate());
		a.setExpectedEndDate(b.getExpectedEndDate());
		a.setActualStartDate(b.getActualStartDate());
		a.setActualEndDate(b.getActualEndDate());
		a.setLocationName(b.getLocation().getLocationName());
		a.setDetailedLocation(b.getDetailLocation());
		a.setBudgetCode(b.getBudget().getBudgetName());
		a.setEstimatedBudget(b.getEstimatedBudget());
		a.setClassAdmin(b.getClassAdmin().getAccount());

		a.setHistory(b.getHistory());

		return a;

	}

}
