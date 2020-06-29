package modelo;

public interface Descuento {
	public double calcularDescuento(double pago);
	
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
