package complementosGUI;

import java.awt.Component;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import modelo.Chat;
import modelo.ChatIndividual;
import modelo.Mensaje;
import modelo.Usuario;


public class EmojiRenderer extends JLabel implements ListCellRenderer<JLabel> {
	public EmojiRenderer() {
        setOpaque(true);
    } 
	@Override
	public Component getListCellRendererComponent(JList<? extends JLabel> list, JLabel label, int index,
            boolean isSelected, boolean cellHasFocus) {
		
		Icon imageIcon2 = label.getIcon();
		
		   
		   setIcon(imageIcon2);
		   
		   
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
