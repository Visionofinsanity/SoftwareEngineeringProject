import java.util.Timer;
import java.util.concurrent.TimeUnit;

import DataStructures.Catalog;
import DataStructures.Student;
import GetInput.CatalogConstructor;
import GetInput.DataCompiler;
import GetInput.DataLoader;
import GetInput.StudentReader;
import createGUI.MainMenu;

public class Testing {
	String FILE_PATH = "/Portales And Ruidoso Students.xlsx";
	DataCompiler compiler;
	private String sPath = "/TestStudents.xlsx";
	private String cPath = "/TestCourses.xlsx";
	
	public Testing(){
		
		/*compiler = new DataCompiler(sPath, cPath);
		compiler.readStudents();
		compiler.readCourses();
		compiler.writeStudents();*/
		
		Catalog cat = CatalogConstructor.constructCatalog("/ENMUcatalog.txt");
		cat.printCatalog();
		
		
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
