package vista;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

import controlador.ControladorUsuarios;
import modelo.Chat;
import modelo.ChatGrupo;
import modelo.ChatIndividual;
import modelo.Mensaje;
import modelo.Usuario;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;
import java.awt.List;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaLupa extends JDialog {

	private final JPanel usuariosPanel = new JPanel();
	private JTextPane textTextoBuscar;
	//no he usado estas
	private JTextField textFinicial;
	private JTextField textFfinal;
	//he usado estas, porque ya que tengo el parser hecho...
	private JDateChooser fInicio;
	private JDateChooser fFinal;
	private Chat chatCargado;
	private JFrame ventana;
	private ChatIndividual userSelect;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public VentanaLupa(Chat chat, JFrame ventana) {
		chatCargado = chat;												//chat del que se va a buscar
		this.ventana = ventana;
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
		usuariosPanel.setLayout(new FlowLayout());
		usuariosPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_usuariosPanel = new GridBagConstraints();
		gbc_usuariosPanel.fill = GridBagConstraints.BOTH;
		gbc_usuariosPanel.insets = new Insets(0, 0, 5, 0);
		gbc_usuariosPanel.gridx = 1;
		gbc_usuariosPanel.gridy = 1;
		getContentPane().add(usuariosPanel, gbc_usuariosPanel);
		{
			JComboBox<ChatIndividual> comboBox = new JComboBox<ChatIndividual>();
			comboBox.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					userSelect = (ChatIndividual)comboBox.getSelectedItem();
				}
			});
			comboBox.setMaximumRowCount(15);
			comboBox.setSize(300, 30);
			
			if (chat.getClass().getSimpleName().equalsIgnoreCase("ChatGrupo")) {
				DefaultComboBoxModel<ChatIndividual> comboBoxModel = new DefaultComboBoxModel<ChatIndividual>();
				ChatGrupo c1 = (ChatGrupo) chat;
				for (ChatIndividual i : c1.getMiembros()) {
					comboBoxModel.addElement(i);
				}
				comboBox.setModel(comboBoxModel);
			} else {
				comboBox.setEnabled(false);
			}
			
			usuariosPanel.add(comboBox);
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
			textTextoBuscar = new JTextPane();						//testo que se quiere buscar
			textTextoBuscar.setEnabled(true);
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
				fInicio = new JDateChooser();							//fecha a buscar inicial
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
				fFinal = new JDateChooser();							//fecha a buscar final
				panelFechas.add(fFinal);
			}
		}
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
						LocalDateTime fIni = null;
						LocalDateTime fFin = null;
						if(fInicio.getDate() != null && fFinal.getDate() != null) { //se ha seleccionado una fecha
							String fechaDeInicio = ControladorUsuarios.getUnicaInstancia().parsear(fInicio.getDate().toString());
							String fechaDeFin = ControladorUsuarios.getUnicaInstancia().parsear(fFinal.getDate().toString());
					        fIni = LocalDateTime.parse(fechaDeInicio, DateTimeFormatter.ofPattern("dd/MM/yyyy H:mm:ss"));
					        fFin = LocalDateTime.parse(fechaDeFin, DateTimeFormatter.ofPattern("dd/MM/yyyy H:mm:ss"));
						}
						String textoABuscar = textTextoBuscar.getText();
						//TODO: lo del usuario, por ahora lo pongo a null cuando llamas al controlador.
						LinkedList<Mensaje> listMenEnc = ControladorUsuarios.getUnicaInstancia().BuscarPorFiltro(chatCargado, textoABuscar, fIni, fFin, userSelect);
						
						for (Mensaje mensaje : listMenEnc) {
							System.out.println("############ -> " + mensaje.getTexto());
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
