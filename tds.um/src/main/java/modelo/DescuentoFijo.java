package modelo;

public class DescuentoFijo implements Descuento {

	private double DescuentoFijo = 3.0;
	public double calcularDescuento(double pago) {
		//calcula el descuento fijo que tiene un usuario premium
		return pago - DescuentoFijo;
	}

}
