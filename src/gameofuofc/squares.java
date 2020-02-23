package gameofuofc;

public class squares {
	int id;
	char type;
	int effectVal;
	public squares(){
		
	}
	
	public squares(int id, char type, int effectVal) {
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
