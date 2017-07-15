package courseAPI.api;

import java.util.List;

import org.w3c.dom.*;
import jxl.*;

public interface Tag {
	
	public static List<Object> fillDataBase(NodeList nl) {}
	public Element getElement(Object o, Document doc);
	public List<Cell> getXlsCells(Object o, int colInitial, int rowInitial);
	
}
