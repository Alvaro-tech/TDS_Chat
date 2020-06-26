package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import modelo.ChatGrupo;
import modelo.ChatIndividual;
import modelo.Mensaje;
import modelo.Usuario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public final class AdaptadorChatGrupoDAO implements IAdaptadorChatGrupoDAO {

	private ServicioPersistencia servPersistencia;
	private static AdaptadorChatGrupoDAO unicaInstancia = null;

	public static IAdaptadorChatGrupoDAO getUnicaInstancia() {
		if (unicaInstancia == null)
			return new AdaptadorChatGrupoDAO();
		else
			return unicaInstancia;
	}

	public AdaptadorChatGrupoDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	/**
	 * Funcion para devolver
	 **/
	private Entidad ChatGrupoToEntidad(ChatGrupo grupo) {
		Entidad e = new Entidad();
		e.setNombre("ChatGrupo");
		
		e.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList( 
						new Propiedad("nombre", grupo.getNombre()), 
						new Propiedad("historial", obtenerIdMensajes(grupo.getHistorial())),
						new Propiedad("miembros", obtenerMiembros(grupo.getMiembros())),
						new Propiedad("administradores", obtenerAdministradores(grupo.getAdministradores()))
						))
				);
		return e;
		
	}
	
	
	
	@Override
	public void create(ChatGrupo grupo) {
		// TODO Auto-generated method stub
		
	}
	
	//TODO: HACER!!!!
	/*
	private ChatGrupo entidadToChatGrupo(Entidad eGrupo) {
		String nombreg = servPersistencia.recuperarPropiedadEntidad(eGrupo, "nombre");
	}
	*/

	@Override
	public boolean delete(ChatGrupo grupo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ChatGrupo get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChatGrupo> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateNombre(ChatGrupo grupo) {
		// TODO Auto-generated method stub
		
	}
	
	//#########FUNCIONES AUXILIARES DE CHATGRUPOTOENTIDAD():############
		/**
		 * Funcion para, del historial de mensajes del grupo
		 * obtener sus ids como strings.
		 * Este historial está compuesto de mensajes.
		 **/
		private String obtenerIdMensajes(LinkedList<Mensaje> mens) {
			String aux = "";
			for(Mensaje iterador : mens) {
				aux +=  iterador.getId() + " ";
			}
			return aux.trim(); 
		}
		
		/**
		 * Funcion para, de los miembros del grupo
		 * obtener sus ids como strings.
		 * Los miembros son chatsIndividuales (Contactos individuales)
		 **/
		private String obtenerMiembros(LinkedList<ChatIndividual> miembros) {
			String aux = "";
			for(ChatIndividual iterador : miembros) {
				aux +=  iterador.getId() + " ";
			}
			return aux.trim(); 
		}
		
		/**
		 * Funcion para, de los administradores del grupo
		 * obtener sus ids como strings.
		 * Estos son usuarios.
		 **/
		private String obtenerAdministradores(HashSet<Usuario> admins) {
			String aux = "";
			for(Usuario iterador : admins) {
				aux +=  iterador.getId() + " ";
			}
			return aux.trim(); 
		}
		

}
