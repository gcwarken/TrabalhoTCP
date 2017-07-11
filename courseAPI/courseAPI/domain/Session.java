package courseAPI.domain;

public class Session {

	private Room sessionRoom;
	private Integer sessionDuration;
	private Integer	startTime;
	private Integer weekday;
	
	public Session(Room room, int duration, int time, int day) {
		this.sessionRoom = room;
		this.sessionDuration = duration;
		this.startTime = time;
		this.weekday = day;
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
