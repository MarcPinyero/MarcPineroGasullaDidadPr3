package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * @author Marc Piñero i Dídac Gasulla
 *
 * Aquesta classe té un arraylist de bombes refrigerants i un boolean per saber si està actiu. després un
 * mètode per a afegir un bomba, un per activar i un per desactivar, i després un altre per revisar, un per
 * calcular el cost i finalment un per calcular el output
 */

public class SistemaRefrigeracio implements InComponent, Serializable {

    private ArrayList<BombaRefrigerant> bombas;
    private boolean activat;

    public SistemaRefrigeracio() {
        this.bombas = new ArrayList<>();
        this.activat = true;
    }

    public void afegirBomba(BombaRefrigerant bomba){
        bombas.add(bomba);
    }

    @Override
    public void activa() throws CentralUBException {
        for (BombaRefrigerant bomba : bombas){
            bomba.activa();
        }
        this.activat = true;

    }

    @Override
    public void desactiva() {
        for (BombaRefrigerant bomba : bombas){
            bomba.desactiva();
        }
        this.activat = false;

    }

    @Override
    public boolean getActivat() {
        for (BombaRefrigerant bomba : bombas){
            if (bomba.getActivat())
                return true;
        }
        return false;
    }

    @Override
    public void revisa(PaginaIncidencies p) {
        for (BombaRefrigerant bomba : bombas){
            bomba.revisa(p);
        }

    }

    @Override
    /**
     * Aquest mètode utilitza el mètode de bomba refrigerant per a calcular el cost de totes les bombes i
     * retorna la suma d'els valors del cost de cada bomba.
     * @return cost
     */
    public float getCostOperatiu() {
        float cost = 0;
        for (BombaRefrigerant bomba : bombas){
            cost+= bomba.getCostOperatiu();
        }
        return cost;
    }
    /**
     * Aquest mètode primer calcula el nombre de bombes refrigerants que estan actives i després retorna
     * el nombre de bombes atives multiplicat per 250 si aquest valor es més petit que el input donat i sino
     * retorna aquest input.
     * @return float
     */
    @Override
    public float calculaOutput(float input) {
        int activas = 0;

        for (BombaRefrigerant bomba : bombas){
            if (bomba.getActivat())
                activas +=1;
        }
        if (250*activas < input)
            return 250*activas;
        return input;
    }

    public ArrayList<BombaRefrigerant> getBombas() {
        return bombas;
    }

    public void setBombas(ArrayList<BombaRefrigerant> bombas) {
        this.bombas = bombas;
    }
}
