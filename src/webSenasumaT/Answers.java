package webSenasumaT;

import java.io.Serializable;

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


	
	

}
