package com.userfront.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.userfront.domain.Recipient;

 
/**
 * The Interface RecipientDao.
 */
public interface RecipientDao extends CrudRepository<Recipient, Long> {
    
    /**
     * Find all.
     *
     * @return the list
     */
    List<Recipient> findAll();

    /**
     * Find by name.
     *
     * @param recipientName the recipient name
     * @return the recipient
     */
    Recipient findByName(String recipientName);

    /**
     * Delete by name.
     *
     * @param recipientName the recipient name
     */
    void deleteByName(String recipientName);
}
