package com.userfront.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.userfront.domain.SavingsTransaction;

 
/**
 * The Interface SavingsTransactionDao.
 */
public interface SavingsTransactionDao extends CrudRepository<SavingsTransaction, Long> {

    /**
     * Find all.
     *
     * @return the list
     */
    List<SavingsTransaction> findAll();
}

