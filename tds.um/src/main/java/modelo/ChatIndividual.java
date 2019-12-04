package modelo;

import java.util.List;

public class ChatIndividual extends Chat{
	
	String movil;
	
	public ChatIndividual(String nombre, int id, List<Mensaje> mensaje, String movil) 
	{
		super(nombre, id, mensaje);
		this.movil = movil;

	}
	
	
	
	
	
	
	
	
}
