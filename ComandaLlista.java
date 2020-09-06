import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class ComandaLlista {
    
    public static void mostraFitxersCarpetaTaulells() {
        //el enunciat indica que el directori s'anomenará taulells 
        File cwd = new File("taulells/");
        File[] paths = cwd.listFiles();      

        //comprovem si no hi ha cap taulell guardat
        if (paths.length == 0) {
            System.out.println("Cap vaixell guardat");
        } else {
        
            System.out.printf("***TAULELLS GUARDATS***%n");
            for (File path: paths) {
                if (! path.isFile()) {
                    continue; 
                }          
                //mostrem cada un dels taulells guardats com .taulell segons l'enunciat
                
                if (path.getName().endsWith(".taulell")) {
                    System.out.printf(path.getName() + "%n");
                    
                } 
            }
         }
    }    
    
    public static void main(String[] args) throws IOException {            
        mostraFitxersCarpetaTaulells();
    }        
}
