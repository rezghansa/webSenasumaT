package webSenasumaT;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "exam", eager = true)
@SessionScoped
public class Exam implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		//validate login
		public String completeExam() {
			
			return "menu";
		}
}
