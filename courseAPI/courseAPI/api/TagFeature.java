package courseAPI.api;



import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import courseAPI.domain.Feature;
import jxl.Cell;

public class TagFeature implements Tag {

	public static List<Feature> fillDataBase(NodeList nl) {
		//	    <features>
		//			<feature name='Laboratório de ensino' id='1'/>
		//			<feature name='Laboratório de ensino de hardware' id='2'/>
		//			<feature name='Som e microfone' id='3' hidden='true'/>
		//      </features>		

		List<Feature> features = new ArrayList<Feature>();
		
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element eFeature =  (Element) nl.item(i);
				String featureName = eFeature.getAttribute("name");
				int featureId = Integer.parseInt(eFeature.getAttribute("id"));
				Feature f = new Feature(featureId, featureName);
				features.add(f);
				//System.out.println("Added feature " + eFeature.getAttribute("id") + ", " + featureName);
			}
		}
		return features;
	}
	
	public Element getElement(Object o, Document doc) {
		Feature featureObj = (Feature) o;
		
		Element feature = doc.createElement("feature");
		Attr id = doc.createAttribute("id");
		Attr name = doc.createAttribute("name");
		id.setValue(featureObj.getId().toString());
		name.setValue(featureObj.getName());
		feature.setAttributeNode(id);
		feature.setAttributeNode(name);

		return feature;
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
