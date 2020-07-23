package com.everis.demo.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "t_profile")
public class Profile {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "profile_id")
	private Long id;
	
	@Column(name = "profile_name")
	private String profile_name;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		    name = "t_user_profile",
		    joinColumns = @JoinColumn(name = "user_id"),
		    inverseJoinColumns = @JoinColumn(name = "profile_id"))
	private Set<User> users;
	
	@ManyToMany(mappedBy = "profiles", fetch = FetchType.LAZY)
	private Set<UserOperationType> userOperationTypes;
}
