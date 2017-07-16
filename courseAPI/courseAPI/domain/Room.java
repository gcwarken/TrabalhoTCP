package courseAPI.domain;

public class Room {

	private String id;
	private String features;
	private Integer capacity;
	private RoomAvailability availability = new RoomAvailability();
	
	public Room(String _id, int _capacity, String _features) {
		this.id = _id;
		this.features = _features;
		this.capacity = _capacity;
	}
	
	public String getId() {
		return id;
	}
	
	public Integer getCapacity() {
		return capacity;
	}
	
	public String getFeatures(){
		return features;
	}

	public void notAvailable() {
		this.availability.notAvailable();
	}
	
	public boolean getAvailability(int d, int h) {
		return this.availability.getAvailability(d, h); 
	}
	
	public void setAvailability(int d, int h, boolean b) {
		this.availability.setAvailability(d, h, b);
	}
}