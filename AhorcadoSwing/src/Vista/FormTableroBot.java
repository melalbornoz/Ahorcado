package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Controlador.Controlador;

public class FormTableroBot extends JFrame implements ActionListener {
	private static final long serialVersionUID = 2096516197507889852L;
	private String idioma;
	private String nombreBot;
	private Fondo panelPrincipal;
	private Controlador controlador;	
	private ImageIcon imgFondo2, imgFondo3;
	private JButton btnBot, btnVolverInicio;
	private JPanel panelSuperior, panelDerecho;
	private JLabel lblMostrarAcertadas,lblIntentosRestantes, lblMostrarArriesgadas, lblFondo2, lblFondo3;

	//constructor para crear una ventana en la que se visualice como juega un bot
    public FormTableroBot(String idioma , String palabraParaBot, String nombreBot, int intentosBot) {
	   this.controlador = new Controlador();
	   this.controlador.inicializarTablero(idioma , palabraParaBot ,  intentosBot);
			
	   this.idioma = idioma;
	   this.nombreBot = nombreBot;
		    
	   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   this.setBounds(350, 100, 900, 700);
	   this.setLocationRelativeTo(null);
	   this.setTitle("AHORCADO SWING");
	   this.setVisible(true);
		   
	   if(idioma.equals("english"))
		    iniciarComponentes("      BACK    ", "MAKE THE BOT PLAY");
       else 
      	    iniciarComponentes("VOLVER A INICIO", "HACER JUGAR AL BOT");	
	}
		
	//inicia los componentes para que juegue el bot
	private void iniciarComponentes(String volverInicio, String btnBot) {		
		iniciarPaneles();
		iniciarBotones(volverInicio, btnBot);
		iniciarLabel();
	}
		
    private void iniciarPaneles() {
		this.panelPrincipal = new Fondo();
		setContentPane(panelPrincipal);
		this.panelPrincipal.setLayout(new BorderLayout(0, 0));
				    
	    this.panelSuperior = new JPanel();
		this.panelSuperior.setBackground(new Color(240, 128, 128));
		this.panelSuperior.setLayout(new BorderLayout(0, 0));
		this.panelPrincipal.add(this.panelSuperior, BorderLayout.NORTH);
			
		this.panelDerecho = new JPanel();
		this.panelDerecho.setBackground(new Color(187, 143, 206));
		this.panelDerecho.setLayout(new BorderLayout(0, 0));
		this.panelPrincipal.add(this.panelDerecho, BorderLayout.WEST);
	}
    
	private void iniciarBotones(String volverInicio, String btnBot) {
		this.btnBot= new JButton (btnBot);
		this.btnBot.setBackground(Color.PINK);
		this.btnBot.setFont(new Font("Consolas", Font.BOLD, 20));
		this.btnBot.addActionListener(this);
	    this.panelDerecho.add(this.btnBot, BorderLayout.CENTER);
		    
	    this.btnVolverInicio = new JButton (volverInicio);
	    this.btnVolverInicio.setBackground(Color.PINK);
	    this.btnVolverInicio.setFont(new Font("Consolas", Font.BOLD, 20));
		this.btnVolverInicio.addActionListener(this);
		this.panelSuperior.add(this.btnVolverInicio, BorderLayout.WEST );
	}
	
