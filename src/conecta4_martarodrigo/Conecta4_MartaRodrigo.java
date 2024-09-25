/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package conecta4_martarodrigo;

import java.util.*;

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
          Jugador[] jugadors = iniciarMinijocEleccio();
iniciarJoc(tauler, jugadors[0], jugadors[1]);
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
       private static Jugador[] iniciarMinijocEleccio() {
    Random random = new Random();
    int numeroAleatori = random.nextInt(10) + 1; 
    boolean encertat = false;
    Jugador[] jugadors = new Jugador[2]; 

    System.out.println("Comença el minijoc per decidir qui tria X o O!");

    while (!encertat) {
        int intentJuga1 = Util.llegirInt("Jugador 1, introdueix un número entre 1 i 10: ");
        if (intentJuga1 == numeroAleatori) {
            encertat = true;
            System.out.println("Jugador 1 ha encertat!");
            char eleccio = Util.demanaChar("Jugador 1, vols ser X o O?", "XO");
            jugadors[0] = new Jugador(eleccio);
            jugadors[1] = new Jugador(eleccio == 'X' ? 'O' : 'X');
        } else {
            System.out.println("Incorrecte. Jugador 2, ara és el teu torn.");
          
            int intentJuga2 = Util.llegirInt("Jugador 2, introdueix un número entre 1 i 10: ");
            if (intentJuga2 == numeroAleatori) {
                encertat = true;
                System.out.println("Jugador 2 ha encertat!");
                char eleccio = Util.demanaChar("Jugador 2, vols ser X o O?", "XO");
                jugadors[1] = new Jugador(eleccio);
                jugadors[0] = new Jugador(eleccio == 'X' ? 'O' : 'X');
            } else {
                System.out.println("Incorrecte. Tornem a començar.");
            }
        }
    }

    return jugadors; 
}

}
