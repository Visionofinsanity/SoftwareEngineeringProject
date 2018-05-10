package GetInput;

import java.io.File;
import java.util.ArrayList;

import DataStructures.Catalog;
import DataStructures.Course;
import DataStructures.Student;
import ProgramData.CSVwriter;

/**
 * Compiles student and courses taken information into one text file as /Students/students.txt
 * @author visio
 *
 */
public class DataCompiler {
	private ExcelReader sReader, cReader;
	private Catalog catalog;
	
	private String sPath;
	private String cPath;
	
	private ArrayList<Student> students;
	
	private CSVwriter sWriter;
	
	/**
	 * Constructor assigns local variables to class variables.
	 * @param studentPath
	 * @param coursePath
	 * @param catalog
	 */
	public DataCompiler(String studentPath, String coursePath, Catalog catalog) {
		students = new ArrayList<Student>();
		sPath = studentPath;
		cPath = coursePath;
		this.catalog = catalog;
	}
	
	/**
	 * Reads in all the students in.
	 */
	public void readStudents() {
		//TODO could Insertion sort students
		sReader = new ExcelReader(sPath, true);
		String[] next = sReader.getNextRecord();
		while(next != null) {			
			students.add(new Student(next));
			next = sReader.getNextRecord();
		}
	}
	
	/**
	 * Reads all the courses in and assigns them to the appropriate student
	 */
	public void readCourses() {
		//TODO could improve efficiency of finding students with binary search
		cReader = new ExcelReader(cPath, false);
		String[] next = cReader.getNextRecord();
		while(next != null) {
			int index = 0;
			
			//finds index of student to assign course to.
			while(index < students.size()) {
				if(next[0].equals(students.get(index).getID())) 
					break;
				index++;
			}
			if(index != students.size()) {
				//TODO incorporate letters into course class
				//clears any letters at end of course number
				int numInd = 0;
				while(numInd < next[2].length() && Character.isDigit(next[2].charAt(numInd)))
					numInd++;
				String courseStr = next[1] + next[2].substring(0, numInd);
				
				//gets Course from catalog
				Course course = catalog.getCourse(courseStr);
				
				//if course found, add to student
				if(course != null) {
					students.get(index).addCourseTaken(course, next[3]);
				}
			}
			
			next = cReader.getNextRecord();
		}
	}
	
	//TODO Saving for bucket sort of Student records
	/*public String getWritePath(String currentPath, String ID, String currentStr, int depth) {
		System.out.println("Path: " + currentPath);
		File file = new File(currentPath);
		currentStr += ID.charAt(depth);
		
		for(File f: file.listFiles()) {
			if(f.isFile()) {
				if(f.getName().equals(ID + ".csv"))
					return currentPath;
				if(f.getName().indexOf(currentStr) == 0) {
					String nextPath = currentPath + "/" + ID.charAt(depth);
					File newDir = new File(nextPath);
					newDir.mkdir();
					f.renameTo(new File(nextPath + "/" + f.getName()));
					return getWritePath(nextPath, ID, currentStr, depth + 1);
				}
			}else if(f.isDirectory()){
				System.out.println(f.getName());
				if(f.getName().equals("" + ID.charAt(depth)))
						getWritePath(currentPath + "/" + ID.charAt(depth), ID, currentStr, 
								depth + 1);
				
			}
		}
		
		return currentPath; 
	}*/
	
	/**
	 * Compiles all students into one csv file
	 */
	public void writeStudents() {
		sWriter = new CSVwriter("/Students/students.csv", ",");
		for(Student s: students){
			//TODO Saving for bucket sort of students
			/*String path = "/Students";
			String fname = "";
			File check = new File(path);
			if(!check.exists())
				check.mkdir();
			path = getWritePath(path, s.getID(), "", 0);
			if(check.exists())
				System.out.print("True");
			System.out.println(path + "/" + s.getID() + ".csv");
			
			sWriter = new CSVwriter(path + "/" + s.getID() + ".csv", ",");*/
			
			String[] toPrint = s.printToFile();
			for(int i = 0; i < toPrint.length;i++)
				sWriter.println(toPrint[i]);
			
		}
		sWriter.close();
		students.clear();
	}
}
