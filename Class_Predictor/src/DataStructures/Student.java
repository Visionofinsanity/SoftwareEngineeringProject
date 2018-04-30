package DataStructures;

import java.util.ArrayList;

public class Student {
	private String ID;
	private char campus;
	private String major;
	private int actMATH;
	private int satMATH;
	private ArrayList<CourseTaken> coursesTaken;

	public Student(String[] record){
		ID = record[1];
		campus = record[0].charAt(0);
		coursesTaken = new ArrayList<CourseTaken>();
		major = record[2];
		//TODO get actMATH and satMATH
	}
	
	public String toString() {
		String ret;
		ret = "ID Number: " + ID;
		ret += "\nCampus: " + campus;
		return ret;
	}
	
	public void addCourseTaken(Course course, String grade) {
		coursesTaken.add(new CourseTaken(course, grade));
	}
	
	public String[] printToFile() {
		String[] ret = new String[2];
		StringBuilder retStr = new StringBuilder();
		retStr.append(ID);
		retStr.append(',');
		retStr.append(major);
		
		ret[0] = retStr.toString();
		retStr = new StringBuilder();

		if(!coursesTaken.isEmpty()) {
			retStr.append(coursesTaken.get(0).printToFile());
			for(int i = 1; i < coursesTaken.size() -1; i++) {
				retStr.append(",");
				retStr.append(coursesTaken.get(i).printToFile());
			}
		}
		
		ret[1] = retStr.toString();
		
		return ret;
	}
	
	public String getID() {
		return ID;
	}
	
	public boolean hasPassed(Course course, Character grade) {
		//TODO Write this code!
		return false;
	}
	
	public boolean actMATHatLeast(int score) {
		return actMATH >= score;
	}
	
	public boolean satMATHatLeast(int score) {
		return satMATH >= score;
	}
	
	public boolean hasCreditsInField(String fieldAbr, int credits) {
		//TODO Write this code!
		return false;
	}
}
