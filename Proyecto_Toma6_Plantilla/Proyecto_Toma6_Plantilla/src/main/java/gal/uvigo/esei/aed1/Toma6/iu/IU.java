/**
 * Representa la interfaz del juego Toma 6, en este proyecto va a ser una entrada/salida en modo texto
 * Se recomienda una implementación modular.
 */
package gal.uvigo.esei.aed1.Toma6.iu;

import gal.uvigo.esei.aed1.Toma6.core.Jugador;
import gal.uvigo.esei.aed1.Toma6.core.Juego;
import gal.uvigo.esei.aed1.Toma6.core.Baraja;
import gal.uvigo.esei.aed1.Toma6.core.Carta;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class IU {

    private final Scanner teclado;

    public IU() {
        teclado = new Scanner(System.in);
    }

    /**
     * Lee un número de teclado
     *
     * @param msg El mensaje a visualizar.
     * @return El numero como entero
     */
    public int leeNum(String msg) {
        boolean repite;
        int toret = 0;

        do {
            repite = false;
            System.out.print(msg);
            try {
                toret = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException exc) {
                repite = true;
            }
        } while (repite);

        return toret;
    }

    /**
     * Lee un texto de teclado
     *
     * @param msg El mensaje a utilizar
     * @return El texto como String
     */
    public String leeString(String msg) {
        String toret;
        System.out.print(msg);
        toret = teclado.nextLine();
        return toret;
    }

    /**
     * Muestra un mensaje por pantalla
     *
     * @param msg El mensaje a mostrar
     */
    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    /**
     * Solicita los nombres de los jugadores por teclado y los almacena en una
     * estructura de datos
     *
     * @return Los datos de los jugadores almacenados en la estructura de datos
     * correspondiente
     */
    public List<Jugador> pedirNombresJugadores() {
        List<Jugador> nombresJugadores = new ArrayList<>();

        int numJugadores;
        do {
            numJugadores = leeNum("¿Cuántos jugadores/as van a jugar? (entre 2 y 10): ");
        } while (numJugadores < 2 || numJugadores > 10);

        for (int i = 0; i < numJugadores; i++) {
            String nombre = leeString("Nombre del jugador/a " + (i + 1) + ": ");
            Jugador nuevo = new Jugador(nombre);
            nombresJugadores.add(nuevo);
        }
        return nombresJugadores;

    }

    /**
     * Muestra por pantalla los datos de un jugador
     *
     * @param jugador Jugador para el cual se mostrarán los datos por pantalla
     */
    public void mostrarJugador(Jugador jugador) {
        mostrarMensaje(jugador.toString() + "\n");

    }

    /**
     * Muestra por pantalla los datos de una coleccion de jugadores
     *
     * @param jugadores Jugadores cuyos datos se mostrarán por pantalla
     */
    

    /**
     *
     * Muestra una lista de jugadores por pantalla
     *
     *
     *
     * @param listaJugadores Los jugadores a mostrar
     *
     */
    public void mostrarJugadores(Collection<Jugador> listaJugadores) {
        for (Jugador jugador : listaJugadores) {
            mostrarJugador(jugador);
        }
    }

    }
