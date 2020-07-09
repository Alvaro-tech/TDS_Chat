package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import beans.Entidad;
import beans.Propiedad;
import modelo.ChatIndividual;
import modelo.Mensaje;
import modelo.Usuario;

/**
 * 
 * Clase que implementa el Adaptador DAO concreto de Mensaje para el tipo H2.
 * 
 */
public final class AdaptadorMensajeDAO implements IAdaptadorMensajeDAO {

	private ServicioPersistencia servPersistencia;
	private static AdaptadorMensajeDAO unicaInstancia = null;

	/**
	 * Patrón Singleton
	 * 
	 * @return una única instancia de la clase AdaptadorMensajeDAO
	 */
	public static AdaptadorMensajeDAO getUnicaInstancia() {
		if (unicaInstancia == null)
			return new AdaptadorMensajeDAO();
		else
			return unicaInstancia;
	}

	/**
	 * Constructor singleton de AdaptadorMensajeDAO
	 */
	public AdaptadorMensajeDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	/**
	 * 
	 * @param Entidad eMensaje A partir de una entidad crea un objeto Mensaje.
	 * @return Mensaje
	 */
	private Mensaje entidadToMensaje(Entidad eMensaje) {

		// ATRIBUTOS FIJOS
		String texto = servPersistencia.recuperarPropiedadEntidad(eMensaje, "texto");
		String fecha = servPersistencia.recuperarPropiedadEntidad(eMensaje, "fecha");
		String emoji = servPersistencia.recuperarPropiedadEntidad(eMensaje, "emoji");

		Mensaje mensaje = new Mensaje(texto, fecha);
		mensaje.setEmoji(Boolean.getBoolean(emoji));
		mensaje.setId(eMensaje.getId());
		// lo introducimos en la pool
		PoolDAO.getUnicaInstancia().addObjeto(mensaje.getId(), mensaje);

		// ATRIBUTOS BI-DIRECCIONALES
		String emisor = servPersistencia.recuperarPropiedadEntidad(eMensaje, "emisor");
		String receptor = servPersistencia.recuperarPropiedadEntidad(eMensaje, "receptor");

		mensaje.setEmisor(obtenerEmisorById(emisor));
		mensaje.setReceptor(obtenerReceptorById(receptor));
		return mensaje;
	}

//#####################################################################
	private Usuario obtenerEmisorById(String emisor) {
		// el String emisor es el id del usuario correspondiente al emisor.
		return AdaptadorUsuarioDAO.getUnicaInstancia().get(Integer.valueOf(emisor));
	}

	private ChatIndividual obtenerReceptorById(String receptor) {
		// El String receptor es el id del ChatIndividual correspondiente.
		return AdaptadorChatIndividualDAO.getUnicaInstancia().get(Integer.valueOf(receptor));
	}

//#####################################################################

	/**
	 * 
	 * @param mensaje Crea un objeto entidad (persistencia) a partir de un objeto de
	 *                la clase Mansaje. Esto nos servirá para la funcion crear
	 * @return un objeto entidad
	 */
	private Entidad MensajeToEntidad(Mensaje mensaje) {
		Entidad eMensaje = new Entidad();
		eMensaje.setNombre("Mensaje");

		eMensaje.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad("emisor", obtenerIdEmisor(mensaje.getEmisor())),
				new Propiedad("receptor", obtenerIdReceptor(mensaje.getReceptor())),
				new Propiedad("fecha", mensaje.getFecha().toString()), 
				new Propiedad("emoji", mensaje.isEmoji()+""),
				new Propiedad("texto", mensaje.getTexto()))));
		return eMensaje;
	}

	@Override
	/**
	 * Funcion crear, crea un objeto por primera vez y lo introduce en el
	 * servicio de persistencia
	 */
	public void create(Mensaje mensaje) {
		Entidad  eMensaje;
		
		// *-*-*-*-*-*-*-*-Control de si existe el objeto ya, para evitar volver a
		// crearlo y haya repetidos
		boolean existe = true;
		// Si la entidad está registrada no la registra de nuevo
		try {
			eMensaje = servPersistencia.recuperarEntidad(mensaje.getId());
		} catch (Exception e) {
			existe = false;
		}
		if (existe)
			return;
		
		eMensaje = this.MensajeToEntidad(mensaje);
		eMensaje = servPersistencia.registrarEntidad(eMensaje);
		mensaje.setId(eMensaje.getId());
		
	}

	@Override
	/**
	 * Funcion eliminar, para eliminar una Entidad de la persistencia
	 */
	public boolean delete(Mensaje mensaje) {
		Entidad eMensaje;
		eMensaje = servPersistencia.recuperarEntidad(mensaje.getId());
		return servPersistencia.borrarEntidad(eMensaje);
	}

	@Override
	/**
	 * Funcion get para obtener un mensaje del servicio de persistencia a través del
	 * Id de este.
	 */
	public Mensaje get(Integer id) {
		if (PoolDAO.getUnicaInstancia().contiene(id)) {
			return (Mensaje) PoolDAO.getUnicaInstancia().getObjeto(id);
		}
		
		Entidad eMensaje = servPersistencia.recuperarEntidad(id);
		Mensaje m = entidadToMensaje(eMensaje);
		PoolDAO.getUnicaInstancia().addObjeto(id, m);
		return m;
	}

	@Override
	/**
	 * Funcion para obtener una lista de todos los mensajes del catálogo
	 */
	public List<Mensaje> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades("Mensaje");

		List<Mensaje> mensajes = new LinkedList<Mensaje>();
		for (Entidad e : entidades) {
			mensajes.add(get(e.getId()));
		}
		return mensajes;
	}

	// ########FUNCIONES AUXILIARES:#########
	/**
	 * @param receptor Función soporte para obtener el ID del receptor (de tipo
	 *                 ChatIndividual) sirve para la funcion MensajeToEntidad
	 * @return String idReceptor
	 */
	private String obtenerIdReceptor(ChatIndividual receptor) {
		Integer string = (Integer) receptor.getId();
		return string.toString();
	}

	/**
	 * @param emisor Función soporte para obtener el ID del emisor (de tipo Usuario)
	 *               sirve para la funcion MensajeToEntidad
	 * @return String idEmisor
	 */
	private String obtenerIdEmisor(Usuario emisor) {
		return emisor.getId().toString();
	}

}
