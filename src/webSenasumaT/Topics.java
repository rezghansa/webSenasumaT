package webSenasumaT;

import java.io.Serializable;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

@ManagedBean(name = "topics", eager = true)
@SessionScoped
public class Topics implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int topicId;
	private String topicName;
	private int topicTypeId;
	private HashMap<Integer, String> topicTypeMap;
	
	public HashMap<Integer, String> getTopicTypeMap() {
		return topicTypeMap;
	}
	public void setTopicTypeMap(HashMap<Integer, String> topicTypeMap) {
		this.topicTypeMap = topicTypeMap;
	}
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public int getTopicTypeId() {
		return topicTypeId;
	}
	public void setTopicTypeId(int topicTypeId) {
		this.topicTypeId = topicTypeId;
	}
	
	public void setTopic(){
		 DbConnector.connectToDatabase();
		 int autoTempId = DbConnector.getPrimaryKeyLastValue("topics", "topicId");
		 topicId = autoTempId+1;
		 String insertQuery = "insert into topics(topicId,topicname,topicType) values("+topicId+",'"+topicName+"',"+topicTypeId+")";
		 DbConnector.InsertionQuery(insertQuery);
		 DbConnector.ClearConnection();
	}

	public void init(ComponentSystemEvent event) {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
	    	TopicTypes ansTypes = new TopicTypes();
	    	setTopicTypeMap(ansTypes.loadTopicTypes());
	    }
	}
	
}
