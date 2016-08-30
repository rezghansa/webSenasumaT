package webSenasumaT;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

@ManagedBean(name = "subtopics", eager = true)
@SessionScoped
public class SubTopics implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int subTopicId = 0;
	private String subTopic;
	private int topicId = 0;
	private HashMap<Integer, String> topicMap;
	public int getSubTopicId() {
		return subTopicId;
	}
	public void setSubTopicId(int subTopicId) {
		this.subTopicId = subTopicId;
	}
	public String getSubTopic() {
		return subTopic;
	}
	public void setSubTopic(String subTopic) {
		this.subTopic = subTopic;
	}
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	public HashMap<Integer, String> getTopicMap() {
		return topicMap;
	}
	public void setTopicMap(HashMap<Integer, String> topicMap) {
		this.topicMap = topicMap;
	}

	public void init(ComponentSystemEvent event) {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
	    	Topics topics = new Topics();
	    	setTopicMap(topics.loadTopics());
	    }
	}
	
	public void setSubTopic(){
		 DbConnector.connectToDatabase();
		 int autoTempId = DbConnector.getPrimaryKeyLastValue("subtopic", "subtopicId");
		 subTopicId = autoTempId+1;
		 String insertQuery = "insert into subtopic(subtopicId,subtopicname,topicId) values("+subTopicId+",'"+subTopic+"',"+topicId+")";
		 DbConnector.InsertionQuery(insertQuery);
		 DbConnector.ClearConnection();
	}
	public HashMap<Integer, String> loadSubTopicTypes() {
		HashMap<Integer, String> subTopics = null;
		DbConnector.connectToDatabase();
		String sqlString = "select * from subtopic";
		ResultSet rs= DbConnector.getResults(sqlString);
		try{
			subTopics = new HashMap<Integer,String>();
			while (rs.next()) {
				Integer key 	= rs.getInt("subtopicId");
				String value 	= rs.getString("subtopicname");
				subTopics.put(key, value);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		DbConnector.ClearConnection();
		return subTopics;
	}
	
}
