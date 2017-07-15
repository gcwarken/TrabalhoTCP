package courseAPI.domain;

public class Session {

	private Room sessionRoom;
	private Integer sessionDuration;
	private Integer	startTime;
	private Integer weekday;
	private String featuresRequired = "";
	private String roomRequired = "";
	
	public Session(Room room, int duration, int time, int day, String features, String roomReq) {
		sessionRoom = room;
		sessionDuration = duration;
		startTime = time;
		weekday = day;
		featuresRequired = features;
		roomRequired = roomReq;
	}
	
	public Session(int duration, int time, int day) {
		this.sessionDuration = duration;
		this.startTime = time;
		this.weekday = day;
	}
	
	public Room getSessionRoom() {
		return sessionRoom;
	}
	
	public Integer getSessionDuration() {
		return sessionDuration;
	}
	
	public Integer getStartTime() {
		return startTime;
	}
	
	public Integer getWeekday() {
		return weekday;
	}
	
	public String getFeaturesRequired() {
		return featuresRequired;
	}
	
	public String getRoomRequired() {
		return roomRequired;
	}
	
	public boolean checkFeatureRequirement() {
		return (this.featuresRequired != "");
	}
	
	public boolean checkRoomRequirement() {
		return (this.roomRequired != ""); 
	}
	
}

/*
session:
    room_id					* dá pra pegar do room
    duration				* ok
	building_id				* dá pra pegar do room
    weekday					* ok
    start_time				* ok
    requires_room_id		?
    requires_building_id	?
    feature_ids				?
*/
