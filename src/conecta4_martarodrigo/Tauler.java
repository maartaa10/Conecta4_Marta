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
}
