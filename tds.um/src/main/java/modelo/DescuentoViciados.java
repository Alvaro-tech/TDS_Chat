package modelo;

public class DescuentoViciados implements Descuento {
	private static final double PORCENTAJE = 0.5;

	/**
	 * calcula el descuento joven que tiene un usuario premium.
	 * @return pago final una vez aplicado el descuento.
	 */
	@Override
	public double calcularDescuento(double pago) {
		return pago - (pago * PORCENTAJE);
	}

}
