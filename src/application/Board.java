package application;


import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Board {

	public Board(int width, int height) {
		this.width = width;
		this.height = height;	
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void createTile(GridPane gPane, int j, int i) {
		//place random bombs
		boolean bomb = false;
	    double x = Math.random()*100;
	    
	    //random boolean with weight
	    if (x <= prob && bombsAmount < bombsWanted) {
	    	bomb = true;
	    } else {
	    	bomb = false;
	    }
	    
	    //create tile
		Tile tile = new Tile(bomb);

		//add event listener
		tile.setOnMouseClicked(e -> {
			if (e.getButton() == MouseButton.PRIMARY) {
				tile.reveal(i, j);
			} else {
				tile.flag();
			}
		});
		
		//add tile to tileArray
		createTileArray(tile, i, j);
		
		//check if bomb
		getBombs(tile, i, j);
		
		gPane.add(tile, j, i);
		
	}
	
	//debug
	public void printValues() {
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values.length; j++) {
				System.out.print(values[i][j]);
			}
			System.out.println();
		}
	}
	
	public void createTileArray(Tile tile, int i, int j) {
		tileArray[i][j] = tile;
	}
	
	//checks if tile is bomb
	public void getBombs(Tile tile, int i, int j) {
		if (tile.isBomb()) {
			bombArray[i][j] = true;
			tile.setFill(Color.BLACK);
			bombsAmount++;
		} else {
			bombArray[i][j] = false;
		}
	}
	
	public void assignValues(int i, int j) {
		int bombs = 0;
		
		//get neighboring bombs
		bombs = getBombsAround(i, j);

		//add to values array  (mainly for debug)
		values[i][j] = bombs;



		
		//set the tiles value to the amount of bombs
		tileArray[i][j].setValue(values[i][j]);
	}
	
	public int getBombsAround(int i, int j) {
		//check the neighboring indexes in bombArrays for bombs
		
		int temp = 0;
		
		//m m
		if (bombArray[i][j]) {
			return 8;
		} else {
			
			//l t
			if (bombArray[i-1][j-1]) {
				temp++;
			}
			//m t
			if (bombArray[i-1][j]) {
				temp++;
			}
			//r t
			if (bombArray[i-1][j+1]) {
				temp++;
			}
		
			//l m
			if (bombArray[i][j-1]) {
				temp++;
			}
			//r m
			if (bombArray[i][j+1]) {
				temp++;
			}
			
			//l b
			if (bombArray[i+1][j-1]) {
				temp++;
			}
			//m b
			if (bombArray[i+1][j]) {
				temp++;
			}
			//r b
			if (bombArray[i+1][j+1]) {
				temp++;
			}		
			return temp;
		}
	}
	
	
	GridPane pane;
	int width;
	int height;
	int bombsAmount = 0;
	int bombsWanted = 50;
	int prob = 13;
	
	//22 to avoid going out of bounds
	Tile tileArray[][] = new Tile[22][22];
	int values[][] = new int[22][22];
	static boolean bombArray[][] = new boolean[22][22];
}