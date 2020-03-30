package gameofuofc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class Board creates and initializes a new board based on the information about squares stored in a csv file.
 * In the program, the game's board is essentially an ArrayList of Squares. Each square is an object, that has different properties.
 */

public class Board {
	ArrayList<Square> squares = new ArrayList<Square>();
	public Board() {
	    try {
	        InputStream csvFile = new FileInputStream("csvfiles/prototypecsv.csv");
	        Scanner myReader = new Scanner(new InputStreamReader(csvFile));
	            String data = myReader.nextLine();
	        while (myReader.hasNextLine()) {
	            data = myReader.nextLine();
	            String [] split = data.split(",");
	            squares.add(new Square(Integer.parseInt(split[0]),split[1].charAt(0), Integer.parseInt(split[2])));
	        }
	        myReader.close();
	    } catch (FileNotFoundException e) {
	        System.out.println("error error");
	    }
	    // this code is copied from my hackathon app
	}
	
	/**
	 * The constructor creates a board using the information in the board csv file. It then adds all the squares in the csv into the squares ArrayList, which is essentially the game's board.
	 * @param index ID of the square
	 * @return Square of ID:index
	 */

	public Square getSquare(int index) {
		return squares.get(index);
	}
}
