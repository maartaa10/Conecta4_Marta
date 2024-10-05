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
     private Random random;

    public Bot(char simbol, String nom) {
        super(simbol, nom);
        this.random = new Random();
    }

    /**
     * El bot selecciona una columna de manera aleatoria, asegurándose de que sea válida.
     *
     * @param tauler El objeto Tauler que representa el tablero de juego.
     * @return Retorna la columna seleccionada por el bot.
     */
    public int seleccionarColumna(Tauler tauler) {
        int columna;
        do {
            columna = random.nextInt(tauler.getColus());  // Selecciona una columna aleatoria
        } while (!tauler.columnaDisponible(columna));  // Repite hasta que la columna sea válida
        return columna;
    }
}

