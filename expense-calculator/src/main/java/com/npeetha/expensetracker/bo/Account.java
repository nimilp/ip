package com.npeetha.expensetracker.bo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class Account {

	
	private int autoId;

	private String id;
	private String name;
	private String description;
	
	private Date creationDate;
	
	private Date updateDate;
	
	private String lastUpdatedBy;

	private Double budget;

	public int getAutoId() {
		return autoId;
	}

	public Account setAutoId(int autoId) {
		this.autoId = autoId;
		return this;
	}

	public String getId() {
		// id= id == null || id.trim().length()==0?
		// UUID.randomUUID().toString():id;
		return id;
	}

	public Account setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Account setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Account setDescription(String desc) {
		this.description = desc;
		return this;
	}

	public Double getBudget() {
		return budget;
	}

	public Account setBudget(Double budget) {
		this.budget = budget;
		return this;
	}

	// public Document getDocument(){
	// Document doc = new Document();
	//
	// //Date expire = Date.from(this.startDate.toInstant().plus(30,
	// ChronoUnit.DAYS));
	// doc.append(AccountsConstants.name.toString(),
	// this.name).append(AccountsConstants.description.toString(),
	// this.description).append(AccountsConstants.budget.toString(),
	// this.budget);
	// return doc;
	//
	// }
	@Override
	@JsonIgnore
	public String toString() {
		// TODO Auto-generated method stub
		return new String("{id:" + id + ", name:" + name + ", desc:" + description + ", budget:" + budget
				+ ", lastUpdatedBy:" + lastUpdatedBy + ", _id:" + autoId + "}");
		// return super.toString();
	}

	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd", timezone="EST")
	public Date getCreationDate() {
		return creationDate;
	}

	public Account setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
		return this;
	}

	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd", timezone="EST")
	public Date getUpdateDate() {
		return updateDate;
	}

	public Account setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
		return this;
	}
	@JsonIgnore
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public Account setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
		return this;
	}

	

}
