package Vista;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import Controlador.Controlador;


public class FormTablero extends JFrame implements ActionListener {
	private static final long serialVersionUID = 956525646482534960L;
	private String idioma; 
    private JButton [] btnLetras;
	private Controlador controlador;
    private JScrollPane scrollVentana;
    private JTextField textArriesgarPalabra;
    private ImageIcon imgHorca,imgHorcaGameOver;
    ArrayList <ImageIcon>  imgHorcaNormal, imgHorcaFacil;
    private JButton  btnPista, btnVolverInicio, btnArriesgarPalabra,btnBot;
    private JLabel lblMostrarAcertadas,lblIntentosRestantes, lblMostrarArriesgadas, lblHorca;
    private JPanel  panelPrincipal,panelSuperior, panelCentral, panelIzquierdo, panelTeclasLetras, 
                    panelMostrarPartida, panelArriesgarPalabra, panelMostrarAcertadas, panelMostrarIntentos;
  
    //constructor para crear una ventana en la que juege el usuario
    public FormTablero(String idioma, String nivel) {   
       //inicializamos el controlador
	   this.controlador = new Controlador();
	   this.controlador.inicializarTablero(idioma, nivel);
		
	   this.idioma = idioma;
		    
	   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   this.setBounds(350, 100, 1100, 800);
	   this.setLocationRelativeTo(null);
	   this.setTitle("AHORCADO SWING");
	   this.setVisible(true);
		
       scrollVentana = new JScrollPane();
	        
       if(idioma.equals("english"))
		    iniciarComponentes("      BACK      ", "     CLUE     ","   RISK A WORD   " );
       else 
        	iniciarComponentes("VOLVER A INICIO", "   VER PISTA   ","ARRIESGAR PALABRA");
	}
	
	//inicia los componentes para que juegue el usuario
	private void iniciarComponentes(String volverInicio, String pista, String arriesgarPal) {			
	   iniciarPaneles();
	   iniciarBotones(volverInicio,  pista, arriesgarPal);
	   iniciarLabel();
		
	   this.textArriesgarPalabra = new JTextField();
	   this.textArriesgarPalabra.setFont(new Font("Consolas", Font.BOLD, 26));
	   this.panelArriesgarPalabra.add(this.textArriesgarPalabra, BorderLayout.CENTER);
			
	   //Scroll para nuestra ventana, para que podamos redimensionar a traves
	   //de una barra deslizable tanto vertical como horizontalmente.
	   scrollVentana.setViewportView(this.panelPrincipal);
	   getContentPane().add(this.scrollVentana, BorderLayout.SOUTH);
	}

	private void iniciarPaneles() {
	   getContentPane().setLayout(new BorderLayout(0, 0));
	   this.panelPrincipal = new JPanel();
       this.panelPrincipal.setLayout(new BorderLayout(0, 0));
			
	   this.panelSuperior = new JPanel();
	   this.panelSuperior.setBackground(new Color(35,48,59));
	   this.panelPrincipal.add(this.panelSuperior, BorderLayout.NORTH);
			
	   this.panelCentral = new JPanel();
	   this.panelCentral.setBackground(new Color(232, 218, 239));
	   this.panelPrincipal.add(this.panelCentral, BorderLayout.CENTER);
	   
	   this.panelIzquierdo = new JPanel();
	   this.panelIzquierdo.setBackground(Color.PINK);
	   this.panelIzquierdo.setLayout(new BorderLayout(0, 0));
	   this.panelPrincipal.add(this.panelIzquierdo, BorderLayout.EAST);

	   this.panelTeclasLetras = new JPanel();
	   this.panelTeclasLetras.setBackground(new Color(208, 211, 212 ));
	   this.panelIzquierdo.add(this.panelTeclasLetras, BorderLayout.SOUTH);
	   this.panelTeclasLetras.setLayout(new GridLayout(3,9, 0, 0));
			
	   this.panelMostrarPartida = new JPanel();
	   this.panelMostrarPartida.setLayout(new BorderLayout(0, 0));
	   this.panelIzquierdo.add(this.panelMostrarPartida, BorderLayout.CENTER);
			
	   this.panelArriesgarPalabra = new JPanel();
	   this.panelArriesgarPalabra.setLayout(new BorderLayout(0, 0));
	   this.panelMostrarPartida.add(this.panelArriesgarPalabra, BorderLayout.SOUTH);
		
	   this.panelMostrarAcertadas = new JPanel();
	   this.panelMostrarAcertadas.setLayout(null);
	   this.panelMostrarPartida.add(this.panelMostrarAcertadas, BorderLayout.CENTER);
		
	   this.panelMostrarIntentos = new JPanel();
	   this.panelMostrarIntentos.setLayout(new BorderLayout(0, 0));
	   this.panelMostrarPartida.add(this.panelMostrarIntentos, BorderLayout.NORTH);
	}
		
