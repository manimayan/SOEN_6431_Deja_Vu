package com.userfront.domain.security;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.userfront.domain.User;

import lombok.Data;

/**
 * The Class UserRole.
 */
@Entity
@Data
@Table(name = "user_role")
public class UserRole {

	/** The user role id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userRoleId;

	/**
	 * Instantiates a new user role.
	 *
	 * @param user the user
	 * @param role the role
	 */
	public UserRole(User user, Role role) {
		this.user = user;
		this.role = role;
	}

	/** The user. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	/** The role. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;

}
