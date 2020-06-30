package modelo;

public class DescuentoSantaTecla implements Descuento {
	private static final double PORCENTAJE = 0.9;
	
	/**
	 * calcula el descuento joven que tiene un usuario premium.
	 * @return pago final una vez aplicado el descuento.
	 */
	@Override
	public double calcularDescuento(double pago) {
		return pago - (pago*PORCENTAJE);
	}

}
