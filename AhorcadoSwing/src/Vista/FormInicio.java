package Vista;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

public class FormInicio extends JFrame implements ActionListener{
	private static final long serialVersionUID = 4397524904742211859L;
	private String nivel;
	private String idioma;
	private Icon gifFondo;
	private JLabel lblFondo;
	private JButton btnJugar,btnJuegaBot, btnNivelFacil, btnNivelNormal, btnNivelDificil;

	public FormInicio ( String idioma) {
		this.idioma = idioma;
		
		setBounds(350, 100, 650, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("AHORCADO SWING");	
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		if (this.idioma.equals("english")){
		    inicializarComponentes("EASY", "NORMAL", "HARD", "START", "PLAY THE BOT");
		}else {
		    inicializarComponentes("FACIL", "NORMAL", "DIFICIL", "JUGAR", "JUEGA EL BOT");
		}     
	}

	private void inicializarComponentes(String nivelFacil, String nivelNormal, String nivelDificil,String jugar, String jugarBot) {
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		this.btnJugar = new JButton(jugar);
		this.btnJugar.setBounds(205, 282, 220, 60);
		this.btnJugar.setBackground(new Color(176, 58, 46));
		this.btnJugar.setFont(new Font("Consolas", Font.BOLD, 30));
		this.btnJugar.addActionListener(this);
		panel.add(btnJugar);	
		
		this.btnJuegaBot = new JButton(jugarBot);
		this.btnJuegaBot.setBounds(170, 382, 290, 60);
		this.btnJuegaBot.setBackground(new Color(176, 58, 46));
		this.btnJuegaBot.setFont(new Font("Consolas", Font.BOLD, 30));
		this.btnJuegaBot.addActionListener(this);
		panel.add(btnJuegaBot);
		
		this.btnNivelDificil = new JButton(nivelDificil);
    	this.btnNivelDificil.setBounds(230, 202, 170, 40);
    	this.btnNivelDificil.setBackground(new Color(108, 52, 131));
    	this.btnNivelDificil.setFont(new Font("Consolas", Font.BOLD, 30));
    	this.btnNivelDificil.addActionListener(this);
    	panel.add(this.btnNivelDificil);
	
    	this.btnNivelNormal = new JButton(nivelNormal);
    	this.btnNivelNormal.setBounds(230, 122, 170, 40);
	    this.btnNivelNormal.setBackground(new Color(142, 68, 173));
	    this.btnNivelNormal.setFont(new Font("Consolas", Font.BOLD, 30));
	    this.btnNivelNormal.addActionListener(this);
	    panel.add(this.btnNivelNormal);
	
	    this.btnNivelFacil = new JButton(nivelFacil);
	    this.btnNivelFacil.setBounds(230, 40, 170, 40);
	    this.btnNivelFacil.setBackground(new Color(187, 143, 206));
	    this.btnNivelFacil.setFont(new Font("Consolas", Font.BOLD, 30));
	    this.btnNivelFacil.addActionListener(this);
	    panel.add(this.btnNivelFacil);

	    this.gifFondo = new ImageIcon(FormTablero.class.getResource("/imagenes/fondoInicio.gif"));
	    this.lblFondo = new JLabel(gifFondo);
	    this.lblFondo.setBounds(0, 0, 650, 500);
		panel.add(this.lblFondo);
	}

	@Override
    public void actionPerformed(ActionEvent e) {	    	
    	if(this.btnNivelDificil == e.getSource()) {
			this.nivel = "dificil";
		}
    	else if(this.btnNivelNormal == e.getSource()) {
			this.nivel = "normal";
		}
    	else if(this.btnNivelFacil == e.getSource()) {
			this.nivel = "facil";
	    }
    	else if(this.btnJuegaBot == e.getSource()) {
			boolean ingresoValido = false;
    		while(!ingresoValido) {
    		   try {
    			   String nombreBot;
    			   String palabraParaBot;
    			   int intentosBot;
    			  if(this.idioma.equals("english")) {
    			      nombreBot = JOptionPane.showInputDialog("Enter a name for the bot: ");
    			      palabraParaBot = JOptionPane.showInputDialog("Enter a word for the bot to guess: ");
    			      intentosBot = Integer.parseInt(JOptionPane.showInputDialog("Enter a number of attempts for the bot: "));
    			  }else {
    				  nombreBot = JOptionPane.showInputDialog("Ingresa un nombre para el bot: ");
    			      palabraParaBot = JOptionPane.showInputDialog("Ingresa una palabra para que adivine el bot: ");
    			      intentosBot = Integer.parseInt(JOptionPane.showInputDialog("Ingresa una cantidad de intentos para el bot: "));		        
    			  }
    			      FormTableroBot ventanaTablero = new FormTableroBot (this.idioma, palabraParaBot, nombreBot, intentosBot );
    		          ingresoValido = true;
    		   }catch(Exception ex){
    			   if(this.idioma.equals("english")) {
    			      JOptionPane.showMessageDialog(null,"The data entered is invalid, please re-enter it");
    			   }else {
    				  JOptionPane.showMessageDialog(null,"Los datos ingresados no son validos, por favor vuelve a ingresarlos");
    			   }
    			   ingresoValido = false;
    		   }
    		   
		    }
    		setVisible(false); 
    		dispose();
		}
    	else if(this.btnJugar == e.getSource() && this.nivel != null ) {
    	    //que la ventana ya no sea visible	
    		setVisible(false); 
    		dispose();
    		FormTablero ventanaTablero = new FormTablero (this.idioma, this.nivel);
    		//que se liberen todos los recursos de la ventana
    				
    	}
	}
}

			


