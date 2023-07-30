package controller;

import java.net.URL;

import java.util.ResourceBundle;

import model.SecondSemesterTableDB;
import model.Temp;
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
    private MFXTextField linitial_field;

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
    
    @FXML
    private MFXComboBox<String> department_cbox;
    
    
    
    
    ObservableList<Integer> level_list = FXCollections.observableArrayList(100,200,300,400);
    ObservableList<String> sem_list = FXCollections.observableArrayList("FirstSemester","SecondSemester");
    ObservableList<String> dept = FXCollections.observableArrayList(SecondSemesterTableDB.getDepartment());
    
    
    @FXML
    void cancel(ActionEvent event) {
   	 final Node source = (Node) event.getSource();
   	 final Stage stage = (Stage) source.getScene().getWindow();
   	 stage.close();
    }
    
    @FXML
    void addNewCourse(ActionEvent event) {
//    	Temp.setProperty("ID", "0");
    	String $button_state = Temp.configuration().getProperty("ButtonStatus").toString();
    	switch($button_state) {
    		case "Update":
    			NewCourse.update(Integer.parseInt(Temp.configuration().getProperty("ID").toString()), coursename_field.getText(), department_cbox.getSelectedItem(), coursecode_field.getText(), lecturername_field.getText(), linitial_field.getText()
    					, Integer.parseInt(noStudent_field.getText()), level_cbox.getSelectedItem(), programme_field.getText(), group_field.getText(), semester_cbox.getSelectedItem());
    			break;
    		case "Add":
    			NewCourse.add(coursename_field.getText(), department_cbox.getSelectedItem(), coursecode_field.getText(), lecturername_field.getText(), linitial_field.getText()
    					, Integer.parseInt(noStudent_field.getText()), level_cbox.getSelectedItem(), programme_field.getText(), group_field.getText());
    	}
    	
    }
    
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//combo_boxes
		level_cbox.setItems(level_list);
		semester_cbox.setItems(sem_list);
		department_cbox.setItems(dept);
		//preset ui components(update)
		add_update_button.setText(Temp.configuration().getProperty("ButtonStatus").toString());
		NewCourse.setItems(Integer.parseInt(Temp.configuration().getProperty("ID").toString()), coursename_field, coursecode_field, department_cbox, 
				lecturername_field, linitial_field, noStudent_field, level_cbox, programme_field, group_field, semester_cbox);	
	}
}
