package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import beans.Entidad;
import beans.Propiedad;
import modelo.Usuario;

/**
 * 
 * Clase que implementa el Adaptador DAO concreto de Usuario para el tipo H2.
 * 
 */

//TODO: Hacer todas las propiedades necesarias para Usuario.
public final class AdaptadorUsuarioDAO implements IAdaptadorUsuarioDAO {
	
	private ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioDAO unicaInstancia = null;
	
	public static AdaptadorUsuarioDAO getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorUsuarioDAO();
		else
			return unicaInstancia;
	}
	
	public AdaptadorUsuarioDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	private Usuario entidadToUsuario(Entidad eUsuario) {
		
		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		String email = servPersistencia.recuperarPropiedadEntidad(eUsuario, "email");
		String fecha = servPersistencia.recuperarPropiedadEntidad(eUsuario, "fecha");
		String movil = servPersistencia.recuperarPropiedadEntidad(eUsuario, "movil");
		String clave = servPersistencia.recuperarPropiedadEntidad(eUsuario, "clave");
		String saludo = servPersistencia.recuperarPropiedadEntidad(eUsuario, "saludo");

		
		//public Usuario(String nombre, String email, LocalDate fecha, String movil, String clave)
		Usuario Usuario = new Usuario(nombre, email, fecha, movil, clave);
		Usuario.setId(eUsuario.getId());
		return Usuario;
	}
	
	private Entidad UsuarioToEntidad(Usuario Usuario) {
		Entidad  eUsuario = new Entidad();
		eUsuario.setNombre("Usuario"); 
	
		eUsuario.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("nombre", Usuario.getNombre()), 
						new Propiedad("fecha", Usuario.getFecha()),
						new Propiedad("movil", Usuario.getMovil()),
						new Propiedad("email", Usuario.getEmail()),
						new Propiedad("clave", Usuario.getClave()),
						new Propiedad("saludo", Usuario.getSaludo())
						))
				);
		return eUsuario;
	}
	
	public void create(Usuario Usuario) {
		Entidad  eUsuario = this.UsuarioToEntidad(Usuario);
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		Usuario.setId(eUsuario.getId());
		System.out.println(Usuario.getId()); //TODO: Quitar Luego
	}
	
	public boolean delete(Usuario Usuario) {
		Entidad eUsuario;
		eUsuario = servPersistencia.recuperarEntidad(Usuario.getId());
		return servPersistencia.borrarEntidad(eUsuario);
	}
	
	/**
	 * Permite que un Usuario modifique su perfil: email, password y movil
	 */
	public void updatePerfil(Usuario Usuario ) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(Usuario.getId());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "clave");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "clave",Usuario.getClave());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "email");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "email",Usuario.getEmail());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "movil");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "movil",Usuario.getMovil());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "saludo");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "saludo",Usuario.getSaludo());
	}
	
	public Usuario get(int id) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(id);
		return entidadToUsuario(eUsuario);
	}
	
	public List<Usuario> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades("Usuario");
		
		List<Usuario> Usuarios  = new LinkedList<Usuario>();
		for (Entidad eUsuario : entidades) {
			Usuarios.add(get(eUsuario.getId()));
		}
		return Usuarios;
	}

	public void updateSaludo(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		System.out.println(usuario.getId() + usuario.getMovil());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "saludo");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "saludo",usuario.getSaludo());
		
	}
	
	
}
