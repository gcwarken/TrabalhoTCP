package courseAPI.api;

import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.write.Label;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import courseAPI.DataBase;
import courseAPI.domain.Course;

public class TagCourse implements Tag {
	
	public Element getElement(Object o, Document doc){
	
		Course courseObj = (Course) o;
		
		Element course = doc.createElement("course");
		Attr name = doc.createAttribute("name");
		Attr id = doc.createAttribute("id");
		name.setValue(courseObj.getCourseName());
		id.setValue(courseObj.getCourseID());
		course.setAttributeNode(name);
		course.setAttributeNode(id);
		int i;
		Tag tag = new TagGroup();
		Element group = doc.createElement("group");
		for(i = 0; i<courseObj.getGroups().size(); i++) {
			group = tag.getElement(courseObj.getGroups().get(i), doc);
			course.appendChild(group);		
		}
		 
		return course;
	}
	
	public List<Cell> getXlsCells(Object o, int colInitial, int rowInitial) {
		Course course = (Course) o;
		List<Cell> cells = new ArrayList<Cell>();
		
		Tag tag = new TagGroup();
		int i;
		for(i = 0; i<course.getGroups().size(); i++) {
			Label label = new Label(colInitial,rowInitial+i, course.getCourseName());
			cells.add(label);
			label = new Label(colInitial + 1, rowInitial+i, course.getCourseID());
			cells.add(label);
			cells.addAll(tag.getXlsCells(course.getGroups().get(i), colInitial + 2, rowInitial+i));
		}		
		return cells;
	}
	
	public static void fillDataBase(Document d, DataBase db) {

	}

}
