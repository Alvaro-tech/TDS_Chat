package modelo;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;

public abstract class Chat implements Comparator<Chat> {

	// CHAT == CONTACTO

	/*
	 * Chat es como un contacto: solo la info básica. Voy a escribir una propuesta
	 * de Chat pensando en este como contacto.
	 * 
	 */

	private String nombre;
	private LinkedList<Mensaje> historial;
	private int id;
	private Mensaje ultimoMensaje;

	public Chat(String nombre) {
		this.nombre = nombre;
		this.historial = new LinkedList<Mensaje>();
	}

	public Chat(String nombre, LinkedList<Mensaje> historial, Mensaje m) {
		this.nombre = nombre;
		this.historial = historial;
		this.ultimoMensaje = m;
	}

	public void addMensajeHistorial(Mensaje m) {
		// Siempre se añadirá el último mensaje al principio de la lista.
		historial.addFirst(m);
		this.setUltimoMensaje(m);
	}

	public void setMensaje(LinkedList<Mensaje> mensaje) {
		this.historial = mensaje;
	}

	public LinkedList<Mensaje> getHistorial() {
		return historial;
	}

	public int getId() {
		return id;
	}

	public Mensaje getUltimoMensaje() {
		return this.ultimoMensaje;
	}

	public void setUltimoMensaje(Mensaje m) {
		this.ultimoMensaje = m;
	}

	public void setId(int nId) {
		this.id = nId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setHistorial(LinkedList<Mensaje> historial) {
		this.historial.addAll(historial);
		this.setUltimoMensaje(historial.getFirst());
	}

	/**
	 * Funcion para buscar mensajes con texto exactamente igual al dado
	 * @param texto
	 * @return lista de mensajes con ese texto
	 */
	public LinkedList<Mensaje> BuscarPorTextoCompleto(String texto) {
		LinkedList<Mensaje> mensajesFiltrados = new LinkedList<Mensaje>();

		this.historial.stream()
		.filter(m -> m.getTexto().contentEquals(texto))
		.forEach(m -> mensajesFiltrados.add(m));

		return mensajesFiltrados;
	}

	/**
	 * Funcion parabuscar los mensajes que contengan parte (o todo) del texto dado
	 * @param texto
	 * @return lista de mensajes con la coincidencia
	 */
	public LinkedList<Mensaje> BuscarPorTextoCoincidente(String texto) {
		LinkedList<Mensaje> mensajesFiltrados = new LinkedList<Mensaje>();

		this.historial.stream()
		.filter(m -> m.getTexto().contains(texto))
		.forEach(m -> mensajesFiltrados.add(m));

		return mensajesFiltrados;
	}

	/**
	 * Funcion que devuelve los mensajes dada una fecha concreta
	 * @param f
	 * @return Lista de mensajes coincidentes.
	 */
	public LinkedList<Mensaje> BuscarPorFecha(LocalDate f) {
		LinkedList<Mensaje> mensajesFiltrados = new LinkedList<Mensaje>();

		this.historial.stream()
		.filter(m -> m.getFecha().isEqual(f))
		.forEach(m -> mensajesFiltrados.add(m));

		return mensajesFiltrados;
	}

	// Funcion para comparar dos chats y ver cual es el mas reciente
	@Override
	public int compare(Chat o1, Chat o2) {
		int a = o2.getUltimoMensaje().getFecha().compareTo(o1.getUltimoMensaje().getFecha());
		System.out.println(a);
		return a;
	}

	@Override
	public String toString() {
		return "CHAT = " + this.nombre;
	}

}