package com.userfront.service;

import java.security.Principal;
import java.util.List;

import com.userfront.domain.PrimaryAccount;
import com.userfront.domain.PrimaryTransaction;
import com.userfront.domain.Recipient;
import com.userfront.domain.SavingsAccount;
import com.userfront.domain.SavingsTransaction;

 
/**
 * The Interface TransactionService.
 */
public interface TransactionService {
	
	/**
	 * Find primary transaction list.
	 *
	 * @param username the username
	 * @return the list
	 */
	List<PrimaryTransaction> findPrimaryTransactionList(String username);

    /**
     * Find savings transaction list.
     *
     * @param username the username
     * @return the list
     */
    List<SavingsTransaction> findSavingsTransactionList(String username);

    /**
     * Save primary deposit transaction.
     *
     * @param primaryTransaction the primary transaction
     */
    void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction);

    /**
     * Save savings deposit transaction.
     *
     * @param savingsTransaction the savings transaction
     */
    void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction);
    
    /**
     * Save primary withdraw transaction.
     *
     * @param primaryTransaction the primary transaction
     */
    void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction);
    
    /**
     * Save savings withdraw transaction.
     *
     * @param savingsTransaction the savings transaction
     */
    void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction);
    
    /**
     * Between accounts transfer.
     *
     * @param transferFrom the transfer from
     * @param transferTo the transfer to
     * @param amount the amount
     * @param primaryAccount the primary account
     * @param savingsAccount the savings account
     * @throws Exception the exception
     */
    void betweenAccountsTransfer(String transferFrom, String transferTo, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) throws Exception;
    
    /**
     * Find recipient list.
     *
     * @param principal the principal
     * @return the list
     */
    List<Recipient> findRecipientList(Principal principal);

    /**
     * Save recipient.
     *
     * @param recipient the recipient
     * @return the recipient
     */
    Recipient saveRecipient(Recipient recipient);

    /**
     * Find recipient by name.
     *
     * @param recipientName the recipient name
     * @return the recipient
     */
    Recipient findRecipientByName(String recipientName);

    /**
     * Delete recipient by name.
     *
     * @param recipientName the recipient name
     */
    void deleteRecipientByName(String recipientName);
    
    /**
     * To someone else transfer.
     *
     * @param recipient the recipient
     * @param accountType the account type
     * @param amount the amount
     * @param primaryAccount the primary account
     * @param savingsAccount the savings account
     */
    void toSomeoneElseTransfer(Recipient recipient, String accountType, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount);
}
