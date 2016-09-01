package webSenasumaT;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "examsetup", eager = true)
@SessionScoped
public class ExamSetup implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int autoId =0;
	private int examId =0;
	private int questionId =0;

	
	public int getAutoId() {
		return autoId;
	}
	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}
	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	public void persistance(){
		DbConnector.connectToDatabase();
		 int autoTempId = DbConnector.getPrimaryKeyLastValue("examQuestions", "autoId");
		 autoId = autoTempId+1;
		 String insertQuery = "insert into examQuestions(autoId,examId,questionId) values("+autoId+","+getExamId()+","+getQuestionId()+")";
		 DbConnector.InsertionQuery(insertQuery);
		 DbConnector.ClearConnection();
	}
}
