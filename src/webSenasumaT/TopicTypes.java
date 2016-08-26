package webSenasumaT;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "topicTypes", eager = true)
@SessionScoped
public class TopicTypes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int topicTypeId; 
	private String topicType;
	public int getTopicTypeId() {
		return topicTypeId;
	}
	public void setTopicTypeId(int topicTypeId) {
		this.topicTypeId = topicTypeId;
	}
	public String getTopicType() {
		return topicType;
	}
	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}
	
	public void setTopicType(){
		 DbConnector.connectToDatabase();
		 int autoTempId = DbConnector.getPrimaryKeyLastValue("topicType", "topicTypeId");
		 topicTypeId = autoTempId+1;
		 String insertQuery = "insert into topicType(topicTypeId,topicType) values("+topicTypeId+",'"+topicType+"')";
		 DbConnector.InsertionQuery(insertQuery);
		 DbConnector.ClearConnection();
	}

}
