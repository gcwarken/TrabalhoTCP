package courseAPI.api;

import java.util.List;

import org.w3c.dom.*;
import jxl.*;

public interface Tag {
	
	public static List<Object> createObjectList(NodeList nl) {}
	public Element createElement(Object o, Document doc);
	public List<Cell> getXlsCells(Object o, int colInitial, int rowInitial);
	
}
