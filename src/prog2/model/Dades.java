/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;
import java.util.List;

/**
 * @author Marc Piñero i Dídac Gasulla
 *
 * la classe dades és la principal del paquet model, ja que conté i gestiona totes les dades de la central.
 * conté els seguents atributs: una VariableUniforme un float per l'insercio de les barres, un reactor,
 * un sistemaRefrigeracio, un generador de vapor, una turbina, Bitacola un int per contar els dies, i un
 * float per a calcular els guanys totals. també té 4 valors finals, un per als guanys inicials, un per al
 * preu per unitat de potencia, un preu per la sanció per exces de potencia, i un per .
 * Finalment té diverses funcions per interactuar amb aquests atributs.
 */
public class Dades implements InDades, Serializable {
    public final static long  VAR_UNIF_SEED = 123;
    public final static float GUANYS_INICIALS = 0;
    public final static float PREU_UNITAT_POTENCIA = 1;
    public final static float PENALITZACIO_EXCES_POTENCIA = 250;

    // Afegir atributs:
    private VariableUniforme variableUniforme;
    private float insercioBarres;
    private Reactor reactor;
    private SistemaRefrigeracio sistemaRefrigeracio;
    private GeneradorVapor generadorVapor;
    private Turbina turbina;
    private Bitacola bitacola;
    private int dia;
    private float guanysAcumulats;
    public Dades(){
        // Inicialitza Atributs
        this.variableUniforme = new VariableUniforme(VAR_UNIF_SEED);
        this.insercioBarres = 100;
        this.reactor = new Reactor();
        this.reactor.desactiva();
        this.sistemaRefrigeracio = new SistemaRefrigeracio();
        this.generadorVapor = new GeneradorVapor();
        this.generadorVapor.activa();
        this.turbina = new Turbina();
        this.turbina.activa();
        this.bitacola = new Bitacola();
        this.dia = 1;
        this.guanysAcumulats = GUANYS_INICIALS;
        
        // Afegeix bombes refrigerants
        BombaRefrigerant b0 = new BombaRefrigerant(variableUniforme, 0);
        BombaRefrigerant b1 = new BombaRefrigerant(variableUniforme, 1);
        BombaRefrigerant b2 = new BombaRefrigerant(variableUniforme, 2);
        BombaRefrigerant b3 = new BombaRefrigerant(variableUniforme, 3);
        this.sistemaRefrigeracio.afegirBomba(b0);
        this.sistemaRefrigeracio.afegirBomba(b1);
        this.sistemaRefrigeracio.afegirBomba(b2);
        this.sistemaRefrigeracio.afegirBomba(b3);

        this.sistemaRefrigeracio.desactiva();
    }
    
    /**
     * Actualitza l'economia de la central. Genera una pàgina econòmica a 
     * partir de la demanda de potencia actual. Aquesta pàgina econòmica inclou 
     * beneficis, penalització per excès de potència, costos operatius y 
     * guanys acumulats.
     * @param demandaPotencia Demanda de potència actual.
     */
    private PaginaEconomica actualitzaEconomia(float demandaPotencia){
        float potencia = calculaPotencia();
        float penalitzacio = 0;
        float beneficis = potencia;
        float costos = reactor.getCostOperatiu() + sistemaRefrigeracio.getCostOperatiu() + generadorVapor.getCostOperatiu() + turbina.getCostOperatiu();


        if (potencia > demandaPotencia)
            penalitzacio = 250;

        guanysAcumulats+= beneficis - costos - penalitzacio;

        float percentatge = (potencia / demandaPotencia) * 100;

        PaginaEconomica economica = new PaginaEconomica(dia,demandaPotencia,potencia, percentatge,beneficis,penalitzacio,costos,guanysAcumulats);
        return economica;

    }

    /**
     * Aquest mètode ha de establir la nova temperatura del reactor.
     */
    private void refrigeraReactor() {
        float temp = mostraEstat().getOutReactor() - mostraEstat().getOutRefrigeracio();
        if (temp < 25)
            reactor.setTemp(25);
        else
            reactor.setTemp(temp);

    }

