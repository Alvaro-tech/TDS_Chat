package modelo;

import java.util.HashSet;
import java.util.LinkedList;

public class ChatGrupo extends Chat{
	
	/*
	 * Un chat de grupo es un conjunto de contacto individual (chat individual)
	 Con una lista de 
	 */
	
	//Aqui el atributo nombre se entiende como el nombre del grupo
	private LinkedList<ChatIndividual> miembros = new LinkedList<ChatIndividual>();
	private HashSet<Usuario> administradores = new HashSet<Usuario>();
	
	public ChatGrupo(String nombreg, ChatIndividual... mi) {
		super(nombreg);
		for (ChatIndividual m : mi) {
			System.out.println("ejecucion de chatGrupo");
			miembros.addFirst(m);
		}
	}
	
	public ChatGrupo(String nombreg) {
		super(nombreg);
		miembros = new LinkedList<ChatIndividual>();
	}
	
	public boolean addAdmin(Usuario u) {
		return administradores.add(u);
	}



	public LinkedList<ChatIndividual> getMiembros() {
		return this.miembros;
	}


	public HashSet<Usuario> getAdministradores() {
		return this.administradores;
	}

	public void setMiembros(LinkedList<ChatIndividual> miembros) {
		this.miembros.addAll(miembros);
	}

	public void setAdministradores(HashSet<Usuario> admins) {
		this.administradores.addAll(admins);
	}
	
	
}
