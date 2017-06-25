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
		this.buildings = new ArrayList<>();
		this.courses = new ArrayList<>();
		this.features = new ArrayList<>();
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

	public int addCourse(Course c) {
		this.courses.add(c);
		return 0;
	}

	public int addFeature(Feature f) {
		this.features.add(f);
		return 0;
	}
	
	public int addGroup(Group g) {
		this.groups.add(g);
		return 0;
	}
}
