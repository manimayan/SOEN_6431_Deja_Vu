package com.userfront.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.userfront.domain.Appointment;
import com.userfront.domain.User;
import com.userfront.service.AppointmentService;
import com.userfront.service.UserService;

/**
 * The Class AppointmentController.
 */
@Controller
@RequestMapping("/appointment")
public class AppointmentController {

	/** The appointment service. */
	@Autowired
	private AppointmentService appointmentService;

	/** The user service. */
	@Autowired
	private UserService userService;

	/**
	 * Creates the appointment.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping(value = "/create")
	public String createAppointment(Model model) {
		Appointment appointment = new Appointment();
		model.addAttribute("appointment", appointment);
		model.addAttribute("dateString", "");

		return "appointment";
	}

	/**
	 * Creates the appointment post.
	 *
	 * @param appointment the appointment
	 * @param date        the date
	 * @param model       the model
	 * @param principal   the principal
	 * @return the string
	 * @throws ParseException the parse exception
	 */
	@PostMapping(value = "/create")
	public String createAppointmentPost(@ModelAttribute("appointment") Appointment appointment,
			@ModelAttribute("dateString") String date, Model model, Principal principal) throws ParseException {

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.CANADA);
		Date d1 = format1.parse(date);
		appointment.setDate(d1);

		User user = userService.findByUsername(principal.getName());
		appointment.setUser(user);

		appointmentService.createAppointment(appointment);

		return "redirect:/userFront";
	}

}
