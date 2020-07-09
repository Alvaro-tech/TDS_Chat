package complementosGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Scrollbar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import java.awt.Font;
import java.awt.Color;

public class MostrarBusquedas extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public MostrarBusquedas(String texto) {
	    setTitle("Resultado de las búsquedas");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JScrollPane scrollPane = new JScrollPane(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(new Color(173, 216, 230));
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textPane.setText(texto);
		textPane.setEditable(false);
		contentPanel.add(textPane);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		

	}

}
