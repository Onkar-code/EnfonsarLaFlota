
//Aquestes llibreries són necessaries per l'entrada de dades de l'usuari (BufferedReader)
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class ConstructorTaulell{

/*
    // Definim les variables globals:
    // Aquestes quatre inidquen el tamany màxim i mínim del taulell, són constants.
    
    final static int TAMANY_MAXIM_FILES = 99;
    final static int TAMANY_MAXIM_COLUMNES = 99;
    
    final static int TAMANY_MINIM_FILES= 1;
    final static int TAMANY_MINIM_COLUMNES= 1;   
*/

    //Amb aquesta variable booleana sabem si tenim algun taulell en construcció o no
    static boolean taulellEnConstruccio = false;

    //Aquesta variable String és el missatge que imprimim per pantalla quan executem una comanda que no permet treballar sense taulell.
    String missatgeNoHiHaTaulell = "No hi ha cap taulell. Considereu les opcions NOU o CARREGA";

    //Aquesta llista representa totes les comandes disponibles
    static String[] llistaComandes = {"AJUDA","AFEGEIX","CARREGA","ELIMINA","GUARDA","LLISTA","NOU","MOSTRA","OBLIDA"}; 


    //main
    public static void main(String[] args) throws IOException{

        System.out.println("Benvingut a Enfonsar la flota \nAra hi ets al Constructor de taulells");

        String comanda;

        // Amb aquest do()while{} el que demanem és l'entrada de la comanda per part de l'usuari fins que entri una comanda vàlida.
        do{
            System.out.println("Introdueix la comanda que vols executar ( escriu 'AJUDA' per veure les comandes ): ");
            
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
            comanda = entrada.readLine();       // llegim una línia de text

        }
        while (! comandaEsValida(comanda, llistaComandes));

        //si l'entrada és vàlida llavors és processarà amb la funció gestorComanda

        // TODO gestorComanda(comanda)
        
    }

    public static boolean comandaEsValida(String comanda, String[] llistaComandes){
        // Aquesta funció accepta una comanda i retorna true si la comanda és vàlida i false en cas contrari

        //Primer convertim la comanda a majúscules per no distingir entre majúscules i minúscules
        comanda = comanda.toUpperCase();


        //Amb aquest booleà sabrem si la comanda pertany a la llista de comandes vàlides (llistaComandes).
        boolean trobat = false;

        // Recorrem tota la llista de comandes vàlides i si hi ha una coincidencia trobat passa a ser certa i sortim.
        for(int i = 0; i < llistaComandes.length;i++){
            if(comanda.equals(llistaComandes[i])){
                trobat = true;
                return trobat;
            }
           }

        //Finalment, comrpovem que l'usuari no vulgui sortir:
        if(comanda.equals("SURT")){
            System.exit(0);
        }

        // si no s'ha trobat i no s'ha sortit, s'executa aquesta part del codi, es torna el false ja que la comanda no existeix.
        System.out.println("Comanda desconeguda");
        return false;

        }


        public static void gestorComanda(String comanda){

        switch(comanda){
            case "AJUDA":

            case "AFEGEIX":

            case "CARREGA":

            case "ELIMINA":

            case "GUARDA":

            case "LLISTA":

            case "NOU":
                ComandaNou.nou();

            case "MOSTRA":

            case "OBLIDA":
            

            // en principi el default no s'executarà mai. Podriem tancar el programa si s'executa aquest codi.
            default: System.out.println("Comanda desconeguda");
        }
    }


/*
****************************************************************************
                                COMANDES VALIDES
****************************************************************************
*/
/*

    public static void ajuda{
        //el programa mostra un text d’ajuda amb les diferents comandes disponibles.

        System.out.println("Les comandes que pots utilizar són: \n");

        String comandesEnComu = "AJUDA, LLISTA, SURT";

        if (taulellEnConstruccio){

        }

        else{

        }


    }

    public static void afegeix{
        // es vol afegir un nou vaixell al taulell.

        //Si no hi ha cap taulell avisem
        if(! taulellEnConstruccio){
            System.out.println(missatgeNoHiHaTaulell);
        }

        else{
            // TODO descripcio vaixell i comprovació
            // TODO Si no hi ha un taulell en construcció (estat inicial), es mostra el missatge d’error No hi ha cap taulell. Considereu les opcions NOU o CARREGA.

            // TODO Si sí hi ha un taulell, el programa demana la descripció del vaixell. Recorda que un vaixell es descriu amb una coordenada amb possible rang en una de les dimensions. Ex. (5:7, 1) correspon a un vaixell de longitud 3, col·locat de manera vertical a la columna 1, entre les files 5 i 7.

            // TODO Si el vaixell està malament descrit, o no pot ser col·locat al lloc indicat perquè no hi cap o bé perquè toca un altre vaixell, es mostra el missatge d’error Vaixell no vàlid.

            // TODO Si el vaixell ha estat descrit correctament i, en col·locar-lo al taulell actual, el taulell es manté vàlid, llavors el programa l’afegeix al taulell.

            // TODO En cas que tot hagi anat bé, el programa ho indicarà amb el missatge Fet!
        }

    }
    public static void carrega{
        // es vol carregar un taulell guardat previament.

        // si ja hi ha un taulell apareix aquest missatge d'error. 
        if( taulellEnConstruccio){
            System.out.println("Ja hi ha un taulell. Considereu les opcions GUARDA o OBLIDA");
        }
        else{
            //TODO 
            //TODO Si no hi ha cap vaixell en construcció, demana el nom d’un vaixell.

            //TODO Si el nom del taulell no està a la llista de vaixells disponibles, es mostra l’error Taulell desconegut. Considereu l'opció LLISTA.

            //TODO El nom del taulell correspon a un fitxer que conté les dades d’un taulell prèviament guardades per aquest programa.

            //TODO Si el taulell és conegut, s’intenta carregar.

            //TODO Si el contingut del taulell no és vàlid es mostra el missatge Contingut no vàlid. Això no hauria de passar amb els fitxers que hagi guardat aquesta aplicació, però ho comprovem per el fitxer ha estat alterat amb altres mitjans.

            //TODO Si el contingut és vàlid, el taulell és carregat i es mostra el missatge Fet!.
        }

    }
    public static void elimina{
        // es vol eliminar un vaixell del taulell.

        //Si no hi ha cap taulell avisem
        if(! taulellEnConstruccio){
            System.out.println(missatgeNoHiHaTaulell);
        }

        else{
            //descripció vaixell
            //TODO Si el vaixell està malament descrit, o no es troba al taulell es mostra el missatge d’error Vaixell no vàlid.

            //TODO Si el vaixell ha estat descrit correctament i es troba al taulell, llavors el programa l’elimina del taulell.

            //TODO En cas que tot hagi anat bé, el programa ho indicarà amb el missatge Fet!
        }


    }
    public static void guarda{
        //es vol guardar el taulell en construcció

        //Si no hi ha cap taulell avisem
        if(! taulellEnConstruccio){
            System.out.println(missatgeNoHiHaTaulell);
        }

        else{
            //TODO Si hi ha un taulell en construcció, es demana un nom pel taulell.

            //TODO Si el nom del taulell existeix, es sobreescriu el seu contingut amb el del taulell actual. Atenció: això és una simplificació. Normalment hem de fer que els nostres programes demanin confirmació abans de realitzar accions que puguin fer perdre informació.

            //TODO Si el nom del taulell no existeix, es crea un de nou i s’hi guarda el contingut del taulell actual.

            //TODO  En cas que tot hagi anat bé, el programa ho indicarà amb el missatge Fet!.

        }

    }
    public static void llista{
        // es vol veure la llista dels taulells guardats
        // Aquesta opció és vàlida amb i sense taulell en construcció.
        // El programa mostra la llista dels noms dels taulells guardats.


    }
    public static void nou{

    }
    public static void mostra{

    }
    public static void oblida{

    }
*/
}



