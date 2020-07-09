package complementosGUI;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import modelo.ChatIndividual;
import modelo.Usuario;

public class ContactoRenderer  extends JLabel implements ListCellRenderer<ChatIndividual> {

	public ContactoRenderer() {
        setOpaque(true);
    } 
	@Override
	public Component getListCellRendererComponent(JList<? extends ChatIndividual> list, ChatIndividual contacto, int index,
            boolean isSelected, boolean cellHasFocus) {
		
		String nombre = contacto.getNombre();
		String saludo = contacto.getContacto().getSaludo();
		
		String aux = nombre + " ---> " + saludo; 
		ImageIcon imageIcon = new ImageIcon(contacto.getContacto().getFotoPerfil());
		ImageIcon imageIcon2 = new ImageIcon(imageIcon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
		
		setIcon(imageIcon2);
		setText(aux);
		
		if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
 
        return this; 
	}
	

}
