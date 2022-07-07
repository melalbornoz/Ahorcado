package Logica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

//el diccionario tiene archivos de palabras en espaniol 
//y palabras en ingles
public class Diccionario {
	private FileReader file;
	private static HashMap<String,String> diccionario = new HashMap<>();
	
	public Diccionario(){}
	  
	//retorna una palabra en espaniol o ingles.
	public String getPalabra(String buscarPal) {
	    try {
			if(buscarPal.contentEquals("espaniol")) {
				this.file = new FileReader ("PalabrasEspaniol.txt");
			}else {
				this.file = new FileReader ("englishWords.txt");
			}          
		}catch (FileNotFoundException e) {
			e.printStackTrace();
	    }	   
	    return getPalabraRandom(leer(this.file));
	 }
	
	public static String buscarSignificadoPalabra(String palabra) {
		return diccionario.get(palabra);
	}
  	
	//lee el archivo donde se encuentran las palabras y las guarda en un ArrayList
    private HashMap<String,String> leer(FileReader file){ 
    	String [] pal; 
	    try {
		   BufferedReader lectura = new BufferedReader (file);
		   String cadena;	   
		   while ((cadena = (String) lectura.readLine())!=null) {
			    pal = cadena.split(":");
			    diccionario.put(pal[0], pal[1]);		   
		   }
			 lectura.close();
		}catch(Exception e) {
			   e.printStackTrace();
		}
		return diccionario;
	 }
	    
    //selecciona un palabra random de entre todas las palabras y la retorna
    private String getPalabraRandom (HashMap<String,String> diccionario) {
	    int random = (int) (Math.random() * diccionario.size());
		String palabra = " ";
		for(String clave: diccionario.keySet()) {
			 if(random == 0) {
				 palabra = clave;
			 }
			 random--;
		}
     	return palabra;   
	}
 }



    