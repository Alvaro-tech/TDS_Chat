package modelo;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Clase abstracta de Chat, de donde parten ChatIndividual y ChatGrupo
 * @author Álvaro y Ana.
 *
 */
public abstract class Chat implements Comparator<Chat> {

	private String nombre;
	private LinkedList<Mensaje> historial;
	private int id;
	private Mensaje ultimoMensaje;

	/**
	 * Constructor de la clase Chat.
	 * @param nombre 
	 */
	public Chat(String nombre) {
		this.nombre = nombre;
		this.historial = new LinkedList<Mensaje>();
	}
	
	/**
	 * Constructor de la clase Chat.
	 * @param nombre
	 * @param historial
	 * @param Mensaje ultimoMensaje = m
	 */
	public Chat(String nombre, LinkedList<Mensaje> historial, Mensaje m) {
		this.nombre = nombre;
		this.historial = historial;
		this.ultimoMensaje = m;
	}


	// ##################### GETS Y SETS #######################
	
	/**
	 * Método get de Chat.
	 * @return historial de mensajes.
	 */
	public LinkedList<Mensaje> getHistorial() {
		return historial;
	}
	
	/**
	 * Método get de Chat.
	 * @return ID del chat.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Método get de Chat.
	 * @return Mensaje Ultimo mensaje enviado en el chat.
	 */
	public Mensaje getUltimoMensaje() {
		return this.ultimoMensaje;
	}
	
	/**
	 * Método get de Chat.
	 * @return nombre del chat.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método set de Chat.
	 * @param Mensaje m = UltimoMensaje enviado.
	 */
	public void setUltimoMensaje(Mensaje m) {
		this.ultimoMensaje = m;
	}
	
	/**
	 * Método set de Chat.
	 * @param Id del chat.
	 */
	public void setId(int nId) {
		this.id = nId;
	}

	/**
	 * Método set de Chat.
	 * @param nombre del chat.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Metodo set de Chat.
	 * @param historial de mensajes.
	 */
	public void setHistorial(LinkedList<Mensaje> historial) {
		this.historial.addAll(historial);
		this.setUltimoMensaje(historial.getFirst());
	}
	
	/**
	 * Método set de Chat.
	 * @param Mensaje m a añadir.
	 */
	public void addMensajeHistorial(Mensaje m) {
		// Siempre se añadirá el último mensaje al principio de la lista.
		historial.addFirst(m);
		this.setUltimoMensaje(m);
	}
	
	public void vaciarChat() {
		historial = new LinkedList<Mensaje>();
		ultimoMensaje=null;
	}
	
	
	// ##################### FUNCIONALIDAD #######################
	

	/**
	 * Funcion para combinar las busquedas por filtros.
	 * @param texto
	 * @param fecha
	 * @param u
	 * @return mensajes con los filtros introducidos.
	 */
	public LinkedList<Mensaje> BuscarPorFiltros(String texto, LocalDateTime fechaInicio, LocalDateTime fechaFin, ChatIndividual u) {
		LinkedList<Mensaje> mensajes = new LinkedList<Mensaje>();
		mensajes = this.historial;

		if(fechaInicio != null && fechaFin != null) {
			mensajes = this.BuscarPorFechas(fechaInicio, fechaFin, mensajes);
		}
		
		if (texto != null) {
			mensajes = this.BuscarPorTexto(texto, mensajes);
		}

		
		if (u != null) {
			ChatGrupo grupo = (ChatGrupo) this;
			mensajes = grupo.BuscarMensajePorContactos(u, mensajes);
		}
		
		return mensajes;
	}

	/**
	 * Funcion para buscar mensajes que contengan texto dado. Añade los mensajes que
	 * se han encontrado en una busqueda anterior.
	 * 
	 * @param String texto, sobre el que se quiere hacer la búsqueda.
	 * @param LinkedList<Mensaje> mensajes, mensajes ya filtrados. Busco sobre ellos.
	 * @return lista de mensajes con ese texto
	 */
	public LinkedList<Mensaje> BuscarPorTexto(String texto, LinkedList<Mensaje> mensajes) {
		LinkedList<Mensaje> mensajesFiltrados = new LinkedList<Mensaje>();
		
		mensajes.stream()
				.filter(m -> m.getTexto().contains(texto))
				.forEach(m -> mensajesFiltrados.add(m));
		
		return mensajesFiltrados;
	}


	/**
	 * Funcion que devuelve los mensajes dada una fecha concreta se lo añade a una
	 * lista de mensajes ya encontrados en otra busqueda.
	 * 
	 * @param f
	 * @return Lista de mensajes coincidentes.
	 */
	public LinkedList<Mensaje> BuscarPorFechas(LocalDateTime fi, LocalDateTime ffin, LinkedList<Mensaje> mensajes) {
	LinkedList<Mensaje> mensajesFiltrados = new LinkedList<Mensaje>();
		
		mensajes.stream()
					.filter(m -> m.esEntreFechas(fi, ffin))
					.forEach(m -> mensajesFiltrados.add(m));
		
		return mensajesFiltrados;
	}

	

	/**
	 * Funcion para comparar dos chats y ver cual es el mas reciente.
	 * 	@Override
	 */
	@Override
	public int compare(Chat o1, Chat o2) {
		int a = o2.getUltimoMensaje().getFecha().compareTo(o1.getUltimoMensaje().getFecha());
		return a;
	}


	/**
	 * Funcion para pasar un chat a un string.
	 * @Override
	 */
	@Override
	public String toString() {
		return this.nombre;
	}
	
	/**
	 * Funcion que devuelve los mensajes enviados por un usuario concreto en un mes 
	 * concreto.
	 * @param int mes, del 1 al 12.
	 * @param Usuario u, usuario emisor de los mensajes.
	 * @return double numero de mensajes.
	 */
	public double getMensajesEnviadosEsteMes(int mes, Usuario u) {
		return (double) this.getHistorial().stream()
							.filter(m -> m.getEmisor().equals(u))
							.filter(m -> m.getFecha().getMonthValue() == mes)
							.count();
	}

}