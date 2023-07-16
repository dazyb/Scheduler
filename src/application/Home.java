package application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Home extends Stage{
	public Home() {
		StackPane root;
		try {
			root = FXMLLoader.load(getClass().getResource("home.fxml"));
			Scene scene = new Scene(root,1259,737);
			scene.getStylesheets().add(getClass().getResource("Home.css").toExternalForm());
			this.setResizable(false);
			this.setScene(scene);
			this.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
