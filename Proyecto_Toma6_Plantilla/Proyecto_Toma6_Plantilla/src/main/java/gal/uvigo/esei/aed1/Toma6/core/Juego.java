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

    
    public Juego(IU iu,Baraja baraja, Collection<Jugador> jugadores) {
        this.iu = iu;
        this.baraja = baraja;
        this.jugadores = jugadores;
    }
    
    public Juego(IU iu) {
        this.iu = iu;
        this.baraja = new Baraja() ;
    }

    public void repartirCartas() {

        // Repartir 10 cartas a cada jugador
        for (int i = 0; i < 10; i++) {
            for (Jugador jugador : jugadores) {
                if (!baraja.barajaVacia()) {
                    Carta carta = baraja.retirarDeBaraja(); // Quitar la carta del tope de la baraja
                    jugador.recibirCarta(carta);   // Agregar la carta a la mano del jugador
                }
            }
        }

    }
    public List<Jugador> crearJugadores(List<String> nombresJugadores){
        List <Jugador> jugadores = new ArrayList<>();
        for(String nombreJugador : nombresJugadores ){
            Jugador jugador = new Jugador(nombreJugador);
            jugadores.add(jugador);
        }
        return jugadores;
        
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
        
     
        boolean continuar = true;
     

           
        iu.mostrarMesa(mesa);

          
        
    }
    
    
 }

