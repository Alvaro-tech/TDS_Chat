package modelo;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import persistencia.AdaptadorChatGrupoDAO;
import persistencia.AdaptadorChatIndividualDAO;

/**
 * Clase que representa a un Usuario del sistema en el modelo de este.
 * 
 * @author Álvaro y Ana.
 *
 */
public class Usuario {
	String nombre;
	String email;
	String fechaNacimiento;
	String movil;
	Usuario user; // tu propio usuario
	String clave; // contraseña
	String fotoPerfil;
	boolean premium;
	Integer id;
	String saludo;
	String conversacionesAbiertas;
	// Lista de chats
	HashSet<ChatIndividual> chatsInd = new HashSet<ChatIndividual>();
	HashSet<ChatGrupo> chatsGroup = new HashSet<ChatGrupo>();
	// lista de chats abiertos de ppl que no tienes agregada
	HashSet<ChatIndividual> chatsDesconocido = new HashSet<ChatIndividual>();
	

	/**
	 * Constructor de la clase Usuario.
	 * 
	 * @param nombre
	 * @param email
	 * @param fecha
	 * @param movil
	 * @param clave
	 */
	public Usuario(String nombre, String email, String fecha, String movil, String clave) { // Constructor crear uno
																							// nuevo
		this.nombre = nombre;
		this.email = email;
		this.fechaNacimiento = fecha;
		this.movil = movil;
		this.clave = clave;
		this.premium = false;
		this.user = this; // ?
		this.saludo = "Hey there, I'm using TDSchat.";
		this.fotoPerfil = "./iconos/Defecto.PNG";
		this.conversacionesAbiertas = "";
	
	}

	/**
	 * Constructor de la clase Usuario.
	 * 
	 * @param nombre
	 * @param email
	 * @param fecha
	 * @param movil
	 * @param clave
	 * @param saludop
	 * @param foto
	 */
	public Usuario(String nombre, String email, String fecha, String movil, String clave, String saludop, String foto,
			String conversaciones) { // Constructor
		// para
		// recuperar
		this.nombre = nombre;
		this.email = email;
		this.fechaNacimiento = fecha; // FORMATO dd/MM/yyyy ejemplo: 18/11/1999
		this.movil = movil;
		this.clave = clave;
		this.premium = false;
		this.user = this; // ?
		this.saludo = saludop;
		this.fotoPerfil = foto;
		this.conversacionesAbiertas = conversaciones;

	}

	// ##################### METODOS GET Y SET #######################

	/**
	 * Metodo get de Usuario.
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo get de Usuario.
	 * 
	 * @return fecha de nacimiento.
	 */
	public String getFecha() {
		return fechaNacimiento;
	}

	/**
	 * Metodo get de Usuario.
	 * 
	 * @return movil
	 */
	public String getMovil() {
		return movil;
	}

	/**
	 * Metodo get de Usuario.
	 * 
	 * @return true si es premium, false en otro caso.
	 */
	public boolean isPremium() {
		return premium;
	}

	/**
	 * Metodo get de Usuario.
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Metodo get de Usuario.
	 * 
	 * @return password o clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Metodo get de Usuario.
	 * 
	 * @return ID del Usuario.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Metodo get de Usuario.
	 * 
	 * @return saludo
	 */
	public String getSaludo() {
		return saludo;
	}

	/**
	 * Metodo get de Usuario.
	 * 
	 * @return ChatsIndividuales o contactos.
	 */
	public HashSet<ChatIndividual> getChatsInd() {
		return chatsInd;
	}

	/**
	 * Metodo get de Usuario.
	 * 
	 * @return grupos en los que está el usuario.
	 */
	public HashSet<ChatGrupo> getChatsGroup() {
		return chatsGroup;
	}
	
	/**
	 * Método get de Usuario.
	 * @return chats individuales y desconocidos unificado.
	 */
	public HashSet<ChatIndividual> getCHatsIndividualesYDesconocidos(){
		HashSet<ChatIndividual> chats = new HashSet<ChatIndividual>();
		chats.addAll(chatsDesconocido);
		chats.addAll(chatsInd);
		return chats;
	}

	/**
	 * Metodo get de Usuario.
	 * 
	 * @return foto de perfil.
	 */
	public String getFotoPerfil() {
		return fotoPerfil;
	}

