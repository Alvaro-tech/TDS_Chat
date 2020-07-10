package modelo;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.style.markers.SeriesMarkers;
import controlador.ControladorUsuarios;

/**
 * Clase que gestiona la creación de gráficas estadísticas.
 * @author Ana y Álvaro.
 *
 */
public class GestorGraficas {

	public static PieChart getPieChart() {
		// Series //2) TARTA:mostrando los 6 grupos a los que se han enviado más
		// mensajes y para cada grupo qué porcentaje del total de
		// mensajes enviados por ese usuario supone.

		// Create Chart
		PieChart chart = new PieChartBuilder().width(800).height(600).title("Informacion de uso de Grupos").build();

		// Customize Chart
		Color[] sliceColors = new Color[] { new Color(224, 68, 14), new Color(230, 105, 62), new Color(243, 180, 159) };
		chart.getStyler().setSeriesColors(sliceColors);

		List<ChatGrupo> sixTopGrups = ControladorUsuarios.getUnicaInstancia().get6GruposTop();
		for (int i = 0; i < sixTopGrups.size(); i++) {
			ChatGrupo g = sixTopGrups.get(i);
			chart.addSeries(g.getNombre(), g.porcentajeDelTotal(ControladorUsuarios.getUnicaInstancia().getusuarioActual())); //g.porcentajeDelTotal(ControladorUsuarios.getUnicaInstancia().getusuarioActual()
		}
		
		return chart;
	}

	public static XYChart getXYChart() {
		// mostrando el número de mensajes enviados por el usuario en cada mes del año
		// en curso

		// 1) LINEAL: numero de mensajes enviados en un mes. X= tiempo (mes) Y= mensajes
		// en un mes concreto.
		// double[30] x = 1-30
		double[] x = new double[12];
		x[0] = 0.0;
		for (int i = 1; i < x.length; i++) {
			x[i] = (double) i;
		}

		// Double[30] y = mensajes del día (y[1]== mensajes del primer dia del mes)
		double[] y = new double[12];
		y[0] = 0.0;
		for (int i = 1; i < y.length; i++) {
			y[i] = (double) ControladorUsuarios.getUnicaInstancia().getMensajesTotalesDelMes(i);
		}

		XYChart chart = new XYChartBuilder().xAxisTitle("Mes").yAxisTitle("Mensajes envíados").width(600).height(400).title("Mensajes al mes")
				.build();
		chart.getStyler().setYAxisMin((double) -50); // lo hago pequeño por ahora
		chart.getStyler().setYAxisMax((double) 50);

		// nombre //double[] dataX //Double[] dataY
		XYSeries series = chart.addSeries("GraficaLineal", x, y);
		series.setMarker(SeriesMarkers.NONE);

		return chart;
	}

	/**
	 * Convierte una grafica XY en un PNG o un JPG segun el tipo que se le pase.
	 * 
	 * @param XYchart chart
	 * @param String  tipo == "JPG" o "PNG"
	 */
	public void convertirChartEn(XYChart chart, String tipo) {
		// tipo = "PNG" o "JPG"
		if (tipo == "PNG") {

			try {
				BitmapEncoder.saveBitmap(chart, "./estadisticas/" + chart.getTitle(), BitmapFormat.PNG);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (tipo == "JPG") {
			try {
				BitmapEncoder.saveBitmap(chart, "./estadisticas/" + chart.getTitle(), BitmapFormat.JPG);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Convierte una grafica tarta en un PNG o un JPG segun el tipo que se le pase.
	 * 
	 * @param Piechart chart
	 * @param String   tipo == "JPG" o "PNG"
	 */
	public void convertirChartEn(PieChart chart, String tipo) {
		// tipo = "PNG" o "JPG"
		if (tipo == "PNG") {

			try {
				BitmapEncoder.saveBitmap(chart, "./estadisticas/" + chart.getTitle(), BitmapFormat.PNG);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (tipo == "JPG") {
			try {
				BitmapEncoder.saveBitmap(chart, "./estadisticas/" + chart.getTitle(), BitmapFormat.JPG);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
