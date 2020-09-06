
//Aquestes llibreries són necessaries per l'entrada de dades de l'usuari (BufferedReader i Scanner)
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;

// import per al diccionari de les descripciones d'AJUDA
import java.util.Hashtable;
import java.util.Set;

// Utilitzem la funció max()
import java.lang.Math;

public class ConstructorTaulell{


    // Definim les variables globals:
    // Aquestes quatre inidquen el tamany màxim i mínim del taulell, són constants.
    
    final static int TAMANY_MAXIM_FILES = 99;
    final static int TAMANY_MAXIM_COLUMNES = 99;
    
    final static int TAMANY_MINIM_FILES= 1;
    final static int TAMANY_MINIM_COLUMNES= 1;   


    //Amb aquesta variable booleana sabem si tenim algun taulell en construcció o no
    static boolean taulellEnConstruccio = false;

    //Aquesta variable String és el missatge que imprimim per pantalla quan executem una comanda que no permet treballar sense taulell.
    static String missatgeNoHiHaTaulell = "No hi ha cap taulell. Considereu les opcions NOU o CARREGA";

    //Aquesta llista representa totes les comandes disponibles
    static String[] llistaComandes = {"AJUDA","AFEGEIX","CARREGA","ELIMINA","GUARDA","LLISTA","NOU","MOSTRA","OBLIDA","SURT"}; 
    static String[] guardaOblida = {"GUARDA","OBLIDA"};
    static String[] nouCarrega = {"NOU","CARREGA"};

    //variables taulell
    static int files, columnes;
    static int[][] taulell;

    //variables vaixell
    static String coordenadaVaixell;

    static int filaInicial;
    static int filaFinal;
    static int columnaInicial;
    static int columnaFinal;
    static int longitudVaixell;

    static int nombreVaixellsTaulell = 0;

    //main
    public static void main(String[] args) throws IOException{

        System.out.println("Benvingut a Enfonsar la flota \nAra hi ets al Constructor de taulells");
       
        String comanda;

        // Amb aquest do()while{} el que demanem és l'entrada de la comanda per part de l'usuari fins que entri una comanda vàlida.
        do{
            
            System.out.println("Introdueix la comanda que vols executar ( escriu 'AJUDA' per veure les comandes ): ");

            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
            comanda = entrada.readLine();       // llegim una línia de text

            //si l'entrada és vàlida llavors és processarà amb la funció gestorComanda
            if(comandaEsValida(comanda, llistaComandes)){
                gestorComanda(comanda);
            }else{
                System.out.println("Comanda no vàlida. Torna a intentar-ho ( escriu 'AJUDA' per veure les comandes )");
            }

        }
        while (! comanda.toUpperCase().equals("SURT"));


        
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

        // si no s'ha trobat, s'executa aquesta part del codi, es torna el false ja que la comanda no existeix.
        System.out.println("Comanda desconeguda");
        return trobat;

        }


        public static void gestorComanda(String comanda){

        // Si l'entrada és vàlida la convertim en majúscules
        comanda = comanda.toUpperCase();

        switch(comanda){
            case "AJUDA":
                ajuda();
                break;

            case "AFEGEIX":
                afegeix();
                break;

            case "CARREGA":
                carrega();
                break;

            case "ELIMINA":
                elimina();
                break;

            case "GUARDA":
                guarda();
                break;

            case "LLISTA":
                ComandaLlista.mostraFitxersCarpetaTaulells();
                break;

            case "NOU":
                nou();
                break;

            case "MOSTRA":
                mostra();
                break;

            case "OBLIDA":
                oblida();
                break;
            
            case "SURT":
                surt();
                break;
            

            // en principi el default no s'executarà mai. Podriem tancar el programa si s'executa aquest codi.
            default: System.out.println("Comanda desconeguda, estem al final del switch");
        }
    }


/*
****************************************************************************
                                COMANDES VALIDES
****************************************************************************
*/



