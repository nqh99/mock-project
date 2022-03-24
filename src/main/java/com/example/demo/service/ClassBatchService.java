package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.ClassBatch;
import com.example.demo.model.ClassBatchCriteriaModel;

public interface ClassBatchService {

	List<ClassBatch> filterSearchCriteria(ClassBatchCriteriaModel classBatchCriteriaModel);

}
