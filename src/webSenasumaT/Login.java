package webSenasumaT;

import java.io.Serializable;
import java.sql.ResultSet;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "login", eager = true)
@SessionScoped
public class Login implements Serializable{
private static final long serialVersionUID = 1094801825228386363L;
	
	private String pwd;
	private String msg;
	private String user;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	//validate login
	public String validateUsernamePassword() {
		DbConnector.connectToDatabase();
		String sqlString = "select * from userid where userName = '"+user+"'";
		ResultSet rs= DbConnector.getResults(sqlString);
		try{
			if (rs.next()) {
				String unm = rs.getString("userName");
				String pws = rs.getString("pasWrd");
				DbConnector.ClearConnection();
				if(unm.equalsIgnoreCase(user) && pws.equalsIgnoreCase(pwd)){
					//result found, means valid inputs
					return "exam";
				}else{
					this.setMsg("Incorrect Username and Passowrd");
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_WARN,
									"Incorrect Username and Passowrd",
									"Please enter correct username and Password"));
					return "index";
				}
			}
			else{
				this.setMsg("Incorrect Username and Passowrd");
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Incorrect Username and Passowrd",
								"Please enter correct username and Password"));
				return "home";
			}
		}catch(Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Passowrd",
							"Please enter correct username and Password"));
			this.setMsg("Incorrect Username and Passowrd");
			return "index";
		}
	}

}
