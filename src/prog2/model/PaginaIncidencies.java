package prog2.model;

import java.util.ArrayList;
/**
 * @author Marc Piñero i Dídac Gasulla
 *
 * Aquesta classe també hereta de pagina bitacola i conté les incidencies que hi ha a les pagines, després té
 * un mètode per a afegir incidencies, i un set de la llista d'incidencies. finalment el toString per a
 * imprimir les incidencies.
 */

public class PaginaIncidencies extends PaginaBitacola{
    private ArrayList<String> llistaIncidencies;

    public PaginaIncidencies(int dia) {
        super(dia);
        this.llistaIncidencies = new ArrayList<>();
    }

    public void afegeixIncidencia(String descIncidencia){
        llistaIncidencies.add(descIncidencia);
    }

    public ArrayList<String> getLlistaIncidencies() {
        return llistaIncidencies;
    }

    public void setLlistaIncidencies(ArrayList<String> llistaIncidencies) {
        this.llistaIncidencies = llistaIncidencies;
    }

    @Override
    public String toString() {
        String s = "# Pagina Incidencies\n- Dia: " + getDia();
        for (String incidencia : llistaIncidencies) {
            s += "\nDescripció Incidència: " + incidencia;
        }
        return s;
    }

}
