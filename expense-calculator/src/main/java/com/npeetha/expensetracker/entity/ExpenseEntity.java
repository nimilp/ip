package com.npeetha.expensetracker.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.npeetha.expensetracker.bo.Expense;

@Entity
@Table(name = "expenses")
public class ExpenseEntity {

	@Id
	@Column(name = "auto_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int autoId;
	private String id;
	private double amount;
	@Column(name = "account_id")
	private String accountId;
	
//	@ManyToOne(fetch=FetchType.EAGER)
//	@JoinColumn(name="account_id", insertable=false, updatable=false)
//	private AccountEntity account;
	
	@Column(name="place")
	private String venue;
	private String item;
	
	@Column(name="paid_time")
	private Date paidOn;
	
//	@Column(name="account_id")
//	private String accountId;

	public int getAutoId() {
		return autoId;
	}
	

	public ExpenseEntity setAutoId(int autoId) {
		this.autoId = autoId;
		return this;
	}

	public double getAmount() {
		return amount;
	}

	public ExpenseEntity setAmount(double amount) {
		this.amount = amount;
		return this;
	}

	public String getVenue() {
		return venue;
	}

	public ExpenseEntity setVenue(String venue) {
		this.venue = venue;
		return this;
	}

	public String getItem() {
		return item;
	}

	public ExpenseEntity setItem(String item) {
		this.item = item;
		return this;
	}

	public Date getPaidOn() {
		return paidOn;
	}

	public ExpenseEntity setPaidOn(Date paidOn) {
		this.paidOn = paidOn;
		return this;
	}
	
//	public AccountEntity getAccount(){
//		return this.account;
//	}

	public String getAccountId() {
		return accountId;
	}
//
	public ExpenseEntity setAccountId(String accountId) {
		this.accountId = accountId;
		return this;
	}
	
//	public ExpenseEntity setAccount(AccountEntity account){
//		this.account =account;
//		this.accountId = account.getId();
//		return this;
//	}
	

	public void copy(Expense expense) {
		// ExpenseEntity entity = new ExpenseEntity();
//		AccountEntity account = new AccountEntity();
//		account.setId(expense.getAccountId());
		this.setAccountId(expense.getAccountId()).setAmount(expense.getAmount()).setItem(expense.getItem())
				.setPaidOn(expense.getPaidOn()).setVenue(expense.getVenue()).setAutoId(expense.getAutoId()).setId(expense.getId());;
		// return entity;
	}

	public Expense transfer() {
		Expense e = new Expense();
		e.setAccountId(this.getAccountId()).setAmount(this.getAmount()).setAutoId(this.getAutoId())
				.setItem(this.getItem()).setPaidOn(this.getPaidOn()).setVenue(this.getVenue()).setId(this.getId());
		return e;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	
	

}
