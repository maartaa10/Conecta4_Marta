/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conecta4_martarodrigo;
import java.util.*;
/**
 *
 * @author martarodrigo
 */
public class Bot extends Jugador{
     private final Random random;

    public Bot(char simbol, String nom) {
        super(simbol, nom);
        this.random = new Random();
    }

    /**
     * El bot selecciona una columna de manera aleatoria.
     *
     * @param tauler El objecte Tauler que representa el tauler de joc.
     * @return Retorna la columna seleccionada pel bot.
     */
    public int seleccionarColumna(Tauler tauler) {
        int columna;
        do {
            columna = random.nextInt(tauler.getColus());  // Selecciona una columna aleatoria
        } while (!tauler.columnaDisponible(columna));  // HO repeteix fins que la columna sigui valida
        return columna;
    }
}

