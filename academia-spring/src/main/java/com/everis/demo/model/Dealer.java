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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "t_dealer")
public class Dealer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "dealer_id")
	private Long id;

	@Column
	private String dealer_fiscal_name;

	@Column
	private String dealer_id_partner;

	@Column
	private String dealer_tradename;

	@Column
	private String dealer_fiscal_residence;

	@Column
	private String dealer_customer_service_phone;
	
	@Column
	private String dealer_customer_service_email;
	
	@Column
	private String dealer_url;
	
	@Column
	private String dealer_privacy_policy_url;
	
	@Column
	private String dealer_list_data_category_url;
	
	@Column
	private String dealer_signature_email_reception_box;
	
	@Column
	private LocalDateTime create_date;
	
	@Column
	private LocalDateTime update_date;
	
	@Column
	private LocalDateTime delete_date;
	
	@Column
	private boolean is_delete;
	
	@Column
	private String dealer_entities;
	
	@Column
	private String dealer_user_id_dms;
	
	@Column
	private String dealer_pwd_dms;
	
	@Column
	private Integer dealer_in_catalan;
	
	@Column
	private String dealer_url_dms;
	
	@Column
	private Integer dealer_consent_dealer;
	
	@Column
	private Integer dealer_consent_nsc;
}
