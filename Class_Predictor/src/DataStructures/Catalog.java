package DataStructures;

import java.util.ArrayList;

public class Catalog {
	private ArrayList<Field> fields;
	
	public Catalog() {
		fields = new ArrayList<Field>();
	}
	
	public void addField(String fullName, String abrName) {
		Field field = new Field(fullName,abrName);
		if(fields.size() > 0) {
			for(int i = fields.size() - 1; i >= 0; i--)
				if(greaterOrEqual(abrName, fields.get(i).abrName)) {
					if(i == fields.size())
						fields.add(field);
					else
						fields.add(i+1, field);
					return;
				}
			fields.add(0,field);
		}else {
			fields.add(field);
		}
	}
	
	public void addCourse(String fieldAbrName,String[] input) {
		/*for(Field f: fields) {
			System.out.println(f.printToFile());
		}*/
		getField(fieldAbrName).addCourse(input);
	}
	
	public Field getField(String abrName) {
		int lowB = 0;
		int hiB = fields.size() - 1;
		int mid;
		
		while(lowB <= hiB) {
			mid = (hiB + lowB) / 2;
			/*System.out.println("Low: " + lowB + " Mid: " + mid + " Hi: " + hiB);
			System.out.println("Mid:" + fields.get(mid).abrName);
			System.out.println("Abr:" + abrName);*/
			if(fields.get(mid).abrName == abrName)
				return fields.get(mid);
			else if(greaterOrEqual(fields.get(mid).abrName,abrName)) {
				hiB = mid - 1;
			}else {
				lowB = mid + 1;
			}
		}
		return null;
	}
	
	public Course getCourse(String courseStr) {
		int index = -1;
		for(int i = 0; i < courseStr.length(); i++) {
			if(index == -1 && Character.isDigit(courseStr.charAt(i)))
				index = i;
		}
		int num = Integer.parseInt(courseStr.substring(index));
		System.out.println(courseStr);
		return getField(courseStr.substring(0, index)).getCourse(num);
	}
	
	public void linkAllCourses() {
		for(Field f: fields){
			f.linkCourse(this);
		}
	}
	
	/**
	 * 
	 * @param one
	 * @param two
	 * @return true if one >= two, 0 if one < two
	 */
	public boolean greaterOrEqual(String one, String two) {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int index = 0;
		int oneind, twoind;
		
		while(index < one.length() && index < two.length()) {
			oneind = alphabet.indexOf(one.charAt(index));
			twoind = alphabet.indexOf(two.charAt(index));
			if(oneind != twoind) {
				if(oneind > twoind)
					return true;
				else
					return false;
			}
			index++;
		}
		
		if(one.length() > two.length())
			return true;
		else
			return false;
	}
	
	public void printCatalog() {
		
		for(Field f: fields) {
			f.printField();
		}
	}
}
