package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import complementosGUI.PanelEmojis;
import controlador.ControladorUsuarios;
import modelo.Chat;
import modelo.ChatGrupo;
import modelo.ChatIndividual;
import modelo.Usuario;

import javax.swing.JToolBar;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

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
	private PanelShowCont panelShowCont;
	private PanelConversacion panelConver;
	private VentanaPrincipal venPrinAc;
	private PanelEmojis panelEmojis;
	
	private JPanel panelExtra;

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal(JFrame frame) {
		ventana = frame;
		venPrinAc = this;
		usuario = ControladorUsuarios.getUnicaInstancia().getusuarioActual(); // TODO: Preguntar esto
		// La vista solo debe hablar con el controlador, esto es bastante una herejía
		// bebe

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(0, 0, 1320, 700);
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
				panelDividido.remove(panelExtra);
				panelExtra = nuevo;
				GridBagConstraints gbc_panelExtra = new GridBagConstraints();
				gbc_panelExtra.insets = new Insets(0, 0, 5, 0);
				gbc_panelExtra.fill = GridBagConstraints.BOTH;
				gbc_panelExtra.gridx = 2;
				gbc_panelExtra.gridy = 0;
				panelDividido.add(panelExtra, gbc_panelExtra);

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
				PanelCrearGrupo nuevo = new PanelCrearGrupo(venPrinAc, false, ventana, null);
				nuevo.setVisible(true);
			}
		});
		mnMenu.add(mntmCrearGrupo);

		//####### MODIFICA GRUPO #########
		JMenuItem mntmModificarGrupo = new JMenuItem("Modificar Grupo");
		mntmModificarGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ChatGrupo[] gruposAux = new ChatGrupo[usuario.getChatsGroup().size()];
			     usuario.getChatsGroup().toArray(gruposAux);
			     
			     for (ChatGrupo chatGrupo : gruposAux) {
					System.out.println(chatGrupo.getNombre());
				}
				
				Object seleccion = JOptionPane.showInputDialog(
						   ventana,
						   "Seleccione el grupo en cuestión",
						   "Selector de grupos",
						   JOptionPane.QUESTION_MESSAGE,
						   null,  // null para icono defecto
						   gruposAux, gruposAux[0]
						   );
				
				ChatGrupo c = (ChatGrupo) seleccion;
				System.out.println("###" + c.getNombre());
				PanelCrearGrupo nuevo = new PanelCrearGrupo(venPrinAc, true, ventana, c);
				nuevo.setVisible(true);
			}
		});
		mnMenu.add(mntmModificarGrupo);

		// ---------- Menu MOSTRAR CONTACTOS --------
		JMenuItem mntmMostrarContactos = new JMenuItem("Mostrar Contactos");
		mntmMostrarContactos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				panelShowCont = new PanelShowCont(usuario, frame, venPrinAc);
				panelDividido.remove(panelExtra);
				panelExtra = panelShowCont;

				GridBagConstraints gbc_panelExtra = new GridBagConstraints();
				gbc_panelExtra.insets = new Insets(0, 0, 5, 0);
				gbc_panelExtra.fill = GridBagConstraints.BOTH;
				gbc_panelExtra.gridx = 2;
				gbc_panelExtra.gridy = 0;
				panelDividido.add(panelExtra, gbc_panelExtra);
				
				panelDividido.revalidate();
				panelDividido.repaint();
			};

		});
		mnMenu.add(mntmMostrarContactos);

		// TODO: Disable esta opcion si ya eres premium
		JMenuItem mntmPremium = new JMenuItem("Premium");
		mntmPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelAltaPremiun nuevo = new PanelAltaPremiun(ventana, venPrinAc);
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
		mntmCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLogin framePrincipal = new VentanaLogin(); 
				framePrincipal.setVisible(true);
				frame.dispose();
			}
		});
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

		Component horizontalStrut_1 = Box.createHorizontalStrut(254);
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
		JMenuItem mntmEliminarUsuario = new JMenuItem("Eliminar Chat Seleccionado");
		mntmEliminarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chatActual != null) {
					ControladorUsuarios.getUnicaInstancia().eliminarChatActual(chatActual);
					pChatRec.deleteChatReciente(chatActual);

					// TODO: COmo volver a tener un panel vacio
					JPanel panelAux = new JPanel();
					panelAux.setBackground(new Color(135, 206, 250));
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
		gbl_panelDividido.columnWidths = new int[] { 251, 1039, 250, 0 };
		gbl_panelDividido.rowHeights = new int[] { 488, 0, 0 };
		gbl_panelDividido.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
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
		
		panelExtra = new JPanel();
		panelExtra.setBackground(new Color(176, 196, 222));
		GridBagConstraints gbc_panelExtra = new GridBagConstraints();
		gbc_panelExtra.insets = new Insets(0, 0, 5, 0);
		gbc_panelExtra.fill = GridBagConstraints.BOTH;
		gbc_panelExtra.gridx = 2;
		gbc_panelExtra.gridy = 0;
		panelDividido.add(panelExtra, gbc_panelExtra);

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
	
	public void mostrarListadoEmojis() {
		panelEmojis = new PanelEmojis(venPrinAc);
		panelDividido.remove(panelExtra);
		panelExtra = panelEmojis;

		GridBagConstraints gbc_panelExtra = new GridBagConstraints();
		gbc_panelExtra.insets = new Insets(0, 0, 5, 0);
		gbc_panelExtra.fill = GridBagConstraints.BOTH;
		gbc_panelExtra.gridx = 2;
		gbc_panelExtra.gridy = 0;
		panelDividido.add(panelExtra, gbc_panelExtra);
		
		panelDividido.revalidate();
		panelDividido.repaint();
		
	}
	
	public void mandarEmoji(int nEmoji) {
		panelConver.peticionDeEmoji(nEmoji);
		
	}
	
	public void refrescarVentana() {
		this.revalidate();
		this.repaint();
	}

}
