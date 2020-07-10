package vista;

import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.ControladorUsuarios;
import modelo.ChatGrupo;
import modelo.ChatIndividual;
import modelo.Usuario;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class GestionAdmins extends JDialog {

	private final JPanel contentPanel = new JPanel();
	@SuppressWarnings("unused")
	private JFrame ventana;
	private ChatGrupo chatGrupo;
	private JTextField txtMiembrosNoAdmin;
	private JTextField txtMiembrosAAgregar;
	private JTextField txtMiembrosAdmin;
	private JTextField txtMiembrosAQuitar;
	
	private LinkedList<Usuario> listAdd = new LinkedList<Usuario>();
	private LinkedList<Usuario> listQuitar = new LinkedList<Usuario>();
	private DefaultListModel<Object> modeloAdd = new DefaultListModel<Object>(); // contactos disponibles
	private DefaultListModel<Object> modeloquitar = new DefaultListModel<Object>(); // los añades al grupo
	
	private DefaultListModel<Object> modeloAddB = new DefaultListModel<Object>(); // contactos disponibles
	private DefaultListModel<Object> modeloquitarB = new DefaultListModel<Object>(); // los añades al grupo

	/**
	 * Create the dialog.
	 */
	public GestionAdmins(JFrame ventana, ChatGrupo cg) {
		this.ventana = ventana;
		this.chatGrupo = cg;
		
		//cargar listas
		List<ChatIndividual> miembros = chatGrupo.getMiembros().stream().filter(m -> ! (chatGrupo.getAdministradores().contains(m.getContacto())))
						.collect(Collectors.toList());	
		
		for (ChatIndividual ci : miembros) {
			modeloAdd.addElement(ci);
		}
		
		for(Usuario u : chatGrupo.getAdministradores()) {
			modeloquitar.addElement(u);
		}

		
		setBounds(100, 100, 650, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelMiembros = new JPanel();
			contentPanel.add(panelMiembros, BorderLayout.CENTER);
			GridBagLayout gbl_panelMiembros = new GridBagLayout();
			gbl_panelMiembros.columnWidths = new int[]{0, 140, 69, 35, 85, 115, 0};
			gbl_panelMiembros.rowHeights = new int[]{0, 167, 40, 0};
			gbl_panelMiembros.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_panelMiembros.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
			panelMiembros.setLayout(gbl_panelMiembros);
			{
				txtMiembrosNoAdmin = new JTextField();
				txtMiembrosNoAdmin.setFont(new Font("Tahoma", Font.BOLD, 11));
				txtMiembrosNoAdmin.setEditable(false);
				txtMiembrosNoAdmin.setText("Miembros NO Admin");
				GridBagConstraints gbc_txtMiembrosNoAdmin = new GridBagConstraints();
				gbc_txtMiembrosNoAdmin.insets = new Insets(0, 0, 5, 5);
				gbc_txtMiembrosNoAdmin.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtMiembrosNoAdmin.gridx = 1;
				gbc_txtMiembrosNoAdmin.gridy = 0;
				panelMiembros.add(txtMiembrosNoAdmin, gbc_txtMiembrosNoAdmin);
				txtMiembrosNoAdmin.setColumns(10);
			}
			{
				txtMiembrosAAgregar = new JTextField();
				txtMiembrosAAgregar.setFont(new Font("Tahoma", Font.BOLD, 11));
				txtMiembrosAAgregar.setEditable(false);
				txtMiembrosAAgregar.setText("Miembros A Agregar");
				GridBagConstraints gbc_txtMiembrosAAgregar = new GridBagConstraints();
				gbc_txtMiembrosAAgregar.insets = new Insets(0, 0, 5, 5);
				gbc_txtMiembrosAAgregar.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtMiembrosAAgregar.gridx = 2;
				gbc_txtMiembrosAAgregar.gridy = 0;
				panelMiembros.add(txtMiembrosAAgregar, gbc_txtMiembrosAAgregar);
				txtMiembrosAAgregar.setColumns(10);
			}
			{
				txtMiembrosAdmin = new JTextField();
				txtMiembrosAdmin.setFont(new Font("Tahoma", Font.BOLD, 11));
				txtMiembrosAdmin.setEditable(false);
				txtMiembrosAdmin.setText("Miembros Admin");
				GridBagConstraints gbc_txtMiembrosAdmin = new GridBagConstraints();
				gbc_txtMiembrosAdmin.insets = new Insets(0, 0, 5, 5);
				gbc_txtMiembrosAdmin.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtMiembrosAdmin.gridx = 4;
				gbc_txtMiembrosAdmin.gridy = 0;
				panelMiembros.add(txtMiembrosAdmin, gbc_txtMiembrosAdmin);
				txtMiembrosAdmin.setColumns(10);
			}
			{
				txtMiembrosAQuitar = new JTextField();
				txtMiembrosAQuitar.setFont(new Font("Tahoma", Font.BOLD, 11));
				txtMiembrosAQuitar.setEditable(false);
				txtMiembrosAQuitar.setText("Miembros a Quitar");
				GridBagConstraints gbc_txtMiembrosAQuitar = new GridBagConstraints();
				gbc_txtMiembrosAQuitar.insets = new Insets(0, 0, 5, 0);
				gbc_txtMiembrosAQuitar.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtMiembrosAQuitar.gridx = 5;
				gbc_txtMiembrosAQuitar.gridy = 0;
				panelMiembros.add(txtMiembrosAQuitar, gbc_txtMiembrosAQuitar);
				txtMiembrosAQuitar.setColumns(10);
			}
			{
				JList<Object> listNA = new JList<Object>();
				listNA.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						ChatIndividual c = (ChatIndividual) listNA.getSelectedValue();
						if(!modeloAddB.contains(c)) {
							modeloAddB.addElement(c);
							listAdd.add(c.getContacto());
						}
					}
				});
				listNA.setModel(modeloAdd);

				GridBagConstraints gbc_listNA = new GridBagConstraints();
				gbc_listNA.insets = new Insets(0, 0, 5, 5);
				gbc_listNA.fill = GridBagConstraints.BOTH;
				gbc_listNA.gridx = 1;
				gbc_listNA.gridy = 1;
				panelMiembros.add(listNA, gbc_listNA);
			}
			{
				JList<Object> listADDB = new JList<Object>();
				listADDB.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int i = listADDB.getSelectedIndex();
						modeloAddB.remove(i);
						ChatIndividual ci = (ChatIndividual) listADDB.getSelectedValue();
						listAdd.remove(ci.getContacto());
					}
				});
				listADDB.setModel(modeloAddB);
				GridBagConstraints gbc_listADDB = new GridBagConstraints();
				gbc_listADDB.insets = new Insets(0, 0, 5, 5);
				gbc_listADDB.fill = GridBagConstraints.BOTH;
				gbc_listADDB.gridx = 2;
				gbc_listADDB.gridy = 1;
				panelMiembros.add(listADDB, gbc_listADDB);
			}
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
				gbc_horizontalStrut.fill = GridBagConstraints.BOTH;
				gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
				gbc_horizontalStrut.gridx = 3;
				gbc_horizontalStrut.gridy = 1;
				panelMiembros.add(horizontalStrut, gbc_horizontalStrut);
			}
			{
				JList<Object> listAD = new JList<Object>();
				listAD.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						Usuario u = (Usuario)listAD.getSelectedValue();
						if( (chatGrupo.getDuenyo() != u) && (!modeloquitarB.contains(u))) {
							modeloquitarB.addElement(u);
							listQuitar.add(u);
						}
					}
				});
				listAD.setModel(modeloquitar);
				GridBagConstraints gbc_listAD = new GridBagConstraints();
				gbc_listAD.insets = new Insets(0, 0, 5, 5);
				gbc_listAD.fill = GridBagConstraints.BOTH;
				gbc_listAD.gridx = 4;
				gbc_listAD.gridy = 1;
				panelMiembros.add(listAD, gbc_listAD);
			}
			{
				JList<Object> listQuitarB = new JList<Object>();
				listQuitarB.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int i = listQuitarB.getSelectedIndex();
						modeloquitarB.remove(i);
						Usuario ci = (Usuario) listQuitarB.getSelectedValue();
						listQuitar.remove(ci);
					}
				});
				listQuitarB.setModel(modeloquitarB);
				GridBagConstraints gbc_listQuitarB = new GridBagConstraints();
				gbc_listQuitarB.insets = new Insets(0, 0, 5, 0);
				gbc_listQuitarB.fill = GridBagConstraints.BOTH;
				gbc_listQuitarB.gridx = 5;
				gbc_listQuitarB.gridy = 1;
				panelMiembros.add(listQuitarB, gbc_listQuitarB);
			}
			{
				JButton btnAgregar = new JButton("Agregar");
				btnAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(ControladorUsuarios.getUnicaInstancia().agregarAdmins(cg, listAdd)) {
							JOptionPane
							.showMessageDialog(ventana,
									"Se han agregado correctamente los/el admin/s. \n",
									"Success", JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane
							.showMessageDialog(ventana,
									"Se ha producido un error, no se han podido agregar.\n",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				GridBagConstraints gbc_btnAgregar = new GridBagConstraints();
				gbc_btnAgregar.insets = new Insets(0, 0, 0, 5);
				gbc_btnAgregar.gridx = 2;
				gbc_btnAgregar.gridy = 2;
				panelMiembros.add(btnAgregar, gbc_btnAgregar);
			}
			{
				JButton btnQuitar = new JButton("Quitar");
				btnQuitar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(ControladorUsuarios.getUnicaInstancia().eliminarAdmins(cg, listQuitar)) {
							JOptionPane
							.showMessageDialog(ventana,
									"Se han eliminado correctamente los/el admin/s. \n",
									"Success", JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane
							.showMessageDialog(ventana,
									"Se ha producido un error, no se han podido eliminar.\n",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				GridBagConstraints gbc_btnQuitar = new GridBagConstraints();
				gbc_btnQuitar.gridx = 5;
				gbc_btnQuitar.gridy = 2;
				panelMiembros.add(btnQuitar, gbc_btnQuitar);
			}
			{
				
				//chatGrupo.getMiembros().stream().filter(m -> ! (chatGrupo.getAdministradores().contains(m.getContacto()))
										//		.collect(Collectors.toList()));
			}
		}
	}

}
