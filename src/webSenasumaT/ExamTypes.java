package webSenasumaT;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "examTypes", eager = true)
@SessionScoped
public class ExamTypes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int examTypeId = 0;
	private String examTypeName;
	public int getExamTypeId() {
		return examTypeId;
	}
	public void setExamTypeId(int examTypeId) {
		this.examTypeId = examTypeId;
	}
	public String getExamTypeName() {
		return examTypeName;
	}
	public void setExamTypeName(String examTypeName) {
		this.examTypeName = examTypeName;
	}
	
	public void setExamType(){
		 DbConnector.connectToDatabase();
		 int autoTempId = DbConnector.getPrimaryKeyLastValue("examTypes", "examTypeId");
		 examTypeId = autoTempId+1;
		 String insertQuery = "insert into examTypes(examTypeId,examType) values("+examTypeId+",'"+examTypeName+"')";
		 DbConnector.InsertionQuery(insertQuery);
		 DbConnector.ClearConnection();
	}
	

}
