/**
 * Representa el juego Toma 6, con sus reglas (definidas en el documento Primera entrega).
 * Se recomienda una implementación modular.
 */
package gal.uvigo.esei.aed1.Toma6.core;

import gal.uvigo.esei.aed1.Toma6.iu.IU;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import gal.uvigo.esei.aed1.Toma6.core.*;

public class Juego {

    private final IU iu;
    private Baraja baraja;
    private Collection<Jugador> jugadores;
    private Mesa mesa;

    public Juego(IU iu, Baraja baraja, Collection<Jugador> jugadores) {
        this.iu = iu;
        this.baraja = baraja;
        this.jugadores = jugadores;
    }

    public Juego(IU iu) {
        this.iu = iu;
        this.baraja = new Baraja();
        this.mesa = new Mesa(baraja);
    }

    public void repartirCartas() {

        // Repartir 10 cartas a cada jugador
        baraja.barajar(); // llamo al metodo barajar antes de repartir
        for (int i = 0; i < 10; i++) {
            for (Jugador jugador : jugadores) {
                if (!baraja.barajaVacia()) {
                    Carta carta = baraja.retirarDeBaraja(); // Quitar la carta del tope de la baraja
                    jugador.recibirCarta(carta);   // Agregar la carta a la mano del jugador
                }
            }
        }

    }

    public List<Jugador> crearJugadores(List<String> nombresJugadores) {
        List<Jugador> jugadores = new ArrayList<>();
        for (String nombreJugador : nombresJugadores) {
            Jugador jugador = new Jugador(nombreJugador);
            jugadores.add(jugador);
        }
        return jugadores;

    }

    private void seleccionarCarta(Jugador jugador) {
        iu.mostrarMensaje("\nTurno de " + jugador.getNombre() + " para seleccionar una carta.");
        iu.mostrarMensaje("Tu mano actual:");
        iu.mostrarMensaje(jugador.mostrarCartasJugador());

        int seleccion;
        do {
            seleccion = iu.pedirSeleccionCarta();
            if (seleccion < 1 || seleccion > jugador.getMano().size()) {
                iu.mostrarMensaje("Selección inválida. Por favor, elige un número válido.");
            }
        } while (seleccion < 1 || seleccion > jugador.getMano().size());

        Carta cartaSeleccionada = jugador.getMano().get(seleccion - 1); // Obtener la carta seleccionada
        jugador.setCartaSeleccionada(cartaSeleccionada);
        jugador.retirarCarta(cartaSeleccionada); // Retirar la carta de la mano del jugador
        iu.mostrarMensaje(jugador.getNombre() + " ha seleccionado la carta: " + cartaSeleccionada);
    }

    public void jugar() {
        iu.mostrarMensaje("Comenzando juego de toma 6"
                + "\nSe recomienda jugar en pantalla completa\n");
        int numJugadores = iu.pedirNumeroJugadores();

        jugadores = crearJugadores(iu.pedirNombresJugadores(numJugadores));

        iu.mostrarMensaje("\nPreparando la partida y "
                + "repartiendo cartas, por favor espere");

        repartirCartas();
        iu.mostrarJugadores(jugadores);
        Carta carta = baraja.retirarDeBaraja(); // Obtener una carta de la baraja
        mesa.colocarCuatroSobrantesEnMesa(carta); // Colocar la carta en la mesa
        mesa.mostrarContenidoMesa();
        for (Jugador jugador : jugadores) {
            seleccionarCarta(jugador);
        }

        iu.mostrarMensaje("Todas las cartas han sido seleccionadas correctamente.");
        for (Jugador jugador : jugadores) {
            iu.mostrarMensaje(jugador.getNombre() + " ha seleccionado la carta: " + jugador.getCartaSeleccionada());
        }

    }

}
