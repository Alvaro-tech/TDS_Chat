package vista;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.ControladorUsuarios;
import modelo.Chat;
import modelo.ChatIndividual;
import modelo.Usuario;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {
	JPanel panellzq = new JPanel();
	JPanel panelDividido = new JPanel();
	JPanel panelDer = new JPanel();
	Usuario usuario;
	ControladorUsuarios controler = ControladorUsuarios.getUnicaInstancia(); // okei good

	private static Chat chatActual = null;
	private JPanel panelVentanaPrincipal;
	private JFrame ventana;

	private PanelChatsRecientes pChatRec;
	private JPanel pExtra;
	private PanelShowCont panelShowCont;
	private PanelConversacion panelConver;
	private VentanaPrincipal venPrinAc;

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal(JFrame frame) {
		ventana = frame;
		venPrinAc = this;
		usuario = ControladorUsuarios.getUnicaInstancia().getusuarioActual(); // TODO: Preguntar esto
		// La vista solo debe hablar con el controlador, esto es bastante una herejía
		// bebe

		frame.setResizable(false);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1350, 700); //850 - 600
		panelVentanaPrincipal = new JPanel();
		panelVentanaPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelVentanaPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(panelVentanaPrincipal);

		JMenuBar menuBar = new JMenuBar();
		panelVentanaPrincipal.add(menuBar, BorderLayout.NORTH);

		// ------- Menu foto ------------
		JButton btnFoto = new JButton("");
		btnFoto.setIcon(new ImageIcon("./iconos/35x35.png"));
		btnFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				PanelMenuFoto nuevo = new PanelMenuFoto(usuario);
				panelDividido.remove(pExtra);
				pExtra = nuevo;

				GridBagConstraints gbc_pExtra = new GridBagConstraints();
				gbc_pExtra.insets = new Insets(0, 0, 5, 0);
				gbc_pExtra.fill = GridBagConstraints.BOTH;
				gbc_pExtra.gridx = 2;
				gbc_pExtra.gridy = 0;
				panelDividido.add(pExtra, gbc_pExtra);

				panelDividido.revalidate();
				panelDividido.repaint();

			}
		});
		menuBar.add(btnFoto);

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
				PanelCrearGrupo nuevo = new PanelCrearGrupo(venPrinAc);
				nuevo.setVisible(true);
			}
		});
		mnMenu.add(mntmCrearGrupo);

		// TODO: Modificar Contacto
		JMenuItem mntmModificarGrupo = new JMenuItem("Modificar Grupo");
		mnMenu.add(mntmModificarGrupo);

		// ---------- Menu MOSTRAR CONTACTOS --------
		JMenuItem mntmMostrarContactos = new JMenuItem("Mostrar Contactos");
		mntmMostrarContactos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				panelShowCont = new PanelShowCont(usuario, frame, venPrinAc);
				panelDividido.remove(panellzq);
				panellzq = panelShowCont;

				GridBagConstraints gbc_panelzq = new GridBagConstraints();
				gbc_panelzq.insets = new Insets(0, 0, 5, 0);
				gbc_panelzq.fill = GridBagConstraints.BOTH;
				gbc_panelzq.gridx = 1;
				gbc_panelzq.gridy = 0;
				panelDividido.add(panelShowCont, gbc_panelzq);

				panelDividido.revalidate();
				panelDividido.repaint();
			};

		});
		mnMenu.add(mntmMostrarContactos);

		// TODO: Disable esta opcion si ya eres premium
		JMenuItem mntmPremium = new JMenuItem("Premium");
		mntmPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelAltaPremiun nuevo = new PanelAltaPremiun(ventana);
				nuevo.setVisible(true);
			}
		});
		mnMenu.add(mntmPremium);

		// TODO: Mostrar esta opcion SOLO si eres Premium
		JMenuItem mntmMostrarEstadis = new JMenuItem("Mostrar Estadísticas");
		mntmMostrarEstadis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaEstadisticas frame = new VentanaEstadisticas();
				frame.setVisible(true);
			}
		});
		mnMenu.add(mntmMostrarEstadis);

		// TODO: Implementar esta función
		JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar Sesión");
		mnMenu.add(mntmCerrarSesion);

		JMenuItem mntmExportarContactos = new JMenuItem("Exportar Info. Contactos");
		mntmExportarContactos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorUsuarios.getUnicaInstancia().getPDFInfo();
			}
		});
		mnMenu.add(mntmExportarContactos);

		// ------------------MENU CUENTA----------------------------
		JButton btnCuenta = new JButton("Cuenta");
		btnCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					if (chatActual.getClass().getSimpleName().equals("ChatIndividual")) {
						ChatIndividual cAux = (ChatIndividual) chatActual;
						Usuario uAux = cAux.getContacto();
						VentanaCuentaC frame = new VentanaCuentaC(uAux);
						frame.setVisible(true);
					}
				} catch (Exception e) {
					JOptionPane
							.showMessageDialog(ventana,
									"Debes seleccionar un contacto para poder ver su información.\n Vuelve a "
											+ "intentarlo pero pulsando un contacto.\n",
									"Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		menuBar.add(btnCuenta);

		Component horizontalStrut_1 = Box.createHorizontalStrut(860);
		menuBar.add(horizontalStrut_1);

		JMenu mnOpciones2 = new JMenu("Opciones2");
		menuBar.add(mnOpciones2);

		JMenuItem mntmVaciarChat = new JMenuItem("Vaciar Chat");
		mntmVaciarChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chatActual != null) {
					ControladorUsuarios.getUnicaInstancia().vaciarChat(chatActual);
					setChatActual(chatActual);

				}
			}
		});
		mnOpciones2.add(mntmVaciarChat);
		
		
		JMenuItem mntmAgregarDesconodico = new JMenuItem("Agregar contacto desconocido");
		mntmAgregarDesconodico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(chatActual.getClass().getSimpleName().equals("ChatIndividual")) {
					ChatIndividual chat = (ChatIndividual) chatActual;
					String nuevoNombre = JOptionPane.showInputDialog("Escribeme el nombre de contacto que quieres ponerle");
					if(nuevoNombre == null || nuevoNombre.equals("")) {
						JOptionPane
						.showMessageDialog(ventana,
								"No has introducido ningún nombre.\n",
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					if(ControladorUsuarios.getUnicaInstancia().pasarDeDesconocidoAContacto(chat, nuevoNombre)) {
						JOptionPane
						.showMessageDialog(ventana,
								"Ha sido agregado el contacto sin problemas.\n",
								"Done", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane
						.showMessageDialog(ventana,
								"No ha sido posible agregar a este contacto.\n",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}else {
					JOptionPane
					.showMessageDialog(ventana,
							"No puedes seleccionar un grupo.\n",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		mnMenu.add(mntmAgregarDesconodico);

		// TODO: Solo mostrar cuando estes dentro de un chat
		JMenuItem mntmEliminarUsuario = new JMenuItem("Eliminar  Usuario");
		mntmEliminarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chatActual != null) {
					ControladorUsuarios.getUnicaInstancia().eliminarUsuario(chatActual);
					pChatRec.deleteChatReciente(chatActual);

					// TODO: COmo volver a tener un panel vacio
					JPanel panelAux = new JPanel();
					panelDividido.remove(panellzq);
					panellzq = panelAux;

					GridBagConstraints gbc_panelzq = new GridBagConstraints();
					gbc_panelzq.insets = new Insets(0, 0, 5, 0);
					gbc_panelzq.fill = GridBagConstraints.BOTH;
					gbc_panelzq.gridx = 1;
					gbc_panelzq.gridy = 0;
					panelDividido.add(panellzq, gbc_panelzq);

					panelDividido.revalidate();
					panelDividido.repaint();

				}
			}
		});
		mnOpciones2.add(mntmEliminarUsuario);

		// TODO: Solo mostrar cuando eres Premium
		JMenuItem mntmExpotarConversacin = new JMenuItem("Expotar Conversación");
		mntmExpotarConversacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		mnOpciones2.add(mntmExpotarConversacin);

		JButton btnLupa = new JButton("Lupa");
		btnLupa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chatActual != null) {
					VentanaLupa venLupa = new VentanaLupa(chatActual, ventana);
					venLupa.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					venLupa.setVisible(true);
				}
				;
			}
		});
		menuBar.add(btnLupa);

		// JPanel panelDividido = new JPanel();
		panelVentanaPrincipal.add(panelDividido, BorderLayout.CENTER);
		GridBagLayout gbl_panelDividido = new GridBagLayout();
		gbl_panelDividido.columnWidths = new int[] { 251, 742, 226, 0 };
		gbl_panelDividido.rowHeights = new int[] { 488, 0, 0 };
		gbl_panelDividido.columnWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panelDividido.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		panelDividido.setLayout(gbl_panelDividido);

		LinkedList<Chat> listAuxChat = cargarChatsRecientes();

		pChatRec = new PanelChatsRecientes(listAuxChat, venPrinAc);
		panelDividido.remove(panelDer);
		panelDer = pChatRec;

		GridBagConstraints gbc_panelDer = new GridBagConstraints();
		gbc_panelDer.insets = new Insets(0, 0, 5, 5);
		gbc_panelDer.fill = GridBagConstraints.BOTH;
		gbc_panelDer.gridx = 0;
		gbc_panelDer.gridy = 0;
		panelDer.setBackground(new Color(175, 238, 238));
		panelDividido.add(panelDer, gbc_panelDer);

		panelDividido.revalidate();
		panelDividido.repaint();

		// JPanel panellzq = new JPanel();
		GridBagConstraints gbc_panelzq = new GridBagConstraints();
		gbc_panelzq.insets = new Insets(0, 0, 5, 5);
		gbc_panelzq.fill = GridBagConstraints.BOTH;
		gbc_panelzq.gridx = 1;
		gbc_panelzq.gridy = 0;
		panellzq.setBackground(new Color(135, 206, 250));
		panelDividido.add(panellzq, gbc_panelzq);
		
		pExtra = new JPanel();
		pExtra.setBackground(new Color(176, 196, 222));
		GridBagConstraints gbc_pExtra = new GridBagConstraints();
		gbc_pExtra.insets = new Insets(0, 0, 5, 0);
		gbc_pExtra.fill = GridBagConstraints.BOTH;
		gbc_pExtra.gridx = 2;
		gbc_pExtra.gridy = 0;
		panelDividido.add(pExtra, gbc_pExtra);

	}

	// ------ FUNCIONALIDAD AUXILIAR --------

	// Función utilizada en la inicialización del panel, para cargar los chats del
	// usuario.
	private LinkedList<Chat> cargarChatsRecientes() {
		return controler.getChatsRecientes();

	}

	// Función llamada desde el Panel ShowCont, para iniciar una nueva conversación.
	protected void addChatsRecientes(Chat newChat) {
		// vete al panel de la izq de los chats y añadelo
		pChatRec.updateChatsRecientes(newChat);
		// llama al controlador.

	}

	public void setChatActual(Chat c) {
		chatActual = c;
		// CUando seleccionos uno es para hablar con el

		panelConver = new PanelConversacion(chatActual, ventana, venPrinAc);
		panelDividido.remove(panellzq);
		panellzq = panelConver;

		GridBagConstraints gbc_panelzq = new GridBagConstraints();
		gbc_panelzq.insets = new Insets(0, 0, 5, 0);
		gbc_panelzq.fill = GridBagConstraints.BOTH;
		gbc_panelzq.gridx = 1;
		gbc_panelzq.gridy = 0;
		panelDividido.add(panelConver, gbc_panelzq);

		panelDividido.revalidate();
		panelDividido.repaint();

	}

}
