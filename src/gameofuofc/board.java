package gameofuofc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class board {
	ArrayList<squares> squares = new ArrayList<squares>();
	public board() {
	    try {
	        InputStream csvFile = new FileInputStream("csvfiles/prototypecsv.csv");
	        Scanner myReader = new Scanner(new InputStreamReader(csvFile));
	            String data = myReader.nextLine();
	        while (myReader.hasNextLine()) {
	            data = myReader.nextLine();
	            String [] split = data.split(",");
	            squares.add(new squares(Integer.parseInt(split[0]),split[1].charAt(0), Integer.parseInt(split[2])));
	        }
	        myReader.close();
	    } catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	    }
	}
	public squares getsquare(int index) {
		return squares.get(index);
	}
}
