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
        char resposta;
        Scanner teclat = new Scanner(System.in);
        do {
            System.out.println("BENVINGUTS AL CONNECTA 4. COMENÇEM!");

            int files = Util.llegirInt("Introdueix el num de files: ");
            int colus = Util.llegirInt("Introdueix el num de columnes: ");

            Tauler tauler = new Tauler(files, colus);
            Jugador[] jugadors = iniciarMinijocEleccio();
            iniciarJoc(tauler, jugadors[0], jugadors[1]);

            resposta = Util.demanaOpinio("Vols tornar a jugar? (S/N): ");
        } while (resposta == 'S');

        System.out.println("Fins aviat!");
    }

    /**
     * Es demana a l'usuari que triï una columna vàlida per fer un moviment al
     * tauler. Comprova si la columna escollida està disponible i repeteix la
     * pregunta fins que l'usuari proporcioni una columna vàlida.
     *
     * @param tauler L'objecte Tauler que representa el tauler de joc.
     * @return Retorna un int que representa la columna seleccionada (indexada a
     * 0).
     */
    private static int demanarColumnaValida(Tauler tauler) {
        int columna; // Declarem la variable 'columna' per guardar la selecció de l'usuari
        boolean movimentValid = false; // Inicialitzem 'movimentValid' a false

        do {
            // Demanem a l'usuari que triï una columna, restem 1 per ajustar l'índex (que comença a 0)
            columna = Util.llegirInt("Tria una columna:") - 1;
            // Comprovem si la columna escollida està disponible
            movimentValid = tauler.columnaDisponible(columna);
            // Si no és un moviment vàlid, informem l'usuari
            if (!movimentValid) {
                System.out.println("Columna plena o invàlida. Torna a tirar i tria una altra columna:");
            }
        } while (!movimentValid); // Repetim fins que s'introdueixi una columna vàlida

        return columna; // Retornem la columna vàlida seleccionada per l'usuari
    }

    /**
     * Iniciem el joc entre dos jugadors, gestionant els torns i comprovant si
     * hi ha un guanyador o si el joc acaba en empat.
     *
     * @param tauler L'objecte Tauler que representa el tauler de joc.
     * @param juga1 El primer jugador que participarà al joc.
     * @param juga2 El segon jugador que participarà al joc.
     */
    private static void iniciarJoc(Tauler tauler, Jugador juga1, Jugador juga2) {
        boolean jocAcabat = false; // Inicialitzem la variable per saber si el joc ha acabat
        boolean empat = false; // Inicialitzem la variable per saber si hi ha hagut un empat
        Jugador jugadorActual = juga1; // Comencem amb el jugador 1

        while (!jocAcabat && !empat) { // Continuem mentre el joc no hagi acabat i no hi hagi empat
            tauler.mostrarTauler(); // Mostrem el tauler actual
            System.out.println("Torn del jugador " + jugadorActual.getSimbol() + ". Tria una columna:");

            int columna = demanarColumnaValida(tauler); // Demanem una columna vàlida
            tauler.posarFitxa(jugadorActual.getSimbol(), columna); // Posem la fitxa del jugador actual

            // Comprovem si el jugador actual ha guanyat
            if (tauler.comprovarGuanyador(jugadorActual.getSimbol())) {
                tauler.mostrarTauler(); // Mostrem el tauler final
                System.out.println("¡Jugador " + jugadorActual.getSimbol() + " ha guanyat!"); // Anunciem el guanyador
                jocAcabat = true; // Marquem que el joc ha acabat
            } else if (tauler.estaPle()) { // Comprovem si el tauler està ple
                tauler.mostrarTauler(); // Mostrem el tauler final
                System.out.println("¡Empate!"); // Anunciem un empat
                empat = true; // Marquem que hi ha hagut un empat
            }

            // Canviem al següent jugador
            jugadorActual = (jugadorActual == juga1) ? juga2 : juga1;
        }
    }

    /**
     * Iniciem un minijoc per determinar quin jugador escollirà el símbol 'X' o
     * 'O'. Els jugadors han d'endevinar un número aleatori entre 1 i 10. El
     * primer jugador que encerti el número aleatori guanya i escull el seu
     * símbol.
     *
     * @return Un array de dos jugadors, on el primer jugador és el que ha
     * guanyat i escollit el símbol, i el segon jugador té el símbol restant (si
     * el guanyador escull X, el restant es O).
     */
    private static Jugador[] iniciarMinijocEleccio() {
        Random random = new Random(); //Utilitzem Random per generar números aleatoris
        int numeroAleatori = random.nextInt(10) + 1;  // Generem un número aleatori entre 1 i 10
        boolean encertat = false; // Inicialitzem 'encertat' a false
        Jugador[] jugadors = new Jugador[2];  // Declarem un array per guardar els dos jugadors

        System.out.println("Comença el minijoc per decidir qui tria X o O!");

        while (!encertat) { // Continuem fins que un jugador encerti
            // Jugador 1 fa el seu intent
            int intentJuga1 = Util.llegirInt("Jugador 1, introdueix un número entre 1 i 10: ");
            if (intentJuga1 == numeroAleatori) { // Comprovem si ha encertat
                encertat = true; // Marquem que hem encertat
                System.out.println("Jugador 1 ha encertat!"); // Informem que el jugador 1 ha encertat
                char eleccio = Util.demanaChar("Jugador 1, vols ser X o O?", "XO"); // Preguntem quin símbol vol
                jugadors[0] = new Jugador(eleccio); // Creem l'objecte Jugador 1 amb el símbol escollit
                jugadors[1] = new Jugador(eleccio == 'X' ? 'O' : 'X'); // Creem l'objecte Jugador 2 amb el símbol oposat
            } else {
                System.out.println("Incorrecte. Jugador 2, ara és el teu torn."); // Informem que el jugador 1 ha fallat
                // Jugador 2 fa el seu intent
                int intentJuga2 = Util.llegirInt("Jugador 2, introdueix un número entre 1 i 10: ");
                if (intentJuga2 == numeroAleatori) { // Comprovem si el jugador 2 ha encertat
                    encertat = true; // Marquem que hem encertat
                    System.out.println("Jugador 2 ha encertat!"); // Informem que el jugador 2 ha encertat
                    char eleccio = Util.demanaChar("Jugador 2, vols ser X o O?", "XO"); // Preguntem quin símbol vol
                    jugadors[1] = new Jugador(eleccio); // Creem l'objecte Jugador 2 amb el símbol escollit
                    jugadors[0] = new Jugador(eleccio == 'X' ? 'O' : 'X'); // Creem l'objecte Jugador 1 amb el símbol oposat
                } else {
                    System.out.println("Incorrecte. Tornem a començar."); // Informem que el jugador 2 també ha fallat
                }
            }
        }

        return jugadors; // Retornem els dos jugadors creats
    }

}
