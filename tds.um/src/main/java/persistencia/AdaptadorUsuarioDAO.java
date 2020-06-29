 package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
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

//TODO: Hacer todas las propiedades necesarias para Usuario (los conjuntos de chat y el premium).
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
		String fotoP = servPersistencia.recuperarPropiedadEntidad(eUsuario, "fotoPerfil");
		
		//-*-*-*-*-*-*-*-*- Creo un Usuario Solo con estoss datos *-**-*-*-*-*-*-*-*-
		Usuario Usuario = new Usuario(nombre, email, fecha, movil, clave, saludo, fotoP); 
		Usuario.setId(eUsuario.getId());
		//-*-*-*--*-*-*-*-*-*--*Y lo guardo en Pool para que conste:
		PoolDAO.getUnicaInstancia().addObjeto(Usuario.getId(), Usuario);
		
		//*-*-*-*-*--*-*-*-*-* Tratamiento de las propiedad bi-direccionales
		String contactos = servPersistencia.recuperarPropiedadEntidad(eUsuario, "contactos");
		//String chatInd = servPersistencia.recuperarPropiedadEntidad(eUsuario, "chatIndividual");
		//String chatGroup = servPersistencia.recuperarPropiedadEntidad(eUsuario, "chatGrupo");
				
		System.out.println("En entidadtoUsuario: " + contactos);
		Usuario.setContactos(obtenerContactosMapDesdeId(contactos));
		//Usuario.setGrupos(obtenerGruposDesdeId(chatGroup));
		//Usuario.setChatIndividuales(obtenerChatIndividualesDesdeId(chatInd));
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
						new Propiedad("contactos", obtenerIdContactosHash(Usuario.getContactos())),
						//new Propiedad("chatIndividual", obtenerIdChatIndividual(Usuario.getChatsInd())),
						//new Propiedad("chatGrupo", obtenerIdContactosSet(Usuario.getChatsGroup())),
						new Propiedad("fotoPerfil", Usuario.getFotoPerfil())
						))
				);
		return eUsuario;
	}


	public void create(Usuario Usuario) {
		//-*-*-*-*-*--*-* Uso de la pool
		
		Entidad eUsuario;
		
		//*-*-*-*-*-*-*-*-Control de si existe el objeto ya, para evitar volver a crearlo y haya repetidos
		boolean existe = true; 
		// Si la entidad está registrada no la registra de nuevo
		try {
			System.out.println("111Entre en en primer step de la pool");
			eUsuario = servPersistencia.recuperarEntidad(Usuario.getId());
			System.out.println("Entre en en primer step de la pool");
		} catch (Exception e) {
			existe = false;
		}
		if (existe) return;
		
		
		
		
		//-*-*-*-*-*-*-*-*-*-*-*-*-*-*--
		
		
		//Si no habrá que registrar al usuario en el servidor de persistencia
		System.out.println("*-*-*-*--Estoy en el create");
		eUsuario = this.UsuarioToEntidad(Usuario);
		servPersistencia.registrarEntidad(eUsuario);
		Usuario.setId(eUsuario.getId());
		System.out.println("Usuario registrado con la id: " + Usuario.getId()); //TODO: Quitar Luego
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
	
	public Usuario get(int id) { //2º 
		//*-*-*--*-*-*- Pool para recuperar esos Objetos con solo propiedad Unidireccionales que les falta la bidriec
		System.out.println("entre en el get con el id: " + id);
		// Si la entidad está en el pool la devuelve directamente
		if (PoolDAO.getUnicaInstancia().contiene(id)) {
			System.out.println("Supuestamente lo he cogio de la pool");
			return (Usuario) PoolDAO.getUnicaInstancia().getObjeto(id);
		}
		
		
		//-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*--
		
		
		Entidad eUsuario = servPersistencia.recuperarEntidad(id);
		Usuario usuarioAux = entidadToUsuario(eUsuario);
		//*-*-*-*-*-*-*-*-*-*-*--*-*- IMPORTANTE:añadir el Usuario al pool antes de llamar a otros
				// adaptadores
		System.out.println("En teoria lo he guardado de la pool");
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
	
	public void updateContactos(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());

		servPersistencia.eliminarPropiedadEntidad(eUsuario, "contactos");
		System.out.println("Despues del update:");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "contactos",obtenerIdContactosHash(usuario.getContactos()));
		
		System.out.println("en update contactos " + obtenerIdContactosHash(usuario.getContactos()));
		
		
		
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
	
	// -------------------Funciones auxiliares-----------------------------
	//TODO: Estas funciones existen aqui porque son necesarias, pero habria que mover su funcionamiento (código) a sus adaptadores pertinentes
	
	
	//---------------------------Funciones para recuperar/guardar los contactos en persistencia--------------------------
	//Funciones para trabajar con Mapas (HashMap)
	//Esta función es utilizada a la hora de guardar la nueva entidad, salvar las id u los nombres personalizados de tu lista de contactos
		private String obtenerIdContactosHash(HashMap<String, Usuario> listaUsuario) { //Lo hacemos así para recuperar los nombres que el Usuario habia puesto a la people
			String aux = "";
			for (String clave : listaUsuario.keySet()) { //Clave = nombre personal
				int valor = listaUsuario.get(clave).getId(); //valor
				//Clave el es id del contacto
				System.out.println("En iteración: "+ valor+ ":" + clave ); //TODO: Quitar
				aux += valor+ ":" + clave + " ";
			}
			System.out.println("ObtenerIdContactos " + aux);
			return aux.trim(); //Limpiar el string de carácteres invisibles
			//Ejemplo 123:Alvaro 444:Luisa  -> ID:Nombre Personal
		}
		

	//A la hora de cargar los contactos guardados, hay que tener en cuenta que el mapa es Nombre:Usuario, que no es lo que teniamos guarado como propiedad
		private HashMap<String, Usuario> obtenerContactosMapDesdeId(String ContactosG) {
			System.out.println("Esto en ObtenerContactosMapsDesde Id con: " + ContactosG);
			HashMap<String, Usuario> listaContactos = new HashMap<String, Usuario>();
			StringTokenizer strTok = new StringTokenizer(ContactosG, " ");//divide un string en array de palabras separadas en espacios
			while (strTok.hasMoreTokens()) {
				String datos = (String) strTok.nextElement(); //Convierto los datos a string -> (0)Id:(1)NickPersonal
	        	String[] parts = datos.split(":"); //Separo lo que es id del nombre personal
	        	System.out.println("EL parts 0 es (id) : " + parts[0]);
	        	Usuario usuarioaux = AdaptadorUsuarioDAO.getUnicaInstancia().get(Integer.valueOf(parts[0]));
	        	System.out.println("en obtenerConstactos pase del get");
				listaContactos.put(parts[1], usuarioaux); //Cargo en el mapa de contactos todos los nombres personales y los usuarios a los que referencia
			}
			return listaContactos;
		}
		
		
		
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
		
		//Esto pa que te sirve?? Beibi explain to me.
		public Set<ChatIndividual> getAllChatIndividualDesdeId(String ContactosG) {
			Set<ChatIndividual> listaContactos = new HashSet<ChatIndividual>();
			StringTokenizer strTok = new StringTokenizer(ContactosG, " ");
			while (strTok.hasMoreTokens()) {
				String id = (String) strTok.nextElement(); 
	        	ChatIndividual chatAux = AdaptadorChatIndividualDAO.getUnicaInstancia().get(Integer.valueOf(id));
				listaContactos.add(chatAux); 
			}
			return listaContactos;
		}

		
	
}