	private void iniciarLabel() {
	    this.lblMostrarArriesgadas = new JLabel();
	    this.lblMostrarArriesgadas.setFont(new Font("Consolas", Font.BOLD, 25));
	    mostrarLetrasArriesgadas();	
	    this.panelPrincipal.add(this.lblMostrarArriesgadas, BorderLayout.SOUTH);
		      
		this.lblIntentosRestantes = new JLabel();
		mostrarIntentosRestantes();
		this.lblIntentosRestantes.setFont(new Font("Consolas", Font.BOLD, 25));
		this.panelSuperior.add(this.lblIntentosRestantes, BorderLayout.CENTER);	
			
		this.lblMostrarAcertadas = new JLabel();
		this.lblMostrarAcertadas.setFont(new Font("Consolas", Font.BOLD, 30));
		this.lblMostrarAcertadas.setText( Controlador.getAcertadas());	
		this.panelPrincipal.add(this.lblMostrarAcertadas, BorderLayout.CENTER);
			
		this.imgFondo2 = new ImageIcon(FormTableroBot.class.getResource("/imagenes/fondo2Juego.jpeg"));
		this.imgFondo3 = new ImageIcon(FormTableroBot.class.getResource("/imagenes/fondo3Juego.jpeg"));
			
		this.lblFondo2 = new JLabel();
		this.lblFondo2.setBounds(0, 0,300, 250);
		this.lblFondo2.setIcon(new ImageIcon(imgFondo2.getImage().getScaledInstance(this.lblFondo2.getWidth(), this.lblFondo2.getHeight(),Image.SCALE_SMOOTH)));
		this.panelDerecho.add(this.lblFondo2, BorderLayout.SOUTH);
			
		this.lblFondo3 = new JLabel();
		this.lblFondo3.setBounds(0, 0,300, 250);
		this.lblFondo3.setIcon(new ImageIcon(imgFondo3.getImage().getScaledInstance(this.lblFondo3.getWidth(), this.lblFondo3.getHeight(),Image.SCALE_SMOOTH)));
		this.panelDerecho.add(this.lblFondo3, BorderLayout.NORTH);	
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.btnVolverInicio==e.getSource()) {
			   volverInicioJuego();
		}	
		else if(this.btnBot==e.getSource()) {
	           Controlador.juegaElbot();
	           if(Controlador.getJuegoPerdido()) {
	        		   msjbotPerdio();
	           }
	           else if(Controlador.getJuegoGanado()) {
	        	        msjbotGano();
	      	   }else {  
		                mostrarLetrasAcertadas();
		                mostrarIntentosRestantes();
		                mostrarLetrasArriesgadas();
		       }
		}
	}
		
	private void msjbotPerdio() {
		if(this.idioma.equals("english")) {
			  JOptionPane.showMessageDialog(null, "UPPPS! "+ this.nombreBot + " HE JUST LOST");
		      volverInicioJuego();
	   }else {
		      JOptionPane.showMessageDialog(null, "UPPPS! " + this.nombreBot + " ACABA DE PERDER :C");
			      volverInicioJuego();
		}
    }
		
	private void msjbotGano() {
		if(Controlador.getJuegoGanado()) {
		   if(this.idioma.equals("english")) {
			  JOptionPane.showMessageDialog(null, "HURRAY!!! " + this.nombreBot + " HE HAS GUESSED THE WORD");
			  volverInicioJuego();
	       }else {
		      JOptionPane.showMessageDialog(null, "HURRA!!! " + this.nombreBot + " HA ADIVINADO LA PALABRA");
		      volverInicioJuego();
	       }
		}
	}
		
	private void mostrarIntentosRestantes() {
	    if(this.idioma.contentEquals("english")) {
	    	this.lblIntentosRestantes.setText("ATTEMPTS: " + Controlador.getIntentosRestantes());
	    }
	    if (this.idioma.contentEquals("espaniol")){ 
	    	this.lblIntentosRestantes.setText("INTENTOS : " + Controlador.getIntentosRestantes());	
	    }
	}
		
	private void mostrarLetrasArriesgadas() {
		if(Controlador.getJuegoPerdido() && this.idioma.equals("english")) {
			 this.lblMostrarArriesgadas.setText("OCCURRENCES: "+ "[" + Controlador.getArriesgadas() + "]");	
		}else {
		     this.lblMostrarArriesgadas.setText("OCURRENCIAS: "+ "[" + Controlador.getArriesgadas() + "]");	
		}
	}
		
	private void mostrarLetrasAcertadas() {
		this.lblMostrarAcertadas.setText(Controlador.getAcertadas());		
	}
		
	private void volverInicioJuego() {
		setVisible(false);
		FormInicio ventanaInicio = new FormInicio(this.idioma);
		ventanaInicio.setVisible(true);
		dispose();
	}	
}
