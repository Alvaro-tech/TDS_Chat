package modelo;

import java.util.HashSet;
import java.util.LinkedList;

/**
 *  Un chat de grupo es un conjunto de contacto individual (chat individual)
 *	Con una lista de miembros y administradores
 * @author Álvaro y Ana.
 *
 */
public class ChatGrupo extends Chat{
	
	//Aqui el atributo nombre se entiende como el nombre del grupo
	private LinkedList<ChatIndividual> miembros = new LinkedList<ChatIndividual>();
	private HashSet<Usuario> administradores = new HashSet<Usuario>();
	
	/**
	 * Constructor de ChatGurpo
	 * @param nombreg
	 * @param mi
	 */
	public ChatGrupo(String nombreg, ChatIndividual... mi) {
		super(nombreg);
		for (ChatIndividual m : mi) { 
			System.out.println("ejecucion de chatGrupo");
			miembros.addFirst(m);
		}
	}
	
	/**
	 * Constructor de ChatGurpo
	 * @param nombreg
	 */
	public ChatGrupo(String nombreg) {
		super(nombreg);
		miembros = new LinkedList<ChatIndividual>();
	}
	

	// ##################### GETS Y SETS #######################
	
	/**
	 * Método get de ChatGrupo.
	 * @return  LinkedList<ChatIndividual> de miembros del grupo
	 */
	public LinkedList<ChatIndividual> getMiembros() {
		return this.miembros;
	}

	/**
	 * Método get de ChatGrupo.
	 * @return HashSet<Usuario>, los administradores del grupo.
	 */
	public HashSet<Usuario> getAdministradores() {
		return this.administradores;
	}

	/**
	 * Método set de ChatGrupo.
	 * @param LinkedList<ChatIndividual> miembros
	 */
	public void setMiembros(LinkedList<ChatIndividual> miembros) {
		this.miembros.addAll(miembros);
	}

	/**
	 * Método set de ChatGrupo.
	 * @param HashSet<Usuario> admins
	 */
	public void setAdministradores(HashSet<Usuario> admins) {
		this.administradores.addAll(admins);
	}
	
	/**
	 * 
	 * @param Usuario u
	 * @return true si lo añade, false en otro caso.
	 */
	public boolean addAdmin(Usuario u) {
		return administradores.add(u);
	}
	

	// ##################### FUNCIONALIDAD #######################
	
	/**
	 * Busca los mensajes que ha enviado un miembro del grupo.
	 * @param miems
	 * @return mensajes asociados a un miembro del grupo
	 */
	public LinkedList<Mensaje> BuscarMensajePorContactos(ChatIndividual m){
		LinkedList<Mensaje> filtrados = new LinkedList<Mensaje>();
		getHistorial().stream() 
						.filter(men -> men.getEmisor().equals(m.getContacto()))
						.forEach(men -> filtrados.add(men));
		return filtrados;
	}
	 
	/**
	 * Busca los mensajes que ha enviado un miembro del grupo. Se lo añade a los mensajes de
	 * los que ya se había hecho una búsqueda por filtro.
	 * @param miems
	 * @return mensajes asociados a un miembro del grupo
	 */
	public LinkedList<Mensaje> BuscarMensajePorContactos(ChatIndividual m, LinkedList<Mensaje> mensajes){
		LinkedList<Mensaje> filtrados = this.BuscarMensajePorContactos(m);
		filtrados.addAll(mensajes);
		return filtrados;
	}
	
}
