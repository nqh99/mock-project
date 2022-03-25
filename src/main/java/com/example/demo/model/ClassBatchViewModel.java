package com.example.demo.model;

import java.util.Date;

import com.example.demo.entity.BaseEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClassBatchViewModel extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String classCode;
	private String className;
	private String classStatus;
	private Integer plannedTraineeNo;
	private Integer acceptedTraineeNo;
	private Integer actualTraineeNo;
	private Date expectedStartDate;
	private Date expectedEndDate;
	private String locationName;
	private String detailedLocation;
	private String budgetCode;
	private Integer estimatedBudget;
	private String classAdmin;
	private String learningPathFileAddress;
	private String history;
	private String subjectType;
	private String subSubjectType;
	private String deliveryType;
	private String formatType;
	private String scope;
	private String supplierPartner;
	private Date actualStartDate;
	private Date actualEndDate;
	private String masterTrainer;
	private String trainers;
	private String curriculumFileAddress;
	private String remarks;
	private Integer total;
	private String overBudget;
	private String item;
	private String unit;
	private Integer unitExpense;
	private Integer quantity;
	private Integer amount;
	private Integer tax;
	private Integer sum;
	private String budgetNote;
	private Date auditDate;
	private String eventCategory;
	private String relatedPeople;
	private String action;
	private String pic;
	private String deadline;
	private String auditNote;

}
