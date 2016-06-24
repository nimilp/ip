package com.npeetha.timetracker.constants;

public class MongoConstants {

	
	public static final String USERNAME="admin";
	public static final String PASSWORD="qfAdBcBVKUFa";
	public static final String DB="timesheet";
	public static final String MONGO_SERVER=System.getenv("OPENSHIFT_MONGODB_DB_HOST")+":"+System.getenv("OPENSHIFT_MONGODB_DB_PORT");
	public static final String CONNECTION = "mongodb://"+MONGO_SERVER+"/";
	
	public static final String DURATION = "duration";
	public static final String START_DATE ="start_date";
	public static final String END_DATE = "end_date";
}
