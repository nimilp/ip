package com.npeetha.common.constants;

public class MySQLConnectionConstants {
	
	public static final String USERNAME="adminq2aQXEz";
	public static final String PASSWORD="Gu64GdXB438D";
	public static final String DB="ip";
	public static final String DRIVER_NAME="com.mysql.jdbc.Driver";
	public static final String CONNECTION_STRING = "jdbc:mysql://"+System.getenv("OPENSHIFT_MYSQLDB_DB_HOST")+":"+System.getenv("OPENSHIFT_MYSQLDB_DB_PORT")+"/"+DB;

}
