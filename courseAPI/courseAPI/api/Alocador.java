package courseAPI.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import courseAPI.DataBase;
import courseAPI.domain.*;

public class Alocador {
	
	private DataBase apiDB;
	
	public Alocador(DataBase db) {
		this.apiDB = db;
	}
	
	public void alocar() {
		List<Course> courses = apiDB.getCourses();
		List<Building> buildings = apiDB.getBuildings();
		
		this.sortBySessionStartTime(courses);
		
	}
	
	private void sortBySessionStartTime(List<Course> courses) {
		
	}
	
	//getRoomWithFeatures(f : List<Feature>, r : List<Room>) : List<Room>
	private Room getRoomWithFeatures(Feature f, List<Room> rList) {
		Room selectedRoom;
		/**
		 * @TODO
		 * Implement this
		 */
		return selectedRoom;
	}
	
	
	
}
