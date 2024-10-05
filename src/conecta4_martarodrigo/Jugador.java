/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conecta4_martarodrigo;

/**
 * Creem la classe Jugador

 */
public class Jugador {
    private final char simbol;
    private final String nom;

    public Jugador(char simbol, String nom) {
        this.simbol = simbol;
        this.nom = nom;
    }

    public char getSimbol() {
        return simbol;
    }

    public String getNom() {
        return nom;
    }
}
