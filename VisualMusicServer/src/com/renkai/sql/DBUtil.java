package com.renkai.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtil{
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/vsmusic";
	private static String username = "root";
	private static String password = "rk0406";

	public static Connection getConnection() throws Exception{
		Connection connection;
	    try{
           Class.forName(driver);
           connection = DriverManager.getConnection(url,username,password);
	    }catch(Exception e){
           throw e;
	    }
	    return connection;
	}
	public static void closeStatement(Statement statement) throws Exception{
	    statement.close();
	}
	public static void closePreparedStatement(PreparedStatement pStatement) throws Exception{
        pStatement.close();
	}
	public static void closeResultSet(ResultSet resultSet) throws Exception{
	    resultSet.close();
	}
	public static void closeConnection(Connection connection) throws Exception{
        connection.close();
	}
	/*test the connection
    public static void main (String args[]) throws Exception{
    	Connection conn = DBUtil.getConnection();
    	System.out.println(conn);
    }
    */
}