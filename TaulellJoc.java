import java.io.IOException;
import java.util.Arrays;

// import per al diccionari de les descripciones d'AJUDA
import java.util.Hashtable;
import java.util.Set;

public class TaulellJoc {
    public static int[][] creaTaulell(int[][] taulellOriginal){

        int[][] copy = Arrays.stream(taulellOriginal).map(int[]::clone).toArray(int[][]::new);

        return copy;
    }

    public static Boolean[][] creaTaulellBoolea(int[][] taulell){

        Boolean[][] taulellBoolea = new Boolean[taulell.length][taulell[0].length];

        for(int i = 0; i < taulell.length ;i++){
            for(int j = 0; j < taulell[0].length ;j++){
                    // Inicialitzem tots els valors a false
                    taulellBoolea[i][j] = false;
            }
        }

        return taulellBoolea;
    }


    public static void ajuda(){
        //el programa mostra un text d’ajuda amb les diferents comandes disponibles.

        System.out.println("Les comandes que pots utilizar són:");

        //Descripció comandes com a hashtable (diccionari), s'utiliza amb la comanda AJUDA
        Hashtable<String, String> descripcioComandes = new Hashtable<String, String>();
        descripcioComandes.put("AJUDA","Mostra les diferents comandes disponibles.");
        descripcioComandes.put("SOLUCIONA","Resol el taulell. Hi perdràs!");
        descripcioComandes.put("JUGA","Introdueix les coordenades del vaixell a esbrinar. Format (y0:y1, x0:x1)");
        descripcioComandes.put("SURT","Finalitza el joc.");

        // Obtinc totes les claus de descripcioComanes perquè vull recorrer el diccionari.
        Set<String> keysDescripcioComandes = descripcioComandes.keySet();

        //Recorrem les descripcions
        for(String key : keysDescripcioComandes){
            System.out.println( key + ": " + descripcioComandes.get(key));
        }
        System.out.println();

    }

    public static void mostraTaulell(int[][] taulell, Boolean[][] taulellBoolea){

        int files = ConstructorTaulell.obteFilesTaulell(taulell);
        int columnes = ConstructorTaulell.obteColumnesTaulell(taulell);

        // Crearem el taulell que és una matriu de strings a partir del taulell numeric
        String[][] taulellString = new String[files][columnes];
    
        for(int i = 0; i < files ;i++){
            for(int j = 0; j < columnes ;j++){
                if(taulell[i][j] == 0){
                    // El valor 0 corresponent a l'aigua és canvia pel "."
                    taulellString[i][j] = ".";
                }else{
                    taulellString[i][j] = Integer.toString(taulell[i][j]);
                }
            }
        }

        // El mostrem per pantalla
        for(int i = 0; i < files ;i++){
            for(int j = 0; j < columnes ;j++){
                if(taulellBoolea[i][j] == true){
                    System.out.print(taulell[i][j] + " ");
                }else{
                    System.out.print("? ");
                }
            }
            System.out.println();
        }
    }

    public static void mostraTaulell(Boolean[][] taulell){

        // Crearem el taulell que és una matriu de strings a partir del taulell numeric
        String[][] taulellString = new String[taulell.length][taulell[0].length];

        // El mostrem per pantalla
        for(int i = 0; i < taulell.length ;i++){
            for(int j = 0; j < taulell[0].length ;j++){
                System.out.print(taulell[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean coordenadaValida(String entradaVaixell){
        if(! ConstructorTaulell.formatCorrecte(entradaVaixell)){
            System.out.println("1");
            return false;
        }
        
        // if(! nombresCorrectes(coordenadaVaixell)){
        //     return false;
        // }

        return true;
    }

    public static int[][] obteCoordenadesVaixell(String coordenadaVaixell ){
        // Es considera que coordenadaVaixell té coordenades vàlides.

        //Primer treiem els parèntesis i possibles espais
        coordenadaVaixell = coordenadaVaixell.trim();
        coordenadaVaixell = coordenadaVaixell.replace("(","");
        coordenadaVaixell = coordenadaVaixell.replace(")","");

        // Obtenim les coordenades amb contains() i split()
        String[] coordenades = coordenadaVaixell.split(",");
        String coordenadesFilaString = coordenades[0];
        String coordenadesColumnaString = coordenades[1];

        // Iniciatzem les coordenades de surtida a -1, són nombres no vàlids que s'hauran de comprovar posteriorment.
        int[] coordenadaFila = {-1, -1};
        int[] coordenadaColumna = {-1, -1};

        // Comprovem els casos de dimensiones majors a 1
        if(coordenadesFilaString.contains(":")){
            String[] coordenadaFilaString = coordenadesFilaString.split(":");
            coordenadaFila = Utility.StringArrayToIntArray(coordenadaFilaString);

        }else if(coordenadesColumnaString.contains(":")){
            String[] coordenadaColumnaString = coordenadesColumnaString.split(":");
            coordenadaColumna = Utility.StringArrayToIntArray(coordenadaColumnaString);

        }else{
            coordenadaFila[0] = Integer.parseInt(coordenadesFilaString) ;
            coordenadaColumna[0] = Integer.parseInt(coordenadesColumnaString);
        }
    
        // Tornem la matriu de sortida amb les coordenades
        int[][] sortida = {coordenadaFila, coordenadaColumna};
        return sortida;
    }

    public static void soluciona(int[][] taulell){

        int files = ConstructorTaulell.obteFilesTaulell(taulell);
        int columnes = ConstructorTaulell.obteColumnesTaulell(taulell);

        // Crearem el taulell que és una matriu de strings a partir del taulell numeric
        String[][] taulellString = new String[files][columnes];
    
        for(int i = 0; i < files ;i++){
            for(int j = 0; j < columnes ;j++){
                if(taulell[i][j] == 0){
                    // El valor 0 corresponent a l'aigua és canvia pel "."
                    taulellString[i][j] = ".";
                }else{
                    taulellString[i][j] = Integer.toString(taulell[i][j]);
                }
            }
        }

        // El mostrem per pantalla
        for(int i = 0; i < files ;i++){
            for(int j = 0; j < columnes ;j++){
                    System.out.print(taulell[i][j] + " ");
            }
            System.out.println();
        }

        // Sortim del joc
        System.out.println("\nJo guanyo!");
        System.exit(0);
        
    }
}
