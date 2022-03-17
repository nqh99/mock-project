package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = { "classBatch", "classAdminProfile" }, callSuper = false)
@EqualsAndHashCode(exclude = { "classBatch", "classAdminProfile" }, callSuper = false)
@Entity
@Table(name = "CLASS_ADMIN")
@NamedNativeQueries({
		@NamedNativeQuery(name = "FIND_CLASS_ADMIN_BY_USERNAME", query = "SELECT * FROM CLASS_ADMIN WHERE USER_NAME = :userName", resultClass = ClassAdmin.class),
		@NamedNativeQuery(name = "FIND_CLASS_ADMIN_BY_USERNAME_PASSWORD", query = "SELECT * FROM CLASS_ADMIN WHERE USER_NAME = :userName AND PASSWORD = :password", resultClass = ClassAdmin.class), })
public class ClassAdmin extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLASS_ADMIN_ID", columnDefinition = "INT")
	private Integer id;

	@OneToOne(mappedBy = "classAdmin")
	private ClassBatch classBatch;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CLASS_ADMIN_PROFILE_ID")
	private ClassAdminProfile classAdminProfile;

	@Column(name = "REMARKS", columnDefinition = "INT")
	private Integer remarks;

}
