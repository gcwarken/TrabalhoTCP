package courseAPI.api;

import java.util.ArrayList;
import java.util.List;

import courseAPI.DataBase;
import courseAPI.domain.*;

public class AdaptDomain {
	private DataBase apiDB;

	public AdaptDomain(DataBase db) {
		apiDB = db;
	}
	
	public List<Session> adaptIn(){
		
		List<Session> s = new ArrayList<Session>();
		int i,j,k;
		for(i=0; i<apiDB.getCourses().size(); i++)
		{
			for(j=0; j<apiDB.getCourses().get(i).getGroups().size(); j++)
			{
				for(k=0; k<apiDB.getCourses().get(i).getGroups().get(j).getGroupSessions().size(); k++)
				{
					apiDB.getCourses().get(i).getGroups().get(j).getGroupSessions().get(k).setSessionGroup(apiDB.getCourses().get(i).getGroups().get(j));
					s.add(apiDB.getCourses().get(i).getGroups().get(j).getGroupSessions().get(k));
				}
				apiDB.getCourses().get(i).getGroups().get(j).setGroupCourse(apiDB.getCourses().get(i));
			}
		}
		return s;
	}
	
	public List<Course> adaptOut(List<Course> courses){
		List<Course> c = courses;
		int i,j,k;
		for(i=0; i<apiDB.getCourses().size(); i++)
		{
			for(j=0; j<apiDB.getCourses().get(i).getGroups().size(); j++)
			{
				for(k=0; k<apiDB.getCourses().get(i).getGroups().get(j).getGroupSessions().size(); k++)
				{
					System.out.println(c.get(i).getGroups().get(j).getGroupTeacher() + "    " + c.get(i).getGroups().get(j).getGroupSessions().get(k).getSessionRoom().getId());
					//apiDB.getCourses().get(i).getGroups().get(j).getGroupSessions().get(k).setSessionGroup(apiDB.getCourses().get(i).getGroups().get(j));
					//s.add(apiDB.getCourses().get(i).getGroups().get(j).getGroupSessions().get(k));
				}
				//apiDB.getCourses().get(i).getGroups().get(j).setGroupCourse(apiDB.getCourses().get(i));
			}
		}
		return c;
		
	}
}
