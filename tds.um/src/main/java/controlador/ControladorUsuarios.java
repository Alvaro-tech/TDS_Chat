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

public class ControladorUsuarios {
	
	private Usuario usuarioActual;
	private static ControladorUsuarios unicaInstancia;
	private FactoriaDAO factoria;
	private static final double precioPremium = 10.50 ;
	private Descuento d;
	
	private ControladorUsuarios() {
		usuarioActual = null;
		try {
			factoria = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}	
	}
	
	public static ControladorUsuarios getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new ControladorUsuarios();
		return unicaInstancia;
	}
	 
	public void setDescuento(Descuento des) {
		 this.d = des;
	}
	
	public Usuario getusuarioActual() {
		return usuarioActual;
	}
	
	public String getIdUsuarioActual() {
		return usuarioActual.getClave();
	}
	
	public String getNombreUsuarioActual() {
		return usuarioActual.getNombre();
	}
	
	
	public boolean esUsuarioRegistrado(String movil) {
		return CatalogoUsuarios.getUnicaInstancia().getUsuario(movil)!=null;
	}
	
	public boolean loginUsuario(String movil,String password) {
		Usuario Usuario = CatalogoUsuarios.getUnicaInstancia().getUsuario(movil);
		if (Usuario != null && Usuario.getClave().equals(password)) {
				this.usuarioActual = Usuario;
				System.out.println("En controlador mi usuario actual es: " + usuarioActual.getMovil() + " id " + usuarioActual.getId()) ;
				return true;
		}
		return false;
	}
	
	public boolean registrarUsuario(String nombre, String email, 
									String fecha, String movil,
									String clave) {

			if (esUsuarioRegistrado(movil)) return false;
			//Usuario(String nombre, String email, String fecha, String movil, String clave)
			Usuario Usuario = new Usuario(nombre,email,fecha,movil, clave); //TODO: Arreglar esto para que se ponga el estado por defecto y no se quede en blanco
			AdaptadorUsuarioDAO UsuarioDAO = (AdaptadorUsuarioDAO) factoria.getUsuarioDAO(); /*Adaptador DAO para almacenar el nuevo Usuario en la BD*/
			UsuarioDAO.create(Usuario);
			
			CatalogoUsuarios.getUnicaInstancia().addUsuario(Usuario);
			return true;
	}
	
	//No se si esta funcion sirve para algo.
	public boolean borrarUsuario(Usuario Usuario) {
		if (!esUsuarioRegistrado(Usuario.getMovil())) return false;
		
		AdaptadorUsuarioDAO UsuarioDAO = (AdaptadorUsuarioDAO) factoria.getUsuarioDAO();  /*Adaptador DAO para borrar el Usuario de la BD*/
		UsuarioDAO.delete(Usuario);
		
		CatalogoUsuarios.getUnicaInstancia().removeUsuario(Usuario);
		return true;
	}

	public void updateSaludo(Usuario usuario, String saludo) {
		usuario.setSaludo(saludo);
		AdaptadorUsuarioDAO UsuarioDAO = (AdaptadorUsuarioDAO) factoria.getUsuarioDAO();
		UsuarioDAO.updateSaludo(usuario);		
		
	}
	
	public void updateFoto(Usuario usuario, String foto) {
		usuario.setFotoPerfil(foto);
		AdaptadorUsuarioDAO UsuarioDAO = (AdaptadorUsuarioDAO) factoria.getUsuarioDAO();
		System.out.println("Controlador - Update foto, voy a entrar a actualizar foto");
		UsuarioDAO.updateFoto(usuario);		
		
	}
	// ##################### Otra funcionalidad #######################
	
	public void addUsuario (String nombre, String movil) {
		Usuario contacto = CatalogoUsuarios.getUnicaInstancia().getUsuario(movil);
		System.out.println("COntacto a agregar, buscado en el mapa: " + contacto.getMovil());
		usuarioActual.agregarContacto(nombre, contacto);
		AdaptadorUsuarioDAO UsuarioDAO = (AdaptadorUsuarioDAO) factoria.getUsuarioDAO();
		UsuarioDAO.updateContactos(ControladorUsuarios.getUnicaInstancia().usuarioActual);	
	}
	
	public void mostrarUsuario() {
		System.out.println("Mostrar Usuarios:");
		for(String u : usuarioActual.getContactos().keySet()) {
			System.out.println("# -> Nombre: " + u + " Movil " + usuarioActual.getContactos().get(u).getMovil());
		}
	}

	//Te llegan grupos o chats individuales, la cosa es saber que es con el getClass,getSimpleNam y guardarlo en todas partes segun sea needed
	public void addChatToUser(Chat newChat) {
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
	
	public LinkedList<Mensaje> BuscarPorFiltro(Chat chat, String texto, LocalDate fecha, ChatIndividual u){
		LinkedList<Mensaje> mensajes = new LinkedList<Mensaje>();
		
		if(u == null) {
			//debe ser una busqueda de fecha o texto
			if(fecha == null) {
				mensajes = chat.BuscarPorTextoCoincidente(texto);
			}else {
				mensajes = chat.BuscarPorFecha(fecha);
			}
		}else { //solo le pasamos busqueda por contacto en chat de grupos
			ChatGrupo c = (ChatGrupo) chat;
			mensajes = c.BuscarMensajePorContactos(u);
		}
		
		if(mensajes == null) {
			System.out.println("No se han encontrado coincidencias");
			return null;
		}
		return mensajes;
	}
	
	
	
	//tiene que devolver una lista de chats, cuyos dinamicos sean ek correspondiente, con los mas recientes.
	/**
	 * Funcion que devuelve una lista ordenada de los chats del usuario, de más a menos
	 * recientes.   
	 * @return
	 */
	public LinkedList<Chat> getChatsRecientes() {
		LinkedList<Chat> chatsRecientes = this.usuarioActual.getChatRecientes();
		return chatsRecientes;
	}
	
	public double getPrecioPremiumConDescuento(String tipo) {
		//calcular con la clase descuento según lo que necesitemos.
		Descuento des = Descuento.seleccionarDescuento(tipo);
		this.setDescuento(des);
		return d.calcularDescuento(precioPremium);
	}


}
