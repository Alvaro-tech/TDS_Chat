package modelo;


import java.util.LinkedList;
import java.util.List;

public abstract class Chat {
	private String nombre;
	private int id;
	//histórico de mensajes del chat:
	 private LinkedList<Mensaje> mensaje;
	
	public Chat(String nombre, int id) {
		this.nombre = nombre;
		this.id = id;
		this.mensaje = new LinkedList<Mensaje>();
	}
	
	public Chat(String nombre, int id, LinkedList<Mensaje> mensaje) {
		this.nombre = nombre;
		this.id = id;
		this.mensaje = mensaje;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Mensaje> getMensaje() {
		return mensaje;
	}
	

	//El usuario ese es el nombre que tu le has puesto al suso dicho
	public LinkedList<Mensaje> buscarMensajeIncompleto(String texto, String fecha){ //Habra que hacer un parse a LocalDate para trabajar con ella
		LinkedList<Mensaje> returnlist = new LinkedList<Mensaje>();
		
		
		return returnlist;
	}
	
	public LinkedList<Mensaje> buscarMensajeCompleto(){ //TODO: Es feo que el chat individual pueda esto, pero bueno.
		
		//ababnjanfjasd
		
		//nkjcnsdjcsdk
		
		// abstact filtrar usuario
		
		return null;
	}
	
	protected abstract LinkedList<Mensaje> filtroUsuario(LinkedList<Mensaje> listaAfiltrar);
	

}
