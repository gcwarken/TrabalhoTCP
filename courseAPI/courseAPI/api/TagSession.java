package courseAPI.api;

import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.write.Label;
import jxl.write.Number;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import courseAPI.DataBase;
import courseAPI.domain.Group;
import courseAPI.domain.Session;

public class TagSession implements Tag {

	public static void fillDataBase(NodeList nl, DataBase db) {

	}
	
	public Element getElement(Object o, Document doc) {
		Session sObj = (Session) o;
		
		Element session = doc.createElement("Session");
		Attr room_id = doc.createAttribute("room_id");
		Attr duration = doc.createAttribute("duration");
		//Attr building_id = doc.createAttribute("building_id");
		Attr weekday = doc.createAttribute("weekday");
		Attr start_time = doc.createAttribute("start_time");
		room_id.setValue(sObj.getSessionRoom().getId().toString());
		duration.setValue(sObj.getSessionDuration().toString());
		//building_id.setValue(sObj.getSessionRoom().)
		weekday.setValue(sObj.getWeekday().toString());
		start_time.setValue(sObj.getStartTime().toString());
		session.setAttributeNode(room_id);
		session.setAttributeNode(duration);
		//session.setAttributeNode(building_id);
		session.setAttributeNode(weekday);
		session.setAttributeNode(start_time);
		
		return session;
	}
	
	public List<Cell> getXlsCells(Object o, int colInitial, int rowInitial) {
		Session session = (Session) o;
		List<Cell> cells = new ArrayList<Cell>();
		
		Number roomId = new Number(colInitial++, rowInitial, session.getSessionRoom().getId());
		cells.add(roomId);
		Number duration = new Number(colInitial++, rowInitial, session.getSessionDuration());
		cells.add(duration);
		//faltou building ID
		Number weekday = new Number(colInitial++, rowInitial, session.getWeekday());
		cells.add(weekday);
		Number startTime = new Number(colInitial++, rowInitial, session.getStartTime());
		cells.add(startTime);
	
		return cells;
	}
}
