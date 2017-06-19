package courseAPI.domain;

public class Session {

	private Room sessionRoom;
	private int sessionDuration;
	private int	startTime;
	private int weekday;
	
	public Session(Room room, int duration, int time, int day) {
		this.sessionRoom = room;
		this.sessionDuration = duration;
		this.startTime = time;
		this.weekday = day;
	}
	
	public Room getSessionRoom() {
		return sessionRoom;
	}
	
	public int getSessionDuration() {
		return sessionDuration;
	}
	
	public int getStartTime() {
		return startTime;
	}
	
	public int getWeekday() {
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
