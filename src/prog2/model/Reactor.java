package prog2.model;

import prog2.vista.CentralUBException;
/**
 * @author Marc Piñero i Dídac Gasulla
 *
 * Aquesta classe conté un float amb la temperatura del reactor i un boolean per saber si està actiu.
 * un mètode per a activar i per desactivar el reactor, un per revisar el funcionament i un per obtenir el cost
 * i el output.
 */

import java.io.Serializable;
import java.lang.foreign.SegmentAllocator;

public class Reactor implements InComponent, Serializable {
    private float temp;
    private boolean activat;

    public Reactor() {
        activat = true;
        temp = 25;
    }

    @Override
    public void activa() throws CentralUBException {
        if (temp > 1000)
            throw new CentralUBException("Temperatura superior a 1000º");
        activat = true;


    }

    @Override
    public void desactiva() {
        activat = false;

    }

    @Override
    public boolean getActivat() {
        return activat;
    }

    /**
     * Aquest mètode comprova que el funcionament del reactor sugui correcte a partir del valor de la
     * temperatura, en cas que sigui major a 1000 posa el valor del boolean activat a fals.
     * finalement llança una excepció dient que la temperatura del reactor es superior a 1000 graus.
     */
    @Override
    public void revisa(PaginaIncidencies p) {
        if (temp > 1000){
            p.afegeixIncidencia("Temperatura del reactor superior a 1000º");
            activat = false;}

    }

    @Override
    public float getCostOperatiu() {
        return activat ? 35:0;
    }

    @Override
    public float calculaOutput(float input) {
        if (activat)
            return temp + (100-input) * 10;
        return temp;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }
}
