package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import application.Home;
import application.Login;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	//ui components
	@FXML
    private MFXButton cancel_button;

    @FXML
    private MFXPasswordField password_field;

    @FXML
    private MFXCheckbox stay_logged_check;

    @FXML
    private MFXButton submit_button;

    @FXML
    private MFXTextField username_field;
	
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	//clickables---------------------------------------------------------------------------------------------------------------------------------------------
	//buttons
	@FXML
    void cancel(MouseEvent event) {
   	 final Node source = (Node) event.getSource();
   	 final Stage stage = (Stage) source.getScene().getWindow();
   	 stage.close();
    }

    @FXML
    void submit(ActionEvent event) {
    	if(Login.isVerified(username_field.getText(),password_field.getText())) {
    		new Home();
			final Node source = (Node) event.getSource();
	    	final Stage stage = (Stage) source.getScene().getWindow();
	    	stage.close();
    	}else {
    		JOptionPane.showMessageDialog(null, "else");
    	}
    		
    }
	
}
