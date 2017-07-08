package courseAPI.domain;

import java.util.ArrayList;
import java.util.List;

public class Building {
	
	private Integer id;
	private List<Room> rooms;
	
	public Building(int _id) {
		this.id = _id;
		this.rooms = new ArrayList<Room>();
	}
	
	public Building(int _id, List<Room> r) {
		this.id = _id;
		this.rooms = new ArrayList<Room>();
		this.rooms.addAll(r);
	}
	
	
	public Integer getId() {
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
