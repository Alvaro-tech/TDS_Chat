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
	
	public String getId() {
		 Integer id = this.contacto.getId();
		 return id.toString();
	}
	

	public String getmovil() {
		return contacto.getMovil();
	}
	
	/*
	//Un chat individual no va a usarla en un principio, pero debe incluir la función, por ser abstracta en la del padre
	@Override
	protected LinkedList<Mensaje> filtroUsuario(LinkedList<Mensaje> listaAfiltrar) {
		return listaAfiltrar;
	}
	*/
	
}
