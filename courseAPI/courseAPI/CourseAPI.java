package courseAPI;

import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import courseAPI.api.*; 

public class CourseAPI {

	public static void main(String[] args) throws ParserConfigurationException {
		
		Leitor l = new Leitor("test.xml");
		Document dom = l.getFileInfo();
	}

}
