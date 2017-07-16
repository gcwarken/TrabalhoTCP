package courseAPI;

import courseAPI.api.*;
//import courseAPI.domain.*;
//import java.util.List;


public class CourseAPI {

	public static void main(String[] args) throws Exception {
				
//	    if(args.length == 0)
//	    {
//	        System.out.println("Para usar a API corretamente digite: java courseAPI \"inputFile\"");
//	        System.exit(0);
//	    }
//		String inputFileName = args[0];
		
		printLogo();
		
		String inputFileName = "DemandasRecursos.xml";
		//String inputFileName = "test.xml";

		DataBase db = new DataBase();
				
		Leitor l = new Leitor(inputFileName, db);
		l.getFileInfo();
	
		
		Alocador alocador = new Alocador(db);
		alocador.alocar();

		Escritor e = new Escritor("salasAlocadas", db);
		e.createXml();
//		e.createXls();
	}
	
	private static void printLogo() {
		System.out.println("                                     .@@@@@@." +
			                "\n                                    ;#@@@@@@." +
			                "\n                                    @@@@@@@@." +                       
			                "\n                                   #@@@@@@@@," +                      
			                "\n        @@@@@  @@:    :@@@@@@+     @@@@@#``"+                        
			                "\n        @@@@@  @@@#  @@@@@@@@@@    @@@@@," +
			                "\n        @@@@@  @@@#:@@@@@@@@@@@@   @@@@@,"+
			                "\n        @@@@@  @@@@@@@@@@@@@@@@@`  @@@@@@@@@`" +                      
			                "\n        @@@@@  @@@@@@#    @@@@@@#  @@@@@@@@@`" +                      
			                "\n        @@@@@  @@@@@@      @@@@@@  @@@@@@@@@`" +                      
			                "\n        @@@@@  @@@@@.      #@@@@@  @@@@@@###`" +                      
			                "\n        @@@@@  @@@@@       :@@@@@  @@@@@." +                          
			                "\n        @@@@@  @@@@@       .@@@@@  @@@@@." +                          
			                "\n        @@@@@  @@@@@       .#@@@@  @@@@@." +                          
			                "\n        @@@@@  @@@@@       .@@@@@  @@@@@." +                          
			                "\n        @@@@@  @@@@@" +      
			                "\n  ___   @@@@@  @@@@@        Universidade Federal do Rio Grande do Sul" +
			                "\n *****  @@@@@  @@@@@        Instituto de Informática1" +
			                "\n******* @@@@@  @@@@@        INF01120 Técnicas de Construção de Programas" +
			                "\n******* @@@@@  @@@@@        Trabalho Prático​ | Alocador de salas de aula" +
			                "\n *****  @@@@@  @@@@@        Professora​ Erika Cota" + 
			                "\n  \"\"\"   @@@@@  @@@@#        Alunos​ Gabriel Costa Warken e Rodrigo Oliveira\n");
	}
}
