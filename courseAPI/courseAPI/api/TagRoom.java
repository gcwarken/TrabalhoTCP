package courseAPI.api;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
		        Room r = new Room(roomId, roomCapacity);
		        rooms.add(r); 
		        /**
		         * @TODO
		         * implement add features to rooms
		         */
		        
		        //System.out.println("\tAdded room " + roomId + ", capacity: " + eRoom.getAttribute("number_of_places"));
		    }
	    }
		return rooms;
	}
	
	public Element createNewElements(Object o, Document doc) {
		
		Room rObj = (Room) o;
		
		Element room = doc.createElement("room");
		Attr id = doc.createAttribute("id");
		Attr featureIds = doc.createAttribute("feature_ids");
		Attr numberOfPlaces = doc.createAttribute("nuber_of_spaces");
		id.setValue(rObj.getId().toString());
		int i;
		String features = null;
		for(i = 0; i<rObj.getFeatures().size(); i++) {
			features = features + ", " +  rObj.getFeatures().get(i);
		}
		featureIds.setValue(features);
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
