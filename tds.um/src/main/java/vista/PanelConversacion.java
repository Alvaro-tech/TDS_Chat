package vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import modelo.Chat;
import modelo.ChatGrupo;
import modelo.ChatIndividual;
import modelo.Mensaje;
import modelo.Usuario;
import tds.BubbleText;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import controlador.ControladorUsuarios;
import javax.swing.BoxLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class PanelConversacion extends JPanel {
	private JTextField textTexto;
	private Chat chat;
	private JFrame ventana;
	private VentanaPrincipal padre;
	private JPanel panelMensajes;
	private JPanel panelPrincipal;
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public PanelConversacion(Chat chat, JFrame ventana, VentanaPrincipal padre) {
		this.chat = chat;
		this.ventana = ventana;
		this.padre = padre;
		setLayout(new BorderLayout(0, 0));

		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(173, 216, 230));
		add(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));

		JPanel panelEscritura = new JPanel();
		panelEscritura.setBackground(new Color(100, 149, 237));
		panelPrincipal.add(panelEscritura, BorderLayout.SOUTH);

		JLabel lblEmoji = new JLabel("Emoji");
		lblEmoji.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				padre.mostrarListadoEmojis();
			}
		});
		panelEscritura.add(lblEmoji);

		textTexto = new JTextField();
		panelEscritura.add(textTexto);
		textTexto.setColumns(40);

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					crearBurbujaMensaje(); // Crear una burbuja para tu mensaje
					enviarMensaje(false);
					textTexto.setText("");
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		panelEscritura.add(btnEnviar);

		panelMensajes = new JPanel();
		panelMensajes.setBackground(new Color(135, 206, 250));
		panelPrincipal.add(panelMensajes, BorderLayout.CENTER);
		panelMensajes.setLayout(new BoxLayout(panelMensajes, BoxLayout.Y_AXIS));
		panelMensajes.setSize(new Dimension(800, 800));
		scrollPane = new JScrollPane(panelMensajes);
		panelPrincipal.add(scrollPane, BorderLayout.CENTER);

		cargarBurbujas();

	}

	// Funcionalidad
	private void crearBurbujaMensaje() {
		BubbleText burbuja;
		Usuario emisor = ControladorUsuarios.getUnicaInstancia().getusuarioActual();

		burbuja = new BubbleText(panelMensajes, textTexto.getText(), Color.GREEN, emisor.getNombre(), BubbleText.SENT,
				18);
		panelMensajes.add(burbuja);

		panelMensajes.revalidate();
		panelMensajes.repaint();

	}

	private void crearBurbujaEmoji(int nEmoji) {
		BubbleText burbuja;
		Usuario emisor = ControladorUsuarios.getUnicaInstancia().getusuarioActual();

		burbuja = new BubbleText(panelMensajes, nEmoji, Color.GREEN, emisor.getNombre(), BubbleText.SENT, 18);
		panelMensajes.add(burbuja);

		panelMensajes.revalidate();
		panelMensajes.repaint();
	}

	public void peticionDeEmoji(int nEmoji) {
		crearBurbujaEmoji(nEmoji);
		textTexto.setText("" + nEmoji);
		enviarMensaje(true);
		textTexto.setText("");

	}

	private void burbujaMensajeRecibida(Mensaje m) {
		BubbleText burbuja;
		Usuario emisor = m.getEmisor();

		if (m.isEmoji()) {
			burbuja = new BubbleText(panelMensajes, Integer.parseInt(m.getTexto()), Color.CYAN, emisor.getNombre(),
					BubbleText.RECEIVED, 18);
		} else {
			burbuja = new BubbleText(panelMensajes, m.getTexto(), Color.CYAN, emisor.getNombre(), BubbleText.RECEIVED,
					18);
		}

		panelMensajes.add(burbuja);
		burbuja.setVisible(true);

	}

	private void burbujaMensajeEnviada(Mensaje m) {
		BubbleText burbuja;
		Usuario emisor = m.getEmisor();

		if (m.isEmoji()) {
			burbuja = new BubbleText(panelMensajes, Integer.parseInt(m.getTexto()), Color.GREEN, emisor.getNombre(),
					BubbleText.SENT, 18);
		} else {
			burbuja = new BubbleText(panelMensajes, m.getTexto(), Color.GREEN, emisor.getNombre(), BubbleText.SENT, 18);
		}

		panelMensajes.add(burbuja);
		burbuja.setVisible(true);

	}

	private void enviarMensaje(boolean emoji) {
		Usuario emisor = ControladorUsuarios.getUnicaInstancia().getusuarioActual();

		switch (chat.getClass().getSimpleName()) {
		case "ChatIndividual":
			ChatIndividual c1 = (ChatIndividual) chat;
			Mensaje m = ControladorUsuarios.getUnicaInstancia().crearMensaje(emisor, c1, textTexto.getText());

			if (emoji) {
				m.setEmoji(true);
			}

			// Guardamos el mensaje en persistencia, para que tenga un idPropio
			ControladorUsuarios.getUnicaInstancia().guardarEmojiEnPersistencia(m);

			// From me (usuarioAcutal) to un chatIndv con un texto
			ControladorUsuarios.getUnicaInstancia().enviarMensajeAChatInd(m, c1);
			break;

		case "ChatGrupo":
			ChatGrupo c2 = (ChatGrupo) chat;
			Mensaje m1 = ControladorUsuarios.getUnicaInstancia().crearMensaje(emisor, c2.getMiembros().getFirst(),
					textTexto.getText()); // No se le envia a alguien en concreto

			if (emoji) {
				m1.setEmoji(true);
			}

			// Guardamos el mensaje en persistencia, para que tenga un idPropio
			ControladorUsuarios.getUnicaInstancia().guardarEmojiEnPersistencia(m1);

			ControladorUsuarios.getUnicaInstancia().enviarMensajeAGrupo(m1, c2);

			break;

		}
	}

	private void cargarBurbujas() {
		Usuario yo = ControladorUsuarios.getUnicaInstancia().getusuarioActual();
		LinkedList<Mensaje> aux = new LinkedList<Mensaje>(chat.getHistorial());
		Collections.reverse(aux);
		for (Mensaje m : aux) {
			if (m.getEmisor().equals(yo)) {
				burbujaMensajeEnviada(m);

			} else {
				burbujaMensajeRecibida(m);
			}
			panelMensajes.revalidate();
			panelMensajes.repaint();
		}
	}

}
