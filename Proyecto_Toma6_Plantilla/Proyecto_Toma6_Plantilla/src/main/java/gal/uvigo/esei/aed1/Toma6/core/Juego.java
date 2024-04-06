/**
 * Representa el juego Toma 6, con sus reglas (definidas en el documento Primera entrega). 
 * Se recomienda una implementación modular.
 */

package gal.uvigo.esei.aed1.Toma6.core;


import gal.uvigo.esei.aed1.Toma6.iu.IU;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Juego {
    
    private final IU iu;

    
    public Juego(IU iu){
        this.iu = iu;

    }
        
    public void repartirCartas(List<Carta> baraja, List<Jugador> jugadores) {
        // Barajar la baraja de cartas
        Collections.shuffle(baraja);
        
        // Repartir 10 cartas a cada jugador
        for (int i = 0; i < 10; i++) {
            for (Jugador jugador : jugadores) {
                Carta carta = baraja.remove(0); // Remover la carta del tope de la baraja
                jugador.getMano().add(carta);   // Agregar la carta a la mano del jugador
            }
        }
        
        // Ordenar las cartas en la mano de cada jugador de menor a mayor
        for (Jugador jugador : jugadores) {
            Collections.sort(jugador.getMano(), new Comparator<Carta>() {
                @Override
                public int compare(Carta carta1, Carta carta2) {
                    return Integer.compare(carta1.getNumero(), carta2.getNumero());
                }
            });
        }
    }
    public void jugar(){
        

    }
    
    
    
}
