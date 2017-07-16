package courseAPI;

import courseAPI.api.*;
import courseAPI.domain.*;
import java.util.List;


public class CourseAPI {

	public static void main(String[] args) throws Exception {

//	    if(args.length == 0)
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
	
		
		Alocador alocador = new Alocador(db);
		alocador.alocar();

//		Escritor e = new Escritor("saida", db);
//		e.createXml();
//		e.createXls();
		
		System.out.println("\nfalous");
	}
}
