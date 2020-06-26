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
	

	//################Funcionalidad##########
	//TODO: busqueda de mensajes hacer que sea con streams y abstract methods
	
	
	/*
	private String movil; //Numero de telefono del receptor
	private int id;
	//histórico de mensajes del chat:
	private LinkedList<Mensaje> historial;
	
	 
	//Constructor ->
	public Chat(String movil) {
		this.movil = movil;
		//No, se deberá hacer una recuperacion de los chats de la bbdd
		this.historial = new LinkedList<Mensaje>();
	}
	
	
	//No me gusta un pelo esta funcion
	public void setmovil(String movil) {
		this.movil = movil;
	}
	
	
	//rt al anterior comentario
	public void setId(int id) {
		this.id = id;
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
	*/
}
