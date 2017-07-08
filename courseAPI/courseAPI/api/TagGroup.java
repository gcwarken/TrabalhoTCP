package courseAPI.api;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import courseAPI.DataBase;

import courseAPI.DataBase;
import courseAPI.domain.Course;
import courseAPI.domain.Group;

public class TagGroup implements Tag {

	public static void fillDataBase(Document d, DataBase db) {

	}
	
	public Element getElement(Object o, Document doc)
	{
		Group groupObj = (Group) o;
		
		Element group = doc.createElement("Group");
		Attr numStudents = doc.createAttribute("number_of_students");
		Attr teacher = doc.createAttribute("teacher");
		Attr id = doc.createAttribute("id");
		numStudents.setValue(groupObj.getNumStudents().toString());
		teacher.setValue(groupObj.getGroupTeacher());
		id.setValue(groupObj.getGroupId());
		group.setAttributeNode(teacher);
		group.setAttributeNode(id);
		group.setAttributeNode(numStudents);
		int i;
		Tag tag = new TagSession();
		for(i = 0; i<groupObj.getGroupSessions().size(); i++)
		{
			Element session = tag.getElement(groupObj.getGroupSessions().get(i), doc);
			group.appendChild(session);		
		}
		
		return group;
	}

}
