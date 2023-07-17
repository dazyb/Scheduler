package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Loading extends Stage{
	public Loading() {
		AnchorPane root;
		try {
			root = FXMLLoader.load(getClass().getResource("loading.fxml"));
			Scene scene = new Scene(root,227,40);
			this.setResizable(false);
			this.setScene(scene);
			this.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
