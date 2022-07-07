package Logica;

import java.util.ArrayList;
import java.util.Random;

public class Palabra {
	private String palabra;
	
	
	public Palabra(String palabra){
		this.palabra=palabra;
	}
		
	public String getPalabra() {
		return this.palabra;
	}
	
	public boolean getEstaLetra(char letra) {
        if(palabra.indexOf(Character.toLowerCase(letra)) != -1) {
            return true;     
        }
        else{
           return false;
        }
	}
	
    public boolean getEsIgual(String palabra) {
		return this.palabra.equals(palabra.toLowerCase());
	}

    public boolean getEstaCompleta(ArrayList<Character> letras) {
		boolean estaCompleta=true;
		for(int i=0; i<this.palabra.length();i++) {
			 estaCompleta= estaCompleta && letras.contains(this.palabra.charAt(i)); 
		}
		return estaCompleta;	
	} 
}
