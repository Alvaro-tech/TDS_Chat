package modelo;


import java.util.LinkedList;
import java.util.List;

public abstract class Chat {
	
	//-----Tener en cuenta a la hora de dar los atributos que los usuarios guardan los contactos en un Map <Nombre,Usuario>
	
	private String movil; //Numero de telefono del receptor
	private int id;  //solo lo toca la bbdd
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
	
	public LinkedList<Mensaje> buscarMensajeCompleto(){ //TODO: Hace uso de los filtros por separado para poder hacer todas las combinciones
		
		//Lamar a la funcion buscarMensajeCOmpleto
		
		// abstact filtrar usuario
		
		return null;
	}
	
	protected abstract LinkedList<Mensaje> filtroUsuario(LinkedList<Mensaje> listaAfiltrar);
	//TODO: Poner el resto de filtros
	
	public void limpiarHistorial() {
		this.historial = new LinkedList<Mensaje>();
	}
	
	public void almacenarMensaje(Mensaje mensaje) {
		historial.add(mensaje);
	}

}
