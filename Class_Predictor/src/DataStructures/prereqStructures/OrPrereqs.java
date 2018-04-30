package DataStructures.prereqStructures;

import java.util.ArrayList;

import DataStructures.Catalog;
import DataStructures.Student;

public class OrPrereqs implements Prereq{
	private ArrayList<Prereq> prereqs;
	
	public OrPrereqs() {
		prereqs = new ArrayList<Prereq>();
	}
	
	public void addPrereq(Prereq prereq) {
		prereqs.add(prereq);
	}
	
	public void linkCourse(Catalog catalog) {
		for(Prereq p: prereqs) {
			if(p instanceof CoursePrereq) {
				((CoursePrereq) p).linkCourse(catalog);
			}
		}
	}

	@Override
	public boolean hasPrereqs(Student student) {
		for(int i = 0; i< prereqs.size(); i++) {
			if(prereqs.get(0).hasPrereqs(student))
				return true;
		}
		return false;
	}

}
