package GetInput;

import DataStructures.CourseTaken;
import DataStructures.Student;

public class CourseReader extends ExcelReader{
	int[] courseColumns = {4,7,8,14};

	public CourseReader(String path){
		super(path);
		columnsToRead = courseColumns;		
	}
	
	public String[] getNextCourse() {
		String[] record = nextRecord();
		if(record == null) 
			return null;
		else
			return record;
	}
	
	public String[] getHeaders() {
		return loader.headers;
	}
}
