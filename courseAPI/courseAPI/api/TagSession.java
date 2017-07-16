package courseAPI.api;

import java.util.ArrayList;
import java.util.List;

import courseAPI.domain.Session;

import jxl.Cell;
import jxl.write.Number;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class TagSession implements Tag {

	public static List<Session> createObjectList(NodeList nl) {
		//	<session duration='' requires_building_id='' features_ids='1' requires_room_id='ANFA (Anfiteatro Azul)' weekday='1' start_time='13:30'/>
		List<Session> sessions = new ArrayList<Session>();
		for (int i = 0; i < nl.getLength(); i++) {
		    if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
		    	String sessionReqFeatures = "";
		    	String sessionReqRoom = "";
		    	
		    	Element eSession = (Element) nl.item(i);
		    	int sessionDuration = 0;
		    	int defaultSessionDuration = 120;
		        try {
		        	sessionDuration = Integer.parseInt(eSession.getAttribute("duration"));
		        } catch (NumberFormatException e) {
		        	sessionDuration = defaultSessionDuration;
		        }
		        int sessionStartTime = Integer.parseInt((eSession.getAttribute("start_time")).replace(":", ""));
		        int sessionWeekday = Integer.parseInt(eSession.getAttribute("weekday"));
		        sessionReqFeatures.concat(eSession.getAttribute("features_ids"));
		        sessionReqRoom.concat(eSession.getAttribute("requires_room_id"));
		        		        
		        Session s = new Session(sessionDuration, sessionStartTime, sessionWeekday, sessionReqFeatures, sessionReqRoom);
		        sessions.add(s);
		    }
	    }
		return sessions;
	}
	
	public Element createNewElements(Object o, Document doc) {
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
		
		Number roomId = new Number(colInitial++, rowInitial, Integer.parseInt(session.getSessionRoom().getId()));
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
