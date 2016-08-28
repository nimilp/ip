package com.npeetha.expensetracker.bo;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.npeetha.common.constants.MongoConstants.AccountsConstants;
public class Account {

	
	private String id;
	private String name;
	private String description;
	private Double budget;
	
	public String getId() {
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
		return new String("{id:"+id+", desc:"+description+", budget:"+budget+"}");
		//return super.toString();
	}
	
}
