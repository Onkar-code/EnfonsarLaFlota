import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;


public class Fitxers {
    /*
    Aquesta classe dona totes les funcionalitats relacionades amb fitxers: carregar, guardar, escriure, lelgir fitxers...
    */  

    public static void mostraFitxersCarpetaTaulells() {
        //     es vol veure la llista dels taulells guardats
        //     Aquesta opció és vàlida amb i sense taulell en construcció.
        //     El programa mostra la llista dels noms dels taulells guardats.
        //     Si no n’hi ha cap, ho indica amb el missatge Cap vaixell guardat.

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

    public static void guardaTaulell(String nomTaulell, int[][] taulell) throws IOException{
        //TODO

        // sempre guardarem i compararem en minúscules
        nomTaulell = nomTaulell.toLowerCase();

        String path = "taulells/" + nomTaulell + ".taulell";

        ArrayList<String> taulellString = taulellToArrayString(taulell);

        //Si el nom del taulell existeix, es sobreescriu el seu contingut amb el del taulell actual.
        // Atenció: això és una simplificació. Normalment hem de fer que els nostres programes demanin confirmació abans de realitzar accions que puguin fer perdre informació.
        if(fitxerExisteix(nomTaulell)){
            escriuFitxerDeText(path, taulellString, false);
        }else{
            // Ara mateix el control de fluxe no té sentit perquè el true no té efecte, està per si en un futur
            // es vol millor la funcionalitat
            // Si el nom del taulell no existeix, es crea un de nou i s’hi guarda el contingut del taulell actual.
            escriuFitxerDeText(path, taulellString, true);
        }
    }

    public static ArrayList<String> taulellToArrayString(int[][] taulell){
        ArrayList<String> taulellString = new ArrayList<String>();
        String linia = "";

        for(int i = 0; i < taulell.length ;i++){
            for(int j = 0; j < taulell.length ;j++){
                linia += Integer.toString(taulell[i][j]);
            }
            taulellString.add(linia);
            linia = ""; // TODO Java en principi no està pensat per resetejar Strings
        }

        return taulellString;
    }

    public static int[][] taulellArraySListtringToInt(ArrayList<String> taulellString){
        
        //ArrayList<Integer> taulellInt = taulellArrayListStringToArrayListInt(taulellString);
        
        // taulell de sortida
        //int[][] taulell = new int[taulellInt.size()][taulellInt.get(0).length()];
        int[][] taulell = new int[taulellString.size()][taulellString.get(0).length()];


        for(int i = 0;i < taulell.length;i++){
            for(int j = 0;j < taulell[i].length;j++){
                taulell[i][j] = Integer.parseInt(String.valueOf(taulellString.get(i).charAt(j)));
            }
        }

        return taulell;
    }

    // public static ArrayList<Integer> taulellArrayListStringToArrayListInt(ArrayList<String> taulellString){
    //     //TODO
    //     ArrayList<Integer> taulellInt = new ArrayList<Integer>();
        
    //     for(String linia: taulellString){
    //         taulellInt.add(Integer.parseInt(linia));
    //     }

    //     return taulellInt;
        
    //     int[][] taulell = new int[taulellString.size()][taulellString.get(0).length()];

    //     // for(String linia: taulellString){
    //     //     int i = 0;
    //     //     for(int j = 0; j < linia.length();j++){
    //     //         taulell[i][j] = Integer.parseInt(String.valueOf(linia.charAt(j)));
    //     //     }
    //     //     i++;
    //     // }
    //     for(int i = 0; i < taulellString.size() ;i++){
    //         for(int j = 0; j < taulellString.get(i).length() ;j++){
    //             taulell[i][j] = Integer.parseInt(String.valueof(taulellString.get.charAt(j)));
    //         }
    //     }

    // }

    public static boolean fitxerExisteix(String nomTaulell){
        /*
        Recorrem tots els fitxers del directori /taulell i comparem amb el nom passat per paràmetre
        */

        File cwd = new File("taulells/");
        File[] paths = cwd.listFiles();
        for (File path: paths) {
            if (! path.isFile()) {
                continue;   // no és un fitxer regular
            }
            if (path.getName().equals(nomTaulell)) {
                return true;
            }
        }
        return false;
    }

    public static int[][] carregaTaulell(String nomTaulell) throws IOException{

        // Taulell de sortida
        int[][] taulell;
        ArrayList<Integer> taulellArray = new ArrayList<Integer>();

        // Comprovem l'extensió
        if( ! nomTaulell.endsWith(".taulell")){
            nomTaulell = nomTaulell + ".taulell";
        }
    
        //Si el nom del taulell no està a la llista de vaixells disponibles, es mostra error
        if(fitxerExisteix(nomTaulell)){
            // El nom del taulell correspon a un fitxer que conté les dades d’un taulell prèviament guardades per aquest programa.
            // Si el taulell és conegut, s’intenta carregar.
            String path = "taulells/" + nomTaulell;
            ArrayList<String> taulellArrayString = llegeixFitxerDeText(path);

            taulell = taulellArraySListtringToInt(taulellArrayString);

            // TODO Si el contingut del taulell no és vàlid es mostra el missatge Contingut no vàlid. Això no hauria de passar amb els fitxers que hagi guardat aquesta aplicació, però ho comprovem per el fitxer ha estat alterat amb altres mitjans.
            if(! taulellCorrecte(taulell)){
                System.out.println("Contingut no vàlid.");
            }else{
                // Si el contingut és vàlid, el taulell és carregat i es mostra el missatge Fet!.
                System.out.println("Fet!");
                return taulell;
            }
        }
        else{
            System.out.println("Error: Taulell desconegut. Considereu l'opció LLISTA.");
        }
        taulell = null;
        return taulell;
    }

    public static boolean taulellCorrecte(int[][] taulell){
        //TODO
        return true;
    }
    
    /* Donat el camí a un fitxer i una seqüència de línies de text, escriu les
     * línies de text al fitxer indicat.
     * El booleà amplia permet indicar:
     *  true: afegir les línies després dels continguts existents al fitxer
     *  false: reemplaçar els continguts anteriors al fitxer pels nous */
    static void escriuFitxerDeText(String path,
                                      ArrayList<String> linies,
                                      boolean amplia)
                                      throws IOException {
        FileWriter fileWriter = new FileWriter(path, amplia);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (String linia: linies) {
            bufferedWriter.write(linia);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

    /* Donat el camí a un fitxer, llegeix els seus continguts i els retorna en
     * forma d'ArrayList */
    static ArrayList<String> llegeixFitxerDeText(String path) throws IOException {
        ArrayList<String> linies = new ArrayList<String>();
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String linia = "";
        while (linia != null) {
            linia = bufferedReader.readLine();
            if (linia != null) {
                linies.add(linia);
            }
        }
        bufferedReader.close();
        return linies;
    }     
}
