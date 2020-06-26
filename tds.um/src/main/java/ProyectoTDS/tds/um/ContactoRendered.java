package ProyectoTDS.tds.um;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import modelo.Usuario;

public class ContactoRendered  extends JLabel implements ListCellRenderer<Usuario> {

	public ContactoRendered() {
        setOpaque(true);
    } 
	@Override
	public Component getListCellRendererComponent(JList<? extends Usuario> list, Usuario usuario, int index,
            boolean isSelected, boolean cellHasFocus) {
		
		String nombre = usuario.getNombre();
		ImageIcon imageIcon = new ImageIcon(usuario.getFotoPerfil());
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
