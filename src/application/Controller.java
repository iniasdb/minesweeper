package application;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller {
	
	public AnchorPane pane;
	static GridPane gPane;
	static Board board;
	static Timeline tl;
	static int seconds = 0;
	
	public void initialize() {
		int height = 400;
		int width = 400;
		this.amYTiles = height/this.tileSize;
		this.amXTiles = width/this.tileSize;	
		
		gPane = new GridPane();
		pane.getChildren().add(gPane);
		board = new Board(width, height);
		
		tl = new Timeline(new KeyFrame(Duration.seconds(1.0), ob -> {
			seconds++;
		}));
		
		tl.setCycleCount(10);
				
		fillBoard();
	}
	
	public void fillBoard() {		
		
		//create tiles
		for (int i = 1; i < amYTiles+1; i++) {
			for (int j = 1; j < amXTiles+1; j++) {
				
				if (i == 1 && j == 1) {
					tl.playFromStart();
				}
				
				board.createTile(gPane, j, i);
				board.createNeighborList(i, j);
			}
		}
		
		//create tiles
//		for (int i = 1; i < amYTiles; i++) {
//			for (int j = 1; j < amXTiles; j++) {
//				
//				//TODO
//				try {
//				board.createNeighborList(i, j);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				
//			}
//		}
		
		//get neighboring bombs and assign amount of bombs to tile value
		for (int i = 1; i < amYTiles+1; i++) {
			for (int j = 1; j < amXTiles+1; j++) {
				
				board.assignValues(i, j);
				
			}
		}
		
	}
	
	public static void gameOver() {
		tl.stop();
		gPane.setDisable(true);
		Stage secondaryStage = new Stage();
		FlowPane pane2;
		try {
			pane2 = new FlowPane();
			pane2.setAlignment(Pos.CENTER);
			pane2.setOrientation(Orientation.VERTICAL);
			secondaryStage.setScene(new Scene(pane2));
			secondaryStage.setWidth(400);
			secondaryStage.setHeight(400);
			secondaryStage.show();
			pane2.getChildren().add(new Label("You lost"));
			pane2.getChildren().add(new Label("Time elapsed: " + seconds + " seconds"));
			pane2.getChildren().add(new Label("Bombs found: " + board.getBombsFound() + " from " + board.getBombsAmount()));
			pane2.getChildren().add(new Label("Replay option doesnt work yet"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void gameWon() {
		tl.stop();
		gPane.setDisable(true);
		Stage secondaryStage = new Stage();
		FlowPane pane2;
		try {
			pane2 = new FlowPane();
			pane2.setAlignment(Pos.CENTER);
			pane2.setOrientation(Orientation.VERTICAL);
			secondaryStage.setScene(new Scene(pane2));
			secondaryStage.setWidth(400);
			secondaryStage.setHeight(400);
			secondaryStage.show();
			pane2.getChildren().add(new Label("You won"));
			pane2.getChildren().add(new Label("Time elapsed: " + seconds + " seconds"));
			pane2.getChildren().add(new Label("Bombs found: " + board.getBombsFound() + " from " + board.getBombsAmount()));
			pane2.getChildren().add(new Label("Replay option doesnt work yet"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	int tileSize = 20;
	int amYTiles;
	int amXTiles;
	
}
