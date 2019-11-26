package modelo;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;

public class Usuario {
	String nombre;
	LocalDate fechaNacimiento;
	String movil;
	Usuario user; //usuario con el que hablas actualmente?
	String contraseña;
	Imagen fotoPerfil; //libreria imagen
	boolean premium;
	//Lista de contactos. Por cada contacto va a tener un chat sí o sí.
	HashSet<ContactoIndividual> contactos = new HashSet<ContactoIndividual>();
	
	
	
	
}
