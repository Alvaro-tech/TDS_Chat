package complementosGUI;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

import modelo.Chat;
import modelo.ChatIndividual;
import modelo.Mensaje;
import modelo.Usuario;

public class EmojiRenderer extends JLabel implements ListCellRenderer<ImageIcon> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public EmojiRenderer() {
        setOpaque(true);
    } 
	@Override
	public Component getListCellRendererComponent(JList<? extends ImageIcon> list, ImageIcon imagen, int index,
            boolean isSelected, boolean cellHasFocus) {
		
		   setIcon(imagen);
		   
		  //Cambiar la tonalidad del fondo cuando un objeto es seleccionado 
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
