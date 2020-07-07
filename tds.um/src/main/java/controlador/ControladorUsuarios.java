package controlador;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.internal.chartpart.Chart;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

import modelo.CatalogoUsuarios;
import modelo.Chat;
import modelo.ChatGrupo;
import modelo.ChatIndividual;
import modelo.Descuento;
import modelo.GestorGraficas;
import modelo.Mensaje;
import modelo.Usuario;
import persistencia.AdaptadorChatGrupoDAO;
import persistencia.AdaptadorChatIndividualDAO;
import persistencia.AdaptadorUsuarioDAO;
import persistencia.DAOException;
import persistencia.FactoriaDAO;

/**
 * Clase Controlador del sistema. Separa la vista de la lógica del dominio y la
 * persistencia.
 * 
 * @author Álvaro y Ana.
 *
 */
public class ControladorUsuarios {

	private Usuario usuarioActual;
	private static ControladorUsuarios unicaInstancia;
	private FactoriaDAO factoria;
	private static final double PRECIOPREMIUM = 10.50; // al año.
	private Descuento d;

	/**
	 * Funcion que crea el Controlador del sistema. Solo habrá uno, siguiendo el
	 * patrón Singleton.
	 */
	private ControladorUsuarios() {
		usuarioActual = null;
		try {
			factoria = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Funcion que devuelve la única instancia que existe del Controlador. Patrón
	 * Singleton.
	 * 
	 * @return ControladorUsuarios
	 */
	public static ControladorUsuarios getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new ControladorUsuarios();
		return unicaInstancia;
	}

	// ##################### GETS Y SETS #######################

	/**
	 * Funcion que introduce cual va a ser el descuento que se va a utilizar para el
	 * calculo del precio premium.
	 * 
	 * @param Descuento des
	 */
	public void setDescuento(Descuento des) {
		this.d = des;
	}

	/**
	 * Devuelve el usuario con la sesion iniciada actualmente en el sistema.
	 * 
	 * @return Usuario u.
	 */
	public Usuario getusuarioActual() {
		return usuarioActual;
	}

	/**
	 * Método get del ID del usuario actual.
	 * 
	 * @return el ID del usuario actual.
	 */
	public String getIdUsuarioActual() {
		return usuarioActual.getClave();
	}

	/**
	 * Método get del precio premium.
	 * 
	 * @return el precio mensual de premium sin descuento aplicado
	 */
	public String getPrecioPremiumMes() {
		Double precio = (Double) PRECIOPREMIUM / 12;
		return precio.toString();
	}

	/**
	 * Método get del nombre del usuario Actual.
	 * 
	 * @return nombre del usuario Actual
	 */
	public String getNombreUsuarioActual() {
		return usuarioActual.getNombre();
	}

	/**
	 * Funcion que comprueba si el usuario ya está registrado en el sistema.
	 * 
	 * @param movil
	 * @return booleano, true si es cierto, false en otro caso.
	 */
	public boolean esUsuarioRegistrado(String movil) {
		return CatalogoUsuarios.getUnicaInstancia().getUsuario(movil) != null;
	}

	// ##################### FUNCIONALIDAD DE LA PERSISTENCIA
	// #######################

	/**
	 * Funcion para el Login del usuario en el sistema.
	 * 
	 * @param movil
	 * @param password
	 * @return true si ha sido posible, false en otro caso.
	 */
	public boolean loginUsuario(String movil, String password) {
		Usuario Usuario = CatalogoUsuarios.getUnicaInstancia().getUsuario(movil);
		if (Usuario != null && Usuario.getClave().equals(password)) {
			this.usuarioActual = Usuario;
			System.out.println("En controlador mi usuario actual es: " + usuarioActual.getMovil() + " id "
					+ usuarioActual.getId());
			return true;
		}
		return false;
	}

	/**
	 * Funcion para registrar el usuario en el sistema. Lo introduce en el servicio
	 * de persistencia.
	 * 
	 * @param nombre
	 * @param email
	 * @param fecha
	 * @param movil
	 * @param Booleano, true si lo ha introducido con éxito, falso en otro caso.
	 */
	public boolean registrarUsuario(String nombre, String email, String fecha, String movil, String clave) {

		if (esUsuarioRegistrado(movil))
			return false;
		// Usuario(String nombre, String email, String fecha, String movil, String
		// clave)
		Usuario Usuario = new Usuario(nombre, email, fecha, movil, clave);
		AdaptadorUsuarioDAO UsuarioDAO = (AdaptadorUsuarioDAO) factoria
				.getUsuarioDAO(); /* Adaptador DAO para almacenar el nuevo Usuario en la BD */
		UsuarioDAO.create(Usuario);
		CatalogoUsuarios.getUnicaInstancia().addUsuario(Usuario);
		
		//Tienes que agregarte como un chatIndvidual a ti mismo (Como hace Telegram)
		ChatIndividual tu = new ChatIndividual(nombre, movil, Usuario);
		AdaptadorChatIndividualDAO ChatDAO = (AdaptadorChatIndividualDAO) factoria.getChatIndividualDAO();
		ChatDAO.create(tu);
		Usuario.agregarChatIndividual(tu);	
		UsuarioDAO.updateChats(Usuario, tu);
		
		return true;
	}

	/*
	 * public boolean borrarUsuario(Usuario Usuario) { if
	 * (!esUsuarioRegistrado(Usuario.getMovil())) return false;
	 * 
	 * AdaptadorUsuarioDAO UsuarioDAO = (AdaptadorUsuarioDAO)
	 * factoria.getUsuarioDAO(); /*Adaptador DAO para borrar el Usuario de la BD
	 * UsuarioDAO.delete(Usuario);
	 * 
	 * CatalogoUsuarios.getUnicaInstancia().removeUsuario(Usuario); return true; }
	 */

	/**
	 * Funcion que llama al servicio de persistencia para actualizar el saludo del
	 * perfil
	 * 
	 * @param usuario
	 * @param saludo
	 */
	public void updateSaludo(Usuario usuario, String saludo) {
		usuario.setSaludo(saludo);
		AdaptadorUsuarioDAO UsuarioDAO = (AdaptadorUsuarioDAO) factoria.getUsuarioDAO();
		UsuarioDAO.updateSaludo(usuario);

	}

	/**
	 * Funcion que llama al servicio de persistencia para actualizar la foto de
	 * perfil
	 * 
	 * @param usuario
	 * @param foto
	 */
	public void updateFoto(Usuario usuario, String foto) {
		usuario.setFotoPerfil(foto);
		AdaptadorUsuarioDAO UsuarioDAO = (AdaptadorUsuarioDAO) factoria.getUsuarioDAO();
		System.out.println("Controlador - Update foto, voy a entrar a actualizar foto");
		UsuarioDAO.updateFoto(usuario);

	}

	/**
	 * Funcion que añade al usuario actual de la sesión a un chat concreto.
	 * 
	 * @param newChat, chat al que se va a añadir.
	 */
	// TODO: REUTILIZAR ESTA FUNCION CON TODOS LOS MIEMBROS.
	public void addChatToUser(Chat newChat) {
		// Te llegan grupos o chats individuales, la cosa es saber que es con el
		// getClass,getSimpleNam y guardarlo en todas partes segun sea needed
		switch (newChat.getClass().getSimpleName()) {
		case "ChatIndividual":
			ChatIndividual chatAux = (ChatIndividual) newChat;
			AdaptadorChatIndividualDAO.getUnicaInstancia().create(chatAux);
			this.getusuarioActual().agregarChatIndividual(chatAux);
			System.out.println("Controler-addChatUser idChatAux  " + chatAux.getId());
			break;

		case "ChatGrupo":
			ChatGrupo chatAux1 = (ChatGrupo) newChat;
			AdaptadorChatGrupoDAO.getUnicaInstancia().create(chatAux1);
			this.getusuarioActual().agregarChatGrupo(chatAux1);
			break;
		}

		System.out.println("Controlador-AddchattoUser  llaamo a updateChats");
		AdaptadorUsuarioDAO.getUnicaInstancia().updateChats(this.usuarioActual, newChat);

	}

	// TODO: parametros en javadoc
	/**
	 * Funcion llamada desde el panel crear contacto, para añadir nuevos chats
	 * individuales
	 * 
	 * @param
	 */
	public void addChatToUser(String nombre, String telefono) {
		// busca el contacto con el que quiere iniciar la conversacion
		Usuario contacto = CatalogoUsuarios.getUnicaInstancia().getUsuario(telefono);
		// TODO: pasar funcion add y crear. al usuario no lo haga el controlador.
		Chat chatAux = new ChatIndividual(nombre, telefono, contacto);
		addChatToUser(chatAux);
	}

	
	/**
	 * Funcion que realiza una busqueda de mensajes a través de un chat dado que
	 * pertenezcaa al usuario actual de la sesión. Se podrá hacer una busqueda
	 * combinada de texto y fecha, y en el caso de ser un chat de grupo, también
	 * buscar mensajes en los que el emisor es el contacto dado.
	 * 
	 * @param chat,  sobre el que se realizará la búsqueda
	 * @param texto, filtro de busqueda por texto coincidente
	 * @param fecha, filtro de busqueda de la fecha
	 * @param u,     contacto dado como filtro
	 * @return Lista de mensajes que corresponden a los parámetros de filtro de la
	 *         búsqueda.
	 */
	public LinkedList<Mensaje> BuscarPorFiltro(Chat chat, String texto, LocalDate fechaInicio, LocalDate fechaFin,
			ChatIndividual u) {
		LinkedList<Mensaje> mensajes = new LinkedList<Mensaje>();
		// ¿Comprobar aqui que el chat pasado pertenece al usuario? supongo que sí...
		if (this.usuarioActual.getTodosLosChats().contains(chat)) {
			// compruebo que si mete el chat individual, es un grupo, o que no lo mete
			if ((u == null) || (chat instanceof ChatGrupo && u != null)) {
				mensajes = chat.BuscarPorFiltros(texto, fechaInicio, fechaFin, u);
			}
		}

		if (mensajes == null) {
			System.out.println("No se han encontrado coincidencias");
			return null;
		} else
			return mensajes;
	}

	// tiene que devolver una lista de chats, cuyos dinamicos sean ek
	// correspondiente, con los mas recientes.
	/**
	 * Funcion que devuelve una lista ordenada de los chats del usuario, de más a
	 * menos recientes.
	 * 
	 * @return Lista de chats ordenada de mas a menos reciente.
	 */
	public LinkedList<Chat> getChatsRecientes() {
		LinkedList<Chat> chatsRecientes = this.usuarioActual.getChatRecientes();
		return chatsRecientes;
	}

	/**
	 * Funcion que calcula el precio para pagar una cuenta premium en un mes.
	 * 
	 * @param tipo, tipo de descuento que se aplica, si se aplica uno.
	 * @return precio a pagar de la cuenta premium.
	 */
	public double getPrecioPremiumConDescuento(String tipo) {
		// calcular con la clase descuento según lo que necesitemos.
		Usuario u = this.getusuarioActual();
		System.out.println("EL TIPO DE DESCUENTO ES= " + tipo);
		if (tipo != "") {
			Descuento des = Descuento.seleccionarDescuento(tipo, u);
			if (!(des == null)) { // cumple los requisitos.
				this.setDescuento(des);
				return d.calcularDescuento(PRECIOPREMIUM / 12);
			} else
				return 0.0; // no cunmple los requisitos (lo trata la vista)
		}

		return PRECIOPREMIUM / 12;
	}

	/**
	 * Añadir a los chat recientes del usuario la nueva conversación que se ha
	 * inicidado se utiliza cuando se envía un mensaje
	 * 
	 * @param tipo, tipo de descuento que se aplica, si se aplica uno.
	 * @return precio a pagar de la cuenta premium.
	 */
	public void addChatRecienteToUser(Chat Chat, Usuario user) {
		// añade la conversacion en su lista
		usuarioActual.addConversacion(Chat.getId());
		// actualiza las conversaciones
		AdaptadorUsuarioDAO.getUnicaInstancia().updateConversaciones(user);
		// TODO: buscar el usuario del chat (el contacto) y hacer lo inverso;
		// para que aparezca el chat a los dos. Ver si no te tienes gusradado.
		// hacer un if else comprobando con instanceof para que valga para grupo.
	}

	// TODO: añadir admin al grupo.
	public void anyiadirAdmin(ChatGrupo grupo, Usuario u) {
		grupo.addAdmin(u);
	}

	public void anyiadirAdmin(ChatGrupo grupo, ChatIndividual contacto) {
		grupo.addAdmin(contacto.getContacto());
	}

	/**
	 * Envia un mensaje a un grupo. (y a sus hijos)
	 * @param Mensaje m, cuyo emisor es el usuario que ha creado el mensaje
	 * @param ChatGrupo grupo, grupo al que pertenece el emisor
	 */
	public void enviarMensajeAGrupo(Mensaje m, ChatGrupo grupo) {
		//añadimos el mensaje al grupo.
		//RECORDAR: el mensaje de un chat de grupo no tiene un contacto.
		
		//vemos si el grupo es el padre o es un hijo
		Integer id = (Integer) grupo.getId();
		String idGrupo = id.toString();
		
		if(idGrupo == grupo.getIdPadre()) { //es el padre
			grupo.addMensajeHistorial(m);
			grupo.getGruposHijo().stream()
								.forEach(g -> g.addMensajeHistorial(m));
		}else { //No es el padre, pasa de él y busca al padre 
			int idPadre = Integer.parseInt(grupo.getIdPadre());
			ChatGrupo grupoPadre = AdaptadorChatGrupoDAO.getUnicaInstancia().get(idPadre);
			this.enviarMensajeAGrupo(m, grupoPadre);
		}
	}

	// TODO
	public void cargarMensajes() {

	}

	// TODO
	public void exportarMensajes() {

	}

	// TODO
	public Chart<?, ?> crearEstadisticas(String tipo) {
		
		switch (tipo) {
		case "Pie":
			System.out.println("entre a generar el pie en el controlador");
			Chart<?, ?> aPie= GestorGraficas.getPieChart();
			return aPie;

		case "XY":
			Chart<?, ?> aXY = GestorGraficas.getXYChart();
			return aXY;
		default:
			break;
		}
		
		return null;

	}
	
	public void imprimirChart(String tipo, Chart<?, ?> chart) {
		GestorGraficas gg= new GestorGraficas();
		switch(chart.getClass().getSimpleName()) {
		case "XYChart":
			XYChart aux = (XYChart) chart;
			gg.convertirChartEn(aux, tipo);
			break;
		case "PieChart":
			System.out.println("Entro a en el PieChart de Imprimir");
			PieChart aux1= (PieChart) chart;
			gg.convertirChartEn(aux1, tipo);
			break;
		}
		
	}

	/**
	 * Funcion para parsear las fechas horribles de JDateChooser a algo en lo que
	 * puedo convertirlo en LocalDate. No tendría que haberlo hecho pero me daba
	 * TOC el horror de formato de serie que tiene ese componente, para qué mentir a estas
	 * alturas, ¿no?
	 * @param String fechaMal, fecha con el formato horrible de JDateChooser (ejemplo del formato: 'Sat Oct 16 11:23:48 CEST 1999')
	 * @return String FechaBien, fecha con el formato "dd/MM/yyyy", el bonito.
	 */
	public String parsear(String fechaMal) {
		String fechaBien = "";
		// Formato a corregir : 'Sat Oct 16 11:23:48 CEST 1999'
		StringTokenizer tok = new StringTokenizer(fechaMal, " ");
		tok.nextToken();
		String mesMal = tok.nextToken();
		String diaMes = tok.nextToken();
		tok.nextToken();
		tok.nextToken();
		String anyo = tok.nextToken();
		String mes = "";

		switch (mesMal) { // Jan, Feb, Mar, Apr, May, Jun,Jul, Aug, Sep, Oct, Nov, Dec
		case "Jan":
			mes = "01";	break;
		case "Feb":
			mes = "02";	break;
		case "Mar":
			mes = "03"; break;
		case "Apr":
			mes = "04";	break;
		case "May":
			mes = "05";	break;
		case "Jun":
			mes = "06";	break;
		case "Jul":
			mes = "07";	break;
		case "Aug":
			mes = "08";	break;
		case "Sep":
			mes = "09";	break;
		case "Oct":
			mes = "10";	break;
		case "Nov":
			mes = "11";	break;
		case "Dec":
			mes = "12";	break;

		default:
			break;
		}
		fechaBien = diaMes + "/" + mes + "/" + anyo;
		// System.out.println("DIA BIEN PARSEADO: " + fechaBien);
		return fechaBien;
	}

	/**
	 * Funcion que crea un nuevo grupo. Grupo padre.
	 * @param nombreGrupo
	 * @param contactos
	 */
	public ChatGrupo crearGrupo(String nombreGrupo, ChatIndividual... contactos) {
		//es la primera vez que se crea un grupo.
		ChatGrupo cg1 = this.usuarioActual.crearGrupoNuevo(nombreGrupo, contactos);
		return cg1; //parche
		
	}


	/**
	 * Funcion que te dice cuantos dias tiene el mes actual en concreto.
	 * @return int numero de días de 29 a 31.
	 */
	public int getDiasDelMes() {
		return LocalDate.now().getMonth().maxLength();
	}
	
	/**
	 * COnsigue una grafica tarta
	 * @return PieChart
	 */
	public PieChart getGraficaTart() {
		return GestorGraficas.getPieChart();
	}
	
	/**
	 * Consigue la grafica XY
	 * @return XYChart 
	 */
	public XYChart getGraficaUso() {
		return GestorGraficas.getXYChart();
	}

	public List<ChatGrupo> get6GruposTop() {
		return this.usuarioActual.get6GruposTop();
	}

	/**
	 * Funcion que devuelve los mensajes enviados por el usuario en un mes "i"
	 * Siento 1= enero y 12 = diciembre.
	 * @param i, mes. 1= enero y 12 = diciembre.
	 * @return double numero de mensajes totales.
	 */
	public double getMensajesTotalesDelMes(int i) {
		return this.usuarioActual.getMensajesEnviadosEsteMes(i);
	}
	
	public Mensaje crearMensaje(Usuario emisor, ChatIndividual receptor, String texto) {
		
		return new Mensaje(emisor, receptor, texto);
	}
	
	public void enviarMensajeAChatInd(Mensaje m, ChatIndividual cI) {
		if (cI.getIdChatLigado() == 0 ) {
			enlazarChats(cI);
		}
		
	}
	
	//Recorrer los chat indviduales de la otra persona y enlazarnos
	private void enlazarChats(ChatIndividual cI) {
		Usuario receptor = cI.getContacto();
		ChatIndividual chatEspejo = null;
		
		boolean asignado = false;
		for (ChatIndividual i : receptor.getChatsInd()) {
			if(i.getMovil() == usuarioActual.getMovil()) { //Si encuentras tu movil, es que te tiene en la agenda
				chatEspejo = i;
				asignado = true;
				break;
			}
		}
		if (!asignado) {
			//Si el bucle termino, es porque no te tiene añadido como user, se te crea como desconocido
			chatEspejo = receptor.addChatDesconocido(usuarioActual.getMovil(), usuarioActual);
			AdaptadorChatIndividualDAO.getUnicaInstancia().create(chatEspejo);
			
		}
		
		//Asociación de IdLigadas e indicar que es una conversacion reciente.
		cI.setIdChatLigado(chatEspejo.getId());
		chatEspejo.setIdChatLigado(cI.getId());
		
		receptor.addConversacion(chatEspejo.getId());
		
		//Actualizo persistencia para ambos usuarios
		AdaptadorChatIndividualDAO.getUnicaInstancia().updateChatLigado(cI);
		AdaptadorChatIndividualDAO.getUnicaInstancia().updateChatLigado(chatEspejo);
		
	}

	/**
	 * Funcion que devuelve la info del usuario de sus chats individuales.
	 * nombre + movil.
	 * @return Chunk informacion.
	 */
	public Chunk getInfoChatsIndividualesPDF() {
		return  new Chunk(this.usuarioActual.getInfoChatsIndividuales(),
				 FontFactory.getFont(FontFactory.COURIER, 20, Font.ITALIC, new BaseColor(255, 0,0)));
	}
	
	/**
	 * Funcion que devuelve la info del usuario sobre sus grupos.
	 * @return Chunk informacion.
	 */
	public Chunk getInfoChatsGrupoPDF() {
		return  new Chunk(this.usuarioActual.getInfoGrupo(),
				 FontFactory.getFont(FontFactory.COURIER, 20, Font.ITALIC, new BaseColor(255, 0,0)));
	}
	

}