package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = { "setOfLearningPaths", "setOfAllowances", "setOfStatus", "setOfGPAs",
		"setOfRewardPenalties", "setOfGuarantees", "setOfInterviewValuations" })
@EqualsAndHashCode(exclude = { "setOfLearningPaths", "setOfAllowances", "setOfStatus", "setOfGPAs",
		"setOfRewardPenalties", "setOfGuarantees", "setOfInterviewValuations" }, callSuper = false)
@Entity
@Table(name = "TRAINEE")
public class Trainee extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRAINEE_CANDIDATE_ID", columnDefinition = "INT")
	private Integer id;

	@Column(name = "REMARKS", columnDefinition = "INT")
	private Integer remarks;

	@OneToMany(mappedBy = "trainee")
	private Set<LearningPath> setOfLearningPaths;

	@OneToMany(mappedBy = "trainee")
	private Set<Allowance> setOfAllowances;

	@OneToMany(mappedBy = "trainee")
	private Set<Status> setOfStatus;

	@OneToMany(mappedBy = "trainee")
	private Set<GPA> setOfGPAs;

	@OneToMany(mappedBy = "trainee")
	private Set<RewardPenalty> setOfRewardPenalties;

	@OneToMany(mappedBy = "trainee")
	private Set<Guarantee> setOfGuarantees;
	
	@OneToMany(mappedBy = "trainee")
	private Set<AttendantStatus> setOfAttendantStatus;
	
	@OneToMany(mappedBy = "trainee")
	private Set<InterviewValuation> setOfInterviewValuations;
	
	@ManyToOne
	@JoinColumn(name = "CLASS_BATCH_ID")
	private ClassBatch classBatch;
	
	@OneToOne
	@JoinColumn(name = "TRAINEE_CANDIDATE_PROFILE_ID")
	private TraineeCandidateProfile traineeCandidateProfile;
	

}
