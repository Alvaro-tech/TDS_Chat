package modelo;

/**
 * Interfaz para el calculo de los descuentos. Patrón estrategia.
 * @author Álvaro y Ana.
 *
 */
public interface Descuento {
	
	/**
	 * Funcion para calcular el descuento.
	 * @param double pago
	 * @return double precio final
	 */
	public double calcularDescuento(double pago);
	
	/**
	 * Funcion para saber qué estrategia de descuento se ha de seleccionar
	 * según un tipo dado.
	 * @param tipo (de descuento, puede ser "DescuentoFijo" o "DescuentoJoven")
	 * @return Descuento d concreto. Null si el tipo es incorrecto.
	 */
	static Descuento seleccionarDescuento(String tipo) {
		switch (tipo) {
		case "DescuentoFijo":  DescuentoFijo f = new DescuentoFijo();
								return f;
		case "DescuentoJoven":  DescuentoJovenes j = new DescuentoJovenes();
								return j;
		default: return null;
		} 
	}
}
