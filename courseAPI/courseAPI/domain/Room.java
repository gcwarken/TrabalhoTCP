package courseAPI.domain;

import java.util.ArrayList;
import java.util.List;

public class Room {

	private String id;
	private List<Feature> features;
	private Integer capacity;
	private boolean available = true;
	
	public Room(String _id, int _capacity) {
		this.id = _id;
		this.features = new ArrayList<Feature>();
		this.capacity = _capacity;
	}
	
	public Room(String _id, int _capacity, List<Feature> f) {
		this.id = _id;
		this.features = new ArrayList<Feature>();
		this.features.addAll(f);
		this.capacity = _capacity;
	}
	
	public String getId() {
		return id;
	}
	
	public Integer getCapacity() {
		return capacity;
	}

	public void updateAvailability(boolean b) {
		available = b;
	}
	
	public boolean getAvailability() {
		return available;
	}
	
	public List<Feature> getFeatures(){
		return features;
	}

}

/*
room:
	id
	feature_ids
	number_of_places
	available_for_allocation (true/false)
*/