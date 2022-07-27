package com.userfront.service;

import java.util.List;
import java.util.Set;

import com.userfront.domain.User;
import com.userfront.domain.security.UserRole;

 
/**
 * The Interface UserService.
 */
public interface UserService {
	
	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the user
	 */
	User findByUsername(String username);

    /**
     * Find by email.
     *
     * @param email the email
     * @return the user
     */
    User findByEmail(String email);

    /**
     * Check user exists.
     *
     * @param username the username
     * @param email the email
     * @return true, if successful
     */
    boolean checkUserExists(String username, String email);

    /**
     * Check username exists.
     *
     * @param username the username
     * @return true, if successful
     */
    boolean checkUsernameExists(String username);

    /**
     * Check email exists.
     *
     * @param email the email
     * @return true, if successful
     */
    boolean checkEmailExists(String email);
    
    /**
     * Save.
     *
     * @param user the user
     */
    void save (User user);
    
    /**
     * Creates the user.
     *
     * @param user the user
     * @param userRoles the user roles
     * @return the user
     */
    User createUser(User user, Set<UserRole> userRoles);
    
    /**
     * Save user.
     *
     * @param user the user
     * @return the user
     */
    User saveUser (User user); 
    
    /**
     * Find user list.
     *
     * @return the list
     */
    List<User> findUserList();

    /**
     * Enable user.
     *
     * @param username the username
     */
    void enableUser (String username);

    /**
     * Disable user.
     *
     * @param username the username
     */
    void disableUser (String username);
}
