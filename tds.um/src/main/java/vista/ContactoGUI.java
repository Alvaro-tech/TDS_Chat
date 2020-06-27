package vista;

import modelo.Usuario;

public class ContactoGUI {
	
	private Usuario usuario;
	private String nombre;
	
	public ContactoGUI(String nombre, Usuario usuario) {
		this.nombre = nombre;
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
