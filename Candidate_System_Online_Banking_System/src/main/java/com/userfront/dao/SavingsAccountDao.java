package com.userfront.dao;

import com.userfront.domain.SavingsAccount;
import org.springframework.data.repository.CrudRepository;

 
/**
 * Created by z00382545 on 10/21/16.
 */
public interface SavingsAccountDao extends CrudRepository<SavingsAccount, Long> {

    /**
     * Find by account number.
     *
     * @param accountNumber the account number
     * @return the savings account
     */
    SavingsAccount findByAccountNumber (int accountNumber);
}
