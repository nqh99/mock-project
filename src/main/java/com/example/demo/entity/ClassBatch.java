package com.example.demo.entity;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = { "setOfTrainees", "setOfTrainers", "audit"}, callSuper = false)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "CLASS_BATCH")
public class ClassBatch extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLASS_ID", columnDefinition = "INT")
	private Integer id;
	
	@Column(name = "CLASS_NAME", columnDefinition = "NVARCHAR(255)", unique = true)
	@NotEmpty(message = "Class name cannot be empty")
	private String className;
	
	@Column(name = "CLASS_CODE", columnDefinition = "NVARCHAR(255)", unique = true)
	@NotEmpty(message = "Class code cannot be empty")
	private String classCode;
	
	@ManyToOne
	@JoinColumn(name = "BUDGET_ID")
	private Budget budget;
	
	@ManyToOne
	@JoinColumn(name = "LOCATION_ID")
	private Location location;
	
	@ManyToOne
	@JoinColumn(name = "SUBJECT_TYPE_ID")
	private SubjectType subjectType;
	
	@ManyToOne
	@JoinColumn(name = "SUB_SUBJECT_TYPE_ID")
	private SubSubjectType subSubjectType;
	
	@ManyToOne
	@JoinColumn(name = "DELIVERY_TYPE_ID")
	private DeliveryType deliveryType;
	
	@ManyToOne
	@JoinColumn(name = "FORMAT_TYPE_ID")
	private FormatType formatType;
	
	@ManyToOne
	@JoinColumn(name = "SCOPE_ID")
	private Scope scope;
	
	@ManyToOne
	@JoinColumn(name = "CLASS_STATUS_ID")
	private ClassStatus classStatus;
	
	@ManyToOne
	@JoinColumn(name = "SUPPLIER_PARTNER_ID")
	private SupplierPartner supplierPartner;
	
	@OneToOne
	@JoinColumn(name = "AUDIT_ID")
	private Audit audit;
	
	@ManyToOne
	@JoinColumn(name = "CLASS_ADMIN_ID")
	private ClassAdmin classAdmin;
	
	@Column(name = "DETAIL_LOCATION", columnDefinition = "NVARCHAR(255)")
	private String detailLocation;
	
	@Column(name = "ESTIMATED_BUDGET", columnDefinition = "INT")
	private Integer estimatedBudget;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "EXPECTED_START_DATE")
	private Date expectedStartDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "EXPECTED_END_DATE")
	private Date expectedEndDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "ACTUAL_START_DATE")
	private Date actualStartDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "ACTUAL_END_DATE")
	private Date actualEndDate;
	
	@Column(name = "PLANNED_TRAINEE_NUMBER", columnDefinition = "INT")
	private Integer plannedTraineeNumber;
	
	@Column(name = "ACCEPTED_TRAINEE_NUMBER", columnDefinition = "INT")
	private Integer acceptedTraineeNumber;
	
	@Column(name = "ACTUAL_TRAINEE_NUMBER", columnDefinition = "INT")
	private Integer actualTraineeNumber;
	
	@ManyToMany(mappedBy = "setOfClassBatches")
	private Set<Trainer> setOfTrainers;
	
	@ManyToOne
	@JoinColumn(name = "MASTER_TRAINER_ID")
	private Trainer masterTrainer;
	
	@Column(name = "MILESTONES", columnDefinition = "INT")
	private Integer milestones;
	
	@OneToMany(mappedBy = "classBatch")
	private Set<Trainee> setOfTrainees;
	
	@Column(name = "STATUS", columnDefinition = "NVARCHAR(255)")
	private String status;
	
	@Column(name = "REMARKS", columnDefinition = "NVARCHAR(255)")
	private String remarks;
	
	@Column(name = "HISTORY", columnDefinition = "NVARCHAR(255)")
	private String history;
	
	@ManyToOne
	@JoinColumn(name = "LEARNING_PATH_ID")
	private LearningPath learningPath;
	
	@Column(name = "CURRICULUM", columnDefinition = "NVARCHAR(MAX)")
	private String curriculum;
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassBatch other = (ClassBatch) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	

	
}
