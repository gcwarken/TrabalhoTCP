package courseAPI.domain;

import java.util.ArrayList;
import java.util.List;

public class Group {

	private int groupId;
	private Teacher groupTeacher;
	private int numStudents;
	private List<Session> groupSessions;
	
	public Group(int id, Teacher teacher, int students) {
		groupId = id;
		groupTeacher = teacher;
		numStudents = students;
		groupSessions = new ArrayList<>();
	}
	
	public int getGroupId() {
		return groupId;
	}

	public Teacher getGroupTeacher() {
		return groupTeacher;
	}
	
	public int getNumStudents() {
		return numStudents;
	}
}
