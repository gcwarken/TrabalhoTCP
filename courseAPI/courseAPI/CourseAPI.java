package courseAPI;

import org.w3c.dom.*;

import courseAPI.api.*; 

public class CourseAPI {

	public static void main(String[] args) throws Exception {
		
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
		
		
//		TagBuilding.fillDataBase(dom, db);
		
		
	}
}
