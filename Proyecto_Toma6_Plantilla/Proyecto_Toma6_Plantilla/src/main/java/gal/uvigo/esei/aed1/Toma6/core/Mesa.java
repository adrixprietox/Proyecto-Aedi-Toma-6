/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gal.uvigo.esei.aed1.Toma6.core;

/**
 *
 * @author adrian
 */
public class Mesa {
    
    private Carta[][] cuatroSobrantes; // Array de cartas bidimensional para representar las filas de cartas sobrantes
    private final int numFilas= 4; // Número de filas
    private final int numMaxCol= 5; // Máximo de cartas por columna

    public Mesa() {
        cuatroSobrantes = new Carta[numFilas][numMaxCol]; // Inicializar el array bidimensional
        // Llenar las columnas con cartas iniciales
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numMaxCol; j++) {
                // Creamos una nueva carta con valores específicos
                cuatroSobrantes[i][j] = new Carta(numFilas,numMaxCol); // Supongamos que la carta inicial tiene valores 0 y 0
            }
        }
    }

    public void contenidoMesa (){
        for (int i = 0; i < numFilas; i++){
            for (int j = 0; j < numMaxCol; j++){
                System.out.println(cuatroSobrantes[i][j]);
            }
        }
        
        
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mesa:\n");
         for (int i = 0; i < cuatroSobrantes.length; i++) {
            sb.append("Fila ").append(i).append(": ");
            if (cuatroSobrantes[i] != null) {
                for (Carta carta : cuatroSobrantes[i]) {
                    sb.append(carta.toString()).append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}