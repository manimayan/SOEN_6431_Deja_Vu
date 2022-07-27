package com.userfront.service;

import java.security.Principal;

import com.userfront.domain.PrimaryAccount;
import com.userfront.domain.SavingsAccount;

 
/**
 * The Interface AccountService.
 */
public interface AccountService {
	
	/**
	 * Creates the primary account.
	 *
	 * @return the primary account
	 */
	PrimaryAccount createPrimaryAccount();
    
    /**
     * Creates the savings account.
     *
     * @return the savings account
     */
    SavingsAccount createSavingsAccount();
    
    /**
     * Deposit.
     *
     * @param accountType the account type
     * @param amount the amount
     * @param principal the principal
     */
    void deposit(String accountType, double amount, Principal principal);
    
    /**
     * Withdraw.
     *
     * @param accountType the account type
     * @param amount the amount
     * @param principal the principal
     */
    void withdraw(String accountType, double amount, Principal principal);
    
    
}
