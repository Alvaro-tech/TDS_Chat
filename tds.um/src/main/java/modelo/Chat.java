package modelo;

import java.util.LinkedList;

public abstract class Chat{


	//CHAT == CONTACTO
	
    /*
    Chat es como un contacto: solo la info básica.
    Voy a escribir una propuesta de Chat pensando en este como contacto.

    */

    private String nombre;
    private LinkedList<Mensaje> historial;
    private int id;
    private Mensaje ultimoMensaje;

    public Chat(String nombre) 
    {
        this.nombre = nombre;
        this.historial = new LinkedList<Mensaje>();
    }

    public Chat(String nombre, LinkedList<Mensaje> historial, Mensaje m) {
        this.nombre = nombre;
        this.historial = historial;
        this.ultimoMensaje = m;
    }
    
    public void addMensajeHistorial(Mensaje m) {
    	//Siempre se añadirá el último mensaje al principio de la lista.
    	historial.addFirst(m);
    }

    public void setMensaje(LinkedList<Mensaje> mensaje) {
        this.historial = mensaje;
    }


    public LinkedList<Mensaje> getHistorial() {
        return historial;
    }

    public int getId() {
        return id;
    }
    
    public Mensaje getUltimoMensaje() {
        return this.ultimoMensaje;
    }
    
    public void setUltimoMensaje(Mensaje m) {
        this.ultimoMensaje = m;
    }

    public void setId(int nId) {
        this.id = nId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
	public void setHistorial(LinkedList<Mensaje> historial) {
		this.historial.addAll(historial);
		this.setUltimoMensaje(historial.getFirst());
	}

	@Override
	public String toString() {
		return "Chat [nombre=" + nombre + ", Fecha ultimoMensaje=" + ultimoMensaje.getFecha().toString() + "]";
	}
	
	
}