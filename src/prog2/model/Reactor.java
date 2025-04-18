package prog2.model;

import prog2.vista.CentralUBException;

public class Reactor implements InComponent{
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

    @Override
    public void revisa(PaginaIncidencies p) {
        if (temp > 1000)
            p.afegeixIncidencia("Temperatura del reactor superior a 1000º");

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
