package DataStructures.prereqStructures;

import DataStructures.Catalog;
import DataStructures.Course;
import DataStructures.Student;

public class ConCoursePrereq implements Prereq{
	private String concurrentStr;
	private Course concurrent;
	private Character grade = '-';
	
	public ConCoursePrereq(String concurrentStr) {
		this.concurrentStr = concurrentStr;
	}
	
	public ConCoursePrereq(String concurrentStr, Character grade) {
		this.concurrentStr = concurrentStr;
		this.grade = grade;
	}
	
	public void linkCourse(Catalog catalog) {
		concurrent = catalog.getCourse(concurrentStr);
	}
	
	@Override
	public boolean hasPrereqs(Student student) {
		if(student.hasPassed(concurrent, grade) || concurrent.canTake(student))
			return true;
		else
			return false;
	}
	
}
