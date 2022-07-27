package com.userfront.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.userfront.domain.User;

 
/**
 * The Interface UserDao.
 */
public interface UserDao extends CrudRepository<User, Long> {
	
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
     * Find all.
     *
     * @return the list
     */
    List<User> findAll();
}
