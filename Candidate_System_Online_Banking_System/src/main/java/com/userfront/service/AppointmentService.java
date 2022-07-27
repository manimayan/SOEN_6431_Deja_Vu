package com.userfront.service;

import java.util.List;

import com.userfront.domain.Appointment;

 
/**
 * The Interface AppointmentService.
 */
public interface AppointmentService {
	
	/**
	 * Creates the appointment.
	 *
	 * @param appointment the appointment
	 * @return the appointment
	 */
	Appointment createAppointment(Appointment appointment);

    /**
     * Find all.
     *
     * @return the list
     */
    List<Appointment> findAll();

    /**
     * Find appointment.
     *
     * @param id the id
     * @return the appointment
     */
    Appointment findAppointment(Long id);

    /**
     * Confirm appointment.
     *
     * @param id the id
     */
    void confirmAppointment(Long id);
}
