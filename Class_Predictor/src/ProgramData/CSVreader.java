package ProgramData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVreader {
	private BufferedReader is;
	private String delimiter;
	
	public CSVreader(String file) {
		try {
			is = new BufferedReader(new FileReader(file));
			delimiter = is.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String[] readNextLine() {
		String line;
		try {
			line = is.readLine();
			if(line != null && line != "")
				System.out.println(line);
			if(line != null)
				return line.split(delimiter);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void close() {
		try {
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
