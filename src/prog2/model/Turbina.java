package prog2.model;

import prog2.vista.CentralUBException;
/**
 * @author Marc Piñero i Dídac Gasulla
 *
 * Aquesta classe només té un boolean per saber si està activa o no, un mètode per activar i un per desactivar,
 * després un mètode per revisar el funcionament, un per calcular el cost i un per calcular el output.
 */

import java.io.Serializable;

public class Turbina implements InComponent, Serializable {

    private boolean activat;

    public Turbina() {
        this.activat = true;
    }

    @Override
    public void activa(){
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

    @Override
    public void revisa(PaginaIncidencies p) {

    }

    @Override
    public float getCostOperatiu() {
        return activat ? 20:0;
    }

    @Override
    public float calculaOutput(float input) {
        if (activat && input >= 100)
            return input * 2;
        return 0;
    }
}
