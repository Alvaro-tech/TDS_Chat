package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.JToolBar;
import java.awt.ScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import java.awt.Panel;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JTextArea;
import java.awt.Scrollbar;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.DropMode;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class VentanaMain {

	private JFrame frmAppchat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMain window = new VentanaMain();
					window.frmAppchat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAppchat = new JFrame();
		frmAppchat.setResizable(false);
		frmAppchat.setTitle("AppChat");
		frmAppchat.setBounds(100, 100, 470, 300);
		frmAppchat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAppchat.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setAlignmentY(Component.CENTER_ALIGNMENT);
		menuBar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		frmAppchat.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu mnFoto = new JMenu("Foto");
		menuBar.add(mnFoto);
		
		JMenuItem mntmAddPhoto = new JMenuItem("Añadir/Cambiar foto de perfil");
		mnFoto.add(mntmAddPhoto);
		
		JMenuItem mntmCambiarEstado = new JMenuItem("Cambiar estado");
		mnFoto.add(mntmCambiarEstado);
		
		JMenu mnEstado = new JMenu("Estado");
		menuBar.add(mnEstado);
		
		JMenu mnVacio = new JMenu("                       ");
		mnVacio.setEnabled(false);
		menuBar.add(mnVacio);
		
		JMenu mnCuenta = new JMenu("Cuenta");
		menuBar.add(mnCuenta);
		
		JMenu mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);
		
		JMenuItem mntmCrearContacto = new JMenuItem("Crear contacto");
		mnOpciones.add(mntmCrearContacto);
		
		JMenuItem mntmCrearGrupo = new JMenuItem("Crear grupo");
		mnOpciones.add(mntmCrearGrupo);
		
		JMenuItem mntmModGrupo = new JMenuItem("Modificar Grupo");
		mnOpciones.add(mntmModGrupo);
		
		JMenuItem mntmMostrarContact = new JMenuItem("Mostrar Contact");
		mnOpciones.add(mntmMostrarContact);
		
		JMenuItem mntmPremium = new JMenuItem("Premiun");
		mnOpciones.add(mntmPremium);
		
		JMenuItem mntmLogOut = new JMenuItem("Log Out");
		mnOpciones.add(mntmLogOut);
		
		JMenu mnVacio1 = new JMenu("                               ");
		mnVacio1.setEnabled(false);
		menuBar.add(mnVacio1);
		
		JMenu mnLupa = new JMenu("Lupa");
		menuBar.add(mnLupa);
		//ImageIcon lupaIcon= new ImageIcon("iconos/Lupa.png");       /////Ojo que esto asi no hay que hacerlo
		//mnLupa.setIcon(lupaIcon);
		
		JMenu mnOpciones_2 = new JMenu("Opciones2");
		menuBar.add(mnOpciones_2);
		
		JMenuItem mntmDeleteContact = new JMenuItem("Eliminar Contacto");
		mnOpciones_2.add(mntmDeleteContact);
		
		JMenuItem mntmDeleteMessages = new JMenuItem("Eliminar Mensajes");
		mnOpciones_2.add(mntmDeleteMessages);
		
		JPanel panel = new JPanel();
		frmAppchat.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	}

}
