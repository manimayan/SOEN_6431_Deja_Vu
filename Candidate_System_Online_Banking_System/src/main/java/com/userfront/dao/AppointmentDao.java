package com.userfront.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.userfront.domain.Appointment;

 
/**
 * The Interface AppointmentDao.
 */
public interface AppointmentDao extends CrudRepository<Appointment, Long> {

    /**
     * Find all.
     *
     * @return the list
     */
    List<Appointment> findAll();
}
