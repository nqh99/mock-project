package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.ClassBatch;
import com.example.demo.model.ClassBatchCriteriaModel;
import com.example.demo.model.ClassBatchViewModel;

public interface ClassBatchService {

	List<ClassBatch> filterClassSearchCriteria(ClassBatchCriteriaModel classBatchCriteriaModel);

	ClassBatchViewModel convertToViewModel(ClassBatch b);

	boolean changeClassProgression(String classBatchId, List<String> requiredStatuses, String newStatus,
			String userFullName, String completeMessage, String remarksContent);

	boolean changeClassProgression(String classBatchId, List<String> requiredStatuses, String newStatus,
			String userFullName, String completeMessage);

}
