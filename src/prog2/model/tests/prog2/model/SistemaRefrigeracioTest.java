package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SistemaRefrigeracioTest {
    private SistemaRefrigeracio sistemaRefrigeracio;
    private BombaRefrigerant bomba1;
    private BombaRefrigerant bomba2;
    private VariableUniforme uniforme;
    private PaginaIncidencies paginaIncidencies;
    @BeforeEach
    void setUp() {
        paginaIncidencies = new PaginaIncidencies(2);
        uniforme = new VariableUniforme(123);
        bomba1 = new BombaRefrigerant(uniforme,1);
        bomba2 = new BombaRefrigerant(uniforme,2);
        sistemaRefrigeracio = new SistemaRefrigeracio();
    }

    @Test
    void afegirBomba() {
        sistemaRefrigeracio.afegirBomba(bomba1);
        sistemaRefrigeracio.afegirBomba(bomba2);
        assertTrue(sistemaRefrigeracio.getBombas().contains(bomba1));
        assertTrue(sistemaRefrigeracio.getBombas().contains(bomba2));
    }

    @Test
    void activa() {
        sistemaRefrigeracio.afegirBomba(bomba1);
        sistemaRefrigeracio.afegirBomba(bomba2);
        sistemaRefrigeracio.activa();
        assertTrue(bomba1.getActivat());
        assertTrue(bomba2.getActivat());

    }

    @Test
    void desactiva() {
        sistemaRefrigeracio.afegirBomba(bomba1);
        sistemaRefrigeracio.afegirBomba(bomba2);
        bomba1.activa();
        bomba2.activa();
        sistemaRefrigeracio.desactiva();
        assertFalse(bomba1.getActivat());
        assertFalse(bomba2.getActivat());
    }

    @Test
    void getActivat() {
        sistemaRefrigeracio.afegirBomba(bomba1);
        sistemaRefrigeracio.afegirBomba(bomba2);
        bomba2.activa();
        assertTrue(sistemaRefrigeracio.getActivat());
    }

    @Test
    void getCostOperatiu() {
        sistemaRefrigeracio.afegirBomba(bomba1);
        sistemaRefrigeracio.afegirBomba(bomba2);
        bomba1.activa();
        bomba2.activa();
        assertEquals(sistemaRefrigeracio.getCostOperatiu(),260);
        bomba2.desactiva();
        assertEquals(sistemaRefrigeracio.getCostOperatiu(),130);
    }

    @Test
    void calculaOutput() {
        sistemaRefrigeracio.afegirBomba(bomba1);
        sistemaRefrigeracio.afegirBomba(bomba2);
        bomba1.activa();
        bomba2.activa();
        assertEquals(sistemaRefrigeracio.calculaOutput(400),400);
        bomba1.desactiva();
        assertEquals(sistemaRefrigeracio.calculaOutput(500),250);
    }
}