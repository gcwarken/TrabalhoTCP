package courseAPI.api;

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
		//this.alocaComFeatureRequirement();
		//this.alocaSessionsRestantes();
	}
	

	private boolean matchSessionAndRoom(Session s) {
		boolean roomFound = false;
		int i, j, k; 
		i = j = k = 0;

		// loop through buildings of the list
		while (!roomFound && i < this.buildings.size()) { 
			// loop through rooms of building(i)
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
							s.setSessionBuildingId(this.buildings.get(i).getId());
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
		int time = s.getStartTime();
		int hours = s.getSessionDuration();
		
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
		int batata = 0;
		
		// loop through courses
		for (i = 0; i < this.courses.size(); i++) {
			// loop through groups
			for (j = 0; j < this.courses.get(i).getGroups().size(); j++) {
				// loop through sessions
				for (k = 0; k < this.courses.get(i).getGroups().get(j).getGroupSessions().size(); k++) {
					
					Session currentSession = this.courses.get(i).getGroups().get(j).getGroupSessions().get(k);
					if (currentSession.checkRoomRequirement()) {
						try {
							this.matchSessionAndRoom(currentSession);
							batata++;
						} catch (ArrayIndexOutOfBoundsException e) {
							System.out.println("broker session weekday  : " + Integer.toString(currentSession.getWeekday()) + "\nbroker session startTime: " + Integer.toString(currentSession.getStartTime()));
							throw e;
						}
					} 
				}
			}
		}
		System.out.println(Integer.toString(batata) + " sessions com room requirement");
	}
	
	private void alocaComFeatureRequirement() {
		int i, j, k;		

		// loop through courses
		for (i = 0; i < this.courses.size(); i++) {
			// loop through groups
			for (j = 0; j < this.courses.get(i).getGroups().size(); j++) {
				// loop through sessions
				for (k = 0; k < this.courses.get(i).getGroups().get(j).getGroupSessions().size(); k++) {
					
					Session currentSession = this.courses.get(i).getGroups().get(j).getGroupSessions().get(k);
					if (currentSession.checkFeatureRequirement() && currentSession.getSessionRoom() == null) {
						try {
							this.matchSessionAndRoom(currentSession);
							System.out.println("batata com features");
						} catch (ArrayIndexOutOfBoundsException e) {
							System.out.println("batata doce");
						}
					}
				}
			}
		}
	}
	
	private void alocaSessionsRestantes() {
		int i, j, k;		

		// loop through courses
		for (i = 0; i < this.courses.size(); i++) {
			// loop through groups
			for (j = 0; j < this.courses.get(i).getGroups().size(); j++) {
				// loop through sessions
				for (k = 0; k < this.courses.get(i).getGroups().get(j).getGroupSessions().size(); k++) {
					
					Session currentSession = this.courses.get(i).getGroups().get(j).getGroupSessions().get(k);
					if (currentSession.getSessionRoom() == null) {
						try {
							this.matchSessionAndRoom(currentSession);
							System.out.println("batata");
						} catch (ArrayIndexOutOfBoundsException e) {
							System.out.println("batata doce");
						}
					}
				}
			}
		}
	}
}
