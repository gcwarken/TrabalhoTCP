package courseAPI.api;

import java.util.List;

import org.w3c.dom.*;
import jxl.*;

public interface Tag {
	
<<<<<<< HEAD
	public static List<Object> createObjectList(NodeList nl) {}
	public static Object createObject(List<Cell> c) {}
=======
	public static List<Object> createObjectList(NodeList nl);
>>>>>>> 4db5075b1912eb195c74c0e4f20760d400567e34
	public Element createNewElements(Object o, Document doc);
	public List<Cell> getXlsCells(Object o, int colInitial, int rowInitial);
	
}
