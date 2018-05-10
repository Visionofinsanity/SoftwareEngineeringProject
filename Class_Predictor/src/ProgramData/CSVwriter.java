package ProgramData;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 * @author Brennan Gorman, Caleb Sapp
 * This class is to write to a CSV file, however, the values may be separated by something
 * other than commas.  The delimiter can be any string that is put at the beginning of the 
 * file.
 *
 */
public class CSVwriter {
	private PrintWriter os;
	
	/**
	 * Constructs the writer.
	 * @param file -File path of file to write to.
	 * @param delimiter - what separates values in the file
	 */
	public CSVwriter(String file, String delimiter) {
		File outputFile = new File(file);
		try {
			outputFile.createNewFile();
			os = new PrintWriter(outputFile);
			//prints delimiter so reader knows how values are separated.
			os.println(delimiter);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Appends content to end of file
	 * @param content
	 */
	public void println(String content) {
		os.println(content);
	}
	
	/**
	 * Erases document.
	 */
	public void clear() {
		os.write("");
	}
	
	/**
	 * Closes File
	 */
	public void close() {
		os.close();
	}
}
