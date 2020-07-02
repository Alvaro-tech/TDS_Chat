package modelo;

/**
 * Clase que implementa la estrategia de Descuento "DescuentoJoven"
 * @author Álvaro y Ana.
 *
 */
public class DescuentoJovenes implements Descuento{
	//El descuento Joven consiste en el 30%
	private static final double PORCENTAJE = 0.3;
	
	/**
	 * calcula el descuento joven que tiene un usuario premium.
	 * @return pago final (de un mes) una vez aplicado el descuento.
	 */
	public double calcularDescuento(double pago) {
		// calcular el descuento para los jovenes
		return pago - (pago*PORCENTAJE);
	}

}
