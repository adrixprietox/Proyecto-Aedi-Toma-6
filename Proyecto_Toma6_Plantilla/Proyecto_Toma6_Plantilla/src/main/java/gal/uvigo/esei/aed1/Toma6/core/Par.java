/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gal.uvigo.esei.aed1.Toma6.core;

/**
 *
 * @author lili
 */
public class Par<Jugador,Carta> {
    private final Jugador primero;
    private final Carta segundo;

    public Par(Jugador primero, Carta segundo) {
        this.primero = primero;
        this.segundo = segundo;
    }

    public Jugador getPrimero() {
        return primero;
    }

    public Carta getSegundo() {
        return segundo;
    }
    
}
