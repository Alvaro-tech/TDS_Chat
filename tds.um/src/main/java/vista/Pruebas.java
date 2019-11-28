package vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.CardLayout;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JEditorPane;
import javax.swing.JLabel;

public class Pruebas extends JPanel {

	/**
	 * Create the panel.
	 */
	public Pruebas() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		final JPanel cards = new JPanel();
		add(cards);
		cards.setLayout(new CardLayout(0, 0));
		
		JPanel panel1 = new JPanel();
		cards.add(panel1, "name_88581105084600");
		
		JButton btnNewButton = new JButton("New button");
		panel1.add(btnNewButton);
		
		JPanel panel2 = new JPanel();
		cards.add(panel2, "name_88581115344500");
		
		JEditorPane editorPane = new JEditorPane();
		panel2.add(editorPane);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("/iconos/Lupa.png"));
		panel2.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		add(panel);
		
		JButton btnFeo = new JButton("Feo");
		panel.add(btnFeo);
		btnFeo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(cards.getLayout());
			    cl.show(cards, "name_88581115344500");
			}
		});
	}

}
