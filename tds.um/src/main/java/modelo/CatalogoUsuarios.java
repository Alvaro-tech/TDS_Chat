package modelo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import persistencia.DAOException;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorUsuarioDAO;


/**
 * El catalogo mantiene los objetos en memoria, en una tabla hash
 * para mejorar el rendimiento. Esto no se podria hacer en una base de
 * datos con un numero grande de objetos. En ese caso se consultaria
 * directamente la base de datos
 * @author Álvaro y Ana.
 *
 */
public class CatalogoUsuarios { 
	private Map<String,Usuario> Usuarios;  //Los usuarios se guardan mediante Teléfono Móvil
	private static CatalogoUsuarios unicaInstancia = new CatalogoUsuarios();
	
	private FactoriaDAO dao;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	
	/**
	 * Constructor del catálogo.
	 */
	private CatalogoUsuarios() {
		try {
  			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
  			adaptadorUsuario = dao.getUsuarioDAO();
  			Usuarios = new HashMap<String,Usuario>();
  			this.cargarCatalogo();
  		} catch (DAOException eDAO) {
  			eDAO.printStackTrace();
  		}
	}
	
	/**
	 * Método get del Catalogo de usuarios. Patrón Singleton.
	 * @return única instancia de este.
	 */
	public static CatalogoUsuarios getUnicaInstancia(){
		return unicaInstancia;
	}
	
	/**
	 * devuelve todos los Usuarios
	 * @returnList<Usuario>
	 */
	public List<Usuario> getUsuarios(){
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		for (Usuario c:Usuarios.values()) 
			lista.add(c);
		return lista;
	}
	
	/**
	 * Método get del Catálogo de usuarios
	 * @param codigo (ID)
	 * @return Usuario u si está en el catálogo, null en otro caso.
	 */
	public Usuario getUsuario(int codigo) {
		for (Usuario c:Usuarios.values()) {
			if (c.getId()==codigo) return c;
		}
		return null;
	}
	
	/**
	 * Método get del Catálogo de usuarios
	 * @param movil
	 * @return Usuario u si está en el catálogo, null en otro caso.
	 */
	public Usuario getUsuario(String movil) {
		return Usuarios.get(movil); 
	}
	
	/**
	 * Método para añadir un Usuario cli al catálogo.
	 * @param Usuario cli
	 */
	public void addUsuario(Usuario cli) {
		Usuarios.put(cli.getMovil(),cli);
	}
	
	/**
	 * Método para eliminar un Usuario cli al catálogo.
	 * @param cli
	 */
	public void removeUsuario (Usuario cli) {
		Usuarios.remove(cli.getMovil());
	}
	
	/**
	 * Recupera todos los Usuarios para trabajar con ellos en memoria
	 */
	private void cargarCatalogo() throws DAOException {
		 List<Usuario> UsuariosBD = adaptadorUsuario.getAll();
		 for (Usuario cli: UsuariosBD) 
			     Usuarios.put(cli.getMovil(),cli);
	}	
	
}
