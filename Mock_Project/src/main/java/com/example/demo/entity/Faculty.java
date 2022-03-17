package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = { "setOfTraineeCandidateProfiles" }, callSuper = false)
@EqualsAndHashCode(exclude = { "setOfTraineeCandidateProfiles" }, callSuper = false)
@Entity
@Table(name = "FACULTY")
public class Faculty extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UNIVERSITY_ID", columnDefinition = "INT")
	private Integer id;
	
	@Column(name = "FACULTY_NAME", columnDefinition = "NVARCHAR(255)")
	private String facultyName;
	
	@Column(name = "REMARKS", columnDefinition = "INT")
	private Integer remarks;
	
	@OneToMany(mappedBy = "faculty")
	private Set<TraineeCandidateProfile> setOfTraineeCandidateProfiles;

}
