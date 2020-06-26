package modelo;

import java.util.LinkedList;
import java.util.List;

public class ChatIndividual extends Chat{
	
	//ES UN CONTACTO INDIVIDUAL con su lista de mensajes asociado.
	private String movil;
	private Usuario contacto; 
	

	public ChatIndividual(String movil, String nombre, Usuario contacto) 
	{
		super(nombre);
		this.movil = movil;
		this.contacto = contacto;
	}

	
	public String getNombreContacto() {
		return nombre;
	}

	public Usuario getContacto() {
		return contacto;
	}
	
	public Integer getId() {
		 return this.contacto.getId();
	}
	

	public String getmovil() {
		return contacto.getMovil();
	}

}
