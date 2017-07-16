package courseAPI.domain;

public class Session {

	private Room sessionRoom;
	private Group sessionGroup;
	private String sessionBuildingId;
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
	
	public Session(int duration, int time, int day, String features, String roomReq) {
		this.sessionDuration = duration;
		this.startTime = time;
		this.weekday = day;
		this.featuresRequired = features;
		this.roomRequired = roomReq;
	}
	
	public Room getSessionRoom() {
		return sessionRoom;
	}
	
	public Group getSessionGroup() {
		return sessionGroup;
	}
	
	public void setSessionRoom(Room r) {
		this.sessionRoom = r;
	}
	
	public void setSessionGroup(Group g) {
		this.sessionGroup = g;
	}
	
	public String getSessionBuildingId() {
		return this.sessionBuildingId;
	}
	
	public void setSessionBuildingId(String bId) {
		this.sessionBuildingId = bId;
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
