package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ClassBatch;
import com.example.demo.entity.Trainee;
import com.example.demo.model.TraineeCriteriaModel;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.utils.DateUtils;

@Service
public class TraineeServiceImpl implements TraineeService {

	@Autowired
	TraineeRepository traineeRepository;

	@Override
	public List<Trainee> filterTraineeSearchCriteria(TraineeCriteriaModel traineeCriteriaModel) {
		Date dateOfBirth = null;
		System.out.println(traineeCriteriaModel.toString());
		if (Objects.isNull(traineeCriteriaModel.getDateOfBirth()) || traineeCriteriaModel.getDateOfBirth().isEmpty()
				|| traineeCriteriaModel.getDateOfBirth().isBlank()) {
			System.out.println("Date is null");
		} else {
			dateOfBirth = DateUtils.parseDDMMYYYYDateFromString(traineeCriteriaModel.getDateOfBirth());
		}

		List<Trainee> listOfAllTrainees = traineeRepository.findAll();
		List<Trainee> listOfMatchedId = new ArrayList<Trainee>();
		List<Trainee> listOfMatchedAccount = new ArrayList<Trainee>();
		List<Trainee> listOfMatchedName = new ArrayList<Trainee>();
		List<Trainee> listOfMatchedDOB = new ArrayList<Trainee>();
		List<Trainee> listOfMatchedPhone = new ArrayList<Trainee>();
		List<Trainee> listOfMatchedEmail = new ArrayList<Trainee>();

		if (!Objects.isNull(dateOfBirth)) {
			listOfMatchedDOB = traineeRepository.findAllByTraineeCandidateProfile_DateOfBirth(dateOfBirth);
		}

		if (Objects.isNull(traineeCriteriaModel.getEmplId()) || traineeCriteriaModel.getEmplId().isEmpty()
				|| traineeCriteriaModel.getEmplId().isBlank()) {
		} else {
			listOfMatchedId = traineeRepository.findAllById(Integer.valueOf(traineeCriteriaModel.getEmplId()));
		}

		if (Objects.isNull(traineeCriteriaModel.getAccount()) || traineeCriteriaModel.getAccount().isEmpty()
				|| traineeCriteriaModel.getAccount().isBlank()) {
		} else {
			listOfMatchedAccount = traineeRepository.findAllByAccountContaining(traineeCriteriaModel.getAccount());
		}

		if (Objects.isNull(traineeCriteriaModel.getName()) || traineeCriteriaModel.getName().isEmpty()
				|| traineeCriteriaModel.getName().isBlank()) {
		} else {
			listOfMatchedName = traineeRepository
					.findAllByTraineeCandidateProfile_FullNameContaining(traineeCriteriaModel.getName());
		}

		if (Objects.isNull(traineeCriteriaModel.getPhone()) || traineeCriteriaModel.getPhone().isEmpty()
				|| traineeCriteriaModel.getPhone().isBlank()) {
		} else {
			listOfMatchedPhone = traineeRepository
					.findAllByTraineeCandidateProfile_PhoneContaining(traineeCriteriaModel.getPhone());
		}

		if (Objects.isNull(traineeCriteriaModel.getEmail()) || traineeCriteriaModel.getEmail().isEmpty()
				|| traineeCriteriaModel.getEmail().isBlank()) {
		} else {
			listOfMatchedEmail = traineeRepository
					.findAllByTraineeCandidateProfile_EmailContaining(traineeCriteriaModel.getEmail());
		}

		Set<Trainee> searchResult = listOfAllTrainees.stream().distinct().filter(listOfMatchedId::contains)
				.filter(listOfMatchedAccount::contains).filter(listOfMatchedName::contains)
				.filter(listOfMatchedPhone::contains).filter(listOfMatchedEmail::contains)
				.filter(listOfMatchedDOB::contains).collect(Collectors.toSet());

		return new ArrayList<Trainee>(searchResult);

	}

}
