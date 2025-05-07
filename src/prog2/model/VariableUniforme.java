/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;
import java.util.Random;

/**
 * @author Marc Piñero i Dídac Gasulla
 *
 * Aquesta classe té una atribut random per a generar la variable uniforme, és un valor entre 0 i 100, té un
 * mètode per a generar el seguent valor.
 */
public class VariableUniforme implements Serializable {
    private Random random;

    public VariableUniforme(long seed) {
        this.random = new Random(seed);
    }

    public int seguentValor() {
        return random.nextInt(100);
    }
}
