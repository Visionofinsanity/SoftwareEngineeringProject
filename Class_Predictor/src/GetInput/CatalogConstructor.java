package GetInput;

import DataStructures.Catalog;
import DataStructures.Field;
import ProgramData.CSVreader;

public class CatalogConstructor {	
	public static Catalog constructCatalog(String path) {		
		CSVreader reader = new CSVreader(path);
		
		Catalog cat = new Catalog();
		
		String[] input = reader.readNextLine();
		String field = null;
		
		while(input != null) {
			//System.out.println("Catalog Constructor Loop");
			if(input.length == 2) {
				field = input[1];
				System.out.println("Adding Field");
				cat.addField(input[0], input[1]);
			}else {
				cat.addCourse(field, input);
			}
			input = reader.readNextLine();
		}
		
		
		reader.close();
		cat.linkAllCourses();
		return cat;
	}
}
