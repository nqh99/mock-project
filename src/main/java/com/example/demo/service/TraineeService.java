package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Trainee;
import com.example.demo.model.TraineeCriteriaModel;

public interface TraineeService {

	List<Trainee> filterTraineeSearchCriteria(TraineeCriteriaModel traineeCriteriaModel);

}
