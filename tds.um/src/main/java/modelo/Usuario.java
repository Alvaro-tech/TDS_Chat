package modelo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Usuario {
	String nombre;
	String email;
	String fechaNacimiento;
	String movil;
	Usuario user; //tu propio usuario
	String clave; //contraseña
	String fotoPerfil; 
	boolean premium;
	//Lista de chats
	HashSet<ChatIndividual> chatsInd = new HashSet<ChatIndividual>();
	HashSet<ChatGrupo> chatsGroup = new HashSet<ChatGrupo>();
	//lista de contactos que tiene un usuario
	//SUPER NO, ES UNA LISTA DE CHATS QUE MAL QUE MAL QUE MAL
	//TODO:Arreglar esto
	HashMap<String, Usuario> contactos = new HashMap<String, Usuario>(); //Clave nombre Personal , Valor Usuario 
	Integer id;
	String saludo;  
	
	//constructores
	public Usuario(String nombre, String email, String fecha, String movil, String clave) { //Constructor crear uno nuevo
		this.nombre = nombre;
		this.email = email; 
		this.fechaNacimiento = fecha;
		this.movil = movil;
		this.clave = clave;
		this.premium = false;
		this.user = this; //?
		this.saludo = "Hey there, I'm using TDSchat.";
		this.fotoPerfil = "./iconos/Defecto.PNG";
	}
	
	public Usuario(String nombre, String email, String fecha, String movil, String clave, String saludop, String foto) { //Constructor para recuperar
		this.nombre = nombre;
		this.email = email;
		this.fechaNacimiento = fecha;
		this.movil = movil;
		this.clave = clave;
		this.premium = false;
		this.user = this; //?
		this.saludo = saludop;
		this.fotoPerfil = foto;
	}
	
	//gets y sets
	public String getNombre() {
		return nombre;
	}
	
	public String getFecha() {
		return fechaNacimiento;
	}

	public String getMovil() {
		return movil;
	}
	
	public Usuario getUser() {
		return user;
	}
	
	public HashSet<Chat> getTodosLosChats(){
		HashSet<Chat> chats = new HashSet<Chat>();
		chats.addAll(chatsGroup);
		chats.addAll(chatsInd);
		return chats;
	}
	
	public boolean isPremium() {
		return premium;
	}
	
	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public String getEmail() {
		return email;
	}
	
	public String getClave() {
		return clave;
	}


	public void setId(int id) {
		this.id=id;
	}


	public Integer getId() {
		return id;
	}

	
	public String getSaludo() {
		return saludo;
	}
	
	public HashSet<ChatIndividual> getChatsInd() {
		return chatsInd;
	}

	public HashSet<ChatGrupo> getChatsGroup() {
		return chatsGroup;
	}
	
	
	public String getFotoPerfil() {
		return fotoPerfil;
	}
	
	public String setSaludo(String saludo) {
		return this.saludo = saludo;
	}
	
	public HashMap<String, Usuario> getContactos() {
		return contactos;
	}

	public void setContactos(HashMap<String, Usuario> contactos) {
		this.contactos = contactos;
	} 

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
	
	public void setGrupos(HashSet<ChatGrupo> grupos) {
		this.chatsGroup = grupos;
	}
	
	public void setChatIndividuales(HashSet<ChatIndividual> chats) {
		this.chatsInd = chats;
	}

	//########### Otros Métodos ###############
	public void agregarContacto(String NombrePersonal, Usuario contacto) {
		contactos.put(NombrePersonal, contacto);
	}
	
	public void agregarChatIndividual(ChatIndividual chat) {
		chatsInd.add(chat);
	}
	
	public void agregarChatGrupo(ChatGrupo chat) {
		chatsGroup.add(chat);
	}
	
	//TODO: arreglar esto porque ha cambiado la forma de los chatsIndividuales (antes estaba FATAL alvaro)
	/*
	public ChatIndividual empezarChatIndividual(String nombre) { //TODO: Comprobar que la conversacion no existe ya, recorrer los chats, comparando id's(?)
		Usuario DatosUsuario = contactos.get(nombre);
		
		//if (!chatsInd.contains(chat)) {
		ChatIndividual nuevoChat = new ChatIndividual(DatosUsuario.getMovil(),nombre);
		chatsInd.add(nuevoChat);
		//}
		return nuevoChat;
		
	}
	
	public void empezarChatGrupo (String nombre) {  //TODO: la funcion en sí
		
	}
		
	
	public void eliminarChatIndividual(ChatIndividual chat) { //TODO: Esto habrá que cambiarlo a borrarlo por nombre?
		if (chatsInd.contains(chat)) {
			chatsInd.remove(chat);
		}
	}
		
		public void eliminarChatGrupo(ChatGrupo chat) { //TODO: Esto habrá que cambiarlo a borrarlo por nombre?
			if (chatsGroup.contains(chat)) {
				chatsGroup.remove(chat);
			}
		
	}
	*/
	
	public String[] getlistaDeContactos() {
		LinkedList <String> aux = new LinkedList<String>();
		for (String u : this.contactos.keySet()) {
			aux.add(u);
		}
		  // Converting LinkedList to Object Array 
        Object[] objArray = aux.toArray();
  
        // Convert Object[] to String[] 
        String[] array = Arrays.copyOf(objArray, 
                                       objArray.length, 
                                       String[].class); 
		return array;
		
}

	public ChatIndividual empezarChatIndividual(String movilReceptor, String nombre, Usuario emisor) {
		ChatIndividual chatI = new ChatIndividual(movilReceptor, nombre, emisor);
		return chatI;
	}

	/**
	 * Funcion que devuelve una lista de Chats del usuario de manera ordenada.
	 * @return
	 */
	public LinkedList<Chat> getChatRecientes() {
		//TODO: HACER.
		LinkedList<Chat> todos = new LinkedList<Chat>();
		
		todos.addAll(chatsGroup);
		todos.addAll(chatsInd);
		
		
		todos.stream()
			.sorted((c1,c2) -> c1.compare(c1, c2));
		
		return todos;

	}
	
	
	
	
}
	

