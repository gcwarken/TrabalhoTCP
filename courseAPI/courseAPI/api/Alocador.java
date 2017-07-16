package courseAPI.api;

import java.util.ArrayList;
import java.util.List;
import courseAPI.DataBase;
import courseAPI.domain.*;

public class Alocador {
	
	private DataBase apiDB;
	private List<Course> courses;
	private List<Building> buildings;
	
	public Alocador(DataBase db) {
		this.apiDB = db;
		this.courses = apiDB.getCourses();
		this.buildings = apiDB.getBuildings();
	}
	
	public void alocar() {
		this.alocaComRoomRequirement();
		this.alocaComFeatureRequirement();
	}
	

	private boolean matchSessionAndRoom(Session s, List<Room> rList) {
		boolean roomFound = false;
		int i, j, k; 
		i = j = k = 0;

		// run through buildings of the list
		while (!roomFound && i < this.buildings.size()) { 
			// run through rooms of building(i)
			while (!roomFound && j < this.buildings.get(i).getRooms().size()) {
				String roomId = this.buildings.get(i).getRooms().get(j).getId();
				String roomFeatures = this.buildings.get(i).getRooms().get(j).getFeatures();
					
				// if it is the required room OR if there is no required room
				if (roomId.equals(s.getRoomRequired()) || s.getRoomRequired().equals("")) {
					// if the room has the required features (always true if no features required)
					if (roomFeatures.indexOf(s.getFeaturesRequired()) >= 0){
						// if room is available at the session's scheduled day and time
						if (this.checkRoomSchedule(s, this.buildings.get(i).getRooms().get(j))) {
							Room selectedRoom = this.buildings.get(i).getRooms().get(j);
							
							int hours = s.getSessionDuration() / 60;
							int time = s.getStartTime();
							for (k = 0; k < hours; k++) {
								selectedRoom.setAvailability(s.getWeekday(), time, false);
								time = time + 60;
							}
							
							s.setSessionRoom(selectedRoom);
						}
					}
				}
				j++;	
			}
			i++;
		}
		return roomFound;
	}
	
	private boolean checkRoomSchedule(Session s, Room r) {
		boolean available = true;
		int hours = s.getSessionDuration();
		int time = s.getStartTime();
		int i = 0;
		
		while (hours > 0) {
			if (r.getAvailability(s.getWeekday(), time))
				return false;
			time = time + 60;
			hours = hours - 60;
		}
		return available;
	}
	
	private void alocaComRoomRequirement() {
		int i, j, k;		
		for (i = 0; i < this.buildings.size(); i++) {
			for (j = 0; j < (this.buildings.get(i)).getRooms().size(); j++) {
				
			}
		}
	}
	
	private void alocaComFeatureRequirement() {
		
	}
	
}
