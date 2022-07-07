package Logica;


import java.util.ArrayList;


public class Tablero {
	  private static Nivel nivelJuego;
	  private static String idiomaTablero;
	  private static Palabra palabraSecreta;
	  private static int intentosDisponibles;
	  private static String palabraArriesgada;
	  private static ArrayList<Character> acertadas;
	  private static ArrayList<Character> letrasArriesgadas;
	  private static Diccionario diccionario;
	   
	 public Tablero() {}
	 
	 public static void inicializarTablero( String idioma, String nivel) {
	     verificarIdiomaValido(idioma);
		 idiomaTablero = idioma;
		 nivelJuego = new Nivel(nivel);
		 diccionario = new Diccionario();
		 seleccionarNivel();
		 seleccionarPalabraSecreta();
		 letrasArriesgadas = new ArrayList<Character>();
		 acertadas = new ArrayList<Character>();
	  }
	  
	 //en el caso de que la palabra ya este dada:
	 public static void inicializarTablero (String idioma, String palabra, int intentos) {
		  verificarIdiomaValido(idioma);
		  if(!getEsPalabraValida(palabra)) {
			  throw new RuntimeException("La palabra no es valida");
		  }
		  if(intentos>26) {
			  throw new RuntimeException("Se ingresaron demasiados intentos");
		  }
		  idiomaTablero = idioma;
		  palabraSecreta = new Palabra(palabra);
		  intentosDisponibles = intentos;
		  letrasArriesgadas = new ArrayList<Character>();
		  acertadas = new ArrayList<Character>();
	  }
	  
      public static int intentosRestantes() { 
		  return intentosDisponibles;
	  }
      
 	  public static String nivel() {
		 return nivelJuego.getNombreNivel();
	  }
 	  
	  public static boolean getJuegoPerdido() {
		  return intentosDisponibles ==0 ? true : false;	
	  }
	 
	  public static String getPalabraSecreta() {
		  return palabraSecreta.getPalabra();
	  }
 
	  public static boolean getJuegoGanado() {
		  return palabraSecreta.getEstaCompleta(acertadas) || 
				 palabraSecreta.getPalabra().equals(palabraArriesgada);
	  }
	  
	  public static void arriesgarPalabra(String palabra) {
	  //se arriesga una palabra, si no resulta ser la palabra secreta se pierden todas las vidas
		  if(!getEsPalabraValida(palabra)) {
			  throw new RuntimeException ("la palabra no es valida");
		  }
		  if(!palabraSecreta.getEsIgual(palabra)) {
			  intentosDisponibles=0;
		  }else {
			  palabraArriesgada = palabra.toLowerCase();		  
		  }	  
	  }
	  
	  public static void arriesgarLetra(char letra) {
	  //se juega una letra, si esa letra esta en la palabra entonces	
	  //se guarda en ocurrencias y se retorna true, sino se guarda en ocurrencias y
	  //se retorna false;
	  //si es false entonces tiene que restarse un intento.  
		  if(!getEsLetraValida(letra)) {
			  throw new RuntimeException ("la letra no es valida");
		  }
		  if(getSeUtilizoLetra(Character.toLowerCase(letra))) {
			  throw new RuntimeException ("la letra ya ha sido ingresada"); 
		  }
		  if(getEstaLetra(letra)) {
			  guardarLetrasArriesgadas(Character.toLowerCase(letra));
			  guardarAcertadas(Character.toLowerCase(letra));
		  }else {
			  guardarLetrasArriesgadas(Character.toLowerCase(letra));
		      intentosDisponibles--;
		  }
	  }
	  
	  public static boolean getEstaLetra(char letra) {
		  return palabraSecreta.getEstaLetra(letra);
	  }
	  
	  public static String pistaPalabra() {
		  return Diccionario.buscarSignificadoPalabra(palabraSecreta.getPalabra());
	  }
	  
	  public static String mostrar() {
		  String pal = "";
		  for(char letra : palabraSecreta.getPalabra().toCharArray()) {
			  if(acertadas.contains(letra)) {
				  pal += Character.toUpperCase(letra) + "  ";
			  }else {
				  pal += "_ ";
			  }	 
		 }
		 return pal;  
	  }

	  public static String arriesgadas() {
		  String a = "";
		  for(char letra: letrasArriesgadas) {
			  a += Character.toUpperCase(letra) + " ";
		  }
		  return a;
	   }
	 
	  //este metodo simula el bot, si el bot arriesga una letra que ya arriesgó entonces vuelve a arriesgar otra
      public static void juegaElBot() {
		boolean estaArriesgada = true;
		while(estaArriesgada) {	
		   int random = (int) (Math.random()*26);
	       int letra = 65 + random;
	       try { 
		       arriesgarLetra((char)letra);
	    	   estaArriesgada = false;    	   
	       }catch(Exception ex) {
	    	   estaArriesgada = true;
	       }
		 }
	   }
	 
//METODOS PRIVADOS///////////////////////////////////////////////////////////////////////////

      private static void verificarIdiomaValido(String idioma) {
    	  if(!idioma.equals("espaniol") && !idioma.equals("english") ){
			  throw new RuntimeException("el idioma no existe");
   	      }
      }
     
      private static void guardarAcertadas(char letra) {
	      acertadas.add(letra);
      }
  
      private static void guardarLetrasArriesgadas(char letra) {
    	  letrasArriesgadas.add(letra);
      }
 
      private static boolean getEsLetraValida (char letra) {
      //lo convertimos en un caracter en mayuscula, ya que el usuario pudo haber ingresado un caracter en minuscula. 
      //la letra tiene que corresponder a una letra valida.
	      if(Character.toUpperCase(letra)<65 || Character.toUpperCase(letra)>90){
		     return false;
	      }
	      return true;	  
       }
  
       private static boolean getEsPalabraValida (String palabra) {
    	  boolean palabraValida = true; 
    	  if(palabra.length()<2){
			  return !palabraValida;
		  }
    	  //verifica que la palabra contenga caracteres validos.  
		  for(int i = 0 ; i<palabra.length(); i++) {
			  palabraValida = palabraValida && getEsLetraValida(palabra.charAt(i));		   
		  }
	      return palabraValida;
       }

       private static void seleccionarNivel() {
		   intentosDisponibles = nivelJuego.getNivel();
	   }
       
       private static void seleccionarPalabraSecreta() {
    	   palabraSecreta = new Palabra(buscarPalabraEnDiccionario());
       }
     
	   private static boolean getSeUtilizoLetra(Character letra) {
	   //no se puede volver a utilizar una letra que ya se jugo 
		   return letrasArriesgadas.contains(letra);	  
	   }
	   
	   private static String buscarPalabraEnDiccionario() {
		  if(idiomaTablero.equals("espaniol")) {
			  return diccionario.getPalabra("espaniol");
		  }else {
			  return diccionario.getPalabra("english");
	   }
	   }
}
