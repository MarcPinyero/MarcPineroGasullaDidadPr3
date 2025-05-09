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
        bombaRefrigerant = new BombaRefrigerant(uniforme,123);
    }
@Test
    public void activa(){
        bombaRefrigerant.activa();
        assertFalse(bombaRefrigerant.getActivat(),"No s'ha activat la bomba refrigerant");
}
@Test
    public void desactiva(){
        bombaRefrigerant.desactiva();
        assertTrue(bombaRefrigerant.getActivat(),"No s'ha desactivat la bomba refrigerant");
    }
    @Test
    public void revisa(){
        bombaRefrigerant.revisa(paginaIncidencies);
        if (uniforme.seguentValor() <= 25){
            assertFalse(bombaRefrigerant.getForaDeServei(),"No s'ha revisat correctament el funcionament de la bomba refrigerant");

        }

    }

}