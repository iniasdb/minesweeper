package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller {
	
	public AnchorPane pane;
	static GridPane gPane;
	Board board;
	
	public void initialize() {
		int height = 400;
		int width = 400;
		this.amYTiles = height/this.tileSize;
		this.amXTiles = width/this.tileSize;	
		
		gPane = new GridPane();
		pane.getChildren().add(gPane);
		board = new Board(width, height);
				
		fillBoard();
	}
	
	public void fillBoard() {		
		
		//create tiles
		for (int i = 1; i < amYTiles+1; i++) {
			for (int j = 1; j < amXTiles+1; j++) {
				
				board.createTile(gPane, j, i);
				
			}
		}
		
		//get neighboring bombs and assign amount of bombs to tile value
		for (int i = 1; i < amYTiles+1; i++) {
			for (int j = 1; j < amXTiles+1; j++) {
				
				board.assignValues(i, j);
				
			}
		}
		
		//debug
		board.printValues();
		
		
	}
	
	public static void gameOver() {
		gPane.setDisable(true);
		Stage secondaryStage = new Stage();
		Pane pane2;
		try {
			pane2 = FXMLLoader.load(Controller.class.getResource("gameOver.fxml"));
			secondaryStage.setScene(new Scene(pane2));
			secondaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	int tileSize = 20;
	int amYTiles;
	int amXTiles;
	
}
