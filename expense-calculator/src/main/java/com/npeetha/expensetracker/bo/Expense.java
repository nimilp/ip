package com.npeetha.expensetracker.bo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Expense {

	private int autoId;
	private String id;
	private double amount;
	private String venue;
	private String item;
	private Date paidOn;
	private String accountId;
	private String accountName;
	
	
	public double getAmount() {
		return amount;
	}
	public Expense setAmount(double amount) {
		this.amount = amount;
		return this;
	}
	public String getVenue() {
		return venue;
	}
	public Expense setVenue(String venue) {
		this.venue = venue;
		return this;
	}
	public String getItem() {
		return item;
	}
	public Expense setItem(String item) {
		this.item = item;
		return this;
	}
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd", timezone="EST")
	public Date getPaidOn() {
		return paidOn;
	}
	public Expense setPaidOn(Date paidOn) {
		this.paidOn = paidOn;
		return this;
	}
	public String getAccountId() {
		return accountId;
	}
	public Expense setAccountId(String accountId) {
		this.accountId = accountId;
		return this;
	}
	public int getAutoId() {
		return autoId;
	}
	public Expense setAutoId(int autoId) {
		this.autoId = autoId;
		return this;
	}
	public String getId() {
		return id;
	}
	public Expense setId(String id) {
		this.id = id;
		return this;
	}
	public String getAccountName() {
		return accountName;
	}
	public Expense setAccountName(String accountName) {
		this.accountName = accountName;
		return this;
		
	}
	
	
}
