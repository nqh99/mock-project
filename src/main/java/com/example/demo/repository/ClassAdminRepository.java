package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ClassAdmin;

@Repository
public interface ClassAdminRepository extends JpaRepository<ClassAdmin, Integer>{
	
	ClassAdmin findByUsername (String username);

	ClassAdmin findByClassAdminProfile_FullNameIs (String fullName);
}
