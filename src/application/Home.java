package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Home extends Application{
//	public Home() {
//		StackPane root;
//		try {
//			root = FXMLLoader.load(getClass().getResource("home.fxml"));
//			Scene scene = new Scene(root,1259,737);
//			scene.getStylesheets().add(getClass().getResource("Home.css").toExternalForm());
//			this.setResizable(false);
//			this.setScene(scene);
//			this.show();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
	@Override
	public void start(Stage primaryStage) {
		try {
			StackPane root = (StackPane)FXMLLoader.load(getClass().getResource("home.fxml"));
			Scene scene = new Scene(root,1259,737);
			scene.getStylesheets().add(getClass().getResource("Home.css").toExternalForm());
//			Image image=new Image(getClass().getResource("src/uccLogo.png").toURI().toString());
//			primaryStage.getIcons().add(image);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
