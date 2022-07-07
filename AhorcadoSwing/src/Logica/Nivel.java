package Logica;


//esta clase es para configurar los niveles del juego, la dificultad del juego 
//depende de una cantidad de intentos de jugadas
public class Nivel {
	 private String nivel;
	
     public Nivel(String nivel){
	   if(!nivel.equals("facil") && !nivel.equals("normal") && !nivel.equals("dificil")) {
	      throw new RuntimeException("ingresar nivel valido");
	   }  
	   this.nivel = nivel;

      }
   
	 //segun el nivel dado retorna una cantidad de intentos 
     public int getNivel (){
       if(this.nivel.equals("facil")) {
    	  return getNivelFacil();
       }
	   else if(this.nivel.equals("normal")) {
		  return getNivelNormal();
		  
	   }else {
	      return getNivelDificil();	
	   }
     }
   
     public String getNombreNivel() {
       return this.nivel;
     }
 
     private int getNivelFacil(){	
       return 6;
     }
     
     private int getNivelNormal(){  
       return 3;
     }	 
 
     private int getNivelDificil(){
       return 1;
      }
     
 
}
