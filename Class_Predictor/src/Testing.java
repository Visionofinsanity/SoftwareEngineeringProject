import java.util.Timer;
import java.util.concurrent.TimeUnit;

import DataStructures.Catalog;
import DataStructures.Course;
import GetInput.CatalogConstructor;
import GetInput.DataCompiler;

public class Testing {
	String STUDENT_FILE_PATH = "/Portales And Ruidoso Students.xlsx";
	String COURSES_FILE_PATH = "/Portales And Ruidoso Courses.xlsx";
	String PREDICT_PATH = "/Students/students.csv";
	DataCompiler compiler;
	private String sPath = "/TestStudents.xlsx";
	private String cPath = "/TestCourses.xlsx";
	
	public Testing(){
		Catalog cat = CatalogConstructor.constructCatalog("/ENMUcatalog.txt");
		
		/*compiler = new DataCompiler(this.STUDENT_FILE_PATH, this.COURSES_FILE_PATH, cat);
		compiler.readStudents();
		compiler.readCourses();
		System.out.println("Compile complete");
		compiler.writeStudents();
		System.out.println("Print complete");*/
		
		Predictor pre = new Predictor(PREDICT_PATH, cat);
		
		Course[] courses = cat.getField("CS").getAllCourses();
		pre.makeStatistics();
		int[] nums = pre.predict(courses);
		
		for(int i = 0; i < courses.length; i++) {
			System.out.println(courses[i].printToFile());
			System.out.println(nums[i]);
		}
		
		
		pre.printStats();
		
		
		//System.out.println("Printing Caltalog");
		//cat.printCatalog();
		//System.out.println("WHatever");
		
		
		//MainMenu gui = new MainMenu();
		/*long startTime = System.nanoTime();
		StudentReader reader = new StudentReader(FILE_PATH);
		
		System.out.println("Reader Initialized:  " + TimeUnit.SECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS) + " seconds");
		
		String[] headers = reader.getHeaders();
		String allHeaders = headers[0];
		for(int i =1; i < headers.length;i++) {
			allHeaders += ", " + headers[i];
		}
		System.out.println(allHeaders);
		
		int count = 1;
		Student aStudent = reader.getNextStudent();
		
		while(aStudent != null) {
			count++;
			aStudent = reader.getNextStudent();
			if(count % 500 == 0) {
				System.out.println("Student #" + count);
				System.out.println("Time " + TimeUnit.SECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS));
				System.out.println(aStudent.toString());
				
			}
		}
		
		System.out.println("All " + count + " Students scanned");
		System.out.println("Time " + TimeUnit.SECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS));
		*/
		/*Student aStudent = reader.getNextStudent();
		System.out.println(aStudent.toString());
		
		
		
		System.out.println("Time " + TimeUnit.SECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS));
		
		
		while(record != null){
			//count++;
			record = reader.getNextRecord(getind);
			//if (count % 500 == 0){
				//System.out.println(count + " " + TimeUnit.SECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS));
			//}
		}
		System.out.println(count + " " + TimeUnit.SECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS));
	*/
	
	}
	
	public void testMethod(String one, String two) {
		one = "Test 3";
		two = "Test 4";
		System.out.println(one);
		System.out.println(two);
	}
}
