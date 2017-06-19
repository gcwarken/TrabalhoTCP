package courseAPI.domain;

import java.util.ArrayList;
import java.util.List;

public class Room {

	private int id;
	private List<Feature> features;
	private int capacity;
	private boolean available = true;
	
	public Room(int _id, int _capacity) {
		this.id = _id;
		this.features = new ArrayList<>();
		this.capacity = _capacity;
	}
	
	public Room(int _id, int _capacity, Feature[] features) {
		this.id = _id;
		this.features = new ArrayList<>();
		this.capacity = _capacity;
		
		
	}
	
	public int getId() {
		return id;
	}
	
	public int getCapacity() {
		return capacity;
	}

	public void updateAvailability(boolean b) {
		available = b;
	}
	
	public boolean getAvailability() {
		return available;
	}
	
	
	
	
}

/*
room:
	id
	feature_ids
	number_of_places
	available_for_allocation (true/false)
*/