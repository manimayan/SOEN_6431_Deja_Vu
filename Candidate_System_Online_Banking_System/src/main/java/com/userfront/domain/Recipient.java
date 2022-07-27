package com.userfront.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * The Class Recipient.
 */
@Entity

/**
 * Instantiates a new recipient.
 */
@Data
public class Recipient {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** The name. */
	private String name;

	/** The email. */
	private String email;

	/** The phone. */
	private String phone;

	/** The account number. */
	private String accountNumber;

	/** The description. */
	private String description;

	/** The user. */
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

}
