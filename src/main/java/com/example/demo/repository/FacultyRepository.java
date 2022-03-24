package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

	Faculty findByFacultyName(String facultyName);
}
