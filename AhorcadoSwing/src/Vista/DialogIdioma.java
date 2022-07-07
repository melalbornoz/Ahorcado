package Vista;

import javax.swing.JDialog;
import javax.swing.JOptionPane;


public class DialogIdioma extends JDialog{
	private static final long serialVersionUID = 6556522915375617712L;
    private String idioma;

    public DialogIdioma() {
            String[] idiomas = {"espaniol", "english"};         
            
            //dialogo para seleccionar opciones
            //mostramos un dialogo, un titulo, el tipo de opcion y el tipo de mensaje, 
            //que le indicamos que es de pregunta, y pasamos el conjunto de opciones
            int opcionSeleccionada = JOptionPane.showOptionDialog(this, 
             "            Welcome to AHORCADO SWING \n\n please select a language to configure the game:\n"
            , "message", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, idiomas, idiomas[0]);
           
            idioma = idiomas[opcionSeleccionada];
     }
        
    public String getIdioma() {
    	return idioma;
    }

}
