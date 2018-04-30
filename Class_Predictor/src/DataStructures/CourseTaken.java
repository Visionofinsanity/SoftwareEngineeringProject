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
}
