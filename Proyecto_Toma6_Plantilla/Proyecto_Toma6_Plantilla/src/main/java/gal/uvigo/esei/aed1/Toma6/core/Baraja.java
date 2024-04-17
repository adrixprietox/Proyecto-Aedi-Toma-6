
/*
* Representa la baraja del juego Toma 6, en total 104 cartas, enumeradas del 1 al 104 con el número de bueyes
* correspondiente en función del valor de la misma (revisar condiciones en el enunciado del juego). 
* Estructura: se utilizará un TAD adecuado
* Funcionalidad: barajar las cartas, devolver la carta situada encima del montón de cartas
 */
package gal.uvigo.esei.aed1.Toma6.core;

import java.util.Collections;
import java.util.List;
import java.util.Stack;



public class Baraja {

    private Stack<Carta> baraja;
    //constructor 
    public Baraja() {
        this.baraja = new Stack<>();
        int numeroCarta = 1;
        for (int i = 0; i < 104; i++) {
            int bueyes = calcularBueyes (numeroCarta);
            this.baraja.add(new Carta(numeroCarta, bueyes));;
            numeroCarta++;
        }
    }

    public Stack<Carta> getBaraja() {
        return baraja;
    }
    
    
    public boolean barajaVacia(){
        if(baraja.empty()){
            return true;
        }else{
            return false;
        }
        
    }
    public Carta retirarDeBaraja(){
       return baraja.pop();
    }
    
    
    private static int calcularBueyes (int numeroCarta){
        if (numeroCarta % 5 == 0 && numeroCarta % 11 == 0){
            return 7;
        } else if (numeroCarta % 5 == 0){
            return 2;
        } else if (numeroCarta % 10 == 0){
            return 3;
        } else if (numeroCarta % 11 == 0){
            return 5;
        } else {
            return 1;
        }
    }
    public static Stack<Carta> barajar(Stack <Carta> baraja){
        Collections.shuffle(baraja);
        return baraja;
    }
}
