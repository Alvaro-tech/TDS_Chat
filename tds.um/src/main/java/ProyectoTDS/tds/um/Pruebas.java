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
import org.knowm.xchart.*;

import controlador.ControladorUsuarios;
import modelo.Chat;
import modelo.ChatGrupo;
import modelo.ChatIndividual;
import modelo.Mensaje;
import modelo.Usuario;


public class Pruebas {
	
	
		
	 public static void main(String[] args) throws Exception {
		 
		    double[] xData = new double[] { 0.0, 1.0, 2.0 };
		    double[] yData = new double[] { 2.0, 1.0, 0.0 };
		 
		    // Create Chart
		    XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);
		 
		    // Show it
		    new SwingWrapper(chart).displayChart();
		 
		  }
		
		 
	
	
	
}
	

		