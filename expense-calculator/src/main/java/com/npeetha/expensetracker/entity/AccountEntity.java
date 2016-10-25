package com.npeetha.expensetracker.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.npeetha.expensetracker.bo.Account;

@Entity
@Table(name = "accounts")
public class AccountEntity {

	@Id
	@Column(name = "auto_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int autoId;
	private String id;
	private String name;
	private String description;
	
	@Column(name = "creation_date")
	private Date creationDate;
	
	@Column(name = "updation_date")
	private Date updateDate;
	
	@Column(name = "last_updated_by")
	private String lastUpdatedBy;
	private Double budget;
	
	public String getId() {
		return id;
	}
	public AccountEntity setId(String id) {
		this.id = id;
		return this;
	}
	public String getName() {
		return name;
	}
	public AccountEntity setName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public AccountEntity setDescription(String description) {
		this.description = description;
		return this;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public AccountEntity setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
		return this;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public AccountEntity setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
		return this;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public AccountEntity setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
		return this;
	}
	public Double getBudget() {
		return budget;
	}
	public AccountEntity setBudget(Double budget) {
		this.budget = budget;
		return this;
	}
	public int getAutoId() {
		return autoId;
	}
	public AccountEntity setAutoId(int autoId) {
		this.autoId = autoId;
		return this;
	}
	
	public AccountEntity copy(Account entity) {
		//AccountEntity a = new AccountEntity();
		AccountEntity a = this;
		a.setAutoId(entity.getAutoId()).setId(entity.getId()).setBudget(entity.getBudget())
				.setCreationDate(entity.getCreationDate()).setDescription(entity.getDescription())
				.setName(entity.getName()).setLastUpdatedBy(entity.getLastUpdatedBy())
				.setUpdateDate(entity.getUpdateDate());
		return a;

	}

	public Account transfer() {
		AccountEntity a = this;
		Account e = new Account();
		e.setAutoId(a.getAutoId()).setBudget(a.getBudget()).setCreationDate(a.getCreationDate())
				.setDescription(a.getDescription()).setId(a.getId()).setLastUpdatedBy(a.getLastUpdatedBy())
				.setName(a.getName()).setUpdateDate(a.getUpdateDate());
		return e;
	}
	
}
