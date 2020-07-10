package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.ControladorUsuarios;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class PanelCrearContacto extends JDialog {
	private JFrame ventana = new JFrame();
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField textNombreArea;
	private JTextField textTelefonoArea;
	private JTextPane txtCrear;
	private JTextPane txtContacto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PanelCrearContacto dialog = new PanelCrearContacto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PanelCrearContacto() {
		ventana.setAlwaysOnTop(true);
		setTitle("Crear Contacto");
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{103, 106, 129, 92, 0};
		gbl_contentPanel.rowHeights = new int[]{52, 29, 0, 0, 41, 39, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			txtCrear = new JTextPane();
			txtCrear.setEditable(false);
			txtCrear.setBackground(SystemColor.menu);
			txtCrear.setForeground(new Color(0, 0, 255));
			txtCrear.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtCrear.setText("          CREAR");
			GridBagConstraints gbc_txtCrear = new GridBagConstraints();
			gbc_txtCrear.insets = new Insets(0, 0, 5, 5);
			gbc_txtCrear.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCrear.gridx = 1;
			gbc_txtCrear.gridy = 0;
			contentPanel.add(txtCrear, gbc_txtCrear);
		}
		{
			txtContacto = new JTextPane();
			txtContacto.setForeground(new Color(0, 0, 255));
			txtContacto.setBackground(SystemColor.menu);
			txtContacto.setEditable(false);
			txtContacto.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtContacto.setText("CONTACTO");
			GridBagConstraints gbc_txtContacto = new GridBagConstraints();
			gbc_txtContacto.insets = new Insets(0, 0, 5, 5);
			gbc_txtContacto.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtContacto.gridx = 2;
			gbc_txtContacto.gridy = 0;
			contentPanel.add(txtContacto, gbc_txtContacto);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setEditable(false);
			txtNombre.setText("Nombre");
			GridBagConstraints gbc_txtNombre = new GridBagConstraints();
			gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
			gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtNombre.gridx = 1;
			gbc_txtNombre.gridy = 2;
			contentPanel.add(txtNombre, gbc_txtNombre);
			txtNombre.setColumns(10);
		}
		{
			textNombreArea = new JTextField();
			GridBagConstraints gbc_textNombreArea = new GridBagConstraints();
			gbc_textNombreArea.insets = new Insets(0, 0, 5, 5);
			gbc_textNombreArea.fill = GridBagConstraints.HORIZONTAL;
			gbc_textNombreArea.gridx = 2;
			gbc_textNombreArea.gridy = 2;
			contentPanel.add(textNombreArea, gbc_textNombreArea);
			textNombreArea.setColumns(10);
		}
		{
			txtTelefono = new JTextField();
			txtTelefono.setEditable(false);
			txtTelefono.setText("Teléfono");
			GridBagConstraints gbc_txtTelefono = new GridBagConstraints();
			gbc_txtTelefono.insets = new Insets(0, 0, 5, 5);
			gbc_txtTelefono.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtTelefono.gridx = 1;
			gbc_txtTelefono.gridy = 3;
			contentPanel.add(txtTelefono, gbc_txtTelefono);
			txtTelefono.setColumns(10);
		}
		{
			textTelefonoArea = new JTextField();
			GridBagConstraints gbc_textTelefonoArea = new GridBagConstraints();
			gbc_textTelefonoArea.insets = new Insets(0, 0, 5, 5);
			gbc_textTelefonoArea.fill = GridBagConstraints.HORIZONTAL;
			gbc_textTelefonoArea.gridx = 2;
			gbc_textTelefonoArea.gridy = 3;
			contentPanel.add(textTelefonoArea, gbc_textTelefonoArea);
			textTelefonoArea.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							ControladorUsuarios.getUnicaInstancia().addChatToUser(textNombreArea.getText(), textTelefonoArea.getText());
							JOptionPane.showMessageDialog(ventana,
									"Contacto añadido correctamente",
									"Añadir conctacto",
									JOptionPane.INFORMATION_MESSAGE);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(ventana,
									"No se ha podido llevar a cabo el registro. Parámetros incorrectos\n",
									"Añadir conctacto",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
