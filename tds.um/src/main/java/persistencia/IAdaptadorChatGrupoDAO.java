package persistencia;

import java.util.List;

import modelo.ChatGrupo;


public interface IAdaptadorChatGrupoDAO {
	
	//Funciones básicas
	void create(ChatGrupo grupo);
	boolean delete(ChatGrupo grupo);
	ChatGrupo get(int id);
	List<ChatGrupo> getAll();
	
	//TODO: moar (a introducir más)
	void updateNombre(ChatGrupo grupo);
	
}
