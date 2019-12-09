package ProyectoTDS.tds.um;

import java.util.StringTokenizer;


/**
 * Hello world!
 *
 */
public class Pruebas 
{
    public static void main( String[] args )
    {
        System.out.println( "Has entrado en pruebas secundarias:" );
        
        String aux = "123:Alvaro 444:Luisa 213:Papapapep";
        aux.trim();
        StringTokenizer strTokId = new StringTokenizer(aux, " ");

        while (strTokId.hasMoreTokens()) {
        	String cosas = (String) strTokId.nextElement();
        	String[] parts = cosas.split(":");
        	System.out.println("Id: " + parts[0] + " valor: " + parts[1]);
			//int hola = Integer.valueOf((String) strTokId.nextElement());
		}
        
    }
}
