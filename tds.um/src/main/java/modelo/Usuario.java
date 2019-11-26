package modelo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Usuario {
	String nombre;
	String email;
	LocalDate fechaNacimiento;
	String movil;
	Usuario user; //tu propio usuario
	String clave; //contraseña
	//TODO: Imagen fotoPerfil; //libreria imagen
	boolean premium;
	//Lista de chats
	HashSet<Chat> chats = new HashSet<Chat>();
	//lista de contactos que tiene un usuario
	HashSet<Usuario> contactos = new HashSet<Usuario>();
	
	//constructor
	public Usuario(String nombre, String email, LocalDate fecha, String movil, String clave) {
		this.nombre = nombre;
		this.email = email;
		this.fechaNacimiento = fecha;
		this.movil = movil;
		this.clave = clave;
		this.premium = false;
		this.user = this;
	}
	
	
	//gets y sets
	public String getNombre() {
		return nombre;
	}
	
	public LocalDate getFechaNacimiento() {
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


	
	
	
	
	
	
}
