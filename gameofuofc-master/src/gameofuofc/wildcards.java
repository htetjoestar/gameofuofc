package gameofuofc;
/**
 * Wildcards are a random element of the game. A player "draws" a wilcard whenever they land on a wildcard square.
 * The wildcards are shuffled each time before a player draws
 * 
 *
 */
public class wildcards {
	String desc;
	char effect;
	int effectVal;
	/**
	 * Constructor for the wildcard class
	 * @param newDisc The description of the wildcard
	 * @param newEff The effect on the grade metric
	 * @param newEffVal The effect on the social metric
	 */
    public wildcards(String newDisc, char newEff, int newEffVal) {
    	this.desc = newDisc;
    	this.effect = newEff;
    	this.effectVal = newEffVal;
    }
    //getters
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
