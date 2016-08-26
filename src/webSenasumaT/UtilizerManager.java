package webSenasumaT;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name = "utilizerManager", eager = true)
public class UtilizerManager implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value="#{answers}")
	private Answers answersBean;
	@ManagedProperty(value="#{questions}")
	private Questions questionBean;

	
	
	public Answers getAnswersBean() {
		return answersBean;
	}
	public void setAnswersBean(Answers answersBean) {
		this.answersBean = answersBean;
	}
	public Questions getQuestionBean() {
		return questionBean;
	}
	public void setQuestionBean(Questions questionBean) {
		this.questionBean = questionBean;
	}

	public void saveQuestions(){
		answersBean.saveAnswer();
		questionBean.setCorrectAnswer(answersBean.getAnswerId());
		questionBean.saveQuestion();
	}

}
