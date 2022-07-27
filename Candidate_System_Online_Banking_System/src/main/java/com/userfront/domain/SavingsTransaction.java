package com.userfront.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * The Class SavingsTransaction.
 */
@Entity

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data
public class SavingsTransaction {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** The date. */
	private Date date;

	/** The description. */
	private String description;

	/** The type. */
	private String type;

	/** The status. */
	private String status;

	/** The amount. */
	private double amount;

	/** The available balance. */
	private BigDecimal availableBalance;

	/** The savings account. */
	@ManyToOne
	@JoinColumn(name = "savings_account_id")
	private SavingsAccount savingsAccount;

	/**
	 * Instantiates a new savings transaction.
	 *
	 * @param date             the date
	 * @param description      the description
	 * @param type             the type
	 * @param status           the status
	 * @param amount           the amount
	 * @param availableBalance the available balance
	 * @param savingsAccount   the savings account
	 */
	public SavingsTransaction(Date date, String description, String type, String status, double amount,
			BigDecimal availableBalance, SavingsAccount savingsAccount) {
		this.date = date;
		this.description = description;
		this.type = type;
		this.status = status;
		this.amount = amount;
		this.availableBalance = availableBalance;
		this.savingsAccount = savingsAccount;
	}

}
