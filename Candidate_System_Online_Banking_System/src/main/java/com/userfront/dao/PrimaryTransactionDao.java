package com.userfront.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.userfront.domain.PrimaryTransaction;

 
/**
 * The Interface PrimaryTransactionDao.
 */
public interface PrimaryTransactionDao extends CrudRepository<PrimaryTransaction, Long> {

    /**
     * Find all.
     *
     * @return the list
     */
    List<PrimaryTransaction> findAll();
}
