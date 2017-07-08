package courseAPI.api;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import courseAPI.DataBase;
import courseAPI.domain.Building;
import courseAPI.domain.Course;

public final class TagBuilding implements Tag {


	public static void fillDataBase(Document d, DataBase db) {
		
		NodeList buildings = d.getElementsByTagName("building");
		for (int i = 0; i < buildings.getLength(); i++) {
			Element currBuilding = (Element) buildings.item(i);
			System.out.println(currBuilding.getAttribute("id"));
			
			
		}
	
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
		for(i = 0; i<bObj.getRooms().size(); i++)
		{
			room = tag.getElement(bObj.getRooms().get(i), doc);
			building.appendChild(room);	
		}
		return building;
		}	
		
		
//		//Get all the transaction elements and then loop over them
//	    NodeList transaction = doc.getElementsByTagName("transaction");
//	    for(int j = 0; j < transaction.getLength(); j++) {
//	        //Traverse down the transaction node till we get the billing info
//	        NodeList details = ((Element)transaction.item(j)).getElementsByTagName("details");
//	        NodeList account = ((Element)details.item(0)).getElementsByTagName("account");
//	        NodeList billinginfo = ((Element)account.item(0)).getElementsByTagName("billing_info");
//
//	        System.out.println("===Billing Info===");
//	        System.out.println("Type: "+((Element)billinginfo.item(0)).getAttributes("type"));
//
//	        //Get all children nodes from billing info
//	        NodeList billingChildren = billinginfo.item(0).getChildNodes();
//
//	        for(int i = 0; i < billingChildren.getLength(); i++) {
//	            Node current = billingChildren.item(i);
//	            //Only want stuff from ELEMENT nodes
//	            if(current.getNodeType() == Node.ELEMENT_NODE) {
//	                System.out.println(current.getNodeName()+": "+current.getTextContent());
//	            }
//	        }
//	    }	
//		
//		
//	}
	
}