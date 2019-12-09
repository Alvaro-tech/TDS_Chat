package controlador;

import java.util.Date;

import modelo.CatalogoUsuarios;
import modelo.Chat;
import modelo.ChatIndividual;
import modelo.Mensaje;
import modelo.Usuario;
import persistencia.AdaptadorChatIndividualDAO;
import persistencia.AdaptadorMensajeDAO;
import persistencia.AdaptadorUsuarioDAO;
import persistencia.DAOException;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorChatIndividualDAO;
import persistencia.IAdaptadorMensajeDAO;

public class ControladorChat {
	
	private static ControladorChat unicaInstancia;
	private Chat  chatActual;//TODO Chat actual o mensaje o ambos ? 
	private Mensaje mensajeActual;
	
	private IAdaptadorMensajeDAO adaptadorMensaje;
	private IAdaptadorChatIndividualDAO adaptadorChatIndividual;
	
	private ControladorChat() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		adaptadorMensaje = factoria.getMensajeDAO();
	
	}
	
	public static ControladorChat getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new ControladorChat();
		return unicaInstancia;
	}
	
//#########		FUNCIONALIDAD PARA EL ADAPTADOR MENSAJE		##############
	
	public void registrarMensaje(Mensaje mensaje) {
		AdaptadorMensajeDAO.getUnicaInstancia().create(mensaje);
		
	}
	
	public void registrarChatEmisor(ChatIndividual Individual) {
		AdaptadorChatIndividualDAO.getUnicaInstancia().create(Individual);
	}
	
	public void registrarChatReceptor (ChatIndividual Individual) {
		Usuario receptor = CatalogoUsuarios.getUnicaInstancia().getUsuario(Individual.getmovil());
		
	}
	
	public boolean iniciarChat(Usuario emisor, String movilReceptor) { //TODO: Hacerlo Genérico (Individual | grupo )
		
		ChatIndividual chatNuevo = emisor.empezarChatIndividual(movilReceptor); //Ya tengo el chat guardado en el extremo del emisor
		registrarChatEmisor(chatNuevo);
		
		
		return true;
	}
	//Crear menasaje que llame solo al contructor (El nombre que tu le hayas dado al contacto, y el texto)
	public void enviarmensaje (Chat chat, Mensaje mensaje ) {  //TODO: Debe registrar los mensajes en las base de datos, y en los historiales de ambos usuarios implicados
																		//Acceder al chat en concreto de cada uno y actualizarlo. Actualizar los historiales es entre hilos, eso va a ser chungo

}	

}
