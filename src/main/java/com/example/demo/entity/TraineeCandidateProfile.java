package com.example.demo.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = { "trainee" }, callSuper = false)
@EqualsAndHashCode(exclude = { "trainee" }, callSuper = false)
@Entity
@Table(name = "TRAINEE_CANDIDATE_PROFILE")
public class TraineeCandidateProfile extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRAINEE_CANDIDATE_PROFILE_ID", columnDefinition = "INT")
	private Integer id;

	@OneToOne(mappedBy = "traineeCandidateProfile")
	private Trainee trainee;

	@Column(name = "FULLNAME", columnDefinition = "NVARCHAR(255)")
	private String fullName;
	
	@Column(name = "ACCOUNT", columnDefinition = "NVARCHAR(255)")
	private String account;

	@Temporal(TemporalType.DATE)
	@PastOrPresent(message = "Birthdate cannot be in the future")
	@Column(name = "DATE_OF_BIRTH")
	private Date dateOfBirth;

	@Column(name = "GENDER", columnDefinition = "NVARCHAR(255)")
	private String gender;

	@ManyToOne
	@JoinColumn(name = "UNIVERSITY_ID")
	private University university;

	@ManyToOne
	@JoinColumn(name = "FACULTY_ID")
	private Faculty faculty;

	@Column(name = "GRADUATION_YEAR", columnDefinition = "INT")
	@Range(min = 1900, max = 2999)
	private Integer graduationYear;

	@Column(name = "PHONE", columnDefinition = "NVARCHAR(255)", unique = true)
	@NotEmpty(message = "Phone cannot be empty")
	private String phone;

	@Column(name = "EMAIL", columnDefinition = "NVARCHAR(255)", unique = true)
	@NotEmpty(message = "Email cannot be empty")
	private String email;

	@Column(name = "TYPE", columnDefinition = "NVARCHAR(255)")
	private String type;

	@Column(name = "SKILL", columnDefinition = "NVARCHAR(255)")
	private String skill;

	@Column(name = "FOREIGN_LANGUAGE", columnDefinition = "NVARCHAR(255)")
	private String foreignLanguage;

	@Column(name = "LEVEL", columnDefinition = "NVARCHAR(255)")
	private Integer level;

	@Column(name = "CV", columnDefinition = "INT")
	private Integer CV;

	@Column(name = "ALLOCATION_STATUS", columnDefinition = "INT")
	private Integer allocationStatus;

	@Column(name = "REMARKS", columnDefinition = "NVARCHAR(255)")
	private String remarks;

}
