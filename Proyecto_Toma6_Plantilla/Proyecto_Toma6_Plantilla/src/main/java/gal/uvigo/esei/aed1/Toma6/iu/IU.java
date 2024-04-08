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
    public Collection<String> pedirNombresJugadores() {
        Collection<String> nombresJugadores = new ArrayList<>();

        int numJugadores = leeNum("¿Cuántos jugadores/as van a jugar? (entre 2 y 10): ");
        for (int i = 0; i < numJugadores; i++) {
            nombresJugadores.add(leeString("Nombre del jugador " + (i + 1) + ": "));
        }

        return nombresJugadores;
    }

    /**
     * Muestra por pantalla los datos de un jugador
     *
     * @param jugador Jugador para el cual se mostrarán los datos por pantalla
     */
    private void mostrarJugador(Jugador jugador) {
        // Aquí puedes implementar la lógica para mostrar los datos del jugador
    }

    /**
     * Muestra por pantalla los datos de una coleccion de jugadores
     *
     * @param jugadores Jugadores cuyos datos se mostrarán por pantalla
     */
    public void mostrarCartasJugadores(Collection<Jugador> jugadores) {
        for (Jugador jugador : jugadores) {
            System.out.println("Jugador: " + jugador.getNombre());
            System.out.println("Cartas en la mano:");
            for (Carta carta : jugador.getMano()) {
                System.out.println("Número de carta: " + carta.getNumero() + ", Bueyes: " + carta.getBueyes());
            }
            System.out.println();
        }
    }

}
