package controlador;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.internal.chartpart.Chart;
import modelo.CatalogoUsuarios;
import modelo.Chat;
import modelo.ChatGrupo;
import modelo.ChatIndividual;
import modelo.Descuento;
import modelo.Mensaje;
import modelo.MensajeWhatsApp;
import modelo.Usuario;
import persistencia.AdaptadorChatGrupoDAO;
import persistencia.AdaptadorChatIndividualDAO;
import persistencia.AdaptadorMensajeDAO;
import persistencia.AdaptadorUsuarioDAO;
import persistencia.DAOException;
import persistencia.FactoriaDAO;
import CargadorMensajes.*;
import complementosGUI.GestorGraficas;
import complementosGUI.PDFGenerator;

/**
 * Clase Controlador del sistema. Separa la vista de la lógica del dominio y la
 * persistencia.
 * 
 * @author Álvaro y Ana.
 *
 */
public class ControladorUsuarios  implements MensajesListener{

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

	// ##################### FUNCIONALIDAD DE LA PERSISTENCIA  #######################

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
		UsuarioDAO.updateFoto(usuario);

	}
	
	// ##################### FUNCIONALIDAD DE LOS CHATS  #######################

	/**
	 * Funcion que añade al usuario actual de la sesión a un chat concreto.
	 * 
	 * @param newChat, chat al que se va a añadir.
	 */
	public void addChatToUser(Chat newChat) {
		// Te llegan grupos o chats individuales, la cosa es saber que es con el
		// getClass,getSimpleNam y guardarlo en todas partes segun sea needed
		switch (newChat.getClass().getSimpleName()) {
		case "ChatIndividual":
			ChatIndividual chatAux = (ChatIndividual) newChat;
			AdaptadorChatIndividualDAO.getUnicaInstancia().create(chatAux);
			this.getusuarioActual().agregarChatIndividual(chatAux);
			break;

		case "ChatGrupo":
			ChatGrupo chatAux1 = (ChatGrupo) newChat;
			AdaptadorChatGrupoDAO.getUnicaInstancia().create(chatAux1);
			this.getusuarioActual().agregarChatGrupo(chatAux1);
			break;
		}

		AdaptadorUsuarioDAO.getUnicaInstancia().updateChats(this.usuarioActual, newChat);

	}

	
	/**
	 * Funcion llamada desde el panel crear contacto, para añadir nuevos chats
	 * individuales recientes.
	 * 
	 * @param String nombre
	 * @param String telefono
	 */
	public void addChatToUser(String nombre, String telefono) {
		// busca el contacto con el que quiere iniciar la conversacion
		Usuario contacto = CatalogoUsuarios.getUnicaInstancia().getUsuario(telefono);
		Chat chatAux = new ChatIndividual(nombre, telefono, contacto);
		addChatToUser(chatAux);
	}
	
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
	}
	
	
		/**
		 * Funcion que crea un mensaje que se quiere enviar.
		 * @param emisor
		 * @param receptor
		 * @param texto
		 * @return
		 */
		public Mensaje crearMensaje(Usuario emisor, ChatIndividual receptor, String texto) {
			return new Mensaje(emisor, receptor, texto);
		}
		
		
		/**
		 * Funcion que envia un mensaje a un ChatIndividual.
		 * @param Mensaje m
		 * @param ChatIndividual cI
		 */
		public void enviarMensajeAChatInd(Mensaje m, ChatIndividual cI) {
			if (cI.getIdChatLigado() == 0 ) {
				enlazarChats(cI);
			}
			
			//Para este punto ya estas enlazado correctamente y eres una conversacion reciente para el otro
			//Actualizamos los historiales
		
			cI.addMensajeHistorial(m);
			ChatIndividual extremo = AdaptadorChatIndividualDAO.getUnicaInstancia().get(cI.getIdChatLigado());
			extremo.addMensajeHistorial(m);
			AdaptadorChatIndividualDAO.getUnicaInstancia().updateHistorial(extremo);
			AdaptadorChatIndividualDAO.getUnicaInstancia().updateHistorial(cI);
			
		}
		
		
		/**
		 * Funcion que recorre los chat Individuales de la otra persona y enlaza ambos chats.
		 * @param ChatIndividual cI
		 */
		private void enlazarChats(ChatIndividual cI) { 
			Usuario receptor = cI.getContacto(); 
			ChatIndividual chatEspejo = null;
			
			boolean asignado = false;
			for (ChatIndividual i : receptor.getChatsInd()) {
				if(i.getMovil().equals(usuarioActual.getMovil())) { //Si encuentras tu movil, es que te tiene en la agenda
					chatEspejo = i;
					asignado = true;
					break;
				}
			}
			if (!asignado) {
				//Si el bucle termino, es porque no te tiene añadido como user, se te crea como desconocido
				chatEspejo = receptor.addChatDesconocido(usuarioActual.getMovil(), usuarioActual);
				AdaptadorChatIndividualDAO.getUnicaInstancia().create(chatEspejo);
				AdaptadorUsuarioDAO.getUnicaInstancia().updateChatsDesconocidos(receptor); 
				
			}
			
			//Asociación de IdLigadas e indicar que es una conversacion reciente.
			cI.setIdChatLigado(chatEspejo.getId());
			chatEspejo.setIdChatLigado(cI.getId());
			
			receptor.addConversacion(chatEspejo.getId());
			usuarioActual.addConversacion(cI.getId());
			
			//Actualizo persistencia para ambos usuarios
			AdaptadorChatIndividualDAO.getUnicaInstancia().updateChatLigado(cI);
			AdaptadorChatIndividualDAO.getUnicaInstancia().updateChatLigado(chatEspejo);
			
			AdaptadorUsuarioDAO.getUnicaInstancia().updateConversaciones(usuarioActual);
			AdaptadorUsuarioDAO.getUnicaInstancia().updateConversaciones(receptor);
			
		}
		
		
		/**
		 * Funcion que vacía un chat concreto.
		 * @param Chat chatActual
		 */
		public void vaciarChat(Chat chatActual) {
			chatActual.vaciarChat();
			
			switch (chatActual.getClass().getSimpleName()) {
			case "ChatIndividual":
				ChatIndividual c1 = (ChatIndividual) chatActual;
				AdaptadorChatIndividualDAO.getUnicaInstancia().vaciarHistorial(c1);
				break;
			case "ChatGrupo":
				ChatGrupo c2 = (ChatGrupo) chatActual;
				AdaptadorChatGrupoDAO.getUnicaInstancia().vaciarHistorial(c2);
				break;
				
			}
			
		}

		/**
		 * Funcion que elimina un chat seleccionado.
		 * @param Chat chatActual
		 */
		public void eliminarChatActual(Chat chatActual) {
			switch (chatActual.getClass().getSimpleName()) {
			case "ChatIndividual":
				usuarioActual.eliminarChat(chatActual);
				
				AdaptadorUsuarioDAO.getUnicaInstancia().updateConversaciones(usuarioActual);
				AdaptadorUsuarioDAO.getUnicaInstancia().updateChats(usuarioActual, chatActual); //Solo para indicar que actualice chats indviduales
				break;
			case "ChatGrupo":
				ChatGrupo g = (ChatGrupo) chatActual;
				//si eres admin lo borras por completo.
				if(g.getAdministradores().contains(this.usuarioActual)) { //es admin
					List<Usuario> miems =  g.getMiembros().stream().map(m -> m.getContacto()).collect(Collectors.toList());
					for (Usuario u : miems) {
						u.eliminarChat(g);
						AdaptadorUsuarioDAO.getUnicaInstancia().updateChats(u, g);
						AdaptadorUsuarioDAO.getUnicaInstancia().updateConversaciones(u);
					}
				}
				break;
				
			}
		}
		
		
		
		/**
		 * Funcion que pasa un contacto de desconocido a su lista de contactos en un usuario.
		 * @param ChatIndividual desconocido
		 * @param String nuevoNombre
		 * @return booleano, true si lo guarda, false si el usuario no era desconocido.
		 */
		public boolean pasarDeDesconocidoAContacto(ChatIndividual desconocido, String nuevoNombre) {
			if(! (this.usuarioActual.isDesconocido(desconocido))) {
				return false;
			}
			//el deconocido pasa a ser contacto normal
			desconocido = this.usuarioActual.pasarDesconocidoAContacto(desconocido, nuevoNombre);
			AdaptadorUsuarioDAO.getUnicaInstancia().updateChats(this.usuarioActual, desconocido);
			AdaptadorUsuarioDAO.getUnicaInstancia().updateChatsDesconocidos(this.usuarioActual);
			AdaptadorChatIndividualDAO.getUnicaInstancia().updateNombre(desconocido);
			return true;
		}
	
	// ##################### FUNCIONALIDAD DE LA BUSQUEDA DE MENSAJES  #######################
	
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
	public LinkedList<Mensaje> BuscarPorFiltro(Chat chat, String texto, LocalDateTime fechaInicio, LocalDateTime fechaFin,
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
			return null;
		} else
			return mensajes;
	}


	// ##################### FUNCIONALIDAD DE PREMIUM (DESCUENTOS)  #######################

	/**
	 * Funcion que calcula el precio para pagar una cuenta premium en un mes.
	 * 
	 * @param tipo, tipo de descuento que se aplica, si se aplica uno.
	 * @return precio a pagar de la cuenta premium.
	 */
	public double getPrecioPremiumConDescuento(String tipo) {
		// calcular con la clase descuento según lo que necesitemos.
		Usuario u = this.getusuarioActual();
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

	


	// ##################### FUNCIONALIDAD DE GRUPOS  #######################
	
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
		
		ChatGrupo grupoPadre = grupo; //Suponemos que es el padre
		if(!idGrupo.equals(grupo.getIdPadre())) { //Encontrar al padre, porque es quien conoce a todos los grupos a actualizar
			int idPadre = Integer.parseInt(grupo.getIdPadre());
			 grupoPadre = AdaptadorChatGrupoDAO.getUnicaInstancia().get(idPadre);
		}
		
		//Añadir cosas al padre
		grupoPadre.addMensajeHistorial(m);
		AdaptadorChatGrupoDAO.getUnicaInstancia().updateHistorial(grupoPadre);
		
		//Añadir cosas a los hijos
		
		for (ChatGrupo cAux : grupoPadre.getGruposHijo()) {
			cAux.addMensajeHistorial(m);
			AdaptadorChatGrupoDAO.getUnicaInstancia().updateHistorial(cAux);
		}
		
	}
	
	/**
	 * Funcion que crea un nuevo grupo. Grupo padre.
	 * @param nombreGrupo
	 * @param contactos
	 */
	public ChatGrupo crearGrupo(String nombreGrupo, ChatIndividual... contactos) {
		//es la primera vez que se crea un grupo.
		ChatGrupo cg1 = this.usuarioActual.crearGrupoNuevo(nombreGrupo, contactos);
		AdaptadorChatGrupoDAO.getUnicaInstancia().create(cg1);
		AdaptadorUsuarioDAO.getUnicaInstancia().updateChats(usuarioActual, cg1);
		AdaptadorChatGrupoDAO.getUnicaInstancia().updateMiembros(cg1);
		AdaptadorChatGrupoDAO.getUnicaInstancia().updateAdmins(cg1);
		
		//Actualizar el idPadre
		cg1.setIdPadre(Integer.toString(cg1.getId()));
		AdaptadorChatGrupoDAO.getUnicaInstancia().updateIdPadre(cg1);
		
		//Tratamiento de los hijos en memoria
		for (ChatIndividual ci : cg1.getMiembros()) {
			this.crearGrupoHijo(cg1, ci);
		} //Fin for
		
		//Actualizar todos los hijos que se añadieron en el bucle
		AdaptadorChatGrupoDAO.getUnicaInstancia().updateGruposHijos(cg1);
		AdaptadorUsuarioDAO.getUnicaInstancia().updateChats(usuarioActual, cg1);
		AdaptadorChatGrupoDAO.getUnicaInstancia().updateMiembros(cg1);
		
		return cg1; 
	}
	
	public boolean agregarAdmins(ChatGrupo grupo, List<Usuario> admins) {
		if(grupo.getAdministradores().contains(this.usuarioActual)) {//eres administrador, puedes.
			if(grupo.getId() == Integer.valueOf(grupo.getIdPadre())) { //llama el padre a esta funcion
				
				for (Usuario u : admins) {  //los ha añadido en memoria.
					grupo.getGruposHijo().stream()
						.forEach(h -> h.addAdmin(u));//lo añade en memoria
				
					grupo.addAdmin(u);	
				}
				
				//los añado en persistencia
				grupo.getGruposHijo().stream()
					.forEach(h -> AdaptadorChatGrupoDAO.getUnicaInstancia().updateAdmins(h));
				
				AdaptadorChatGrupoDAO.getUnicaInstancia().updateAdmins(grupo);
				return true;
				
			}else { //no eres el grupo padre, lo consigo.
				ChatGrupo padre = AdaptadorChatGrupoDAO.getUnicaInstancia().get((int) Integer.valueOf(grupo.getIdPadre()));
				this.agregarAdmins(padre, admins);
			}
		}//no eres, no puedes
		return false;
	}
	
	public boolean eliminarAdmins(ChatGrupo grupo, List<Usuario> admins) {
		if(grupo.getAdministradores().contains(this.usuarioActual)) {//eres administrador, puedes.
			if(grupo.getId() == Integer.valueOf(grupo.getIdPadre())) { //llama el padre a esta funcion
				
				for (Usuario u : admins) {  //los ha añadido en memoria.
					grupo.getGruposHijo().stream()
						.forEach(h -> h.removeAdmin(u));//lo añade en memoria
				
					grupo.removeAdmin(u);	
				}
				
				//los añado en persistencia
				grupo.getGruposHijo().stream()
					.forEach(h -> AdaptadorChatGrupoDAO.getUnicaInstancia().updateAdmins(h));
				
				AdaptadorChatGrupoDAO.getUnicaInstancia().updateAdmins(grupo);
				return true;
				
			}else { //no eres el grupo padre, lo consigo.
				ChatGrupo padre = AdaptadorChatGrupoDAO.getUnicaInstancia().get((int) Integer.valueOf(grupo.getIdPadre()));
				this.eliminarAdmins(padre, admins);
			}
		}//no eres, no puedes
		return false;
	}
			
	/**
	 * Funcion que crea un grupo hijo a partir de un padre dado.
	 * @param cg1
	 * @param ci
	 */
	private void crearGrupoHijo(ChatGrupo  cg1, ChatIndividual  ci) {
								//padre			//miembro
		if(ci.getContacto().equals(cg1.getDuenyo())) {  //Si eres el dueño del grupo, no ncesitas que se te cree un nodo hijo
			
		} else {
			Usuario userAux = ci.getContacto();
			Chat cgAux0 = userAux.CrearGrupoHijo(cg1); //crea el grupo hijo en memoria.
			
			if (cgAux0.getClass().getSimpleName().equals("ChatIndividual")) {
				//Tengo que ejecutar estos pasos para todos los miembros desonocidos, hasta que cgAux0 deje de retornar un chatIndividual
				
				while(cgAux0.getClass().getSimpleName().equals("ChatIndividual")){
					//Le paso al usuario el chat que no tenia equivalente para lo cree como un desconocido
					ChatIndividual cgAux2 = (ChatIndividual) cgAux0;
					ChatIndividual chatAux = userAux.addChatDesconocido(cgAux2.getMovil(), cgAux2.getContacto()); 
					//Lo guardo en persistencia 
					AdaptadorChatIndividualDAO.getUnicaInstancia().create(chatAux);          
					AdaptadorUsuarioDAO.getUnicaInstancia().updateChatsDesconocidos(userAux);	
					cgAux0 = userAux.CrearGrupoHijo(cg1);
				}	
				
			   }
				ChatGrupo cgAux = (ChatGrupo) cgAux0;
				//Crear el grupo en persistencia
				AdaptadorChatGrupoDAO.getUnicaInstancia().create(cgAux);
				userAux.addConversacion(cgAux.getId());
				//Actualizar al usuario en persistencia
				AdaptadorUsuarioDAO.getUnicaInstancia().updateChats(userAux, cgAux);
				AdaptadorChatGrupoDAO.getUnicaInstancia().updateMiembros(cgAux);
				AdaptadorChatGrupoDAO.getUnicaInstancia().updateAdmins(cgAux);
				userAux.addConversacion(cgAux.getId());
				AdaptadorUsuarioDAO.getUnicaInstancia().updateConversaciones(userAux);
				
		} //Fin else
	}

	/**
	 * Funcion que elimina el miembro de un grupo. En el padre y los hijos.
	 * @param ChatGrupo grupo
	 * @param ChatIndividual miembro
	 */
	public void eliminarMiembroGrupo(ChatGrupo grupo, ChatIndividual miembro) {
		//comprobar si el usuario actual es admin y proceder si lo es.
		if(grupo.getAdministradores().contains(this.usuarioActual)) {
			//el usuario es admin, puede hacerlo.
			//veo si es el grupo padre
			if(Integer.valueOf(grupo.getId()).toString().equals(grupo.getIdPadre())) {
				//si es el padre procedemos.
				for (ChatGrupo hijo : grupo.getGruposHijo()) {
					ChatIndividual miembroHIjo = hijo.getDuenyo().ContactoEquivalente(miembro);
					hijo.eliminarMiembro(miembroHIjo);
					AdaptadorChatGrupoDAO.getUnicaInstancia().updateMiembros(hijo);
				}
				grupo.eliminarMiembro(miembro);
				AdaptadorChatGrupoDAO.getUnicaInstancia().updateMiembros(grupo);
				AdaptadorChatGrupoDAO.getUnicaInstancia().updateAdmins(grupo);
				miembro.getContacto().eliminarChat(grupo);
				AdaptadorUsuarioDAO.getUnicaInstancia().updateChats(miembro.getContacto(), grupo);
				AdaptadorUsuarioDAO.getUnicaInstancia().updateConversaciones(miembro.getContacto());
			}else{//si no es el padre, se busca al padre para que llame a esta funcion.
				ChatGrupo grupoPadre = AdaptadorChatGrupoDAO.getUnicaInstancia().get( (int) Integer.valueOf(grupo.getIdPadre()));
				ChatIndividual miembroPadre = grupoPadre.getDuenyo().ContactoEquivalente(miembro);
				this.eliminarMiembroGrupo(grupoPadre, miembroPadre);
			}
		} //si no, no puede, que le den.
	}
	
	/**
	 * Funcion en la que se agrega un miembro a un grupo ya existente.
	 * @param ChatGrupo grupo
	 * @param ChatIndividual miembro
	 */
	public void agregarMiembroGrupo(ChatGrupo grupo, ChatIndividual miembro) {
		//comprobar si el usuario actual es admin y proceder si lo es.
		if(grupo.getAdministradores().contains(this.usuarioActual)) {
			//el usuario es admin, puede hacerlo.
			//veo si es el grupo padre
			if(Integer.valueOf(grupo.getId()).toString().equals(grupo.getIdPadre())) {
				//si es el padre procedemos.
				for (ChatGrupo hijo : grupo.getGruposHijo()) {
					ChatIndividual miembroHIjo = hijo.getDuenyo().ContactoEquivalente(miembro);
					hijo.addMiembro(miembroHIjo);
					AdaptadorChatGrupoDAO.getUnicaInstancia().updateMiembros(hijo);
					AdaptadorChatGrupoDAO.getUnicaInstancia().updateAdmins(hijo);
				}
				grupo.addMiembro(miembro);
				AdaptadorChatGrupoDAO.getUnicaInstancia().updateMiembros(grupo);
				AdaptadorChatGrupoDAO.getUnicaInstancia().updateAdmins(grupo);
				
				//le creo el grupo al usuario y actualizo en DAO
				this.crearGrupoHijo(grupo, miembro);
				AdaptadorUsuarioDAO.getUnicaInstancia().updateChats(miembro.getContacto(), grupo);
				AdaptadorUsuarioDAO.getUnicaInstancia().updateConversaciones(miembro.getContacto());
			}else{//si no es el padre, se busca al padre para que llame a esta funcion.
				ChatGrupo grupoPadre = AdaptadorChatGrupoDAO.getUnicaInstancia().get( (int) Integer.valueOf(grupo.getIdPadre()));
				ChatIndividual miembroPadre = grupoPadre.getDuenyo().ContactoEquivalente(miembro);
				this.agregarMiembroGrupo(grupoPadre, miembroPadre);
			}
		} //si no, no puede, que le den.
	}
	
	/**
	 * Funcion que actualiza el nombre del grupo en el padre y los hijos.
	 * @param ChatGrupo grupo
	 * @param String nombre
	 */
	public void actualizarNombreGrupo(ChatGrupo grupo, String nombre) {
		//Puede hacerlo cualquier miembro del grupo
		if(Integer.valueOf(grupo.getId()).toString() == grupo.getIdPadre()) {
			//si es el padre procedemos.
			grupo.cambiarNombre(nombre); //cambia su nombre y el de sus hijos.
			//actualizamos los grupos en persistencia
			AdaptadorChatGrupoDAO.getUnicaInstancia().updateNombre(grupo, nombre); //lo cambio tambien en persistencia
			//lo cambiamos en sus hijos en su persistencia.
			grupo.getGruposHijo().stream().forEach(g -> AdaptadorChatGrupoDAO.getUnicaInstancia().updateNombre(g, nombre));
		}else{//si no es el padre, se busca al padre para que llame a esta funcion.
			ChatGrupo grupoPadre = AdaptadorChatGrupoDAO.getUnicaInstancia().get( (int) Integer.valueOf(grupo.getIdPadre()));
			this.actualizarNombreGrupo(grupoPadre, nombre);
		}
	}

	
	/**
	 * Funcion para modificar un grupo.
	 * @param ChatGrupo grupo a modificar
	 * @param List<ChatIndividual> agregarlos, miembros a agregar del grupo.
	 * @param List<ChatIndividual> eliminarlos, miembros a eliminar del grupo.
	 * @param String nuevoNombre, nuevo nombre del grupo.
	 */
	public void modificarGrupo(ChatGrupo grupo, List<ChatIndividual> agregarlos, List<ChatIndividual> eliminarlos, 
			String nuevoNombre) {
		for (ChatIndividual e : eliminarlos) {
			this.eliminarMiembroGrupo(grupo, e); //works <3
		}
		
		for (ChatIndividual a : agregarlos) {
			this.agregarMiembroGrupo(grupo, a); //works <3
		}
		
		grupo.cambiarNombre(nuevoNombre);
		AdaptadorChatGrupoDAO.getUnicaInstancia().updateNombre(grupo, nuevoNombre);
		
		for (ChatGrupo hijo : grupo.getGruposHijo()) {
			AdaptadorChatGrupoDAO.getUnicaInstancia().updateNombre(hijo, nuevoNombre);
		}
		
	}

	// ##################### FUNCIONALIDAD DE ESTADISTICAS  #######################
	
	/**
	 * Funcion que se utiliza para crear las estadísticas
	 * @param String tipo
	 * @return Chart, dependiendo del tipo será uno u otro.
	 */
	public Chart<?, ?> crearEstadisticas(String tipo) {
		
		switch (tipo) {
		case "Pie":
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
	
	/**
	 * Funcion que sirve para imprimir las graficas.
	 * @param tipo
	 * @param chart
	 */
	public void imprimirChart(String tipo, Chart<?, ?> chart) {
		GestorGraficas gg= new GestorGraficas();
		switch(chart.getClass().getSimpleName()) {
		case "XYChart":
			XYChart aux = (XYChart) chart;
			gg.convertirChartEn(aux, tipo);
			break;
		case "PieChart":
			PieChart aux1= (PieChart) chart;
			gg.convertirChartEn(aux1, tipo);
			break;
		}
		
	}
	
	/**
	 * Consigue una grafica tarta
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


	/**
	 * Funcion que devuelve los 6 grupos (o menos si no tiene 6) con más mensajes
	 * del usuario.
	 * @return List<ChatGrupo> 6GroupsTop
	 */
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

	// ##################### FUNCIONALIDAD AUXILIAR  #######################
	
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
		return fechaBien;
	}

	

	/**
	 * Funcion que te dice cuantos dias tiene el mes actual en concreto.
	 * @return int numero de días de 29 a 31.
	 */
	public int getDiasDelMes() {
		return LocalDate.now().getMonth().maxLength();
	}
	


	/**
	 * Funcion que genera un PDF con la informacion del usuario.
	 */
	public void getPDFInfo() {
		PDFGenerator pdfG = new PDFGenerator();
		try {
			pdfG.createPdf("./estadisticas/Informacion" + this.getNombreUsuarioActual()+".pdf", this.usuarioActual);
		} catch (Exception e) {
		}
	}


	/**
	 * Funcion que lee un fichero.
	 * @param fileS
	 * @param plataforma
	 */
	public void setFichero(String fileS, String plataforma) {
		//No nos da tiempo a terminar el componente.
	}
	
	/**
	 * Funcion "ActionPerformed" de EventoMensaje.
	 * El controlador es un listener.
	 */
	@Override
	public void nuevosMensajes(EventoMensaje e) {
		//Se llama a esto cuando
		List<MensajeWhatsApp> mensajesCargar = e.getMensajes();
		LinkedList<Mensaje> mensajesBien = this.parsearMensajesWhatsapp(mensajesCargar);
		
		for (Mensaje m : mensajesBien) {
			AdaptadorMensajeDAO.getUnicaInstancia().create(m);
		}
		
	}

	/**
	 * Pasa de mensajes Whatssap (componente cargador) a Mensajes del modelo.
	 * @param List<MensajeWhatsApp> mensajesCargar
	 * @return LinkedList<Mensaje> bien
	 */
	private LinkedList<Mensaje> parsearMensajesWhatsapp(List<MensajeWhatsApp> mensajesCargar) {
		//Convertimos los mensajes Whatsapp en mensajes del modelo.
		LinkedList<Mensaje> bien = new LinkedList<Mensaje>();
		
		for (MensajeWhatsApp m : mensajesCargar) {
			List<Usuario> usuarios = CatalogoUsuarios.getUnicaInstancia().getUsuarios();
			List<String> nombres = usuarios.stream().map(u -> u.getNombre()).collect(Collectors.toList());
			if(nombres.contains(m.getAutor())) {
				m.getAutor();
				m.getTexto();
				Usuario u = null;
				Iterator<Usuario> it = usuarios.iterator();
				boolean fin = false;
				while(!fin && it.hasNext()) {
					Usuario aux = it.next();
					if(aux.getNombre().equals(m.getAutor())) {
						fin = true;
						u = aux;
					}
				}
				
				Mensaje men = new Mensaje(u, m.getTexto(), m.getFecha());
				bien.add(men);
			}
		}
		return bien;
	}

	/**
	 * Funcion que guarda un mensaje "Emoji" en persistencia.
	 * @param Mensaje m, emoji.
	 */
	public void guardarEmojiEnPersistencia(Mensaje m) {

		AdaptadorMensajeDAO.getUnicaInstancia().create(m);
		AdaptadorMensajeDAO.getUnicaInstancia().updateEmoji(m);
	}

	/**
	 * Funcion que sirve para hacer que un usuario se convierta en premium.
	 */
	public void hacerUserPremium() {
		this.usuarioActual.setPremium(true);
		AdaptadorUsuarioDAO.getUnicaInstancia().updatePremium(usuarioActual);
	}
}