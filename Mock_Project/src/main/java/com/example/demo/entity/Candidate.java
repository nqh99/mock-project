package com.example.demo.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = { "traineeCandidateProfile", "location", "setOfChannels",
"setOfOffers" }, callSuper = false)
@EqualsAndHashCode(exclude = { "traineeCandidateProfile", "location", "setOfChannels",
		"setOfOffers" }, callSuper = false)
@Entity
@Table(name = "CANDIDATE")
public class Candidate extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CANDIDATE_ID", columnDefinition = "INT")
	private Integer id;

	private TraineeCandidateProfile traineeCandidateProfile;

	@Temporal(TemporalType.DATE)
	@Column(name = "APPLICATION_DATE")
	private Date applicationDate;

	@OneToMany(mappedBy = "candidate")
	private Set<Channel> setOfChannels;

	@ManyToOne
	@JoinColumn(name = "LOCATION_ID")
	private Location location;

	@OneToMany(mappedBy = "candidate")
	private Set<EntryTest> setOfEntryTests;

	@OneToMany(mappedBy = "candidate")
	private Set<Interview> setOfInterviews;

	@OneToMany(mappedBy = "candidate")
	private Set<Offer> setOfOffers;

	@Column(name = "STATUS", columnDefinition = "NVARCHAR(255)")
	private String status;

	@Column(name = "REMARKS", columnDefinition = "NVARCHAR(255)")
	private String remarks;

}
