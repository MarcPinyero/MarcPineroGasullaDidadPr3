package prog2.model;

import prog2.vista.CentralUBException;
/**
 * @author Marc Piñero i Dídac Gasulla
 *
 * Aquesta classe té un boolean per si està actiu o no el generador i un mètode per activar o desactivar-lo,
 *  un per revisar el correcte funcionament i un per calcular el cost i el output.
 */

import java.io.Serializable;

public class GeneradorVapor implements InComponent, Serializable {

    private boolean activat;

    public GeneradorVapor() {
        this.activat = true;
    }

    @Override
    public void activa() throws CentralUBException {
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
        return activat ? 25:0;
    }

    @Override
    public float calculaOutput(float input) {
        return activat ? input*0.9f:25;
    }
}
