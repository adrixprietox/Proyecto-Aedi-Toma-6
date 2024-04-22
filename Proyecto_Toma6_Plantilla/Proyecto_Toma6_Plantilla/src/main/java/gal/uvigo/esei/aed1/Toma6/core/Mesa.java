/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gal.uvigo.esei.aed1.Toma6.core;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author adrian
 */
public class Mesa {

    private List<Carta>[] cuatroSobrantes; // Array de listas
    private Baraja baraja;

    public Mesa(Baraja baraja) {
        this.baraja = baraja;
        cuatroSobrantes = new List[4]; // un array de 4 elementos(en este caso,cada elemento es una lista)
        for (int i = 0; i < 4; i++) {
            cuatroSobrantes[i] = new LinkedList<>(); // inicializamos las cuatro listas vacias 
        }
    }

    public List<Carta>[] getCuatroSobrantes() {
        return cuatroSobrantes;
    }
    

    public void colocarCuatroSobrantesEnMesa(Carta carta) {
        // Iterar sobre cada lista en cuatroSobrantes
        for (List<Carta> lista : cuatroSobrantes) {
            Carta cartaDiferente = baraja.retirarDeBaraja(); // Obtener una carta diferente de la baraja
            lista.add(0, cartaDiferente); // Agregar la carta al principio de la lista
        }
    }

    public void mostrarContenidoMesa() {
        for (int i = 0; i < cuatroSobrantes.length; i++) {
            System.out.println("Lista " + (i + 1) + ":");
            List<Carta> lista = cuatroSobrantes[i];
            for (Carta carta : lista) {
                System.out.println(carta);
            }
            System.out.println();
        }
    }
}
