package vista;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import modelo.Usuario;

public class ContactoRenderer  extends JLabel implements ListCellRenderer<ContactoGUI> {

	public ContactoRenderer() {
        setOpaque(true);
    } 
	@Override
	public Component getListCellRendererComponent(JList<? extends ContactoGUI> list, ContactoGUI usuario, int index,
            boolean isSelected, boolean cellHasFocus) {
		
		String nombre = usuario.getNombre();
		ImageIcon imageIcon = new ImageIcon(usuario.getUsuario().getFotoPerfil());
		ImageIcon imageIcon2 = new ImageIcon(imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		
		setIcon(imageIcon2);
		setText(nombre);
		
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
