package modelo;

import java.time.LocalDate;
import java.time.Month;

/**
 * Interfaz para el calculo de los descuentos. Patrón estrategia.
 * 
 * @author Álvaro y Ana.
 *
 */
public interface Descuento {

	/**
	 * Funcion para calcular el descuento.
	 * 
	 * @param double pago
	 * @return double precio final
	 */
	public double calcularDescuento(double pago);
		public static int MENSAJESVICIO = 2000;
	/**
	 * Funcion para saber qué estrategia de descuento se ha de seleccionar según un
	 * tipo dado.
	 * 
	 * @param tipo (de descuento, puede ser "DescuentoFijo" o "DescuentoJoven")
	 * @return Descuento d concreto. Null si el tipo es incorrecto.
	 */
	static Descuento seleccionarDescuento(String tipo, Usuario u) {
		switch (tipo) {
		case "DescuentoJovenes":
			if (u.getEdad() <= 25) {
				DescuentoJovenes f = new DescuentoJovenes();
				return f;
			} else
				return null;

		case "DescuentoViciados":
			if (u.getNumeroDeMensajesDelMes() >= MENSAJESVICIO) {
				DescuentoViciados j = new DescuentoViciados();
				return j;
			} else
				return null;

		case "DescuentoSantaTecla":
			LocalDate fechaActual = LocalDate.now();
			//Comprueba que se hace el descuento en esos días del mes, en santa tecla
			//Santa Tecla es del 15 al 24 de Septiembre
			if (fechaActual.getMonth().equals(Month.SEPTEMBER) && fechaActual.getDayOfMonth() >= 15 && fechaActual.getDayOfMonth() <=24) {
				DescuentoSantaTecla j = new DescuentoSantaTecla();
				return j;
			} else
				return null;
			
		default:
			return null;
		}
	}
}
