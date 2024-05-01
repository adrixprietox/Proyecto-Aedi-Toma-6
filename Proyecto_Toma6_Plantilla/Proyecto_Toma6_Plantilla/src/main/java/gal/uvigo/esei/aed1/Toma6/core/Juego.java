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
    private List<Par<Jugador, Carta>> selecciones;

    public Juego(IU iu, Baraja baraja, List<Jugador> jugadores, List<Par<Jugador, Carta>> selecciones) {
        this.iu = iu;
        this.baraja = baraja;
        this.jugadores = jugadores;
        this.mesa = new Mesa();
        this.selecciones = new ArrayList<>();

    }

    public Juego(IU iu) {
        this.iu = iu;
        this.baraja = new Baraja();
        this.mesa = new Mesa();
        this.selecciones = new ArrayList<>();
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

    public static List<Carta> crearCuatroCartas(Baraja baraja) {
        List<Carta> cuatroCartas = new ArrayList<>();
        // Agregar las cuatro cartas a la lista
        for (int i = 0; i < 4; i++) {
            Carta carta = baraja.retirarDeBaraja();
            cuatroCartas.add(carta);
        }
        return cuatroCartas;
    }

    private void seleccionarCarta(Jugador jugador) {
        iu.mostrarMensaje("\nTurno de " + jugador.getNombre() + " para seleccionar una carta.");
        iu.mostrarMensaje("Tu mano actual:");
        iu.mostrarMensaje(jugador.toString());

        int seleccion;
        do {
            seleccion = iu.pedirSeleccionCarta();
            if (seleccion < 1 || seleccion > jugador.getMano().size()) {
                iu.mostrarMensaje("Selección inválida. Por favor, elige un número válido.");
            }
        } while (seleccion < 1 || seleccion > jugador.getMano().size());

        Carta cartaSeleccionada = jugador.getMano().get(seleccion - 1); // Obtener la carta seleccionada
        selecciones.add(new Par<>(jugador, cartaSeleccionada));
        jugador.setCartaSeleccionada(cartaSeleccionada);
        jugador.retirarCarta(cartaSeleccionada); // Retirar la carta de la mano del jugador
        iu.mostrarMensaje(jugador.getNombre() + " ha seleccionado la carta: " + cartaSeleccionada);
    }

    public void mostrarSeleccionesJugadores() {
        for (Par<Jugador, Carta> relacion : selecciones) {
            Jugador jugador = relacion.getPrimero();
            Carta cartaSeleccionada = relacion.getSegundo();
            System.out.println(jugador.getNombre() + " ha seleccionado la carta: " + cartaSeleccionada);
        }
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

    private int[] encontrarPosicionParaCarta(Carta carta, List<Carta>[] cuatroSobrantes) {
        int[] posicion = {-1, -1}; // Inicializa la posición como no encontrada
        int diferenciaMinima = Integer.MAX_VALUE; // Inicializa la diferencia mínima como el máximo valor entero

        // Iterar sobre todas las filas de montones
        for (int i = 0; i < cuatroSobrantes.length; i++) {
            List<Carta> fila = cuatroSobrantes[i];

            // Obtener la última carta de la fila
            Carta ultimaCarta = obtenerUltimaCarta(fila);

            // Verificar si la fila no está vacía y la última carta es menor que la carta a insertar
            if (ultimaCarta != null && ultimaCarta.getNumero() < carta.getNumero()) {
                // Calcular la diferencia entre la última carta y la carta a insertar
                int diferencia = carta.getNumero() - ultimaCarta.getNumero();

                // Verificar si la diferencia es menor que la diferencia mínima actual
                if (diferencia < diferenciaMinima) {
                    posicion[0] = i; // Fila
                    posicion[1] = fila.size(); // Posición en la fila
                    diferenciaMinima = diferencia; // Actualizar la diferencia mínima
                }
            }
        }

        return posicion;
    }

    public Carta obtenerUltimaCarta(List<Carta> lista) {
        if (!lista.isEmpty()) {
            return lista.get(lista.size() - 1); // Obtener la última carta de la lista
        } else {
            return null; // lista vacia es null
        }
    }

    public void colocarCartasSeleccionadas(List<Carta> cartasSeleccionadas, List<Carta>[] cuatroSobrantes) { //COLOCA LAS CARTAS SELECCIONADAS
        // Obtener el número de la carta seleccionada
        int numeroCartaSeleccionada = cartasSeleccionadas.get(0).getNumero(); // Suponiendo que solo se selecciona una carta a la vez

        // Iterar sobre todas las últimas cartas de las listas
        boolean todasMayores = true;
        for (List<Carta> fila : cuatroSobrantes) {
            // Obtener la última carta de la fila
            Carta ultimaCartaFila = obtenerUltimaCarta(fila);

            // Verificar si la fila no está vacía y la última carta es mayor o igual que la carta seleccionada
            if (ultimaCartaFila != null && ultimaCartaFila.getNumero() < numeroCartaSeleccionada) {
                todasMayores = false;
                break; // Si se encuentra una carta menor, se sale del bucle
            }
        }

        // Si todas las últimas cartas son mayores que la carta seleccionada, mostrar el mensaje correspondiente y devolver la carta a la baraja
        if (todasMayores) {
            iu.mostrarMensaje("El jugador no puede colocar la carta en la mesa porque todas las cartas son mayores. La carta se devuelve a la baraja.");
            baraja.getBaraja().push(cartasSeleccionadas.get(0)); // Suponiendo que solo se selecciona una carta a la vez
            return; // Salir del método
        }

        // Recorre las cartas seleccionadas de los jugadores
        for (Carta carta : cartasSeleccionadas) {
            // Encuentra la pila de descarte adecuada para colocar la carta seleccionada
            int[] posicion = encontrarPosicionParaCarta(carta, cuatroSobrantes);

            // Si se encuentra una posición válida, coloca la carta en esa pila
            if (posicion[0] != -1 && posicion[1] != -1) {
                // Verificar si la fila está llena
                if (cuatroSobrantes[posicion[0]].size() >= 5) {
                    iu.mostrarMensaje("La fila " + (posicion[0] + 1) + " está llena. La carta se devuelve a la baraja.");
                    // Devolver la carta a la baraja
                    baraja.getBaraja().push(carta);
                } else {
                    // La fila no está llena, colocar la carta en esa pila
                    cuatroSobrantes[posicion[0]].add(posicion[1], carta);
                }
            } else {
                // La fila está llena o no se puede colocar la carta, devolver la carta a la baraja
                iu.mostrarMensaje("El jugador no puede colocar la carta en la mesa. La carta se devuelve a la baraja.");
                baraja.getBaraja().push(carta);
            }
        }
    }

    private boolean todosSinCartas() {
        // recorre los jugadores
        for (Jugador jugador : jugadores) {
            if (!jugador.getMano().isEmpty()) {
                return false; // Al menos un jugador tiene cartas en la mano
            }
        }
        return true; // Todos los jugadores están sin cartas en la mano
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
        for(int i =0; i<4;i++){
            Carta carta = baraja.retirarDeBaraja();
            mesa.colocarCuatroSobrantesEnMesa(carta);
        }
         // Colocar la carta en la mesa
        mesa.mostrarContenidoMesa();

        while (!todosSinCartas()) {
            // Recorre los jugadores
            for (Jugador jugador : jugadores) {
                seleccionarCarta(jugador);
            }

            // Mostrar las cartas seleccionadas por cada jugador
            iu.mostrarMensaje("Todas las cartas han sido seleccionadas correctamente.");
            mostrarSeleccionesJugadores();
            // Ordenar las cartas seleccionadas en orden creciente
            List<Carta> cartasEnOrden = obtenerCartasEnOrdenCreciente(jugadores);

            // Colocar las cartas seleccionadas en la mesa
            colocarCartasSeleccionadas(cartasEnOrden, mesa.getCuatroSobrantes());

            // Mostrar mesa después de colocar las cartas
            mesa.mostrarContenidoMesa();
        }

        // Mensaje de fin de partida
        iu.mostrarMensaje("La partida ha finalizado.");
    }

}
