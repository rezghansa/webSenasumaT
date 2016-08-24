package webSenasumaT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnector {

	static Connection con  = null;
	static String serverIp = "localhost";
	static String portId   = "3306";
	static String dbName   = "sanasuma";
	static String userDb   = "root";
	static String pasWrdDb = "1234";

	/**
	 *  connection init and hold the data
	 */
	public static void connectToDatabase(){
       	try {
   		// Establish the connection.
      		Class.forName("com.mysql.jdbc.Driver");
      		con = DriverManager.getConnection("jdbc:mysql://"+serverIp+":"+portId+"/"+dbName,userDb, pasWrdDb);
       	}
		// Handle any errors that may have occurred.
		catch (Exception e) {
				e.printStackTrace();
		}
	}


	/**
	 * get Results set for given sql
	 * @param sqlString
	 * @return
	 */
	public static ResultSet getResults(String sqlString){
		// Declare the JDBC objects.
		Statement stmt = null;
		ResultSet rs = null;

		// Create and execute an SQL statement that returns some data.
		String SQL = sqlString;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);
			return rs;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Close Connection after work to memory clean up
	 */
	public static void ClearConnection(){
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	* This methods is going to use for Insertion query
	*/
	public static boolean InsertionQuery(String queryInsert){
		if(con!= null){
			try{
					Statement stmt = null;
					stmt = con.createStatement();
					stmt.executeUpdate(queryInsert);
			}catch(Exception e){
					e.printStackTrace();
					return false;
			}
		}
		return true;
	}
	
	public static int getAutoId(String tableName){
		Statement stmt = null;
		ResultSet rs = null;
		// Create and execute an SQL statement that returns some data.
		String SQL = "select max(autoid) as autoid from "+tableName;
		if(con!= null){
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(SQL);
				if (rs.next()) {
					int value = rs.getInt(1);			
					return value;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
		}
		return 0;
	}

}
