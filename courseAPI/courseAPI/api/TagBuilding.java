package courseAPI.api;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import courseAPI.DataBase;

public final class TagBuilding implements Tag {
	
	private TagBuilding() throws Exception {
		// Tag classes are utility only and should not be instantiated
	}

	public static void fillDataBase(Document d, DataBase db) {
		
		NodeList buildings = d.getElementsByTagName("building");
		for (int i = 0; i < buildings.getLength(); i++) {
			Element currBuilding = (Element) buildings.item(i);
			System.out.println(currBuilding.getAttribute("id"));
			
			
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
	}
	
}