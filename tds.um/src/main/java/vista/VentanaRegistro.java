package vista;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JCalendar;

import controlador.ControladorUsuarios;
import com.toedter.calendar.JDateChooser;


public class VentanaRegistro extends JPanel {
	private JFrame ventana;
	private JPanel jpanelAnterior;
	private JTextField textNombre;
	private JTextField textPhone;
	private JTextField txtFnacimiento;
	private JTextField textEmail;
	private JTextField txtUsuario;
	private JTextField txtClave;
	private JTextField txtConfirmacion;
	private JTextField textName_W;
	private JTextField textPhone_W;
	private JTextField textEmail_W;
	private JTextField textUsuario_W;
	private JPasswordField passwordClave_W;
	private JPasswordField passwordConfirmación_W;
	private JDateChooser dateChooser;
	

	/**
	 * Create the panel.
	 */
	public VentanaRegistro(JFrame frame) {
		ventana = frame;
		jpanelAnterior = (JPanel) ventana.getContentPane();
		
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Sur = new JPanel();
		add(panel_Sur, BorderLayout.SOUTH);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean OK=false;
				OK=checkFields();
				if (OK) {
						boolean registrado=false;
						registrado = ControladorUsuarios.getUnicaInstancia().registrarUsuario(
										textName_W.getText(),
										textEmail_W.getText(),
										dateChooser.getDate().toString(),
										textPhone_W.getText(),										
										new String(passwordClave_W.getPassword()));
						if (registrado) {
							System.out.println("fechaN : " + dateChooser.getDateFormatString());
							JOptionPane.showMessageDialog(
										ventana,
										"Asistente registrado correctamente.",
										"Registro",
										JOptionPane.INFORMATION_MESSAGE);
							ventana.setContentPane(jpanelAnterior);
							ventana.revalidate();
						} else JOptionPane.showMessageDialog(ventana,
								"No se ha podido llevar a cabo el registro.\n",
								"Registro",
								JOptionPane.ERROR_MESSAGE);
						ventana.setTitle("Login Gestor Eventos");	
				} else JOptionPane.showMessageDialog(ventana,
						"No se ha podido llevar a cabo el registro. Parámetros incorrectos\n",
						"Registro",
						JOptionPane.ERROR_MESSAGE);
				ventana.setTitle("Login Gestor Eventos");	
				
			}
		});
		panel_Sur.add(btnRegistrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.setContentPane(jpanelAnterior);
				ventana.revalidate();
			}
		});
		panel_Sur.add(btnCancelar);
		
		JPanel panel_Central = new JPanel();
		add(panel_Central, BorderLayout.CENTER);
		GridBagLayout gbl_panel_Central = new GridBagLayout();
		gbl_panel_Central.columnWidths = new int[]{58, 91, 240, 75, 0};
		gbl_panel_Central.rowHeights = new int[]{0, 0, 29, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_Central.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_Central.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_Central.setLayout(gbl_panel_Central);
		
		textNombre = new JTextField();
		textNombre.setEditable(false);
		textNombre.setText("Nombre:");
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre.gridx = 1;
		gbc_textNombre.gridy = 0;
		panel_Central.add(textNombre, gbc_textNombre);
		textNombre.setColumns(10);
		
		textName_W = new JTextField();
		GridBagConstraints gbc_textName_W = new GridBagConstraints();
		gbc_textName_W.insets = new Insets(0, 0, 5, 5);
		gbc_textName_W.fill = GridBagConstraints.HORIZONTAL;
		gbc_textName_W.gridx = 2;
		gbc_textName_W.gridy = 0;
		panel_Central.add(textName_W, gbc_textName_W);
		textName_W.setColumns(10);
		
		textPhone = new JTextField();
		textPhone.setText("Teléfono");
		textPhone.setEditable(false);
		GridBagConstraints gbc_textPhone = new GridBagConstraints();
		gbc_textPhone.insets = new Insets(0, 0, 5, 5);
		gbc_textPhone.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPhone.gridx = 1;
		gbc_textPhone.gridy = 1;
		panel_Central.add(textPhone, gbc_textPhone);
		textPhone.setColumns(10);
		
		textPhone_W = new JTextField();
		GridBagConstraints gbc_textPhone_W = new GridBagConstraints();
		gbc_textPhone_W.insets = new Insets(0, 0, 5, 5);
		gbc_textPhone_W.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPhone_W.gridx = 2;
		gbc_textPhone_W.gridy = 1;
		panel_Central.add(textPhone_W, gbc_textPhone_W);
		textPhone_W.setColumns(10);
		
		txtFnacimiento = new JTextField();
		txtFnacimiento.setEditable(false);
		txtFnacimiento.setText("F.Nacimiento:");
		GridBagConstraints gbc_txtFnacimiento = new GridBagConstraints();
		gbc_txtFnacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_txtFnacimiento.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFnacimiento.gridx = 1;
		gbc_txtFnacimiento.gridy = 2;
		panel_Central.add(txtFnacimiento, gbc_txtFnacimiento);
		txtFnacimiento.setColumns(10);
		
		dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 2;
		gbc_dateChooser.gridy = 2;
		panel_Central.add(dateChooser, gbc_dateChooser);
		
		textEmail = new JTextField();
		textEmail.setEditable(false);
		textEmail.setText("Email:");
		GridBagConstraints gbc_textEmail = new GridBagConstraints();
		gbc_textEmail.insets = new Insets(0, 0, 5, 5);
		gbc_textEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEmail.gridx = 1;
		gbc_textEmail.gridy = 3;
		panel_Central.add(textEmail, gbc_textEmail);
		textEmail.setColumns(10);
		
		textEmail_W = new JTextField();
		GridBagConstraints gbc_textEmail_W = new GridBagConstraints();
		gbc_textEmail_W.insets = new Insets(0, 0, 5, 5);
		gbc_textEmail_W.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEmail_W.gridx = 2;
		gbc_textEmail_W.gridy = 3;
		panel_Central.add(textEmail_W, gbc_textEmail_W);
		textEmail_W.setColumns(10);
		
		txtUsuario = new JTextField();
		txtUsuario.setEditable(false);
		txtUsuario.setText("Usuario:");
		GridBagConstraints gbc_txtUsuario = new GridBagConstraints();
		gbc_txtUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_txtUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsuario.gridx = 1;
		gbc_txtUsuario.gridy = 4;
		panel_Central.add(txtUsuario, gbc_txtUsuario);
		txtUsuario.setColumns(10);
		
		textUsuario_W = new JTextField();
		GridBagConstraints gbc_textUsuario_W = new GridBagConstraints();
		gbc_textUsuario_W.insets = new Insets(0, 0, 5, 5);
		gbc_textUsuario_W.fill = GridBagConstraints.HORIZONTAL;
		gbc_textUsuario_W.gridx = 2;
		gbc_textUsuario_W.gridy = 4;
		panel_Central.add(textUsuario_W, gbc_textUsuario_W);
		textUsuario_W.setColumns(10);
		
		txtClave = new JTextField();
		txtClave.setEditable(false);
		txtClave.setText("Clave:");
		GridBagConstraints gbc_txtClave = new GridBagConstraints();
		gbc_txtClave.insets = new Insets(0, 0, 5, 5);
		gbc_txtClave.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtClave.gridx = 1;
		gbc_txtClave.gridy = 5;
		panel_Central.add(txtClave, gbc_txtClave);
		txtClave.setColumns(10);
		
		passwordClave_W = new JPasswordField();
		GridBagConstraints gbc_passwordClave_W = new GridBagConstraints();
		gbc_passwordClave_W.insets = new Insets(0, 0, 5, 5);
		gbc_passwordClave_W.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordClave_W.gridx = 2;
		gbc_passwordClave_W.gridy = 5;
		panel_Central.add(passwordClave_W, gbc_passwordClave_W);
		
		txtConfirmacion = new JTextField();
		txtConfirmacion.setEditable(false);
		txtConfirmacion.setText("Confirmación:");
		GridBagConstraints gbc_txtConfirmacion = new GridBagConstraints();
		gbc_txtConfirmacion.insets = new Insets(0, 0, 5, 5);
		gbc_txtConfirmacion.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtConfirmacion.gridx = 1;
		gbc_txtConfirmacion.gridy = 6;
		panel_Central.add(txtConfirmacion, gbc_txtConfirmacion);
		txtConfirmacion.setColumns(10);
		
		passwordConfirmación_W = new JPasswordField();
		GridBagConstraints gbc_passwordConfirmación_W = new GridBagConstraints();
		gbc_passwordConfirmación_W.insets = new Insets(0, 0, 5, 5);
		gbc_passwordConfirmación_W.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordConfirmación_W.gridx = 2;
		gbc_passwordConfirmación_W.gridy = 6;
		panel_Central.add(passwordConfirmación_W, gbc_passwordConfirmación_W);
		
		JPanel panel_Norte = new JPanel();
		add(panel_Norte, BorderLayout.NORTH);
		
		JTextPane txtpnBienvenidoaAAppchat = new JTextPane();
		txtpnBienvenidoaAAppchat.setText("Bienvenido/a a AppChat");
		txtpnBienvenidoaAAppchat.setForeground(SystemColor.textHighlight);
		txtpnBienvenidoaAAppchat.setFont(new Font("Sitka Small", Font.BOLD, 16));
		txtpnBienvenidoaAAppchat.setEditable(false);
		txtpnBienvenidoaAAppchat.setBackground(SystemColor.menu);
		panel_Norte.add(txtpnBienvenidoaAAppchat);
		


	}

	private boolean checkFields() {
		boolean salida=true;
		/*borrar todos los errores en pantalla*/
		if (textName_W.getText().trim().isEmpty()) {
			 salida=false;
		}
		if (textEmail_W.getText().trim().isEmpty()) {
			 salida=false;
		}
		if (dateChooser.getDate().toString().isEmpty()) { //Arreglar
			 salida=false;
		}
		String password = new String(passwordClave_W.getPassword());
		String password2 = new String(passwordConfirmación_W.getPassword());
		if (password.equals("")) {
			salida=false;
		} else if (!password.equals(password2)) {
			 salida=false;
		}
		/* Comprobar que no exista otro usuario con igual login */
		if (ControladorUsuarios.getUnicaInstancia().esUsuarioRegistrado(txtUsuario.getText())) {
			 salida=false;
		}
		return salida;
	}
	
}
