package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.font.TextMeasurer;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.ComponentOrientation;
import javax.swing.JTextArea;
import java.awt.Component;
import javax.swing.JPasswordField;

import controlador.ControladorUsuarios;
import modelo.CatalogoUsuarios;


public class VentanaLogin {

	private JFrame frame;
	private JTextField relleno;
	private JTextField relleno_1;
	private JPanel panel_Principal;
	private JTextPane textPane;
	private JTextPane textPane_1;
	private JPanel panel_Org;
	private JTextField textMovil;
	private JTextField textMovil_W;
	private JPasswordField textPasswrd_W;
	private JTextField txtPassword;
	private Component verticalStrut;
	private Component verticalStrut_1;
	private Component verticalStrut_3;
	private Component verticalStrut_2;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin window = new VentanaLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Ventana del login y registro
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextPane textBienvenida = new JTextPane();
		textBienvenida.setEditable(false);
		textBienvenida.setForeground(SystemColor.textHighlight);
		textBienvenida.setBackground(SystemColor.control);
		textBienvenida.setFont(new Font("Sitka Small", Font.BOLD, 16));
		textBienvenida.setText("                      Bienvenido/a a AppChat");
		frame.getContentPane().add(textBienvenida, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnAceptar = new JButton("Aceptar");              //TODO: Hacer todo el proceso de login
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				String movil = textMovil_W.getText();
				String password = new String (textPasswrd_W.getPassword());
				
				boolean ControlUser = ControladorUsuarios.getUnicaInstancia().loginUsuario(movil, password);				
				
				//Cambio de panel
				if (ControlUser) {
				System.out.println(ControladorUsuarios.getUnicaInstancia().getusuarioActual().getMovil());
			//	VentanaPrincipal framePrincipal = new VentanaPrincipal(ControladorUsuarios.getUnicaInstancia().getusuarioActual()); //TODO: Preguntar esto
				VentanaPrincipal framePrincipal = new VentanaPrincipal(frame); //TODO: Preguntar esto
				framePrincipal.setVisible(true);
				frame.dispose();
				} else {
					JOptionPane.showMessageDialog(frame,
							"No se encontró usuario con esa combinación Teléfono/Contraseña\n",
							"Registro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel_1.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panel_1.add(btnCancelar);
		
		JButton btnRegistro = new JButton("Registro");   //?????????????????????????????????????
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel registroV = new VentanaRegistro(frame); //Esto seria mejor subirlo?
				frame.setContentPane(registroV);
				frame.revalidate();
			}
		});
		panel_1.add(btnRegistro);
		
		
		relleno = new JTextField();
		relleno.setBorder(null);
		relleno.setBackground(SystemColor.control);
		relleno.setEnabled(false);
		frame.getContentPane().add(relleno, BorderLayout.WEST);
		relleno.setColumns(10);
		
		relleno_1 = new JTextField();
		relleno_1.setBorder(null);
		relleno_1.setBackground(SystemColor.control);
		relleno_1.setEnabled(false);
		frame.getContentPane().add(relleno_1, BorderLayout.EAST);
		relleno_1.setColumns(10);
		
		panel_Principal = new JPanel();
		frame.getContentPane().add(panel_Principal, BorderLayout.CENTER);
		panel_Principal.setLayout(new BorderLayout(0, 0));
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBackground(SystemColor.activeCaption);
		panel_Principal.add(textPane, BorderLayout.NORTH);
		
		textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBackground(SystemColor.activeCaption);
		panel_Principal.add(textPane_1, BorderLayout.SOUTH);
		
		panel_Org = new JPanel();
		panel_Principal.add(panel_Org, BorderLayout.CENTER);
		GridBagLayout gbl_panel_Org = new GridBagLayout();
		gbl_panel_Org.columnWidths = new int[]{0, 0, 0, 80, 126, 0, 0, 0, 0};
		gbl_panel_Org.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_Org.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_Org.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_Org.setLayout(gbl_panel_Org);
		
		verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.fill = GridBagConstraints.BOTH;
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 3;
		gbc_verticalStrut.gridy = 0;
		panel_Org.add(verticalStrut, gbc_verticalStrut);
		
		verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_1.gridx = 3;
		gbc_verticalStrut_1.gridy = 1;
		panel_Org.add(verticalStrut_1, gbc_verticalStrut_1);
		
		textMovil = new JTextField();
		textMovil.setEditable(false);
		textMovil.setBorder(null);
		textMovil.setBackground(SystemColor.control);
		textMovil.setText("Nº Teléfono:");
		GridBagConstraints gbc_textMovil = new GridBagConstraints();
		gbc_textMovil.fill = GridBagConstraints.HORIZONTAL;
		gbc_textMovil.insets = new Insets(0, 0, 5, 5);
		gbc_textMovil.gridx = 3;
		gbc_textMovil.gridy = 2;
		panel_Org.add(textMovil, gbc_textMovil);
		textMovil.setColumns(6);
		
		textMovil_W = new JTextField();
		GridBagConstraints gbc_textMovil_W = new GridBagConstraints();
		gbc_textMovil_W.insets = new Insets(0, 0, 5, 5);
		gbc_textMovil_W.fill = GridBagConstraints.HORIZONTAL;
		gbc_textMovil_W.gridx = 4;
		gbc_textMovil_W.gridy = 2;
		panel_Org.add(textMovil_W, gbc_textMovil_W);
		textMovil_W.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setEditable(false);
		txtPassword.setBackground(SystemColor.control);
		txtPassword.setBorder(null);
		txtPassword.setText("Contraseña:");
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtPassword.gridx = 3;
		gbc_txtPassword.gridy = 3;
		panel_Org.add(txtPassword, gbc_txtPassword);
		txtPassword.setColumns(13);
		
		textPasswrd_W = new JPasswordField();
		GridBagConstraints gbc_textPasswrd_W = new GridBagConstraints();
		gbc_textPasswrd_W.insets = new Insets(0, 0, 5, 5);
		gbc_textPasswrd_W.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPasswrd_W.gridx = 4;
		gbc_textPasswrd_W.gridy = 3;
		panel_Org.add(textPasswrd_W, gbc_textPasswrd_W);
		textPasswrd_W.setColumns(10);
		
		verticalStrut_2 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_2 = new GridBagConstraints();
		gbc_verticalStrut_2.fill = GridBagConstraints.BOTH;
		gbc_verticalStrut_2.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_2.gridx = 3;
		gbc_verticalStrut_2.gridy = 4;
		panel_Org.add(verticalStrut_2, gbc_verticalStrut_2);
		
		verticalStrut_3 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_3 = new GridBagConstraints();
		gbc_verticalStrut_3.fill = GridBagConstraints.BOTH;
		gbc_verticalStrut_3.insets = new Insets(0, 0, 0, 5);
		gbc_verticalStrut_3.gridx = 3;
		gbc_verticalStrut_3.gridy = 5;
		panel_Org.add(verticalStrut_3, gbc_verticalStrut_3);
	}

}
