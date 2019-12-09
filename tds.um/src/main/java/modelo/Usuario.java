package modelo;

import java.util.HashMap;
import java.util.HashSet;

public class Usuario {
	String nombre;
	String email;
	String fechaNacimiento;
	String movil;
	Usuario user; //tu propio usuario
	String clave; //contraseña
	//TODO: Imagen fotoPerfil; //libreria imagen
	boolean premium;
	//Lista de chats
	HashSet<ChatIndividual> chatsInd = new HashSet<ChatIndividual>();
	HashSet<ChatGrupo> chatsGroup = new HashSet<ChatGrupo>();
	//lista de contactos que tiene un usuario
	HashMap<String, Usuario> contactos = new HashMap<String, Usuario>(); //Clave nombre Personal , Valor Usuario 
	Integer id;
	String saludo;
	
	//constructores
	public Usuario(String nombre, String email, String fecha, String movil, String clave) { //TODO: ID??
		this.nombre = nombre;
		this.email = email;
		this.fechaNacimiento = fecha;
		this.movil = movil;
		this.clave = clave;
		this.premium = false;
		this.user = this; //?
		this.saludo = "Hey there, I'm using TDSchat.";
		
	}
	
	public Usuario(String nombre, String email, String fecha, String movil, String clave, String saludop) { //TODO: ID??
		this.nombre = nombre;
		this.email = email;
		this.fechaNacimiento = fecha;
		this.movil = movil;
		this.clave = clave;
		this.premium = false;
		this.user = this; //?
		this.saludo = saludop;
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
	
	public String setSaludo(String saludo) {
		return this.saludo = saludo;
	}
	
	public HashMap<String, Usuario> getContactos() {
		return contactos;
	}

	public void setContactos(HashMap<String, Usuario> contactos) {
		this.contactos = contactos;
	}
	
	public HashSet<ChatIndividual> getChatsInd() {
		return chatsInd;
	}

	public HashSet<ChatGrupo> getChatsGroup() {
		return chatsGroup;
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
	
	public ChatIndividual empezarChatIndividual(String nombre) { //TODO: Comprobar que la conversacion no existe ya, recorrer los chats, comparando id's(?)
		Usuario DatosUsuario = contactos.get(nombre);
		
		//if (!chatsInd.contains(chat)) {
		ChatIndividual nuevoChat = new ChatIndividual(DatosUsuario.getMovil(),nombre);
		chatsInd.add(nuevoChat);
		//}
		return nuevoChat;
		
	}
	
	public void empezarChatGrupo (String nombre) {  //TODO:
		
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
	
	
	
	
	
	
}
	

