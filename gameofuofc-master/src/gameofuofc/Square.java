package gameofuofc;

import java.io.Serializable;

public class Square implements Serializable {

	//Squares instance variables 
	private static final long serialVersionUID = 2L;
	int id;
	char type;
	int effectVal;
	public Square(){

	}
	/**
	 * The constructor which creates a square, all squares make up the board
	 * @param id The current squares ID	
	 * @param type The type of square, this changes what effects will happen upon landing on this square
	 * @param effectVal The value that will change a metric, depending on the type of square
	 */
	public Square(int id, char type, int effectVal) {
		this.id = id;
		this.type = type;
		this.effectVal = effectVal;
	}

	//Getters
	public int getSquareId() {
		return this.id;
	}

	public char getType() {
		return this.type;
	}

	public int getEffectVal() {
		return this.effectVal;
	}
}
