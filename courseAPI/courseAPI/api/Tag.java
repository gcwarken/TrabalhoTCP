package courseAPI.api;

import java.util.List;

import org.w3c.dom.*;
import jxl.*;

import courseAPI.DataBase;

public interface Tag {
	
	public static void fillDataBase(NodeList nl, DataBase db) {}
	public Element getElement(Object o, Document doc);
	public List<Cell> getXlsCells(Object o, int colInitial, int rowInitial);
	
}
