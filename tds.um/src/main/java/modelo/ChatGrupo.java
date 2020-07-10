package modelo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Un chat de grupo es un conjunto de contacto individual (chat individual) Con
 * una lista de miembros y administradores
 * 
 * @author Álvaro y Ana.
 *
 */
public class ChatGrupo extends Chat {

	// Aqui el atributo nombre se entiende como el nombre del grupo
	private LinkedList<ChatIndividual> miembros = new LinkedList<ChatIndividual>();
	private HashSet<Usuario> administradores = new HashSet<Usuario>();
	// si es el padre (el primer grupo que se crea) su idPadre = idChat
	private String idPadre;
	// Usuario "dueño" del grupo
	private Usuario duenyo;
	// grupo duplicado: todo igual menos los miembros.
	private HashSet<ChatGrupo> gruposHijo = new HashSet<ChatGrupo>();

	/**
	 * Constructor de ChatGurpo
	 * 
	 * @param nombreg
	 * @param mi
	 */
	public ChatGrupo(String nombreg, ChatIndividual... mi) {
		super(nombreg);
		for (ChatIndividual m : mi) {
			miembros.addFirst(m);
		}
		this.idPadre = "0"; // Se sobrescribira luego con el correcto. Evitar nulos
	}

	/**
	 * Constructor de ChatGurpo
	 * 
	 * @param nombreg
	 * @param LinkedList<ChatIndividual> mi
	 */
	public ChatGrupo(String nombreg, LinkedList<ChatIndividual> mi) {
		super(nombreg);
		for (ChatIndividual m : mi) {
			miembros.addFirst(m);
		}
	}

	/**
	 * Constructor de ChatGurpo
	 * 
	 * @param nombreg
	 */
	public ChatGrupo(String nombreg) {
		super(nombreg);
		miembros = new LinkedList<ChatIndividual>();
	}

	// ##################### GETS Y SETS #######################

	/**
	 * Método get de ChatGrupo.
	 * 
	 * @return LinkedList<ChatIndividual> de miembros del grupo
	 */
	public LinkedList<ChatIndividual> getMiembros() {
		return this.miembros;
	}

	/**
	 * Método get de ChatGrupo.
	 * 
	 * @return HashSet<Usuario>, los administradores del grupo.
	 */
	public HashSet<Usuario> getAdministradores() {
		return this.administradores;
	}

	/**
	 * Método get de ChatGrupo.
	 * 
	 * @return String idPadre, idChat del padre.
	 */
	public String getIdPadre() {
		return this.idPadre;
	}

	/**
	 * Método get de ChatGrupo.
	 * 
	 * @return HashSet<ChatGrupo> grupos hijo.
	 */
	public HashSet<ChatGrupo> getGruposHijo() {
		return gruposHijo;
	}

	/**
	 * Método get de ChatGrupo.
	 * 
	 * @return Usuario dueño del grupo.
	 * 
	 */
	public Usuario getDuenyo() {
		return this.duenyo;
	}

	/**
	 * Método set de ChatGrupo.
	 * 
	 * @param HashSet<ChatGrupo> gruposHijo
	 */
	public void setGruposHijo(HashSet<ChatGrupo> gruposHijo) {
		this.gruposHijo = gruposHijo;
	}

	/**
	 * Método set de ChatGrupo.
	 * 
	 * @param LinkedList<ChatIndividual> miembros
	 */
	public void setMiembros(LinkedList<ChatIndividual> miembros) {
		this.miembros.addAll(miembros);
	}

	/**
	 * Método set de ChatGrupo.
	 * 
	 * @param HashSet<Usuario> admins
	 */
	public void setAdministradores(HashSet<Usuario> admins) {
		this.administradores.addAll(admins);
	}

	/**
	 * Método set de ChatGrupo. Se utilizará cuando sea la primera vez que se crea
	 * el grupo (en el controlador)
	 * 
	 * @param String idPadre
	 */
	public void setIdPadre(String idPadre) {
		this.idPadre = idPadre;
	}

	/**
	 * Método set de ChatGrupo. Indica el usuario "dueño" de este grupo
	 * 
	 * @param Usuario u
	 */
	public void setDuenyo(Usuario u) {
		this.duenyo = u;
	}

