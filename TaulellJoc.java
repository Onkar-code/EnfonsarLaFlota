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

    public static boolean coordenadaAigua(int[] coordenadesVaixell,int[][] taulell){
        boolean aigua = false;

        if(taulell[coordenadesVaixell[0]][coordenadesVaixell[1]] == 0){
            // El valor 0 és el corresponent a l'aigua.
            aigua = true;
        }

        return aigua;
    }

    public static void modificaTaulell(int[] coordenadesVaixell, Boolean[][] taulellBoolea){
        // Modifiquem el taulell boolea perquè l'usuari ha insserit aquestes coordenades.
        taulellBoolea[coordenadesVaixell[0]][coordenadesVaixell[1]] =  true;
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

    // public static boolean coordenadaValida(String entradaVaixell){
    //     if(! ConstructorTaulell.formatCorrecte(entradaVaixell)){
    //         return false;
    //     }
        
    //     // if(! nombresCorrectes(coordenadaVaixell)){
    //     //     return false;
    //     // }

    //     return true;
    // }

    public static boolean formatCorrecte(String coordenadaVaixell){
        // Comprobem la longitud. No pot ser 0 ni més gran que 12 per a un taulell 99x99 ( 90:99, 90) 
        if(coordenadaVaixell.length() < 1 || coordenadaVaixell.length() > 12){
            return false;
        }

        // Ha de contindre "," i "( )"
        if((! coordenadaVaixell.contains(",")) || (! coordenadaVaixell.contains("(")) || (! coordenadaVaixell.contains(")"))){
            return false;
        }

        // No pot contenir cap ":"  i exactament s'ha de tenir 1 coma
        int quantitatDosPunts = 0;
        int quantitatComes = 0;
        char[]coordenadaVaixellCharArray = coordenadaVaixell.toCharArray();
        for( char caracter : coordenadaVaixellCharArray){
            if(caracter == ':'){
                quantitatDosPunts++;
            }
            if(caracter == ','){
                quantitatComes++;
            }
        }

        if(quantitatDosPunts > 0 || quantitatComes != 1){
            return false;
        }
        return true;
    }

    public static int[] obteCoordenadesVaixell(String coordenadaVaixell ){
        // Es considera que coordenadaVaixell té coordenades vàlides. Exemple format: (2,4)

        //Primer treiem els parèntesis i possibles espais
        coordenadaVaixell = coordenadaVaixell.trim();
        coordenadaVaixell = coordenadaVaixell.replace("(","");
        coordenadaVaixell = coordenadaVaixell.replace(")","");

        // Obtenim les coordenades amb contains() i split()
        String[] coordenades = coordenadaVaixell.split(",");
        String coordenadaFilaString = coordenades[0];
        String coordenadaColumnaString = coordenades[1];

        // Iniciatzem les coordenades de surtida a -1, són nombres no vàlids que s'hauran de comprovar posteriorment.
        int coordenadaFila = Integer.parseInt(coordenadaFilaString);
        int coordenadaColumna = Integer.parseInt(coordenadaColumnaString);
    
        // Tornem la matriu de sortida amb les coordenades
        int[] sortida = {coordenadaFila, coordenadaColumna};
        return sortida;
    }
    
    public static boolean nombreFilesCorrecte(int valor, int files) {
        if (valor >= files || valor < 0) {
            return false;
        }else{
            return true;
        }             
     }

     public static boolean nombreColumnesCorrecte(int valor, int columnes) {
        if (valor >= columnes || valor < 0) {
            return false;
        }else{
            return true;
        }             
     }

     public static boolean coordenadesRepetides(int[] coordenadesVaixell,Boolean[][] taulellBoolea){
        boolean repetit = false;

        if(taulellBoolea[coordenadesVaixell[0]][coordenadesVaixell[1]] == true ){
            repetit = true;
        }

        return repetit;
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

    public static boolean coordenadesDintreTaulell(int[] coordenadesVaixell,int files, int columnes ){
        boolean dintre = true;
        for(int i = 0; i < coordenadesVaixell.length ;i++){

                if(! (TaulellJoc.nombreFilesCorrecte(coordenadesVaixell[i], files) && TaulellJoc.nombreColumnesCorrecte(coordenadesVaixell[i], columnes))){
                    dintre = false;
                }
        }

        return dintre;
    }

    public static boolean vaixellEnfonsat(int[] coordenadesVaixell, int[][] taulell, Boolean[][] taulellBoolea){
        boolean enfonsat = false;

        // Primer contemplem el cas dels vaixells de tamany 1
        if(taulell[coordenadesVaixell[0]][coordenadesVaixell[1]] == 1){
            enfonsat = true;
            return enfonsat;
        }

        if(esVaixellHoritzontal(coordenadesVaixell, taulell)){
            // int[] posicioPrevia = {coordenadesVaixell[0], coordenadesVaixell[1] - 1};
            // int[] posicioPosterior = {coordenadesVaixell[0], coordenadesVaixell[1] + 1};

            if(esVaixellHoritzontalEnfonsat(coordenadesVaixell, taulell, taulellBoolea)){
                enfonsat = true;
            }

        }else{ // Si no és horitzontal ni de tamany 1 per força és vertical
            // int[] posicioPrevia = {coordenadesVaixell[0] - 1, coordenadesVaixell[1]};
            // int[] posicioPosterior = {coordenadesVaixell[0] + 1, coordenadesVaixell[1]};

            if(esVaixellVerticalEnfonsat(coordenadesVaixell, taulell, taulellBoolea)){
                enfonsat = true;
            }
        }

        return enfonsat;
    }

    public static boolean esVaixellHoritzontalEnfonsat(int[] coordenadesVaixell, int[][] taulell, Boolean[][] taulellBolea){
        boolean enfonsat = false;

        int fila = coordenadesVaixell[0];
        int columna = coordenadesVaixell[1];

        int tamanyVaixell = taulell[fila][columna];
        int comptadorDanys = 0;

        int danysDreta = comptaImpactesDreta(fila, columna, taulell, taulellBolea);
        int danysEsquerra = comptaImpactesEsquerra(fila, columna, taulell, taulellBolea);

        comptadorDanys = danysEsquerra + danysDreta - 1; //Restem 1 perquè començem pel mateix lloc en ambdues funcions.

        if( comptadorDanys ==  tamanyVaixell){
            enfonsat = true;
        }

        return enfonsat;
        
    }

    public static boolean esVaixellVerticalEnfonsat(int[] coordenadesVaixell, int[][] taulell, Boolean[][] taulellBolea){
        boolean enfonsat = false;

        int fila = coordenadesVaixell[0];
        int columna = coordenadesVaixell[1];

        int tamanyVaixell = taulell[fila][columna];
        int comptadorDanys = 0;

        int danysDreta = comptaImpactesAdalt(fila, columna, taulell, taulellBolea);
        int danysEsquerra = comptaImpactesAbaix(fila, columna, taulell, taulellBolea);

        comptadorDanys = danysEsquerra + danysDreta - 1; //Restem 1 perquè començem pel mateix lloc en ambdues funcions.

        if( comptadorDanys ==  tamanyVaixell){
            enfonsat = true;
        }

        return enfonsat;
        
    }

    public static int comptaImpactesDreta(int fila, int columna, int[][] taulell, Boolean[][] taulellBolea){
        int impactes = 0;

        for(int j = columna; j < taulell[fila].length ;j++){
            if(taulellBolea[fila][j] == true && taulell[fila][j] > 1){
                impactes++;
            }else{
                return impactes;
            }
        }

        return impactes;
    }

    public static int comptaImpactesEsquerra(int fila, int columna, int[][] taulell, Boolean[][] taulellBolea){
        int impactes = 0;

        for(int j = columna; j > 0 ;j--){
            if(taulellBolea[fila][j] == true && taulell[fila][j] > 1){
                impactes++;
            }else{
                return impactes;
            }
        }

        return impactes;
    }

    public static int comptaImpactesAbaix(int fila, int columna, int[][] taulell, Boolean[][] taulellBolea){
        int impactes = 0;

        for(int i = fila; i < taulell.length ;i++){
            if(taulellBolea[i][fila] == true && taulell[i][fila] > 1){
                impactes++;
            }else{
                return impactes;
            }
        }

        return impactes;
    }

    public static int comptaImpactesAdalt(int fila, int columna, int[][] taulell, Boolean[][] taulellBolea){
        int impactes = 0;

        for(int i = fila; i > 0 ;i--){
            if(taulellBolea[i][fila] == true && taulell[i][fila] > 1){
                impactes++;
            }else{
                return impactes;
            }
        }

        return impactes;
    }

    public static boolean esVaixellHoritzontal(int[] coordenadesVaixell, int[][] taulell){
        boolean horitzontal = false;

        int[] posicioPrevia = {coordenadesVaixell[0], coordenadesVaixell[1] - 1};
        int[] posicioPosterior = {coordenadesVaixell[0], coordenadesVaixell[1] + 1};

        try{
            if(( taulell[posicioPrevia[0]][posicioPrevia[1]] != 0 ) || ( taulell[posicioPosterior[0]][posicioPosterior[1]] != 0 )){
                horitzontal = true;
            }    
        }catch(ArrayIndexOutOfBoundsException ex){}

        return horitzontal;
    }
}
