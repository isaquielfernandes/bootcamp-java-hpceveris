package com.everis.demo.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "t_user_operation_type")
public class UserOperationType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "operation_type_id")
	private Long Id;
	
	@Column(name = "operation_type_name")
	private String operation_type_name;
	
	@ManyToMany
	@JoinTable(name = "t_profile_operation",
    	joinColumns = @JoinColumn(name = "operation_type_id"),
    	inverseJoinColumns = @JoinColumn(name = "profile_id"))
	private Set<Profile> profiles;
}
