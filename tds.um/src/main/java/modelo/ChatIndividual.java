package modelo;

import java.util.LinkedList;
import java.util.List;

public class ChatIndividual extends Chat{
	
	String nombre;
	
	public ChatIndividual(String movil, String nombre) 
	{
		super(movil);
		this.nombre = nombre;

	}

	
	public String getNombre() {
		return nombre;
	}

	//Un chat individual no va a usarla en un principio, pero debe incluir la función, por ser abstracta en la del padre
	@Override
	protected LinkedList<Mensaje> filtroUsuario(LinkedList<Mensaje> listaAfiltrar) {
		return listaAfiltrar;
	}
	
	
	
	
	
	
	
	
}
