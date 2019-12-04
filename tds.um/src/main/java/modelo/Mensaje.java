package modelo;

import java.time.LocalDate;

public class Mensaje {
	String usuario;
	String texto;
	LocalDate fecha; //fecha en la que se crea el mensaje
	int id;
	//Imagen emoticon;
	
	public Mensaje(String usuario, String texto) { //Entendiendo usuario como número de teléfono
		this.usuario = usuario;
		this.texto = texto;
		this.fecha = LocalDate.now(); //cuando creas el mensaje se le pone la fecha actual.
	}
	
	public Mensaje(String usuario, String texto, String fecha) { //Entendiendo usuario como número de teléfono
		this.usuario = usuario;
		this.texto = texto;
		this.fecha = LocalDate.parse(fecha); //cuando creas el mensaje se le pone la fecha actual.
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getTexto() {
		return texto;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	

}
