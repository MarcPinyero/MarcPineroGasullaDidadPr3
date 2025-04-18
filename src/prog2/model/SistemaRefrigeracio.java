package prog2.model;

import prog2.vista.CentralUBException;

import java.util.ArrayList;

public class SistemaRefrigeracio implements InComponent{

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
    public float getCostOperatiu() {
        float cost = 0;
        for (BombaRefrigerant bomba : bombas){
            cost+= bomba.getCostOperatiu();
        }
        return cost;
    }

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
