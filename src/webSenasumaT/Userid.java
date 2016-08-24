package webSenasumaT;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "userId", eager = true)
@SessionScoped
public class Userid implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
	private boolean isUser = false;
	private int autoId;
	private User userDetailsClone;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isUser() {
		return isUser;
	}

	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}

	public int getAutoId() {
		return autoId;
	}

	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}

	public User getUserDetailsClone() {
		return userDetailsClone;
	}

	public void setUserDetailsClone(User userDetailsClone) {
		this.userDetailsClone = userDetailsClone;
	}

	public Userid(){
		String sqlString = "select * from userid where userName ='"+name+"'";
		DbConnector.connectToDatabase();
		ResultSet rs= DbConnector.getResults(sqlString);
		try {
			if(!rs.wasNull())
			 if(rs.getString("pasWrd").equalsIgnoreCase(password)){
					isUser = true;
					int autoId = rs.getInt("autoId");
					loadUserDetails(isUser,autoId);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean getValidUser(){
		return isUser;
	}

	public void loadUserDetails(boolean isValidUser,int autoId){
		if(!isValidUser)
			return;

		String sqlStringLoadUser = "select * from userdetails where autoId = "+autoId;
		DbConnector.connectToDatabase();
		ResultSet rs= DbConnector.getResults(sqlStringLoadUser);
		try {
			if(!rs.wasNull()){
				userDetailsClone = new User(rs);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
}
