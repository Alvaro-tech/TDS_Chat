package modelo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import controlador.ControladorUsuarios;

public class PDFGenerator {

	/** Path del  PDF creado. */
	public static final String PATH = "results/part1/chapter01/hello.pdf";

	/**
	 * Crea un archivo PDF
	 */
	public static void main(String[] args) throws DocumentException, IOException {
		new PDFGenerator().createPdf(PATH);
	}

	/**
	 * Crea un documento PDF
	 * 
	 * @param filename (path) para el nuevo Documento PDF
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void createPdf(String filename) throws DocumentException, IOException {
		// creo el documento pdf
		Document document = new Document();
		// lo escribo
		PdfWriter.getInstance(document, new FileOutputStream(filename));
		// abro el documento pa escribir en él los cosos
		document.open();
		// Añado un párrafo: el de los chatsIndividuales
		document.add(new Paragraph(ControladorUsuarios.getUnicaInstancia().getInfoChatsIndividualesPDF()));
		// Añado un párrafo: el de los grupos
		document.add(new Paragraph(ControladorUsuarios.getUnicaInstancia().getInfoChatsGrupoPDF()));
		// step 5
		document.close();
	}

}
