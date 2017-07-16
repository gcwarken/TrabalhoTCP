package courseAPI.api;

import java.util.Arrays;
import java.util.List;
import courseAPI.DataBase;
import courseAPI.domain.*;

public class Alocador {
	
	private DataBase apiDB;
	private List<Course> courses;
	private List<Building> buildings;
	private int sessionCounter;
	
	public Alocador(DataBase db) {
		this.apiDB = db;
		this.courses = apiDB.getCourses();
		this.buildings = apiDB.getBuildings();
		this.sessionCounter = 0;
	}
	
	public void alocar() {
		//this.printBuildings(this.buildings);
		
		this.alocaComRoomRequirement();
		this.alocaComFeatureRequirement();
		this.alocaSessionsRestantes();
		
		System.out.println(Integer.toString(this.sessionCounter) + " seções alocadas com sucesso.");
	}
	
	@SuppressWarnings("unused")
	private void printBuildings(List<Building> b) {
		int i, j;
		for (i = 0; i < b.size(); i++) {
			System.out.println("Building ID: " + b.get(i).getId());
			for (j = 0; j < b.get(i).getRooms().size(); j++)
				System.out.println("\tRoom ID: " + b.get(i).getRooms().get(j).getId() + " | Room features: " + b.get(i).getRooms().get(j).getFeatures());
		}
		
		System.out.println("Num of buildings: " + Integer.toString(this.buildings.size()));
		System.out.println("\n");
	}

	private boolean matchSessionAndRoom(Session s) {
		boolean roomFound = false;
		int i, j, k; 
		i = j = k = 0;
		// loop through buildings of the list
		while ( (!roomFound) && (i < this.buildings.size()) ) { 
			j = 0;
			// loop through rooms of building(i)
			while ( (!roomFound) && (j < this.buildings.get(i).getRooms().size()) ) {
				Room currentRoom = this.buildings.get(i).getRooms().get(j);
				String roomId = currentRoom.getId();
				
				// if it is the required room OR if there is no required room
				if (s.getRoomRequired().equals(roomId) || s.getRoomRequired().isEmpty()) {
					// if the room has the required features (always true if no features required)
					if (this.gotTheFeature(s, currentRoom)){
						// if room is available at the session's scheduled day and time
						if (this.checkRoomSchedule(s, currentRoom)) {
							///System.out.println("\tI got the time you want!");
							Room selectedRoom = this.buildings.get(i).getRooms().get(j);
							
							int hours = s.getSessionDuration() / 60;
							int time = s.getStartTime();
							for (k = 0; k < hours; k++) {
								selectedRoom.setAvailability(s.getWeekday(), time, false);
								time = time + 100;
							}
							
							s.setSessionRoom(selectedRoom);
							s.setSessionBuildingId(this.buildings.get(i).getId());
							roomFound = true;
						}
					}
				}
				j++;	
			}
			i++;
		}
		return roomFound;
	}
	
	private boolean gotTheFeature(Session s, Room r) {
		if (s.getFeaturesRequired().isEmpty()) return true;
		
		List<String> roomFeatures = Arrays.asList(r.getFeatures().split(","));
		List<String> requiredFeatures = Arrays.asList(s.getFeaturesRequired().split(","));
		int i = 0;
		
		while (i < requiredFeatures.size()) {
			if (!roomFeatures.contains(requiredFeatures.get(i)))
				return false;
			i++;
		} 
		
		return true;
	}
	
	private boolean checkRoomSchedule(Session s, Room r) {
		boolean available = true;
		int time = s.getStartTime();
		int hours = s.getSessionDuration();
		
		while (hours > 0) {
			if (!r.getAvailability(s.getWeekday(), time))
				return false;
			time = time + 100;
			hours = hours - 60;
		}
		return available;
	}
	
	private void alocaComRoomRequirement() {
		int i, j, k;		
		
		// loop through courses
		for (i = 0; i < this.courses.size(); i++) {
			// loop through groups
			for (j = 0; j < this.courses.get(i).getGroups().size(); j++) {
				// loop through sessions
				for (k = 0; k < this.courses.get(i).getGroups().get(j).getGroupSessions().size(); k++) {
					
					Session currentSession = this.courses.get(i).getGroups().get(j).getGroupSessions().get(k);
					if (currentSession.checkRoomRequirement()) {
						try {
							if (this.matchSessionAndRoom(currentSession))
								this.sessionCounter++;
							else 
								System.out.println("Session not alocated! Room required = " + currentSession.getRoomRequired());
						} catch (ArrayIndexOutOfBoundsException e) {
							System.out.println("Broker session weekday  : " + Integer.toString(currentSession.getWeekday()) + "\nbroker session startTime: " + Integer.toString(currentSession.getStartTime()));
							throw e;
						}
					} 
				}
			}
		}
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
							if (this.matchSessionAndRoom(currentSession))
								this.sessionCounter++;
							else 
								System.out.println("Session not alocated! Features required = " + currentSession.getFeaturesRequired());
						} catch (ArrayIndexOutOfBoundsException e) {
							System.out.println("broker session weekday  : " + Integer.toString(currentSession.getWeekday()) + "\nbroker session startTime: " + Integer.toString(currentSession.getStartTime()));
							throw e;
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
							if (this.matchSessionAndRoom(currentSession))
								this.sessionCounter++;
						} catch (ArrayIndexOutOfBoundsException e) {
							System.out.println("broker session weekday  : " + Integer.toString(currentSession.getWeekday()) + "\nbroker session startTime: " + Integer.toString(currentSession.getStartTime()));
							throw e;
						}
					}
				}
			}
		}
	}
}
