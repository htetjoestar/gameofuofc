package gameofuofc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

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
	public Square getSquare(int index) {
		return squares.get(index);
	}
}