	private void iniciarBotones(String volverInicio, String pista, String arriesgarPal) {    
	   this.btnVolverInicio = new JButton (volverInicio);
       this.btnVolverInicio.setFont(new Font("Consolas", Font.BOLD, 26));
	   this.btnVolverInicio.addActionListener(this);
	   this.panelSuperior.add(this.btnVolverInicio);
		
	   this.btnPista = new JButton (pista);
	   this.btnPista.setFont(new Font("Consolas", Font.BOLD, 26));
	   this.btnPista.addActionListener(this);
	   this.panelSuperior.add(this.btnPista);
		    
	   this.btnArriesgarPalabra = new JButton(arriesgarPal);
	   this.btnArriesgarPalabra.setFont(new Font("Consolas", Font.BOLD, 20));
	   this.btnArriesgarPalabra.setBackground(Color.RED);
       this.btnArriesgarPalabra.addActionListener(this);
	   this.panelArriesgarPalabra.add(this.btnArriesgarPalabra, BorderLayout.WEST);
	    
       this.btnLetras = new JButton [26];
	   for(int i=0 ; i < this.btnLetras.length ; i++) {
			int letra = 65 + i;
			this.btnLetras[i] = new JButton(""+ (char)letra);
			this.btnLetras[i].setBackground(new Color(195, 155, 211 ));
			this.btnLetras[i].setFont(new Font("Consolas", Font.BOLD, 34));
			this.btnLetras[i].addActionListener(this);
			this.panelTeclasLetras.add(this.btnLetras[i]);
		}
	}
	
    private void iniciarLabel() {
	   this.imgHorcaNormal = new ArrayList<ImageIcon>();
	   this.imgHorcaFacil= new ArrayList<ImageIcon>();
	   imagenes();
	
       this.lblHorca = new JLabel();
	   this.lblHorca.setBounds(5, 90,500, 700);
	   this.lblHorca.setIcon(new ImageIcon(imgHorca.getImage().getScaledInstance(this.lblHorca.getWidth(), this.lblHorca.getHeight(),Image.SCALE_SMOOTH)));
	   panelCentral.add(this.lblHorca);	
	   
	   this.lblMostrarAcertadas = new JLabel();
	   this.lblMostrarAcertadas.setFont(new Font("Consolas", Font.BOLD, 18));
	   this.lblMostrarAcertadas.setText( Controlador.getAcertadas());	
	   this.lblMostrarAcertadas.setBounds(150, -100, 500, 700);
	   this.panelMostrarAcertadas.add(this.lblMostrarAcertadas);
	   
       this.lblMostrarArriesgadas = new JLabel();
       this.lblMostrarArriesgadas.setFont(new Font("Consolas", Font.BOLD, 18));
       mostrarLetrasArriesgadas();	
       this.panelMostrarIntentos.add(this.lblMostrarArriesgadas, BorderLayout.SOUTH);
		
   	   this.lblIntentosRestantes = new JLabel();
	   mostrarIntentosRestantes();
	   this.lblIntentosRestantes.setBounds(0, 0, 200, 200); 
	   this.lblIntentosRestantes.setFont(new Font("Consolas", Font.BOLD, 18));
	   this.panelMostrarIntentos.add(this.lblIntentosRestantes, BorderLayout.NORTH);
	}
		
	private void imagenes() {	
       this.imgHorca = new ImageIcon(FormTablero.class.getResource("/imagenes/horca.jpeg"));
       this.imgHorcaGameOver =new ImageIcon(FormTablero.class.getResource("/imagenes/horcaGameOver.jpeg"));
			 
	   for(int i = 1 ; i < 3  ; i ++ ) {
        	this.imgHorcaNormal.add(new ImageIcon(FormTablero.class.getResource("/imagenes/normal" + i + ".jpeg")));
       } 
	   for(int i = 1 ; i < 6; i ++ ) {
			this.imgHorcaFacil.add(new ImageIcon(FormTablero.class.getResource("/imagenes/facil" + i + ".jpeg")));	
       }
	}
    
