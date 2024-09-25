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
      private static void iniciarJoc(Tauler tauler, Jugador juga1, Jugador juga2) {
        boolean jocAcabat = false;
        boolean empat = false;
        Jugador jugadorActual = juga1;

        while (!jocAcabat && !empat) {
            tauler.mostrarTauler();
            System.out.println("Torn del jugador " + jugadorActual.getSimbol() + ". Tria una columna:");

          
            int columna = demanarColumnaValida(tauler);

          
            tauler.posarFitxa(jugadorActual.getSimbol(), columna);

           
            if (tauler.comprovarGuanyador(jugadorActual.getSimbol())) {
                tauler.mostrarTauler();
                System.out.println("¡Jugador " + jugadorActual.getSimbol() + " ha guanyat!");
                jocAcabat = true;
            } else if (tauler.estaPle()) {
                tauler.mostrarTauler();
                System.out.println("¡Empate!");
                empat = true;
            }

            jugadorActual = (jugadorActual == juga1) ? juga2 : juga1;
        }
    }
}
