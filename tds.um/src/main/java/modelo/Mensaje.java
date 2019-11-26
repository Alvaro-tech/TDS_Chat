package modelo;

import java.time.LocalDate;

public class Mensaje {
	Usuario usuario;
	String texto;
	LocalDate fecha; //fecha en la que se crea el mensaje
	//Imagen emoticon;
	
	public Mensaje(Usuario usuario, String texto) {
		this.usuario = usuario;
		this.texto = texto;
		this.fecha = LocalDate.now(); //cuando creas el mensaje se le pone la fecha actual.
	}
	
	public String getTexto() {
		return texto;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	

}
