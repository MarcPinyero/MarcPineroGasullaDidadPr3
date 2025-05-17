package prog2.model;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;

public class DadesTest {

    private Dades dades;

    @BeforeEach
    void setUp() {
        dades = new Dades();
    }

    @Test
    void GetInsercioBarresInicial() {
        assertEquals(100, dades.getInsercioBarres());
    }

    @Test
    void SetInsercioBarresValid() throws CentralUBException {
        dades.setInsercioBarres(50);
        assertEquals(50, dades.getInsercioBarres());
    }

    @Test
    void SetInsercioBarresInvalidBaix() {
        assertThrows(CentralUBException.class, () -> dades.setInsercioBarres(-10));
    }


    @Test
    void ActivaReactorValid() throws CentralUBException {
        dades.mostraReactor().setTemp(500);
        dades.activaReactor();
        assertTrue(dades.mostraReactor().getActivat());
    }

    @Test
    void ActivaReactorExcepcio() {
        dades.mostraReactor().setTemp(1200);
        assertThrows(CentralUBException.class, () -> dades.activaReactor());
    }

    @Test
    void DesactivaReactor() {
        dades.mostraReactor().activa();
        dades.desactivaReactor();
        assertFalse(dades.mostraReactor().getActivat());
    }

    @Test
    void ActivaDesactivaBomba() throws CentralUBException {
        dades.activaBomba(1);
        assertTrue(dades.mostraSistemaRefrigeracio().getBombas().get(1).getActivat());

        dades.desactivaBomba(2);
        assertFalse(dades.mostraSistemaRefrigeracio().getBombas().get(2).getActivat());
    }


    @Test
    void calculaPotenciaTest() {
        dades.setInsercioBarres(40);
        dades.activaReactor();
        dades.mostraReactor().setTemp(300);
        dades.mostraSistemaRefrigeracio().activa();
        assertEquals(dades.calculaPotencia(), 1620);

    }

    @Test
    void GetGuanysAcumulats() {
        assertEquals(0, dades.getGuanysAcumulats());
    }

    @Test
    void MostraEstat() {
        assertNotNull(dades.mostraEstat());
    }

    @Test
    void MostraBitacola() {
        assertNotNull(dades.mostraBitacola());
    }

    @Test
    void MostraIncidencies() {
        assertNotNull(dades.mostraIncidencies());
        assertTrue(dades.mostraIncidencies().isEmpty());
    }

    @Test
    void FinalitzaDia() {
        Bitacola resultat = dades.finalitzaDia(150);
        assertNotNull(resultat);
    }
}