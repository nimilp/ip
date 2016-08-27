package com.npeetha.expensetracker.bo;

import org.bson.Document;

import com.npeetha.common.constants.MongoConstants.AccountsConstants;
public class Account {

	private Integer id;
	private String name;
	private String desc;
	private Double budget;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public Account setName(String name) {
		this.name = name;
		return this;
	}

	public String getDesc() {
		return desc;
	}

	public Account setDesc(String desc) {
		this.desc = desc;
		return this;
	}

	public Double getBudget() {
		return budget;
	}

	public Account setBudget(Double budget) {
		this.budget = budget;
		return this;
	}

	public Document getDocument(){
		Document doc = new Document();
		
		//Date expire = Date.from(this.startDate.toInstant().plus(30, ChronoUnit.DAYS));
		doc.append(AccountsConstants.id.toString(), this.id).append(AccountsConstants.name.toString(), this.name).append(AccountsConstants.desc.toString(), this.desc).append(AccountsConstants.budget.toString(), this.budget);
		return doc;
		
	}
	
}
