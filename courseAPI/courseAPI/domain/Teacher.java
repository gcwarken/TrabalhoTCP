package courseAPI.domain;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Academic {
	private List<Group> groups;
	
	public Teacher(int _id, String _name) {
		super(_id, _name);
		this.groups = new ArrayList<>();
	}
	
}
