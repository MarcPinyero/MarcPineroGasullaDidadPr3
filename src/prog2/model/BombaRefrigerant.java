package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;

public class BombaRefrigerant implements InBombaRefrigerant, Serializable {

    private int Id;
    private  boolean activat;
    private  boolean foraDeServei;
    private VariableUniforme variableUniforme;

    public BombaRefrigerant(VariableUniforme var, int id) {
        Id = id;
        variableUniforme = var;

    }

    @Override
    public int getId() {
        return this.Id;
    }

    @Override
    public void activa() throws CentralUBException {
        if (foraDeServei)
            throw new CentralUBException("Fora de servei.");
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
        if(variableUniforme.seguentValor() <= 25) {
            foraDeServei = true;
            activat = false;
            p.afegeixIncidencia("La bomba refrig. " + Id +"esta fora de servei.");
        }


    }

    @Override
    public boolean getForaDeServei() {
        return foraDeServei;
    }

    //EN el pdf no dice nada de capacidad
    @Override
    public float getCapacitat() {
        return activat ? 250:0;
    }

    @Override
    public float getCostOperatiu() {
        return activat ? 130:0;
    }
}