	/**
	 * Añade un nuevo administrador al grupo.
	 * 
	 * @param Usuario u
	 * @return true si lo añade, false en otro caso.
	 */
	public boolean addAdmin(Usuario u) {
		Integer id = (Integer) this.getId();

		if (id.toString() == this.idPadre) { // es el grupo Padre
			this.getGruposHijo().stream().forEach(h -> h.addAdmin(u));
		}

		return administradores.add(u);
	}

	/**
	 * Añade un nuevo miembro al grupo.
	 * 
	 * @param ChatIndividual m, nuevo miembro (contacto del usuario dueño del
	 *                       grupo.)
	 */
	public void addMiembro(ChatIndividual m) {
		this.miembros.add(m);

	}

	/**
	 * Funcion que solo se aplica a un grupo padre (id == idPadre)
	 * 
	 * @param ChatGrupo hijo, grupo "duplicado" que pende de él.
	 */
	public void addGrupoHijo(ChatGrupo hijo) {
		this.gruposHijo.add(hijo);
	}

	// ##################### FUNCIONALIDAD #######################

	/**
	 * Busca los mensajes que ha enviado un miembro del grupo.
	 * Sobre una lista de mensajes ya filtrados.
	 * @param ChatIndividual m
	 * @param LinkedList<Mensaje> mensajesFiltrados
	 * @return mensajes asociados a un miembro del grupo
	 */
	public LinkedList<Mensaje> BuscarMensajePorContactos(ChatIndividual m, LinkedList<Mensaje> mensajesFiltrados) {
		LinkedList<Mensaje> mensajes = new LinkedList<Mensaje>();
		
		mensajesFiltrados.stream()
						.filter(men -> men.getEmisor().equals(m.getContacto()))
						.forEach(men -> mensajes.add(men));
		
		return mensajes;
	}

	/**
	 * Devuelve el numero de mensajes totales enviads en el grupo.
	 * 
	 * @return int mensajes totales del grupo.
	 */
	public int getMensajesTotales() {
		return (int) this.getHistorial().stream().count();
	}

	/**
	 * Funcion que devuelve el porcentaje de mensajes que ha enviado un usuario.
	 * 
	 * @param Usuario u
	 * @return int porcentaje de mensajes que ha enviado el usuario.
	 */
	public double porcentajeDelTotal(Usuario u) {
		int totales = this.getMensajesTotales();
		int usuario = (int) this.getHistorial().stream().filter(m -> m.getEmisor().equals(u)).count();
		// si para TOtales --> usuario
		// para 100 --> x
		return (double) usuario * 100 / totales;
	}

	public String getInfo() {
		String infoTot = "";
		infoTot = "[ Nombre de grupo= " + this.getNombre() + "Miembros: \n";
		Iterator<ChatIndividual> iterador = miembros.iterator();

		while (iterador.hasNext()) {
			infoTot += iterador.next().getInfo();
		}

		infoTot += " ] \n";
		return infoTot;
	}

	/**
	 * Funcion que elimina un miembro de un grupo. La llama un administrador.
	 * 
	 * @param ChatIndividual miembro a borrar.
	 */
	public void eliminarMiembro(ChatIndividual miembro) {

		Iterator<ChatIndividual> it = this.miembros.iterator();
		boolean fin = false;
		while (!fin && it.hasNext()) {
			if (it.next().equals(miembro)) {
				fin = true;
				it.remove();
			}
		}

	}

	/**
	 * Funcion que actualiza el nombre de grupoo en el padre y los hijos.
	 * 
	 * @param nombre
	 */
	public void cambiarNombre(String nombre) {
		// Vemos si la ha llamado el grupo padre (siempre pasará esto al inicio).
		if (this.idPadre == Integer.valueOf(this.getId()).toString()) {
			// el padre llamará a todos los hijos y cuando estos terminen él se tratará.

			for (ChatGrupo gHijo : this.gruposHijo) {
				gHijo.setNombre(nombre);
			}

		} // si no es el padre se trata y ya.
		this.setNombre(nombre);

	}

}
