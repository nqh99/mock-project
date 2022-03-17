package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = { "traineeCandidateProfile" }, callSuper = false)
@EqualsAndHashCode(exclude = { "traineeCandidateProfile" }, callSuper = false)
@Entity
@Table(name = "UNIVERSITY")
public class University extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UNIVERSITY_ID", columnDefinition = "INT")
	private Integer id;
	
	@Column(name = "UNIVERSITY_NAME", columnDefinition = "NVARCHAR(255)")
	private String universityName;
	
	@Column(name = "REMARKS", columnDefinition = "INT")
	private Integer remarks;
	
	@OneToOne(mappedBy = "university")
	private TraineeCandidateProfile traineeCandidateProfile;

}
