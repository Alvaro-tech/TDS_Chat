package modelo;

import java.util.LinkedList;

/**
 * Clase que corresponde a la entidad del dominio "Contacto" en el sistema.
 * @author Álvaro y Ana.
 *
 */
public class ChatIndividual extends Chat {

	// ES UN CONTACTO INDIVIDUAL con su lista de mensajes asociado.
	private String movil; // Movil del contacto
	private Usuario contacto;
	private int idChatLigado;

	/**
	 * Constructor de la clase ChatIndividual.
	 * 
	 * @param nombre
	 * @param movil
	 * @param contacto
	 */
	public ChatIndividual(String nombre, String movil, Usuario contacto) {
		super(nombre);
		this.movil = movil;
		this.contacto = contacto;
		this.idChatLigado = 0; //La persistencia no genera 0 como id, recien creado un chat,al no haber mensajes, no esta aún enlazado
	}

	/**
	 * Constructor de la clase ChatIndividual.
	 * 
	 * @param movil
	 * @param nombre
	 * @param historial
	 * @param contacto
	 */
	public ChatIndividual(String movil, String nombre, LinkedList<Mensaje> historial, Usuario contacto) {
		super(nombre, historial, historial.getFirst()); // el ultimo mensaje sera siempre el primero en la lista
		this.movil = movil;
		this.contacto = contacto;
	}
	
	/**
	 * Constructor de la clase ChatIndividual.
	 * @param nombre
	 * @param movil
	 */
	public ChatIndividual(String nombre, String movil) {
		super(nombre);
		this.movil = movil;
	}


	// ##################### GETS Y SETS #######################
	
	/**
	 * método get de ChatIndividual.
	 * @return nombre del contacto
	 */
	public String getNombreContacto() {
		return contacto.getNombre();
	}

	/**
	 * método get de ChatIndividual.
	 * @return Usuario contacto.
	 */
	public Usuario getContacto() {
		return contacto;
	}
	
	/**
	 * método get de ChatIndividual.
	 * @return Id del Usuario contacto.
	 */
	public Integer getUserId() {
		return this.contacto.getId();
	}
	
	/**
	 * método get de ChatIndividual.
	 * @return movil del contacto.
	 */
	public String getMovil() {
		return this.movil;
	}
	
	/**
	 * método set de ChatIndividual. Se utilizará para la persistencia.
	 * @param Usuario contacto
	 */
	public void setContacto(Usuario contacto) {
		this.contacto = contacto;
	}

	public int getIdChatLigado() {
		return idChatLigado;
	}

	public void setIdChatLigado(int idChatLigado) {
		this.idChatLigado = idChatLigado;
	}

	
}
