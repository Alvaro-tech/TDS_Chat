package modelo;

import java.time.LocalDate;

public class Mensaje {
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
	
	public Mensaje(Usuario u, String texto) {
		this.texto = texto;
		this.usuarioAct = u;
		this.fecha =  LocalDate.now();
	}
	
	@Override
	public String toString() {
		return "Mensaje= " + this.texto;
	}

	public Mensaje(String texto, String fecha) {
		this.texto = texto;
		this.fecha =  LocalDate.parse(fecha);
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

	public void setEmisor(Usuario emisor) {
		this.usuarioAct = emisor;	
	}

	public void setReceptor(ChatIndividual receptor) {
		this.contacto = receptor;
	}


}
