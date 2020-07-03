package modelo;

import java.time.LocalDate;

/**
 * Clase que representa los mensajes en el modelo del sistema.
 * @author Álvaro y Ana.
 *
 */
public class Mensaje {
	
	private Usuario usuarioAct; //emisor
	private ChatIndividual contacto; //receptor
	private String texto;
	private LocalDate fecha; //fecha en la que se crea el mensaje
	private int id;
	//Imagen emoticon;
	
	/**
	 * Constructor de la calse mensaje.
	 * @param Usuario u, emisor
	 * @param ChatIndividual c (contacto), receptor
	 * @param texto
	 */
	public Mensaje(Usuario u, ChatIndividual c, String texto) { //Entendiendo usuario como número de teléfono
		this.usuarioAct = u;
		this.contacto = c; 
		this.texto = texto;
		this.fecha = LocalDate.now(); //cuando creas el mensaje se le pone la fecha actual.
	}
	
	/**
	 * Constructor de la calse mensaje.
	 * @param Usuario u, emisor
	 * @param ChatIndividual c (contacto), receptor
	 * @param texto
	 * @param fecha
	 */
	public Mensaje(Usuario u, ChatIndividual c, String texto, String fecha) { 
		this.usuarioAct = u;
		this.contacto = c; 
		this.texto = texto;
		this.fecha = LocalDate.parse(fecha); //cuando creas el mensaje se le pone la fecha actual.
	} 
	
	/**
	 * Constructor de la calse mensaje.
	 * @param texto
	 * @param fecha
	 */
	public Mensaje(String texto, String fecha) {
		this.texto = texto;
		this.fecha =  LocalDate.parse(fecha);
	}
	
	/**
	 * Constructor de la calse mensaje. Crea mensajes de grupo.
	 * @param Usuario u
	 * @param texto
	 */
	public Mensaje(Usuario u, String texto) {
		this.usuarioAct = u;
		this.contacto = null; //ESTO QUIERE DECIR QUE ES UN MENSAJE DE GRUPO.
		this.texto = texto;
		this.fecha = LocalDate.now();
	}
	

	// ##################### GETS Y SETS #######################

	/**
	 * Método get de Mensaje.
	 * @return ID del mensaje
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Método get de Mensaje.
	 * @return texto del mensaje
	 */
	public String getTexto() {
		return texto;
	}
	
	/**
	 * Método get de Mensaje.
	 * @return LocalDate fecha
	 */
	public LocalDate getFecha() {
		return fecha;
	}
	
	/**
	 * Método get de Mensaje.
	 * @return ChatIndividual receptor
	 */
	public ChatIndividual getReceptor() {
		return contacto;
	}
	
	/**
	 * Método get de Mensaje.
	 * @return Usuario emisor
	 */
	public Usuario getEmisor() {
		return this.usuarioAct;
	}

	/**
	 * Método set de Mensaje.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Método set de Mensaje.
	 * @param Usuario emisor
	 */
	public void setEmisor(Usuario emisor) {
		this.usuarioAct = emisor;	
	}

	/**
	 * Método set de Mensaje.
	 * @param ChatIndividual receptor
	 */
	public void setReceptor(ChatIndividual receptor) {
		this.contacto = receptor;
	}


	// ##################### FUNCIONALIDAD #######################
	
	/**
	 * Funcion para pasar un Mensaje a un String.
	 */
	@Override
	public String toString() {
		return "Mensaje= " + this.texto;
	}
	
	/**
	 * comprueba que un mensaje ha sido enviado entre dos fechas.
	 * @param LocalDate fi, fecha de inicio
	 * @param LocalDate ff, fecha final
	 * @return
	 */
	public boolean esEntreFechas(LocalDate fi, LocalDate ff) {
		return fi.isEqual(this.fecha) || fi.isBefore(this.fecha) || ff.isEqual(this.fecha) || ff.isAfter(this.fecha);
	}


}
