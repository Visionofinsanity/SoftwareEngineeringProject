package DataStructures.prereqStructures;

import DataStructures.Student;

public class CreditInFieldPrereq implements Prereq{
	private int credits;
	private String fieldAbr;
	
	public CreditInFieldPrereq(String fieldAbr, int credits) {
		this.fieldAbr = fieldAbr;
		this.credits = credits;
	}

	@Override
	public boolean hasPrereqs(Student student) {
		return student.hasCreditsInField(fieldAbr, credits);
	}

}
