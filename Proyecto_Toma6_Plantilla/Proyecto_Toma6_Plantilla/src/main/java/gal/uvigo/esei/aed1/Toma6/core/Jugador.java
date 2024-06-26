/*
 * Representa a un jugador, identificado por el nombre y sus cartas de la mano
 * Estructura mano: se utilizará un TAD adecuado
 * Funcionalidad: Añadir carta a la mano (añadir la carta de foma que queden 
 * ordenadas de menor a mayor por su número), convertir a String el objeto Jugador (toString)
 */

package gal.uvigo.esei.aed1.Toma6.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Jugador {
    private String nombre;
    private List<Carta> mano;
    public Jugador (String nombre){
        this.nombre = nombre;
        this.mano = new ArrayList<>();


    }

    public String getNombre() {
        return nombre;
    }

    public List<Carta> getMano() {
        return mano;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jugador :  ").append(this.nombre).append("\n");
        for (Carta i : this.getMano()) {
            sb.append(i.toString()).append("\t");
        }
        return sb.toString();
    }
    public String mostrarCartasJugador() {
        StringBuilder sb = new StringBuilder();
        int size = mano.size();
        for (int i = 0; i < size; i++) {
            sb.append(i + 1).append(") ");
            sb.append(mano.get(i).toString());
            sb.append("   ");
        }
        sb.append("\n");
        return sb.toString();
    }

    
}