package courseAPI;

import java.util.ArrayList;
import java.util.List;
import courseAPI.domain.*;

public class DataBase {

	//private List<Teacher> teachers;
	private List<Building> buildings;
	private List<Course> courses;
	private List<Feature> features;
	private List<Group> groups;
	//private List<Room> rooms;
	//private List<Session> sessions;
	
	public DataBase() {
	//	this.teachers = new ArrayList<>();
		buildings = new ArrayList<Building>();
		courses = new ArrayList<Course>();
		features = new ArrayList<Feature>();
	//	this.groups = new ArrayList<>();
	//	this.rooms = new ArrayList<>();
	//	this.sessions = new ArrayList<>();
	}

/*	
	public int addTeacher(Teacher t) {
		this.teachers.add(t);
		return 0;
	}
*/

	public int addBuilding(Building b) {
		this.buildings.add(b);
		return 0;
	}
	
	public int addBuildingList(List<Building> b) {
		this.buildings.addAll(b);
		return 0;
	}

	public int addCourse(Course c) {
		this.courses.add(c);
		return 0;
	}

	public int addCourseList(List<Course> c) {
		this.courses.addAll(c);
		return 0;
	}
	
	
	public int addFeature(Feature f) {
		this.features.add(f);
		return 0;
	}
	
	public int addFeatureList(List<Feature> f) {
		this.features.addAll(f);
		return 0;
	}
	
	public int addGroup(Group g) {
		this.groups.add(g);
		return 0;
	}
	
	public List<Building> getBuildings(){
		return buildings;
	}
	
	public List<Feature> getFeatures(){
		return features;
	}
	
	public List<Course> getCourses(){
		return courses;
	}
	
}
