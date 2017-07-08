package courseAPI.domain;

public class Feature {
	private Integer id;
	private String name;
	
	public Feature(int _id, String _name) {
		this.id = _id;
		this.name = _name;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
}

/*
feature:
	name
	id
	hidden (true/false)
*/