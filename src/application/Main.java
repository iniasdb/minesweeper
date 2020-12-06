package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("build.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("minesweeper");
		primaryStage.show();
		primaryStage.setHeight(430);
		primaryStage.setWidth(407);
		primaryStage.setResizable(false);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
