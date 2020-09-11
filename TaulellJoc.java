public class TaulellJoc {
    public static void mostraTaulell(int[][] taulell){

        int files = ConstructorTaulell.obteFilesTaulell(taulell);
        int columnes = ConstructorTaulell.obteColumnesTaulell(taulell);

        // Crearem el taulell que és una matriu de strings a partir del taulell numeric
        String[][] taulellString = new String[taulell.length][columnes];
    
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
    }   
}
