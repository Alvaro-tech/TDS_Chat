package ProyectoTDS.tds.um;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import modelo.Usuario;
import vista.ContactoRenderer;

import javax.swing.JList;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;


public class PruebaLista extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	 public static void main(String[] a3d) throws FileNotFoundException, DocumentException {
	      FileOutputStream archivo = new FileOutputStream("C:\\hola.pdf");
	      Document documento = new Document();
	      PdfWriter.getInstance(documento, archivo);
	      documento.open();
	      documento.add(new Paragraph("Hola Mundo!"));
	      documento.add(new Paragraph("SoloInformaticaYAlgoMas.blogspot.com"));
	      documento.close();
	   }

	/**
	 * Create the frame.
	 */
	public PruebaLista() {
		
	

	}
}
