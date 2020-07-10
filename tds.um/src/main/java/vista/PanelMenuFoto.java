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
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Color;

@SuppressWarnings("serial")
public class PanelMenuFoto extends JPanel {
	private JTextField textNameField;
	private JTextField textSaludoField;
	@SuppressWarnings("unused")
	private JPanel panelImagen;
	private JLabel lblFoto;
	private JPanel panelBotones;
	private JButton btnCambiarFoto;
	private JButton btnCambiarSaludo;
	
	private  Usuario usuario;

	/**
	 * Create the panel.
	 */
	public PanelMenuFoto(Usuario usuariop) {
		this.usuario = usuariop;
		
		setLayout(new BorderLayout(0, 0));
		
		final JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(176, 196, 222));
		add(panelPrincipal);
		GridBagLayout gbl_panelPrincipal = new GridBagLayout();
		gbl_panelPrincipal.columnWidths = new int[]{202, 0};
		gbl_panelPrincipal.rowHeights = new int[]{282, 57, 40, 56, 0, 0};
		gbl_panelPrincipal.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelPrincipal.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelPrincipal.setLayout(gbl_panelPrincipal);
		
		panelImagen = new JPanel();
		GridBagConstraints gbc_panelImagen = new GridBagConstraints();
		gbc_panelImagen.insets = new Insets(0, 0, 5, 5);
		gbc_panelImagen.fill = GridBagConstraints.BOTH;
		gbc_panelImagen.gridx = 2;
		gbc_panelImagen.gridy = 1;
	
		
		lblFoto = new JLabel(); 
		GridBagConstraints gbc_lblFoto = new GridBagConstraints();
		gbc_lblFoto.fill = GridBagConstraints.BOTH;
		gbc_lblFoto.insets = new Insets(0, 0, 5, 0);
		gbc_lblFoto.gridx = 0;
		gbc_lblFoto.gridy = 0;
		panelPrincipal.add(lblFoto, gbc_lblFoto);
		
		panelPrincipal.revalidate();
		panelPrincipal.repaint();
		
		lblFoto.setSize(248, 282);
		 
		//-------Tratemiento de la imagen de perfil ------
		ImageIcon imagen = new ImageIcon(usuariop.getFotoPerfil());
		
		Icon icono = new ImageIcon (imagen.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT));
		lblFoto.setIcon(icono);
		
		panelBotones = new JPanel();
		panelBotones.setBackground(new Color(192, 192, 192));
		GridBagConstraints gbc_panelBotones = new GridBagConstraints();
		gbc_panelBotones.insets = new Insets(0, 0, 5, 0);
		gbc_panelBotones.fill = GridBagConstraints.BOTH;
		gbc_panelBotones.gridx = 0;
		gbc_panelBotones.gridy = 1;
		panelPrincipal.add(panelBotones, gbc_panelBotones);
		
		btnCambiarFoto = new JButton("Cambiar Foto");
		final JFileChooser fc = new JFileChooser("./iconos/"); //Ruta por defecto
		btnCambiarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnCambiarFoto) {
					int returnVal = fc.showOpenDialog(PanelMenuFoto.this);
					if(returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						String foto = file.toString();
						
						//-----Redimensionar el tamaña de un label------
						ImageIcon imagen = new ImageIcon(foto);
						Icon icono = new ImageIcon (imagen.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT));
						
						lblFoto.setIcon(icono);
						panelPrincipal.repaint();
						
						//------Actualizar datos------
						ControladorUsuarios.getUnicaInstancia().updateFoto(usuario, foto);
					}
				
				}
			}	
			
		});
		
		panelBotones.add(btnCambiarFoto);
		
		btnCambiarSaludo = new JButton("Cambiar Saludo");
		btnCambiarSaludo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nuevoSaludo = textSaludoField.getText();
				ControladorUsuarios.getUnicaInstancia().updateSaludo(usuario, nuevoSaludo);
			}
		});
		
		panelBotones.add(btnCambiarSaludo);
		
		textNameField = new JTextField();
		textNameField.setEditable(false);
		textNameField.setText(usuario.getNombre());
		GridBagConstraints gbc_textNameField = new GridBagConstraints();
		gbc_textNameField.insets = new Insets(0, 0, 5, 0);
		gbc_textNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNameField.gridx = 0;
		gbc_textNameField.gridy = 2;
		panelPrincipal.add(textNameField, gbc_textNameField);
		textNameField.setColumns(10);
		
		textSaludoField = new JTextField();
		textSaludoField.setText(usuario.getSaludo());
		GridBagConstraints gbc_textSaludoField = new GridBagConstraints();
		gbc_textSaludoField.insets = new Insets(0, 0, 5, 0);
		gbc_textSaludoField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textSaludoField.gridx = 0;
		gbc_textSaludoField.gridy = 3;
		panelPrincipal.add(textSaludoField, gbc_textSaludoField);
		textSaludoField.setColumns(10);

	}

}
