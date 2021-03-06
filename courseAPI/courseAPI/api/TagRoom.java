package courseAPI.api;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import courseAPI.domain.Building;
import courseAPI.domain.Room;
import jxl.Cell;

public class TagRoom implements Tag {

	public static List<Room> createObjectList(NodeList nl) {
		// <room id='ANFV (Anfiteatro Vermelho)' feature_ids='3, 5, 8' number_of_places='76'></room>
		List<Room> rooms = new ArrayList<Room>();
	    for (int i = 0; i < nl.getLength(); i++) {
		    if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
		        Element eRoom = (Element) nl.item(i);
		        String roomId = eRoom.getAttribute("id");
		        int roomCapacity = Integer.parseInt(eRoom.getAttribute("number_of_places"));
		        String roomFeatures = (eRoom.getAttribute("feature_ids")).replace(" ", "");
		        
		        Room r = new Room(roomId, roomCapacity, roomFeatures);
		        if (eRoom.getAttribute("available_for_allocation").equals("false"))
		        	r.notAvailable();  
		        
		        // System.out.println("\tAdded room with id: " + r.getId() + " and features: " + r.getFeatures());
		        rooms.add(r);
		    }
	    }
		return rooms;
	}
	
	public static Room createObject(List<Cell> c){
		Room r = new Room(c.get(1).getContents(),Integer.valueOf(c.get(3).getContents()),c.get(2).getContents());
		return r;
	}
	
	public Element createNewElements(Object o, Document doc) {
		
		Room rObj = (Room) o;
		
		Element room = doc.createElement("room");
		Attr id = doc.createAttribute("id");
		Attr featureIds = doc.createAttribute("feature_ids");
		Attr numberOfPlaces = doc.createAttribute("nuber_of_spaces");
		id.setValue(rObj.getId().toString());
		featureIds.setValue(rObj.getFeatures());
		numberOfPlaces.setValue(rObj.getCapacity().toString());
		room.setAttributeNode(id);
		room.setAttributeNode(featureIds);
		room.setAttributeNode(numberOfPlaces);
		
		return room;
	}	

	
	public List<Cell> getXlsCells(Object o, int colInitial, int rowInitial) {
		List<Cell> cells = new ArrayList<Cell>();
		/**
		 * @TODO
		 * implement this 
		 */
		return cells;
	}
}
