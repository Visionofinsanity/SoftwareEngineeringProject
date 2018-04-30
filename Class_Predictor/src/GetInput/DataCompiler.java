package GetInput;

import java.util.ArrayList;

import DataStructures.Student;
import ProgramData.CSVwriter;

public class DataCompiler {
	private StudentReader sReader;
	private CourseReader cReader;
	
	private String sPath;
	private String cPath;
	
	private ArrayList<Student> students;
	
	private CSVwriter sWriter;
	
	public DataCompiler(String studentPath, String coursePath) {
		students = new ArrayList<Student>();
		sPath = studentPath;
		cPath = coursePath;
	}
	
	public void readStudents() {
		sReader = new StudentReader(sPath);
		Student next = sReader.getNextStudent();
		while(next != null) {
			students.add(next);			
			next = sReader.getNextStudent();
		}
	}
	
	public void readCourses() {
		cReader = new CourseReader(cPath);
		String[] next = cReader.getNextCourse();
		while(next != null) {
			int index = 0;
			while(index < students.size()) {
				if(next[0].equals(students.get(index).getID())) 
					break;
				index++;
			}
			if(index != students.size()) {
				int numInd = 0;
				while(numInd < next[2].length() && Character.isDigit(next[2].charAt(numInd)))
					numInd++;
				
				students.get(index).addCourseTaken(next[1], 
						Integer.parseInt(next[2].substring(0, numInd)), 
						next[3]);
			}
			
			next = cReader.getNextCourse();
		}
	}
	
	public void writeStudents() {
		for(Student s: students){
			sWriter = new CSVwriter(s.getID() + ".csv", ",");
			String[] toPrint = s.printToFile();
			for(int i = 0; i < toPrint.length;i++)
				sWriter.println(toPrint[i]);
			sWriter.close();
		}
	}
}
