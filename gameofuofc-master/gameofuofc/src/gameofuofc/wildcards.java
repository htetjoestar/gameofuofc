package gameofuofc;

public class wildcards {
	String desc;
	char effect;
	int effectVal;
    public wildcards(String newDisc, char newEff, int newEffVal) {
    	this.desc = newDisc;
    	this.effect = newEff;
    	this.effectVal = newEffVal;
    }
    public String getDisc() {
    	return desc;
    }
    public char getEff() {
    	return effect;
    }
    public int getEffectVal() {
    	return effectVal;
    }
}
