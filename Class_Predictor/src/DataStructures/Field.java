package DataStructures;

import java.util.ArrayList;

public class Field {
	public String fullName;
	public String abrName;
	public ArrayList<Course> courses;
	
	public Field(String fullName, String abrName) {
		this.fullName = fullName;
		this.abrName = abrName;
		courses = new ArrayList<Course>();
	}
	
	public void addCourse(String[] input) {
		courses.add(new Course(input, this));
	}
	
	public Course getCourse(int number) {
		//TODO MAKE BETTER!!!
		for(int i = 0; i < courses.size(); i++) {
			if(courses.get(i).number == number)
				return courses.get(i);
		}
		return null;
	}
	
	public void linkCourse(Catalog catalog) {
		for(Course c: courses) {
			c.linkCourse(catalog);
		}
	}
	
	public String printToFile() {
		return abrName;
	}
	
	public void printField() {
		System.out.println(printToFile() + ": " + fullName);
		for(Course c: courses) {
			System.out.print(c.toString());
		}
	}
}
