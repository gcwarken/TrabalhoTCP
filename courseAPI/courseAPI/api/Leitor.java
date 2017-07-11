package courseAPI.api;

import courseAPI.DataBase;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;


public class Leitor {
	
	private String inputFileName;
	private File inputFile;
	private DataBase db;
	
	public Leitor(String _inputFileName, DataBase _db) {
		inputFileName = _inputFileName;
		db = _db;
		inputFile = new File(inputFileName);	
	}
		
	public void getFileInfo() throws ParserConfigurationException {
		Document doc = null;
		String extension = inputFileName.substring(inputFileName.lastIndexOf(".") + 1, inputFileName.length()); 
		
		if (extension.equals("xml")) {
			doc = readXml();
		}
		else System.out.println("Invalid input file");
		
		callTags(doc);
		
		//quickTest(doc);
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
	    
	    //quickTest(doc); 
	    return doc;
	}
	
	private void callTags(Document doc) {
		// add Features
		NodeList nlFeatures = doc.getElementsByTagName("feature");
		TagFeature.fillDataBase(nlFeatures, this.db);
		
		// add Buildings
		NodeList nlBuildings = doc.getElementsByTagName("building");
		TagBuilding.fillDataBase(nlBuildings, this.db);
		
		// add Courses
		NodeList nlCourses = doc.getElementsByTagName("course");
		TagCourse.fillDataBase(nlCourses, this.db);
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	// this won't really be used, but it was the simplest way to understand how to navigate the freaking DOM //
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unused")
	private void quickTest(Document doc) {
		System.out.println("Root element: " + doc.getDocumentElement().getNodeName()); // allocation
		
		NodeList nCourses = doc.getElementsByTagName("course");

		for (int i = 0; i < nCourses.getLength(); i++) {
			if (nCourses.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Node nCourse = nCourses.item(i);
				System.out.println("\nCurrent Element: " + nCourse.getNodeName());
			
			    Element eCourse = (Element) nCourse;
			
			    System.out.println("Course name: " + eCourse .getAttribute("name"));
			    System.out.println("Course id: " + eCourse.getAttribute("id"));
			    
			    NodeList nGroups = nCourse.getChildNodes();
			    
			    for (int j = 0; j < nGroups.getLength(); j++) {
				    if (nGroups.item(j).getNodeType() == Node.ELEMENT_NODE) {
				        Element eGroup = (Element) nGroups.item(j);
				        System.out.println("\tGroup id: " + eGroup.getAttribute("id"));
				        System.out.println("\t\tGroup " + eGroup.getAttribute("id") + " teacher: " + eGroup.getAttribute("teacher"));
				        System.out.println("\t\tGroup " + eGroup.getAttribute("id") + " number of students: " + eGroup.getAttribute("number_of_students"));
				    }
			    }
			}
		}
	}

}
