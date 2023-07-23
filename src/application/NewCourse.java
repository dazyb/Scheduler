package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NewCourse extends Stage {
	public NewCourse() {
		VBox root;
		try {
			root = FXMLLoader.load(getClass().getResource("add.fxml"));
			Scene scene = new Scene(root,370,500);
			this.initStyle(StageStyle.UNDECORATED);
			this.setResizable(false);
			this.setScene(scene);
			this.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
