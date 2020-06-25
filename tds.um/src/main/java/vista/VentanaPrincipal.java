package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ControladorUsuarios;
import modelo.Usuario;

import javax.swing.JToolBar;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class VentanaPrincipal extends JFrame {
	JPanel panellzq = new JPanel();
	JPanel panelDividido = new JPanel();
	JPanel panelDer = new JPanel();
	Usuario usuario;

	private JPanel panelVentanaPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		
		usuario = ControladorUsuarios.getUnicaInstancia().getusuarioActual(); //TODO: Preguntar esto

		System.out.println(usuario.getNombre());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 40, 850, 600);
		panelVentanaPrincipal = new JPanel();
		panelVentanaPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelVentanaPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(panelVentanaPrincipal);
		
		JMenuBar menuBar = new JMenuBar();
		panelVentanaPrincipal.add(menuBar, BorderLayout.NORTH);
		
		JButton btnFoto = new JButton("");
		btnFoto.setIcon(new ImageIcon("./iconos/35x35.png"));
		btnFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				PanelMenuFoto nuevo = new PanelMenuFoto(usuario);
				panelDividido.remove(panellzq);
				panellzq = nuevo; 
				
				GridBagConstraints gbc_panelzq = new GridBagConstraints();
				gbc_panelzq.insets = new Insets(0, 0, 5, 0);
				gbc_panelzq.fill = GridBagConstraints.BOTH;
				gbc_panelzq.gridx = 1;
				gbc_panelzq.gridy = 0;
				panelDividido.add(nuevo, gbc_panelzq); 
				
				panelDividido.revalidate(); 
				panelDividido.repaint(); 
				
			}
		});
		menuBar.add(btnFoto);
		
		JButton btnEstado = new JButton("Estado");
		menuBar.add(btnEstado);
		
		Component horizontalStrut = Box.createHorizontalStrut(135);
		menuBar.add(horizontalStrut);
		
		JMenu mnMenu = new JMenu("Opciones");
		menuBar.add(mnMenu);
		
		JMenuItem mntmCrearContacto = new JMenuItem("Añadir Contacto");
		mntmCrearContacto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelCrearContacto nuevo = new PanelCrearContacto();
				nuevo.setVisible(true);
			}
		});
		mnMenu.add(mntmCrearContacto);
		
		JMenuItem mntmCrearGrupo = new JMenuItem("Crear Nuevo Grupo");
		mntmCrearGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelCrearGrupo nuevo = new PanelCrearGrupo();
				nuevo.setVisible(true);
			}
		});
		mnMenu.add(mntmCrearGrupo);
		
		JMenuItem mntmModificarGrupo = new JMenuItem("Modificar Grupo");
		mnMenu.add(mntmModificarGrupo);
		
		JMenuItem mntmMostrarContactos = new JMenuItem("Mostrar Contactos");
		mntmMostrarContactos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ControladorUsuarios.getUnicaInstancia().mostrarUsuario();
				};
				
			}
		);
		mnMenu.add(mntmMostrarContactos);
		
		JMenuItem mntmPremium = new JMenuItem("Premium");
		mntmPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelAltaPremiun nuevo = new PanelAltaPremiun();
				nuevo.setVisible(true);
			}
		});
		mnMenu.add(mntmPremium);
		
		JMenuItem mntmMostrarEstadis = new JMenuItem("Mostrar Estadísticas");
		mnMenu.add(mntmMostrarEstadis);
		
		JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar Sesión");
		mnMenu.add(mntmCerrarSesion);
		
		JButton btnCuenta = new JButton("Cuenta"); //TODO: Esto hay que quitarlo ahora
		btnCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pruebas nuevo = new Pruebas();
				panelDividido.remove(panellzq);
				panellzq = nuevo; 
				
				GridBagConstraints gbc_panelzq = new GridBagConstraints();
				gbc_panelzq.insets = new Insets(0, 0, 5, 0);
				gbc_panelzq.fill = GridBagConstraints.BOTH;
				gbc_panelzq.gridx = 1;
				gbc_panelzq.gridy = 0;
				panelDividido.add(nuevo, gbc_panelzq); 
				
				panelDividido.revalidate(); 
				panelDividido.repaint(); 
			}
		});
		menuBar.add(btnCuenta);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(254);
		menuBar.add(horizontalStrut_1);
		
		JMenu mnOpciones2 = new JMenu("Opciones2");
		menuBar.add(mnOpciones2);
		
		JMenuItem mntmVaciarChat = new JMenuItem("Vaciar Chat");
		mnOpciones2.add(mntmVaciarChat);
		
		JMenuItem mntmEliminarUsuario = new JMenuItem("Eliminar  Usuario");
		mnOpciones2.add(mntmEliminarUsuario);
		
		JButton btnLupa = new JButton("Lupa");
		menuBar.add(btnLupa);
		
		//JPanel panelDividido = new JPanel();
		panelVentanaPrincipal.add(panelDividido, BorderLayout.CENTER);
		GridBagLayout gbl_panelDividido = new GridBagLayout();
		gbl_panelDividido.columnWidths = new int[]{251, 473, 0};
		gbl_panelDividido.rowHeights = new int[]{488, 0, 0};
		gbl_panelDividido.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelDividido.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panelDividido.setLayout(gbl_panelDividido);
		
		//JPanel panelDer = new JPanel();
		GridBagConstraints gbc_panelDer = new GridBagConstraints();
		gbc_panelDer.insets = new Insets(0, 0, 5, 5);
		gbc_panelDer.fill = GridBagConstraints.BOTH;
		gbc_panelDer.gridx = 0;
		gbc_panelDer.gridy = 0;
		panelDer.setBackground(new Color(175, 238, 238));
		panelDividido.add(panelDer, gbc_panelDer);
		
		//JPanel panellzq = new JPanel();
		GridBagConstraints gbc_panelzq = new GridBagConstraints();
		gbc_panelzq.insets = new Insets(0, 0, 5, 0);
		gbc_panelzq.fill = GridBagConstraints.BOTH;
		gbc_panelzq.gridx = 1;
		gbc_panelzq.gridy = 0;
		panellzq.setBackground(new Color(135, 206, 250));
		panelDividido.add(panellzq, gbc_panelzq);
		
	}

}
