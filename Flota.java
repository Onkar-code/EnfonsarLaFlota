import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// implementació del joc
public class Flota{
    static int[][] taulell;
    static int files;
    static int columnes;
    public static void main(String[] args) throws IOException{
        /*
        En arrencar el programa, carrega el taulell contingut a un fitxer, a partir del nom del taulell segons s’hagi definit a ConstructorTaulell.
        El nom del taulell és proporcionat com a argument de línia de comandes.
        De no trobar-se el taulell amb aquest nom, finalitza execució amb un missatge d’error.
        El programa valida que el contingut del fitxer sigui correcte. De no ser-ho, finalitza execució amb un missatge d’error.
        */
        System.out.println("Nom taulell? ");
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        String nomTaulell = entrada.readLine();
        
        taulell = Fitxers.carregaTaulell(nomTaulell);

        if(taulell != null){
            files = ConstructorTaulell.obteFilesTaulell(taulell);
            columnes = ConstructorTaulell.obteColumnesTaulell(taulell);
        }

        /*
        D’estar tot correcte, el programa mostra el mapa del taulell on, inicialment totes les cel·les apareixen amb el valor ocult sota un símbol d’interrogació.
        Com a ajuda, al voltant dels valors del taulell, apareixeran els noms de la fila/la columna corresponent.
        */
        TaulellJoc.mostraTaulell(taulell);
        //TaulellJoc.mostraTaulell();
    }
}