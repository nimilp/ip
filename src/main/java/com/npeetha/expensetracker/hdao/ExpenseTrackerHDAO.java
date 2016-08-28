package com.npeetha.expensetracker.hdao;

import static com.npeetha.common.constants.MongoConstants.DB;
import static com.npeetha.common.constants.MongoConstants.MONGO_SERVER;
import static com.npeetha.common.constants.MongoConstants.PASSWORD;
import static com.npeetha.common.constants.MongoConstants.USERNAME;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.bson.BSON;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.npeetha.common.constants.MongoConstants.AccountsConstants;
import com.npeetha.expensetracker.bo.Account;

import jersey.repackaged.com.google.common.collect.Lists;

/**
 * @author nimilpeethambaran
 *
 */
public class ExpenseTrackerHDAO {
	private static Logger log = Logger.getLogger(ExpenseTrackerHDAO.class.getName());
	private MongoClient mongoClient = null;
	private MongoCollection<Document> collection;

	private MongoDatabase getConnection() {
		MongoCredential credential = MongoCredential.createCredential(USERNAME, DB, PASSWORD.toCharArray());
		log.info(MONGO_SERVER);
		mongoClient = new MongoClient(new ServerAddress(MONGO_SERVER), Arrays.asList(credential));
		return mongoClient.getDatabase(DB);
	}

	/**
	 * @return
	 */
	public List<Account> getAccounts() {

		MongoDatabase db = getConnection();
		collection = db.getCollection("accounts");
		FindIterable<Document> find = collection.find();
		List<Account> returnList = Lists.newArrayList();
		for (Document doc : find) {
			Account account = new Account();
			account.setId(doc.getObjectId(AccountsConstants._id.toString()).toString()).setDescription(doc.getString(AccountsConstants.description.toString()))
					.setBudget(doc.getDouble(AccountsConstants.budget.toString())).setName(doc.getString(AccountsConstants.name.toString()));
			returnList.add(account);
		}
		mongoClient.close();
		return returnList;
	}
	
	public Account getAccount(String id) {

		MongoDatabase db = getConnection();
		Account account = null;
		collection = db.getCollection("accounts");
		Document doc1 = new Document("_id",new ObjectId(id));
		FindIterable<Document> find = collection.find(doc1);
		List<Account> returnList = Lists.newArrayList();
		for (Document doc : find) {
			account = new Account();
			account.setId(doc.getObjectId(AccountsConstants._id.toString()).toString()).setDescription(doc.getString(AccountsConstants.description.toString()))
					.setBudget(doc.getDouble(AccountsConstants.budget.toString())).setName(doc.getString(AccountsConstants.name.toString()));
			//returnList.add(account);
		}
		mongoClient.close();
		return account;
	}

	/**
	 * @param time
	 */
	public void insertAccounts(Account account) {
		Integer id = 1;
		MongoDatabase db = getConnection();
		collection = db.getCollection("accounts");
		if (collection == null) {
			db.createCollection("accounts");
			collection = db.getCollection("accounts");
		}
		//account.setId(collection.count() + 1);
		System.out.println("received account " + account.toString());
		collection.insertOne(account.getDocument());
	}
	
	public void deleteAccount(String id){
		MongoDatabase db = getConnection();
		collection = db.getCollection("accounts");
		Document doc = new Document("_id",new ObjectId(id));
		//bson.
		collection.deleteOne(doc);
	}
	
	public void updateAccount(Account account){
		MongoDatabase db = getConnection();
		collection = db.getCollection("accounts");
		Document doc = new Document("_id",new ObjectId(account.getId()));
		Document updateDoc = new Document("$set",account.getDocument());
		//bson.
		collection.updateOne(doc, updateDoc);
	}
}
