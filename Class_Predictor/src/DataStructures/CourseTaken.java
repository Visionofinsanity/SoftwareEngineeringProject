package DataStructures;

public class CourseTaken {
	public Course course;
	public char grade;
	
	public CourseTaken(Course course, String grade) {
		this.course = course;
		this.grade = grade.charAt(0);
	}
	
	public String printToFile() {
		return course.printToFile() + "," + grade;
	}
	
	public boolean hasPassed(char toPass) {
		String test = "ABCDF";
		for(char c: test.toCharArray()) {
			if(grade == c)
				return true;
			else if(toPass == c)
				return false;
		}
		return false;
	}
}
