package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ClassBatch;

@Repository
public interface ClassBatchRepository extends JpaRepository<ClassBatch, Integer> {

	ClassBatch findByClassName(String className);

	ClassBatch findByClassCode(String classCode);

	List<ClassBatch> findAllByLocation_LocationNameIsAndClassNameIsAndClassStatus_ClassStatusNameIsAndActualStartDateAfterAndActualEndDateBefore(
			String locationName, String className, String classStatusName, Date fromDate, Date toDate);

	List<ClassBatch> findAllByClassName(String className);

	List<ClassBatch> findAllByLocation_LocationName(String locationName);

	List<ClassBatch> findAllByClassStatus_ClassStatusName(String classStatusName);

	List<ClassBatch> findAllByActualStartDateAfter(Date fromDate);

	List<ClassBatch> findAllByActualEndDateBefore(Date toDate);

	List<ClassBatch> findAllByActualStartDateAfterAndActualEndDateBefore(Date fromDate, Date toDate);
}
