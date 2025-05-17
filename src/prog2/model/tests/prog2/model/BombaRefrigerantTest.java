package prog2.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class BombaRefrigerantTest {
    private BombaRefrigerant bombaRefrigerant;
    private VariableUniforme uniforme;
    private PaginaIncidencies paginaIncidencies;
    @BeforeEach
    void setUp() {
        paginaIncidencies = new PaginaIncidencies(2);
        uniforme = new VariableUniforme(123);
        bombaRefrigerant = new BombaRefrigerant(uniforme,123);
    }
    @Test
    public void activa(){
        bombaRefrigerant.activa();
        assertTrue(bombaRefrigerant.getActivat());
    }
    @Test
    public void desactiva(){
        bombaRefrigerant.desactiva();
        assertFalse(bombaRefrigerant.getActivat());
    }
    @Test
    public void revisa(){
        if (uniforme.seguentValor() <= 25){
            bombaRefrigerant.revisa(paginaIncidencies);
            assertTrue(paginaIncidencies.toString().contains("La bomba refrig. " + bombaRefrigerant.getId() +"esta fora de servei."));
        }



    }

}