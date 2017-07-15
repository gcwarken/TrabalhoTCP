package courseAPI.api;

import java.util.ArrayList;
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
		
		
	}

}
