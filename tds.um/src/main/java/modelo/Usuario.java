package modelo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Clase que representa a un Usuario del sistema en el modelo de este.
 * 
 * @author Álvaro y Ana.
 *
 */
public class Usuario {
	String nombre;
	String email;
	String fechaNacimiento;
	String movil;
	Usuario user; // tu propio usuario
	String clave; // contraseña
	String fotoPerfil;
	boolean premium;
	// Lista de chats
	HashSet<ChatIndividual> chatsInd = new HashSet<ChatIndividual>();
	HashSet<ChatGrupo> chatsGroup = new HashSet<ChatGrupo>();
	// lista de contactos que tiene un usuario
	// SUPER NO, ES UNA LISTA DE CHATS QUE MAL QUE MAL QUE MAL
	// TODO:Arreglar esto,
	// ESTO NO PUEDE SER, DEBERÍAS HACERLO A TRAVES DE CHAT INDIVIDUAL.
	HashMap<String, Usuario> contactos = new HashMap<String, Usuario>(); // Clave nombre Personal , Valor Usuario
	Integer id;
	String saludo;

	/**
	 * Constructor de la clase Usuario.
	 * @param nombre
	 * @param email
	 * @param fecha
	 * @param movil
	 * @param clave
	 */
	public Usuario(String nombre, String email, String fecha, String movil, String clave) { // Constructor crear uno
																							// nuevo
		this.nombre = nombre;
		this.email = email;
		this.fechaNacimiento = fecha;
		this.movil = movil;
		this.clave = clave;
		this.premium = false;
		this.user = this; // ?
		this.saludo = "Hey there, I'm using TDSchat.";
		this.fotoPerfil = "./iconos/Defecto.PNG";
	}

	/**
	 * Constructor de la clase Usuario.
	 * @param nombre
	 * @param email
	 * @param fecha
	 * @param movil
	 * @param clave
	 * @param saludop
	 * @param foto
	 */
	public Usuario(String nombre, String email, String fecha, String movil, String clave, String saludop, String foto) { // Constructor
																															// para
																															// recuperar
		this.nombre = nombre;
		this.email = email;
		this.fechaNacimiento = fecha;
		this.movil = movil;
		this.clave = clave;
		this.premium = false;
		this.user = this; // ?
		this.saludo = saludop;
		this.fotoPerfil = foto;
	}

	// ##################### METODOS GET Y SET #######################

	/**
	 * Metodo get de Usuario.
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo get de Usuario.
	 * @return fecha de nacimiento.
	 */
	public String getFecha() {
		return fechaNacimiento;
	}

	/**
	 * Metodo get de Usuario.
	 * @return movil
	 */
	public String getMovil() {
		return movil;
	}

	/**
	 * Metodo get de Usuario.
	 * @return true si es premium, false en otro caso.
	 */
	public boolean isPremium() {
		return premium;
	}

	/**
	 * Metodo get de Usuario.
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Metodo get de Usuario.
	 * @return password o clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Metodo get de Usuario.
	 * @return ID del Usuario.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Metodo get de Usuario.
	 * @return saludo
	 */
	public String getSaludo() {
		return saludo;
	}

	/**
	 * Metodo get de Usuario.
	 * @return ChatsIndividuales o contactos.
	 */
	public HashSet<ChatIndividual> getChatsInd() {
		return chatsInd;
	}

	/**
	 * Metodo get de Usuario.
	 * @return grupos en los que está el usuario.
	 */
	public HashSet<ChatGrupo> getChatsGroup() {
		return chatsGroup;
	}

	/**
	 * Metodo get de Usuario.
	 * @return foto de perfil.
	 */
	public String getFotoPerfil() {
		return fotoPerfil;
	}

	// DEBERIAMOS QUITAR ESTO
	/**
	 * Metodo get de Usuario.
	 * @return contactos (HashSet de Usuario)
	 */
	public HashMap<String, Usuario> getContactos() {
		return contactos;
	}

	/**
	 * Metodo get de Usuario.
	 * @return todos los chats que tiene el Usuario.
	 */
	public HashSet<Chat> getTodosLosChats() {
		HashSet<Chat> chats = new HashSet<Chat>();
		chats.addAll(chatsGroup);
		chats.addAll(chatsInd);
		return chats;
	}

