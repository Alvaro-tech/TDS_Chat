package modelo;

import java.util.LinkedList;
import java.util.List;

public class ChatIndividual extends Chat{
	
	//ES UN CONTACTO INDIVIDUAL con su lista de chats
	private String movil;
	
	public ChatIndividual(String movil, String nombre) 
	{
		super(nombre);
		this.movil = movil;

	}

	
	public String getNombreContacto() {
		return nombre;
	}

	/*
	//Un chat individual no va a usarla en un principio, pero debe incluir la función, por ser abstracta en la del padre
	@Override
	protected LinkedList<Mensaje> filtroUsuario(LinkedList<Mensaje> listaAfiltrar) {
		return listaAfiltrar;
	}
	*/
	
	
	
	
	
	
	
	
}
