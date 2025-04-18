package prog2.model;

import java.util.ArrayList;
import java.util.List;

public class Bitacola implements InBitacola{
    private ArrayList<PaginaBitacola> pagines;

    public Bitacola() {
        this.pagines = new ArrayList<>();
    }

    @Override
    public void afegeixPagina(PaginaBitacola p) {
        pagines.add(p);

    }

    //PUEDE QUE ESTE MAL
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
