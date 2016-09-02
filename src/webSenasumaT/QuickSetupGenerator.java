package webSenasumaT;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

@ManagedBean(name = "quickSetupGenerator", eager = true)
@SessionScoped
public class QuickSetupGenerator implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String topicType;
	private String topic;
	private String subtopic;
	private String examType;
	private String exam;
	private String answerType;
	private String answer;
	private HashMap<Integer,String> listOfTopicTypes;
	private HashMap<Integer,String> listOfTopics;
	private HashMap<Integer,String> listOfSubTopics;
	private HashMap<Integer,String> listOfexamType;
	private HashMap<Integer,String> listOfexam;
	private HashMap<Integer,String> listOfanswerType;
	private HashMap<Integer,String> listOfanswer;
	
	
	
	
	public String getAnswerType() {
		return answerType;
	}

	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public HashMap<Integer, String> getListOfanswerType() {
		return listOfanswerType;
	}

	public void setListOfanswerType(HashMap<Integer, String> listOfanswerType) {
		this.listOfanswerType = listOfanswerType;
	}

	public HashMap<Integer, String> getListOfanswer() {
		return listOfanswer;
	}

	public void setListOfanswer(HashMap<Integer, String> listOfanswer) {
		this.listOfanswer = listOfanswer;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public String getExam() {
		return exam;
	}

	public void setExam(String exam) {
		this.exam = exam;
	}

	public HashMap<Integer, String> getListOfexamType() {
		return listOfexamType;
	}

	public void setListOfexamType(HashMap<Integer, String> listOfexamType) {
		this.listOfexamType = listOfexamType;
	}

	public HashMap<Integer, String> getListOfexam() {
		return listOfexam;
	}

	public void setListOfexam(HashMap<Integer, String> listOfexam) {
		this.listOfexam = listOfexam;
	}

	public String getSubtopic() {
		return subtopic;
	}

	public void setSubtopic(String subtopic) {
		this.subtopic = subtopic;
	}

	public HashMap<Integer, String> getListOfSubTopics() {
		return listOfSubTopics;
	}

	public void setListOfSubTopics(HashMap<Integer, String> listOfSubTopics) {
		this.listOfSubTopics = listOfSubTopics;
	}

	public HashMap<Integer, String> getListOfTopics() {
		return listOfTopics;
	}

	public void setListOfTopics(HashMap<Integer, String> listOfTopics) {
		this.listOfTopics = listOfTopics;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public HashMap<Integer, String> getListOfTopicTypes() {
		return listOfTopicTypes;
	}

	public void setListOfTopicTypes(HashMap<Integer, String> listOfTopicTypes) {
		this.listOfTopicTypes = listOfTopicTypes;
	}

	public String getTopicType() {
		return topicType;
	}

	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}

	public void init(ComponentSystemEvent event) {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
	    	TopicTypes topicTypes = new TopicTypes();
	    	Topics topics = new Topics();
	    	SubTopics subTopic = new SubTopics();
	    	ExamTypes examTypes = new ExamTypes();
	    	Exam exam = new Exam();
	    	AnswerTypes answerTypes = new AnswerTypes();
	    	Answers answers = new Answers();
	    	listOfTopicTypes =  topicTypes.loadTopicTypes();
	    	listOfTopics = topics.loadTopics();
	    	listOfSubTopics = subTopic.loadSubTopicTypes();
	    	listOfexamType = examTypes.loadExamTypes();
	    	listOfexam = exam.loadExam();
	    	listOfanswerType = answerTypes.loadAnswerTypes();
	    	listOfanswer  = answers.loadAnswers();
	    }
	}
	
	public List<String> loadtopicType(String query) {
        List<String> results = loadAutoComplete(query,listOfTopicTypes);
        return results;
    }
	
	public List<String> loadtopic(String query) {
        List<String> results = loadAutoComplete(query,listOfTopics);
        return results;
    }
	
	public List<String> loadSubtopic(String query) {
        List<String> results = loadAutoComplete(query,listOfSubTopics);
        return results;
    }
	
	public List<String> loadExamType(String query) {
        List<String> results = loadAutoComplete(query,listOfexamType);
        return results;
    }
	
	public List<String> loadExam(String query) {
        List<String> results = loadAutoComplete(query,listOfexam);
        return results;
    }
	
	public List<String> loadAnswerType(String query) {
        List<String> results = loadAutoComplete(query,listOfanswerType);
        return results;
    }
	
	public List<String> loadAnswer(String query) {
        List<String> results = loadAutoComplete(query,listOfanswer);
        return results;
    }
	
	
	private List<String> loadAutoComplete(String query,HashMap<Integer,String> dataSource){
		List<String> results = new ArrayList<String>();
        String tempResult = null;
       
        for(int key: dataSource.keySet()) {
        	tempResult =dataSource.get(key);
            if(tempResult.toLowerCase().contains(query.toLowerCase())) {
            	 results.add(tempResult);
            }
        }
        return results;
	}
	
	
}
