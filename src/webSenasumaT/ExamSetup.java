package webSenasumaT;

import java.io.Serializable;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

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
	private HashMap<Integer, String> questionMap;
	private HashMap<Integer,String> examMap;
	
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
	public HashMap<Integer, String> getQuestionMap() {
		return questionMap;
	}
	public void setQuestionMap(HashMap<Integer, String> questionMap) {
		this.questionMap = questionMap;
	}
	public HashMap<Integer, String> getExamMap() {
		return examMap;
	}
	public void setExamMap(HashMap<Integer, String> examMap) {
		this.examMap = examMap;
	}
	
	public void init(ComponentSystemEvent event) {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
	    	Exam examMap = new Exam();
	    	setExamMap(examMap.loadExam());
	    	Questions questionMap = new Questions();
	    	setQuestionMap(questionMap.loadQuestion());
	    }
	}
	
	

}
