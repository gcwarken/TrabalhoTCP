package courseAPI.domain;

import java.util.ArrayList;
import java.util.List;

public class Course {
	
	private String courseName;
	private String courseId; 
	private List<Group> groups;
	
	public Course(String name, String id, List<Group> groups){
		this.courseName = name;
		this.courseId = id;
		this.groups = new ArrayList<Group>();
		this.groups.addAll(groups);		
	}
	
	public Course(String name, String id){
		this.courseName = name;
		this.courseId = id;
		this.groups = new ArrayList<Group>();	
	}
	
	public String getCourseName(){
		return courseName;
	}
	
	public String getCourseID() {
		return courseId;
	}
	
	public List<Group> getGroups() {
		return groups;
	}
}
