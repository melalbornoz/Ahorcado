package Controlador;
import Logica.Tablero;

public class Controlador {
	
	public Controlador() {}
	
	public void inicializarTablero(String idioma, String nivel) {
		Tablero.inicializarTablero(idioma, nivel);
	}
	
	public void inicializarTablero(String idioma, String palabra, int intentos) { 
		Tablero.inicializarTablero(idioma, palabra, intentos);
	}
	
	public static void juegaElbot() {
		Tablero.juegaElBot();
	}
	
	public static void arriesgarPalabra(String palabra) {
		Tablero.arriesgarPalabra(palabra);
	}
	
	public static void arriesgarLetra(char letra) {
		Tablero.arriesgarLetra(letra);
	}
	
	public static String getPista() {
		return Tablero.pistaPalabra();
	}
	
	public static String getAcertadas() {
		return Tablero.mostrar();
	}
    
    public static String getArriesgadas() {
    	return Tablero.arriesgadas();
    }
    
	public static String mostrarPalabraSecreta() {
		return Tablero.getPalabraSecreta();
	}
		
	public static boolean getJuegoGanado() {
		return Tablero.getJuegoGanado();
	}

    public static boolean getJuegoPerdido() {
    	return Tablero.getJuegoPerdido();
    }
    
    public static boolean getEstaLetra(char letra) {
    	return Tablero.getEstaLetra(letra);
    }
 
    public static int getIntentosRestantes() {
    	return Tablero.intentosRestantes();
    }

    public static String getNivel() {
    	return Tablero.nivel();
    }
    

}


