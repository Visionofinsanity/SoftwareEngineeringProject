package ProgramData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class CSVwriter {
	private PrintWriter os;
	
	public CSVwriter(String file, String delimiter) {
		File outputFile = new File(file);
		try {
			outputFile.createNewFile();
			os = new PrintWriter(outputFile);
			os.println(delimiter);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void append(String content) {
		os.append(content);
	}
	
	public void println(String content) {
		os.println(content);
	}
	
	public void clear() {
		os.write("");
	}
	
	public void close() {
		os.close();
	}
}
