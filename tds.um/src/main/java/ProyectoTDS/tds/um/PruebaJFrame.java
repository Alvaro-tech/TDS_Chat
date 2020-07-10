package ProyectoTDS.tds.um;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import complementosGUI.JPanelBackground;
import tds.BubbleText;
import vista.VentanaLogin;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class PruebaJFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblLabel;
	private PruebaJFrame yo;

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
		
		this.yo=this;
		
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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			BubbleText a = new BubbleText(panelCentral, "hola", Color.GREEN, "pepe", BubbleText.SENT, 18);
			a.setVisible(true);
			panelCentral.add(a);
			
			System.out.println("Hola");
			contentPane.revalidate();
			contentPane.repaint();
			}
		});
		panelCentral.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				VentanaLogin11 a = new VentanaLogin11();
				a.getFrame().setVisible(true);
				a.revalidate();
				a.repaint();
				
				yo.dispose();
				
			}
		});
		panelCentral.add(btnNewButton_1);
		
		lblLabel = new JLabel("New label");
		panelCentral.add(lblLabel);
	}

}
