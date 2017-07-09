package courseAPI.api;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import jxl.*;

import courseAPI.DataBase;

public interface Tag {
	
	public static void fillDataBase(Document d, DataBase db);
	public Element getElement(Object o, Document doc);
	public List<Cell> getXlsCells(Object o, int colInitial, int rowInitial);
	
}
