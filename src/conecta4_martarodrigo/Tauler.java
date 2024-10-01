/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conecta4_martarodrigo;

/**
 *
 * @author martarodrigo
 */
public class Tauler {

    private final char[][] tauler;
    private final int files;
    private final int colus;

    public Tauler(int files, int colus) {
        this.files = files;
        this.colus = colus;
        tauler = new char[files][colus];
        initTauler();
    }

    /**
     * Inicialitzem el tauler omplint totes les caselles amb espais buits.
     */
    private void initTauler() {
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < colus; j++) {
                tauler[i][j] = ' ';
            }
        }
    }

    /**
     * Mostrem tot el tauler de joc, incloent la part superior (capçalera), les
     * files, la part d'abaix i els números de columna.
     */
    public void mostrarTauler() {
        mostrarPartDalt();
        mostrarFilas();
        mostrarSeparadorInferior();
        mostrarNumerosColumna();
    }

    /**
     * Mostrem la part superior del tauler, amb els números de les columnes.
     */
    private void mostrarPartDalt() {

        // Imprimeix una línia en blanc per a separar visualment la part superior del tauler
        System.out.println();

        // Aquest bucle recorre totes les columnes i imprimeix el número de cada columna
        for (int i = 0; i < colus; i++) {
            // El número de columna que es mostra és i + 1, ja que les columnes comencen en 1 (no en 0)
            System.out.print("  " + (i + 1) + " ");
        }

        // Després de mostrar tots els números de les columnes, es fa un salt de línia
        System.out.println();
    }

    /**
     * Mostra les files del tauler de joc amb les separacions horitzontals entre
     * elles.
     *
     *
     */
    private void mostrarFilas() {

        // Inicialitzem la variable 'separador', que s'utilitzarà per a dibuixar les línies de separació entre files.
        String separador = "";

        // Amb aquest bucle es fa el separador horitzontal utilitzant tants guions ("-") com cal per a cobrir la mida del tauler.
        // Cada columna necessita 4 caràcters: 3 per als espais reservats per cada fitxa ("| _ ") i un de més per la línia final.
        // El +1 és per incloure el guió addicional que tanca la fila.
        for (int i = 0; i < colus * 4 + 1; i++) {
            separador += "-";  // Afegeix un guió a cada iteració del bucle
        }

        // Aquest segon bucle for recorre cada fila del tauler.
        for (int i = 0; i < files; i++) {

            // Primer, imprimeix el separador que delimita una fila de l'altra.
            System.out.println(separador);

            // Ara, dins de la fila, recorre totes les columnes.
            for (int j = 0; j < colus; j++) {
                // Mostrem el contingut de la cel·la actual del tauler, que pot ser un espai en blanc (' ') o una fitxa ('X' o 'O').
                // La sortida és " | " seguida del contingut de la cel·la (tauler[i][j]) i després un espai.
                System.out.print("| " + tauler[i][j] + " ");
            }

            // Un cop s'ha imprès tota la fila (totes les columnes d'aquesta fila), imprimim la barra final de la fila.
            System.out.println("|");
        }
    }

    /**
     * Mostra els números de cada columna a la part inferior del tauler. Els
     * números indiquen quina columna poden triar els jugadors per deixar-hi la
     * seva fitxa.
     */
    private void mostrarNumerosColumna() {
        // Aquest bucle recorre el nombre de columnes del tauler (colus) i imprimeix els números corresponents a cadascuna.
        // Comença a 1 per a que l'usuari pugui veure els números a partir de l'1 (no comença des de 0).
        for (int i = 0; i < colus; i++) {
            System.out.print("  " + (i + 1) + " ");  // Mostra el número de columna, amb dos espais abans i un després per formatejar
        }
        // Un cop ha acabat de mostrar tots els números de les columnes, imprimeix una nova línia per a separar del que vingui després.
        System.out.println("\n");
    }

    /**
     * Mostra una línia de separació horitzontal a la part inferior del tauler.
     *
     */
    private void mostrarSeparadorInferior() {

        // Inicialitzem una cadena buida per a construir el separador de guions.
        String separador = "";

        // Aquest bucle construeix el separador horitzontal. El nombre total de guions és proporcional al nombre de columnes.
        // Cada columna ocupa 4 caràcters (per representar l'espai de cada fitxa i les delimitacions).
        // El +1 és per tancar correctament la fila amb un guió addicional.
        for (int i = 0; i < colus * 4 + 1; i++) {
            separador += "-";  // Afegeix un guió a cada iteració del bucle.
        }

        // Un cop el separador s'ha construït completament, mostrem per pantalla.
        System.out.println(separador);
    }

    /**
     * Comprova si una columna està disponible per col·locar una fitxa. Una
     * columna està disponible si no està fora dels límits del tauler o si la
     * seva fila superior està buida (indicant que hi ha espai).
     *
     * @param columna La columna que es vol comprovar (comença en 0).
     * @return true si la columna és vàlida i té espai per col·locar una fitxa;
     * false en cas contrari (si està fora de límits o plena).
     */
    public boolean columnaDisponible(int columna) {
        if (columna < 0 || columna >= colus) {
            return false;
        }
        return tauler[0][columna] == ' ';
    }

    /**
     * Col·loca una fitxa del jugador en la columna indicada. La fitxa es
     * col·locarà a la posició més baixa disponible d'aquesta columna (com si
     * caigués).
     *
     * @param simbol El símbol del jugador que es vol col·locar (per exemple,
     * 'X' o 'O').
     * @param columna La columna on es vol col·locar la fitxa (comença en 0).
     */
    public void posarFitxa(char simbol, int columna) {
        boolean fitxaPosada = false; // Controla si ja hem posat la fitxa
        for (int i = files - 1; i >= 0 && !fitxaPosada; i--) {
            if (tauler[i][columna] == ' ') {
                tauler[i][columna] = simbol; // Col·loca la fitxa del jugador
                fitxaPosada = true; // Marca que ja hem posat la fitxa
            }
        }
    }

    /**
     * Comprovem si hi ha quatre fitxes consecutives del mateix jugador en una
     * línia horitzontal al tauler.
     *
     * @param simbol El símbol del jugador que es vol comprovar (per exemple,
     * 'X' o 'O').
     * @return true si es troben quatre fitxes consecutives horitzontals del
     * mateix jugador; false en cas contrari.
     */
    private boolean comprovarHoritzontal(char simbol) {
        for (int i = 0; i < files; i++) { // Recórrer cada fila
            for (int j = 0; j < colus - 3; j++) { // Recórrer cada columna amb marge suficient per 4 fitxes (i evitar index out of bounds)
                // Comprovar si hi ha 4 fitxes consecutives del mateix jugador
                if (tauler[i][j] == simbol && tauler[i][j + 1] == simbol
                        && tauler[i][j + 2] == simbol && tauler[i][j + 3] == simbol) {
                    return true; // S'ha trobat una línia horitzontal guanyadora
                }
            }
        }
        return false; // No s'ha trobat cap línia horitzontal guanyadora
    }

    /**
     * Comprovem si hi ha quatre fitxes consecutives del mateix jugador en una
     * columna del tauler.
     *
     *
     * @param simbol El símbol del jugador que es vol comprovar (per exemple,
     * 'X' o 'O').
     * @return true si es troben quatre fitxes consecutives verticals del mateix
     * jugador; false en cas contrari.
     */
    private boolean comprovarVertical(char simbol) {
        for (int j = 0; j < colus; j++) { // Recórrer cada columna
            for (int i = 0; i < files - 3; i++) { // Recórrer cada fila amb marge suficient per 4 fitxes (i evitar index out of bounds)
                // Comprovar si hi ha 4 fitxes consecutives del mateix jugador
                if (tauler[i][j] == simbol && tauler[i + 1][j] == simbol
                        && tauler[i + 2][j] == simbol && tauler[i + 3][j] == simbol) {
                    return true; // S'ha trobat una línia vertical guanyadora
                }
            }
        }
        return false; // No s'ha trobat cap línia vertical guanyadora
    }

    /**
     * Comprovem si hi ha quatre fitxes consecutives del mateix jugador en una
     * línia diagonal al tauler.
     *
     * @param simbol El símbol del jugador que es vol comprovar (per exemple,
     * 'X' o 'O').
     * @return true si es troben quatre fitxes consecutives diagonals del mateix
     * jugador; false en cas contrari.
     */
    private boolean comprovarDiagonals(char simbol) {
    // Recórrer cada fila amb marge suficient per 4 fitxes (i evitar index out of bounds)
    for (int i = 0; i < files - 3; i++) {
        // Recórrer cada columna amb marge suficient per 4 fitxes (i evitar index out of bounds)
        for (int j = 0; j < colus - 3; j++) {
            // Comprovem si els quatre símbols en la diagonal principal són iguals
            if (tauler[i][j] == simbol && tauler[i + 1][j + 1] == simbol
                    && tauler[i + 2][j + 2] == simbol && tauler[i + 3][j + 3] == simbol) {
                // Retorna true si troba la seqüència (S'ha trobat una línia diagonal guanyadora)
                return true;
            }
        }
    }

 // Recórrer cada fila amb marge suficient per 4 fitxes (i evitar index out of bounds)
    for (int i = 0; i < files - 3; i++) {
         //Fem el bucle sobre les columnes del tauler, començant des de la quarta columna
        for (int j = 3; j < colus; j++) {
            // Comprovem si els quatre símbols en la segona diagonal són iguals
            if (tauler[i][j] == simbol && tauler[i + 1][j - 1] == simbol
                    && tauler[i + 2][j - 2] == simbol && tauler[i + 3][j - 3] == simbol) {
                // Retorna true si troba la seqüència (S'ha trobat una línia diagonal guanyadora)
                return true;
            }
        }
    }
// No s'ha trobat cap línia diagonal guanyadora
    return false;
}

public boolean comprovarGuanyador(char simbol) {
    // Comprova si el jugador ha guanyat horitzontalment, verticalment o diagonalment
    return comprovarHoritzontal(simbol) || comprovarVertical(simbol) || comprovarDiagonals(simbol);
}


    /**
 * Comprova si el tauler està ple, és a dir, si no queden espais buits.
 *
 * @return Retorna true si el tauler està ple, false si hi ha espais buits.
 */
public boolean estaPle() {
    // Itera sobre totes les columnes del tauler
    for (int j = 0; j < colus; j++) {
        // Comprova si la primera fila de la columna està buida
        if (tauler[0][j] == ' ') {
            // Retorna false si troba un espai buit
            return false;
        }
    }
    // Retorna true si no hi ha espais buits
    return true;
}
}
