package prog2.model;

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
