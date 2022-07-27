package com.userfront.service.userserviceimpl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userfront.dao.RoleDao;
import com.userfront.dao.UserDao;
import com.userfront.domain.User;
import com.userfront.domain.security.UserRole;
import com.userfront.service.AccountService;
import com.userfront.service.UserService;

/**
 * The Class UserServiceImpl.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	/** The user dao. */
	@Autowired
	private UserDao userDao;

	/** The role dao. */
	@Autowired
	private RoleDao roleDao;

	/** The password encoder. */
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/** The account service. */
	@Autowired
	private AccountService accountService;

	/**
	 * Save.
	 *
	 * @param user the user
	 */
	public void save(User user) {
		userDao.save(user);
	}

	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the user
	 */
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the user
	 */
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	/**
	 * Creates the user.
	 *
	 * @param user      the user
	 * @param userRoles the user roles
	 * @return the user
	 */
	public User createUser(User user, Set<UserRole> userRoles) {
		User localUser = userDao.findByUsername(user.getUsername());

		if (localUser != null) {
			LOG.info("User with username {} already exist. Nothing will be done. ", user.getUsername());
		} else {
			String encryptedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encryptedPassword);

			for (UserRole ur : userRoles) {
				roleDao.save(ur.getRole());
			}

			user.getUserRoles().addAll(userRoles);

			user.setPrimaryAccount(accountService.createPrimaryAccount());
			user.setSavingsAccount(accountService.createSavingsAccount());

			localUser = userDao.save(user);
		}

		return localUser;
	}

	/**
	 * Check user exists.
	 *
	 * @param username the username
	 * @param email    the email
	 * @return true, if successful
	 */
	public boolean checkUserExists(String username, String email) {
		if (checkUsernameExists(username) || checkEmailExists(username)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check username exists.
	 *
	 * @param username the username
	 * @return true, if successful
	 */
	public boolean checkUsernameExists(String username) {
		if (null != findByUsername(username)) {
			return true;
		}

		return false;
	}

	/**
	 * Check email exists.
	 *
	 * @param email the email
	 * @return true, if successful
	 */
	public boolean checkEmailExists(String email) {
		if (null != findByEmail(email)) {
			return true;
		}

		return false;
	}

	/**
	 * Save user.
	 *
	 * @param user the user
	 * @return the user
	 */
	public User saveUser(User user) {
		return userDao.save(user);
	}

	/**
	 * Find user list.
	 *
	 * @return the list
	 */
	public List<User> findUserList() {
		return userDao.findAll();
	}

	/**
	 * Enable user.
	 *
	 * @param username the username
	 */
	public void enableUser(String username) {
		User user = findByUsername(username);
		user.setEnabled(true);
		userDao.save(user);
	}

	/**
	 * Disable user.
	 *
	 * @param username the username
	 */
	public void disableUser(String username) {
		User user = findByUsername(username);
		user.setEnabled(false);
		userDao.save(user);
	}
}
