package courseAPI.api;

import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.write.Label;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import courseAPI.domain.Course;
import courseAPI.domain.Group;

public class TagCourse implements Tag {
	
	public static List<Course> createObjectList(NodeList nl) {
		//	<course name='AGENTES AUTÔNOMOS E SISTEMAS MULTIAGENTES' id='INF05019'>
		//		<group number_of_students='30' teacher='ANA LUCIA CETERTICH BAZZAN, RAFAEL HEITOR BORDINI' id='U'>
		//			<session room_id='' duration='240' building_id='' weekday='5' start_time='13:30'/>
		//		</group>
		//	</course>
		
		List<Course> courses = new ArrayList<Course>();
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element eCourse =  (Element) nl.item(i);
				
				String courseId = eCourse.getAttribute("id");
				String courseName = eCourse.getAttribute("name");
								
				// add rooms
			    NodeList nlGroups = (nl.item(i)).getChildNodes();
			    List<Group> groups = TagGroup.createObjectList(nlGroups);
			    
			    Course c = new Course(courseName, courseId, groups);
				courses.add(c);
				
				//System.out.println("Added course " + courseName);
			}
		}
		
		return courses;
	}
	
	public Element createNewElements(Object o, Document doc){
	
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
			group = tag.createNewElements(courseObj.getGroups().get(i), doc);
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
	

}
