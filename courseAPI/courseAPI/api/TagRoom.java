package courseAPI.api;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import courseAPI.DataBase;
import courseAPI.domain.Building;
import courseAPI.domain.Room;

public class TagRoom implements Tag {

	
	public static void fillDataBase(Document d, DataBase db) {

	}
	
	public Element getElement(Object o, Document doc){
		
		Room rObj = (Room) o;
		
		Element room = doc.createElement("room");
		Attr id = doc.createAttribute("id");
		Attr featureIds = doc.createAttribute("feature_ids");
		Attr numberOfPlaces = doc.createAttribute("nuber_of_spaces");
		id.setValue(rObj.getId().toString());
		int i;
		String features = null;
		for(i = 0; i<rObj.getFeatures().size(); i++)
		{
			features = features + ", " +  rObj.getFeatures().get(i);
		}
		featureIds.setValue(features);
		numberOfPlaces.setValue(rObj.getCapacity().toString());
		room.setAttributeNode(id);
		room.setAttributeNode(featureIds);
		room.setAttributeNode(numberOfPlaces);
		
		return room;
		}	

}
