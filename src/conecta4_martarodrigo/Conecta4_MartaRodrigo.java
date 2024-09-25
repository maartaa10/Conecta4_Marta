/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package conecta4_martarodrigo;

/**
 *
 * @author martarodrigo
 */
public class Conecta4_MartaRodrigo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int files = Util.llegirInt("Introdueix el num de files: ");
        int colus = Util.llegirInt("Introdueix el num de columnes: ");
  
        Tauler tauler = new Tauler(files, colus);

    }
     private static int demanarColumnaValida(Tauler tauler) {
        int columna;
        boolean movimentValid = false;
        do {
            columna = Util.llegirInt("Tria una columna:") - 1;  
            movimentValid = tauler.columnaDisponible(columna);
            if (!movimentValid) {
                System.out.println("Columna plena o invalida. Torna a tirar i tria una altra columna:");
            }
        } while (!movimentValid);

        return columna;
    }
}
