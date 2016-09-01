package webSenasumaT;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.DragDropEvent;

 
@ManagedBean(name = "examSetupView")
@ViewScoped
public class ExamSetupView implements Serializable {
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{questions}")
    private Questions service;
 
    private List<Questions> cars;
     
    private List<Questions> droppedCars;
     
    private Questions selectedCar;
     
    @PostConstruct
    public void init() {
        cars = service.loadListQuestions();
        droppedCars = new ArrayList<Questions>();
    }
     
    public void onCarDrop(DragDropEvent ddEvent) {
    	Questions car = ((Questions) ddEvent.getData());
  
        droppedCars.add(car);
        cars.remove(car);
    }
     
    public void setService(Questions service) {
        this.service = service;
    }
 
    public List<Questions> getCars() {
        return cars;
    }
 
    public List<Questions> getDroppedCars() {
        return droppedCars;
    }    
 
    public Questions getSelectedCar() {
        return selectedCar;
    }
 
    public void setSelectedCar(Questions selectedCar) {
        this.selectedCar = selectedCar;
    }
}
