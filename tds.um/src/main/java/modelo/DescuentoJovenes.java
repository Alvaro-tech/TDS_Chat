package modelo;

/**
 * Clase que implementa la estrategia de Descuento "DescuentoJoven"
 * @author Álvaro y Ana.
 *
 */
public class DescuentoJovenes implements Descuento{

	private double DescuentoJoven = 5.0; 
	
	/**
	 * calcula el descuento joven que tiene un usuario premium.
	 * @return pago final una vez aplicado el descuento.
	 */
	public double calcularDescuento(double pago) {
		// calcular el descuento para los jovenes
		return pago - DescuentoJoven;
	}

}
