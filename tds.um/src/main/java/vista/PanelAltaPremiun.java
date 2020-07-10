package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.ControladorUsuarios;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PanelAltaPremiun extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textPrecio;
	private JFrame ventana;
	private VentanaPrincipal padre;

	
	/**
	 * Create the dialog.
	 */
	public PanelAltaPremiun(JFrame ventana, VentanaPrincipal padre) {
		this.ventana = ventana;
		this.padre  = padre;
		
		setBounds(80, 80, 500, 320);
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
			//El numero cambia antes de darle al ok
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
				JRadioButton rdbtnMensajes = new JRadioButton("Descuento para Jovenes");
				rdbtnMensajes.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						mostrarPrecioSinCompromiso();
					}
					
				});
				{
					JRadioButton rdbtnSinDescuento = new JRadioButton("Sin descuento");
					rdbtnSinDescuento.setHorizontalAlignment(SwingConstants.LEFT);
					rdbtnSinDescuento.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							textPrecio.setText(ControladorUsuarios.getUnicaInstancia().getPrecioPremiumMes() + "€");
						}
					});
					rdbtnSinDescuento.setActionCommand("Descuento para Jovenes");
					GridBagConstraints gbc_rdbtnSinDescuento = new GridBagConstraints();
					gbc_rdbtnSinDescuento.anchor = GridBagConstraints.WEST;
					gbc_rdbtnSinDescuento.insets = new Insets(0, 0, 5, 5);
					gbc_rdbtnSinDescuento.gridx = 0;
					gbc_rdbtnSinDescuento.gridy = 0;
					panel.add(rdbtnSinDescuento, gbc_rdbtnSinDescuento);
					buttonGroup.add(rdbtnSinDescuento);
				}
				rdbtnMensajes.setActionCommand("Descuento para Jovenes");
				buttonGroup.add(rdbtnMensajes);
				GridBagConstraints gbc_rdbtnMensajes = new GridBagConstraints();
				gbc_rdbtnMensajes.anchor = GridBagConstraints.NORTHWEST;
				gbc_rdbtnMensajes.insets = new Insets(0, 0, 5, 5);
				gbc_rdbtnMensajes.gridx = 0;
				gbc_rdbtnMensajes.gridy = 1;
				panel.add(rdbtnMensajes, gbc_rdbtnMensajes);
			}
			{
				JRadioButton rdbtnEspecialVerano = new JRadioButton("Descuento para Viciados");
				rdbtnEspecialVerano.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mostrarPrecioSinCompromiso();
					}
				});
				rdbtnEspecialVerano.setActionCommand("Descuento para Viciados");
				buttonGroup.add(rdbtnEspecialVerano);
				GridBagConstraints gbc_rdbtnEspecialVerano = new GridBagConstraints();
				gbc_rdbtnEspecialVerano.anchor = GridBagConstraints.NORTHWEST;
				gbc_rdbtnEspecialVerano.insets = new Insets(0, 0, 5, 5);
				gbc_rdbtnEspecialVerano.gridx = 0;
				gbc_rdbtnEspecialVerano.gridy = 2;
				panel.add(rdbtnEspecialVerano, gbc_rdbtnEspecialVerano);
			}
			{
				JRadioButton rdbtnEstandar = new JRadioButton("Descuento de Santa Tecla");
				rdbtnEstandar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mostrarPrecioSinCompromiso();
					}
				});
				rdbtnEstandar.setActionCommand("Descuento de Santa Tecla");
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
				textPrecio = new JTextField();
				textPrecio.setFont(new Font("Tahoma", Font.BOLD, 14));
				textPrecio.setText("???");//texto donde aparece el precio.
				textPrecio.setEditable(false);
				GridBagConstraints gbc_textPrecio = new GridBagConstraints();
				gbc_textPrecio.fill = GridBagConstraints.BOTH;
				gbc_textPrecio.gridx = 3;
				gbc_textPrecio.gridy = 4;
				panel.add(textPrecio, gbc_textPrecio);
				textPrecio.setColumns(10);
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
				if(ControladorUsuarios.getUnicaInstancia().getusuarioActual().isPremium()) {
					okButton.setEnabled(false);
				}
				okButton.setActionCommand("OK");
				okButton.addActionListener((new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!(buttonGroup.getSelection() ==null)) { 
							//si no se ha seleccionado nada no se trata.
							String tipoDescuento = buttonGroup.getSelection().getActionCommand(); //string tipo descuento
							
							//consigo el pago mensual con el descuento seleccionado
							double precio = ControladorUsuarios.getUnicaInstancia().getPrecioPremiumConDescuento(tipoDescuento);
							if(precio != 0.0) {
								//se convierte en usuario premium
								ControladorUsuarios.getUnicaInstancia().hacerUserPremium();
								JOptionPane.showMessageDialog(ventana,
			                            "¡Enhorabuena, eres premium!",
			                            "¡yuju!",
			                            JOptionPane.INFORMATION_MESSAGE);
								okButton.setEnabled(false);
								padre.refrescarVentana();
							}else {
								JOptionPane.showMessageDialog(ventana,
			                            "No cumples con los requisitos especificados",
			                            "Error",
			                            JOptionPane.ERROR_MESSAGE);
							}
						}	
					}
				}));
				
			
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
	}
	
	//----Funcion auxiliar para mostrar los precios antes de pagar.
	private void mostrarPrecioSinCompromiso() {
		String tipoDescuento = buttonGroup.getSelection().getActionCommand(); //string tipo descuento
		//consigo el pago mensual con el descuento seleccionado
		double precio = ControladorUsuarios.getUnicaInstancia().getPrecioPremiumConDescuento(tipoDescuento);
		if(precio != 0.0) {
			Double p = (Double) precio;
			textPrecio.setText(p.toString()+ "€");
		} else {
			textPrecio.setText("N/A");
		}
	}
}
