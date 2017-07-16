package courseAPI.api;

import courseAPI.DataBase;
import courseAPI.domain.*;

public class AdaptDomain {
	private DataBase apiDB;

	public AdaptDomain(Database db) {
		apiDB = db;
	}
	
	public List<Session> adapt(List<Course> c){
		
		List<Session> s = new Arraylist();
		int i,j,k;
		for(i=0; i<apiDB.getCourses().size(); i++)
		{
			for(j=0; j<apiDB.getCourses().get(i).getGroups().size(); j++)
			{
				for(k=0; k<apiDB.getCourses().get(i).getGroups().get(j).getGroupSessions().size(); k++)
				{
					apiDB.getCourses().get(i).getGroups().get(j).getGroupSessions().get(k).setSessionsGroup(apiDB.getCourses().get(i).getGroups().get(j));
					s.add(apiDB.getCourses().get(i).getGroups().get(j).getGroupSessions().get(k))
				}
				apiDB.getCourses().get(i).getGroups().get(j).setGroupCourse(apiDB.getCourses().get(i));
			}
		}
		return s;
	}
}
