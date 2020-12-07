package application;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;


public class Tile extends Rectangle {
	
	public Tile(boolean hasBomb) {
		this.hasBomb = hasBomb;
	
        this.setFill(Color.ALICEBLUE);
        this.setHeight(tileSize);
        this.setWidth(tileSize);
        this.setStroke(Color.BLACK);
        this.setStrokeType(StrokeType.INSIDE);	
	}
	
	//flag a tile
	public void flag() {
		if (this.flagged == false) { //if not flagged yet, flag the tile

			this.setFill(new ImagePattern(new Image(getClass().getResource("images/flag.png").toExternalForm()))); //fill rect with flag image
			this.flagged = true;
		} else {  //if already flagged, unflag the tile
			this.flagged = false;
			this.setFill(Color.ALICEBLUE);
		}

	}
	
	public boolean isFlagged() {
		return this.flagged;
	}
	
	//run when clicked on tile
	public void reveal() {
		
		//check if tile has bomb
		if (hasBomb) {
			//set tile bomb image
			this.setFill(new ImagePattern(new Image(getClass().getResource("images/bomb.png").toExternalForm())));
			//load game over method
			Controller.gameOver();
		} else {
			//set tile image (check for empty rects and bombs
			if (this.value != 0 && this.value != 8) {
				this.setFill(new ImagePattern(new Image(getClass().getResource("images/"+this.value+".png").toExternalForm()))); //fill rect with value image
			} else if (this.value == 0) {
				try {
					checkNeighbors();
				} catch (Exception e) {
					e.printStackTrace();
				}
				this.setFill(Color.BROWN);
			}
			//make unclickable
			this.setDisable(true);
		}
		
	}
	
	public void checkNeighbors() {
		System.out.println(this.neighbors);
		for (int i = 0; i < this.neighbors.size(); i++) {
			if (!this.neighbors.get(i).hasBomb && !this.neighbors.get(i).isDisable()) {
				this.neighbors.get(i).reveal();
			}
		}
	}
	
	public boolean isBomb() {
		return hasBomb;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void setNeighbors(ArrayList<Tile> neighbors) {
		this.neighbors = neighbors;
	}
	
	public ArrayList<Tile> getNeighbors() {
		return this.neighbors;
	}
	
	private int tileSize = 20;
	private boolean hasBomb;
	private boolean flagged = false;
	private int value;
	private ArrayList<Tile> neighbors;
}
