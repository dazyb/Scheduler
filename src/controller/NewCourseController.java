package controller;

import java.net.URL;

import java.util.ResourceBundle;

import model.NewCourse;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewCourseController implements Initializable{
	@FXML
    private MFXButton add_update_button;

    @FXML
    private MFXTextField coursecode_field;

    @FXML
    private MFXTextField coursename_field;

    @FXML
    private MFXTextField group_field;

    @FXML
    private MFXTextField initails_field;

    @FXML
    private MFXTextField lecturername_field;

    @FXML
    private MFXComboBox<Integer> level_cbox;

    @FXML
    private VBox main_vbox;

    @FXML
    private MFXTextField noStudent_field;

    @FXML
    private MFXTextField programme_field;

    @FXML
    private MFXComboBox<String> semester_cbox;
    
    
    ObservableList<Integer> level_list = FXCollections.observableArrayList(100,200,300,400);
    ObservableList<String> sem_list = FXCollections.observableArrayList("FirstSemester","SecondSemester");
    
    @FXML
    void cancel(MouseEvent event) {
   	 final Node source = (Node) event.getSource();
   	 final Stage stage = (Stage) source.getScene().getWindow();
   	 stage.close();
    }
    
    @FXML
    void addNewCourse(ActionEvent event) {
    	addNew();
    }
    
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//combo_boxes
		level_cbox.setItems(level_list);
		semester_cbox.setItems(sem_list);
		
	}
	
	void addNew() {

	}
	
	void update() {
		
	}
	
	

}
