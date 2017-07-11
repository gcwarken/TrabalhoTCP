package courseAPI.api;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import courseAPI.DataBase;
import courseAPI.domain.Building;
import courseAPI.domain.Course;
import jxl.Cell;

public final class TagBuilding implements Tag {


	public static void fillDataBase(NodeList n, DataBase db) {
		
		
		
//		for (int i = 0; i < buildings.getLength(); i++) {
//			Element currBuilding = (Element) buildings.item(i);
//			System.out.println(currBuilding.getAttribute("id"));
//		}
	
	}
	
	public Element getElement(Object o, Document doc){
			
		Building bObj = (Building) o;
		
		Element building = doc.createElement("Building");
		Attr id = doc.createAttribute("id");
		id.setValue(bObj.getId().toString());
		building.setAttributeNode(id);
		
		int i;
		Tag tag = new TagRoom();
		Element room = doc.createElement("room");
		for(i = 0; i<bObj.getRooms().size(); i++){
			room = tag.getElement(bObj.getRooms().get(i), doc);
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