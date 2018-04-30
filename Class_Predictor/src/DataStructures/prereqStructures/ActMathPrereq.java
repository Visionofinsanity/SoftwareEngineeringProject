package DataStructures.prereqStructures;

import DataStructures.Student;

public class ActMathPrereq implements Prereq{
	private int actMathScore;

	public ActMathPrereq(int actMathScore) {
		this.actMathScore = actMathScore;
	}
	@Override
	public boolean hasPrereqs(Student student) {
		return student.actMATHatLeast(actMathScore);
	}

}
