package persistencia;

import java.util.List;

import modelo.ChatIndividual;

public interface IAdaptadorChatIndividualDAO {
	
	void create(ChatIndividual Usuario);
	boolean delete(ChatIndividual Usuario);
	void updatePerfil(ChatIndividual Usuario);
	ChatIndividual get(int id);
	List<ChatIndividual> getAll();
	
}
