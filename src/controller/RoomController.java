package controller;

import java.net.URL;
import java.util.ResourceBundle;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.RoomTableDB;
import model.DBConfig;

public class RoomController implements Initializable{
	
	@FXML
    private MFXButton add_update_button;

    @FXML
    private MFXTextField buildingname_field;

    @FXML
    private MFXTextField capacity_field;

    @FXML
    private VBox main_vbox;

    @FXML
    private MFXTextField roomname_field;
    
    @FXML
    private TextArea preView_textarea;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		add_update_button.setText(DBConfig.configuration().getProperty("ButtonStatus").toString());
		RoomTableDB.setItems(Integer.parseInt(DBConfig.configuration().getProperty("ID").toString()),	buildingname_field , roomname_field, capacity_field);
	}
	
	
    
    //clickables
    @FXML
    void cancel(ActionEvent event) {
   	 final Node source = (Node) event.getSource();
   	 final Stage stage = (Stage) source.getScene().getWindow();
   	 stage.close();
    }
    
    @FXML
    void addNewRoom(ActionEvent event) {
    	String $button_state = DBConfig.configuration().getProperty("ButtonStatus").toString();
    	switch($button_state) {
    		case "Update":
    			RoomTableDB.update(Integer.parseInt(DBConfig.configuration().getProperty("ID").toString()), buildingname_field.getText(), roomname_field.getText(), Integer.parseInt(capacity_field.getText()));
    			final Node source = (Node) event.getSource();
    		   	final Stage stage = (Stage) source.getScene().getWindow();
    		   	stage.close();
    			break;
    		case "Add":
    			RoomTableDB.add(buildingname_field.getText(), roomname_field.getText(), Integer.parseInt(capacity_field.getText()));
    			final Node source1 = (Node) event.getSource();
    		   	final Stage stage1 = (Stage) source1.getScene().getWindow();
    		   	stage1.close();
    			break;
    	}
//    	clear();
    }
	
    @FXML
    void getPreView(MouseEvent event) {
    	preView_textarea.setText("Building's Name = "+buildingname_field.getText()+"\nRoom's Name = "+roomname_field.getText()+"\nRoom's Capacity = "+capacity_field.getText());
    }
    

	//clear
	void clear() {
		buildingname_field.clear();
		roomname_field.clear();
		capacity_field.clear();
	}
	
	
	
	

}
