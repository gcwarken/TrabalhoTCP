package courseAPI.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import courseAPI.domain.Building;
import courseAPI.domain.Course;
import courseAPI.domain.Group;
import courseAPI.domain.Room;
import courseAPI.domain.Session;

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
	
	
	/*public static void main (String[] args)
	{
		System.out.println("ola");
	}*/
	
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
		ArrayList<Group> turmasAux = new ArrayList<Group>();
		
		
		for(i=0;i<aulas.size();i++)
		{
			if(aulas.get(i).checkFeatureRequirement())
				aulasComRequisitos.add(aulas.get(i));
		}
		
		for(i=0;i<aulas.size();i++)
		{
			if(aulas.get(i).checkRoomRequirement())
			{
				aulasComReqroomid.add(aulas.get(i));
			}
		}
		
		
		
		for(i=0;i<aulas.size();i++)
		{
			if(!aulas.get(i).checkFeatureRequirement() && !aulas.get(i).checkRoomRequirement())
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
						
						if(predios.get(j).getRooms().get(x).getAvailability(diaSemana, horario) && (aulasComReqroomid.get(i).getSessionGroup().getNumStudents() <=predios.get(j).getRooms().get(x).getCapacity()));
						{
//							System.out.println(predios.get(j).getSalas().get(x).getId() + "\n");
//							System.out.println(aulasComReqroomid.get(i).room_id() + "\n");
							//System.out.println(predios.get(j).getSalas().get(x).getId() + " " + predios.get(j).getId());
							aulasComReqroomid.get(i).setSessionRoom(predios.get(j).getRooms().get(x));
							aulasComReqroomid.get(i).setSessionBuildingId(predios.get(j).getId());
							predios.get(j).getRooms().get(x).setAvailability(diaSemana, horario, false);
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
			Course cadeiraAuxiliar =  new Course(aulasComReqroomid.get(i).getSessionGroup().getGroupCourse().getCourseName(), aulasComReqroomid.get(i).getSessionGroup().getGroupCourse().getCourseID());
			
			Group turmaAuxiliar = new Group();
			
			
			turmaAuxiliar.setGroupId(aulasComReqroomid.get(i).getSessionGroup().getGroupId());
			
			for(int y=0;y<cadeirasAux.size();y++)
			{
				if(cadeiraAuxiliar.getCourseID().equals(cadeirasAux.get(y).getCourseID()))
				{
					for(int k=0;k<cadeirasAux.get(y).getGroups().size();k++)
					{
						if(turmaAuxiliar.getGroupId().equals(cadeirasAux.get(y).getGroups().get(k).getGroupId()))
						{
							cadeirasAux.get(y).getGroups().get(k).addGroupSession(aulasComReqroomid.get(i));
							achouTurma=true;
						}
							
					}
					if(!achouTurma)
					{
						turmaAuxiliar.addGroupSession(aulasComReqroomid.get(i));
						turmasAux.add(turmaAuxiliar);
						cadeirasAux.get(y).addGroup(turmaAuxiliar);
						
					}
					achouTurma=false;
					achouCadeira=true;
				}
			}
			if(!achouCadeira)
			{
				turmaAuxiliar.addGroupSession(aulasComReqroomid.get(i));
				turmasAux.add(turmaAuxiliar);
				cadeiraAuxiliar.addGroup(turmaAuxiliar);
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
					if(true)
					{
						int diaSemana = aulasComRequisitos.get(i).getWeekday();
						int horario = aulasComRequisitos.get(i).getStartTime();
						int duracao = aulasComRequisitos.get(i).getSessionDuration();
//						if(predios.get(j).getSalas().get(x).verificaSala(diaSemana, horario,duracao))
//								System.out.println(aulasComRequisitos.get(i).getAlunos() + " " + predios.get(j).getSalas().get(x).getCapacity());
						if(predios.get(j).getRooms().get(x).getAvailability(diaSemana, horario))
						{
//							System.out.println("Achei sala");
//							System.out.println(predios.get(j).getSalas().get(x).getRecursos().length());
							switch(predios.get(j).getRooms().get(x).getFeatures().length())
							{
							case 1:
								
								//System.out.println(predios.get(j).getSalas().get(x).getRecursos().charAt(0) );
								if(predios.get(j).getRooms().get(x).getFeatures().charAt(0) == aulasComRequisitos.get(i).getFeaturesRequired().charAt(0))
								{
									
									
									aulasComRequisitos.get(i).setSessionRoom(predios.get(j).getRooms().get(x));
									aulasComRequisitos.get(i).setSessionBuildingId(predios.get(j).getId());
									predios.get(j).getRooms().get(x).setAvailability(diaSemana, horario, false);
									achou=true;
//									x=predios.get(j).getSalas().size();
//									j=predios.size();
									
								}
								break;
							case 4:
								if(predios.get(j).getRooms().get(x).getFeatures().charAt(0) == aulasComRequisitos.get(i).getFeaturesRequired().charAt(0))
								{
									 
									
									aulasComRequisitos.get(i).setSessionRoom(predios.get(j).getRooms().get(x));
									aulasComRequisitos.get(i).setSessionBuildingId(predios.get(j).getId());
									predios.get(j).getRooms().get(x).setAvailability(diaSemana, horario, false);
									achou=true;
//									x=predios.get(j).getSalas().size();
//									j=predios.size();
									
								}
								else if(predios.get(j).getRooms().get(x).getFeatures().charAt(3) == aulasComRequisitos.get(i).getFeaturesRequired().charAt(0))
								{
									
									
									aulasComRequisitos.get(i).setSessionRoom(predios.get(j).getRooms().get(x));
									aulasComRequisitos.get(i).setSessionBuildingId(predios.get(j).getId());
									predios.get(j).getRooms().get(x).setAvailability(diaSemana, horario, false);
//									x=predios.get(j).getSalas().size();
//									j=predios.size();
									achou=true;
									
								}
								break;
							case 7:
								if(predios.get(j).getRooms().get(x).getFeatures().charAt(0) == aulasComRequisitos.get(i).getFeaturesRequired().charAt(0))
								{
									 
									
									aulasComRequisitos.get(i).setSessionRoom(predios.get(j).getRooms().get(x));
									aulasComRequisitos.get(i).setSessionBuildingId(predios.get(j).getId());
									predios.get(j).getRooms().get(x).setAvailability(diaSemana, horario, false);
//									x=predios.get(j).getSalas().size();
//									j=predios.size();
									achou=true;
									
								}
								else if(predios.get(j).getRooms().get(x).getFeatures().charAt(3) == aulasComRequisitos.get(i).getFeaturesRequired().charAt(0))
								{
									 
									
									aulasComRequisitos.get(i).setSessionRoom(predios.get(j).getRooms().get(x));
									aulasComRequisitos.get(i).setSessionBuildingId(predios.get(j).getId());
									predios.get(j).getRooms().get(x).setAvailability(diaSemana, horario, false);
//									x=predios.get(j).getSalas().size();
//									j=predios.size();
									achou=true;
									
								}
								else if(predios.get(j).getRooms().get(x).getFeatures().charAt(6) == aulasComRequisitos.get(i).getFeaturesRequired().charAt(0))
								{
									 
									
									aulasComRequisitos.get(i).setSessionRoom(predios.get(j).getRooms().get(x));
									aulasComRequisitos.get(i).setSessionBuildingId(predios.get(j).getId());
									predios.get(j).getRooms().get(x).setAvailability(diaSemana, horario, false);
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
				System.out.println(aulasComRequisitos.get(i).getSessionGroup().getGroupId());
				System.out.println(aulasComRequisitos.get(i).getSessionGroup().getGroupCourse().getCourseID());
			}
			else
			{
				//System.out.println("CERTO comREQ");
				achou=false;
			}
			
			Course cadeiraAuxiliar =  new Course(aulasComReqroomid.get(i).getSessionGroup().getGroupCourse().getCourseName(), aulasComReqroomid.get(i).getSessionGroup().getGroupCourse().getCourseID());
			
			Group turmaAuxiliar = new Group();
			
			turmaAuxiliar.setGroupId(aulasComReqroomid.get(i).getSessionGroup().getGroupId());
			
			for(int y=0;y<cadeirasAux.size();y++)
			{
				if(cadeiraAuxiliar.getCourseID().equals(cadeirasAux.get(y).getCourseID()))
				{
					for(int k=0;k<cadeirasAux.get(y).getGroups().size();k++)
					{
						if(turmaAuxiliar.getGroupId().equals(cadeirasAux.get(y).getGroups().get(k).getGroupId()))
						{
							cadeirasAux.get(y).getGroups().get(k).addGroupSession(aulasComRequisitos.get(i));
							achouTurma=true;
						}
							
					}
					if(!achouTurma)
					{
						turmaAuxiliar.addGroupSession(aulasComRequisitos.get(i));
						turmasAux.add(turmaAuxiliar);
						cadeirasAux.get(y).addGroup(turmaAuxiliar);
						
					}
					achouTurma=false;
					achouCadeira=true;
				}
			}
			if(!achouCadeira)
			{
				turmaAuxiliar.addGroupSession(aulasComRequisitos.get(i));
				turmasAux.add(turmaAuxiliar);
				cadeiraAuxiliar.addGroup(turmaAuxiliar);
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
		
		
		
		//Collections.sort((ArrayList) aulasSemReq);
		
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
					
					if(predios.get(j).getRooms().get(x).getAvailability(diaSemana, horario) && (aulasSemReq.get(i).getSessionGroup().getNumStudents()<predios.get(j).getRooms().get(x).getCapacity()))
					{	
						//System.out.println(predios.get(j).getSalas().get(x).getId());
						aulasSemReq.get(i).setSessionRoom(predios.get(j).getRooms().get(x));
						aulasSemReq.get(i).setSessionBuildingId(predios.get(j).getId());
						predios.get(j).getRooms().get(x).setAvailability(diaSemana, horario, false);
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
			
			Course cadeiraAuxiliar =  new Course(aulasComReqroomid.get(i).getSessionGroup().getGroupCourse().getCourseName(), aulasComReqroomid.get(i).getSessionGroup().getGroupCourse().getCourseID());
			
			Group turmaAuxiliar = new Group();
			
			
			turmaAuxiliar.setGroupId(aulasComReqroomid.get(i).getSessionGroup().getGroupId());
			achouTurma=false;
			achouCadeira=false;
			
			for(int y=0;y<cadeirasAux.size();y++)
			{
				if(cadeiraAuxiliar.getCourseID().equals(cadeirasAux.get(y).getCourseID()))
				{
					for(int k=0;k<cadeirasAux.get(y).getGroups().size();k++)
					{
						if(turmaAuxiliar.getGroupId().equals(cadeirasAux.get(y).getGroups().get(k).getGroupId()))
						{
							cadeirasAux.get(y).getGroups().get(k).addGroupSession(aulasSemReq.get(i));
							achouTurma=true;
						}
							
					}
					if(!achouTurma)
					{
						turmaAuxiliar.addGroupSession(aulasSemReq.get(i));
						turmasAux.add(turmaAuxiliar);
						cadeirasAux.get(y).addGroup(turmaAuxiliar);
						
					}
					achouTurma=false;
					achouCadeira=true;
				}
			}
			if(!achouCadeira)
			{
				turmaAuxiliar.addGroupSession(aulasSemReq.get(i));
				turmasAux.add(turmaAuxiliar);
				cadeiraAuxiliar.addGroup(turmaAuxiliar);
				cadeirasAux.add(cadeiraAuxiliar);
			}
			achouCadeira=false;
		}
		
//		Collections.sort(cadeirasAux);
		
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
