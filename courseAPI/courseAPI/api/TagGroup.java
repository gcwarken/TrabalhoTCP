package courseAPI.api;

import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.write.Label;
import jxl.write.Number;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import courseAPI.DataBase;

import courseAPI.DataBase;
import courseAPI.domain.Course;
import courseAPI.domain.Group;
import courseAPI.domain.Room;
import courseAPI.domain.Session;

public class TagGroup implements Tag {

	public static List<Group> fillDataBase(NodeList nl, DataBase db) {
		//	<group number_of_students='30' teacher='ANA LUCIA CETERTICH BAZZAN, RAFAEL HEITOR BORDINI' id='U'>
		//		<session room_id='' duration='240' building_id='' weekday='5' start_time='13:30'/>
		//	</group>		
		
		List<Group> groups = new ArrayList<Group>();
		for (int i = 0; i < nl.getLength(); i++) {
		    if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {

		    	Element eGroup = (Element) nl.item(i);
		        String groupId = eGroup.getAttribute("id");
		        String groupTeacher = eGroup.getAttribute("teacher");
		        int groupNumStudents = Integer.parseInt(eGroup.getAttribute("number_of_students"));
		        
		        List<Session> sessions = new ArrayList<Session>();
		        /**
		         * @TODO
		         * implement add sessions to groups
		         */
		        Group g = new Group(groupId, groupTeacher, groupNumStudents, sessions);
		        groups.add(g); 
		        
		        System.out.println("\tAdded group " + groupId + ", teacher " + groupTeacher + ", num students = " + eGroup.getAttribute("number_of_students"));
		    }
	    }
		return groups;
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
