package prog2.model;

import prog2.vista.CentralUBException;

public class GeneradorVapor implements InComponent{

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
