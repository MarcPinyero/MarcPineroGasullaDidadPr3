package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BombaRefrigerantTest {
    private BombaRefrigerant bombaRefrigerant;
    private VariableUniforme uniforme;
    private PaginaIncidencies paginaIncidencies;
    @BeforeEach
    void setUp() {
        uniforme = new VariableUniforme(31);
        bombaRefrigerant = new BombaRefrigerant(uniforme,123);
    }
    @Test
    public void activa(){
        bombaRefrigerant.activa();
        assertTrue(bombaRefrigerant.getActivat(),"No s'ha activat la bomba refrigerant");
    }
    @Test
    public void desactiva(){
        bombaRefrigerant.desactiva();
        assertFalse(bombaRefrigerant.getActivat(),"No s'ha desactivat la bomba refrigerant");
    }
    @Test
    public void revisa(){
        bombaRefrigerant.revisa(paginaIncidencies);
        if (uniforme.seguentValor() <= 25){
            assertTrue(bombaRefrigerant.getForaDeServei(),"No s'ha revisat correctament el funcionament de la bomba refrigerant");

        }

    }

}