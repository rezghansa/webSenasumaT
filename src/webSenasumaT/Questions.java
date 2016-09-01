package webSenasumaT;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

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
	private int answerTypeId = 0;
	private HashMap<Integer, String> answeTypesMap;
	
	
	public void init(ComponentSystemEvent event) {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
	    	AnswerTypes ansTypes = new AnswerTypes();
			setAnsweTypesMap(ansTypes.loadAnswerTypes());
	    }
	}
	
	public Questions(){
		System.out.println("called consructor");
	}
	
	
	public int getAnswerTypeId() {
		return answerTypeId;
	}


	public void setAnswerTypeId(int answerTypeId) {
		this.answerTypeId = answerTypeId;
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
	public HashMap<Integer, String> getAnsweTypesMap() {
		return answeTypesMap;
	}

	public void setAnsweTypesMap(HashMap<Integer, String> answeTypesMap) {
		this.answeTypesMap = answeTypesMap;
	}
		
	public void saveQuestion(){
		DbConnector.connectToDatabase();
		int autoTempId = DbConnector.getPrimaryKeyLastValue("questions", "questionId");
		questionId = autoTempId+1;
		String insertQuery = "insert into questions(questionId,question,autoId,correctAnsewer,answerType) values("+questionId+",'"+question+"',"+autoID+","+correctAnswer+","+answerTypeId+")";
		DbConnector.InsertionQuery(insertQuery);
		DbConnector.ClearConnection();
	}

	public HashMap<Integer, String> loadQuestion() {
		HashMap<Integer, String> questions = null;
		DbConnector.connectToDatabase();
		String sqlString = "select * from questions";
		ResultSet rs= DbConnector.getResults(sqlString);
		try{
			questions = new HashMap<Integer,String>();
			while (rs.next()) {
				Integer key 	= rs.getInt("questionId");
				String value 	= rs.getString("question");
				questions.put(key, value);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		DbConnector.ClearConnection();
		return questions;
	}

	public ArrayList<Questions> loadListQuestions(){
		ArrayList<Questions> listoFQuestions = new ArrayList<Questions>();
		HashMap<Integer, String> mapQuestion = loadQuestion();
		Iterator<Entry<Integer, String>> iterator = mapQuestion.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> pair = iterator.next();
			Questions que = new Questions();
			que.setQuestionId((Integer)pair.getKey());
			que.setQuestion((String)pair.getValue());
	        iterator.remove();
	        listoFQuestions.add(que);
		}
		return listoFQuestions; 
	}

}
