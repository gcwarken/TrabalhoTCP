package courseAPI;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
		dom.getElementsByTagName("string");
		
		//TagBuilding.fillDataBase(dom, db);
	}

}
