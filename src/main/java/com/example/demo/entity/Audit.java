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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = { "classBatch", "pic", "relatedPartyPeople" }, callSuper = false)
@EqualsAndHashCode(exclude = { "classBatch", "pic", "relatedPartyPeople" }, callSuper = false)
@Entity
@Table(name = "AUDIT")
public class Audit extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AUDIT_ID", columnDefinition = "INT")
	private Integer id;

	@OneToOne(mappedBy = "audit")
	private ClassBatch classBatch;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE")
	private Date date;

	@Column(name = "EVENT_CATEGORY", columnDefinition = "NVARCHAR(255)")
	private String eventCategoty;

	@ManyToOne
	@JoinColumn(name = "RELATED_PARTY_PEOPLE_ID")
	private Trainer relatedPartyPeople;

	@Column(name = "ACTION", columnDefinition = "INT")
	private Integer action;

	@ManyToOne
	@JoinColumn(name = "PIC_ID")
//	@Column(name = "PIC")
	private Trainer pic;

	@Temporal(TemporalType.DATE)
	@Column(name = "DEADLINES")
	private Date deadline;

	@Column(name = "NOTE", columnDefinition = "NVARCHAR(255)")
	private String note;

}
