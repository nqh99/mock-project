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
@ToString(exclude = { "classBatch", "candidate" }, callSuper = false)
@EqualsAndHashCode(exclude = { "classBatch", "candidate" }, callSuper = false)
@Entity
@Table(name = "LOCATION")
public class Location extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LOCATION_ID", columnDefinition = "INT")
	private Integer id;

	@OneToOne(mappedBy = "location")
	private ClassBatch classBatch;

	@Column(name = "REMARKS", columnDefinition = "INT")
	private Integer remarks;

	@Column(name = "LOCATION_NAME", columnDefinition = "NVARCHAR(255)")
	private String locationName;

	@OneToOne(mappedBy = "location")
	private Candidate candidate;

}
