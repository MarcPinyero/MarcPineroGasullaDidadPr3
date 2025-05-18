package prog2.model;

import prog2.vista.CentralUBException;
/**
 * @author Marc Piñero i Dídac Gasulla
 *
 * Aquesta classe conté 4 atributs, que són el id, un boolean per si està actiu i un per si està fora de
 * servei i un de tipus VariableUniforme. Després els getters i setters d'aquests atributs i uns mètodes per
 * activar o desactivar la bomba, que llancen excepcions en cas necessari. Finalment un mètode que revisa que
 * el seguent valor de la variable uniforme no sigui més petit que 25, i en cas que ho sigui posa la bomba
 * fora de servei
 *
 */

import java.io.Serializable;

public class BombaRefrigerant implements InBombaRefrigerant, Serializable {

    private int Id;
    private  boolean activat;
    private  boolean foraDeServei;
    private VariableUniforme variableUniforme;

    public BombaRefrigerant(VariableUniforme var, int id) {
        Id = id;
        this.variableUniforme = var;

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

    /**
     * Aquest mètode comprova que el funcionament de la bomba sugui correcte a partir del valor de la variable
     * uniforme, en cas que sigui menor o igual a 25 posa el valor del boolean fora de servei a true i el de
     * activat a fals. finalement llança una excepció dient que la bomba esta fora de servei.
     */
    @Override
    public void revisa(PaginaIncidencies p) {

        if(variableUniforme.seguentValor() <= 25) {
            foraDeServei = true;
            activat = false;
            p.afegeixIncidencia("La bomba refrig. " + Id +"esta fora de servei.");
        }
        else {
            foraDeServei = false;
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

    public void setForaDeServei(boolean foraDeServei) {
        this.foraDeServei = foraDeServei;
    }
}
