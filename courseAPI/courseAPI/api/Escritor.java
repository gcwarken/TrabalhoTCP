package courseAPI.api;

import java.util.ArrayList;
import java.util.List;
import courseAPI.DataBase;
import java.io.File;
import java.io.IOException;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import jxl.*;
import jxl.write.*; 
import jxl.write.biff.RowsExceededException;

public class Escritor{

	private String output;
	private DataBase apiDB;
	
	public Escritor(String out, DataBase db)
	{
		output = out;
		apiDB = db;
	}
	
	public void createXml() throws TransformerException 
	{
		try{
			//File outputFile = new File(this.output + ".xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			Document doc = dbBuilder.newDocument();
			
			//root element
			Element rootElement = doc.createElement("allocation");
			doc.appendChild(rootElement);
			
			//courses element
			Element courses = doc.createElement("courses");
			rootElement.appendChild(courses);
			//Feature element
			Element features = doc.createElement("features");
			rootElement.appendChild(features);
			//Building element
			Element buildings = doc.createElement("buildings");
			rootElement.appendChild(buildings);
			
			//fill courses
			int i;
			Tag tag = new TagCourse();
			Element course = doc.createElement("course");
			for(i = 0; i<apiDB.getCourses().size(); i++)
			{
				course = tag.createElement(apiDB.getCourses().get(i), doc);
				courses.appendChild(course);				
			}
			
			//fill features
			tag = new TagFeature();
			Element feature = doc.createElement("feature");
			for(i = 0; i<apiDB.getFeatures().size(); i++)
			{
				feature = tag.createElement(apiDB.getFeatures().get(i), doc);
				features.appendChild(feature);
			}
			
			//fill buildings
			tag = new TagBuilding();
			Element building = doc.createElement("building");
			for(i = 0; i<apiDB.getBuildings().size(); i++)
			{
				building = tag.createElement(apiDB.getBuildings().get(i), doc);
				buildings.appendChild(building);
			}
			
			
			
			//Write XML
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult Result = new StreamResult(new File(output + ".xml"));
			transformer.transform(source, Result);	
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createXls() {
		
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(new File(output + ".xls"));
			WritableSheet sheetCourse = workbook.createSheet("Demandas", 0);
			
			//Course name column, Course
			Label label = new Label(0,0,"name");
			sheetCourse.addCell(label);
			List<Cell> cells = new ArrayList<Cell>();
			Tag tag = new TagCourse();
			int i;
			for(i = 0; i<apiDB.getCourses().size(); i++)
			{
				cells = tag.getXlsCells(apiDB.getCourses().get(i),0,i+1);
			}
			for(i = 0; i<cells.size(); i++)
			{
				sheetCourse.addCell((WritableCell)cells.get(i));
			}
			
			
			workbook.write();
			workbook.close(); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
