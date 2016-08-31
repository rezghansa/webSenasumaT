package webSenasumaT;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

@ManagedBean(name = "exam", eager = true)
@SessionScoped
public class Exam implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int autoId = 0;
	private String examName;
	private int examTypeId = 0;
	private int subTopicId = 0;	
	private HashMap<Integer, String> subTopicTypeMap;
	private HashMap<Integer,String> examTypeMap;
	
	

	public int getAutoId() {
		return autoId;
	}



	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}



	public String getExamName() {
		return examName;
	}



	public void setExamName(String examName) {
		this.examName = examName;
	}



	public int getExamTypeId() {
		return examTypeId;
	}



	public void setExamTypeId(int examTypeId) {
		this.examTypeId = examTypeId;
	}



	public int getSubTopicId() {
		return subTopicId;
	}



	public void setSubTopicId(int subTopicId) {
		this.subTopicId = subTopicId;
	}



	public HashMap<Integer, String> getSubTopicTypeMap() {
		return subTopicTypeMap;
	}



	public void setSubTopicTypeMap(HashMap<Integer, String> subTopicTypeMap) {
		this.subTopicTypeMap = subTopicTypeMap;
	}



	public HashMap<Integer, String> getExamTypeMap() {
		return examTypeMap;
	}



	public void setExamTypeMap(HashMap<Integer, String> examTypeMap) {
		this.examTypeMap = examTypeMap;
	}

	public void init(ComponentSystemEvent event) {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
	    	SubTopics subtopicTypeMap = new SubTopics();
	    	setSubTopicTypeMap(subtopicTypeMap.loadSubTopicTypes());
	    	ExamTypes examTypeMap = new ExamTypes();
	    	setExamTypeMap(examTypeMap.loadExamTypes());
	    }
	}

	public void setTopic(){
		 DbConnector.connectToDatabase();
		 int autoTempId = DbConnector.getPrimaryKeyLastValue("exam", "autoId");
		 autoId = autoTempId+1;
		 String insertQuery = "insert into exam(autoId,examName,examTypeId,subtopicId) values("+autoId+",'"+examName+"',"+examTypeId+","+subTopicId+")";
		 DbConnector.InsertionQuery(insertQuery);
		 DbConnector.ClearConnection();
	}

		//validate login
		public String completeExam() {
			
			return "menu";
		}



		public HashMap<Integer, String> loadExam() {
			HashMap<Integer, String> exames = null;
			DbConnector.connectToDatabase();
			String sqlString = "select * from exam";
			ResultSet rs= DbConnector.getResults(sqlString);
			try{
				exames = new HashMap<Integer,String>();
				while (rs.next()) {
					Integer key 	= rs.getInt("autoId");
					String value 	= rs.getString("examName");
					exames.put(key, value);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			DbConnector.ClearConnection();
			return exames;
		}
}
