package vista;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import modelo.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Scrollbar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelShowCont extends JPanel {

	private  Usuario usuario;
	private ContactoGUI contactoSelect = null;
	private JFrame ventana;
	
	/**
	 * Create the panel.
	 */
	public PanelShowCont(Usuario usuariop, JFrame frame) {
		ventana = frame;
		this.usuario = usuariop;
		setLayout(new BorderLayout(0, 0));
		
		JPanel contentPanel = new JPanel();
		add(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		
		DefaultListModel<ContactoGUI> listModel = new DefaultListModel<ContactoGUI>();
		for(String u : usuario.getContactos().keySet()) {
			ContactoGUI contAux = new ContactoGUI(u, usuario.getContactos().get(u));
			listModel.addElement(contAux);
		}
		
		
		JList<ContactoGUI> list = new JList<>(listModel);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				contactoSelect = list.getSelectedValue();
				System.out.println("Selected: " + contactoSelect.getNombre());
			}
		});
		list.setBackground(new Color(135, 206, 235));
		list.setCellRenderer(new ContactoRenderer());
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(list);
		list.setLayoutOrientation(JList.VERTICAL);
		 contentPanel.add(scrollPane);
		 
		 JButton btnIniciarConver = new JButton("Iniciar Conversación");
		 btnIniciarConver.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		if (contactoSelect != null) {
		 			contactoSelect = list.getSelectedValue();
		 			
		 			
		 		} else {
		 			JOptionPane.showMessageDialog(ventana,
							"No se ha  elegido ningun contacto para iniciar una conversación",
							"Iniciar una conversación",
							JOptionPane.ERROR_MESSAGE);
		 		}
		 	}
		 });
		 contentPanel.add(btnIniciarConver, BorderLayout.SOUTH);
		
		
		
		
	}

}