package GetInput;

import DataStructures.Catalog;
import ProgramData.CSVreader;

/**
 * Reads course information from text document and constructs the catalog from it.
 * @author Brennan Gorman, Caleb Sapp
 *
 */
public class CatalogConstructor {
	
	/**
	 * Processes text document and returns the catalog.
	 * @param path - File path to course information
	 * @return cat - Processed catalog
	 */
	public static Catalog constructCatalog(String path) {		
		CSVreader reader = new CSVreader(path);
		Catalog cat = new Catalog();
		
		String[] input = reader.readNextLine();
		String field = null;
		
		//while there are more lines to read
		while(input != null) {
			//input length of 2 denotes a new Field to be added
			if(input.length == 2) {
				field = input[1];
				//add new field
				cat.addField(input[0], input[1]);
			}else {
				//add new course
				cat.addCourse(field, input);
			}
			input = reader.readNextLine();
		}
		
		//Close CSVReader, and then link the course prerequisites to their courses
		reader.close();
		cat.linkAllCourses();
		return cat;
	}
}
