package modelo;

import java.util.LinkedList;

public class ChatIndividual extends Chat{
	
	//ES UN CONTACTO INDIVIDUAL con su lista de mensajes asociado.
	private String movil; //Movil del contacto
	private Usuario contacto; 
	
	//Constructor de un chat sin mensajes
	public ChatIndividual(String movil, String nombre, Usuario contacto) 
	{
		super(nombre);
		this.movil = movil; 
		this.contacto = contacto;
	}

	//Constructor de un chatInd ya con mensajes previos
	public ChatIndividual(String movil, String nombre, LinkedList<Mensaje> historial, Usuario contacto) 
	{
		super(nombre, historial, historial.getFirst()); //el ultimo mensaje sera siempre el primero en la lista
		this.movil = movil;
		this.contacto = contacto;
	}
	
	
	public ChatIndividual(String nom, String mo) {
		super(nom);
		this.movil = mo;
	}
	
	
	//--------Métodos get/set--------------
	public String getNombreContacto() {
		return contacto.getNombre();
	}
	
	public Usuario getContacto() {
		return contacto;
	}
	
	public Integer getUserId() {
		 return this.contacto.getId();
	}
	

	public String getMovil() {
		return this.movil;
	}

	public void setContacto(Usuario contacto) {
		this.contacto = contacto;
	}

}
