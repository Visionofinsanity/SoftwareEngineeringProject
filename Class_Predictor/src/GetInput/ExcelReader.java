package GetInput;

/**
 * Higher Level control over the DataLoader.  Stores columns that the Course and
 * Student classes need.
 * @author Brennan Gorman, Caleb Sapp
 *
 */
public class ExcelReader {
	private DataLoader loader;
	private int[] columnsToRead;
	private int[] courseColumns = {4,7,8,14};
	private int[] studentColumns = {1,4,6, 19, 22};

	/**
	 * Constructs the reader, and determines which columns to use
	 * @param path - File path to excel file to be read
	 * @param student - if true, use studentColumns, if not, use courseColumns
	 */
	public ExcelReader(String path, boolean student){
		loader = new DataLoader(path);
		if(student)
			columnsToRead = studentColumns;
		else
			columnsToRead = courseColumns;
	}
	
	/**
	 * Returns the next record of relevant information, returns empty if at
	 * end of excel file.
	 * @return String[] - record
	 */
	public String[] getNextRecord() {
		String[] record = loader.getNextRecord(columnsToRead);
		if(record == null) 
			return null;
		else
			return record;
	}
	
	/**
	 * Returns first row of excel sheet, where the headers should be
	 * @return String[] - headers
	 */
	public String[] getHeaders() {
		return loader.headers;
	}
}
