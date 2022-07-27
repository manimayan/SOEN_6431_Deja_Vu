package com.userfront.dao;

import org.springframework.data.repository.CrudRepository;

import com.userfront.domain.security.Role;

 
/**
 * The Interface RoleDao.
 */
public interface RoleDao extends CrudRepository<Role, Integer> {
    
    /**
     * Find by name.
     *
     * @param name the name
     * @return the role
     */
    Role findByName(String name);
}
