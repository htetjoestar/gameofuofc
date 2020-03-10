package gameofuofc;

import java.io.Serializable;

public class Person implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		public int playerID;
		public String playerName;
		public Square playerLocation;
		public int playerGrades;
		public int playerSocial;
		
		public Person(int id, String name, Square location, int grades, int social) {
			this.playerID = id;
			this.playerName = name;
			this.playerLocation = location;
			this.playerGrades = grades;
			this.playerSocial = social;
		}
}
