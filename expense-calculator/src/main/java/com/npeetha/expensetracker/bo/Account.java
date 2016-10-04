package com.npeetha.expensetracker.bo;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.bson.Document;

import com.npeetha.common.constants.MongoConstants.AccountsConstants;

@Entity
@Table(name="accounts")
public class Account {

	@Id
	@Column(name="auto_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int autoId;
	
	private String id;
	private String name;
	private String description;
	
	@Column(name="creation_date")
	private Date creationDate;
	
	@Column(name="updation_date")
	private Date updateDate;
	
	@Column(name="last_updated_by")
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
		//id= id == null || id.trim().length()==0? UUID.randomUUID().toString():id;
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

	public Document getDocument(){
		Document doc = new Document();
		
		//Date expire = Date.from(this.startDate.toInstant().plus(30, ChronoUnit.DAYS));
		doc.append(AccountsConstants.name.toString(), this.name).append(AccountsConstants.description.toString(), this.description).append(AccountsConstants.budget.toString(), this.budget);
		return doc;
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new String("{id:"+id+", name:"+name+", desc:"+description+", budget:"+budget+", lastUpdatedBy:"+lastUpdatedBy+", _id:"+autoId+"}");
		//return super.toString();
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public Account setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
		return this;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public Account setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
		return this;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public Account setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
		return this;
	}
	
}
