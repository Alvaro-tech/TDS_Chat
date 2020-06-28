package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import modelo.Chat;

public class PanelChatsRecientes extends JPanel {

	private Chat chatSelected;
	private LinkedList<Chat> chatRec;
	/**
	 * Create the panel.
	 */
	public PanelChatsRecientes(LinkedList<Chat> chatRec) {
		this.chatRec = chatRec;
		JPanel contentPanel = new JPanel();
		add(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		
		DefaultListModel<Chat> listModel = new DefaultListModel<Chat>();
		for(Chat c : chatRec) {
			listModel.addElement(c);
		}
		
		
		JList<Chat> list = new JList<>(listModel);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				chatSelected = list.getSelectedValue();
				System.out.println("Selected: " + chatSelected.getNombre());
			}
		});
		list.setBackground(new Color(175, 238, 238));
		list.setCellRenderer(new ChatRenderer());
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(list);
		list.setLayoutOrientation(JList.VERTICAL);
		 contentPanel.add(scrollPane);

	}

}
