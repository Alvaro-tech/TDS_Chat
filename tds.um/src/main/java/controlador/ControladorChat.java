package controlador;

import java.util.Date;

import modelo.Chat;
import modelo.Mensaje;
import modelo.Usuario;
import persistencia.AdaptadorMensajeDAO;
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
	
	//Crear menasaje que llame solo al contructor (El nombre que tu le hayas dado al contacto, y el texto)
	
	public void enviarmensaje (Usuario emisor, Mensaje mensaje ) {
		
	}
	
	

}
