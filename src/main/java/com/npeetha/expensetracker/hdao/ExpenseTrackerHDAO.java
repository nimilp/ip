package com.npeetha.expensetracker.hdao;

import static com.npeetha.common.constants.MongoConstants.DB;
import static com.npeetha.common.constants.MongoConstants.MONGO_SERVER;
import static com.npeetha.common.constants.MongoConstants.PASSWORD;
import static com.npeetha.common.constants.MongoConstants.USERNAME;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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
		MongoCredential credential = MongoCredential.createCredential(USERNAME, DB,
				PASSWORD.toCharArray());
		log.info(MONGO_SERVER);
		mongoClient = new MongoClient(new ServerAddress(MONGO_SERVER), Arrays.asList(credential));
		return mongoClient.getDatabase(DB);
	}
	
	/**
	 * @return
	 */
	public List<Account> getAccounts(){
		
		MongoDatabase db= getConnection();
		collection = db.getCollection("accounts");
		FindIterable<Document> find = collection.find();
		List<Account> returnList = Lists.newArrayList();
//		for(Document doc : find){
//			Account account = new Account();
//			time.setDuration(doc.getInteger(DURATION)).setEndDate(doc.getDate(END_DATE)).setStartDate(doc.getDate(START_DATE));
//			returnList.add(time);
//		}
		mongoClient.close();
		return returnList;
	}
	
	/**
	 * @param time
	 */
	public void insertAccounts(Account account){
		MongoDatabase db = getConnection();
		collection = db.getCollection("accounts");
		if(collection==null){
			db.createCollection("accounts");
			collection = db.getCollection("accounts");
		}
		System.out.println("received account "+account.toString() );
		collection.insertOne(account.getDocument());
	}
}
