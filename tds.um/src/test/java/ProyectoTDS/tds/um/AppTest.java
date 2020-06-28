package ProyectoTDS.tds.um;

import java.time.LocalDate;
import java.util.LinkedList;

import controlador.ControladorUsuarios;
// cd  \Users\alvar\Desktop\Universidad\Persistencia\H2
// java -jar ServidorPersistencia.jar
//ANA-->   C:\Users\pc\Downloads\PersistenciaH2\H2>java -jar ServidorPersistencia.jar
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import modelo.CatalogoUsuarios;
import modelo.Chat;
import modelo.ChatGrupo;
import modelo.ChatIndividual;
import modelo.Mensaje;
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
    	/*
    	System.out.println("algo");
    	for(int i = 0; i<100; i++) {
    		System.out.println(i);
    	}
    	
    	ControladorUsuarios.getUnicaInstancia().registrarUsuario("maria", "email", "fecha", "movil", "clave");
    	Usuario u = CatalogoUsuarios.getUnicaInstancia().getUsuario("movil");
    	Object a = PoolDAO.getUnicaInstancia().getObjeto(u.getId());
        assertTrue( true );
    	*/
    	
    	Usuario Ana = new Usuario("Ana", "ana@gamial.com", "18/11/1999", "111", "111");
		Usuario Alvaro = new Usuario("alvaro", "ana@gamial.com", "18/11/1999", "222", "111");
		Usuario Fran = new Usuario("francisco", "ana@gamial.com", "18/11/1999", "444", "111");
		
		ChatIndividual ind1 = new ChatIndividual("111", "Anita", Ana);
		ChatIndividual ind2 = new ChatIndividual("444", "fran", Fran);
		ChatIndividual ind3 = new ChatIndividual("444", "Alvarito", Alvaro);
		
		//ind1.toString()
		
								//emisor, receptor, texto, fecha
		LocalDate f1 = LocalDate.of(2020, 06, 25);
		LocalDate f2 = LocalDate.of(2020, 06, 26);
		LocalDate f3 = LocalDate.of(2020, 06, 27);
		
		Mensaje m1 = new Mensaje(Alvaro, ind1, "holi que tal", f1.toString() );
		Mensaje m2 = new Mensaje(Ana, ind3, "pos mu bien", f2.toString());
		Mensaje m3 = new Mensaje(Alvaro, ind2, "holi que tal", f3.toString() );
		
		ind1.addMensajeHistorial(m1);
		ind2.addMensajeHistorial(m3);
		ind3.addMensajeHistorial(m2);
		
		

		ChatGrupo grupo = new ChatGrupo("chupipandi", ind1, ind2);
		
		Mensaje m4 = new Mensaje(Alvaro, "que tal peñita");
		grupo.addMensajeHistorial(m4);
		
		Ana.agregarChatIndividual(ind2);
		Ana.agregarChatIndividual(ind3);
		Alvaro.agregarChatIndividual(ind1);
		Alvaro.agregarChatIndividual(ind2);
		Fran.agregarChatIndividual(ind1);
		Fran.agregarChatIndividual(ind3);
		
		Ana.agregarChatGrupo(grupo);
		Alvaro.agregarChatGrupo(grupo);
		Fran.agregarChatGrupo(grupo);
		
		ControladorUsuarios.getUnicaInstancia().setUsuarioActual(Ana);
		
		LinkedList<Chat> lista = ControladorUsuarios.getUnicaInstancia().getChatsRecientes();
		for (Chat chat : lista) {
			System.out.println(chat.toString());
		}
    }
}
