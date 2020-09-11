// implementació del joc
public class Flota{
    public static void main(String[] args){
        /*
        En arrencar el programa, carrega el taulell contingut a un fitxer, a partir del nom del taulell segons s’hagi definit a ConstructorTaulell.
        El nom del taulell és proporcionat com a argument de línia de comandes.
        De no trobar-se el taulell amb aquest nom, finalitza execució amb un missatge d’error.
        El programa valida que el contingut del fitxer sigui correcte. De no ser-ho, finalitza execució amb un missatge d’error.
        */
        ConstructorTaulell.carrega();
        /*
        D’estar tot correcte, el programa mostra el mapa del taulell on, inicialment totes les cel·les apareixen amb el valor ocult sota un símbol d’interrogació.
        Com a ajuda, al voltant dels valors del taulell, apareixeran els noms de la fila/la columna corresponent.
        */
        TaulellJoc.mostraTaulell();
    }
}