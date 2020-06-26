package controlador;

import modelo.CatalogoUsuarios;
import modelo.Usuario;
import persistencia.AdaptadorUsuarioDAO;
import persistencia.DAOException;
import persistencia.FactoriaDAO;

public class ControladorUsuarios {
	
	private Usuario usuarioActual;
	private static ControladorUsuarios unicaInstancia;
	private FactoriaDAO factoria;
	
	private ControladorUsuarios() {
		usuarioActual = null;
		try {
			factoria = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}	
	}
	
	public static ControladorUsuarios getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new ControladorUsuarios();
		return unicaInstancia;
	}
	
	public Usuario getusuarioActual() {
		return usuarioActual;
	}
	
	public String getIdUsuarioActual() {
		return usuarioActual.getClave();
	}
	
	public String getNombreUsuarioActual() {
		return usuarioActual.getNombre();
	}
	
	public boolean esUsuarioRegistrado(String movil) {
		return CatalogoUsuarios.getUnicaInstancia().getUsuario(movil)!=null;
	}
	
	public boolean loginUsuario(String movil,String password) {
		Usuario Usuario = CatalogoUsuarios.getUnicaInstancia().getUsuario(movil);
		if (Usuario != null && Usuario.getClave().equals(password)) {
				this.usuarioActual = Usuario;
				return true;
		}
		return false;
	}
	
	public boolean registrarUsuario(String nombre, String email, 
									String fecha, String movil,
									String clave) {

			if (esUsuarioRegistrado(movil)) return false;
			//Usuario(String nombre, String email, String fecha, String movil, String clave)
			Usuario Usuario = new Usuario(nombre,email,fecha,movil, clave); //TODO: Arreglar esto para que se ponga el estado por defecto y no se quede en blanco
			
			AdaptadorUsuarioDAO UsuarioDAO = (AdaptadorUsuarioDAO) factoria.getUsuarioDAO(); /*Adaptador DAO para almacenar el nuevo Usuario en la BD*/
			UsuarioDAO.create(Usuario);
			
			CatalogoUsuarios.getUnicaInstancia().addUsuario(Usuario);
			return true;
	}
	
	//No se si esta funcion sirve para algo.
	public boolean borrarUsuario(Usuario Usuario) {
		if (!esUsuarioRegistrado(Usuario.getMovil())) return false;
		
		AdaptadorUsuarioDAO UsuarioDAO = (AdaptadorUsuarioDAO) factoria.getUsuarioDAO();  /*Adaptador DAO para borrar el Usuario de la BD*/
		UsuarioDAO.delete(Usuario);
		
		CatalogoUsuarios.getUnicaInstancia().removeUsuario(Usuario);
		return true;
	}

	public void updateSaludo(Usuario usuario, String saludo) {
		usuario.setSaludo(saludo);
		AdaptadorUsuarioDAO UsuarioDAO = (AdaptadorUsuarioDAO) factoria.getUsuarioDAO();
		UsuarioDAO.updateSaludo(usuario);		
		
	}
	
	public void updateFoto(Usuario usuario, String foto) {
		usuario.setFotoPerfil(foto);
		AdaptadorUsuarioDAO UsuarioDAO = (AdaptadorUsuarioDAO) factoria.getUsuarioDAO();
		UsuarioDAO.updateFoto(usuario);		
		
	}
	// ##################### Otra funcionalidad #######################
	
	public void addUsuario (String nombre, String movil) {
		Usuario contacto = CatalogoUsuarios.getUnicaInstancia().getUsuario(movil);
		System.out.println("COntacto a agregar, buscado en el mapa: " + contacto.getMovil());
		usuarioActual.agregarContacto(nombre, contacto);
		AdaptadorUsuarioDAO UsuarioDAO = (AdaptadorUsuarioDAO) factoria.getUsuarioDAO();
		UsuarioDAO.updateContactos(ControladorUsuarios.getUnicaInstancia().usuarioActual);	
	}
	
	public void mostrarUsuario() {
		System.out.println("Mostrar Usuarios:");
		for(String u : usuarioActual.getContactos().keySet()) {
			System.out.println("# -> Nombre: " + u + " Movil " + usuarioActual.getContactos().get(u).getMovil());
		}
	}
}
