package gameofuofc;

public class squares {
	String id;
	Boolean multipath;
	char type;
	int effectVal;
	
	squares(String id, Boolean multipath, char type, int effectVal) {
		this.id = id;
		this.multipath = multipath;
		this.type = type;
		this.effectVal = effectVal;
	}
	
	//Getters 
	String getSquareId() {
		return this.id;
	}
	
	Boolean getMultipath() {
		return this.multipath;
	}
	
	char getType() {
		return this.type;
	}
	
	int getEffectVal() {
		return this.effectVal;
	}
}