	/**
	 * Funcion que calcula la edad del usuario.
	 * 
	 * @return int años de edad.
	 */
	public int getEdad() {
		LocalDate ahora = LocalDate.now();
		LocalDate fNaci = LocalDate.parse(this.fechaNacimiento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Period periodo = Period.between(fNaci, ahora);
		return periodo.getYears();
	}

	/**
	 * Funcion para devolver las conversaciones abiertas del usuario en su sesion
	 * 
	 * @return String conversacionesAbiertas
	 */
	public String getConversacionesAbiertas() {
		return conversacionesAbiertas;
	}

	/**
	 * Metodo get de Usuario.
	 * 
	 * @return todos los chats que tiene el Usuario.
	 */
	public HashSet<Chat> getTodosLosChats() {
		HashSet<Chat> chats = new HashSet<Chat>();
		chats.addAll(chatsGroup);
		chats.addAll(chatsInd);
		chats.addAll(chatsDesconocido);
		return chats;
	}

	/**
	 * Metodo set de Usuario.
	 * 
	 * @param premium, boolean.
	 */
	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	/**
	 * Metodo set de Usuario.
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Metodo set de Usuario.
	 * 
	 * @param saludo
	 * @return String saludo actual
	 */
	public void setSaludo(String saludo) {
		this.saludo = saludo;
	}

	/**
	 * Metodo set de Usuario.
	 * 
	 * @param fotoPerfil
	 */
	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	/**
	 * Metodo set de Usuario.
	 * 
	 * @param grupos
	 */
	public void setGrupos(HashSet<ChatGrupo> grupos) {
		this.chatsGroup = grupos;
	}

	/**
	 * Metodo set de Usuario.
	 * 
	 * @param chats
	 */
	public void setChatIndividuales(HashSet<ChatIndividual> chats) {
		this.chatsInd = chats;
	}

	/**
	 * Método get de Usuario
	 * 
	 * @param conversacionesAbiertas
	 */
	public void setConversacionesAbiertas(String conversacionesAbiertas) {
		this.conversacionesAbiertas = conversacionesAbiertas;
	}

	/**
	 * Funcion para añadir chats a las conversaciones abiertas
	 * 
	 * @param String idChat
	 */
	public void addConversacion(int idChat) {
		this.conversacionesAbiertas = conversacionesAbiertas + idChat + " ";
	}
	

	/**
	 * Funcion para agregar un chat individual a la lista de chats
	 * 
	 * @param chat
	 */
	public void agregarChatIndividual(ChatIndividual chat) {
		chatsInd.add(chat);
	}

	/**
	 * Funcion para agregar un grupo a la lista de grupos.
	 * 
	 * @param chat
	 */
	public void agregarChatGrupo(ChatGrupo chat) {
		chatsGroup.add(chat);
	}
	
	

	// ##################### FUNCIONALIDAD #######################

	/**
	 * Funcion que devuelve una lista de Chats del usuario de manera ordenada.
	 * 
	 * @return lista de chats del usuarios ordenada. LinkedList<Chat>
	 */
	public LinkedList<Chat> getChatRecientes() {
		LinkedList<Chat> todos = new LinkedList<Chat>();
		todos.addAll(this.getTodosLosChats());
		LinkedList<Chat> recientes = new LinkedList<Chat>();
		String idChat = "";
		
		for (Chat chat : todos) {
			idChat = chat.getId() + ""; // De esta manera el constains no da por true el 1 en un 12, asegura buscar
										// numeros independientes
			if (conversacionesAbiertas.contains(idChat)) {
				recientes.add(chat);
			}
		}

		recientes.stream().sorted((c1, c2) -> c1.compare(c1, c2));
		return recientes;

	}
	
	/**
	 * Funcion que crea un grupoPadre. Crea los grupo hijo en los respectivos miembros de este.
	 * @param nombreGrupo
	 * @param contactos, argumento variable de tipo ChatIndividual.
	 */
	public ChatGrupo crearGrupoNuevo(String nombreGrupo, ChatIndividual... contactos) {
		//primera vez que se crea un grupo (es decir, es el grupo padre)
		ChatGrupo grupoPadre = new ChatGrupo(nombreGrupo, contactos);
		grupoPadre.addAdmin(this);
		grupoPadre.setDuenyo(this);
		
		//añado mi propio contacto al chatIndividual
		this.anyadirmeAGrupo(grupoPadre);
		this.chatsGroup.add(grupoPadre);
		
		return grupoPadre;
		/*
		grupoPadre.getMiembros().stream()
								.forEach(m -> m.getContacto().CrearGrupoHijo(grupoPadre));
		
		//TODO Lo he añadido para poder retornar el grupo y que aparezca en chats recientes (Parche)
		return grupoPadre;*/
	}

	/**
	 * Función que recorre la lista de contactos del usuario actual para añadirse a sí mismo.
	 * @param ChatGrupo grupo
	 */
	private void anyadirmeAGrupo(ChatGrupo grupo) {
		Iterator<ChatIndividual> iterator = this.getChatsInd().iterator(); 
		boolean fin = false;
		while (!fin && iterator.hasNext()) {
			ChatIndividual aux = iterator.next();
			if(aux.getContacto().equals(this)) {
				grupo.addMiembro(aux);
				System.out.println("anyadirmeAGrupo, me añadi (movil): " + aux.getMovil());
			}
	    }
	}

	
	
	
	/**
	 * Funcion que permite crear un chat de grupo "hijo" a partir de un "padre" dado.
	 * @param ChatGrupo grupoPadre
	 */
	public ChatGrupo CrearGrupoHijo(ChatGrupo grupoPadre) {
		// Vamos a crear un grupo hijo.
		//recorremos toda la lista de grupoPadre de miembros
		LinkedList<ChatIndividual> nuevosMiembros = new LinkedList<ChatIndividual>();
		
		for (ChatIndividual c : grupoPadre.getMiembros()) {
			//para crear los nuevos miembros vamos a basarnos en los miembros del grupo padre.
			
			ChatIndividual miembroBien = this.ContactoEquivalente(c);
			System.out.println("añadi como miembro en crearGrupoHijo a: " + miembroBien.getNombre());
			nuevosMiembros.add(miembroBien);		
		}
		
		ChatGrupo grupoHijo = new ChatGrupo(grupoPadre.getNombre(), nuevosMiembros);
		//idPadre del hijo == id del padre.
		grupoHijo.setIdPadre(grupoPadre.getIdPadre());
		//pongo al usuario como su dueño
		grupoHijo.setDuenyo(grupoPadre.getDuenyo()); 
		
		//APARTIR DE AQUÍ: tengo en cuenta el aliasing y me aprovecho de ello
		//meto los mensajes del grupo en este
		//TODO: Puede no haber mensajes aun y eso daría un error, sigo sin corregir persistencia
		//Pero el objeto per se está creado, no debería dar null nunca...
		//me aprovecho del aliasing para que todos apunten al mismo objeto historial.
		try {
			grupoHijo.setHistorial(grupoPadre.getHistorial());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//le pongo los mismo administradores
		grupoHijo.setAdministradores(grupoPadre.getAdministradores());
		//los grupoHijo siempre vacíos. 
		//Lo mismo, me aprovecho del aliasing y no. No debería estar vacío en absoluto. EL padre tiene al creador de admin.
		
		//Por ultimo, lo añado a la lista de los grupo hijo del padre. y a la lista de chat de grupo del usuario.
		grupoPadre.addGrupoHijo(grupoHijo);
		this.chatsGroup.add(grupoHijo);
		
		return grupoHijo;
		
	}
	

	
	
	/**
	 * Funcion que permite añadir un contacto desconocido. Todo se mantendrá igual excepto el nombre y que ahora
	 * estará en tu lista de contactos.
	 * @param desconocido
	 * @param nombre
	 */
	public void pasarDesconocidoAContacto(ChatIndividual desconocido, String nombre) {
		Iterator<ChatIndividual> it = this.chatsDesconocido.iterator();
		boolean fin = false;
		
		while(!fin && it.hasNext()) {
			if(it.next().equals(desconocido)) {
				fin = true;
				it.remove();
				desconocido.setNombre(nombre);
				this.chatsInd.add(desconocido);
			}
		}
	}
	
	/**
	 * Funcion que recorre la lista de contactos y devuelve el equivalente.
	 * En caso de no tener el contacto devuelve uno "desconocido" que guardará.
	 * @param ChatIndividual m
	 * @return ChatIndividual contactoEquivalente.
	 */
	
	public ChatIndividual ContactoEquivalente(ChatIndividual m) {
		boolean fin= false;
		Iterator<ChatIndividual> iterator = this.getChatsInd().iterator(); 
		
			ChatIndividual aux = null;
			while (fin == false && iterator.hasNext()) {
			aux = iterator.next();
			System.out.println("###En contacto equivalante, movil en agenda" + aux.getMovil());
			System.out.println("###En contacto equivalante, miembro del grupo " + m.getMovil());
	        if (aux.getMovil() == m.getMovil()) { //si tienen el mismo movil, son el mismo. (no mismo objeto)
	        	fin = true;
	        	System.out.println("encontre contacto equivalente");
	        }
	        
		}
		
		if (!fin) { //sale porque no lo ha encontrado, es un contacto desconocido.
			//creamos un contacto "desconocido" y lo asociamos al grupo y a su lista de contactos desconocidos.
			//contacto desconocido = tiene el nombre como su movil.
			System.out.println("####en contacto equivaletente voy a retornar un  contacto desconcdio");
			ChatIndividual anonimo = new ChatIndividual(m.getMovil(), m.getMovil(), m.getContacto());
			this.chatsDesconocido.add(anonimo);
			return anonimo;
			
		}else return aux;
	}
	
	/**
	 * Funcion que retorna el numero de mensajes totales enviados por el usuario en
	 * este mes.
	 * 
	 * @return int numero de mensajes.
	 */
	public int getNumeroDeMensajesDelMes() {
		return (int) this.getMensajesDelMes().count();
	}
	
	
	/**
	 * Funcion que devuelve un stream de los mensajes que se envian en el mes actual.
	 * @return Stream<Mensaje> mensajes del mes.
	 */
	private Stream<Mensaje> getMensajesDelMes(){
		LinkedList<Chat> todos = new LinkedList<Chat>();
		todos.addAll(this.getTodosLosChats());

		// saco de todos una lista de listas de mensajes
		List<List<Mensaje>> mensas = todos.stream().map(c -> c.getHistorial()).collect(Collectors.toList());

		// de mensas hago un flatmap para convertir la lista de listas en una lista
		// simple
		List<Mensaje> mensajes = // lista con todos los mensajes
				mensas.stream().flatMap(List::stream).collect(Collectors.toList());

		// hago la busqueda de los mensajes y los cuento.
		return  mensajes.stream().filter(m -> m.getEmisor().equals(this))
				.filter(m -> m.getFecha().getMonth().equals(LocalDate.now().getMonth())); // Equals sobre enumerado
	}

	
	/**
	 * Funcion que devuelve los seis grupos con más mensajes enviados.
	 * @return List<ChatGrupo>  top6Grupos
	 */
	public List<ChatGrupo> get6GruposTop() {
		ArrayList<ChatGrupo> grupos = new ArrayList<ChatGrupo>(6);
		
		for (ChatGrupo g : chatsGroup) {
			int aux = g.getMensajesTotales();
			//vemos si lo añadimos
			int i = 1;
			boolean fin = false;
			while(!fin && i< grupos.size()) {
				if (aux > grupos.get(i).getMensajesTotales()) {
					grupos.add(i, g);
					fin=true;
				}
				i++;
			}
		}
		return grupos;
	}

	/**
	 * Funcion que devuelve los mensajes enviados por el usuario en el mes concreto.
	 * @param i
	 * @return
	 */
	public double getMensajesEnviadosEsteMes(int i) {
		double totales = 0.0;
		for (Chat c : getTodosLosChats()) {
			totales += c.getMensajesEnviadosEsteMes(i, this);
		}
		
		return totales;
	}

	public String getInfoChatsIndividuales() {
		String infoTot = "";
		for (ChatIndividual c : this.getCHatsIndividualesYDesconocidos()) {
			infoTot += c.getInfo();
		}
		
		return infoTot;
	}

	public String getInfoGrupo() {
		String infoTot = "";
		for (ChatGrupo c : this.chatsGroup) {
			infoTot += c.getInfo();
		}
		
		return infoTot;
	}
	
	/**
	 * Funcion que crea un chatDesconocido y lo añade al usuario.
	 * @param movil del nuevo contacto, será su nombre también.
	 * @param Usuario u, usuario desconocido.
	 * @return ChatIndividual desconocido.
	 */
	public ChatIndividual addChatDesconocido(String movil, Usuario u) {
		ChatIndividual desconocido = new ChatIndividual(movil, movil, u);
		this.chatsDesconocido.add(desconocido);
		return desconocido;
	}

}
