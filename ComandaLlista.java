import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class ComandaLlista {
    
    public static void mostraFitxersCarpetaTaulells() {
        //modificar el nom de la carpeta on guardarem els taulells
        File cwd = new File("archivos_de_texto/");
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
                //mostrem cada un dels taulells guardats
                
                if (path.getName().endsWith(".txt")) {
                    System.out.printf("" + path.getName() + "%n");
                    
                } 
            }
         }
    }    
    
    public static void main(String[] args) throws IOException {            
        mostraFitxersCarpetaTaulells();
    }        
}
