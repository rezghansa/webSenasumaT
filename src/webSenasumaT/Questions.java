package webSenasumaT;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "questions", eager = true)
@SessionScoped
public class Questions implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int questionId;
	private int autoID;
	private String question;
	private int correctAnswer;
	
	public Questions(){
		System.out.println("called consructor");
	}
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getAutoID() {
		return autoID;
	}
	public void setAutoID(int autoID) {
		this.autoID = autoID;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
		
	public void saveQuestion(){
		DbConnector.connectToDatabase();
		int autoTempId = DbConnector.getPrimaryKeyLastValue("questions", "questionId");
		questionId = autoTempId+1;
		String insertQuery = "insert into questions(questionId,question,autoId,correctAnsewer) values("+questionId+",'"+question+"',"+autoID+","+correctAnswer+")";
		DbConnector.InsertionQuery(insertQuery);
		DbConnector.ClearConnection();
	}
	
	

}
