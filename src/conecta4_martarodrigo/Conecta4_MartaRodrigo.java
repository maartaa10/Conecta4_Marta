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
 * Inicia el joc entre dos jugadors, gestionant els torns i comprovant si
 * hi ha un guanyador o si el joc acaba en empat.
 *
 * @param tauler L'objecte Tauler que representa el tauler de joc.
 * @param guanya El jugador que ha guanyat el minijoc i començarà primer.
 * @param perd El segon jugador que participarà al joc.
 */
private static void iniciarJoc(Tauler tauler, Jugador guanya, Jugador perd) {
    boolean jocAcabat = false; // Boolean que indica si el joc ha acabat
    boolean empat = false; // Boolean que indica si hi ha un empat
    Jugador jugadorActual = guanya; // Inicialitzem el jugador actual com a guanyador del minijoc

    while (!jocAcabat && !empat) { // Continuem el joc mentre no hi hagi un guanyador ni un empat
        tauler.mostrarTauler(); // Mostrem l'estat actual del tauler de joc
        System.out.println("Torn de " + jugadorActual.getNom() + " (" + jugadorActual.getSimbol() + ")."); // Informem de qui és el torn

        int columna; // Variable per emmagatzemar la columna seleccionada

        if (jugadorActual instanceof Bot) { // Comprovem si el jugador actual és un Bot
            // Si és el torn del bot, escull una columna automàticament
            columna = ((Bot) jugadorActual).seleccionarColumna(tauler); // El bot selecciona una columna vàlida
            System.out.println(jugadorActual.getNom() + " ha seleccionat la columna " + (columna + 1)); // Informem de la columna seleccionada pel bot
        } else {
            // Si és un jugador humà, demanem que seleccioni una columna
            columna = demanarColumnaValida(tauler); // Demanem al jugador que introdueixi una columna vàlida
        }

        tauler.posarFitxa(jugadorActual.getSimbol(), columna); // El jugador actual posa la seva fitxa a la columna seleccionada

        if (tauler.comprovarGuanyador(jugadorActual.getSimbol())) { // Comprovem si el jugador actual ha guanyat
            tauler.mostrarTauler(); // Mostrem l'estat final del tauler
            System.out.println("¡" + jugadorActual.getNom() + " (" + jugadorActual.getSimbol() + ") ha guanyat!"); // Informem del guanyador
            jocAcabat = true; // Marquem que el joc ha acabat
        } else if (tauler.estaPle()) { // Comprovem si el tauler està ple
            tauler.mostrarTauler(); // Mostrem l'estat final del tauler
            System.out.println("¡Empat!"); // Informem que hi ha hagut un empat
            empat = true; // Marquem que el joc ha acabat en empat
        }

        // Canviem al següent jugador
        jugadorActual = (jugadorActual == guanya) ? perd : guanya; // Alternem entre els jugadors
    }
}


  /**
 * Minijoc per determinar quin jugador escollirà el símbol 'X' o
 * 'O'. Els jugadors han d'endevinar un número aleatori entre 1 i 10. El
 * primer jugador que encerti el número aleatori guanya i escull el seu
 * símbol i tira primer.
 *
 * En cas de jugar contra un bot, el primer jugador escollirà el seu
 * símbol i el bot rebrà el símbol restant. Si els jugadors decideixen jugar
 * entre ells, el minijoc consistirà en adivinar un número entre 1 i 10. 
 * El guanyador escollirà el seu símbol, mentre que el segon jugador tindra 
 * el símbol restant.
 *
 * @return Un array de dos jugadors, on el primer jugador és el que ha
 * guanyat i escollit el símbol, i el segon jugador té el símbol restant 
 * (si el guanyador escull 'X', el restant és 'O').
 */
