package DataStructures;

import java.util.ArrayList;

import DataStructures.prereqStructures.Prereq;

public class Student {
	private String ID;
	public char campus;
	public String major;
	private int actMATH;
	private int satMATH;
	public ArrayList<CourseTaken> coursesTaken;

	public Student(String[] record){
		ID = record[1];
		campus = record[0].charAt(0);
		coursesTaken = new ArrayList<CourseTaken>();
		major = record[2];
		//TODO FIX THIS!!!!
		try {
			actMATH = Integer.parseInt(record[3]);
		}catch(NumberFormatException e) {
			actMATH = 0;
		}
		try {
			satMATH = Integer.parseInt(record[3]);
		}catch(NumberFormatException e) {
			satMATH = 0;
		}
	}
	
	public Student(String[] record1, String[] record2, Catalog cat) {
		ID = record1[0];
		campus = record1[2].charAt(0);
		coursesTaken = new ArrayList<CourseTaken>();
		major = record1[1];
		actMATH = Integer.parseInt(record1[3]);
		satMATH = Integer.parseInt(record1[4]);
		
		/*System.out.println("Student");
		for(int i = 0; i < record1.length; i++) {
			System.out.println(record1[i]);
		}
		System.out.println("Courses " + record2.length);
		for(int i = 0; i < record2.length; i++) {
			System.out.println(record2[i]);
		}*/
		if(!record2[0].equals("skip")) {
			for(int i = 0; i < record2.length; i+=2) {
				addCourseTaken(cat.getCourse(record2[i]), record2[i+1]);
			}
		}
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
		retStr.append(',');
		retStr.append(campus);
		retStr.append(',');
		retStr.append(actMATH);
		retStr.append(',');
		retStr.append(satMATH);
		
		ret[0] = retStr.toString();
		retStr = new StringBuilder();

		if(!coursesTaken.isEmpty()) {
			retStr.append(coursesTaken.get(0).printToFile());
			for(int i = 1; i < coursesTaken.size() -1; i++) {
				retStr.append(",");
				retStr.append(coursesTaken.get(i).printToFile());
			}
		}else {
			retStr.append("skip");
		}
		
		ret[1] = retStr.toString();
		
		return ret;
	}
	
	public String getID() {
		return ID;
	}
	
	public boolean canTake(Course course) {
		for(Prereq p: course.prereqs) {
			if(!p.hasPrereqs(this))
				return false;
		}
			
		return true;
	}
	
	public boolean hasPassed(Course course, Character grade) {
		for(CourseTaken courseT: coursesTaken) {
			if(course.equals(courseT.course)) {
				if(courseT.hasPassed(grade))
					return true;
			}
				
		}
		return false;
	}
	
	public boolean actMATHatLeast(int score) {
		return actMATH >= score;
	}
	
	public boolean satMATHatLeast(int score) {
		return satMATH >= score;
	}
	
	public boolean hasCreditsInField(String fieldAbr, int credits) {
		int fieldCredits = 0;
		for(CourseTaken courseT: coursesTaken) {
			if(fieldAbr.equals(courseT.course.field.abrName)) {
				fieldCredits += courseT.course.credits;
				if(fieldCredits >= credits)
					return true;
			}
				
		}
		return false;
	}
}
