public class Revisar {
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
        int[] coordenadaFila = {-1};
        int[] coordenadaColumna = {-1};

        // Comprovem els casos de dimensiones majors a 1
        if(coordenadesFilaString.contains(":")){
            String[] coordenadaFilaString = coordenadesFilaString.split(":");
            coordenadaFila = Utility.StringArrayToIntArray(coordenadaFilaString);
            coordenadaColumna[0] = Integer.parseInt(coordenadesColumnaString);

        }else if(coordenadesColumnaString.contains(":")){
            String[] coordenadaColumnaString = coordenadesColumnaString.split(":");
            coordenadaColumna = Utility.StringArrayToIntArray(coordenadaColumnaString);
            coordenadaFila[0] = Integer.parseInt(coordenadesFilaString) ;

        }else{
            coordenadaFila[0] = Integer.parseInt(coordenadesFilaString) ;
            coordenadaColumna[0] = Integer.parseInt(coordenadesColumnaString);
        }
    
        // Tornem la matriu de sortida amb les coordenades
        int[][] sortida = {coordenadaFila, coordenadaColumna};
        return sortida;
    }

    public static boolean coordenadesDintreTaulell(int[][] coordenadesVaixell,int files, int columnes ){
        boolean dintre = true;
        for(int i = 0; i < 2 ;i++){
            for(int j = 0; j < 2 ;j++){
                if(coordenadesVaixell[i].length > 1){
                    if(! (TaulellJoc.nombreFilesCorrecte(coordenadesVaixell[i][j], files) && TaulellJoc.nombreColumnesCorrecte(coordenadesVaixell[i][j], columnes))){
                        dintre = false;
                    }
                }else{
                    if(! (TaulellJoc.nombreFilesCorrecte(coordenadesVaixell[i][0], files) && TaulellJoc.nombreColumnesCorrecte(coordenadesVaixell[i][0], columnes))){
                        dintre = false;
                    }
                }
            }
        }
}
