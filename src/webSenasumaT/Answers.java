package webSenasumaT;

import java.io.Serializable;
import java.util.HashMap;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

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
	private int answerTypeId = 0;
	private HashMap<Integer, String> answeTypesMap;
	
	public void init(ComponentSystemEvent event) {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
	    	AnswerTypes ansTypes = new AnswerTypes();
			setAnsweTypesMap(ansTypes.loadAnswerTypes());
	    }
	}
	
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
	public int getAnswerTypeId() {
		return answerTypeId;
	}
	public void setAnswerTypeId(int answerTypeId) {
		this.answerTypeId = answerTypeId;
	}
	
	public HashMap<Integer, String> getAnsweTypesMap() {
		return answeTypesMap;
	}

	public void setAnsweTypesMap(HashMap<Integer, String> answeTypesMap) {
		this.answeTypesMap = answeTypesMap;
	}
	
	public void saveAnswer(){
		DbConnector.connectToDatabase();
		int autoTempId = DbConnector.getPrimaryKeyLastValue("answers", "answerId");
		answerId = autoTempId+1;
		String insertQuery = "insert into answers(answerId,ansewer,answerType) values("+answerId+",'"+answer+"',"+answerTypeId+")";
		DbConnector.InsertionQuery(insertQuery);
		DbConnector.ClearConnection();
		
	}


	
	

}