	///realizar acciones para los botones
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0 ; i < 26 ; i++) {
			int letra = 65 + i;
			if(this.btnLetras[i]==e.getSource()) {		
				arriesgarLetra((char)letra);
				this.btnLetras[i].setEnabled(false);
				mostrarLetrasAcertadas();
				mostrarLetrasArriesgadas();
				if(!Controlador.getEstaLetra((char)letra)) {
				     mostrarIntentosRestantes();
				     dibujarIntentosRestantes();
				}	
				mostrarMsjJuegoPerdido();
				mostrarMsjJuegoGanado();
			}
		}
		if(this.btnArriesgarPalabra==e.getSource()) {
		   try {
			  arriesgarPalabra(this.textArriesgarPalabra.getText());
			  if(!Controlador.mostrarPalabraSecreta().equals(this.textArriesgarPalabra.getText())) {
			      mostrarIntentosRestantes();
			      dibujarIntentosRestantes();
			  }
			  mostrarMsjJuegoPerdido();
			  mostrarMsjJuegoGanado();
		    }catch(Exception ex) {
			  if(this.idioma.equals("english")) 
				  JOptionPane.showMessageDialog(null, "the word entered is not valid!!!");
			  else
			      JOptionPane.showMessageDialog(null, "La palabra ingresada no es valida!!!");
		   }
		 }
		else if(this.btnPista==e.getSource()) {	
		   mostrarPista();
		}	
		else if(this.btnVolverInicio==e.getSource()) {
		   volverInicioJuego();
		}
	}
	
	private void arriesgarLetra(char letra) {
		Controlador.arriesgarLetra(letra);
	}
	
	private void arriesgarPalabra(String textArriesgarPalabra) {
		Controlador.arriesgarPalabra(this.textArriesgarPalabra.getText());
	}
	
	private void volverInicioJuego() {
		setVisible(false);
		FormInicio ventanaInicio = new FormInicio(this.idioma);
		ventanaInicio.setVisible(true);
		dispose();
	}
	
	private void mostrarIntentosRestantes() {
	    if(this.idioma.contentEquals("english")) {
	    	this.lblIntentosRestantes.setText("AVAILABLE ATTEMPTS: " + Controlador.getIntentosRestantes());
	    }
	    if (this.idioma.contentEquals("espaniol")){ 
	    	this.lblIntentosRestantes.setText("INTENTOS DISPONIBLES: " + Controlador.getIntentosRestantes());	
	    }
	}
	
	private void dibujarIntentosRestantes() {
	    mostrarIntentosRestantes();
	    
		if(Controlador.getIntentosRestantes() == 0) {
	    	this.lblHorca.setIcon(new ImageIcon(this.imgHorcaGameOver.getImage().getScaledInstance(this.lblHorca.getWidth(), this.lblHorca.getHeight(),Image.SCALE_SMOOTH)));
		}		
		else if(Controlador.getNivel().equals("normal")) {
			this.lblHorca.setIcon(new ImageIcon(this.imgHorcaNormal.get(Controlador.getIntentosRestantes()-1).getImage().getScaledInstance(this.lblHorca.getWidth(), this.lblHorca.getHeight(),Image.SCALE_SMOOTH)));	
    	}		
		else if(Controlador.getNivel().equals("facil")){
			this.lblHorca.setIcon(new ImageIcon(this.imgHorcaFacil.get(Controlador.getIntentosRestantes()-1).getImage().getScaledInstance(this.lblHorca.getWidth(), this.lblHorca.getHeight(),Image.SCALE_SMOOTH)));	
		}
	}
	
	private void mostrarLetrasAcertadas() {
		this.lblMostrarAcertadas.setText(Controlador.getAcertadas());		
	}
	
	private void mostrarPista() {
		JOptionPane.showMessageDialog(null, Controlador.getPista());
	}

	private void mostrarLetrasArriesgadas() {
		if(Controlador.getJuegoPerdido() && this.idioma.equals("english")) {
			 this.lblMostrarArriesgadas.setText("OCCURRENCES: "+ "[" + Controlador.getArriesgadas() + "]");	
		}else {
		     this.lblMostrarArriesgadas.setText("OCURRENCIAS: "+ "[" + Controlador.getArriesgadas() + "]");	
		}
	}
	
	private void mostrarMsjJuegoPerdido() {
		if(Controlador.getJuegoPerdido() && this.idioma.equals("english")) {
			JOptionPane.showMessageDialog(null,"you lost! the word was: " + Controlador.mostrarPalabraSecreta().toUpperCase() + "\n Press ok to restart the game ");
			volverInicioJuego();
		}else if (Controlador.getJuegoPerdido() && this.idioma.equals("espaniol")){
			JOptionPane.showMessageDialog(null,"Perdiste! La palabra era: " + Controlador.mostrarPalabraSecreta().toUpperCase() + "\nPresiona ok para volver a iniciar el juego");
			volverInicioJuego();
		}			
	}
	
	private void mostrarMsjJuegoGanado() {
		if(Controlador.getJuegoGanado() && this.idioma.equals("english")) {
			JOptionPane.showMessageDialog(null,"You won! The word was: " + Controlador.mostrarPalabraSecreta().toUpperCase() + "\nPress ok to restart the game ");
			volverInicioJuego();
		}else if (Controlador.getJuegoGanado() && this.idioma.equals("espaniol")){
			JOptionPane.showMessageDialog(null,"Ganaste! La palabra era: " + Controlador.mostrarPalabraSecreta().toUpperCase() + "\nPresiona ok para volver a iniciar el juego");
			volverInicioJuego();
		}
		 
	}

}
