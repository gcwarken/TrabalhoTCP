package courseAPI.domain;

import java.util.ArrayList;
import java.util.List;

public class Course {
	
	private String courseName;
	private int courseId; 
	private List<Group> groups;
	
	public Course(String name, int id){
		courseName = name;
		courseId = id;
		groups = new ArrayList<>();		
	}
	
	public String getCourseName(){
		return courseName;
	}
	
	public int getCourseID() {
		return courseId;
	}
}
