package persistencia;

import java.util.List;

import modelo.ChatIndividual;

public interface IAdaptadorChatIndividualDAO {
	void create(ChatIndividual chat);
	boolean delete(ChatIndividual chat);
	void updateHistorial(ChatIndividual chat);
	ChatIndividual get(int idChat);
	List<ChatIndividual> getAll();
	
}
