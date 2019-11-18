package interfazGráfica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.JToolBar;
import java.awt.ScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import java.awt.Panel;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import java.awt.Scrollbar;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class VentanaMain {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMain window = new VentanaMain();
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
	public VentanaMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setAlignmentY(Component.CENTER_ALIGNMENT);
		menuBar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JButton btnImagen = new JButton("Foto");
		menuBar.add(btnImagen);
		
		JButton btnCuenta = new JButton("Cuenta");
		menuBar.add(btnCuenta);
		
		JMenu mnNewMenu = new JMenu(" ");
		mnNewMenu.setEnabled(false);
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("New menu");
		mnNewMenu.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("New menu");
		mnNewMenu.add(mnNewMenu_2);
		
		JButton btnEstado = new JButton("Estado");
		menuBar.add(btnEstado);
		
		JButton btnBuscar = new JButton("Lupa");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnOpciones = new JButton("Opciones");
		menuBar.add(btnOpciones);
		menuBar.add(btnBuscar);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JTextArea editorPane = new JTextArea();
		editorPane.setColumns(7);
		panel.add(editorPane);
		
		JEditorPane editorPane_1 = new JEditorPane();
		editorPane_1.setBackground(Color.BLUE);
		panel.add(editorPane_1);
	}

}
