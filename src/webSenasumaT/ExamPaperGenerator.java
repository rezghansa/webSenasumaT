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
	private ArrayList<ExamPapaerBean> listOfExamPaperBeans;
	
	
	
	public ArrayList<ExamPapaerBean> getListOfExamPaperBeans() {
		return listOfExamPaperBeans;
	}

	public void setListOfExamPaperBeans(ArrayList<ExamPapaerBean> listOfExamPaperBeans) {
		this.listOfExamPaperBeans = listOfExamPaperBeans;
	}

	public void init(ComponentSystemEvent event) {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
	    	loadData();
	    }
	}
	
	public void loadData(){
		listOfExamPaperBeans = new ArrayList<ExamPapaerBean>();
		int examId = 2;
		String queryFetch = "SELECT a.ansewer as ansewer,a.incorrectAnswer as incorrectAnswer,ast.questionAnswerTypeName as questionAnswerTypeName,q.question as question FROM "+ 
							"answers as a,answertype as ast,questions as q,examquestions as ex "+
							"where ex.examId = "+examId+" group by q.questionId";
		DbConnector.connectToDatabase();
		ResultSet rs= DbConnector.getResults(queryFetch);
		try {
			while(rs.next()){
				ExamPapaerBean examPaperBean = new ExamPapaerBean();
				examPaperBean.setAnsewer((String)rs.getString("ansewer"));
				examPaperBean.setIncorrectAnswer((String)rs.getString("incorrectAnswer"));
				examPaperBean.setQuestionAnswerTypeName((String)rs.getString("questionAnswerTypeName"));
				examPaperBean.setQuestion((String)rs.getString("question"));
				listOfExamPaperBeans.add(examPaperBean);
			}
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
