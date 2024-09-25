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

    private void initTauler() {
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < colus; j++) {
                tauler[i][j] = ' '; 
            }
        }
    }
    public void mostrarTauler() {
        mostrarPartDalt();
        mostrarFilas();
      mostrarSeparadorInferior();
       mostrarNumerosColumna();
    }

    private void mostrarPartDalt() {
        System.out.println();
        for (int i = 0; i < colus; i++) {
            System.out.print("  " + (i + 1) + " ");
        }
        System.out.println();
    }
     private void mostrarFilas() {
        String separador = "-".repeat(colus * 4 + 1);
        for (int i = 0; i < files; i++) {
            System.out.println(separador);
            for (int j = 0; j < colus; j++) {
                System.out.print("| " + tauler[i][j] + " ");
            }
            System.out.println("|");
        }
    }
  private void mostrarNumerosColumna() {
        for (int i = 0; i < colus; i++) {
            System.out.print("  " + (i + 1) + " ");
        }
        System.out.println("\n");
    }
    private void mostrarSeparadorInferior() {
        String separador = "-".repeat(colus * 4 + 1);
        System.out.println(separador);
    }
     public boolean columnaDisponible(int columna) {
        if (columna < 0 || columna >= colus) {
            return false;
        }
        return tauler[0][columna] == ' ';
    }

    public void posarFitxa(char simbol, int columna) {
        for (int i = files - 1; i >= 0; i--) {
            if (tauler[i][columna] == ' ') {
                tauler[i][columna] = simbol;
                break;
            }
        }
    }
      private boolean comprovarHoritzontal(char simbol) {
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < colus - 3; j++) {  
                if (tauler[i][j] == simbol && tauler[i][j + 1] == simbol &&
                    tauler[i][j + 2] == simbol && tauler[i][j + 3] == simbol) {
                    return true;
                }
            }
        }
        return false;
    }
  private boolean comprovarVertical(char simbol) {
        for (int j = 0; j < colus; j++) {
            for (int i = 0; i < files - 3; i++) {  
                if (tauler[i][j] == simbol && tauler[i + 1][j] == simbol &&
                    tauler[i + 2][j] == simbol && tauler[i + 3][j] == simbol) {
                    return true;
                }
            }
        }
        return false;
    }
}
