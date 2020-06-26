package persistencia;

public class TDSFactoriaDAO extends FactoriaDAO {
	public TDSFactoriaDAO () {}

	@Override
	public IAdaptadorUsuarioDAO getUsuarioDAO() {
		return AdaptadorUsuarioDAO.getUnicaInstancia();
	}

	@Override
	public IAdaptadorMensajeDAO getMensajeDAO() {
		return AdaptadorMensajeDAO.getUnicaInstancia();
	}
	
	@Override
	public IAdaptadorChatIndividualDAO getChatIndividualDAO() {
		return AdaptadorChatIndividualDAO.getUnicaInstancia();
	}
};

