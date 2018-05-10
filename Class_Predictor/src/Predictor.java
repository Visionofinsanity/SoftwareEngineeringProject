import java.util.ArrayList;

import DataStructures.Catalog;
import DataStructures.Course;
import DataStructures.CourseTaken;
import DataStructures.Field;
import DataStructures.Student;
import DataStructures.prereqStructures.Prereq;
import ProgramData.CSVreader;

public class Predictor {
	private Catalog cat;
	private String sFilePath;

	public Predictor(String sFilePath, Catalog cat) {
		this.sFilePath = sFilePath;
		this.cat = cat;
	}
	
	public void makeStatistics() {
		CSVreader reader = new CSVreader(sFilePath);
		String[] nextline = reader.readNextLine();
		while(nextline != null) {
			String[] nextline2 = reader.readNextLine();
			Student s = new Student(nextline, nextline2, cat);
			
			if(s.campus == 'P') {
				for(CourseTaken courseT: s.coursesTaken) {
					switch(courseT.grade) {
						case 'A': courseT.course.gA++;
								break;
						case 'B': courseT.course.gB++;
								break;
						case 'C': courseT.course.gC++;
								break;
						case 'D': courseT.course.gD++;
								break;
						case 'F': courseT.course.gF++;
								break;
						case 'W': courseT.course.gW++;
								break;
						case 'I': courseT.course.gI++;
								break;
						case '-': courseT.course.gBlank++;
								break;
						case 'S': courseT.course.gS++;
								break;
						case 'U': courseT.course.gU++;
								break;
						case 'N': courseT.course.gN++;
								break;
						default: //System.out.println(courseT.grade);
					}
					boolean found = false;
					//System.out.println(courseT.course.majorNames.size());
					for(int i = 0; i < courseT.course.majorNames.size(); i++) {
						if(s.major.equals(courseT.course.majorNames.get(i))) {
							courseT.course.majorNums[i]++;
							found = true;
						}
					}
					if(!found) {
						courseT.course.majorNames.add(s.major);
						int[] newArr = new int[courseT.course.majorNums.length + 1];
						for(int i = 0; i < courseT.course.majorNums.length; i++) {
							newArr[i] = courseT.course.majorNums[i];
						}
						newArr[newArr.length - 1] = 1;
						courseT.course.majorNums = newArr;
					}
				}
			}
			nextline = reader.readNextLine();
		}
	}
	
	public int[] predict(Field field) {
		return predict(field.getAllCourses());
	}
	
	public int[] predict(Course[] courses) {
		int[] ret = new int[courses.length];
		for(int i = 0; i < ret.length; i++) {
			ret[i] = 0;
		}
		ArrayList<int[]> byMajor = new ArrayList<int[]>();
		ArrayList<String> majors = new ArrayList<String>();
		CSVreader reader = new CSVreader(sFilePath);
		String[] nextline = reader.readNextLine();
		int count = 0;
		
		while(nextline != null) {
			String[] nextline2 = reader.readNextLine();
			Student s = new Student(nextline, nextline2, cat);
			count++;
			if(s.campus == 'P') {
				int index = -1;
				for(int i = 0; i < majors.size(); i++) {
					if(s.major.equals(majors.get(i))) {
						index = i;
						break;
					}
				}
				if(index == -1) {
					majors.add(s.major);
					byMajor.add(new int[courses.length]);
					index = majors.size() - 1;
					for(int i = 0; i < byMajor.get(index).length; i++) {
						byMajor.get(index)[i] = 0;
					}
				}
				for(int i = 0; i < courses.length; i++) {
					Course c = courses[i];
					//TODO Account for Ds if noncore class
					if(!s.hasPassed(c, 'D')) {
						if(c.prereqs.size() == 0) {
							//TODO apply Freshman statistics
							byMajor.get(index)[i]++;
						}else {
							if(s.canTake(c)) {
								byMajor.get(index)[i]++;
							}else {
								//TODO if can take after passing the class this semester
							}
						}
					}
				}
			}
			
			nextline = reader.readNextLine();
		}
		//TODO apply statistics
		for(int i = 0; i < majors.size(); i++) {
			for(int j = 0; j < courses.length; j++) {
				int index = -1;
				for(int k = 0; k < courses[j].majorNames.size(); k++) {
					if(majors.get(i).equals(courses[j].majorNames.get(k)))
						index = k;
				}
				/*System.out.println(".1 " + majors.get(i));
				System.out.println("1: " + byMajor.get(i)[j]);*/
				ret[j] += byMajor.get(i)[j];
				/*if(index != -1) { 
					//System.out.println("2: " + courses[j].majorNums[index]);
					//System.out.println("3: " + courses[j].numStudentsTaken());
					//System.out.println("4: " + ((double) courses[j].majorNums[index] / (double) courses[j].numStudentsTaken()));
					ret[j] += (int) (byMajor.get(i)[j] * ((double) courses[j].majorNums[index] / (double) courses[j].numStudentsTaken()));
			
				}*/
			}
		}
		//System.out.println("Count: " + count);
		reader.close();
		return ret;
	}
	
	public void printStats() {
		for(Field f: cat.getAllFields()) {
			for(Course c: f.getAllCourses()) {
				System.out.println(f.abrName + c.number);
				System.out.println("Total: " + c.numStudentsTaken());
				System.out.println("A: " + c.gA);
				System.out.println("B: " + c.gB);
				System.out.println("C: " + c.gC);
				System.out.println("D: " + c.gD);
				System.out.println("F: " + c.gF);
				System.out.println("W: " + c.gW);
				System.out.println("I: " + c.gI);
				System.out.println("S: " + c.gS);
				System.out.println("U: " + c.gU);
				System.out.println("N: " + c.gN);
				System.out.println("-: " + c.gBlank);
				for(int i = 0; i < c.majorNames.size(); i++) {
					System.out.println(c.majorNames.get(i) + ": " + c.majorNums[i]);
				}
			}
		}
	}
}
