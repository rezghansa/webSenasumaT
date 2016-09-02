package webSenasumaT;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "answers", eager = true)
@ApplicationScoped
public class Answers implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int answerId;
	private String answer;
	private String incorrctanswer;
		
	public String getIncorrctanswer() {
		return incorrctanswer;
	}

	public void setIncorrctanswer(String incorrctanswer) {
		this.incorrctanswer = incorrctanswer;
	}

	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public void saveAnswer(){
		DbConnector.connectToDatabase();
		int autoTempId = DbConnector.getPrimaryKeyLastValue("answers", "answerId");
		answerId = autoTempId+1;
		String insertQuery = "insert into answers(answerId,ansewer,incorrectAnswer) values("+answerId+",'"+answer+"','"+incorrctanswer+"')";
		DbConnector.InsertionQuery(insertQuery);
		DbConnector.ClearConnection();
		
	}


	public HashMap<Integer, String> loadAnswers() {
		HashMap<Integer, String> answers = null;
		DbConnector.connectToDatabase();
		String sqlString = "select * from answers";
		ResultSet rs= DbConnector.getResults(sqlString);
		try{
			answers = new HashMap<Integer,String>();
			while (rs.next()) {
				Integer key 	= rs.getInt("answerId");
				String value 	= rs.getString("ansewer");
				answers.put(key, value);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		DbConnector.ClearConnection();
		return answers;
	}
	

}
