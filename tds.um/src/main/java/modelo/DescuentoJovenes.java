package modelo;

public class DescuentoJovenes implements Descuento{

	private double DescuentoJoven = 5.0;
	public double calcularDescuento(double pago) {
		// calcular el descuento para los jovenes
		return pago - DescuentoJoven;
	}

}
