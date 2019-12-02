package modelo;

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
	HashSet<Chat> chats = new HashSet<Chat>();
	//lista de contactos que tiene un usuario
	HashSet<Usuario> contactos = new HashSet<Usuario>();
	Integer id;
	String login;
	String saludo;
	
	//constructor
	public Usuario(String nombre, String email, String fecha, String movil, String clave) { //TODO: ID??
		this.nombre = nombre;
		this.email = email;
		this.fechaNacimiento = fecha;
		this.movil = movil;
		this.clave = clave;
		this.premium = false;
		this.user = this; //?
	//	this.id = id;
		this.login=movil;
		this.saludo="Hey there, I'm using TDSchat.";
		
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


	public String getLogin() {
		return login;
	}
	
	public String getSaludo() {
		return saludo;
	}
	
	public String setSaludo(String saludo) {
		return this.saludo = saludo;
	}
	
	
}