    public static void ajuda(){
        //el programa mostra un text d’ajuda amb les diferents comandes disponibles.

        System.out.println("Les comandes que pots utilizar són:");

        //Descripció comandes com a hashtable (diccionari), s'utiliza amb la comanda AJUDA
        Hashtable<String, String> descripcioComandes = new Hashtable<String, String>();
        descripcioComandes.put("AJUDA","Mostra les diferents comandes disponibles.");
        descripcioComandes.put("AFEGEIX","Afegeix un nou vaixell al taulell.");
        descripcioComandes.put("CARREGA","Carrega un taulell guardat prèviament.");
        descripcioComandes.put("ELIMINA","Elimina un vaixell del taulell.");
        descripcioComandes.put("GUARDA","Guarda el taulell en construcció.");
        descripcioComandes.put("LLISTA","Obté la llista dels taulells guardats.");
        descripcioComandes.put("NOU","Crea un nou taulell.");
        descripcioComandes.put("MOSTRA","Mostra el contingut del taulell en construcció.");
        descripcioComandes.put("OBLIDA","Ignora el taulell en construcció sense guardar cap canvi.");
        descripcioComandes.put("SURT","Finalitza l'aplicació.");

        // Obtinc totes les claus de descripcioComanes perquè vull recorrer el diccionari.
        Set<String> keysDescripcioComandes = descripcioComandes.keySet();

        //Recorrem les descripcions
        for(String key : keysDescripcioComandes){
            System.out.println( key + ": " + descripcioComandes.get(key));
        }
        System.out.println();
    }

