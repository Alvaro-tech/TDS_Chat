package ProyectoTDS.tds.um;


import org.junit.Test;

import controlador.ControladorUsuarios;
//java -jar ServidorPersistencia.jar


public class TestRegistro {
	private ControladorUsuarios controlador = ControladorUsuarios.getUnicaInstancia();
	@Test
	public void testRegistro() {
		controlador.registrarUsuario(
				"UsuarioTest",
				"text@um.es",
				"09/03/1998",
				"678339904",										
				"contraseña");
	}
	
}
