package com.everis.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "t_user_operation_record")
public class UserOperationRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "operation_record_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	//@Column(name = "operation_record_user_id")
	private User user;
	
	@Column
	private LocalDateTime operation_record_date;
	
	//@Column(name = "user_operation_type")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "operation_record_type")
	private UserOperationType userOperationType;
	
	@Column(name ="operation_record_system_log")
	private String log;
	
	@Column
	private LocalDateTime create_date;
	
	@Column
	private LocalDateTime update_date;
	
	@Column
	private LocalDateTime delete_date;
	
	@Column(name ="is_delete")
	private boolean isDelete;
	
	@Column(name = "code_message")
	private String codeMessage;
}
