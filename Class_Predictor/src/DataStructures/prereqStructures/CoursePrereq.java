package DataStructures.prereqStructures;

import DataStructures.Catalog;
import DataStructures.Course;
import DataStructures.Student;

public class CoursePrereq implements Prereq{
	
	private String prereqStr;
	private Course prereq;
	private char grade = '-';
	
	public CoursePrereq(String prereqStr) {
		this.prereqStr = prereqStr;
	}
	
	public CoursePrereq(String prereqStr, char grade) {
		this.prereqStr = prereqStr;
		this.grade = grade;
	}
	
	public void linkCourse(Catalog catalog) {
		prereq = catalog.getCourse(prereqStr);
	}

	@Override
	public boolean hasPrereqs(Student student) {
		return student.hasPassed(prereq, grade);
	}
	
	public String toString() {
		return "Course Prereq: " + prereq.field.abrName + prereq.number;
	}

}
