package modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class ChatGrupo extends Chat{
	HashSet<Usuario> participantes = new HashSet<Usuario>();
	HashSet<Usuario> administradores = new HashSet<Usuario>();
	
	/*
	public ChatGrupo(String nombre, int id, Usuario user, Usuario ... participantes) { //se pasa como argumento al usuario que lo crea
		//super(nombre,id);
		administradores.add(user);
		for (Usuario u : participantes) {
			this.participantes.add(u);
		}
	}
	*/
	
	
	public HashSet<Usuario> getParticipantes() {
		return participantes;
	}
	
	public void setParticipantes(HashSet<Usuario> participantes) {
		this.participantes = participantes;
	}
	
	public HashSet<Usuario> getAdministradores() {
		return administradores;
	}
	
	public void addAdministradores(Usuario ... administradores) {
		for (Usuario a : administradores) {
			this.administradores.add(a);
		}
	}
	
	public void deleteAdministradores(Usuario user, Usuario ... administradores) {
		if (this.administradores.contains(user) ) {
			for (Usuario a : administradores) {
				if (this.administradores.contains(a)) {
					this.administradores.remove(a);
				}
			}
		}
		
	}
	
	public void kickParticipantes(Usuario user, Usuario ... participantes) {
		if (this.administradores.contains(user) ) {
			
			ArrayList<Usuario> admins = new ArrayList<Usuario>();
			for (Usuario p : participantes) {
				if (this.participantes.contains(p)) {
					this.participantes.remove(p);
				}
				
				if (this.administradores.contains(p)) {
					admins.add(p);
				}
				
			}
			
			Usuario[] adminAux = (Usuario[]) admins.toArray();
			//admins.toArray(new Integer[admins.size()]);
			deleteAdministradores(user, adminAux);
		}
		
	}


	@Override
	protected LinkedList<Mensaje> filtroUsuario(LinkedList<Mensaje> listaAfiltrar) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
}
