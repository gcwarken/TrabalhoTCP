package courseAPI.api;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;


public class Leitor {
	
	private String inputFileName;
	private File inputFile;
	//private DataBase apiDB;
	
	public Leitor(String _inputFileName) {
		inputFileName = _inputFileName;
		inputFile = new File(inputFileName);	
	}
		
	public Document getFileInfo() throws ParserConfigurationException {
		Document doc = null;
		String extension = inputFileName.substring(inputFileName.lastIndexOf(".") + 1, inputFileName.length());
		
		if (extension.equals("xml")) {
			doc = readXml();
		}
		else System.out.println("Invalid input file");
			
		return doc;
	}

	private Document readXml() throws ParserConfigurationException {
	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = null;
	    try {
			doc = dBuilder.parse(this.inputFile);
			doc.getDocumentElement().normalize();
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
	    
	    return doc;
	}
}
