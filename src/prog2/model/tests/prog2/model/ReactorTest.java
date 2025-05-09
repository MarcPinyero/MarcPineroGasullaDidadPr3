package prog2.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
public class ReactorTest {
    private Reactor reactor;
    private PaginaIncidencies paginaIncidencies;
    private float input;
    @BeforeEach
    void setUp() {
        reactor = new Reactor();
    }
    @Test
    public void activa(){
        reactor.activa();
        if (reactor.getTemp() <= 1000){
            assertFalse(reactor.getActivat(),"No s'ha activat el reactor");
        }
    }
    @Test
    public void desactiva(){
        reactor.desactiva();
        assertTrue(reactor.getActivat(),"No s'ha desactivat el reactor");
    }
    @Test
    public void revisa (){
        reactor.revisa(paginaIncidencies);
        if (reactor.getTemp() > 1000){
            assertTrue(reactor.getActivat(),"No s'ha revisat correctament el funcionament del reactor");
        }
    }
    @Test
    public void calculaOutput(){
        reactor.calculaOutput(input);
        assertEquals(reactor.calculaOutput(input), reactor.getTemp() + (100 - input) * 10, "No s'ha calculat correctament el output");

    }


}