    public static void afegeix(){
        //es vol afegir un nou vaixell al taulell.

        //  Si no hi ha un taulell en construcció (estat inicial), es mostra el missatge d’error No hi ha cap taulell. Considereu les opcions NOU o CARREGA.
        if(! taulellEnConstruccio){
            System.out.println(missatgeNoHiHaTaulell);
        }

        else{
            //  descripcio vaixell i comprovació
            //  Si sí hi ha un taulell, el programa demana la descripció del vaixell. Recorda que un vaixell es descriu amb una coordenada amb possible rang en una de les dimensions. Ex. (5:7, 1) correspon a un vaixell de longitud 3, col·locat de manera vertical a la columna 1, entre les files 5 i 7.
            //  Si el vaixell ha estat descrit correctament i, en col·locar-lo al taulell actual, el taulell es manté vàlid, llavors el programa l’afegeix al taulell.
            //  En cas que tot hagi anat bé, el programa ho indicarà amb el missatge Fet!
            
            System.out.println("Introdueix el vaixell: ");
            try{
                BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
                coordenadaVaixell = entrada.readLine();
    
                if(coordenadaValida(coordenadaVaixell)){
    
                    if(taulellCorrecteAmbNouVaixell(coordenadaVaixell)){ //TODO
                        afegeixVaixell(coordenadaVaixell);

                        // Incrementem el comptador de vaixells
                        nombreVaixellsTaulell++;
                        System.out.println("Fet!");
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

    public static boolean coordenadaValida(String coordenadaVaixell){

        if(! formatCorrecte(coordenadaVaixell)){
            return false;
        }

        obteCoordenadesVaixell(coordenadaVaixell);
        
        if(! nombresCorrectes(coordenadaVaixell)){
            return false;
        }

        return true;
    }

    public static boolean formatCorrecte(String coordenadaVaixell){
        // Comprobem la longitud. No pot ser 0 ni més gran que 12 per a un taulell 99x99 ( 90:99, 90) 
        if(coordenadaVaixell.length() < 1 || coordenadaVaixell.length() > 12){
            return false;
        }

        // Ha de contindre "," i "( )"
        if((! coordenadaVaixell.contains(",")) || (! coordenadaVaixell.contains("(")) || (! coordenadaVaixell.contains(")"))){
            return false;
        }

        // No pot contenir dos ":" (no es poden colocar els vaixells en diagonal) i exactament s'ha de tenir 1 coma
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

        if(quantitatDosPunts > 1 || quantitatComes != 1){
            return false;
        }

        return true;
    }

    public static boolean nombresCorrectes(String coordenadaVaixell){


        //Les coordenades han de ser-hi en el taulell
        if((filaInicial >= files) || (filaFinal >= files) || (columnaInicial >= columnes) || (columnaFinal >= columnes)){
            return false;
        }

        //Comprobem que l'ordre tingui sentit
        if(filaInicial > filaFinal || columnaInicial > columnaFinal){
            return false;
        }

        // Calculem les longituds
        int longitudEixY = filaFinal - filaInicial + 1;
        int longitudEixX = columnaFinal - columnaInicial + 1;

        // Comprobem les longituds
        // Mirem que cap longitud sigui 0 o negativa
        if(longitudEixX < 1 || longitudEixY < 1){
            return false;
        }

        // Com no permetem diagonals, només una de les longituds pot ser més gran que 1
        if(longitudEixX > 1 && longitudEixY > 1){
            return false;
        }

        //Obtenim la longitud del vaixell
        longitudVaixell = Math.max(longitudEixX, longitudEixY);
        
        return true;
    }

    public static void obteCoordenadesVaixell(String coordenadaVaixell ){
        //Primer treiem els parèntesis i possibles espais
        coordenadaVaixell = coordenadaVaixell.trim();
        coordenadaVaixell = coordenadaVaixell.replace("(","");
        coordenadaVaixell = coordenadaVaixell.replace(")","");

        // Obtenim les coordenades amb contains() i split()
        String[] coordenades = coordenadaVaixell.split(",");
        String coordenadesFila = coordenades[0];
        String coordenadesColumna = coordenades[1];

        if(coordenadesFila.contains(":")){
            String[] coordenadaFila = coordenadesFila.split(":");
             filaInicial = Integer.parseInt(coordenadaFila[0]);
             filaFinal = Integer.parseInt(coordenadaFila[1]);

        }else if(coordenadesColumna.contains(":")){
            String[] coordenadaColumna = coordenadesColumna.split(":");
             columnaInicial = Integer.parseInt(coordenadaColumna[0]);
             columnaFinal = Integer.parseInt(coordenadaColumna[1]);

        }else{
             filaInicial = Integer.parseInt(coordenadesFila);
             filaFinal = filaInicial;
             columnaInicial = Integer.parseInt(coordenadesColumna);
             columnaFinal = columnaInicial;
        }
    }

    public static void afegeixVaixell(String coordenadaVaixell){
        //TODO da problemas con los ":"
        for(int i = 0; i < files ;i++){
            for(int j = 0; j < columnes ;j++){
                if((i >= filaInicial && i <= filaFinal) && (j >= columnaInicial && j <= columnaFinal))
                    taulell[i][j] = longitudVaixell;
                }
            }
    }
    

    public static boolean taulellCorrecteAmbNouVaixell(String coordenadaVaixell){
        //TODO
        return true;
    }

    public static void carrega(){
        //es vol carregar un taulell guardat previament.
        //si ja hi ha un taulell apareix aquest missatge d'error. 
        if( taulellEnConstruccio){
            System.out.println("Ja hi ha un taulell. Considereu les opcions GUARDA o OBLIDA");
        }
        else{
            // Si no hi ha cap vaixell en construcció, demana el nom d’un vaixell.
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

            String nomTaulell = entrada.readLine();


            // TODO Si el nom del taulell no està a la llista de vaixells disponibles, es mostra l’error Taulell desconegut. Considereu l'opció LLISTA.
            
            // TODO El nom del taulell correspon a un fitxer que conté les dades d’un taulell prèviament guardades per aquest programa.
            // TODO Si el taulell és conegut, s’intenta carregar.
            carregaTaulell(nomTaulell);

            // TODO Si el contingut del taulell no és vàlid es mostra el missatge Contingut no vàlid. Això no hauria de passar amb els fitxers que hagi guardat aquesta aplicació, però ho comprovem per el fitxer ha estat alterat amb altres mitjans.

            // TODO Si el contingut és vàlid, el taulell és carregat i es mostra el missatge Fet!.
        }

    }
    public static void elimina(){
    //     //es vol eliminar un vaixell del taulell.

    //     Si no hi ha cap taulell avisem
         if(! taulellEnConstruccio){
            System.out.println(missatgeNoHiHaTaulell);
         }

         else{
//         descripció vaixell
            System.out.println("Introdueix el vaixell que vols eliminar: ");
            try{
                BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
                coordenadaVaixell = entrada.readLine();
    
                if(coordenadaValida(coordenadaVaixell)){

                    // Si el vaixell ha estat descrit correctament i es troba al taulell, llavors el programa l’elimina del taulell.
                    eliminaVaixell(coordenadaVaixell);

                    nombreVaixellsTaulell--;

                    System.out.println("Fet!");
                    
                }else{
                    // Si el vaixell està malament descrit, o no es troba al taulell es mostra el missatge d’error Vaixell no vàlid.
                    System.out.println("Vaixell no vàlid.");
                }
            }catch(IOException e){
                System.out.println("Error");
            }


          }


    }

    public static void eliminaVaixell(String coordenadesVaixell){

        for(int i = 0; i < files ;i++){
            for(int j = 0; j < columnes ;j++){
                if((i >= filaInicial && i <= filaFinal) && (j >= columnaInicial && j <= columnaFinal))
                    taulell[i][j] = 0;
                }
            }
    }
    public static void guarda(){
        // es vol guardar el taulell en construcció
        // Si no hi ha cap taulell avisem
        if(! taulellEnConstruccio){
            System.out.println(missatgeNoHiHaTaulell);
        }
        else if(nombreVaixellsTaulell == 0){
            System.out.println("El taulell en construcció no té cap vaixell. Considereu AFEGEIX.");
        }
        else{
            try{
                // Si hi ha un taulell en construcció, es demana un nom pel taulell.
                BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

                String nomTaulell = entrada.readLine();

                guardaTaulell(nomTaulell);

                // TODO Si el nom del taulell existeix, es sobreescriu el seu contingut amb el del taulell actual. Atenció: això és una simplificació. Normalment hem de fer que els nostres programes demanin confirmació abans de realitzar accions que puguin fer perdre informació.

                // TODO Si el nom del taulell no existeix, es crea un de nou i s’hi guarda el contingut del taulell actual.

                // En cas que tot hagi anat bé, el programa ho indicarà amb el missatge Fet!.
                System.out.println("Fet!");
            }catch(IOException ex){
                System.out.println("Error");
            }

        }

    }

    public static void guardaTaulell(String nomTaulell){
        //TODO me tengo que mirar como se manipulaban ficheros...
    }

    public static void mostra(){
        // és vol veure el contingut del taulell en construcció.
        // Aquesta opció només és vàlida quan hi ha un taulell en construcció. 
        // Altrament es mostra el missatge No hi ha cap taulell. 
        // Considereu les opcions NOU o CARREGA.

        if(! taulellEnConstruccio){
            System.out.println(missatgeNoHiHaTaulell);
        }
        else{
            // el programa mostrarà una representació del taulell. 
            // Malgrat el taulell pot arribar a ser molt gran per ser representat 
            // en una pantalla amb comoditat, en aquesta primera versió del programa
            // no s’exigeix gestionar paginació

            mostraTaulell();
         }
             
     }
    
    public static void oblida(){
        // es vol ignorar el taulell en construcció sense guardar cap canvi.
        // Aquesta opció només és vàlida quan hi ha un taulell en construcció. 
        // Altrament es mostra el missatge No hi ha cap taulell. 
        // Considereu les opcions NOU o CARREGA.

        if(! taulellEnConstruccio){
            System.out.println(missatgeNoHiHaTaulell);
        }
        else{
            // En cas que sí hi hagi, el taulell en construcció és oblidat 
            // i es deixa de tenir taulell de construcció. En aquest cas, 
            // el programa confirmarà amb l’habitual missatge Fet!.
            
            taulellEnConstruccio = false;
            System.out.println("Fet!");
        }
    }

    public static void surt(){

        if (! taulellEnConstruccio){
            System.out.println("Fet!");
            System.exit(0);
        }
        else {
            subMenuComandes(guardaOblida);
        }
    }

    public static void nou() {
        //TODO si hi ha un vaixell considerar la opció GUARDA o OBLIDA
        //TODO constantes locales pendiente de mirar si pueden aprovechar las del ConstructorTaulel

    
        try{
        //entrada estandar
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        
        if (! taulellEnConstruccio) {
        
            //demanem numero de columnes del taulell
            System.out.println("Introdueix número de files del taulell:");
            
            //pendiente control de flujo de entrada
            //suponemos que recibimos siempre digitos        
            do {
                //String files = entrada.readLine();
                files = Integer.parseInt(entrada.readLine());
            
            } while (! dimensionsValida(files));
                
            //demanem numero de files del taulell
            System.out.println("Introdueix número de columnes del taulell:");
            
            do {
                //String columnes = entrada.readLine();
                columnes = Integer.parseInt(entrada.readLine());
            
            } while (! dimensionsValida(columnes));

            // Creem el taulell amb tot aigua
            creaTaulell(files, columnes);

            taulellEnConstruccio = true;
            System.out.println("Fet!");

        //cas en el que existeix un taulell 
        } else {
            System.out.println("Ja hi ha un taulell. Considereu les opcions GUARDA o OBLIDA.");
            // subMenuComandes(guardaOblida); //Opció avançada amb submenús
            
        }     
    }
    
    catch(IOException e){
        System.out.println("error");
        System.exit(0);
    }
  }

 public static boolean dimensionsValida(int valor) {
    if ((valor < TAMANY_MINIM_FILES || valor > TAMANY_MAXIM_FILES) || (valor < TAMANY_MINIM_COLUMNES || valor > TAMANY_MAXIM_COLUMNES)) {
        System.out.println("Cal un enter entre 1 i 99");
        return false;
    
    }else{
        return true;
    }             
 }

 public static void creaTaulell(int files, int columnes){
    
    // Crearem el taulell que és una matriu de nombres naturals i l'omplirem inicialment a 0, que és el valor de l'aigua.
    taulell = new int[files][columnes];

    for(int i = 0; i < files ;i++){
        for(int j = 0; j < columnes ;j++){
            taulell[i][j] = 0;
        }
    }

 }

 public static void mostraTaulell(){
    
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
 }

     public static void subMenuComandes(String[] llistaComandesDisponibles){

            if(llistaComandesDisponibles[0] == "NOU"){
                System.out.println("Ja hi ha un taulell. Considereu les opcions NOU o CARREGA");
            }   
            else{
                System.out.println("Ja hi ha un taulell. Considereu les opcions GUARDA o OBLIDA");
            }

            String comanda;
            //String[] llistaComandes = {"GUARDA","OBLIDA"};

            // Demanem l'entrada de la comanda continuament fins que introdueixi GUARDA o OBLIDA
            do {
                Scanner entrada = new Scanner(System.in);                
                comanda = entrada.next();            
            } while (! ConstructorTaulell.comandaEsValida(comanda, llistaComandesDisponibles));
            
            // Convertim comanda a majúscules
            comanda = comanda.toUpperCase();

            switch(comanda){

            case "CARREGA":
                break;

            case "NOU":
                nou();
                break;
            
            case "GUARDA":
                guarda();
                break;

            case "OBLIDA":
                oblida();
                break;

            }
    }
}



