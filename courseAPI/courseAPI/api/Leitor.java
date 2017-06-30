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
		this.inputFileName = _inputFileName;
		this.inputFile = new File(this.inputFileName);
		
	}
		
	public Document getFileInfo() throws ParserConfigurationException {
		Document doc = null;
		String extension = inputFileName.substring(inputFileName.lastIndexOf(".") + 1, inputFileName.length());
		
		if (extension.equals("xml")) {
			readXml(doc);
		}
		else System.out.println("Invalid input file");
			
		return doc;
	}

	private void readXml(Document doc) throws ParserConfigurationException {
	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    
	    try {
			doc = dBuilder.parse(inputFile);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
	}
}
