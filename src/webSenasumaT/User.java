package webSenasumaT;

import java.io.Serializable;
import java.sql.ResultSet;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "userDetails", eager = true)
@SessionScoped
public class User implements Serializable{

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  private String userFullName;
  private String userFirst;
  private String userSurname;
  private String address;
  private String email;
  private String userName;
  private String userPassowrd;
  private String confirmPassword;
  private int autoId;
  private String gender;
  

public String getConfirmPassword() {
	return confirmPassword;
}

public void setConfirmPassword(String confirmPassword) {
	this.confirmPassword = confirmPassword;
}

public String getUserFullName() {
	return userFullName;
}

public void setUserFullName(String userFullName) {
	this.userFullName = userFullName;
}

public String getUserFirst() {
	return userFirst;
}

public void setUserFirst(String userFirst) {
	this.userFirst = userFirst;
}

public String getUserSurname() {
	return userSurname;
}

public void setUserSurname(String userSurname) {
	this.userSurname = userSurname;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getUserPassowrd() {
	return userPassowrd;
}

public void setUserPassowrd(String userPassowrd) {
	this.userPassowrd = userPassowrd;
}

public int getAutoId() {
	return autoId;
}

public void setAutoId(int autoId) {
	this.autoId = autoId;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public User(ResultSet rs){
	  try{
	      this.userFullName = rs.getString("nameFull");
	      this.address      = rs.getString("address");
	      this.autoId       = rs.getInt("autoId");
	  }catch(Exception e){e.printStackTrace();}
}

  public User(){}

  public void signUpUser(){
	  setUserFullName(userFirst+" "+userSurname);
	  DbConnector.connectToDatabase();
	  int autoTempId = DbConnector.getAutoId("userdetails");
	  autoId = autoTempId+1;
	  String insertQuery = "insert into userdetails(autoid,namefull) values("+autoId+",'"+userFullName+"')";
	  DbConnector.InsertionQuery(insertQuery);
	  createLogin();
	  DbConnector.ClearConnection();
  }

  private void createLogin(){
	  String insertQuery = "insert into userid(userName,pasWrd,autoId) values('"+userName+"','"+userPassowrd+"',"+autoId+")";
	  DbConnector.InsertionQuery(insertQuery);
	  
  }
}
