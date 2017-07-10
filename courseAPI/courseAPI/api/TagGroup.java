package courseAPI.api;

import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.write.Label;
import jxl.write.Number;

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
	
	public Element getElement(Object o, Document doc) {
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
		for(i = 0; i<groupObj.getGroupSessions().size(); i++) {
			Element session = tag.getElement(groupObj.getGroupSessions().get(i), doc);
			group.appendChild(session);		
		}
		
		return group;
	}

	public List<Cell> getXlsCells(Object o, int colInitial, int rowInitial) {
		Group group = (Group) o;
		List<Cell> cells = new ArrayList<Cell>();
		
		Tag tag = new TagSession();
		int i;
		for(i = 0; i<group.getGroupSessions().size(); i++) {
			Number numStudents = new Number(colInitial, rowInitial+i, group.getNumStudents());
			cells.add(numStudents);
			Label label = new Label(colInitial + 1, rowInitial+i, group.getGroupTeacher());
			cells.add(label);
			label = new Label(colInitial + 2, rowInitial+i, group.getGroupId());
			cells.add(label);
			cells.addAll(tag.getXlsCells(group.getGroupSessions().get(i), colInitial + 3, rowInitial+i));
		}		
		return cells;
	}
	
}
