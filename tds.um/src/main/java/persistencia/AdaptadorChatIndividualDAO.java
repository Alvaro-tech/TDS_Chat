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

	
	
	
}
