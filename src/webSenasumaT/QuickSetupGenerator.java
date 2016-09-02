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
	private HashMap<Integer,String> listOfTopicTypes;
	private HashMap<Integer,String> listOfTopics;
	
	
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
	    	listOfTopicTypes =  topicTypes.loadTopicTypes();
	    	listOfTopics = topics.loadTopics();
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
