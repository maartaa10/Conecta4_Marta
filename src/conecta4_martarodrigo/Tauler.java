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
    System.out.println(); // Imprimeix una línia buida per separar la part superior del tauler de les dades anteriors.
    
    // Recorre cada columna del tauler, des de 0 fins a (colus - 1).
    for (int i = 0; i < colus; i++) {
        // Modificat per centrar els números
        // Imprimeix el número de la columna (i + 1) amb un espai per centrar-lo.
        // El %2d ens diu que el número ocuparà 2 espais, ajudant a l'alineació.
        System.out.printf("%2d  ", (i + 1)); // Alineació centrada
    }
    
    System.out.println(); // Imprimeix una línia nova per separar la part superior del tauler del contingut de les files.
}

    /**
     * Mostra les files del tauler amb les fitxes col·locades. Cada fila es
     * separa amb línies horitzontals.
     */
    private void mostrarFilas() {
        String separador = "";

        // Construïm el separador
        for (int i = 0; i < colus * 4 + 1; i++) {
            separador += "-";
        }

        for (int i = 0; i < files; i++) {
            System.out.println(separador);
            for (int j = 0; j < colus; j++) {
                // utilitzem printf per "formatear" la sortida
                System.out.printf("| %c ", tauler[i][j]);
            }
            System.out.println("|");
        }
    }

    /**
     * Mostra els números de les columnes a la part inferior del tauler.
     */
    private void mostrarNumerosColumna() {
        for (int i = 0; i < colus; i++) {
            // utilitzem printf per centrar els números
            System.out.printf("%2d  ", (i + 1)); // Espais per centrar el número
        }
        System.out.println("\n");
    }

    /**
     * Mostra la línia horitzontal inferior del tauler. Aquesta línia separa el
     * tauler de les accions del jugador.
     */
    private void mostrarSeparadorInferior() {
        String separador = "";
        for (int i = 0; i < colus * 4 + 1; i++) {
            separador += "-";
        }
        System.out.println(separador);
    }

    /**
     * Comprova si una columna esta disponible per col·locar una fitxa.
     *
     * @param columna Índex de la columna a comprovar.
     * @return True si la columna està disponible, false en cas contrari.
     */
    public boolean columnaDisponible(int columna) {
        if (columna < 0 || columna >= colus) {
            return false;
        }
        return tauler[0][columna] == ' ';
    }

    /**
 * Col·loca una fitxa al tauler en una columna específica.
 *
 * @param simbol Simbol que representa la fitxa (ex. 'X' o 'O').
 * @param columna Índex de la columna on col·locar la fitxa.
 */
public void posarFitxa(char simbol, int columna) {
    boolean fitxaPosada = false; // Boolean que indica si la fitxa ha estat col·locada o no.
    
    // Recorre les files del tauler començant per la fila inferior (files - 1) fins a la superior (0).
    for (int i = files - 1; i >= 0 && !fitxaPosada; i--) {
        // Comprova si la casella actual està buida (conté un espai).
        if (tauler[i][columna] == ' ') {
            // Si la casella està buida, col·loca la fitxa (simbol) a la posició corresponent.
            tauler[i][columna] = simbol; 
            fitxaPosada = true; // Marca que la fitxa s'ha col·locat.
        }
    }
}


   /**
 * Comprova si hi ha una combinació guanyadora horitzontal.
 *
 * @param simbol Simbol de la fitxa a comprovar.
 * @return TRue si s'ha trobat una combinació guanyadora, false en cas
 * contrari.
 */
private boolean comprovarHoritzontal(char simbol) {
    // Recorre totes les files del tauler, on 'files' és el nombre total de files
    for (int i = 0; i < files; i++) {
        // Recorre les columnes del tauler, aturant-se 3 posicions abans del final
        for (int j = 0; j < colus - 3; j++) {
            // Comprova si hi ha 4 fitxes consecutives horitzontalment
            if (tauler[i][j] == simbol && tauler[i][j + 1] == simbol
                    && tauler[i][j + 2] == simbol && tauler[i][j + 3] == simbol) {
                return true; //es troba una combinació guanyadora
            }
        }
    }
    return false; // no es troba cap combinació
}


    /**
 * Comprova si hi ha una combinació guanyadora vertical.
 *
 * @param simbol Simbol de la fitxa a comprovar.
 * @return True si s'ha trobat una combinació guanyadora, False en cas
 * contrari.
 */
private boolean comprovarVertical(char simbol) {
    // Recorre totes les columnes del tauler
    for (int j = 0; j < colus; j++) {
        // Recorre les files del tauler, aturant-se 3 posicions abans del final
        for (int i = 0; i < files - 3; i++) {
            // Comprova si hi ha 4 fitxes consecutives verticalment
            if (tauler[i][j] == simbol && tauler[i + 1][j] == simbol
                    && tauler[i + 2][j] == simbol && tauler[i + 3][j] == simbol) {
                return true; // Si es troba una combinació guanyadora, retorna cert
            }
        }
    }
    return false; // Si no es troba cap combinació, retorna fals
}

    /**
 * Comprova si hi ha una combinació guanyadora en diagonals.
 *
 * @param simbol Simbol de la fitxa a comprovar.
 * @return Cert si s'ha trobat una combinació guanyadora, fals en cas
 * contrari.
 */
private boolean comprovarDiagonals(char simbol) {
    // Recorre les files del tauler, aturant-se 3 posicions abans del final
    for (int i = 0; i < files - 3; i++) {
        // Recorre les columnes del tauler, aturant-se 3 posicions abans del final
        for (int j = 0; j < colus - 3; j++) {
            // Comprova si hi ha 4 fitxes consecutives en diagonal (de dalt a baix, esquerra a dreta)
            if (tauler[i][j] == simbol && tauler[i + 1][j + 1] == simbol
                    && tauler[i + 2][j + 2] == simbol && tauler[i + 3][j + 3] == simbol) {
                return true; // Si es troba una combinació guanyadora, retorna cert
            }
        }
    }

    // Recorre les files del tauler, aturant-se 3 posicions abans del final
    for (int i = 0; i < files - 3; i++) {
        // Recorre les columnes del tauler, començant des de la columna 3
        for (int j = 3; j < colus; j++) {
            // Comprova si hi ha 4 fitxes consecutives en diagonal (de dalt a baix, dreta a esquerra)
            if (tauler[i][j] == simbol && tauler[i + 1][j - 1] == simbol
                    && tauler[i + 2][j - 2] == simbol && tauler[i + 3][j - 3] == simbol) {
                return true; // Si es troba una combinació guanyadora, retorna cert
            }
        }
    }
    return false; // Si no es troba cap combinació, retorna fals
}

    /**
     * Comprova si hi ha un guanyador al tauler.
     *
     * @param simbol Simbol de la fitxa a comprovar.
     * @return Cert si s'ha trobat un guanyador, fals en cas contrari.
     */
    public boolean comprovarGuanyador(char simbol) {
        return comprovarHoritzontal(simbol) || comprovarVertical(simbol) || comprovarDiagonals(simbol);
    }

    /**
     * Comprova si el tauler està ple.
     *
     * @return Cert si el tauler està ple, fals en cas contrari.
     */
    public boolean estaPle() {
        for (int j = 0; j < colus; j++) {
            if (tauler[0][j] == ' ') {
                return false;
            }
        }
        return true;
    }

    /**
     * Obté el nombre de columnes del tauler.
     *
     * @return Nombre de columnes.
     */
    public int getColus() {
        return this.colus;
    }
}
