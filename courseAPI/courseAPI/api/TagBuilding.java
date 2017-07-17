package courseAPI.api;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import courseAPI.domain.Building;
import courseAPI.domain.Room;
import jxl.Cell;

public final class TagBuilding implements Tag {


	public static List<Building> createObjectList(NodeList nl) {
		//	<building id='43412(65)'>
		//		<room id='ANFV (Anfiteatro Vermelho)' feature_ids='3, 5, 8' number_of_places='76'></room>
		//		<room available_for_allocation='false' id='214' feature_ids='4, 5' number_of_places='15'></room>
		//		<room available_for_allocation='false' id='216' feature_ids='4, 5' number_of_places='15'></room>
		//	</building>
		
		List<Building> buildings = new ArrayList<Building>();
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element eBuilding =  (Element) nl.item(i);
				String buildingId = eBuilding.getAttribute("id");
								
				// add rooms
			    NodeList nlRooms = (nl.item(i)).getChildNodes();
			    List<Room>rooms = TagRoom.createObjectList(nlRooms);
			    
			    Building b = new Building(buildingId, rooms);
				buildings.add(b);
				//System.out.println("Added building " + eBuilding.getAttribute("id"));
			}
		}
		return buildings;
	}
	
	public static Building createObject(List<Cell> c){
		
		List<Room> r = new ArrayList<Room>();
		r.add(TagRoom.createObject(c));
		Building b = new Building(c.get(0).getContents(), r);
		return b;
	}
	
	public Element createNewElements(Object o, Document doc) {
			
		Building bObj = (Building) o;
		
		Element building = doc.createElement("Building");
		Attr id = doc.createAttribute("id");
		id.setValue(bObj.getId().toString());
		building.setAttributeNode(id);
		
		int i;
		Tag tag = new TagRoom();
		Element room = doc.createElement("room");
		for(i = 0; i<bObj.getRooms().size(); i++){
			room = tag.createNewElements(bObj.getRooms().get(i), doc);
			building.appendChild(room);	
		}
		return building;
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
