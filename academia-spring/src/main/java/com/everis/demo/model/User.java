package com.everis.demo.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "t_user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "user_id")
	private Long id;
	
	@Column
	private String user_email;
	
	@Column
	private String user_password;
	
	@Column
	private String user_name;
	
	@Column
	private String user_lastname1;
	
	@Column
	private String user_lastname2;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dealer_id")
	//@Column(name = "user_partner")
	private Dealer userPartner;
	
	//@Column(name = "user_department")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id")
	private Department user_department;
	
	@Column(name ="create_date")
	private LocalDateTime createDate;
	
	@Column(name ="update_date")
	private LocalDateTime updateDate;
	
	@Column(name = "delete_date")
	private LocalDateTime deleteDate;
	
	@Column(name = "is_delete")
	private boolean isDelete;
	
	@Column
	private LocalDateTime access_date;
	
	@Column(name ="is_recovery")
	private boolean isRecovery;
	
	@Column(name ="is_active")
	private boolean isActive;
	
	@Column
	private String name;
	
	@Column(name = "pswd_create_date")
	private LocalDateTime pswdCreateDate;
	
	@ManyToMany(mappedBy = "users")
	private Set<Profile> profiles;
}
