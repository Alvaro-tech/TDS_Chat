package ProyectoTDS.tds.um;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import complementosGUI.JPanelBackground;

import javax.swing.JButton;

public class PruebaJFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PruebaJFrame frame = new PruebaJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PruebaJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelSuperior = new JPanel();
		contentPane.add(panelSuperior, BorderLayout.NORTH);

		JPanelBackground panelCentral = new JPanelBackground();
		panelCentral.setBackground("./interfaz/Info_contacto.jpg");
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("New button");
		panelCentral.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		panelCentral.add(btnNewButton_1);
	}

}
