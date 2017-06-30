package courseAPI.api;

import java.util.ArrayList;
import java.util.List;
import courseAPI.DataBase;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;


public class Leitor {
	
	private String inputFilePath;
	//private DataBase apiDB;
		
	public Document getFileInfo(String fileName) {
		Document doc = null;
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		
		if (extension.equals("xml")) {
			readXml(fileName);
		}
		
		return doc;
	}
	
	private void openFile() {
	} 
	
	private void readXml(String xml) {
		
	}
	

}
