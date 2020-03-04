package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JEditorPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

public class PanelAltaPremiun extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PanelAltaPremiun dialog = new PanelAltaPremiun();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PanelAltaPremiun() {
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{434, 0};
		gridBagLayout.rowHeights = new int[]{228, 33, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.fill = GridBagConstraints.BOTH;
		gbc_contentPanel.insets = new Insets(0, 0, 5, 0);
		gbc_contentPanel.gridx = 0;
		gbc_contentPanel.gridy = 0;
		getContentPane().add(contentPanel, gbc_contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JEditorPane dtrpnconvierteteEnPremium = new JEditorPane();
			dtrpnconvierteteEnPremium.setBackground(SystemColor.menu);
			dtrpnconvierteteEnPremium.setFont(new Font("Tahoma", Font.BOLD, 16));
			dtrpnconvierteteEnPremium.setForeground(SystemColor.textHighlight);
			dtrpnconvierteteEnPremium.setEditable(false);
			dtrpnconvierteteEnPremium.setText("              ¡¡¡Conviertete en Premium hoy!!!");
			contentPanel.add(dtrpnconvierteteEnPremium, BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{69, 101, 97, 77, 0};
			gbl_panel.rowHeights = new int[]{40, 40, 40, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JRadioButton rdbtnMensajes = new JRadioButton("Más Mensajero");
				buttonGroup.add(rdbtnMensajes);
				GridBagConstraints gbc_rdbtnMensajes = new GridBagConstraints();
				gbc_rdbtnMensajes.anchor = GridBagConstraints.NORTHWEST;
				gbc_rdbtnMensajes.insets = new Insets(0, 0, 5, 5);
				gbc_rdbtnMensajes.gridx = 0;
				gbc_rdbtnMensajes.gridy = 1;
				panel.add(rdbtnMensajes, gbc_rdbtnMensajes);
			}
			{
				JRadioButton rdbtnEspecialVerano = new JRadioButton("Especial Verano");
				buttonGroup.add(rdbtnEspecialVerano);
				GridBagConstraints gbc_rdbtnEspecialVerano = new GridBagConstraints();
				gbc_rdbtnEspecialVerano.anchor = GridBagConstraints.NORTHWEST;
				gbc_rdbtnEspecialVerano.insets = new Insets(0, 0, 5, 5);
				gbc_rdbtnEspecialVerano.gridx = 0;
				gbc_rdbtnEspecialVerano.gridy = 2;
				panel.add(rdbtnEspecialVerano, gbc_rdbtnEspecialVerano);
			}
			{
				JRadioButton rdbtnEstandar = new JRadioButton("Estándar");
				buttonGroup.add(rdbtnEstandar);
				GridBagConstraints gbc_rdbtnEstandar = new GridBagConstraints();
				gbc_rdbtnEstandar.insets = new Insets(0, 0, 5, 5);
				gbc_rdbtnEstandar.anchor = GridBagConstraints.NORTHWEST;
				gbc_rdbtnEstandar.gridx = 0;
				gbc_rdbtnEstandar.gridy = 3;
				panel.add(rdbtnEstandar, gbc_rdbtnEstandar);
			}
			{
				JTextPane txtpnPrecio = new JTextPane();
				txtpnPrecio.setEditable(false);
				txtpnPrecio.setBackground(SystemColor.menu);
				txtpnPrecio.setFont(new Font("Tahoma", Font.BOLD, 17));
				txtpnPrecio.setForeground(SystemColor.textHighlight);
				txtpnPrecio.setText("         Precio:");
				GridBagConstraints gbc_txtpnPrecio = new GridBagConstraints();
				gbc_txtpnPrecio.insets = new Insets(0, 0, 0, 5);
				gbc_txtpnPrecio.fill = GridBagConstraints.BOTH;
				gbc_txtpnPrecio.gridx = 2;
				gbc_txtpnPrecio.gridy = 4;
				panel.add(txtpnPrecio, gbc_txtpnPrecio);
			}
			{
				textField = new JTextField();
				textField.setFont(new Font("Tahoma", Font.BOLD, 14));
				textField.setText("0 €");
				textField.setEditable(false);
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.fill = GridBagConstraints.BOTH;
				gbc_textField.gridx = 3;
				gbc_textField.gridy = 4;
				panel.add(textField, gbc_textField);
				textField.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			GridBagConstraints gbc_buttonPane = new GridBagConstraints();
			gbc_buttonPane.anchor = GridBagConstraints.NORTH;
			gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_buttonPane.gridx = 0;
			gbc_buttonPane.gridy = 1;
			getContentPane().add(buttonPane, gbc_buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
