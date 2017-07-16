package courseAPI;

import java.util.ArrayList;
import java.util.List;

import courseAPI.domain.Building;
import courseAPI.domain.Course;
import courseAPI.domain.Feature;

public class DataBase {
	private List<Building> buildings;
	private List<Course> courses;
	private List<Feature> features;
	
	public DataBase() {
		buildings = new ArrayList<Building>();
		courses = new ArrayList<Course>();
		features = new ArrayList<Feature>();
	}

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
	
	public List<Building> getBuildings(){
		return this.buildings;
	}
	
	public List<Feature> getFeatures(){
		return this.features;
	}
	
	public List<Course> getCourses(){
		return this.courses;
	}
}
