package com.npeetha.timetracker.hdao;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.bson.BsonDocument;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.npeetha.timetracker.bo.Time;
import static com.npeetha.common.constants.MongoConstants.*;

import jersey.repackaged.com.google.common.collect.Lists;

/**
 * @author nimilpeethambaran
 *
 */
public class TimeTrackerHDAO {
	private static Logger log = Logger.getLogger(TimeTrackerHDAO.class.getName());
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
	public List<Time> getTimesheets(){
		
		MongoDatabase db= getConnection();
		collection = db.getCollection("timesheets");
		FindIterable<Document> find = collection.find();
		List<Time> returnList = Lists.newArrayList();
		for(Document doc : find){
			Time time = new Time();
			time.setDuration(doc.getInteger(DURATION)).setEndDate(doc.getDate(END_DATE)).setStartDate(doc.getDate(START_DATE));
			returnList.add(time);
		}
		mongoClient.close();
		return returnList;
	}
	
	/**
	 * @param time
	 */
	public void insertTimeSheet(Time time){
		MongoDatabase db = getConnection();
		collection = db.getCollection("timesheets");
		if(collection==null){
			db.createCollection("timesheet");
			collection = db.getCollection("timesheets");
		}
		collection.insertOne(time.getDocument());
		collection.createIndex(new Document(START_DATE,1).append(END_DATE, 1));
	}
}
