import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;


public class prova {

    public static void main(String[] args) throws IOException{
        
        String nomTaulell = "/taulells/fitxer.taulell";

        if(fitxerExisteix(nomTaulell)){
            System.out.println("Fitxer existeix");
        }else{
            System.out.println("Fitxer NO existeix");

        }
    }

    static boolean fitxerExisteix(String nomTaulell) throws IOException{
        /*
        Recorrem tots els fitxers del directori /taulell i comparem amb el nom passat per paràmetre
        */
        File cwd = new File("taulells/");
        File[] paths = cwd.listFiles();
        for (File path: paths) {
            if(path.exists()){
                if (! path.isFile()) {
                    continue;   // no és un fitxer regular
                }

                if (path.getName() == ".") {
                    return true;
                }
           }
        }
        return false;
    }
}

