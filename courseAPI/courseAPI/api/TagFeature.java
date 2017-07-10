package courseAPI.api;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import courseAPI.DataBase;
import courseAPI.domain.Feature;
import jxl.Cell;

public class TagFeature implements Tag {

	public static void fillDataBase(Document d, DataBase db) {
	}
	
	public Element getElement(Object o, Document doc)
	{
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
