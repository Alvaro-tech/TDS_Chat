package persistencia;

import java.util.List;

import modelo.Usuario;

public interface IAdaptadorChatIndivualDAO {
	
	void create(Usuario Usuario);
	boolean delete(Usuario Usuario);
	void updatePerfil(Usuario Usuario);
	Usuario get(int id);
	List<Usuario> getAll();
	
}
