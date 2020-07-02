package vista;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

import modelo.Chat;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;

public class VentanaLupa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFinicial;
	private JTextField textFfinal;

	private Chat chatCargado;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public VentanaLupa(Chat chat) {
		chatCargado = chat;												//chat del que se va a buscar
		//TODO: meter un Usuario para hacer pruebas. Será el usuario del que se hace la busqueda.
		setBounds(100, 100, 550, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{79, 323, 0};
		gridBagLayout.rowHeights = new int[]{39, 37, 61, 59, 30, 33, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{
			JCheckBox chckbxUsuario = new JCheckBox("Usuario");
			chckbxUsuario.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_chckbxUsuario = new GridBagConstraints();
			gbc_chckbxUsuario.fill = GridBagConstraints.BOTH;
			gbc_chckbxUsuario.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxUsuario.gridx = 0;
			gbc_chckbxUsuario.gridy = 1;
			getContentPane().add(chckbxUsuario, gbc_chckbxUsuario);
			
			System.out.println("EN ventana lupa me llega el chat con la clase: " + chat.getClass().getSimpleName() );
			if(chat.getClass().getSimpleName().equalsIgnoreCase("ChatIndividual")){
				chckbxUsuario.setEnabled(false);
			}
		}
		
	
		
		{
			JCheckBox chckbxTexto = new JCheckBox("Texto    ");
			chckbxTexto.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_chckbxTexto = new GridBagConstraints();
			gbc_chckbxTexto.fill = GridBagConstraints.HORIZONTAL;
			gbc_chckbxTexto.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxTexto.gridx = 0;
			gbc_chckbxTexto.gridy = 2;
			getContentPane().add(chckbxTexto, gbc_chckbxTexto);
		}
		{
			JTextPane textTextoBuscar = new JTextPane();						//testo que se quiere buscar
			textTextoBuscar.setEnabled(false);
			GridBagConstraints gbc_textTextoBuscar = new GridBagConstraints();
			gbc_textTextoBuscar.insets = new Insets(0, 0, 5, 0);
			gbc_textTextoBuscar.fill = GridBagConstraints.BOTH;
			gbc_textTextoBuscar.gridx = 1;
			gbc_textTextoBuscar.gridy = 2;
			getContentPane().add(textTextoBuscar, gbc_textTextoBuscar);
		}
		{
			JCheckBox chckbxFechas = new JCheckBox("Fechas ");
			chckbxFechas.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_chckbxFechas = new GridBagConstraints();
			gbc_chckbxFechas.fill = GridBagConstraints.BOTH;
			gbc_chckbxFechas.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxFechas.gridx = 0;
			gbc_chckbxFechas.gridy = 3;
			getContentPane().add(chckbxFechas, gbc_chckbxFechas);
		}
		{
			JPanel panelFechas = new JPanel();
			GridBagConstraints gbc_panelFechas = new GridBagConstraints();
			gbc_panelFechas.insets = new Insets(0, 0, 5, 0);
			gbc_panelFechas.fill = GridBagConstraints.BOTH;
			gbc_panelFechas.gridx = 1;
			gbc_panelFechas.gridy = 3;
			getContentPane().add(panelFechas, gbc_panelFechas);
			
			{
				textFinicial = new JTextField();
				textFinicial.setEditable(false);
				textFinicial.setText("Fecha Inicial:");
				panelFechas.add(textFinicial);
				textFinicial.setColumns(10);
			}
			{
				JDateChooser fInicio = new JDateChooser();							//fecha a buscar inicial
				panelFechas.add(fInicio);
			}
			{
				textFfinal = new JTextField();
				textFfinal.setText("Fecha Final:");
				textFfinal.setEditable(false);
				panelFechas.add(textFfinal);
				textFfinal.setColumns(10);
			}
			{
				JDateChooser fFinal = new JDateChooser();							//fecha a buscar final
				panelFechas.add(fFinal);
			}
		}
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.fill = GridBagConstraints.BOTH;
		gbc_contentPanel.insets = new Insets(0, 0, 5, 0);
		gbc_contentPanel.gridx = 1;
		gbc_contentPanel.gridy = 4;
		getContentPane().add(contentPanel, gbc_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			GridBagConstraints gbc_buttonPane = new GridBagConstraints();
			gbc_buttonPane.anchor = GridBagConstraints.NORTH;
			gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_buttonPane.gridx = 1;
			gbc_buttonPane.gridy = 5;
			getContentPane().add(buttonPane, gbc_buttonPane);
			{
				JButton okButton = new JButton("Buscar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//TODO: hacer para las busquedas
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
