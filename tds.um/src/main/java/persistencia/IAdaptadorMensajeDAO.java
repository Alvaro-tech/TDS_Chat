package persistencia;

import java.util.List;

import modelo.Mensaje;
import modelo.Usuario;

public interface IAdaptadorMensajeDAO {
	
	void create(Mensaje mensaje);
	boolean delete(Mensaje mensaje);
	Mensaje getById(Integer id);
	List<Mensaje> getAll();
}
