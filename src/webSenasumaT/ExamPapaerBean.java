package webSenasumaT;

import java.io.Serializable;

public class ExamPapaerBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ansewer;
	private String incorrectAnswer;
	private String questionAnswerTypeName;
	private String question;
	
	
	public String getAnsewer() {
		return ansewer;
	}
	public void setAnsewer(String ansewer) {
		this.ansewer = ansewer;
	}
	public String getIncorrectAnswer() {
		return incorrectAnswer;
	}
	public void setIncorrectAnswer(String incorrectAnswer) {
		this.incorrectAnswer = incorrectAnswer;
	}
	public String getQuestionAnswerTypeName() {
		return questionAnswerTypeName;
	}
	public void setQuestionAnswerTypeName(String questionAnswerTypeName) {
		this.questionAnswerTypeName = questionAnswerTypeName;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	

}
