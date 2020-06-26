package modelo;

//CHAT == CONTACTO
import java.util.LinkedList;

public abstract class Chat {

	/*
	Chat es como un contacto: solo la info básica.
	Voy a escribir una propuesta de Chat pensando en este como contacto.
	
	*/
	
	protected String nombre;
	private LinkedList<Mensaje> historial;
	
	public Chat(String nombre) 
	{
		this.nombre = nombre;
		this.historial = new LinkedList<Mensaje>();
	}

	public void setMensaje(LinkedList<Mensaje> mensaje) {
		this.historial = mensaje;
	}
	
	
	public LinkedList<Mensaje> getHistorial() {
		return historial;
	}

}
