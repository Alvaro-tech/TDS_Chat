package modelo;

import java.time.LocalDate;

//no... darle una vuelta
public class Mensaje {
	//debería tener un emisor y receptor asociados.
	private Usuario usuarioAct; //emisor
	private ChatIndividual contacto; //receptor
	
	private String texto;
	private LocalDate fecha; //fecha en la que se crea el mensaje
	private int id;
	//Imagen emoticon;
	
	public Mensaje(Usuario u, ChatIndividual c, String texto) { //Entendiendo usuario como número de teléfono
		this.usuarioAct = u;
		this.contacto = c;
		this.texto = texto;
		this.fecha = LocalDate.now(); //cuando creas el mensaje se le pone la fecha actual.
	}
	
	public Mensaje(Usuario u, ChatIndividual c, String texto, String fecha) { 
		this.usuarioAct = u;
		this.contacto = c;
		this.texto = texto;
		this.fecha = LocalDate.parse(fecha); //cuando creas el mensaje se le pone la fecha actual.
	}
	
	public int getId() {
		return id;
	}
	
	public String getTexto() {
		return texto;
	}
	public LocalDate getFecha() {
		return fecha;
	}

	public ChatIndividual getReceptor() {
		return contacto;
	}
	
	public Usuario getEmisor() {
		return this.usuarioAct;
	}

	public void setId(int id) {
		this.id = id;
	}


}
