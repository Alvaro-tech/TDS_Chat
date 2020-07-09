package complementosGUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import tds.BubbleText;
import vista.VentanaPrincipal;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class PanelEmojis extends JPanel {

	private VentanaPrincipal padre;
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
		GridBagConstraints gbc_panelEmojis = new GridBagConstraints();
		gbc_panelEmojis.fill = GridBagConstraints.BOTH;
		gbc_panelEmojis.gridx = 0;
		gbc_panelEmojis.gridy = 0;
		//ContentPane.add(panelEmojis, gbc_panelEmojis);
		panelEmojis.setLayout(new BoxLayout(panelEmojis, BoxLayout.Y_AXIS));
		
		/*
		for(int i = 1; i <= 24; i++) {
			JLabel x= new JLabel();
			x.setIcon(BubbleText.getEmoji(i));
			panelEmojis.add(x);
		}*/
		
		JScrollPane scroll = new JScrollPane(panelEmojis);
		ContentPane.add(scroll, gbc_panelEmojis);
		ContentPane.revalidate();
		ContentPane.repaint();
	}

}
