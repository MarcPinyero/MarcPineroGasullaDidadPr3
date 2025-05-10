package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BitacolaTest {
    private Bitacola bitacola;
    private PaginaEconomica paginaEconomica;
    private PaginaIncidencies paginaIncidencies;
    @BeforeEach
    void setUp() {
        bitacola = new Bitacola();
    }
    @Test
    public void afegirPagina() {
        paginaEconomica = new PaginaEconomica(2, 3500, 4000, 2000,110, 0,200, 100);
        bitacola.afegeixPagina(paginaEconomica);
        assertTrue(bitacola.toString()!= null, "La pagina no s'ha afegit ");
    }
    @Test
    public void getLListaIncidencies(){
        paginaIncidencies = new PaginaIncidencies(1);
        bitacola.afegeixPagina(paginaIncidencies);
        assertTrue(bitacola.getIncidencies() != null, "La llista d'incidencies no funciona correctement");
    }

}