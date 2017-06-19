package courseAPI.domain;

import java.util.ArrayList;
import java.util.List;

public class Building {
	
	private int id;
	private List<Room> rooms;
	
	public Building(int _id) {
		this.id = _id;
		this.rooms = new ArrayList<>();
	}
	
	public int getId() {
		return id;
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
