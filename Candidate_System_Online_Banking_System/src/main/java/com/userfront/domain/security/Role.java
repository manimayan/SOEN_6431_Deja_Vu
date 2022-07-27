package com.userfront.domain.security;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * The Class Role.
 */
@Entity

/**
 * Instantiates a new role.
 */
@Data
public class Role {

	/** The role id. */
	@Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
	private int roleId;

	/** The name. */
	private String name;

	/** The user roles. */
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<UserRole> userRoles = new HashSet<>();

}
