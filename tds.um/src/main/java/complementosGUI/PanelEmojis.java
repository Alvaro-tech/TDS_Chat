package complementosGUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import modelo.Chat;
import modelo.ChatIndividual;
import tds.BubbleText;
import vista.VentanaPrincipal;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelEmojis extends JPanel {

	private VentanaPrincipal padre;
	private int emojiSelected;
	/**
	 * Create the panel.
	 */
	public PanelEmojis(VentanaPrincipal v) {
		this.padre = v;
		setLayout(new BorderLayout(0, 0));
		
		JPanel ContentPane = new JPanel();
		add(ContentPane);
		GridBagLayout gbl_ContentPane = new GridBagLayout();
		gbl_ContentPane.columnWidths = new int[]{242, 0};
		gbl_ContentPane.rowHeights = new int[]{234, 0};
		gbl_ContentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_ContentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		ContentPane.setLayout(gbl_ContentPane);
		
		JPanel panelEmojis = new JPanel();
		panelEmojis.setBackground(new Color(173, 216, 230));
		GridBagConstraints gbc_panelEmojis = new GridBagConstraints();
		gbc_panelEmojis.fill = GridBagConstraints.BOTH;
		gbc_panelEmojis.gridx = 0;
		gbc_panelEmojis.gridy = 0;
		
		
		
		DefaultListModel<ImageIcon> listModel = new DefaultListModel<ImageIcon>();
		for (int i = 0; i <24 ; i++) {	
			listModel.addElement(BubbleText.getEmoji(i));
			
		}
		
		
		
		
		JScrollPane scroll = new JScrollPane(panelEmojis);
		panelEmojis.setLayout(new BorderLayout(0, 0));
		
		JList<ImageIcon> list = new JList<ImageIcon>(listModel);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				emojiSelected = list.getSelectedIndex();
				indicarEmoji();
			}
		});
		list.setBackground(new Color(135, 206, 250));
		panelEmojis.add(list, BorderLayout.CENTER);
		ContentPane.add(scroll, gbc_panelEmojis);
		
		
		
		ContentPane.revalidate();
		ContentPane.repaint();
	}
	
	private void indicarEmoji() {
		padre.mandarEmoji(emojiSelected);
	}

}
