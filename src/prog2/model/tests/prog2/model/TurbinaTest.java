package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TurbinaTest {
    private Turbina turbina;
    private float input;
    @BeforeEach
    void setup(){
        turbina = new Turbina();
    }
    @Test
    public void activa(){
        turbina.activa();
        assertTrue(turbina.getActivat(),"No s'ha activat la turbina");
    }
    @Test
    public void desactiva(){
        turbina.desactiva();
        assertFalse(turbina.getActivat(),"No s'ha desactivat la turbina");
    }
    @Test
    public void costOperatiu(){
        if (turbina.getActivat()){
            assertEquals(turbina.getCostOperatiu(), 20,"No s'ha obtingut el cost operatiu correctament ");
        }
    }
    @Test
    void calculaOutput() {
        assertEquals(turbina.calculaOutput(150),300);
        assertEquals(turbina.calculaOutput(50),0);
        turbina.desactiva();
        assertEquals(turbina.calculaOutput(150),0);


    }

}