package ProyectoTDS.tds.um;

import controlador.ControladorUsuarios;
// cd  \Users\alvar\Desktop\Universidad\Persistencia\H2
// java -jar ServidorPersistencia.jar
//ANA-->   C:\Users\pc\Downloads\PersistenciaH2\H2>java -jar ServidorPersistencia.jar
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import modelo.CatalogoUsuarios;
import modelo.Usuario;
import persistencia.PoolDAO;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	System.out.println("algo");
    	for(int i = 0; i<100; i++) {
    		System.out.println(i);
    	}
    	
    	ControladorUsuarios.getUnicaInstancia().registrarUsuario("maria", "email", "fecha", "movil", "clave");
    	Usuario u = CatalogoUsuarios.getUnicaInstancia().getUsuario("movil");
    	Object a = PoolDAO.getUnicaInstancia().getObjeto(u.getId());
        assertTrue( true );
    }
}
