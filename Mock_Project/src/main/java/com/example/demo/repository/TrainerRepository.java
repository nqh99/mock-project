package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
	
	Trainer findByUsername(String username);
	
	Trainer findByTrainerProfile_FullNameIs (String fullName);
}
