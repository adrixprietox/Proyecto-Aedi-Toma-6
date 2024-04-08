/**
 * Representa el juego Toma 6, con sus reglas (definidas en el documento Primera entrega). 
 * Se recomienda una implementación modular.
 */

package gal.uvigo.esei.aed1.Toma6.core;


import gal.uvigo.esei.aed1.Toma6.iu.IU;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Juego {
    private final IU iu;
    private List<Carta> baraja;
    private Collection<Jugador> jugadores;
    
    public Juego(IU iu) {
        this.iu = iu;
    }

    public void repartirCartas(List<Carta> baraja, List<Jugador> jugadores) {
        // Verificar si hay suficientes cartas en la baraja
        if (baraja.size() < jugadores.size() * 10) {
            iu.mostrarMensaje("No hay suficientes cartas en la baraja para repartir.");
            return;
        }

        // Barajar la baraja de cartas
        Collections.shuffle(baraja);

        // Repartir 10 cartas a cada jugador
        for (int i = 0; i < 10; i++) {
            for (Jugador jugador : jugadores) {
                if (!baraja.isEmpty()) {
                    Carta carta = baraja.remove(0); // Remover la carta del tope de la baraja
                    jugador.getMano().add(carta);   // Agregar la carta a la mano del jugador
                }
            }
        }

        // Ordenar las cartas en la mano de cada jugador de menor a mayor
        for (Jugador jugador : jugadores) {
            Collections.sort(jugador.getMano(), Comparator.comparingInt(Carta::getNumero));
        }
        // Luego de repartir las cartas, mostrar los datos de los jugadores
        iu.mostrarCartasJugadores(jugadores);
    }

    public void jugar() {
        // Mostrar cartas de cada jugador
        for (Jugador jugador : jugadores) {
            mostrarCartasJugadores(jugadores);
        }
    }
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

