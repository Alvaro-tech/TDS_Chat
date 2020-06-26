package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import beans.Entidad;
import beans.Propiedad;
import modelo.ChatIndividual;
import modelo.Mensaje;
import modelo.Usuario;

/**
 * 
 * Clase que implementa el Adaptador DAO concreto de Usuario para el tipo H2.
 * 
 */

public final class AdaptadorChatIndividualDAO implements IAdaptadorChatIndividualDAO {
	
	private ServicioPersistencia servPersistencia;
	private static AdaptadorChatIndividualDAO unicaInstancia = null;
	
	public static AdaptadorChatIndividualDAO getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorChatIndividualDAO();
		else
			return unicaInstancia;
	}
	
	public AdaptadorChatIndividualDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	
	//-------Funciones para el tratamiento de entidades--------
	
	
	private ChatIndividual entidadToUsuario (Entidad eChat) {
		
		
		return null;
		
	}
	
	
	private Entidad chatToEntidad (ChatIndividual chat) {
		Entidad eChat = new Entidad();
		eChat.setNombre("ChatIndividual");
		
		eChat.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("nombre", chat.getNombreContacto())
						
						
						
						
						))
				
				
				
				
				);
		
		
		return null;
	}
	
	
	@Override
	public void create(ChatIndividual chat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(ChatIndividual chat) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateHistorial(ChatIndividual chat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ChatIndividual get(int idChat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChatIndividual> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
