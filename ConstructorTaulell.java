
//Aquestes llibreries són necessaries per l'entrada de dades de l'usuari (BufferedReader)
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class ConstructorTaulell{

    // Definim les variables globals:
    // Aquestes dues inidquen el tamany màxim del taulell, són constants.
    final static int TAMANY_MAXIM_FILES = 99;
    final static int TAMANY_MAXIM_COLUMNES = 99;

    //main
    public static void main(String[] args) throws IOException{

        System.out.println("Benvingut a Enfonsar la flota \nAra hi ets al Constructor de taulells");
        System.out.println("Introdueix la comanda que vols aaa executar: ");

        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        String comanda = entrada.readLine();       // llegim una línia de text




        
    }

    //public static void menuAjuda{

    //}

}
