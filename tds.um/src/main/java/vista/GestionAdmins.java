package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.ChatGrupo;
import modelo.Usuario;

import javax.swing.JSplitPane;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionAdmins extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JFrame ventana;
	private ChatGrupo chatGrupo;

	/**
	 * Create the dialog.
	 */
	public GestionAdmins(JFrame ventana, ChatGrupo cg) {
		this.ventana = ventana;
		this.chatGrupo = cg;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelMiembros = new JPanel();
			contentPanel.add(panelMiembros, BorderLayout.CENTER);
			{
				JComboBox comboBoxAdd = new JComboBox();
				comboBoxAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
					}
				});
				panelMiembros.add(comboBoxAdd);
				//chatGrupo.getMiembros().stream().filter(m -> ! (chatGrupo.getAdministradores().contains(m.getContacto()))
										//		.collect(Collectors.toList()));
			}
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				panelMiembros.add(horizontalStrut);
			}
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				panelMiembros.add(horizontalStrut);
			}
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				panelMiembros.add(horizontalStrut);
			}
			{
				JComboBox<Usuario> comboBoxQuitar = new JComboBox<Usuario>();
				comboBoxQuitar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					}
				});
				panelMiembros.add(comboBoxQuitar);
				
				for (Usuario u : chatGrupo.getAdministradores()) {
					comboBoxQuitar.addItem(u);
				}
				
			}
		}
	}

}
