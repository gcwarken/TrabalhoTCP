package courseAPI.api;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import courseAPI.DataBase;

public interface Tag {
	
	public static void fillDataBase(Document d, DataBase db);
	public Element getElement(Object o, Document doc);
	
}
