package vista;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import controlador.ControladorUsuarios;
import modelo.Usuario;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelMenuFoto extends JPanel {
	private JTextField txtNombre;
	private JTextField txtSaludo;
	private JTextField textNameField;
	private JTextField textSaludoField;
	private JPanel panelImagen;
	private PanelVerImagen pantallaVerImagen;
	private JLabel lblFoto;
	private JPanel panelBotones;
	private JButton btnCambiarFoto;
	private JButton btnCambiarSaludo;
	private JTextField textRelleno;
	private JTextField textRelleno1;
	
	private  Usuario usuario;

	/**
	 * Create the panel.
	 */
	public PanelMenuFoto(Usuario usuariop) {
		this.usuario = usuariop;
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelPrincipal = new JPanel();
		add(panelPrincipal);
		GridBagLayout gbl_panelPrincipal = new GridBagLayout();
		gbl_panelPrincipal.columnWidths = new int[]{90, 76, 170, 61, 95, 0};
		gbl_panelPrincipal.rowHeights = new int[]{109, 167, 44, 57, 40, 56, 0, 0};
		gbl_panelPrincipal.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelPrincipal.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelPrincipal.setLayout(gbl_panelPrincipal);
		
		panelImagen = new JPanel();
		GridBagConstraints gbc_panelImagen = new GridBagConstraints();
		gbc_panelImagen.insets = new Insets(0, 0, 5, 5);
		gbc_panelImagen.fill = GridBagConstraints.BOTH;
		gbc_panelImagen.gridx = 2;
		gbc_panelImagen.gridy = 1;
		
		textRelleno = new JTextField();
		textRelleno.setBackground(SystemColor.textHighlight);
		textRelleno.setEditable(false);
		GridBagConstraints gbc_textRelleno = new GridBagConstraints();
		gbc_textRelleno.insets = new Insets(0, 0, 5, 5);
		gbc_textRelleno.fill = GridBagConstraints.BOTH;
		gbc_textRelleno.gridx = 1;
		gbc_textRelleno.gridy = 1;
		panelPrincipal.add(textRelleno, gbc_textRelleno);
		textRelleno.setColumns(10);
	
		//TODO: Como hacer esto bien
		lblFoto = new JLabel(new ImageIcon("C:\\Users\\alvar\\git\\TDS_AppChat\\tds.um\\iconos\\Lupa.png"));
		GridBagConstraints gbc_lblFoto = new GridBagConstraints();
		gbc_lblFoto.insets = new Insets(0, 0, 5, 5);
		gbc_lblFoto.gridx = 2;
		gbc_lblFoto.gridy = 1;
		panelPrincipal.add(lblFoto, gbc_lblFoto);
		
		panelPrincipal.revalidate();
		panelPrincipal.repaint();
		
		textRelleno1 = new JTextField();
		textRelleno1.setBackground(SystemColor.textHighlight);
		GridBagConstraints gbc_textRelleno1 = new GridBagConstraints();
		gbc_textRelleno1.insets = new Insets(0, 0, 5, 5);
		gbc_textRelleno1.fill = GridBagConstraints.BOTH;
		gbc_textRelleno1.gridx = 3;
		gbc_textRelleno1.gridy = 1;
		panelPrincipal.add(textRelleno1, gbc_textRelleno1);
		textRelleno1.setColumns(10);
		
		panelBotones = new JPanel();
		GridBagConstraints gbc_panelBotones = new GridBagConstraints();
		gbc_panelBotones.insets = new Insets(0, 0, 5, 5);
		gbc_panelBotones.fill = GridBagConstraints.BOTH;
		gbc_panelBotones.gridx = 2;
		gbc_panelBotones.gridy = 3;
		panelPrincipal.add(panelBotones, gbc_panelBotones);
		
		btnCambiarFoto = new JButton("Cambiar Foto");
		panelBotones.add(btnCambiarFoto);
		
		btnCambiarSaludo = new JButton("Cambiar Saludo");
		btnCambiarSaludo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Texto en saludo: " + usuario.getSaludo());  /////////////////////////////
				usuario.setSaludo(textSaludoField.getText());
				ControladorUsuarios.getUnicaInstancia().updateSaludo(usuario);
			}
		});
		
		panelBotones.add(btnCambiarSaludo);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setText("Nombre");
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.gridx = 1;
		gbc_txtNombre.gridy = 4;
		panelPrincipal.add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);
		
		textNameField = new JTextField();
		textNameField.setEditable(false);
		textNameField.setText(usuario.getNombre());
		GridBagConstraints gbc_textNameField = new GridBagConstraints();
		gbc_textNameField.insets = new Insets(0, 0, 5, 5);
		gbc_textNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNameField.gridx = 2;
		gbc_textNameField.gridy = 4;
		panelPrincipal.add(textNameField, gbc_textNameField);
		textNameField.setColumns(10);
		
		txtSaludo = new JTextField();
		txtSaludo.setEditable(false);
		txtSaludo.setText("Saludo");
		GridBagConstraints gbc_txtSaludo = new GridBagConstraints();
		gbc_txtSaludo.insets = new Insets(0, 0, 5, 5);
		gbc_txtSaludo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSaludo.gridx = 1;
		gbc_txtSaludo.gridy = 5;
		panelPrincipal.add(txtSaludo, gbc_txtSaludo);
		txtSaludo.setColumns(10);
		
		textSaludoField = new JTextField();
		System.out.println("El saludo antes de hacer nada de nada: " + usuario.getSaludo() + " y su id es " + usuario.getId()); ///////////////////
		textSaludoField.setText(usuario.getSaludo());
		GridBagConstraints gbc_textSaludoField = new GridBagConstraints();
		gbc_textSaludoField.insets = new Insets(0, 0, 5, 5);
		gbc_textSaludoField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textSaludoField.gridx = 2;
		gbc_textSaludoField.gridy = 5;
		panelPrincipal.add(textSaludoField, gbc_textSaludoField);
		textSaludoField.setColumns(10);

	}

}
