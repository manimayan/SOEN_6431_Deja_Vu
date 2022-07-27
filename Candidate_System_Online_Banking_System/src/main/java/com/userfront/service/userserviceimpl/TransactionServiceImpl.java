package com.userfront.service.userserviceimpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userfront.dao.PrimaryAccountDao;
import com.userfront.dao.PrimaryTransactionDao;
import com.userfront.dao.RecipientDao;
import com.userfront.dao.SavingsAccountDao;
import com.userfront.dao.SavingsTransactionDao;
import com.userfront.domain.PrimaryAccount;
import com.userfront.domain.PrimaryTransaction;
import com.userfront.domain.Recipient;
import com.userfront.domain.SavingsAccount;
import com.userfront.domain.SavingsTransaction;
import com.userfront.domain.User;
import com.userfront.service.TransactionService;
import com.userfront.service.UserService;

/**
 * The Class TransactionServiceImpl.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	/** The user service. */
	@Autowired
	private UserService userService;

	/** The primary transaction dao. */
	@Autowired
	private PrimaryTransactionDao primaryTransactionDao;

	/** The savings transaction dao. */
	@Autowired
	private SavingsTransactionDao savingsTransactionDao;

	/** The primary account dao. */
	@Autowired
	private PrimaryAccountDao primaryAccountDao;

	/** The savings account dao. */
	@Autowired
	private SavingsAccountDao savingsAccountDao;

	/** The recipient dao. */
	@Autowired
	private RecipientDao recipientDao;

	/**
	 * Find primary transaction list.
	 *
	 * @param username the username
	 * @return the list
	 */
	public List<PrimaryTransaction> findPrimaryTransactionList(String username) {
		User user = userService.findByUsername(username);
		return user.getPrimaryAccount().getPrimaryTransactionList();
	}

	/**
	 * Find savings transaction list.
	 *
	 * @param username the username
	 * @return the list
	 */
	public List<SavingsTransaction> findSavingsTransactionList(String username) {
		User user = userService.findByUsername(username);
		return user.getSavingsAccount().getSavingsTransactionList();
	}

	/**
	 * Save primary deposit transaction.
	 *
	 * @param primaryTransaction the primary transaction
	 */
	public void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction) {
		primaryTransactionDao.save(primaryTransaction);
	}

	/**
	 * Save savings deposit transaction.
	 *
	 * @param savingsTransaction the savings transaction
	 */
	public void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction) {
		savingsTransactionDao.save(savingsTransaction);
	}

	/**
	 * Save primary withdraw transaction.
	 *
	 * @param primaryTransaction the primary transaction
	 */
	public void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction) {
		primaryTransactionDao.save(primaryTransaction);
	}

	/**
	 * Save savings withdraw transaction.
	 *
	 * @param savingsTransaction the savings transaction
	 */
	public void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction) {
		savingsTransactionDao.save(savingsTransaction);
	}

	/**
	 * Between accounts transfer.
	 *
	 * @param transferFrom   the transfer from
	 * @param transferTo     the transfer to
	 * @param amount         the amount
	 * @param primaryAccount the primary account
	 * @param savingsAccount the savings account
	 * @throws Exception the exception
	 */
	public void betweenAccountsTransfer(String transferFrom, String transferTo, String amount,
			PrimaryAccount primaryAccount, SavingsAccount savingsAccount) throws Exception {
		if (transferFrom.equalsIgnoreCase("Primary") && transferTo.equalsIgnoreCase("Savings")) {
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
			primaryAccountDao.save(primaryAccount);
			savingsAccountDao.save(savingsAccount);

			Date date = new Date();

			PrimaryTransaction primaryTransaction = new PrimaryTransaction(date,
					"Between account transfer from " + transferFrom + " to " + transferTo, "Account", "Finished",
					Double.parseDouble(amount), primaryAccount.getAccountBalance(), primaryAccount);
			primaryTransactionDao.save(primaryTransaction);
		} else if (transferFrom.equalsIgnoreCase("Savings") && transferTo.equalsIgnoreCase("Primary")) {
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			primaryAccountDao.save(primaryAccount);
			savingsAccountDao.save(savingsAccount);

			Date date = new Date();

			SavingsTransaction savingsTransaction = new SavingsTransaction(date,
					"Between account transfer from " + transferFrom + " to " + transferTo, "Transfer", "Finished",
					Double.parseDouble(amount), savingsAccount.getAccountBalance(), savingsAccount);
			savingsTransactionDao.save(savingsTransaction);
		} else {
			throw new IOException("Invalid Transfer");
		}
	}

	/**
	 * Find recipient list.
	 *
	 * @param principal the principal
	 * @return the list
	 */
	public List<Recipient> findRecipientList(Principal principal) {
		String username = principal.getName();
		List<Recipient> recipientList = recipientDao.findAll().stream() // convert list to stream
				.filter(recipient -> username.equals(recipient.getUser().getUsername())) // filters the line, equals to
																							// username
				.collect(Collectors.toList());

		return recipientList;
	}

	/**
	 * Save recipient.
	 *
	 * @param recipient the recipient
	 * @return the recipient
	 */
	public Recipient saveRecipient(Recipient recipient) {
		return recipientDao.save(recipient);
	}

	/**
	 * Find recipient by name.
	 *
	 * @param recipientName the recipient name
	 * @return the recipient
	 */
	public Recipient findRecipientByName(String recipientName) {
		return recipientDao.findByName(recipientName);
	}

	/**
	 * Delete recipient by name.
	 *
	 * @param recipientName the recipient name
	 */
	public void deleteRecipientByName(String recipientName) {
		recipientDao.deleteByName(recipientName);
	}

	/**
	 * To someone else transfer.
	 *
	 * @param recipient      the recipient
	 * @param accountType    the account type
	 * @param amount         the amount
	 * @param primaryAccount the primary account
	 * @param savingsAccount the savings account
	 */
	public void toSomeoneElseTransfer(Recipient recipient, String accountType, String amount,
			PrimaryAccount primaryAccount, SavingsAccount savingsAccount) {
		if (accountType.equalsIgnoreCase("Primary")) {
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			primaryAccountDao.save(primaryAccount);

			Date date = new Date();

			PrimaryTransaction primaryTransaction = new PrimaryTransaction(date,
					"Transfer to recipient " + recipient.getName(), "Transfer", "Finished", Double.parseDouble(amount),
					primaryAccount.getAccountBalance(), primaryAccount);
			primaryTransactionDao.save(primaryTransaction);
		} else if (accountType.equalsIgnoreCase("Savings")) {
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			savingsAccountDao.save(savingsAccount);

			Date date = new Date();

			SavingsTransaction savingsTransaction = new SavingsTransaction(date,
					"Transfer to recipient " + recipient.getName(), "Transfer", "Finished", Double.parseDouble(amount),
					savingsAccount.getAccountBalance(), savingsAccount);
			savingsTransactionDao.save(savingsTransaction);
		}
	}
}
