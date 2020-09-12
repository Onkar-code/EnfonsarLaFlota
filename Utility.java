public class Utility {
    public static int[] StringArrayToIntArray(String[] arrayString){
        int size = arrayString.length;
        int[] arrayInt = new int[size];

        for(int i = 0;i < size;i++){
            arrayInt[i] = Integer.parseInt(arrayString[i]);
        }
        
        return arrayInt;
    }

    public static void mostraMatriu(int[][] matriu){
        // Es considera que el paràmetre sempre és vàlid i amb dimensió > 0.
        for(int i = 0; i < matriu.length ;i++){
            for(int j = 0; j < matriu[0].length ;j++){
                System.out.print(matriu[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void mostraMatriu(String[][] matriu){
        // Es considera que el paràmetre sempre és vàlid i amb dimensió > 0.
        for(int i = 0; i < matriu.length ;i++){
            for(int j = 0; j < matriu[0].length ;j++){
                System.out.print(matriu[i][j] + " ");
            }
            System.out.println();
        }
    }
}
