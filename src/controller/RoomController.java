package controller;

import java.net.URL;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.NewRoom;

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
    void cancel(MouseEvent event) {
   	 final Node source = (Node) event.getSource();
   	 final Stage stage = (Stage) source.getScene().getWindow();
   	 stage.close();
    }
    
    @FXML
    void addNewRoom(ActionEvent event) {
    	NewRoom room = new NewRoom();
    	room.add(buildingname_field.getText(), roomname_field.getText(), Integer.parseInt(capacity_field.getText()));
    	clear();
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	//clear
	void clear() {
		buildingname_field.clear();
		roomname_field.clear();
		capacity_field.clear();
	}

}
