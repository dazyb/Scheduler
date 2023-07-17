package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Completed extends Stage{
	public Completed() {
		AnchorPane root;
		try {
			root = FXMLLoader.load(getClass().getResource("completed.fxml"));
			Scene scene = new Scene(root,208,40);
			this.setResizable(false);
			this.setScene(scene);
			this.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
