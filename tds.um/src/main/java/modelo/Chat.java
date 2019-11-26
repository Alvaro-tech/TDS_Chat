package modelo;


import java.util.LinkedList;

public abstract class Chat {
	String nombre;
	//histórico de mensajes del chat:
	LinkedList<Mensaje> mensaje;
	
	public String getNombre() {
		return nombre;
	}
	
	public LinkedList<Mensaje> getMensaje() {
		return mensaje;
	}
	
	//TODO: protected LinkedList<Mensaje> buscarMensaje();
	
	

}
