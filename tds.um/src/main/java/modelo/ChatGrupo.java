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
	//si es el padre (el primer grupo que se crea) su idPadre = idChat
	private String idPadre;
	//Usuario "dueño" del grupo
	private Usuario duenyo;
	//grupo duplicado: todo igual menos los miembros.
	private HashSet<ChatGrupo> gruposHijo = new HashSet<ChatGrupo>();
	
	
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
	 * @param LinkedList<ChatIndividual> mi
	 */
	public ChatGrupo(String nombreg, LinkedList<ChatIndividual> mi) {
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
	 * Método get de ChatGrupo.
	 * @return String idPadre, idChat del padre.
	 */
	public String getIdPadre() {
		return this.idPadre;
	}

	/**
	 * Método get de ChatGrupo.
	 * @return HashSet<ChatGrupo> grupos hijo.
	 */
	public HashSet<ChatGrupo> getGruposHijo() {
		return gruposHijo;
	}

	/**
	 *  Método set de ChatGrupo.
	 * @param HashSet<ChatGrupo> gruposHijo
	 */
	public void setGruposHijo(HashSet<ChatGrupo> gruposHijo) {
		this.gruposHijo = gruposHijo;
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
	 * Método set de ChatGrupo.
	 * Se utilizará cuando sea la primera vez que se crea el grupo (en el controlador)
	 * @param String idPadre
	 */
	public void setIdPadre(String idPadre) {
		this.idPadre = idPadre;	
	}
	
	/**
	 * Método set de ChatGrupo.
	 * Indica el usuario "dueño" de este grupo
	 * @param Usuario u
	 */
	public void setDuenyo(Usuario u) {
		this.duenyo = u;
	}
	
	/**
	 * Añade un nuevo administrador al grupo.
	 * @param Usuario u
	 * @return true si lo añade, false en otro caso.
	 */
	public boolean addAdmin(Usuario u) {
		return administradores.add(u);
	}
	
	/**
	 * Añade un nuevo miembro al grupo.
	 * @param ChatIndividual m, nuevo miembro (contacto del usuario dueño del grupo.)
	 */
	public void addMiembro(ChatIndividual m) {
		miembros.add(m);
	}
	
	public void addGrupoHijo(ChatGrupo hijo) {
		this.gruposHijo.add(hijo);
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
