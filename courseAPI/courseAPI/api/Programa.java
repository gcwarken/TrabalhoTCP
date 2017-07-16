package courseAPI.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Programa {
	ArrayList<Session> aulas;
	ArrayList<Building> predios;
	ArrayList<Course> cadeirasPronta;
	
	
	public Programa(ArrayList<Session> aulas , ArrayList<Building> predios)
	{
		this.aulas=aulas;
		this.predios=predios;
		cadeirasPronta = new ArrayList<Course>();
	}
	
	public ArrayList<Session> getAulas()
	{
		return aulas;
	}
	public ArrayList<Building> getPredios()
	{
		return predios;
	}
	
	
	public static void main (String[] args)
	{
		System.out.println("ola");
	}
	
	public ArrayList<Course> getCadeiras()
	{
		return cadeirasPronta;
	}
	public void aloca()
	{
		int i;
		boolean achou = false;
		boolean achouTurma = false;
		boolean achouCadeira = false;
		
		List<Session> aulasComRequisitos = new ArrayList<Session>();
		List<Session> aulasComReqroomid = new ArrayList<Session>();
		List<Session> aulasSemReq = new ArrayList<Session>();
		
		ArrayList<Course> cadeirasAux = new ArrayList<Course>();
		ArrayList<Turma> turmasAux = new ArrayList<Turma>();
		
		
		for(i=0;i<aulas.size();i++)
		{
			if(aulas.get(i).checkFeatureRequirement() != "")
				aulasComRequisitos.add(aulas.get(i));
		}
		
		for(i=0;i<aulas.size();i++)
		{
			if(aulas.get(i).checkRoomRequirement() != "")
			{
				aulasComReqroomid.add(aulas.get(i));
			}
		}
		
		
		
		for(i=0;i<aulas.size();i++)
		{
			if(aulas.get(i).checkFeatureRequirement() == "" && aulas.get(i).checkRoomRequirement() == "")
				aulasSemReq.add(aulas.get(i));
		}
		
//		for (int f=0; f<aulasSemReq.size(); f++)
//		{
//			System.out.println(aulasSemReq.get(f).getAlunos());
//		}
		
		//System.out.println("FIM");
		
		for(i=0;i<aulasComReqroomid.size();i++)
		{
			for(int j=0;j<predios.size();j++)
			{
//				System.out.println(j + "\n");
				//System.out.println(predios.get(j).getId());
				for (int x=0;x<predios.get(j).getRooms().size();x++)
				{
					if(aulasComReqroomid.get(i).getRoomRequired().equals(predios.get(j).getRooms().get(x).getId()))
					{	
//						System.out.println("OlaA");
						int diaSemana = aulasComReqroomid.get(i).getWeekday();
						int horario = aulasComReqroomid.get(i).getStartTime();
						int duracao = aulasComReqroomid.get(i).getSessionDuration();
						
						if(predios.get(j).getRooms().get(x).getAvailability(diaSemana, horario) && (aulasComReqroomid.get(i).getAlunos()<=predios.get(j).getRooms().get(x).getCapacity()));
						{
//							System.out.println(predios.get(j).getSalas().get(x).getId() + "\n");
//							System.out.println(aulasComReqroomid.get(i).room_id() + "\n");
							//System.out.println(predios.get(j).getSalas().get(x).getId() + " " + predios.get(j).getId());
							aulasComReqroomid.get(i).insereSala(predios.get(j).getRooms().get(x).getId());
							aulasComReqroomid.get(i).inserePredio(predios.get(j).getId());
							predios.get(j).getRooms().get(x).reservaSala(diaSemana, horario, duracao);
							achou=true;
							
//							x=predios.get(j).getSalas().size();
//							j=predios.size();
						}
					}
					if(achou)
						break;	
				}
				//System.out.println("sai");
				if(achou)
					break;
				
				//System.out.println("break");
				//System.out.println(j + "\n");
			}
			
			if(!achou)
				System.out.println("Erro ROOMiD");
			achou=false;
			Course cadeiraAuxiliar =  new Course();
			
			Turma turmaAuxiliar = new Turma();
			
			cadeiraAuxiliar.setCadeira(aulasComReqroomid.get(i).getTurma().getCourse().getNome(), aulasComReqroomid.get(i).getTurma().getCadeira().getidentificador());
			turmaAuxiliar.setId(aulasComReqroomid.get(i).getTurma().getid());
			
			for(int y=0;y<cadeirasAux.size();y++)
			{
				if(cadeiraAuxiliar.getidentificador().equals(cadeirasAux.get(y).getidentificador()))
				{
					for(int k=0;k<cadeirasAux.get(y).getTurmas().size();k++)
					{
						if(turmaAuxiliar.getid().equals(cadeirasAux.get(y).getTurmas().get(k).getid()))
						{
							cadeirasAux.get(y).getTurmas().get(k).addAula(aulasComReqroomid.get(i));
							achouTurma=true;
						}
							
					}
					if(!achouTurma)
					{
						turmaAuxiliar.addAula(aulasComReqroomid.get(i));
						turmasAux.add(turmaAuxiliar);
						cadeirasAux.get(y).addTurma(turmaAuxiliar);
						
					}
					achouTurma=false;
					achouCadeira=true;
				}
			}
			if(!achouCadeira)
			{
				turmaAuxiliar.addAula(aulasComReqroomid.get(i));
				turmasAux.add(turmaAuxiliar);
				cadeiraAuxiliar.addTurma(turmaAuxiliar);
				cadeirasAux.add(cadeiraAuxiliar);
			}
			achouCadeira=false;
		}
		for(i=0;i<aulasComRequisitos.size();i++)
		{
			for(int j=0;j<predios.size();j++)
			{
				//System.out.println(predios.get(j).getId());
				for (int x=0;x<predios.get(j).getRooms().size();x++)
				{
					if(predios.get(j).getRooms().get(x).dispo())
					{
						int diaSemana = aulasComRequisitos.get(i).getWeekday();
						int horario = aulasComRequisitos.get(i).getStartTime();
						int duracao = aulasComRequisitos.get(i).getSessionDuration();
//						if(predios.get(j).getSalas().get(x).verificaSala(diaSemana, horario,duracao))
//								System.out.println(aulasComRequisitos.get(i).getAlunos() + " " + predios.get(j).getSalas().get(x).getCapacity());
						if(predios.get(j).getRooms().get(x).verificaSala(diaSemana, horario,duracao))
						{
//							System.out.println("Achei sala");
//							System.out.println(predios.get(j).getSalas().get(x).getRecursos().length());
							switch(predios.get(j).getRooms().get(x).getRecursos().length())
							{
							case 1:
								
								//System.out.println(predios.get(j).getSalas().get(x).getRecursos().charAt(0) );
								if(predios.get(j).getRooms().get(x).getRecursos().charAt(0) == aulasComRequisitos.get(i).getRecursos().charAt(0))
								{
									
									
									aulasComRequisitos.get(i).insereSala(predios.get(j).getRooms().get(x).getId());
									aulasComRequisitos.get(i).inserePredio(predios.get(j).getId());
									predios.get(j).getRooms().get(x).reservaSala(diaSemana, horario, duracao);
									achou=true;
//									x=predios.get(j).getSalas().size();
//									j=predios.size();
									
								}
								break;
							case 4:
								if(predios.get(j).getRooms().get(x).getRecursos().charAt(0) == aulasComRequisitos.get(i).getRecursos().charAt(0))
								{
									 
									
									aulasComRequisitos.get(i).insereSala(predios.get(j).getRooms().get(x).getId());
									aulasComRequisitos.get(i).inserePredio(predios.get(j).getId());
									predios.get(j).getRooms().get(x).reservaSala(diaSemana, horario, duracao);
									achou=true;
//									x=predios.get(j).getSalas().size();
//									j=predios.size();
									
								}
								else if(predios.get(j).getRooms().get(x).getRecursos().charAt(3) == aulasComRequisitos.get(i).getRecursos().charAt(0))
								{
									
									
									aulasComRequisitos.get(i).insereSala(predios.get(j).getRooms().get(x).getId());
									aulasComRequisitos.get(i).inserePredio(predios.get(j).getId());
									predios.get(j).getRooms().get(x).reservaSala(diaSemana, horario, duracao);
//									x=predios.get(j).getSalas().size();
//									j=predios.size();
									achou=true;
									
								}
								break;
							case 7:
								if(predios.get(j).getRooms().get(x).getRecursos().charAt(0) == aulasComRequisitos.get(i).getRecursos().charAt(0))
								{
									 
									
									aulasComRequisitos.get(i).insereSala(predios.get(j).getRooms().get(x).getId());
									aulasComRequisitos.get(i).inserePredio(predios.get(j).getId());
									predios.get(j).getRooms().get(x).reservaSala(diaSemana, horario, duracao);
//									x=predios.get(j).getSalas().size();
//									j=predios.size();
									achou=true;
									
								}
								else if(predios.get(j).getRooms().get(x).getRecursos().charAt(3) == aulasComRequisitos.get(i).getRecursos().charAt(0))
								{
									 
									
									aulasComRequisitos.get(i).insereSala(predios.get(j).getRooms().get(x).getId());
									aulasComRequisitos.get(i).inserePredio(predios.get(j).getId());
									predios.get(j).getRooms().get(x).reservaSala(diaSemana, horario, duracao);
//									x=predios.get(j).getSalas().size();
//									j=predios.size();
									achou=true;
									
								}
								else if(predios.get(j).getRooms().get(x).getRecursos().charAt(6) == aulasComRequisitos.get(i).getRecursos().charAt(0))
								{
									 
									
									aulasComRequisitos.get(i).insereSala(predios.get(j).getRooms().get(x).getId());
									aulasComRequisitos.get(i).inserePredio(predios.get(j).getId());
									predios.get(j).getRooms().get(x).reservaSala(diaSemana, horario, duracao);
//									x=predios.get(j).getSalas().size();
//									j=predios.size();
									achou=true;
									
								}
								break;
							}
						}
					}
					if(achou)
						break;
				}
				if(achou)
					break;
			}
			if(!achou)
			{
				System.out.println("Erro comREQ");
				System.out.println(aulasComRequisitos.get(i).getTurma().getid());
				System.out.println(aulasComRequisitos.get(i).getTurma().getCadeira().getidentificador());
			}
			else
			{
				//System.out.println("CERTO comREQ");
				achou=false;
			}
			
			Course cadeiraAuxiliar =  new Course();
			
			Turma turmaAuxiliar = new Turma();
			
			cadeiraAuxiliar.setCadeira(aulasComRequisitos.get(i).getTurma().getCadeira().getNome(), aulasComRequisitos.get(i).getTurma().getCadeira().getidentificador());
			turmaAuxiliar.setId(aulasComRequisitos.get(i).getTurma().getid());
			
			for(int y=0;y<cadeirasAux.size();y++)
			{
				if(cadeiraAuxiliar.getidentificador().equals(cadeirasAux.get(y).getidentificador()))
				{
					for(int k=0;k<cadeirasAux.get(y).getTurmas().size();k++)
					{
						if(turmaAuxiliar.getid().equals(cadeirasAux.get(y).getTurmas().get(k).getid()))
						{
							cadeirasAux.get(y).getTurmas().get(k).addAula(aulasComRequisitos.get(i));
							achouTurma=true;
						}
							
					}
					if(!achouTurma)
					{
						turmaAuxiliar.addAula(aulasComRequisitos.get(i));
						turmasAux.add(turmaAuxiliar);
						cadeirasAux.get(y).addTurma(turmaAuxiliar);
						
					}
					achouTurma=false;
					achouCadeira=true;
				}
			}
			if(!achouCadeira)
			{
				turmaAuxiliar.addAula(aulasComRequisitos.get(i));
				turmasAux.add(turmaAuxiliar);
				cadeiraAuxiliar.addTurma(turmaAuxiliar);
				cadeirasAux.add(cadeiraAuxiliar);
			}
			achouCadeira=false;
		}
		
		
		//Collections.sort(aulasSemReq);
		//System.out.println(aulas.size());
//		for (int f=0; f<aulasSemReq.size(); f++)
//		{
//			System.out.println(aulasSemReq.get(f).getAlunos());
//		}
//		
//		System.out.println("FIMMMMM");
		
		
		
		Collections.sort(aulasSemReq);
		
//		for (int f=0; f<aulasSemReq.size(); f++)
//		{
//			System.out.println(aulasSemReq.get(f).getAlunos());
//		}
		
		for(i=0;i<aulasSemReq.size();i++)
		{
			for(int j=0;j<predios.size();j++)
			{
				for (int x=0;x<predios.get(j).getRooms().size();x++)
				{
					int diaSemana = aulasSemReq.get(i).getWeekday();
					int horario = aulasSemReq.get(i).getStartTime();
					int duracao = aulasSemReq.get(i).getSessionDuration();
					
					if(predios.get(j).getRooms().get(x).verificaSala(diaSemana, horario, duracao) && (aulasSemReq.get(i).getAlunos()<predios.get(j).getRooms().get(x).getCapacity()))
					{	
						//System.out.println(predios.get(j).getSalas().get(x).getId());
						aulasSemReq.get(i).insereSala(predios.get(j).getRooms().get(x).getId());
						aulasSemReq.get(i).inserePredio(predios.get(j).getId());
						predios.get(j).getRooms().get(x).reservaSala(diaSemana, horario, duracao);
//						x=predios.get(j).getSalas().size();
//						j=predios.size();
						achou=true;
					}
					if(achou)
						break;
				}
				if(achou)
					break;
			}
			if(!achou)
				System.out.println("Erro SEmREQ");
			
			achou=false;
			
			Course cadeiraAuxiliar =  new Course();
			
			Turma turmaAuxiliar = new Turma();
			
			cadeiraAuxiliar.setCadeira(aulasSemReq.get(i).getTurma().getCadeira().getNome(), aulasSemReq.get(i).getTurma().getCadeira().getidentificador());
			turmaAuxiliar.setId(aulasSemReq.get(i).getTurma().getid());
			achouTurma=false;
			achouCadeira=false;
			
			for(int y=0;y<cadeirasAux.size();y++)
			{
				if(cadeiraAuxiliar.getidentificador().equals(cadeirasAux.get(y).getidentificador()))
				{
					for(int k=0;k<cadeirasAux.get(y).getTurmas().size();k++)
					{
						if(turmaAuxiliar.getid().equals(cadeirasAux.get(y).getTurmas().get(k).getid()))
						{
							cadeirasAux.get(y).getTurmas().get(k).addAula(aulasSemReq.get(i));
							achouTurma=true;
						}
							
					}
					if(!achouTurma)
					{
						turmaAuxiliar.addAula(aulasSemReq.get(i));
						turmasAux.add(turmaAuxiliar);
						cadeirasAux.get(y).addTurma(turmaAuxiliar);
						
					}
					achouTurma=false;
					achouCadeira=true;
				}
			}
			if(!achouCadeira)
			{
				turmaAuxiliar.addAula(aulasSemReq.get(i));
				turmasAux.add(turmaAuxiliar);
				cadeiraAuxiliar.addTurma(turmaAuxiliar);
				cadeirasAux.add(cadeiraAuxiliar);
			}
			achouCadeira=false;
		}
		
		Collections.sort(cadeirasAux);
		
//		System.out.println(cadeirasAux.size());
//		
//		for(i=0;i<cadeirasAux.size();i++)
//		{
//			System.out.println(cadeirasAux.get(i).getNome());
//			for(int r=0;r<cadeirasAux.get(i).getTurmas().size();r++)
//			{
//				System.out.println(cadeirasAux.get(i).getTurmas().get(r).getid());
//				for(int z=0;z<cadeirasAux.get(i).getTurmas().get(r).getAulas().size();z++)
//				{
//					System.out.println(cadeirasAux.get(i).getTurmas().get(r).getAulas().get(z).getWeekday());
//				}
//			}
//		}
		
		this.cadeirasPronta=cadeirasAux;
		
		
		
//		for(i=0;i<aulasSemReq.size();i++)
//		{
//			if(aulasSemReq.get(i).getIdSala().equals(""))
//				System.out.println("ERRO");
//			else
//				System.out.println(aulasSemReq.get(i).getIdSala());
//		}
//		
//		System.out.println("FIM111");
//		
//		for(i=0;i<aulasComRequisitos.size();i++)
//		{
//			if(aulasComRequisitos.get(i).getIdSala().equals(""))
//				System.out.println("ERRO");
//			else
//				System.out.println(aulasComRequisitos.get(i).getIdSala());
//		}
//		
//		System.out.println("FIM222");
//		for(i=0;i<aulasComReqroomid.size();i++)
//		{
//			if(aulasComReqroomid.get(i).getIdSala().equals(""))
//				System.out.println("ERRO");
//			else
//				System.out.println(aulasComReqroomid.get(i).getIdSala());
//		}
		
		
		
	}
	
}