    /**
     * Aquest mètode ha de revisar els components de la central. Si
     * es troben incidències, s'han de registrar en la pàgina d'incidències
     * que es proporciona com a paràmetre d'entrada.
     * @param paginaIncidencies Pàgina d'incidències.
     */
    private void revisaComponents(PaginaIncidencies paginaIncidencies) {
          reactor.revisa(paginaIncidencies);
          sistemaRefrigeracio.revisa(paginaIncidencies);
          generadorVapor.revisa(paginaIncidencies);
          turbina.revisa(paginaIncidencies);
    }

    @Override
    public float getInsercioBarres() {
        return insercioBarres;
    }

    @Override
    public void setInsercioBarres(float insercioBarres) throws CentralUBException {
        if (insercioBarres < 0 || insercioBarres > 100)
            throw new CentralUBException("El percentatge d'inserciò ha d'estar entre 0 i 100");
        this.insercioBarres = insercioBarres;
    }

    @Override
    public void activaReactor() throws CentralUBException {
        reactor.activa();


    }

    @Override
    public void desactivaReactor() {
        reactor.desactiva();

    }

    @Override
    public Reactor mostraReactor() {
        return reactor;
    }

    @Override
    public void activaBomba(int id) throws CentralUBException {

        for (BombaRefrigerant bomba : sistemaRefrigeracio.getBombas()){
            if (bomba.getId() == id)
                bomba.activa();
        }


    }

    @Override
    public void desactivaBomba(int id) {
        for (BombaRefrigerant bomba : sistemaRefrigeracio.getBombas()){
            if (bomba.getId() == id)
                bomba.desactiva();
        }

    }

    @Override
    public SistemaRefrigeracio mostraSistemaRefrigeracio() {
        return sistemaRefrigeracio;
    }

    @Override
    public float calculaPotencia() {
        return mostraEstat().getOutTurbina();
    }

    @Override
    public float getGuanysAcumulats() {
        return guanysAcumulats;
    }

    @Override
    public PaginaEstat mostraEstat() {
        float OutBarres = insercioBarres;
        float OutReactor = reactor.calculaOutput(insercioBarres);
        float OutRefrigeracio = sistemaRefrigeracio.calculaOutput(OutReactor);
        float OutGenerador = generadorVapor.calculaOutput(OutRefrigeracio);
        float OutTurbina = turbina.calculaOutput(OutGenerador);
        PaginaEstat estat = new PaginaEstat(dia, OutBarres, OutReactor, OutRefrigeracio, OutGenerador, OutTurbina);
        return estat;
    }

    @Override
    public Bitacola mostraBitacola() {
        return bitacola;
    }

    @Override
    public List<PaginaIncidencies> mostraIncidencies() {
        return List.of(bitacola.getIncidencies().toArray(new PaginaIncidencies[0]));
    }

    public Bitacola finalitzaDia(float demandaPotencia) {
        // Actualitza economia
        PaginaEconomica paginaEconomica = actualitzaEconomia(demandaPotencia);
        
        // Genera pàgina d'estat amb la configuració escollida (la nova pàgina
        // d'estat inclou la nova configuració escollida pel operador abans de
        // refrigerar el reactor)
        PaginaEstat paginaEstat = mostraEstat();

        // Actualitza estat de la central...

        // Refrigera el reactor
        refrigeraReactor();

        // Revisa els components de la central i registra incidències
        PaginaIncidencies paginaIncidencies = new PaginaIncidencies(dia);
        revisaComponents(paginaIncidencies);
        
        // Incrementa dia
        dia += 1;
        
        // Guarda pàgines de bitacola
        bitacola.afegeixPagina(paginaEconomica);
        bitacola.afegeixPagina(paginaEstat);
        bitacola.afegeixPagina(paginaIncidencies);
        
        // Retorna pàgines
        Bitacola bitacolaDia = new Bitacola();
        bitacolaDia.afegeixPagina(paginaEconomica);
        bitacolaDia.afegeixPagina(paginaEstat);
        bitacolaDia.afegeixPagina(paginaIncidencies);
        return bitacolaDia;
    }
}
