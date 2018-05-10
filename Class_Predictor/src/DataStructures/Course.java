package DataStructures;

import java.util.ArrayList;

import DataStructures.prereqStructures.*;

public class Course {
	public Field field;
	
	public int number;
	public boolean lab;
	
	public String name;
	
	public int gA= 0, gB = 0, gC = 0, gD = 0, gF = 0,gI = 0, gW = 0, gBlank = 0, gS = 0, gU = 0, gN = 0;
	
	public ArrayList<String> majorNames = new ArrayList<String>();
	public int[] majorNums = new int[0];
	
	public int credits;
	
	public String description;
	public boolean Fall = false, Summer = false, Spring = false, Even = false, Odd = false, Unknown = false;
	
	public ArrayList<Prereq> prereqs;
	
	public Course(Field field, int number) {
		this.field = field;
		this.number = number;
		prereqs = new ArrayList<Prereq>();
	}
	
	public Course(String[] input, Field field) {
		this.field = field;
		prereqs = new ArrayList<Prereq>();
		
		//input 0- Number and lab
		if(input[0].charAt(input[0].length() - 1) == 'L') {
			lab = true;
			number = Integer.parseInt(input[0].substring(0, input[0].length() - 1));
		}else {
			lab = false;
			number = Integer.parseInt(input[0]);
		}
		
		//input 1- Class name
		name = input[1];
		
		//input 2- Credit hours
		credits = Integer.parseInt(input[2]);
		
		//input 3- Description
		description = input[3];
		
		//input 4- Semesters Offered
		if(input[4] == "-") {
			Unknown = true;
		}else {
			if(input[4].charAt(0) == 'E') {
				Even = true;
				input[4] = input[4].substring(4);
			}else if(input[4].contains("Odd")) {
				Odd = true;
				input[4] = input[4].substring(3);
			}
		
			if(input[4].charAt(0) == 'F')
				Fall = true;
			if(input[4].charAt(input[4].length() - 1) == 'S')
				Spring = true;
			if(input[4].length() > 2)
				Summer = true;
		}
		
		//input 5+ - Prerequisites
		boolean con = false;
		if(input.length > 4) {
			for(int i = 5; i < input.length; i++) {
				if(Character.isUpperCase(input[i].charAt(0))) {
					if(!con) {
						if(!Character.isDigit(input[i].charAt(input[i].length() - 1))) {
							prereqs.add(new CoursePrereq(input[i].substring(0,input[i].length() - 1),
									input[i].charAt(input[i].length() - 1)));
						}else {
							prereqs.add(new CoursePrereq(input[i]));
						}
					}else {
						if(!Character.isDigit(input[i].charAt(input[i].length() - 1))) {
							prereqs.add(new ConCoursePrereq(input[i].substring(0,input[i].length() - 1),
									input[i].charAt(input[i].length() - 1)));
						}else {
							prereqs.add(new ConCoursePrereq(input[i]));
						}
						con = false;
					}
				}else {
					if(input[i] == "con") {
						con = true;
					}else if(input[i].indexOf("or") == 0){
						input[i] = input[i].substring(2);
						if(!(prereqs.get(prereqs.size() - 1) instanceof OrPrereqs)) {
							OrPrereqs orprereq = new OrPrereqs();
							orprereq.addPrereq(prereqs.get(prereqs.size() - 1));
							prereqs.set(prereqs.size() -1, orprereq);							
						}
						
						if(input[i].indexOf("ACTMATH") == 0) {
							((OrPrereqs) prereqs.get(prereqs.size() - 1)).addPrereq(new ActMathPrereq(Integer.parseInt(input[i].substring(7))));
						}else if (input[i].indexOf("SATMATH") == 0) {
							((OrPrereqs) prereqs.get(prereqs.size() - 1)).addPrereq(new SatMathPrereq(Integer.parseInt(input[i].substring(7))));
						}else if (input[i].equals("instruct")){
							
						}else {
							if(!Character.isDigit(input[i].charAt(input[i].length() - 1))) {
								((OrPrereqs) prereqs.get(prereqs.size() - 1)).addPrereq(new CoursePrereq(input[i].substring(0,input[i].length() - 1),
										input[i].charAt(input[i].length() - 1)));
							}else {
								((OrPrereqs) prereqs.get(prereqs.size() - 1)).addPrereq(new CoursePrereq(input[i]));
							}
						}
					}else if(input[i].indexOf("credits") == 0) {
						int indexnum = 0;
						for(int j = 7; j < input[i].length(); j++) {
							if(indexnum == 0 && Character.isDigit(input[i].charAt(j))) {
								indexnum = j;
							}
						}
						prereqs.add(new CreditInFieldPrereq(input[i].substring(7,indexnum),
								Integer.parseInt(input[i].substring(indexnum))));
						
					}else {
						//Check for 'instruct', 'orinstruct'
					}
					
				}
			}
		}
		
		
	}
	
	public int getLastLetterIndex(String course) {
		for(int i = course.length() - 1; i >= 0; i--) {
			if(!Character.isDigit(course.charAt(i)))
				return i;
		}
		return -1;
	}
	
	public void linkCourse(Catalog catalog) {
		//System.out.println("THis COurse" + printToFile() );
		for(Prereq p: prereqs) {
			if(p instanceof CoursePrereq) {
				((CoursePrereq) p).linkCourse(catalog);
			}else if(p instanceof ConCoursePrereq){
				((ConCoursePrereq) p).linkCourse(catalog);
			}else if(p instanceof OrPrereqs) {
				((OrPrereqs) p).linkCourse(catalog);
			}
		}
	}
	
	public boolean canTake(Student student) {
		//TODO write Code here!
		return false;
	}
	
	public String printToFile() {
		return field.printToFile() + number;
	}
	
	public boolean equals(Course c) {
		if(c.field.abrName.equals(this.field.abrName) &&
				c.number == this.number)
			return true;
		return false;
	}
	
	public int numStudentsTaken() {
		int ret = 0;
		for(int i = 0; i < majorNums.length; i++) {
			ret += majorNums[i];
		}
		return ret;
	}
	
	public String toString() {
		String ret= "";
		ret += printToFile();
		
		if(lab) {
			ret+= "L";
		}
		ret += ": " + name + "\n";
		ret += this.description + "\n";
		
		if(Unknown) 
			ret += "-";
		if(Even) 
			ret += "Even";
		if(Odd)
			ret += "Odd";
		if(Fall)
			ret += "F";
		if(Summer)
			ret += "Su";
		if(Spring)
			ret += "S";
		ret += "\n";
		
			
		
		
		
		
		return ret;
	}

}
