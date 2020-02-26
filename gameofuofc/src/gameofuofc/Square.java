package gameofuofc;

public class Square {
	int id;
	char type;
	int effectVal;
	public Square(){

	}

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
