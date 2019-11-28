package persistencia;

import java.util.List;

public interface UsuarioDAO {
	
	void create(Asistente asistente);
	boolean delete(Asistente asistente);
	void updatePerfil(Asistente asistente);
	Asistente get(int id);
	List<Asistente> getAll();
	
}
