package modelo;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import persistencia.AdaptadorChatIndividualDAO;

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
	Integer id;
	String saludo;
	String conversacionesAbiertas;

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
		this.conversacionesAbiertas = "";
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
	public Usuario(String nombre, String email, String fecha, String movil, String clave, String saludop, String foto, String conversaciones) { // Constructor
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
		this.conversacionesAbiertas = conversaciones;
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
	
	/**
	 * Funcion que calcula la edad del usuario.
	 * @return int años de edad.
	 */
	public int getEdad() {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaNac = LocalDate.parse(this.getFecha(), fmt);
		LocalDate ahora = LocalDate.now();

		Period periodo = Period.between(fechaNac, ahora);
		return periodo.getYears();
	}
	
	
	/**
	 * Funcion para devolver las conversaciones abiertas del usuario en su sesion
	 * @return String conversacionesAbiertas
	 */
	public String getConversacionesAbiertas() {
		return conversacionesAbiertas;
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
	 */
	public void setSaludo(String saludo) {
		this.saludo = saludo;
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

	/**
	 * Método get de Usuario
	 * @param conversacionesAbiertas
	 */
	public void setConversacionesAbiertas(String conversacionesAbiertas) {
		this.conversacionesAbiertas = conversacionesAbiertas;
	}

	/**
	 * Funcion para añadir chats a las conversaciones abiertas
	 * @param String idChat
	 */
	public void addConversacion(int idChat) {
		this.conversacionesAbiertas = conversacionesAbiertas + idChat + " ";
	}
	
	// ##################### FUNCIONALIDAD #######################

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


	/** 
	 * Funcion que devuelve una lista de Chats del usuario de manera ordenada.
	 * @return lista de chats del usuarios ordenada. LinkedList<Chat>
	 */
	public LinkedList<Chat> getChatRecientes() {
		LinkedList<Chat> todos = new LinkedList<Chat>();
		todos.addAll(this.getTodosLosChats());
		LinkedList<Chat> recientes = new LinkedList<Chat>();

        	for (Chat chat : todos) {
        		String idChat = chat.getId() +" "; //De esta manera el constains no da por true el 1 en un 12, asegura buscar numeros independientes
				if(conversacionesAbiertas.contains(idChat)) {
					recientes.add(chat);
				}
			}
		

		recientes.stream()
			.sorted((c1, c2) -> c1.compare(c1, c2));

		return recientes;

	}

	/**
	 * Funcion que retorna el numero de mensajes totales enviados por el usuario
	 * en este mes.
	 * @return int numero de mensajes.
	 */
	public int getNumeroDeMensajesDelMes() {
		LinkedList<Chat> todos = new LinkedList<Chat>();
		todos.addAll(this.getTodosLosChats());
		
		LinkedList<Mensaje> mens = new LinkedList<Mensaje>();
		
		//Para el futuro: convertir esto en stream para que me quede preciosa la funcion.
		for (Chat c : todos) {
			for (Mensaje m : c.getHistorial()) {
				if(m.getEmisor().equals(this)) {
					mens.add(m);
				}
			}
		}
		
		//En mens tengo todos los mensajes que han sido enviados por el usuario.
		return (int) mens.stream()
				.filter(m -> m.getFecha().getMonth().equals(LocalDate.now().getMonth())) //Equals sobre enumerado
				.count();
			
	}

}
