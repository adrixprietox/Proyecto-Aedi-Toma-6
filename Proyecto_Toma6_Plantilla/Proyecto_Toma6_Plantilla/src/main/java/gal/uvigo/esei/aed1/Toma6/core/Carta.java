
/*
 * Representa una carta, formada por un número y la cantidad de bueyes correspondiente
 */
package gal.uvigo.esei.aed1.Toma6.core;

public class Carta {

    private int numero;
    private int bueyes;
    
    public Carta(int numero, int bueyes) {
        this.numero = numero;
        this.bueyes = bueyes;
    }

    public int getNumero() {
        return numero;
    }
    
    public int getBueyes() {
        return bueyes;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            sb.append("Carta ").append(":");
            sb.append("{numero=").append(numero);
            sb.append(", bueyes=").append(bueyes);
            sb.append('}').append("");
        
        
        return sb.toString();
    }
    
}
