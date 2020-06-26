package modelo;

import java.util.LinkedList;

public class ChatIndividual extends Chat{
	
	//ES UN CONTACTO INDIVIDUAL con su lista de mensajes asociado.
	private String movil;
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
		super(nombre, historial);
		this.movil = movil;
		this.contacto = contacto;
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

}
