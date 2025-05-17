package prog2.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;
public class ReactorTest {
    private Reactor reactor;
    private PaginaIncidencies paginaIncidencies;
    private float input;
    @BeforeEach
    void setUp() {
        reactor = new Reactor();
        paginaIncidencies = new PaginaIncidencies(1);
    }
    @Test
    public void activa(){
        reactor.activa();
        assertTrue(reactor.getActivat());
        reactor.desactiva();
        reactor.setTemp(2000);
        CentralUBException excepcio = assertThrows(CentralUBException.class, () -> {
            reactor.activa();
        });
        assertTrue(excepcio.getMessage().contains("Temperatura superior a 1000º"));

    }
    @Test
    public void desactiva(){
        reactor.desactiva();
        assertFalse(reactor.getActivat());
    }
    @Test
    public void revisa (){
        reactor.setTemp(2000);
        reactor.revisa(paginaIncidencies);
        assertTrue(paginaIncidencies.toString().contains("Temperatura del reactor superior a 1000"));


    }
    @Test
    public void calculaOutput(){
        reactor.calculaOutput(input);
        assertEquals(reactor.calculaOutput(input), reactor.getTemp() + (100 - input) * 10, "No s'ha calculat correctament el output");

    }


}