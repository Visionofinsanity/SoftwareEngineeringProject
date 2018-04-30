package DataStructures.prereqStructures;

import DataStructures.Student;

public class SatMathPrereq implements Prereq{
	private int satMathPrereq;
	
	public SatMathPrereq(int satMathPrereq) {
		this.satMathPrereq = satMathPrereq;
	}
	@Override
	public boolean hasPrereqs(Student student) {
		return student.satMATHatLeast(satMathPrereq);
	}
	
}
