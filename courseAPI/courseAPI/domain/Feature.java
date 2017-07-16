package courseAPI.domain;

public class Feature {
	private Integer id;
	private String name;
	
	public Feature(int _id, String _name) {
		id = _id;
		name = _name;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
}