package modelo;

/**
 * Clase que implementa la estrategia de Descuento "DescuentoFijo"
 * @author Álvaro y Ana
 *
 */
public class DescuentoFijo implements Descuento {

	private double DescuentoFijo = 3.0; 
	
	/**
	 * calcula el descuento fijo que tiene un usuario premium.
	 * @return pago final una vez aplicado el descuento.
	 */
	public double calcularDescuento(double pago) {
		//calcula el descuento fijo que tiene un usuario premium
		return pago - DescuentoFijo;
	}

}
