package com.userfront.service.userserviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userfront.dao.AppointmentDao;
import com.userfront.domain.Appointment;
import com.userfront.service.AppointmentService;

 
/**
 * The Class AppointmentServiceImpl.
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {

	/** The appointment dao. */
	@Autowired
	private AppointmentDao appointmentDao;

	/**
	 * Creates the appointment.
	 *
	 * @param appointment the appointment
	 * @return the appointment
	 */
	public Appointment createAppointment(Appointment appointment) {
		return appointmentDao.save(appointment);
	}

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Appointment> findAll() {
		return appointmentDao.findAll();
	}

	/**
	 * Find appointment.
	 *
	 * @param id the id
	 * @return the appointment
	 */
	public Appointment findAppointment(Long id) {
		return null;
	}

	/**
	 * Confirm appointment.
	 *
	 * @param id the id
	 */
	public void confirmAppointment(Long id) {
		Appointment appointment = findAppointment(id);
		appointment.setConfirmed(true);
		appointmentDao.save(appointment);
	}
}
