package prog2.model;
/**
 * @author Marc Piñero i Dídac Gasulla
 *
 * Aquesta classe té un int amb el dia corresponent a la pàgina i els seus getters i setters.
 */

import java.io.Serializable;

public class PaginaBitacola implements Serializable {
    private int dia;

    public PaginaBitacola(int dia) {
        this.dia = dia;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }


}
