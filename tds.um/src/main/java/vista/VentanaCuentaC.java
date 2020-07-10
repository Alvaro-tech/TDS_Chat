package vista;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import complementosGUI.JPanelBackground;
import modelo.Usuario;
import java.awt.Image;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class VentanaCuentaC extends JFrame {

	private JPanelBackground contentPane;
	@SuppressWarnings("unused")
	private Usuario contacto;
	private JTextField textTelefono;
	private JTextField textNombre;
	private JTextField textNombre_I;
	private JTextField textMovil_I;
	private JTextField texEmail;
	private JTextField textEmail_I;
	private JTextField textFnacimiento;
	private JTextField textFnacito_I;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VentanaCuentaC(Usuario contacto) {
		this.contacto = contacto;
		setAlwaysOnTop(true);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 40, 450, 500);
		setTitle("Infomarción sobre contacto");
		contentPane = new JPanelBackground();
		contentPane.setBackground("./interfaz/Info_contacto.jpg");
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanelBackground panelFoto = new JPanelBackground();
		panelFoto.setOpaque(false);
		contentPane.add(panelFoto, BorderLayout.NORTH);
		
		JLabel lblFoto = new JLabel();
		lblFoto.setSize(248, 282);
		ImageIcon imageIcon = new ImageIcon(contacto.getFotoPerfil());
		ImageIcon imageIcon2 = new ImageIcon(imageIcon.getImage().getScaledInstance(248, 282, Image.SCALE_DEFAULT));	
		lblFoto.setIcon(imageIcon2);
		panelFoto.add(lblFoto);
		
		JPanelBackground panelInfo = new JPanelBackground();
		panelInfo.setOpaque(false);
		
		contentPane.add(panelInfo, BorderLayout.CENTER);
		GridBagLayout gbl_panelInfo = new GridBagLayout();
		gbl_panelInfo.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panelInfo.rowHeights = new int[]{41, 0, 0, 0, 0, 0, 0};
		gbl_panelInfo.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelInfo.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelInfo.setLayout(gbl_panelInfo);
		
		textNombre = new JTextField();
		textNombre.setEditable(false);
		textNombre.setHorizontalAlignment(SwingConstants.CENTER);
		textNombre.setText("Nombre:");
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre.gridx = 1;
		gbc_textNombre.gridy = 1;
		panelInfo.add(textNombre, gbc_textNombre);
		textNombre.setColumns(10);
		
		textNombre_I = new JTextField();
		textNombre_I.setEditable(false);
		GridBagConstraints gbc_textNombre_I = new GridBagConstraints();
		gbc_textNombre_I.insets = new Insets(0, 0, 5, 5);
		gbc_textNombre_I.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre_I.gridx = 2;
		gbc_textNombre_I.gridy = 1;
		panelInfo.add(textNombre_I, gbc_textNombre_I);
		textNombre_I.setColumns(10);
		textNombre_I.setText(contacto.getNombre());
		
		textTelefono = new JTextField();
		textTelefono.setEditable(false);
		textTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		textTelefono.setText("Teléfono Móvil:");
		GridBagConstraints gbc_textTelefono = new GridBagConstraints();
		gbc_textTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_textTelefono.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTelefono.gridx = 1;
		gbc_textTelefono.gridy = 2;
		panelInfo.add(textTelefono, gbc_textTelefono);
		textTelefono.setColumns(10);
		
		textMovil_I = new JTextField();
		textMovil_I.setEditable(false);
		GridBagConstraints gbc_textMovil_I = new GridBagConstraints();
		gbc_textMovil_I.insets = new Insets(0, 0, 5, 5);
		gbc_textMovil_I.fill = GridBagConstraints.HORIZONTAL;
		gbc_textMovil_I.gridx = 2;
		gbc_textMovil_I.gridy = 2;
		panelInfo.add(textMovil_I, gbc_textMovil_I);
		textMovil_I.setColumns(10);
		textMovil_I.setText(contacto.getMovil());
		
		texEmail = new JTextField();
		texEmail.setText("Correo Electrónico");
		texEmail.setEditable(false);
		texEmail.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_texEmail = new GridBagConstraints();
		gbc_texEmail.insets = new Insets(0, 0, 5, 5);
		gbc_texEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_texEmail.gridx = 1;
		gbc_texEmail.gridy = 3;
		panelInfo.add(texEmail, gbc_texEmail);
		texEmail.setColumns(10);
		
		textEmail_I = new JTextField();
		textEmail_I.setEditable(false);
		GridBagConstraints gbc_textEmail_I = new GridBagConstraints();
		gbc_textEmail_I.insets = new Insets(0, 0, 5, 5);
		gbc_textEmail_I.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEmail_I.gridx = 2;
		gbc_textEmail_I.gridy = 3;
		panelInfo.add(textEmail_I, gbc_textEmail_I);
		textEmail_I.setColumns(10);
		textEmail_I.setText(contacto.getEmail());
		
		
		textFnacimiento = new JTextField();
		textFnacimiento.setHorizontalAlignment(SwingConstants.CENTER);
		textFnacimiento.setText("Fecha de Nacimiento:\r\n");
		textFnacimiento.setEditable(false);
		GridBagConstraints gbc_textFnacimiento = new GridBagConstraints();
		gbc_textFnacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_textFnacimiento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFnacimiento.gridx = 1;
		gbc_textFnacimiento.gridy = 4;
		panelInfo.add(textFnacimiento, gbc_textFnacimiento);
		textFnacimiento.setColumns(10);
		
		textFnacito_I = new JTextField();
		textFnacito_I.setEditable(false);
		GridBagConstraints gbc_textFnacito_I = new GridBagConstraints();
		gbc_textFnacito_I.insets = new Insets(0, 0, 5, 5);
		gbc_textFnacito_I.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFnacito_I.gridx = 2;
		gbc_textFnacito_I.gridy = 4;
		panelInfo.add(textFnacito_I, gbc_textFnacito_I);
		textFnacito_I.setColumns(10);
		textFnacito_I.setText(contacto.getFecha());
		
	}

}
