package GetInput;

import DataStructures.Student;

public class StudentReader extends ExcelReader{
	
	int[] studentColumns = {1,4,6};

	public StudentReader(String path){
		super(path);
		columnsToRead = studentColumns;		
	}
	
	public Student getNextStudent() {
		String[] record = nextRecord();
		if(record == null) 
			return null;
		else
			return new Student(record);
	}
	
	public String[] getHeaders() {
		return loader.headers;
	}
}
