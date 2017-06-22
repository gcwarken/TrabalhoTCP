package courseAPI;

import java.util.ArrayList;
import java.util.List;
import courseAPI.domain.*;

public class DataBase {

	private List<Teacher> teachers;
	private List<Building> buildings;
	private List<Course> courses;
	private List<Feature> features;
	private List<Group> groups;
	private List<Room> rooms;
	private List<Session> sessions;
	
	public DataBase() {
		this.teachers = new ArrayList<>();
		this.buildings = new ArrayList<>();
		this.courses = new ArrayList<>();
		this.features = new ArrayList<>();
		this.groups = new ArrayList<>();
		this.rooms = new ArrayList<>();
		this.sessions = new ArrayList<>();
	}

	public int addTeacher(Teacher t) {
	}

	public int addBuilding(Building b) {
	}

	public int addCourse(Course c) {
	}

	public int addFeature(Feature f) {
	}

	public int addGroup(Group g) {
	}
	
	public int addRoom(Room r) {
	}

	public int addSession(Session s) {
	}
	
}