	/**
	 * Metodo set de Usuario.
	 * @param premium, boolean.
	 */
	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	/**
	 * Metodo set de Usuario.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Metodo set de Usuario.
	 * @param saludo
	 * @return String saludo actual
	 *         //???????????????????????????????????????????????????????????????
	 */
	public String setSaludo(String saludo) {
		return this.saludo = saludo;
	}

	/**
	 * Metodo set de Usuario.
	 * @param contactos
	 */
	public void setContactos(HashMap<String, Usuario> contactos) {
		this.contactos = contactos;
	}

	/**
	 * Metodo set de Usuario.
	 * @param fotoPerfil
	 */
	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	/**
	 * Metodo set de Usuario.
	 * @param grupos
	 */
	public void setGrupos(HashSet<ChatGrupo> grupos) {
		this.chatsGroup = grupos;
	}

	/**
	 * Metodo set de Usuario.
	 * @param chats
	 */
	public void setChatIndividuales(HashSet<ChatIndividual> chats) {
		this.chatsInd = chats;
	}

	// ##################### FUNCIONALIDAD #######################

	/**
	 * Funcion para agregar un contacto
	 * @param NombrePersonal
	 * @param contacto
	 */
	public void agregarContacto(String NombrePersonal, Usuario contacto) {
		contactos.put(NombrePersonal, contacto);
	}

	/**
	 * Funcion para agregar un chat individual a la lista de chats
	 * @param chat
	 */
	public void agregarChatIndividual(ChatIndividual chat) {
		chatsInd.add(chat);
	}

	/**
	 * Funcion para agregar un grupo a la lista de grupos.
	 * @param chat
	 */
	public void agregarChatGrupo(ChatGrupo chat) {
		chatsGroup.add(chat);
	}

	// TODO: arreglar esto porque ha cambiado la forma de los chatsIndividuales
	// (antes estaba FATAL alvaro)
	/*
	 * public ChatIndividual empezarChatIndividual(String nombre) { //TODO:
	 * Comprobar que la conversacion no existe ya, recorrer los chats, comparando
	 * id's(?) Usuario DatosUsuario = contactos.get(nombre);
	 * 
	 * //if (!chatsInd.contains(chat)) { ChatIndividual nuevoChat = new
	 * ChatIndividual(DatosUsuario.getMovil(),nombre); chatsInd.add(nuevoChat); //}
	 * return nuevoChat;
	 * 
	 * }
	 * 
	 * public void empezarChatGrupo (String nombre) { //TODO: la funcion en sí
	 * 
	 * }
	 * 
	 * 
	 * public void eliminarChatIndividual(ChatIndividual chat) { //TODO: Esto habrá
	 * que cambiarlo a borrarlo por nombre? if (chatsInd.contains(chat)) {
	 * chatsInd.remove(chat); } }
	 * 
	 * public void eliminarChatGrupo(ChatGrupo chat) { //TODO: Esto habrá que
	 * cambiarlo a borrarlo por nombre? if (chatsGroup.contains(chat)) {
	 * chatsGroup.remove(chat); }
	 * 
	 * }
	 */

	/**
	 * Funcion para obtener la lista de contactos asociados al Usuario
	 * @return
	 */
	public String[] getlistaDeContactos() {
		LinkedList<String> aux = new LinkedList<String>();
		for (String u : this.contactos.keySet()) {
			aux.add(u);
		}
		// Converting LinkedList to Object Array
		Object[] objArray = aux.toArray();

		// Convert Object[] to String[]
		String[] array = Arrays.copyOf(objArray, objArray.length, String[].class);
		return array;

	}
	
	/**
	 * Funcion que comienza un chat Individual con un contacto.
	 * @param movilReceptor
	 * @param nombre
	 * @param emisor
	 * @return
	 */
	public ChatIndividual empezarChatIndividual(String movilReceptor, String nombre, Usuario emisor) {
		ChatIndividual chatI = new ChatIndividual(movilReceptor, nombre, emisor);
		return chatI;
	}

	/**
	 * Funcion que devuelve una lista de Chats del usuario de manera ordenada.
	 * @return lista de chats del usuarios ordenada. LinkedList<Chat>
	 */
	public LinkedList<Chat> getChatRecientes() {
		LinkedList<Chat> todos = new LinkedList<Chat>();
		todos.addAll(this.getTodosLosChats());

		todos.stream()
			.sorted((c1, c2) -> c1.compare(c1, c2));

		return todos;

	}

}
