package prog2.model;

import prog2.vista.CentralUBException;

public class Turbina implements InComponent{

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
