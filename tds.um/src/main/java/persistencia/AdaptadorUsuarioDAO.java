package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import beans.Entidad;
import beans.Propiedad;
import modelo.Chat;
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
	
	private Usuario entidadToUsuario(Entidad eUsuario) {
		
		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		String email = servPersistencia.recuperarPropiedadEntidad(eUsuario, "email");
		String fecha = servPersistencia.recuperarPropiedadEntidad(eUsuario, "fecha");
		String movil = servPersistencia.recuperarPropiedadEntidad(eUsuario, "movil");
		String clave = servPersistencia.recuperarPropiedadEntidad(eUsuario, "clave");
		String saludo = servPersistencia.recuperarPropiedadEntidad(eUsuario, "saludo");
		String contactos = servPersistencia.recuperarPropiedadEntidad(eUsuario, "contactos");
		String chatInd = servPersistencia.recuperarPropiedadEntidad(eUsuario, "chatIndividual");
		String chatGroup = servPersistencia.recuperarPropiedadEntidad(eUsuario, "chatGrupo");

		
		//public Usuario(String nombre, String email, String fecha, String movil, String clave)
		Usuario Usuario = new Usuario(nombre, email, fecha, movil, clave, saludo); 
		Usuario.setId(eUsuario.getId());
		
		Usuario.setContactos(obtenerContactosMapDesdeId(contactos));
		
		for(ChatIndividual iterador : obtenerChatIndividualDesdeId(chatInd)) {
			Usuario.agregarChatIndividual(iterador);
		}
		
		
		return Usuario;
	}
	
	//Función para crear la entidad Usuario
	private Entidad UsuarioToEntidad(Usuario Usuario) {
		Entidad  eUsuario = new Entidad();
		eUsuario.setNombre("Usuario"); 
		
		/* Es necesario (?), los usuarios no los crea el, sino el registro, el solo deberá añadirlo a su lista de contactos. Lo mismo con las voncesaciones o chats
		// registrar primero los atributos que son objetos
				AdaptadorVentaTDS adaptadorVenta = AdaptadorVentaTDS.getUnicaInstancia();
				for (Venta v : cliente.getVentas())
					adaptadorVenta.registrarVenta(v);
				*/ 
	
		eUsuario.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("nombre", Usuario.getNombre()), 
						new Propiedad("fecha", Usuario.getFecha()),
						new Propiedad("movil", Usuario.getMovil()),
						new Propiedad("email", Usuario.getEmail()),
						new Propiedad("clave", Usuario.getClave()),
						new Propiedad("saludo", Usuario.getSaludo()),
						new Propiedad("contactos", obtenerIdContactosHash(Usuario.getContactos())),
						new Propiedad("chatIndividual", obtenerIdChatIndividual(Usuario.getChatsInd()))
						//new Propiedad("chatGrupo", obtenerIdContactosSet(Usuario.getChatsGroup()))
						))
				);
		return eUsuario;
	}
	
	public void create(Usuario Usuario) {
		Entidad eUsuario;
		boolean existe = true; 
		
		//Uso de la pool
		/*
		// Si la entidad está registrada no la registra de nuevo
		try {
			eUsuario = servPersistencia.recuperarEntidad(Usuario.getId());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe) return;*/
		
		//Si no habrá que registrar al usuario en el servidor de persistencia
		eUsuario = this.UsuarioToEntidad(Usuario);
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
		servPersistencia.anadirPropiedadEntidad(eUsuario, "contactos",Usuario.getSaludo());
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
		//System.out.println("COsas cogiendo al usuario:" + usuario.getId() + usuario.getMovil() + usuario.getSaludo());
		//System.out.println("Cosas cogiendo al eusaurio" + eUsuario.getId());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "saludo");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "saludo",usuario.getSaludo());
		//System.out.println("Saludo al final:" + servPersistencia.recuperarPropiedadEntidad(eUsuario, "saludo"));
		
	}
	
	public void updateContactos(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());

		servPersistencia.eliminarPropiedadEntidad(eUsuario, "contactos");
		System.out.println("Despues del update:");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "contactos",obtenerIdContactosHash(usuario.getContactos()));
		
		
		
	}
	
	// -------------------Funciones auxiliares-----------------------------
	//TODO: Estas funciones existen aqui porque son necesarias, pero habria que mover su funcionamiento (código) a sus adaptadores pertinentes
	
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
			HashMap<String, Usuario> listaContactos = new HashMap<String, Usuario>();
			StringTokenizer strTok = new StringTokenizer(ContactosG, " ");
			while (strTok.hasMoreTokens()) {
				String datos = (String) strTok.nextElement(); //Convierto los datos a string -> (0)Id:(1)NickPersonal
	        	String[] parts = datos.split(":"); //Separo lo que es id del nombre personal
	        	System.out.println("EL parts 0 es : " + parts[0]);
	        	Usuario usuarioaux = AdaptadorUsuarioDAO.getUnicaInstancia().get(Integer.valueOf(parts[0]));
				listaContactos.put(parts[1], usuarioaux); //Cargo en el mapa de contactos todos los nombres personales y los usuarios a los que referencia
			}
			return listaContactos;
		}
		
	//Funciones para trabajar con HashSet's
	//TODO: Como no tener tantas funciones puto iguales

		private String obtenerIdChatIndividual(HashSet<ChatIndividual> listaUsuario) {
			String aux = "";
			for(Chat iterador : listaUsuario) {
				aux +=  iterador.getId() + " ";
			}
			return aux.trim(); 
		}
		
		private Set<ChatIndividual> obtenerChatIndividualDesdeId(String ContactosG) {
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
