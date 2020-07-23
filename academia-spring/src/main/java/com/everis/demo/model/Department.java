package com.everis.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "t_department")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "department_id")
	private Long id;
	
	@Column(name = "departement_name")
	private String department_name;
	
	@Column(name = "create_date")
	private LocalDateTime create_date;
	
	@Column(name = "update_date")
	private LocalDateTime update_date;
	
	@Column(name = "delete_date")
	private LocalDateTime delete_date;
	
	@Column(name = "is_delete")
	private boolean is_delete;
}