private static Jugador[] iniciarMinijocEleccio() {
    Random random = new Random(); // Utilitzem Random per generar números aleatoris
    Jugador[] jugadors = new Jugador[2]; // Cream un array per guardar els dos jugadors

    String nomJugador1 = Util.llegirString("Jugador 1, introdueix el teu nom: "); // Llegim el nom del jugador 1

    String resposta;
    do {
        // Preguntem si vol jugar contra el Bot o contra un altre jugador
        resposta = Util.llegirString("Vols jugar contra el Bot o contra un altre jugador? (Bot/Jugador o B/J): ");

        // Convertim la resposta a minúscules per simplificar la comparació
        resposta = resposta.toLowerCase();

    } while (!(resposta.equals("bot") || resposta.equals("b") || resposta.equals("jugador") || resposta.equals("j")));

    if (resposta.equals("bot") || resposta.equals("b")) {
        // Si el jugador escull jugar contra el bot
        String nomBot = "Bot"; // Nom del bot
        char eleccio = Util.demanaChar(nomJugador1 + ", vols ser X o O?", "XO"); // Preguntem al jugador 1 quin símbol vol
        jugadors[0] = new Jugador(eleccio, nomJugador1);  // Cream l'objecte Jugador 1 amb el símbol escollit
        jugadors[1] = new Bot(eleccio == 'X' ? 'O' : 'X', nomBot);  // Al bot li toca la fitxa restant
    } else {
        // Si el jugador escull jugar contra un altre jugador, comencem el minijoc d'endevinar un número entre 1-10
        String nomJugador2 = Util.llegirString("Jugador 2, introdueix el teu nom: "); // Llegim el nom del jugador 2

        System.out.println("Minijoc: Intenteu endevinar un número entre 1 i 10. Qui encerti primer, tria la seva fitxa."); 
        int numeroAleatori = random.nextInt(10) + 1;  // Elegim un número aleatori entre 1 i 10
        boolean encertat = false; // Inicialitzem 'encertat' a false
        Jugador guanya = null; // Variable per emmagatzemar el guanyador

        while (!encertat) { // Continuem fins que un jugador encerti el número
            // Jugador 1 intenta endevinar
            int intentJuga1 = Util.llegirInt(nomJugador1 + ", introdueix un número entre 1 i 10: "); // Demanem al jugador 1 que endevini un número
            if (intentJuga1 == numeroAleatori) { // Comprovem si el jugador 1 ha encertat
                System.out.println("¡" + nomJugador1 + " ha encertat!"); // Informem que el jugador 1 ha encertat
                char eleccio = Util.demanaChar(nomJugador1 + ", vols ser X o O?", "XO"); // Preguntem quin símbol vol
                jugadors[0] = new Jugador(eleccio, nomJugador1); // Creem l'objecte Jugador 1 amb el símbol escollit
                jugadors[1] = new Jugador(eleccio == 'X' ? 'O' : 'X', nomJugador2); // Creem l'objecte Jugador 2 amb el símbol oposat
                encertat = true; // Marquem que s'ha encertat
                guanya = jugadors[0]; // Guardem el guanyador
            } else {
                System.out.println("No, segueix intentant."); // Missatge d'error si no encerta
            }

            if (!encertat) { // Només preguntarem al jugador 2 si el jugador 1 no ha encertat
                // Jugador 2 intenta endevinar
                int intentJuga2 = Util.llegirInt(nomJugador2 + ", introdueix un número entre 1 i 10: "); // Demanem al jugador 2 que endevini un número
                if (intentJuga2 == numeroAleatori) { // Comprovem si el jugador 2 ha encertat
                    System.out.println("¡" + nomJugador2 + " ha encertat!"); // Informem que el jugador 2 ha encertat
                    char eleccio = Util.demanaChar(nomJugador2 + ", vols ser X o O?", "XO"); // Preguntem quin símbol vol
                    jugadors[1] = new Jugador(eleccio, nomJugador2); // Creem l'objecte Jugador 2 amb el símbol escollit
                    jugadors[0] = new Jugador(eleccio == 'X' ? 'O' : 'X', nomJugador1); // Creem l'objecte Jugador 1 amb el símbol oposat
                    encertat = true; // Marquem que s'ha encertat
                    guanya = jugadors[1]; // GUardem el guanyador
                } else {
                    System.out.println("No, segueix intentant."); // Missatge d'error si no encerta
                }
            }
        }

        // Al final del minijoc, el guanyador ha de començar primer
        return new Jugador[]{guanya, jugadors[0] == guanya ? jugadors[1] : jugadors[0]}; // Retornem el guanyador i el perdedor
    }

    return jugadors; // Retornem els dos jugadors creats
}

}