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
    private List<Jugador> jugadores;
    private Mesa mesa;
     

    public Juego(IU iu, Baraja baraja, List<Jugador> jugadores) {
        this.iu = iu;
        this.baraja = baraja;
        this.jugadores = jugadores;
        this.mesa = new Mesa(baraja);
        
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

    public Carta encontrarCartaMasPequena(List<Jugador> jugadores) {
        // Inicializar la carta más pequeña como la primera carta del primer jugador
        Carta cartaMasPequena = jugadores.get(0).getCartaSeleccionada();

        // Iterar sobre las cartas seleccionadas de todos los jugadores para encontrar la más pequeña
        for (Jugador jugador : jugadores) {
            Carta cartaActual = jugador.getCartaSeleccionada();
            if (cartaActual != null && cartaActual.getNumero() < cartaMasPequena.getNumero()) {
                cartaMasPequena = cartaActual;
            }
        }

        return cartaMasPequena;
    }

    public void mostrarEstadoDeLaMesa() {
        // Mostrar el estado actual de la mesa
        iu.mostrarMensaje("Estado actual de la mesa:");
        mesa.mostrarContenidoMesa();
    }

    private List<Carta> obtenerCartasEnOrdenCreciente(List<Jugador> jugadores) { //ORDENA LAS CARTAS SELECCIONADAS EN UN ARRAY
        List<Carta> cartasEnOrden = new ArrayList<>();

        // Iterar sobre todas las cartas seleccionadas por los jugadores y agregarlas a la lista
        for (Jugador jugador : jugadores) {
            Carta cartaSeleccionada = jugador.getCartaSeleccionada();
            cartasEnOrden.add(cartaSeleccionada);
        }

        // Ordenar la lista de cartas en función del número de carta (orden creciente)
        Collections.sort(cartasEnOrden, Comparator.comparingInt(Carta::getNumero));

        return cartasEnOrden;
    }

    public void colocarCartasSeleccionadas(List<Carta> cartasSeleccionadas, List<Carta>[] cuatroSobrantes) { //COLOCA LAS CARTAS SELECCIONADAS
        // Itera sobre las cartas seleccionadas de los jugadores
        for (Carta carta : cartasSeleccionadas) {
            // Encuentra la pila de descarte adecuada para colocar la carta seleccionada
            int[] posicion = encontrarPosicionParaCarta(carta, cuatroSobrantes);

            // Si se encuentra una posición válida, coloca la carta en esa pila
            if (posicion[0] != -1 && posicion[1] != -1) {
                cuatroSobrantes[posicion[0]].add(posicion[1], carta);
            } else {
                // Manejar el caso en el que no se puede colocar la carta
                // Esto podría incluir devolver la carta al jugador o al mazo, dependiendo de las reglas del juego.
            }
        }
    }

    private int[] encontrarPosicionParaCarta(Carta carta, List<Carta>[] cuatroSobrantes) { //ENCUENTRA LA POSICION PARA INSERTAR LA CARTA
        int[] posicion = {-1, -1}; // Inicializa la posición como no encontrada
        int diferenciaMinima = Integer.MAX_VALUE; // Inicializa la diferencia mínima como el máximo valor entero

        // Itera sobre todas las filas de cartas
        for (int i = 0; i < cuatroSobrantes.length; i++) {
            List<Carta> fila = cuatroSobrantes[i];

            // Itera sobre todas las cartas en la fila
            for (int j = 0; j <= fila.size(); j++) {
                // Calcula la diferencia entre la carta actual y la carta seleccionada
                int diferencia = (j < fila.size()) ? carta.getNumero() - fila.get(j).getNumero() : Integer.MAX_VALUE;

                // Si la diferencia es menor que la diferencia mínima actual,
                // actualiza la posición y la diferencia mínima
                if (diferencia < diferenciaMinima) {
                    posicion[0] = i; // Fila
                    posicion[1] = j; // Posición en la fila
                    diferenciaMinima = diferencia;
                }
            }
        }

        return posicion;
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
//        List<Carta> cartasEnOrden = obtenerCartasEnOrdenCreciente(jugadores);
//        colocarCartasSeleccionadas(cartasEnOrden,cuatroSobrantes);

    }

}
