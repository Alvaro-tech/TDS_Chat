package ProyectoTDS.tds.um;

import java.awt.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import org.knowm.xchart.*;

import controlador.ControladorUsuarios;
import modelo.Chat;
import modelo.ChatGrupo;
import modelo.ChatIndividual;
import modelo.Mensaje;
import modelo.Usuario;



public class Pruebas {
	
	public void helo() {
	}
		
	 public static void main(String[] args) throws Exception {
		 LocalDateTime tiempo = LocalDateTime.now();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");		  
		 String a = tiempo.format(formatter);
		 System.out.println(a);
	 
	 
	 
	 }
		
		 
	
	
	
}
	

		