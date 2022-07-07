package Vista;

import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Main {

	public static void main(String[] args) {

		try{ 
			  JFrame.setDefaultLookAndFeelDecorated(true);
			  JDialog.setDefaultLookAndFeelDecorated(true);  
			  UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

		}catch (Exception e){
			  e.printStackTrace();
		}	

		EventQueue.invokeLater(new Runnable() {	
			DialogIdioma opcionIdioma = new DialogIdioma();
		   public void run() {
			  try {
        	       opcionIdioma.setVisible(true);
        		   FormInicio ventanaInicio = new FormInicio (opcionIdioma.getIdioma());
        		   ventanaInicio.setVisible(true);
			  } catch (Exception e) {
				   e.printStackTrace();
			   	
  			}
		   }
       });	
	}
}

        
		
        

