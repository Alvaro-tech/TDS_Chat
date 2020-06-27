package modelo;

//CHAT == CONTACTO
import java.util.LinkedList;

public abstract class Chat {

    /*
    Chat es como un contacto: solo la info básica.
    Voy a escribir una propuesta de Chat pensando en este como contacto.

    */

    private String nombre;
    private LinkedList<Mensaje> historial;
    private int id;

    public Chat(String nombre) 
    {
        this.nombre = nombre;
        this.historial = new LinkedList<Mensaje>();
    }

    public Chat(String nombre, LinkedList<Mensaje> historial) {
        this.nombre = nombre;
        this.historial = historial;
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
	}
}