import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// implementació del joc
public class Flota{
    static String nomTaulell;
    static Boolean fitxerTrobat;
    static int[][] taulellOriginal;
    static int[][] taulell;
    static Boolean[][] taulellBoolea;

    static String[] llistaComandes = {"SURT","SOLUCIONA","AJUDA","JUGA"};
    static int files;
    static int columnes;

    public static void main(String[] args) throws IOException{
        /*
        En arrencar el programa, carrega el taulell contingut a un fitxer, a partir del nom del taulell segons s’hagi definit a ConstructorTaulell.
        El nom del taulell és proporcionat com a argument de línia de comandes.
        De no trobar-se el taulell amb aquest nom, finalitza execució amb un missatge d’error.
        El programa valida que el contingut del fitxer sigui correcte. De no ser-ho, finalitza execució amb un missatge d’error.
        */
        System.out.println("Benvingut a Enfonsar la flota \nAra hi ets al Joc principal.");

        do{
        System.out.println("Introdueix el nom del taulell que vols carregar: ");
        BufferedReader entradaTaulell = new BufferedReader(new InputStreamReader(System.in));
        nomTaulell = entradaTaulell.readLine().toLowerCase();

        // Quan l'usuari escrigui llista li mostrarem els taulells guardats
        if(nomTaulell.equals("llista")){
            Fitxers.mostraFitxersCarpetaTaulells();
        }

        fitxerTrobat = Fitxers.fitxerExisteix(nomTaulell);

        if(! fitxerTrobat){
            System.out.println("Fitxer no trobat. Considera Llista.");
            continue;
        }

        taulellOriginal = Fitxers.carregaTaulell(nomTaulell);

        if(taulellOriginal != null){
            //Creem el taulell de joc i el taullell que es indicarà els intents amb la posició respectiva
            taulell = TaulellJoc.creaTaulell(taulellOriginal);
            taulellBoolea = TaulellJoc.creaTaulellBoolea(taulellOriginal);

            // Obtenim les dimensiones del taulell
            files = taulell.length;
            columnes = taulell[0].length;

            // Finalment, mostrem el taulell inicial de joc
            TaulellJoc.mostraTaulell(taulell, taulellBoolea);
        }
        }while(! fitxerTrobat);

        String comanda;

        // Amb aquest do()while{} el que demanem és l'entrada de la comanda per part de l'usuari fins que entri una comanda vàlida.
        do{

            System.out.println("Introdueix la comanda que vols executar ( escriu 'AJUDA' per veure les comandes ): ");

            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
            comanda = entrada.readLine();       // llegim una línia de text

            //si l'entrada és vàlida llavors és processarà amb la funció gestorComanda
            /*
            D’estar tot correcte, el programa mostra el mapa del taulell on, inicialment totes les cel·les apareixen amb el valor ocult sota un símbol d’interrogació.
            Com a ajuda, al voltant dels valors del taulell, apareixeran els noms de la fila/la columna corresponent.
            */
            if(ConstructorTaulell.comandaEsValida(comanda, llistaComandes)){
                gestorComanda(comanda);
            }else{
                System.out.println("Comanda no vàlida. Torna a intentar-ho ( escriu 'AJUDA' per veure les comandes )");
            }

        }
        while (! comanda.toUpperCase().equals("SURT"));

    }

    public static void gestorComanda(String comanda){

        // Si l'entrada és vàlida la convertim en majúscules
        comanda = comanda.toUpperCase();

        switch(comanda){
            case "AJUDA":
                TaulellJoc.ajuda();
                break;

            case "SOLUCIONA":
                TaulellJoc.soluciona(taulellOriginal);
                break;

            case "JUGA":
                juga();
                break;

            case "SURT":
                ConstructorTaulell.surt();
                break;

            // en principi el default no s'executarà mai. Podriem tancar el programa si s'executa aquest codi.
            default: System.out.println("Comanda desconeguda, estem al final del switch");
        }
    }

    public static void juga(){
        System.out.println("Introdueix el vaixell: ");
            try{
                BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
                String entradaVaixell = entrada.readLine();

                if( TaulellJoc.formatCorrecte(entradaVaixell)){
                    int[] coordenadesVaixell = TaulellJoc.obteCoordenadesVaixell(entradaVaixell);

                    // Verifiquem que no hi ha nombres negatius en les coordenades.
                    if( Utility.matriuAmbNombresNegatius(coordenadesVaixell)){
                        System.out.println("Has d'introduir nombres positius com a coordenades! (més grans o iguals a 0).");
                        return;
                    }

                    // // Dimensions vaixell
                    // int filesVaixell = coordenadesVaixell[0].length;
                    // int columnesVaixell = coordenadesVaixell[1].length;

                    // // Coordenades del vaixell
                    // int x0 = coordenadesVaixell[0][0];
                    // if(filesVaixell > 1){
                    //     int x1 = coordenadesVaixell[0][1];
                    //     System.out.println("x1: " + x1);
                    // }

                    // int y0 = coordenadesVaixell[1][0];
                    // if(columnesVaixell > 1){
                    //     int y1 = coordenadesVaixell[1][1];
                    //     System.out.println("y1: " + y1);
                    // }

                    // System.out.println("x0: " + x0 + " y0:" + y0);

                    // Comprovem que les coordenades hi són dintre del taulell
                    if(! TaulellJoc.coordenadesDintreTaulell(coordenadesVaixell, files, columnes)){
                        System.out.println("Les coordenades hi són fora del taulell!");
                        return;
                    }
                    
                    /*
                    Si és una cel·la ja destapada, el programa respon amb un missatge que invita la jugadora a estar més atenta.
                    */
                    // Comprovem que les coordenades no són repetides!
                    if( TaulellJoc.coordenadesRepetides(coordenadesVaixell, taulellBoolea)){
                        System.out.println("Ja havies introduit aquesta coordenada!");
                        return;
                    }

                    /*
                    Si és una cel·la que contenia aigua, el programa respondrà «AIGUA» i mostrarà el mapa del taulell amb la cel·la destapada.
                    */
                    if( TaulellJoc.coordenadaAigua(coordenadesVaixell, taulell)){
                        TaulellJoc.modificaTaulell(coordenadesVaixell, taulellBoolea);
                        System.out.println("AIGUA!");
                        TaulellJoc.mostraTaulell(taulell, taulellBoolea);
                        return;
                    }else{
                        TaulellJoc.modificaTaulell(coordenadesVaixell, taulellBoolea);
                        if(TaulellJoc.vaixellEnfonsat(coordenadesVaixell,taulell, taulellBoolea)){
                            System.out.println("TOCAT I ENFONSAT!");
                            TaulellJoc.mostraTaulell(taulell, taulellBoolea);

                        }else{
                            System.out.println("TOCAT!");
                            TaulellJoc.mostraTaulell(taulell, taulellBoolea);
                        }
                    }
                }else{
                    //  Si el vaixell està malament descrit, o no pot ser col·locat al lloc indicat perquè no hi cap o bé perquè toca un altre vaixell, es mostra el missatge d’error Vaixell no vàlid.
                    System.out.println("Vaixell no vàlid.");
                }
            }catch(IOException e){
                System.out.println("Error");
            }
    }
}