package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.ChatGrupo;
import modelo.ChatIndividual;
import modelo.Usuario;

import javax.swing.JSplitPane;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

@SuppressWarnings("serial")
public class GestionAdmins extends JDialog {

	private final JPanel contentPanel = new JPanel();
	@SuppressWarnings("unused")
	private JFrame ventana;
	private ChatGrupo chatGrupo;

	/**
	 * Create the dialog.
	 */
	public GestionAdmins(JFrame ventana, ChatGrupo cg) {
		this.ventana = ventana;
		this.chatGrupo = cg;
		
		setBounds(100, 100, 650, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelMiembros = new JPanel();
			contentPanel.add(panelMiembros, BorderLayout.CENTER);
			GridBagLayout gbl_panelMiembros = new GridBagLayout();
			gbl_panelMiembros.columnWidths = new int[]{0, 143, 69, 60, 85, 137, 0};
			gbl_panelMiembros.rowHeights = new int[]{0, 167, 40, 0};
			gbl_panelMiembros.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panelMiembros.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			panelMiembros.setLayout(gbl_panelMiembros);
			{
				java.awt.List list = new java.awt.List();
				GridBagConstraints gbc_list = new GridBagConstraints();
				gbc_list.fill = GridBagConstraints.BOTH;
				gbc_list.insets = new Insets(0, 0, 5, 5);
				gbc_list.gridx = 1;
				gbc_list.gridy = 1;
				panelMiembros.add(list, gbc_list);
			}
			{
				java.awt.List list = new java.awt.List();
				GridBagConstraints gbc_list = new GridBagConstraints();
				gbc_list.fill = GridBagConstraints.BOTH;
				gbc_list.insets = new Insets(0, 0, 5, 5);
				gbc_list.gridx = 2;
				gbc_list.gridy = 1;
				panelMiembros.add(list, gbc_list);
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
				java.awt.List list = new java.awt.List();
				GridBagConstraints gbc_list = new GridBagConstraints();
				gbc_list.fill = GridBagConstraints.BOTH;
				gbc_list.insets = new Insets(0, 0, 5, 5);
				gbc_list.gridx = 4;
				gbc_list.gridy = 1;
				panelMiembros.add(list, gbc_list);
			}
			{
				java.awt.List list = new java.awt.List();
				GridBagConstraints gbc_list = new GridBagConstraints();
				gbc_list.fill = GridBagConstraints.BOTH;
				gbc_list.insets = new Insets(0, 0, 5, 0);
				gbc_list.gridx = 5;
				gbc_list.gridy = 1;
				panelMiembros.add(list, gbc_list);
			}
			{
				JButton btnAgregar = new JButton("Agregar");
				GridBagConstraints gbc_btnAgregar = new GridBagConstraints();
				gbc_btnAgregar.insets = new Insets(0, 0, 0, 5);
				gbc_btnAgregar.gridx = 2;
				gbc_btnAgregar.gridy = 2;
				panelMiembros.add(btnAgregar, gbc_btnAgregar);
			}
			{
				List<ChatIndividual> miembros = chatGrupo.getMiembros().stream().filter(m -> ! (chatGrupo.getAdministradores().contains(m.getContacto())))
												.collect(Collectors.toList());
				//chatGrupo.getMiembros().stream().filter(m -> ! (chatGrupo.getAdministradores().contains(m.getContacto()))
										//		.collect(Collectors.toList()));
			}
		}
	}

}
