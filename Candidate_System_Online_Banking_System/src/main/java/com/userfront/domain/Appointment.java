package com.userfront.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class Appointment.
 */
@Entity

/**
 * Instantiates a new appointment.
 */

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data

/**
 * Instantiates a new appointment.
 *
 * @param id the id
 * @param date the date
 * @param location the location
 * @param description the description
 * @param confirmed the confirmed
 * @param user the user
 */
@AllArgsConstructor

/**
 * Instantiates a new appointment.
 */
@NoArgsConstructor
public class Appointment {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** The date. */
	private Date date;

	/** The location. */
	private String location;

	/** The description. */
	private String description;

	/** The confirmed. */
	private boolean confirmed;

	/** The user. */
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	/**
	 * Instantiates a new appointment.
	 *
	 * @param appointment the appointment
	 */
	public Appointment(Appointment appointment) {
		this.user = appointment.user;
		this.confirmed = appointment.confirmed;
		this.date = appointment.date;
		this.description = appointment.description;
		this.id = appointment.id;
		this.location = appointment.location;
	}
}
