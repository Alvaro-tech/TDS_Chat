package modelo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.stream.*;

public class ChatGrupo extends Chat{
	
	/*
	 * Un chat de grupo es un conjunto de contacto individual (chat individual)
	 Con una lista de 
	 */
	
	//Aqui el atributo nombre se entiende como el nombre del grupo
	private LinkedList<ChatIndividual> miembros;
	private HashSet<Usuario> administradores = new HashSet<Usuario>();
	
	public ChatGrupo(String nombreg, ChatIndividual ... mi) {
		super(nombreg);
		for (ChatIndividual m : mi) {
			miembros.add(m);
		}
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
	
	
}
