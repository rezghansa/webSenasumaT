package webSenasumaT;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.DragDropEvent;

 
@ManagedBean(name = "examSetupView")
@ViewScoped
public class ExamSetupView implements Serializable {
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{questions}")
    private Questions service;
 
    private List<Questions> questions;
     
    private List<Questions> droppedQuestions;
     
    private Questions selectedQuestion;
     
    @PostConstruct
    public void init() {
        questions = service.loadListQuestions();
        droppedQuestions = new ArrayList<Questions>();
    }
     
    public void onQuestionDrop(DragDropEvent ddEvent) {
    	Questions question = ((Questions) ddEvent.getData());
  
        droppedQuestions.add(question);
        questions.remove(question);
    }

	public Questions getService() {
		return service;
	}

	public void setService(Questions service) {
		this.service = service;
	}

	public List<Questions> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}

	public List<Questions> getDroppedQuestions() {
		return droppedQuestions;
	}

	public void setDroppedQuestions(List<Questions> droppedQuestions) {
		this.droppedQuestions = droppedQuestions;
	}

	public Questions getSelectedQuestion() {
		return selectedQuestion;
	}

	public void setSelectedQuestion(Questions selectedQuestion) {
		this.selectedQuestion = selectedQuestion;
	}
     
}
