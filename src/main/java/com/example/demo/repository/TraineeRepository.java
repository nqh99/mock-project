package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Trainee;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Integer> {

	List<Trainee> findAllByTraineeCandidateProfile_DateOfBirth(Date dateOfBirth);

	List<Trainee> findAllByTraineeCandidateProfile_PhoneContaining(String phone);

	List<Trainee> findAllByTraineeCandidateProfile_EmailContaining(String email);

	List<Trainee> findAllById(Integer id);

	List<Trainee> findAllByAccountContaining(String account);

	List<Trainee> findAllByTraineeCandidateProfile_FullNameContaining(String fullName);

	List<Trainee> findAllByIdOrTraineeCandidateProfile_FullNameContainingOrAccountContainingOrTraineeCandidateProfile_PhoneContainingOrTraineeCandidateProfile_EmailContaining(
			Integer id, String fullname, String account, String phone, String email);

}
