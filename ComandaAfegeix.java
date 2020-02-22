/*
comanda per afeguir un vaixell en cas de que hi hagui un taulell en construcció 
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class ComandaAfegeix {
    
    BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
    static String[] nouCarrega = {"NOU","CARREGA"};
    public static void afegeix() {
        //si hay  un taulell en construcció, afegim
        if (! ConstructorTaulell.taulellEnConstruccio) {
            //si no hi ha un taulell en construcció,  Nou o Carreguem             
            ConstructorTaulell.subMenuComandes(nouCarrega);         
            System.out.println("alex marica");            
        } else {
            //TODO
            System.out.println("afegeix, deberías de llamar a la función on afegueix els vaixells");                 
        }    
    }
    public static void main(String[] args) throws IOException {
        afegeix();    
        
    }    
} 
