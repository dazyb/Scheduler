package controller;

import java.net.URL;

import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import model.SecondSemesterTableDB;
import model.DBConfig;
import model.Faculty;
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
    @FXML
    private MFXComboBox<Integer> creditHours_cbox;
    
    
    
    
    ObservableList<Integer> level_list = FXCollections.observableArrayList(100,200,300,400);
    ObservableList<String> sem_list = FXCollections.observableArrayList("FirstSemester","SecondSemester");
    ObservableList<String> dept = FXCollections.observableArrayList(Faculty.get_Department(DBConfig.configuration().getProperty("faculty").toString()));
    ObservableList<Integer> creditHours = FXCollections.observableArrayList(1,2,3);
    
    
    @FXML
    void cancel(ActionEvent event) {
   	 final Node source = (Node) event.getSource();
   	 final Stage stage = (Stage) source.getScene().getWindow();
   	 stage.close();
    }
    
    @FXML
    void addNewCourse(ActionEvent event) {
    	try {
        	String $button_state = DBConfig.configuration().getProperty("ButtonStatus").toString();
        	switch($button_state) {
        		case "Update":
        			NewCourse.update(Integer.parseInt(DBConfig.configuration().getProperty("ID").toString()), coursename_field.getText(), department_cbox.getText(), coursecode_field.getText(), lecturername_field.getText(), linitial_field.getText()
        					, Integer.parseInt(noStudent_field.getText()), Integer.parseInt(level_cbox.getText()), programme_field.getText(), group_field.getText(),Integer.parseInt(creditHours_cbox.getText()));
        			final Node source = (Node) event.getSource();
        		   	final Stage stage = (Stage) source.getScene().getWindow();
        		   	stage.close();
        			break;
        		case "Add":
        			if(lecturername_field.getText().length()>0&&linitial_field.getText().length()>0) {
        				NewCourse.add(coursename_field.getText(), department_cbox.getSelectedItem(), coursecode_field.getText(), lecturername_field.getText(), linitial_field.getText()
            					, Integer.parseInt(noStudent_field.getText()), Integer.parseInt(level_cbox.getSelectedItem().toString()), programme_field.getText(), group_field.getText());
        				final Node source1 = (Node) event.getSource();
        			   	final Stage stage1 = (Stage) source1.getScene().getWindow();
        			   	stage1.close();
        				break;
        			}else {
        				JOptionPane.showMessageDialog(null, "Empty Spaces");
        			}

        			break;
        	}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
			final Node source = (Node) event.getSource();
		   	final Stage stage = (Stage) source.getScene().getWindow();
		   	stage.close();
		}
    	
    }
    
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//combo_boxes
		level_cbox.setItems(level_list);
		department_cbox.setItems(dept);
		creditHours_cbox.setItems(creditHours);
		//preset ui components(update)
		add_update_button.setText(DBConfig.configuration().getProperty("ButtonStatus").toString());
		

		NewCourse.setItems(Integer.parseInt(DBConfig.configuration().getProperty("ID").toString()), coursename_field, coursecode_field, department_cbox, 
					lecturername_field, linitial_field, noStudent_field, level_cbox, programme_field, group_field, creditHours_cbox);

		
			
	}
	
	void clear() {
		coursename_field.setText("");
		coursecode_field.setText("");
		lecturername_field.setText("");
		linitial_field.setText("");
		group_field.setText("");
	}
}
