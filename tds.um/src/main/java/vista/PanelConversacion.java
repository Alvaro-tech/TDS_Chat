package vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import modelo.Chat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class PanelConversacion extends JPanel {
	private JTextField textTexto;
	private Chat chat;
	private JFrame ventana;
	private VentanaPrincipal padre;
	/**
	 * Create the panel.
	 */
	public PanelConversacion(Chat chat, JFrame ventana, VentanaPrincipal padre) {
		this.chat = chat;
		this.ventana = ventana;
		this.padre = padre;
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(173, 216, 230));
		add(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel panelEscritura = new JPanel();
		panelEscritura.setBackground(new Color(100, 149, 237));
		panelPrincipal.add(panelEscritura, BorderLayout.SOUTH);
		
		JLabel lblEmoji = new JLabel("Emoji");
		panelEscritura.add(lblEmoji);
		
		textTexto = new JTextField();
		panelEscritura.add(textTexto);
		textTexto.setColumns(40);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO
			}
		});
		panelEscritura.add(btnEnviar);

	}

}
