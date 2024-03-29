package com.userfront.dao;

import com.userfront.domain.PrimaryAccount;
import org.springframework.data.repository.CrudRepository;

 
/**
 * Created by z00382545 on 10/21/16.
 */
public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount,Long> {

    /**
     * Find by account number.
     *
     * @param accountNumber the account number
     * @return the primary account
     */
    PrimaryAccount findByAccountNumber (int accountNumber);
}
