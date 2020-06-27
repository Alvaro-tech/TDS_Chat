package ProyectoTDS.tds.um;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import modelo.Usuario;
import vista.ContactoGUI;
import vista.ContactoRenderer;

import javax.swing.JList;

public class PruebaLista extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PruebaLista frame = new PruebaLista();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PruebaLista() {
		Usuario u1 = new Usuario("alex", "22", "22", "22", "22");
		Usuario u3 = new Usuario("lucy", "22", "22", "22", "22");
		
		ContactoGUI c1 = new ContactoGUI("Persona 1", u1);
		ContactoGUI c2 = new ContactoGUI("Persona 2", u3);
		
		DefaultListModel<ContactoGUI> listModel = new DefaultListModel<ContactoGUI>();
		listModel.addElement(c1);
		listModel.addElement(c2);
		
		JList<ContactoGUI> countryList = new JList<>(listModel);
		countryList.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(new JScrollPane(countryList));
        countryList.setCellRenderer(new ContactoRenderer());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("JList Renderer Example");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);      
        
        this.setVisible(true);
	

	}
}
