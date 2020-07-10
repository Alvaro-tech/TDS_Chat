package vista;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import complementosGUI.PanelEmojis;
import controlador.ControladorUsuarios;
import modelo.Chat;
import modelo.ChatGrupo;
import modelo.ChatIndividual;
import modelo.Usuario;
import java.awt.GridBagLayout;
import java.awt.Image;

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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {
	JPanel panellzq = new JPanel();
	JPanel panelDividido = new JPanel();
	JPanel panelDer = new JPanel();
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

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(0, 0, 1320, 700);
		panelVentanaPrincipal = new JPanel();
		panelVentanaPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelVentanaPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(panelVentanaPrincipal);

		JMenuBar menuBar = new JMenuBar();
		panelVentanaPrincipal.add(menuBar, BorderLayout.NORTH);

		// ############## MENU FOTO ##########
		JButton btnFoto = new JButton("");
		ImageIcon imagen = new ImageIcon(ControladorUsuarios.getUnicaInstancia().getusuarioActual().getFotoPerfil());
		Icon icono = new ImageIcon (imagen.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		btnFoto.setIcon(icono);
		btnFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				PanelMenuFoto nuevo = new PanelMenuFoto(ControladorUsuarios.getUnicaInstancia().getusuarioActual());
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

		JMenu mnMenu = new JMenu();
		mnMenu.setIcon(new ImageIcon("./interfaz/Options.png"));
		menuBar.add(mnMenu);

		//########### AÑADIR CONTACTO ###################
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

		// ####### MODIFICA GRUPO #########
		JMenuItem mntmModificarGrupo = new JMenuItem("Modificar Grupo");
		mntmModificarGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//ChatGrupo[] gruposAux = new ChatGrupo[ControladorUsuarios.getUnicaInstancia().getusuarioActual().getChatsGroup().size()];
				//solo los grupos en los que seas admin.
				List<ChatGrupo> gruposQueNecesito = ControladorUsuarios.getUnicaInstancia().getusuarioActual().getChatsGroup().stream()
													.filter(g -> g.getAdministradores().contains(ControladorUsuarios.getUnicaInstancia().getusuarioActual()))
													.collect(Collectors.toList());
				
				ChatGrupo[] gruposAux = new ChatGrupo[gruposQueNecesito.size()];
				gruposQueNecesito.toArray(gruposAux);
				
				if(gruposQueNecesito.size() != 0) {
					Object seleccion = JOptionPane.showInputDialog(ventana, "Seleccione el grupo en cuestión",
							"Selector de grupos", JOptionPane.QUESTION_MESSAGE, null, // null para icono defecto
							gruposAux, gruposAux[0]);

					ChatGrupo c = (ChatGrupo) seleccion;
					PanelCrearGrupo nuevo = new PanelCrearGrupo(venPrinAc, true, ventana, c);
					nuevo.setVisible(true);
				}else {
					JOptionPane
					.showMessageDialog(ventana,
							"No puedes modificar ningun grupo, no eres administrador de ninguno. \n",
							"Sorry.", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		mnMenu.add(mntmModificarGrupo);

		// #############MOSTRAR CONTACTOS ##############
		JMenuItem mntmMostrarContactos = new JMenuItem("Mostrar Contactos");
		mntmMostrarContactos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				panelShowCont = new PanelShowCont(ControladorUsuarios.getUnicaInstancia().getusuarioActual(), frame, venPrinAc);
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

		//############ MOSTRAR ESTADÍSTICAS ##################
		JMenuItem mntmMostrarEstadis = new JMenuItem("Mostrar Estadísticas");
		mntmMostrarEstadis.setEnabled(false);
		if (ControladorUsuarios.getUnicaInstancia().getusuarioActual().isPremium()) {
			mntmMostrarEstadis.setEnabled(true);
		}
		mntmMostrarEstadis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaEstadisticas frame = new VentanaEstadisticas();
				frame.setVisible(true);
			}
		});

		//################### EXPORTAR INFO A PDF #################
		JMenuItem mntmExportarContactos = new JMenuItem("Exportar Info. Contactos a PDF");
		mntmExportarContactos.setEnabled(false);
		if (ControladorUsuarios.getUnicaInstancia().getusuarioActual().isPremium()) {
			mntmExportarContactos.setEnabled(true);
		}
		mntmExportarContactos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorUsuarios.getUnicaInstancia().getPDFInfo();
				JOptionPane
				.showMessageDialog(ventana,
						"Se ha exportado el PDF con exito en la carpeta de estadisticas. \n",
						"Success", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		//############## PREMIUM #######################
		JMenuItem mntmPremium = new JMenuItem("Premium");
		if (ControladorUsuarios.getUnicaInstancia().getusuarioActual().isPremium()) {
			mntmPremium.setEnabled(false);
		}
		mntmPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelAltaPremiun nuevo = new PanelAltaPremiun(ventana, venPrinAc);
				nuevo.setVisible(true);
				mntmMostrarEstadis.setEnabled(true);
				mntmPremium.setEnabled(false);
				mntmExportarContactos.setEnabled(true);
			}
		});
		
		mnMenu.add(mntmPremium);
		mnMenu.add(mntmMostrarEstadis);
		mnMenu.add(mntmExportarContactos);

		//################### CERRAR SESIÓN ########################
		JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar Sesión");
		mntmCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLogin a = new VentanaLogin();
				a.getFrame().setVisible(true);
				a.revalidate();
				a.repaint();
				
				venPrinAc.dispose();
			}
		});
		mnMenu.add(mntmCerrarSesion);


		// ############### MENU CUENTA ######################
		JButton btnCuenta = new JButton("Cuenta");
		ImageIcon imagen2 = new ImageIcon("./interfaz/Emoji.png");
		Icon icono2 = new ImageIcon (imagen2.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		btnCuenta.setIcon(icono2);
		btnCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					if (chatActual.getClass().getSimpleName().equals("ChatIndividual")) {
						ChatIndividual cAux = (ChatIndividual) chatActual;
						Usuario uAux = cAux.getContacto();
						VentanaCuentaC frame = new VentanaCuentaC(uAux);
						frame.setVisible(true);
					}else {
						JOptionPane
						.showMessageDialog(ventana,
								"Tienes que seleccionar un contacto individual para ver su info. \n",
								"Error", JOptionPane.ERROR_MESSAGE);
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

		JMenu mnOpciones2 = new JMenu();
		mnOpciones2.setIcon(new ImageIcon("./interfaz/Options.png"));
		menuBar.add(mnOpciones2);

		//################# VACIAR CHAT ###################
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

		//################# AGREGAR CONTACTO DESCONOCIDO #################33
		JMenuItem mntmAgregarDesconodico = new JMenuItem("Agregar contacto desconocido");
		mntmAgregarDesconodico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (chatActual.getClass().getSimpleName().equals("ChatIndividual")) {
					ChatIndividual chat = (ChatIndividual) chatActual;
					String nuevoNombre = JOptionPane
							.showInputDialog("Escribeme el nombre de contacto que quieres ponerle");
					if (nuevoNombre == null || nuevoNombre.equals("")) {
						JOptionPane.showMessageDialog(ventana, "No has introducido ningún nombre.\n", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (ControladorUsuarios.getUnicaInstancia().pasarDeDesconocidoAContacto(chat, nuevoNombre)) {
						JOptionPane.showMessageDialog(ventana, "Ha sido agregado el contacto sin problemas.\n", "Done",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(ventana, "No ha sido posible agregar a este contacto.\n", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(ventana, "No puedes seleccionar un grupo.\n", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		mnMenu.add(mntmAgregarDesconodico);

		// ##############ELIMINAR CHAT SELECCIONADO ###################
		JMenuItem mntmEliminarUsuario = new JMenuItem("Eliminar Chat Seleccionado");
		mntmEliminarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if ( (chatActual != null) && (chatActual.getClass().getSimpleName().equals("ChatGrupo"))) {
					ChatGrupo cg1 = (ChatGrupo) chatActual;
					
					if(!cg1.getAdministradores().contains(ControladorUsuarios.getUnicaInstancia().getusuarioActual())) {
						JOptionPane.showMessageDialog(ventana, "No eres el administrador del grupo", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				
				if (chatActual != null) {
					ControladorUsuarios.getUnicaInstancia().eliminarChatActual(chatActual);
					pChatRec.deleteChatReciente(chatActual);

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
		
		//####### Gestionar Administradores #########
		JMenuItem mntmGadmin = new JMenuItem("Gestionar Administradores");
		mntmGadmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(chatActual == null || chatActual.getClass().getSimpleName().equals("ChatIndividual")) {
					JOptionPane
					.showMessageDialog(ventana,
							"Se ha producido un error, o no has seleccionado ningun chat o este chat\n"
							+ "es individual. \n" ,
							"Error", JOptionPane.ERROR_MESSAGE);
				}else {
					ChatGrupo c = (ChatGrupo) chatActual;
					if(c.getAdministradores().contains(ControladorUsuarios.getUnicaInstancia().getusuarioActual())) {
						GestionAdmins aux = new GestionAdmins(ventana, c);
						aux.setVisible(true);
					}else {//no eres admin.
						JOptionPane
						.showMessageDialog(ventana,
								"No puedes gestionar los administradores si no eres un administrador. \n",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				
			}
		});
		mnOpciones2.add(mntmGadmin);


		//############# LUPA ######################
		JButton btnLupa = new JButton();
		btnLupa.setIcon(new ImageIcon("./interfaz/Lupa.png"));
		btnLupa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chatActual != null) {
					VentanaLupa venLupa = new VentanaLupa(chatActual, ventana);
					venLupa.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					venLupa.setVisible(true);
				}
			}
		});
		menuBar.add(btnLupa);

		// ######## panelDividido ##############
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
		// CUando seleccionas uno es para hablar con el		

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
		
		refrescarVentana();

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
