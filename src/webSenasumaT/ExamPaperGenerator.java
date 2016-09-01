package webSenasumaT;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

@ManagedBean(name = "ExamPaperGenerator", eager = true)
@SessionScoped
public class ExamPaperGenerator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void init(ComponentSystemEvent event) {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
	    	loadData();
	    }
	}
	
	public void loadData(){
		ArrayList<ExamPapaerBean> listOfExamPaperBeans = new ArrayList<ExamPapaerBean>();
		int examId = 2;
		String queryFetch = "SELECT a.ansewer,a.incorrectAnswer,ast.questionAnswerTypeName,q.question FROM "+ 
							"answers as a,answertype as ast,questions as q,examquestions as ex "+
							"where ex.examId = "+examId+" group by q.questionId";
		DbConnector.connectToDatabase();
		ResultSet rs= DbConnector.getResults(queryFetch);
		try {
			if(!rs.wasNull()){
				ExamPapaerBean examPaperBean = new ExamPapaerBean();
				examPaperBean.setAnsewer((String)rs.getString("ansewer"));
				examPaperBean.setAnsewer((String)rs.getString("incorrectAnswer"));
				examPaperBean.setAnsewer((String)rs.getString("questionAnswerTypeName"));
				examPaperBean.setAnsewer((String)rs.getString("question"));
				listOfExamPaperBeans.add(examPaperBean);
			}
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
