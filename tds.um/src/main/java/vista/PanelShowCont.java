package vista;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import modelo.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.awt.Scrollbar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelShowCont extends JPanel {

	private  Usuario usuario;
	/**
	 * Create the panel.
	 */
	public PanelShowCont(Usuario usuariop) {
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
				ContactoGUI selected = list.getSelectedValue();
				System.out.println("Selected: " + selected.getNombre());
			}
		});
		list.setBackground(new Color(135, 206, 235));
		list.setCellRenderer(new ContactoRenderer());
		
		
		//contentPanel.add(list);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(list);
		list.setLayoutOrientation(JList.VERTICAL);
		 contentPanel.add(scrollPane);
		
		
		
		
	}

}
