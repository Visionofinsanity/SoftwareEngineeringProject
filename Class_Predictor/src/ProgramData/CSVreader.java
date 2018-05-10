package ProgramData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class will read from a special CSV file.  Rather than each item being separated
 * by a comma, each item is separated by a delimiter string as noted on the first line
 * of the document
 * @author Brennan Gorman, Caleb Sapp
 *
 */
public class CSVreader {
	private BufferedReader is;
	private String delimiter;
	
	/**
	 * Constructor creates the file reader
	 * @param file - File path to file to be read
	 */
	public CSVreader(String file) {
		try {
			is = new BufferedReader(new FileReader(file));
			
			//reads the delimiter from first line.
			delimiter = is.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads next line of document and returns as an array of strings, each denoting
	 * an item.  If at the end of the document, returns null.
	 * @return
	 */
	public String[] readNextLine() {
		String line;
		try {
			line = is.readLine();
			if(line != null)
				return line.split(delimiter);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Closes File stream.
	 */
	public void close() {
		try {
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
