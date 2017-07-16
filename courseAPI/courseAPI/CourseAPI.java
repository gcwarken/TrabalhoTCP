package courseAPI;

import java.util.ArrayList;
import java.util.List;

import courseAPI.api.AdaptDomain;
import courseAPI.api.Escritor;
import courseAPI.api.Leitor;
import courseAPI.api.Programa;
import courseAPI.domain.Building;
import courseAPI.domain.Course;
import courseAPI.domain.Feature;
import courseAPI.domain.Room;
import courseAPI.domain.Session; 
import courseAPI.DataBase; 

public class CourseAPI {

	public static void main(String[] args) throws Exception {

//		if(args.length == 0)
//	    {
//	        System.out.println("Para usar a API corretamente digite: java courseAPI \"inputFile\"");
//	        System.exit(0);
//	    }
//		String inputFileName = args[0];
		
		String inputFileName = "DemandasRecursos.xml";
		//String inputFileName = "test.xml";

		DataBase db = new DataBase();
				
		Leitor l = new Leitor(inputFileName, db);
		l.getFileInfo();
		
//		Alocador alocador = new Alocador(db);
//		alocador.alocar();
				
		
		AdaptDomain a = new AdaptDomain(db);
		List<Session> s = new ArrayList<Session>();
		s = a.adaptIn();
/*		for(int i = 0; i<s.size(); i++)
		{
			System.out.println(s.get(i).getSessionGroup().getGroupCourse().getCourseName() + " " + s.get(i).getSessionGroup().getGroupId());
		}
*/
		
		Programa p = new Programa((ArrayList<Session>)s, (ArrayList<Building>)db.getBuildings());
		p.aloca();
		
		a.adaptOut(p.getCadeiras());
		
		
		
		
		
//		Escritor e = new Escritor("saida343", db);
//		e.createXml();
//		e.createXls();
	
		System.out.println("\nfalous");
	}
}
