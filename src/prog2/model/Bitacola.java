package prog2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Marc Piñero i Dídac Gasulla
 *
 * Aquesta classe conté un arraylist d'objectes de pàgina bitacola. i uns mètodes per a afegir pàgines,
 * obtenir incidencies i un toString per a imprimir les pàgines.
 */

public class Bitacola implements InBitacola, Serializable {
    private ArrayList<PaginaBitacola> pagines;

    public Bitacola() {
        this.pagines = new ArrayList<>();
    }

    @Override
    public void afegeixPagina(PaginaBitacola p) {
        pagines.add(p);

    }


    @Override
    public List<PaginaIncidencies> getIncidencies() {
        List<PaginaIncidencies> incidencies = new ArrayList<>();
        for (PaginaBitacola pagina: pagines){
            if (pagina instanceof PaginaIncidencies)
                incidencies.add((PaginaIncidencies) pagina);
        }
        return List.of(incidencies.toArray(new PaginaIncidencies[0]));
    }

    @Override
    public String toString() {
        String s = "";
        for (PaginaBitacola pagina : pagines){
            s += pagina.toString() + "\n";
        }
        return  s;
    }
}
