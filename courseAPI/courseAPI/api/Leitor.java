package courseAPI.api;

import courseAPI.DataBase;
import courseAPI.domain.*;
import jxl.Sheet;
import jxl.Workbook;
import jxl.Cell;
import jxl.read.biff.BiffException;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



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
			callTags(doc);
		}
		else if(extension.equals("xls")){
			readXls();
		}
		else System.out.println("Invalid input file");
		
		
	}

	/////////////////////////////////////////
	// parse input xml into a DOM document //
	/////////////////////////////////////////
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
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	// Call Tag methods in a coordinated way to fill database with information provided by the input xml //
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	private void callTags(Document doc) {
		// add Features
		NodeList nlFeatures = doc.getElementsByTagName("feature");
		List<Feature> f = TagFeature.createObjectList(nlFeatures);
		this.db.addFeatureList(f);
		
		// add Buildings
		NodeList nlBuildings = doc.getElementsByTagName("building");
		List<Building> b = TagBuilding.createObjectList(nlBuildings);
		this.db.addBuildingList(b);
		
		// add Courses
		NodeList nlCourses = doc.getElementsByTagName("course");
		List<Course> c = TagCourse.createObjectList(nlCourses);
		this.db.addCourseList(c);
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
	
	private void readXls()
	{
		Workbook workbook = null;
        try {

            workbook = Workbook.getWorkbook(new File(inputFileName));
            Sheet demandas = workbook.getSheet("Demandas");
            Sheet features = workbook.getSheet("Features");
            Sheet buildings = workbook.getSheet("Espaço Físico");
            
            int i;
            for(i=0; i<features.getRows()-1; i++)
            {
            	db.addFeature((Feature)TagFeature.createObject(new ArrayList<Cell>(Arrays.asList(features.getRow(i+1)))));
            }
            
            for(i=0; i<buildings.getRows()-1; i++)
            {
            	Building b = TagBuilding.createObject(new ArrayList<Cell>(Arrays.asList(buildings.getRow(i+1))));
        
        		int j;
        		boolean found = false;
            	for(j=0; j<db.getBuildings().size(); j++){
            		if(b.getId().equals(db.getBuildings().get(j).getId())){
            			found = true;
            			break;
            		}
            	}
            	
            	if(found){
            		db.getBuildings().get(j).getRooms().addAll(b.getRooms());
            	}
            	else {
        			db.addBuilding(b);          		
            	}
            }
            
            boolean foundC = false, foundG = false;
            int courseIndex = 0, groupIndex = 0;
            Course c;
            System.out.println(demandas.getRows());
            for(i=0; i<demandas.getRows()-1; i++)
            {
            	c = TagCourse.createObject(new ArrayList<Cell>(Arrays.asList(demandas.getRow(i+1))));
            	
	            int j;
	            for(j=0; j<db.getCourses().size(); j++)
	            {
	            	if(db.getCourses().get(j).getCourseID().equals(c.getCourseID()) && 
	            			db.getCourses().get(j).getCourseName().equals(c.getCourseID()))
	            	{
	            		foundC = true;
	            		courseIndex = j;
	            		int k;
		            	for(k=0; k<db.getCourses().get(j).getGroups().size(); k++)
		            	{
		            		if(db.getCourses().get(j).getGroups().get(k).getGroupId().equals(c.getGroups().get(0).getGroupId()))
							{
			            		foundG = true;
			            		groupIndex = k;
			            		break;
			           		}
		            	}
	            		break;
	            	}
            	}
	            Session sec;
	            System.out.println(foundC + "  " + foundG);
	            if(foundC && foundG)
	            {
	            	sec = c.getGroups().get(0).getGroupSessions().get(0);
	            	db.getCourses().get(courseIndex).getGroups().get(groupIndex).getGroupSessions().add(sec);
	            }
	            else if(foundC && !foundG)
	            {
	            	db.getCourses().get(courseIndex).getGroups().add(c.getGroups().get(0));
	            }
	            else
	            {
	            	db.addCourse(c);
	            }
	            
	            courseIndex = 0;
	            groupIndex = 0;
	            foundC =false;
	            foundG = false;
	      
            }
            
        

            
            
        } catch (IOException e) {
         e.printStackTrace();
		} catch (BiffException e) {
            e.printStackTrace();
        } finally {

            if (workbook != null) {
                workbook.close();
            }

        }
	}

}
