package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GeneradorVaporTest {
    private GeneradorVapor generadorVapor;
    @BeforeEach
    public void setUp() {
        generadorVapor = new GeneradorVapor();
    }
    @Test
    public void activa(){
        generadorVapor.activa();
        assertTrue(generadorVapor.getActivat(),"No s'ha activat el generador de vapor");
    }
    @Test
    public void desactiva(){
        generadorVapor.desactiva();
        assertFalse(generadorVapor.getActivat(),"No s'ha desactivat el generador de vapor");
    }

}