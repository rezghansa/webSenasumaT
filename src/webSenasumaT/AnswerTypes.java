package webSenasumaT;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "answerTypes", eager = true)
@SessionScoped
public class AnswerTypes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int answerTypeId;
	private String answerTypeName;
	
	public int getAutoId() {
		return answerTypeId;
	}
	public void setAutoId(int autoId) {
		this.answerTypeId = autoId;
	}
	public String getAnswerTypeName() {
		return answerTypeName;
	}
	public void setAnswerTypeName(String answerTypeName) {
		this.answerTypeName = answerTypeName;
	}
	
	public void setAnswerType(){
		 DbConnector.connectToDatabase();
		 int autoTempId = DbConnector.getPrimaryKeyLastValue("answerType", "questionAnswerType");
		 answerTypeId = autoTempId+1;
		 String insertQuery = "insert into answerType(questionAnswerType,questionAnswerTypeName) values("+answerTypeId+",'"+answerTypeName+"')";
		 DbConnector.InsertionQuery(insertQuery);
		 DbConnector.ClearConnection();
	}
	
	public HashMap<Integer, String> loadAnswerTypes(){
		HashMap<Integer, String> answerTypes = null;
		DbConnector.connectToDatabase();
		String sqlString = "select * from answerType";
		ResultSet rs= DbConnector.getResults(sqlString);
		try{
			answerTypes = new HashMap<Integer,String>();
			while (rs.next()) {
				Integer key 	= rs.getInt("questionAnswerType");
				String value 	= rs.getString("questionAnswerTypeName");
				answerTypes.put(key, value);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		DbConnector.ClearConnection();
		return answerTypes;
	}
	
}
