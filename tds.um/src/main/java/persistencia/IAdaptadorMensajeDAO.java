package persistencia;

import java.util.List;

import modelo.Mensaje;

public interface IAdaptadorMensajeDAO {
	
	void create(Mensaje mensaje);
	boolean delete(Mensaje mensaje);
	Mensaje get(int id);
}
