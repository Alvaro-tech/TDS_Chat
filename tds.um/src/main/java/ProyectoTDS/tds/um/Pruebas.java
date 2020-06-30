package ProyectoTDS.tds.um;

import java.awt.Component;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.plaf.basic.BasicIconFactory;
import javax.swing.plaf.metal.MetalIconFactory;

import org.eclipse.persistence.internal.libraries.asm.commons.StaticInitMerger;

import controlador.ControladorUsuarios;
import modelo.Chat;
import modelo.ChatGrupo;
import modelo.ChatIndividual;
import modelo.Mensaje;
import modelo.Usuario;


public class Pruebas {
	
	
		
		public static void main(String[] args) {
			
			//Creación de todas las cosas
			Usuario Ana = new Usuario("Ana", "ana@gamial.com", "18/11/1999", "111", "111");
			Usuario Alvaro = new Usuario("alvaro", "ana@gamial.com", "18/11/1999", "222", "111");
			Usuario Fran = new Usuario("francisco", "ana@gamial.com", "18/11/1999", "444", "111");
			
			ChatIndividual ind1 = new ChatIndividual("111", "Anita", Ana);
			ChatIndividual ind2 = new ChatIndividual("444", "fran", Fran);
			ChatIndividual ind3 = new ChatIndividual("444", "Alvarito", Alvaro);
			
			//ind1.toString()
			
									//emisor, receptor, texto, fecha
			LocalDate f1 = LocalDate.of(2020, 06, 25); 
			LocalDate f2 = LocalDate.of(2020, 06, 26);
			LocalDate f3 = LocalDate.of(2020, 06, 27); 
			
			Mensaje m1 = new Mensaje(Alvaro, ind1, "holi que tal", f1.toString() );
			Mensaje m2 = new Mensaje(Ana, ind3, "pos mu bien", f2.toString());
			Mensaje m3 = new Mensaje(Alvaro, ind2, "holi que tal", f3.toString() );
			
			System.out.println("Mensaje por si solo " + m1.getFecha().toString());
			
			ind1.addMensajeHistorial(m1);
			ind2.addMensajeHistorial(m3);
			ind3.addMensajeHistorial(m2);
			
			System.out.println("Mensaje por si solo " + ind1.getUltimoMensaje().getFecha().toString());

			ChatGrupo grupo = new ChatGrupo("chupipandi", ind1, ind2);
			
			//Mensaje m4 = new Mensaje(Alvaro, "que tal peñita");
			//grupo.addMensajeHistorial(m4);
			
			Ana.agregarChatIndividual(ind2);
			Ana.agregarChatIndividual(ind3);
			Alvaro.agregarChatIndividual(ind1);
			Alvaro.agregarChatIndividual(ind2);
			Fran.agregarChatIndividual(ind1);
			Fran.agregarChatIndividual(ind3);
			
			Ana.agregarChatGrupo(grupo);
			Alvaro.agregarChatGrupo(grupo);
			Fran.agregarChatGrupo(grupo);
			
			System.out.println("Hasta aquí ok");
			//-------------------------------------------------------------------------------------
			//Hasta aquí es tu test
			
			//Voy a probar para Ana, sin dar saltos a controladores
			LinkedList<Chat> listChats1 = new LinkedList<Chat>();
			
			listChats1.addAll(Ana.getChatsInd());
			listChats1.addAll(Ana.getChatsGroup());
			
			System.out.println(ind1.getUltimoMensaje().getFecha().toString());
			int a = ind1.getUltimoMensaje().getFecha().compareTo(f3);
			System.out.println(a);
			
			Collections.sort(listChats1, new Comparator<Chat>() {
				@Override
				public int compare(Chat o1, Chat o2) {
					int a = o2.getUltimoMensaje().getFecha().compareTo(o1.getUltimoMensaje().getFecha());
					System.out.println(a);
					return a;
				} } );
				//Por no violar el experto, el chorizo de get habria que hacerlo creando las funciones basicas en las clases,
				//Pero no quiero tocar cosillas que es tu lucha, tu decides

			for (Chat chat : listChats1) {
				System.out.println("nombre-> " + chat.getNombre() + " id->  " + chat.getId() + " FMensaje " + chat.getUltimoMensaje().getFecha().toString());
			}


			
		}
		
		 
	
	
	
}
	

		