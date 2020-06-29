package vista;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import modelo.Chat;
import modelo.ChatIndividual;
import modelo.Mensaje;
import modelo.Usuario;

public class ChatRenderer  extends JLabel implements ListCellRenderer<Chat> {

	public ChatRenderer() {
        setOpaque(true);
    } 
	@Override
	public Component getListCellRendererComponent(JList<? extends Chat> list, Chat chat, int index,
            boolean isSelected, boolean cellHasFocus) {
		
		String textAux = "";
		ImageIcon imageIcon2 = null;
		
		// declaración de switch
		switch(chat.getClass().getSimpleName())
		{
		   case "ChatIndividual" :
			   ChatIndividual chatAux = (ChatIndividual) chat;
			   Usuario contacto = chatAux.getContacto();
			   
			  
			   ImageIcon imageIcon = new ImageIcon(contacto.getFotoPerfil());
			   imageIcon2 = new ImageIcon(imageIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
		      break; // break es opcional
		   
		   case "ChatGrupo" :
			   //FOTO
			   ImageIcon imageIcon1 = new ImageIcon("./iconos/grupo.PNG");
			   imageIcon2 = new ImageIcon(imageIcon1.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
		      break; // break es opcional
		}
		
			//Contrucción del Objeto
		   //NOMBRE
		   String nombre = chat.getNombre();
		   //Mensaje
		 //  Mensaje mensaje  = chat.getUltimoMensaje();
		   textAux = "<html> <pre>" + nombre  +  "      fecha"  + "<br/>" + "Esto es un texto de prueba";
		   
		   setIcon(imageIcon2);
		   setText(textAux);
		
		   
		   
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
