package courseAPI.domain;

import java.util.ArrayList;
import java.util.List;

public class Building {
	
	private String id;
	private List<Room> rooms;
	
	public Building(String _id) {
		this.id = _id;
		this.rooms = new ArrayList<Room>();
	}
	
	public Building(String _id, List<Room> r) {
		this.id = _id;
		this.rooms = new ArrayList<Room>();
		this.rooms.addAll(r);
	}
	
	
	public String getId() {
		return id;
	}
	
	public List<Room> getRooms(){
		return rooms;
	}
}

/*
building:
	id
	rooms:
		id
		feature_ids
		number_of_places
		available_for_allocation (true/false)
*/        
