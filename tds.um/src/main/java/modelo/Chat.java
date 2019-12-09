package modelo;


import java.util.LinkedList;
import java.util.List;

public abstract class Chat {
	private String movil; //Numero de telefono del receptor
	private int id;
	//histórico de mensajes del chat:
	 private LinkedList<Mensaje> historial;
	
	 
	 //Constructor ->
	public Chat(String movil) {
		this.movil = movil;
		this.historial = new LinkedList<Mensaje>();
	}
	
	
	public String getmovil() {
		return movil;
	}
	public void setmovil(String movil) {
		this.movil = movil;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LinkedList<Mensaje> getHistorial() {
		return historial;
	}

	public void setMensaje(LinkedList<Mensaje> mensaje) {
		this.historial = mensaje;
	}
	
	//################Funcionalidad##########
	//El usuario ese es el nombre personal que tu le has puesto al suso dicho
	public LinkedList<Mensaje> buscarMensajeIncompleto(String texto, String fecha){ //Habra que hacer un parse a LocalDate para trabajar con ella
		LinkedList<Mensaje> returnlist = new LinkedList<Mensaje>();
		
		
		return returnlist;
	}
	
	public LinkedList<Mensaje> buscarMensajeCompleto(){ //TODO: Es feo que el chat individual pueda esto, pero bueno.
		
		//Lamar a la funcion buscarMensajeCOmpleto
		
		// abstact filtrar usuario
		
		return null;
	}
	
	protected abstract LinkedList<Mensaje> filtroUsuario(LinkedList<Mensaje> listaAfiltrar);
	
	public void limpiarHistorial() {
		this.historial = new LinkedList<Mensaje>();
	}
	
	public void almacenarMensaje(Mensaje mensaje) {
		historial.add(mensaje);
	}

}
