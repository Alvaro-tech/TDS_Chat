package vista;

import org.knowm.xchart.style.markers.SeriesMarkers;
import controlador.ControladorUsuarios;

import java.awt.Color;

import org.knowm.xchart.*;

public class PlantillaGraficaAna {
	//Gráficas
	//(POR AHORA)
	//1) LINEAL: numero de mensajes enviados en un mes. X= tiempo (días del mes) Y= mensajes en un día.
	//double[30] x = 1-30 //Double[30] y = mensajes del día (y[1]== mensajes del primer dia del mes)
	//2) TARTA: chats desconocidos vs contactos conocidos vs grupos que tienes
	
	
	public static void main(String[] args) {
		int dias = ControladorUsuarios.getUnicaInstancia().getDiasDelMes();
		
		//1) LINEAL: numero de mensajes enviados en un mes. X= tiempo (días del mes) Y= mensajes en un día.
		//double[30] x = 1-30
		double[] x = new double[dias];
		x[0] = 0.0;
		for (int i = 1; i < x.length; i++) {
			x[i] = (double) i;
		}
		
		//Double[30] y = mensajes del día (y[1]== mensajes del primer dia del mes)
		double[] y = new double[dias];
		y[0] = 0.0;
		for (int i = 1; i < y.length; i++) {
			y[i] = (double) ControladorUsuarios.getUnicaInstancia().getMensajesTotalesDelDia(i);
		}
		
		XYChart chart = new XYChartBuilder().xAxisTitle("Días del mes").yAxisTitle("Mensajes envíados").width(600).height(400).build();
		chart.getStyler().setYAxisMin((double) -50); //lo hago pequeño por ahora
		chart.getStyler().setYAxisMax((double) 50);
	
										//nombre //double[] dataX //Double[] dataY
		XYSeries series = chart.addSeries("GraficaLineal", x , y);
		series.setMarker(SeriesMarkers.NONE);
		new SwingWrapper<XYChart>(chart).displayChart();
		/*
		//2) TARTA: chats desconocidos vs contactos conocidos vs grupos que tienes
		    PieChart pieChart = getChart();
		    new SwingWrapper<PieChart>(pieChart).displayChart();
		    */
	}
	
	/*
	  private static PieChart getChart() {
	 
	    // Create Chart
	    PieChart chart = new PieChartBuilder().width(800).height(600).title("Chats totales").build();
	 
	    // Customize Chart
	    Color[] sliceColors = new Color[] { new Color(224, 68, 14), new Color(230, 105, 62), new Color(243, 180, 159) };
	    chart.getStyler().setSeriesColors(sliceColors);
	 
	    // Series //2) TARTA: chats desconocidos vs contactos conocidos vs grupos que tienes
	    chart.addSeries("Chats de grupo", ControladorUsuarios.getUnicaInstancia().getNumeroGrupos());
	    chart.addSeries("Contactos individuales", ControladorUsuarios.getUnicaInstancia().getNumeroContactos());
	    chart.addSeries("Contactos desconocidos", ControladorUsuarios.getUnicaInstancia().getNumeroContactosDesconocidos());
	 
	    
	    return chart;
	  }
	  */

}
