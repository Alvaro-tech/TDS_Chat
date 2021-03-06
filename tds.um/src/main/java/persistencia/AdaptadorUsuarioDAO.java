 package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import beans.Entidad;
import beans.Propiedad;
import modelo.Chat;
import modelo.ChatGrupo;
import modelo.ChatIndividual;
import modelo.Usuario;

/**
 * 
 * Clase que implementa el Adaptador DAO concreto de Usuario para el tipo H2.
 * 
 */
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
	
	private Usuario entidadToUsuario(Entidad eUsuario) { //3º
		
		//*-*-*-*-*-*-*-*-*-*--*-*-Objetos Fijos o unidireccionales
		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		String email = servPersistencia.recuperarPropiedadEntidad(eUsuario, "email");
		String fecha = servPersistencia.recuperarPropiedadEntidad(eUsuario, "fecha");
		String movil = servPersistencia.recuperarPropiedadEntidad(eUsuario, "movil");
		String clave = servPersistencia.recuperarPropiedadEntidad(eUsuario, "clave");
		String saludo = servPersistencia.recuperarPropiedadEntidad(eUsuario, "saludo");
		String premium = servPersistencia.recuperarPropiedadEntidad(eUsuario, "premium");
		String fotoP = servPersistencia.recuperarPropiedadEntidad(eUsuario, "fotoPerfil");
		String conversaciones = servPersistencia.recuperarPropiedadEntidad(eUsuario, "conversacionesAbiertas");
		//-*-*-*-*-*-*-*-*- Creo un Usuario Solo con estoss datos *-**-*-*-*-*-*-*-*-
		Usuario Usuario = new Usuario(nombre, email, fecha, movil, clave, saludo, fotoP, conversaciones); 
		Usuario.setId(eUsuario.getId());
		Usuario.setPremium(Boolean.valueOf(premium));
		
		//-*-*-*--*-*-*-*-*-*--*Y lo guardo en Pool para que conste:
		PoolDAO.getUnicaInstancia().addObjeto(Usuario.getId(), Usuario);
		
		//*-*-*-*-*--*-*-*-*-* Tratamiento de las propiedad bi-direccionales
		String chatInd = servPersistencia.recuperarPropiedadEntidad(eUsuario, "chatIndividual");
		String chatGroup = servPersistencia.recuperarPropiedadEntidad(eUsuario, "chatGrupo");
		String chatDesc = servPersistencia.recuperarPropiedadEntidad(eUsuario, "chatDesconocidos");
				
		Usuario.setGrupos(obtenerGruposDesdeId(chatGroup));
		Usuario.setChatIndividuales(obtenerChatIndividualesDesdeId(chatInd));
		
		try {
			Usuario.setChatsDesconocido(obtenerChatIndividualesDesdeId(chatDesc));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return Usuario;
	}


	//Función para crear la entidad Usuario
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
						new Propiedad("saludo", Usuario.getSaludo()),
						new Propiedad("premium", Usuario.isPremium()+""),
						new Propiedad("conversacionesAbiertas", Usuario.getConversacionesAbiertas()),
						new Propiedad("chatIndividual", obtenerIdChatIndividual(Usuario.getChatsInd())),
						new Propiedad("chatDesconocidos", obtenerIdChatIndividual(Usuario.getCHatsIndividualesYDesconocidos())),
						new Propiedad("chatGrupo", obtenerIdContactosSet(Usuario.getChatsGroup())),
						new Propiedad("fotoPerfil", Usuario.getFotoPerfil())
						))
				);
		return eUsuario;
	}


	public void create(Usuario Usuario) {
		//-*-*-*-*-*--*-* Uso de la pool
		
		Entidad eUsuario = new Entidad();
		
		//*-*-*-*-*-*-*-*-Control de si existe el objeto ya, para evitar volver a crearlo y haya repetidos
		boolean existe = true; 
		// Si la entidad está registrada no la registra de nuevo
		try {
			eUsuario = servPersistencia.recuperarEntidad(Usuario.getId());
		} catch (Exception e) {
			existe = false;
		}
		if (existe) return;
		
		
		
		
		//-*-*-*-*-*-*-*-*-*-*-*-*-*-*--
		
		
		//Si no habrá que registrar al usuario en el servidor de persistencia
		eUsuario = this.UsuarioToEntidad(Usuario);
		eUsuario = servPersistencia.registrarEntidad(eUsuario); //MUY IMPORTANTE QUE ESTO SEA ASI, AUNQUE SEA REDUNDANTE
		Usuario.setId(eUsuario.getId());
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
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "premium");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "premium", Boolean.toString(Usuario.isPremium()));
	}
	
	public Usuario get(int id) { //2º 
		//*-*-*--*-*-*- Pool para recuperar esos Objetos con solo propiedad Unidireccionales que les falta la bidriec
		// Si la entidad está en el pool la devuelve directamente
		if (PoolDAO.getUnicaInstancia().contiene(id)) {
			return (Usuario) PoolDAO.getUnicaInstancia().getObjeto(id);
		}
		
		Entidad eUsuario = servPersistencia.recuperarEntidad(id);
		Usuario usuarioAux = entidadToUsuario(eUsuario);
		//*-*-*-*-*-*-*-*-*-*-*--*-*- IMPORTANTE:añadir el Usuario al pool antes de llamar a otros
		// adaptadores
		PoolDAO.getUnicaInstancia().addObjeto(id, usuarioAux);
				
		return usuarioAux;
	}
	
	public List<Usuario> getAll() { //1º -> Llamado por el catálogo
		List<Entidad> entidades = servPersistencia.recuperarEntidades("Usuario");
		
		List<Usuario> Usuarios  = new LinkedList<Usuario>();
		for (Entidad eUsuario : entidades) {
			Usuarios.add(get(eUsuario.getId()));
		}
		return Usuarios;
	}

	public void updateSaludo(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "saludo");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "saludo",usuario.getSaludo());		
	}
	
	public void updateFoto(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "fotoPerfil");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "fotoPerfil",usuario.getFotoPerfil());		
	}
	
	public void updateConversaciones(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "conversacionesAbiertas");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "conversacionesAbiertas",usuario.getConversacionesAbiertas());
	}
	
	public void updatePremium(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "premium");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "premium", Boolean.toString(usuario.isPremium()));
	}

	
	public void updateChats(Usuario usuario, Chat newChat) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		
		switch(newChat.getClass().getSimpleName())
		{
		   case "ChatIndividual" :
			   servPersistencia.eliminarPropiedadEntidad(eUsuario, "chatIndividual");
			   servPersistencia.anadirPropiedadEntidad(eUsuario,"chatIndividual", obtenerIdChatIndividual(usuario.getChatsInd()));
			   break;
			   
		   case "ChatGrupo" :
			   servPersistencia.eliminarPropiedadEntidad(eUsuario, "chatGrupo");
			   servPersistencia.anadirPropiedadEntidad(eUsuario, "chatGrupo", obtenerIdContactosSet(usuario.getChatsGroup()));
			   break;
		}
		
	}
	
	public void updateChatsDesconocidos(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
			   servPersistencia.eliminarPropiedadEntidad(eUsuario, "chatDesconocidos");
			   servPersistencia.anadirPropiedadEntidad(eUsuario,"chatDesconocidos", obtenerIdChatIndividual(usuario.getChatsDesconocido()));
		
		
	}
	
	// -------------------Funciones auxiliares-----------------------------
	//TODO: Estas funciones existen aqui porque son necesarias, pero habria que mover su funcionamiento (código) a sus adaptadores pertinentes

		
		
		
		private HashSet<ChatIndividual> obtenerChatIndividualesDesdeId(String chatsInd) {
			HashSet<ChatIndividual> chats = new HashSet<ChatIndividual>();

			StringTokenizer tok = new StringTokenizer(chatsInd, " ");
			while (tok.hasMoreTokens()) {
				String id = (String) tok.nextElement();
				ChatIndividual aux = AdaptadorChatIndividualDAO.getUnicaInstancia().get(Integer.valueOf(id));
				chats.add(aux);
			}

			return chats;
		}

		private HashSet<ChatGrupo> obtenerGruposDesdeId(String chatsg) {
			HashSet<ChatGrupo> grupos = new HashSet<ChatGrupo>();

			StringTokenizer tok = new StringTokenizer(chatsg, " ");
			while (tok.hasMoreTokens()) {
				String id = (String) tok.nextElement();
				ChatGrupo aux = AdaptadorChatGrupoDAO.getUnicaInstancia().get(Integer.valueOf(id));
				grupos.add(aux);
			}

			return grupos;
		}
		
		
	//Funciones para trabajar con HashSet's
	//TODO: Como no tener tantas funciones puto iguales

		private String obtenerIdChatIndividual(HashSet<ChatIndividual> listaUsuario) {
			String aux = "";
			for(ChatIndividual iterador : listaUsuario) {
				aux +=  iterador.getId() + " ";
			}
			return aux.trim(); 
		}
		
		
		private String obtenerIdContactosSet(HashSet<ChatGrupo> chatsGroup) {
			String aux = "";
			for(ChatGrupo iterador : chatsGroup) {
				aux +=  iterador.getId() + " ";
			}
			return aux.trim(); 
		}
		


		
		
	
}
