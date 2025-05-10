package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaRefrigeracioTest {
    private SistemaRefrigeracio sistemaRefrigeracio;
    private BombaRefrigerant bombaRefrigerant;
    private PaginaIncidencies paginaIncidencies;
    private VariableUniforme uniforme;
    private float input;
    @BeforeEach
    void setup(){
        uniforme = new VariableUniforme(321);
        bombaRefrigerant = new BombaRefrigerant(uniforme, 123);
        sistemaRefrigeracio = new SistemaRefrigeracio();
    }
    @Test
    public void activa(){
        sistemaRefrigeracio.activa();
        assertFalse(sistemaRefrigeracio.getActivat(),"No s'ha activat el sistema de refrigeracio");

    }
    @Test
    public void desactiva(){
        sistemaRefrigeracio.desactiva();
        assertFalse(sistemaRefrigeracio.getActivat(),"No s'ha desactivat el sistema de refrigeracio");
    }
    @Test
    public void afegeirBomba(){

    }
    @Test
    public void revisa() {
        bombaRefrigerant.revisa(paginaIncidencies);
        if (uniforme.seguentValor() <= 25) {
            assertFalse(bombaRefrigerant.getForaDeServei(), "No s'ha revisat correctament el funcionament de la bomba refrigerant");

        }
    }

    //@Test
    //public void calculaOutput(){
    //sistemaRefrigeracio.calculaOutput(input);
    // assertEquals(sistemaRefrigeracio.calculaOutput(input),input , "No s'ha calculat correctament el output");

    //}


}