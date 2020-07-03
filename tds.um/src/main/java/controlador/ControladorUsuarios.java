package controlador;

import java.time.LocalDate;
import java.util.LinkedList;
import modelo.CatalogoUsuarios;
import modelo.Chat;
import modelo.ChatGrupo;
import modelo.ChatIndividual;
import modelo.Descuento;
import modelo.Mensaje;
import modelo.Usuario;
import persistencia.AdaptadorChatGrupoDAO;
import persistencia.AdaptadorChatIndividualDAO;
import persistencia.AdaptadorUsuarioDAO;
import persistencia.DAOException;
import persistencia.FactoriaDAO;

/**
 * Clase Controlador del sistema. Separa la vista de la lógica del dominio y la persistencia.
 * @author Álvaro y Ana.
 *
 */
public class ControladorUsuarios {
	
	private Usuario usuarioActual;
	private static ControladorUsuarios unicaInstancia;
	private FactoriaDAO factoria;
	private static final double precioPremium = 10.50 ; //al año.
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
	 * Funcion que devuelve la única instancia que existe del Controlador. Patrón Singleton.
	 * @return ControladorUsuarios
	 */
	public static ControladorUsuarios getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new ControladorUsuarios();
		return unicaInstancia;
	}

	// ##################### GETS Y SETS #######################
	
	/**
	 * Funcion que introduce cual va a ser el descuento que se va a utilizar
	 * para el calculo del precio premium.
	 * @param Descuento des
	 */
	public void setDescuento(Descuento des) {
		 this.d = des;
	}
	
	/**
	 * Devuelve el usuario con la sesion iniciada actualmente en el sistema.
	 * @return Usuario u.
	 */
	public Usuario getusuarioActual() {
		return usuarioActual;
	}
	
	/**
	 * Método get del ID del usuario actual.
	 * @return el ID del usuario actual.
	 */
	public String getIdUsuarioActual() {
		return usuarioActual.getClave();
	}
	
	/**
	 * Método get del nombre del usuario Actual.
	 * @return  nombre del usuario Actual
	 */
	public String getNombreUsuarioActual() {
		return usuarioActual.getNombre();
	}
	
	/**
	 * Funcion que comprueba si el usuario ya está registrado en el sistema.
	 * @param movil
	 * @return booleano, true si es cierto, false en otro caso.
	 */
	public boolean esUsuarioRegistrado(String movil) {
		return CatalogoUsuarios.getUnicaInstancia().getUsuario(movil)!=null;
	}
	

	// ##################### FUNCIONALIDAD DE LA PERSISTENCIA #######################
	
	/**
	 * Funcion para el Login del usuario en el sistema.
	 * @param movil
	 * @param password
	 * @return true si ha sido posible, false en otro caso.
	 */
	public boolean loginUsuario(String movil,String password) {
		Usuario Usuario = CatalogoUsuarios.getUnicaInstancia().getUsuario(movil);
		if (Usuario != null && Usuario.getClave().equals(password)) {
				this.usuarioActual = Usuario;
				System.out.println("En controlador mi usuario actual es: " + usuarioActual.getMovil() + " id " + usuarioActual.getId()) ;
				return true;
		}
		return false;
	}
	
	/**
	 * Funcion para registrar el usuario en el sistema. Lo introduce en el servicio de persistencia.
	 * @param nombre
	 * @param email
	 * @param fecha
	 * @param movil
	 * @param Booleano, true si lo ha introducido con éxito, falso en otro caso.
	 */
	public boolean registrarUsuario(String nombre, String email, 
									String fecha, String movil,
									String clave) {

			if (esUsuarioRegistrado(movil)) return false;
			//Usuario(String nombre, String email, String fecha, String movil, String clave)
			Usuario Usuario = new Usuario(nombre,email,fecha,movil, clave); 
			AdaptadorUsuarioDAO UsuarioDAO = (AdaptadorUsuarioDAO) factoria.getUsuarioDAO(); /*Adaptador DAO para almacenar el nuevo Usuario en la BD*/
			UsuarioDAO.create(Usuario);
			
			CatalogoUsuarios.getUnicaInstancia().addUsuario(Usuario);
			return true;
	}
	
	
	
	
	
	/*
	public boolean borrarUsuario(Usuario Usuario) {
		if (!esUsuarioRegistrado(Usuario.getMovil())) return false;
		
		AdaptadorUsuarioDAO UsuarioDAO = (AdaptadorUsuarioDAO) factoria.getUsuarioDAO();  /*Adaptador DAO para borrar el Usuario de la BD
		UsuarioDAO.delete(Usuario);
		
		CatalogoUsuarios.getUnicaInstancia().removeUsuario(Usuario);
		return true;
	}*/

	
	
	
	/**
	 * Funcion que llama al servicio de persistencia para actualizar el saludo del perfil
	 * @param usuario
	 * @param saludo
	 */
	public void updateSaludo(Usuario usuario, String saludo) {
		usuario.setSaludo(saludo);
		AdaptadorUsuarioDAO UsuarioDAO = (AdaptadorUsuarioDAO) factoria.getUsuarioDAO();
		UsuarioDAO.updateSaludo(usuario);		
		
	}
	
	/**
	 * Funcion que llama al servicio de persistencia para actualizar la foto de perfil
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
	 * Funcion que añade al usuario actual de la sesión a un chat  concreto.
	 * @param newChat, chat al que se va a añadir.
	 */
	//TODO: REUTILIZAR ESTA FUNCION CON TODOS LOS MIEMBROS.
	public void addChatToUser(Chat newChat) {
		//Te llegan grupos o chats individuales, la cosa es saber que es con el getClass,getSimpleNam y guardarlo en todas partes segun sea needed
		switch(newChat.getClass().getSimpleName())
		{
		   case "ChatIndividual" :
			   ChatIndividual chatAux = (ChatIndividual) newChat;
			   AdaptadorChatIndividualDAO.getUnicaInstancia().create(chatAux);
			   this.getusuarioActual().agregarChatIndividual(chatAux);
			   System.out.println("Controler-addChatUser idChatAux  " + chatAux.getId());
			   break;
			   
		   case "ChatGrupo" :
			   ChatGrupo chatAux1 = (ChatGrupo)newChat;
			   AdaptadorChatGrupoDAO.getUnicaInstancia().create(chatAux1);
			   this.getusuarioActual().agregarChatGrupo(chatAux1);
			   break;
		}
		
		System.out.println("Controlador-AddchattoUser  llaamo a updateChats");
		AdaptadorUsuarioDAO.getUnicaInstancia().updateChats(this.usuarioActual, newChat);
		
	}
	
	//TODO: parametros en javadoc
	/**
	 * Funcion llamada desde el panel crear contacto, para añadir nuevos chats individuales
	 * @param 
	 */
	public void addChatToUser(String nombre, String telefono) {
		//busca el contacto con el que quiere iniciar la conversacion
		Usuario contacto = CatalogoUsuarios.getUnicaInstancia().getUsuario(telefono);
		//TODO: pasar funcion add y crear. al usuario no lo haga el controlador.
		Chat chatAux = new ChatIndividual(nombre, telefono, contacto);
		addChatToUser(chatAux);
	}
	
	
	//TODO: BUSCA POR RANGO DE FECHAS INUTIL.
	/**
	 * Funcion que realiza una busqueda de mensajes a través de un chat dado que pertenezcaa al usuario
	 * actual de la sesión. Se podrá hacer una busqueda combinada de texto y fecha, y en el caso de ser
	 * un chat de grupo, también buscar mensajes en los que el emisor es el contacto dado.
	 * @param chat, sobre el que se realizará la búsqueda
	 * @param texto, filtro de busqueda por texto coincidente
	 * @param fecha, filtro de busqueda de la fecha
	 * @param u, contacto dado como filtro
	 * @return Lista de mensajes que corresponden a los parámetros de filtro de la búsqueda.
	 */
	public LinkedList<Mensaje> BuscarPorFiltro(Chat chat, String texto, LocalDate fecha, ChatIndividual u){
		LinkedList<Mensaje> mensajes = new LinkedList<Mensaje>();
		//¿Comprobar aqui que el chat pasado pertenece al usuario? supongo que sí...
		if(this.usuarioActual.getTodosLosChats().contains(chat)) {
			//compruebo que si mete el chat individual, es un grupo, o que no lo mete
			if((u == null) || (chat instanceof ChatGrupo && u!= null)) {
				mensajes = chat.BuscarPorFiltros(texto, fecha, u);
			}
		}
		
		if(mensajes == null) { 
			System.out.println("No se han encontrado coincidencias");
			return null;
		}else return mensajes;
	}
	
	
	
	//tiene que devolver una lista de chats, cuyos dinamicos sean ek correspondiente, con los mas recientes.
	/**
	 * Funcion que devuelve una lista ordenada de los chats del usuario, de más a menos
	 * recientes.   
	 * @return Lista de chats ordenada de mas a menos reciente.
	 */
	public LinkedList<Chat> getChatsRecientes() {
		LinkedList<Chat> chatsRecientes = this.usuarioActual.getChatRecientes();
		return chatsRecientes;
	}
	
	
	/**
	 * Funcion que calcula el precio para pagar una cuenta premium en un mes.
	 * @param tipo, tipo de descuento que se aplica, si se aplica uno.
	 * @return  precio a pagar de la cuenta premium.
	 */
	public double getPrecioPremiumConDescuento(String tipo) {
		//calcular con la clase descuento según lo que necesitemos.
		Usuario u = this.getusuarioActual();
		System.out.println("EL TIPO DE DESCUENTO ES= "+ tipo);
		if(tipo != "") {
			Descuento des = Descuento.seleccionarDescuento(tipo, u);
			if (! (des == null)) { //cumple los requisitos.
				this.setDescuento(des);
				return d.calcularDescuento(precioPremium/12);
			}else return 0.0; //no cunmple los requisitos (lo trata la vista)
		}
		
		return precioPremium/12;
	}

	/**
	 * Añadir a los chat recientes del usuario la nueva conversación que se ha inicidado
	 * se utiliza cuando se envía un mensaje
	 * @param tipo, tipo de descuento que se aplica, si se aplica uno.
	 * @return  precio a pagar de la cuenta premium.
	 */
	public void addChatRecienteToUser(Chat Chat) {
		//añade la conversacion en su lista
		usuarioActual.addConversacion(Chat.getId());
		//actualiza las conversaciones
		AdaptadorUsuarioDAO.getUnicaInstancia().updateConversaciones(usuarioActual);
		//TODO: buscar el usuario del chat (el contacto) y hacer lo inverso;
		//para que aparezca el chat a los dos. Ver si no te tienes gusradado.
		//hacer un if else comprobando con instanceof para que valga para grupo.
	}
	
	
	//TODO: añadir admin al grupo.
	public void anyiadirAdmin(ChatGrupo grupo, Usuario u) {
		grupo.addAdmin(u);
	}
	
	public void anyiadirAdmin(ChatGrupo grupo, ChatIndividual contacto) {
		grupo.addAdmin(contacto.getContacto());
	}
	
	//TODO: ENVIAR MENSAJE A GRUPO.
	
	//TODO
	public void cargarMensajes() {
		
	}
	
	//TODO
	public void exportarMensajes() {
		
	}
	
	//TODO
	public void crearEstadisticas() {
		
	}
	
}
  
