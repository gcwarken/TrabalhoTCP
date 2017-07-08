package courseAPI.domain;

import java.util.ArrayList;
import java.util.List;

public class Group {

	private String groupId;
	private String groupTeacher;
	private Integer numStudents;
	private List<Session> groupSessions;
	
	public Group(String id, String teacher, int students, List<Session> sessions) {
		this.groupId = id;
		this.groupTeacher = teacher;
		this.numStudents = students;
		this.groupSessions = new ArrayList<Session>();
		this.groupSessions.addAll(sessions);
	}
	
	public String getGroupId() {
		return groupId;
	}

	public String getGroupTeacher() {
		return groupTeacher;
	}
	
	public Integer getNumStudents() {
		return numStudents;
	}
	
	public List<Session> getGroupSessions(){
		return groupSessions;
	}
}
