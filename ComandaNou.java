//creando taulell
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class ComandaNou {
    
    //final static int TAMANY_MAXIM_FILES = 99;
    //final static int TAMANY_MAXIM_COLUMNES = 99;
    
    //final static int TAMANY_MINIM_FILES= 1;
    //final static int TAMANY_MINIM_COLUMNES= 1;    
    
    //static boolean taulellEnConstruccio = true;
    
    // comprobar si les dimensions son  válides
    public static boolean dimensionsValida(int valor) {
        if ((valor < TAMANY_MINIM_FILES || valor > TAMANY_MAXIM_FILES) || (valor < TAMANY_MINIM_COLUMNES || valor > TAMANY_MAXIM_COLUMNES)) {
            System.out.println("Cal un enter entre 1 i 99");
            return false;
        
        }else{
        
            return true;
        }             
    }
    
    public static void nou(){
        //TODO si hi ha un vaixell considerar la opció GUARAD o OBLIDA
        //TODO constantes locales pendiente de mirar si pueden aprovechar las del ConstructorTaulel
        final static int TAMANY_MAXIM_FILES = 99;
        final static int TAMANY_MAXIM_COLUMNES = 99;
    
        final static int TAMANY_MINIM_FILES= 1;
        final static int TAMANY_MINIM_COLUMNES= 1;    
    
        int filesEnter, columnaEnter;
        //entrada estandar
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        
        if (! taulellEnConstruccio) {
        
            //demanem numero de columnes del taulell
            System.out.println("Introdueix número de files del taulell:");
            
            //pendiente control de flujo de entrada
            //suponemos que recibimos siempre digitos        
            do {
                String files = entrada.readLine();
                filesEnter = Integer.parseInt(files);
            
            } while (! dimensionsValida(filesEnter));
                
            //demanem numero de files del taulell
            System.out.println("Introdueix número de columnes del taulell:");
            
            do {
                String columna = entrada.readLine();
                columnaEnter = Integer.parseInt(columna);
            
            } while (! dimensionsValida(columnaEnter));
        
        //caso en el que existeix un taulell 
        } else {
            //TODO
            System.out.println("Ja hi ha un taulell. Considereu les opcions GUARDA o OBLIDA");
            String comanda;
            String[] llistaComandes = {"GUARDA","OBLIDA"};
            
            do {                
                comanda = entrada.readLine();            
            } while (! ConstructorTaulell.comandaEsValida(comanda, llistaComandes));
            
            System.out.println("OK");
        }     
    
    
    public static void main(String[] args) {
        //BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        
        //cridem a la funció nou
        //nou();                 
    }       
}
           
