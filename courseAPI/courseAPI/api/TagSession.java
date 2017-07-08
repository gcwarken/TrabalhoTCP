package courseAPI.api;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import courseAPI.DataBase;
import courseAPI.domain.Session;

public class TagSession implements Tag {

	public static void fillDataBase(Document d, DataBase db) {

	}
	
	public Element getElement(Object o, Document doc)
	{
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
}
