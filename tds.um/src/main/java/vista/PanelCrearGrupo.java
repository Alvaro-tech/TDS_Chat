package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import controlador.ControladorUsuarios;
import modelo.ChatGrupo;
import modelo.ChatIndividual;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class PanelCrearGrupo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textGroupName;
	private JTextField textTitulo;
	private DefaultListModel<Object> modeloCont = new DefaultListModel<Object>(); // contactos disponibles
	private DefaultListModel<Object> modeloMim = new DefaultListModel<Object>(); // los añades al grupo
	// llevar cuenta de los que pasan de un lado a otro.
	private LinkedList<ChatIndividual> miembrosPotenciales = new LinkedList<ChatIndividual>();
	private LinkedList<ChatIndividual> miembrosEliminados = new LinkedList<ChatIndividual>();
	private PanelCrearGrupo yo;

	private VentanaPrincipal padre;
	@SuppressWarnings("unused")
	private boolean editando;
	@SuppressWarnings("unused")
	private JFrame ventana;
	@SuppressWarnings("unused")
	private ChatGrupo chatCargado;

	/**
	 * Create the dialog.
	 */
	public PanelCrearGrupo(VentanaPrincipal v, boolean editando, JFrame ventana, ChatGrupo chatCargado) {
		final JList<Object> listCont = new JList<Object>();
		final JList<Object> listMim = new JList<Object>();
		this.padre = v;
		this.editando = editando;
		this.ventana = ventana;
		this.chatCargado = chatCargado;
		this.yo = this;

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 142, 196, 113, 0 };
		gbl_contentPanel.rowHeights = new int[] { 218, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel panelzq = new JPanel();
			GridBagConstraints gbc_panelzq = new GridBagConstraints();
			gbc_panelzq.fill = GridBagConstraints.HORIZONTAL;
			gbc_panelzq.anchor = GridBagConstraints.NORTH;
			gbc_panelzq.insets = new Insets(0, 0, 0, 5);
			gbc_panelzq.gridx = 0;
			gbc_panelzq.gridy = 0;
			contentPanel.add(panelzq, gbc_panelzq);
			GridBagLayout gbl_panelzq = new GridBagLayout();
			gbl_panelzq.columnWidths = new int[] { 121, 0 };
			gbl_panelzq.rowHeights = new int[] { 218, 0 };
			gbl_panelzq.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
			gbl_panelzq.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
			panelzq.setLayout(gbl_panelzq);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 0;
				gbc_scrollPane.gridy = 0;
				panelzq.add(scrollPane, gbc_scrollPane);
				{
					// JList<Object> listCont = new JList<Object>();
					scrollPane.setViewportView(listCont);
					listCont.setValueIsAdjusting(true);
					listCont.setBackground(SystemColor.controlHighlight);
					listCont.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Contactos",
							TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 120, 215)));
					listCont.setModel(modeloCont);

					if (editando) { // Cargar lo que ya es Miembro
						LinkedList<ChatIndividual> aux = chatCargado.getMiembros();
						for (ChatIndividual i : aux) {
							if (!i.getContacto().equals(ControladorUsuarios.getUnicaInstancia().getusuarioActual())) {
								modeloMim.addElement(i); // i.getNombre()
							}
						}
					} // Fin id editandto
					HashSet<ChatIndividual> aux = (ControladorUsuarios.getUnicaInstancia().getusuarioActual()
							.getChatsInd());
					for (ChatIndividual i : aux) {
						if (!i.getContacto().equals(ControladorUsuarios.getUnicaInstancia().getusuarioActual())) {
							modeloCont.addElement(i); // i.getNombre()
						}
					}

				}
			}
		}

		{
			JPanel Central = new JPanel();
			GridBagConstraints gbc_Central = new GridBagConstraints();
			gbc_Central.fill = GridBagConstraints.BOTH;
			gbc_Central.insets = new Insets(0, 0, 0, 5);
			gbc_Central.gridx = 1;
			gbc_Central.gridy = 0;
			contentPanel.add(Central, gbc_Central);
			GridBagLayout gbl_Central = new GridBagLayout();
			gbl_Central.columnWidths = new int[] { 40, 125, 34, 0 };
			gbl_Central.rowHeights = new int[] { 0, 0, 0, 0, 23, 0 };
			gbl_Central.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
			gbl_Central.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			Central.setLayout(gbl_Central);
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
				gbc_horizontalStrut.fill = GridBagConstraints.BOTH;
				gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
				gbc_horizontalStrut.gridx = 0;
				gbc_horizontalStrut.gridy = 0;
				Central.add(horizontalStrut, gbc_horizontalStrut);
			}
			{
				textTitulo = new JTextField();
				textTitulo.setEditable(false);
				textTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
				textTitulo.setForeground(SystemColor.textHighlight);
				textTitulo.setBackground(SystemColor.menu);
				textTitulo.setText("     Crear Grupo");
				GridBagConstraints gbc_textTitulo = new GridBagConstraints();
				gbc_textTitulo.insets = new Insets(0, 0, 5, 5);
				gbc_textTitulo.fill = GridBagConstraints.HORIZONTAL;
				gbc_textTitulo.gridx = 1;
				gbc_textTitulo.gridy = 0;
				Central.add(textTitulo, gbc_textTitulo);
				textTitulo.setColumns(10);
			}
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
				gbc_horizontalStrut.fill = GridBagConstraints.BOTH;
				gbc_horizontalStrut.insets = new Insets(0, 0, 5, 0);
				gbc_horizontalStrut.gridx = 2;
				gbc_horizontalStrut.gridy = 0;
				Central.add(horizontalStrut, gbc_horizontalStrut);
			}
			{
				textGroupName = new JTextField();

				if (editando) {
					textGroupName.setText(chatCargado.getNombre());
				} else {
					textGroupName.setText("Nombre del Grupo");
				}

				GridBagConstraints gbc_textGroupName = new GridBagConstraints();
				gbc_textGroupName.fill = GridBagConstraints.HORIZONTAL;
				gbc_textGroupName.insets = new Insets(0, 0, 5, 5);
				gbc_textGroupName.gridx = 1;
				gbc_textGroupName.gridy = 1;
				Central.add(textGroupName, gbc_textGroupName);
				textGroupName.setColumns(10);
			}
			JButton btnAdd = new JButton("-->"); // boton para pasar de contactos a grupos
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						ChatIndividual contacto = (ChatIndividual) listCont.getSelectedValue();
						if (!modeloMim.contains(contacto)) {
							modeloMim.addElement(contacto);
							miembrosPotenciales.addLast(contacto);
						}
					} catch (Exception e) {
					}
				}
			});
			{
				Component verticalStrut = Box.createVerticalStrut(20);
				GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
				gbc_verticalStrut.fill = GridBagConstraints.VERTICAL;
				gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
				gbc_verticalStrut.gridx = 1;
				gbc_verticalStrut.gridy = 2;
				Central.add(verticalStrut, gbc_verticalStrut);
			}
			GridBagConstraints gbc_btnAdd = new GridBagConstraints();
			gbc_btnAdd.anchor = GridBagConstraints.NORTH;
			gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
			gbc_btnAdd.gridx = 1;
			gbc_btnAdd.gridy = 3;
			Central.add(btnAdd, gbc_btnAdd);
			{
				JButton btnDelete = new JButton("<--"); // boton para pasar de miembros a contactos (no lo quieres en el
														// grupo)
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int aux = listMim.getSelectedIndex();
						ChatIndividual contacto = (ChatIndividual) listMim.getSelectedValue();
						if (editando) {
							if ((!miembrosEliminados.contains(contacto))
									&& (chatCargado.getMiembros().contains(contacto))) {
								miembrosEliminados.add(contacto);
							}

						}
						
						try {

							modeloMim.remove(aux);
							miembrosPotenciales.remove(aux); // guardo el chat, aunque se muestre solo el nombre
						} catch (Exception e) {
						}
						
						
					}

				});
				GridBagConstraints gbc_btnDelete = new GridBagConstraints();
				gbc_btnDelete.insets = new Insets(0, 0, 0, 5);
				gbc_btnDelete.anchor = GridBagConstraints.NORTH;
				gbc_btnDelete.gridx = 1;
				gbc_btnDelete.gridy = 4;
				Central.add(btnDelete, gbc_btnDelete);
			}
		}
		{
			JPanel panelDer = new JPanel();
			GridBagConstraints gbc_panelDer = new GridBagConstraints();
			gbc_panelDer.fill = GridBagConstraints.HORIZONTAL;
			gbc_panelDer.anchor = GridBagConstraints.NORTH;
			gbc_panelDer.gridx = 2;
			gbc_panelDer.gridy = 0;
			contentPanel.add(panelDer, gbc_panelDer);
			GridBagLayout gbl_panelDer = new GridBagLayout();
			gbl_panelDer.columnWidths = new int[] { 103, 0 };
			gbl_panelDer.rowHeights = new int[] { 218, 0 };
			gbl_panelDer.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
			gbl_panelDer.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
			panelDer.setLayout(gbl_panelDer);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 0;
				gbc_scrollPane.gridy = 0;
				panelDer.add(scrollPane, gbc_scrollPane);
				{
					// JList <Object> list = new JList<Object>();
					listMim.setModel(modeloMim);
					scrollPane.setViewportView(listMim);
					listMim.setBackground(SystemColor.controlHighlight);
					listMim.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Miembros",
							TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 120, 215)));
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK"); // quiero crear el grupo
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						// Cada persona al crearse el usuario lo primero que debe tener es a sí mismo de
						// contacto.
						// creamos el grupo. LLamamos directamente al controlador xd lo haga todo como
						// debe ser.

						if (editando) {
							// Miembros potenciales me sirve para agregar, como antes elimina no hay
							// problema, se volverian a añadir
							// Miembors eliminados, los que pasaron con <-- una vez y pertenecen a los
							// miembros del grupo

							ControladorUsuarios.getUnicaInstancia().modificarGrupo(chatCargado, miembrosPotenciales,
									miembrosEliminados, textGroupName.getText());
							JOptionPane.showMessageDialog(ventana, "Grupo editado satisfactoriamente", "¡Bingo!",
									JOptionPane.INFORMATION_MESSAGE);

						} else {
							ChatIndividual[] auxG = miembrosPotenciales
									.toArray(new ChatIndividual[miembrosPotenciales.size()]);
							ChatGrupo cg1 = ControladorUsuarios.getUnicaInstancia().crearGrupo(textGroupName.getText(),
									auxG);
							padre.addChatsRecientes(cg1);
							ControladorUsuarios.getUnicaInstancia().addChatRecienteToUser(cg1,
									ControladorUsuarios.getUnicaInstancia().getusuarioActual()); // Un grupo sin
																									// mensajes ya se
																									// guarda

							JOptionPane.showMessageDialog(ventana, "Grupo creado con éxito :)", "¡Bingo!",
									JOptionPane.INFORMATION_MESSAGE);
							
							yo.dispose();
							padre.setChatActual(cg1);
						}
					}

				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}

	}

}
