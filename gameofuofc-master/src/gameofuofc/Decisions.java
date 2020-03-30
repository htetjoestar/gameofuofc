package gameofuofc;

import java.util.Scanner;
/**
 * The decisions are required stop points in the game. A player needs to make a decision everytime they pass by or land on a
 * decision spot. The consequences of the decision are not revealed until a player makes a decision
 * 
 *
 */
public class Decisions {
private String Desc;
private String Op1;
private int effg1;
private int effs1;
private String Op2;
private int effg2;
private int effs2;
private Scanner playerin = new Scanner(System.in);
private int choice;
	/**
	 * The constructor for the Decision class. 
	 * @param newDesc The Description of the Decision
	 * @param newOp1 The description for the first option
	 * @param effg1 The effect on the grade metric of the first option
	 * @param effs1 The effect on the social metric of the first option
	 * @param newOp2 The description for the second option
	 * @param effg2 The effect on the grade metric of the second option
	 * @param effs2 The effect on the social metric of the second option
	 */
	public Decisions(String newDesc, String newOp1,int effg1, int effs1,String newOp2,int effg2, int effs2) {
		this.Desc = newDesc;
		this.Op1 = newOp1;
		this.effg1 = effg1;
		this.effs1 = effs1;
		this.Op2 = newOp2;
		this.effg2 = effg2;
		this.effs2 = effs2;
	}
	/**
	 * The copy Constructor of the Decision class
	 * @param tocopy the decision to be copied
	 */
	public Decisions(Decisions tocopy) {
		this.Desc = tocopy.getDesc();
		this.Op1 = tocopy.getOp1();
		this.Op2 = tocopy.getOp2();
		this.effg1 = tocopy.getEffg1();
		this.effg2 = tocopy.getEffg2();
		this.effs1 = tocopy.getEffs1();
		this.effs2 = tocopy.getEffs2();
	}
	/**
	 * Prompts the player to make a Decision between the two options
	 * @return The choice that the player has made
	 */
	public int makeDecision() {
		System.out.println(Desc);
		System.out.println("1 : " + Op1);
		System.out.println("2 : " + Op2);
		choice = playerin.nextInt();
		if (choice == 1) {
			return 1;
		} else if (choice == 2) {
			return 2;
		}
		return 0;
	}
	// getters
	public String getDesc() {
		return Desc;
	}
	public String getOp1() {
		return Op1;
	}
	public String getOp2() {
		return Op2;
	}
	public int getEffg1(){
		return effg1;
	}
	public int getEffs1(){
		return effs1;
	}
	public int getEffg2(){
		return effg2;
	}
	public int getEffs2(){
		return effs2;
	}
}
