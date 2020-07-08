package modelo;


import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import controlador.ControladorUsuarios;

public class PDFGenerator {

	/** Path del  PDF creado. */
	public static final String PATH = "results/part1/chapter01/hello.pdf";


	/**
	 * Genera el documento PDF. A esta funcion es la que llama el controlador.
	 * @param u
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void generarPDF(Usuario u) throws DocumentException, IOException {
		//genero el pdf
		new PDFGenerator().createPdf(PATH, u);
	}
	
	/**
	 * Crea un documento PDF
	 * 
	 * @param filename (path) para el nuevo Documento PDF
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void createPdf(String filename, Usuario u) throws DocumentException, IOException {
		// creo el documento pdf
		Document document = new Document();
		// lo escribo
		PdfWriter.getInstance(document, new FileOutputStream(filename));
		// abro el documento pa escribir en él los cosos
		document.open();
		//añado un titulo
		document.addHeader("Contactos\n", "Lista de tus contactos y los numeros de teléfono asociados. \n");
		// Añado un párrafo: el de los chatsIndividuales
		document.add(new Paragraph(this.getInfoChatsIndividualesPDF(u)));
		//añado un titulo.
		document.addHeader("Grupos y miembros de estos\n", "Lista de grupos y miembros de estos. \n");
		// Añado un párrafo: el de los grupos
		document.add(new Paragraph(this.getInfoChatsGrupoPDF(u)));
		// step 5
		document.close();
		
	}
	
	/**
	 * Funcion que devuelve la info del usuario de sus chats individuales.
	 * nombre + movil.
	 * @return Chunk informacion.
	 */
	public Chunk getInfoChatsIndividualesPDF(Usuario u) {
		return  new Chunk(u.getInfoChatsIndividuales(),
				 FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL, BaseColor.BLACK));
	}
	
	/**
	 * Funcion que devuelve la info del usuario sobre sus grupos.
	 * @return Chunk informacion.
	 */
	public Chunk getInfoChatsGrupoPDF(Usuario u) {
		return  new Chunk(u.getInfoGrupo(),
				 FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL, BaseColor.BLACK));
	}

}
