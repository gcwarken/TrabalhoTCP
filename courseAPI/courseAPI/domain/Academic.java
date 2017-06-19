package courseAPI.domain;

public abstract class Academic {
	private int id;
	private String name;
	
	public Academic(int _id, String _name) {
		this.id = _id;
		this.name = _name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
}
