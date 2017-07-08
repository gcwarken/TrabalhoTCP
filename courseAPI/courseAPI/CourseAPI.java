package courseAPI;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.*;

import courseAPI.api.*; 
import courseAPI.domain.*;

public class CourseAPI {

	public static void main(String[] args) throws Exception {
		/*
//	    if(args.length == 0)
//	    {
//	        System.out.println("Para usar a API corretamente digite: java courseAPI inputFile");
//	        System.exit(0);
//	    }
//		String inputFileName = args[0];
		
		//String inputFileName = "DemandasRecursos.xml";
		String inputFileName = "test.xml";
		DataBase db = new DataBase();
				
		Leitor l = new Leitor(inputFileName);
		Document dom = l.getFileInfo();
		
		NodeList nList = dom.getElementsByTagName("string");
		Node nNode = nList.item(0);
		Element eElement = (Element) nNode;
		
		System.out.println(dom.getDocumentElement().getNodeName());
		
		System.out.println(eElement.getElementsByTagName("string").item(1));
		
		
//		TagBuilding.fillDataBase(dom, db);*/
		
		
		
		//creating a data base filled
		DataBase db = new DataBase();
		
		//adding features		
		Feature f1 = new Feature(1,"computador");
		Feature f2 = new Feature(2,"projetor");
		db.addFeature(f1);
		db.addFeature(f2);
		
		//adding Building
		List<Room> rooms = new ArrayList<Room>();
		List<Feature> rAFeatures = new ArrayList<Feature>();
		rAFeatures.add(f1);
		Room rA = new Room(202, 40, rAFeatures);
		List<Feature> rBFeatures = new ArrayList<Feature>();
		rBFeatures.add(f2);
		Room rB = new Room(204, 35, rBFeatures);
		rooms.add(rA);
		rooms.add(rB);
		Building b1 = new Building(43425, rooms);
		db.addBuilding(b1);
		
		//adding course
		Session s1 = new Session(rA, 120, 8, 2);
		Session s2 = new Session(rA, 120, 8, 4);
		Session s3 = new Session(rB, 120, 8, 3);
		Session s4 = new Session(rB, 120, 8, 5);
		List<Session> sessionsGroupA = new ArrayList<Session>();
		List<Session> sessionsGroupB = new ArrayList<Session>();
		sessionsGroupA.add(s1);
		sessionsGroupA.add(s2);
		sessionsGroupB.add(s3);
		sessionsGroupB.add(s4);
		Group g1 = new Group("A", "jose", 20, sessionsGroupA);
		Group g2 = new Group("B", "maria", 20, sessionsGroupB);
		List<Group> groups = new ArrayList<Group>();
		groups.add(g1);
		groups.add(g2);
		Course c1 = new Course("calculo1", "MAT123", groups);
		db.addCourse(c1);
		
		Escritor e = new Escritor("saida", db);
		e.createXml();
		System.out.println("falous");
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
