package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.style.markers.SeriesMarkers;


import controlador.ControladorUsuarios;

import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import javax.swing.Box;

public class VentanaEstadisticas extends JFrame {

	private JPanel contentPane;
	private JPanel panelGrafica;
	private Chart<?, ?> aux;
	private JComboBox comboBox;
	private String formatoSelect = "PNG";

	
	/**
	 * Create the frame.
	 */
	public VentanaEstadisticas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.NORTH);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formatoSelect = (String) comboBox.getSelectedItem();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"PNG", "JPG"}));
		panelBotones.add(comboBox);
		
		JButton btnImprimir = new JButton("Imprimir");
		panelBotones.add(btnImprimir);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panelBotones.add(horizontalStrut);
		
		JButton btnLineal = new JButton("Lineal");
		panelBotones.add(btnLineal);
		
		JButton btnTarta = new JButton("Tarta");
		panelBotones.add(btnTarta);
		
		panelGrafica = new JPanel();
		panelGrafica.setBackground(Color.MAGENTA);
		contentPane.add(panelGrafica, BorderLayout.CENTER);
		
		btnTarta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aux = ControladorUsuarios.getUnicaInstancia().crearEstadisticas("Pie");
				PieChart aux1 = (PieChart) aux;
				JPanel panelChart = new XChartPanel(aux1);
				
				contentPane.remove(panelGrafica);
				panelGrafica = panelChart;
				
				contentPane.add(panelGrafica, BorderLayout.CENTER);
				
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		btnLineal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aux = ControladorUsuarios.getUnicaInstancia().crearEstadisticas("XY");
				XYChart aux1 = (XYChart) aux;
				
				JPanel panelChart = new XChartPanel(aux1);
				
				contentPane.remove(panelGrafica);
				panelGrafica = panelChart;
				
				contentPane.add(panelGrafica, BorderLayout.CENTER);
				
				contentPane.revalidate();
				contentPane.repaint();
				
			}
		});
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ControladorUsuarios.getUnicaInstancia().imprimirChart(formatoSelect, aux);
				
			}
		});
	}

}
