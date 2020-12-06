package application;

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
	public void reveal(int i, int j) {
				
		if (hasBomb) {
			this.setFill(new ImagePattern(new Image(getClass().getResource("images/bomb.png").toExternalForm())));
			System.out.println("game over");
			Controller.gameOver();
		} else {
			//debug
			System.out.println("ja");
			System.out.println(this.getValue());
			//set tile image (check for empty rects and bombs
			System.out.println(this.value);
			if (this.value != 0 && this.value != 8) {
				this.setFill(new ImagePattern(new Image(getClass().getResource("images/"+this.value+".png").toExternalForm()))); //fill rect with value image
			} else if (this.value == 0) {
				this.setFill(Color.BEIGE);
			}
			//make unclickable
			this.setDisable(true);
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
	
	public void setImage(Image img) {
		this.img = img;
	}
	
	public Image getImgage() {
		return this.img;
	}
	
	private int tileSize = 20;
	private boolean hasBomb;
	private boolean flagged = false;
	private int value;
	private Image img;
}
