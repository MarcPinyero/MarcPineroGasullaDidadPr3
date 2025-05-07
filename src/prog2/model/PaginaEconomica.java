package prog2.model;
/**
 * @author Marc Piñero i Dídac Gasulla
 *
 * Aquesta classe hereta de paginaBitacola i té 7 floats, la demanda, la potencia, la demanda satisfeta,
 * els beneficis, la penalització, el cost i els guanys. Els mètodes son només els getters i setters i el
 * toString per a imprimir tota la informacio de la pàgina.
 */

public class PaginaEconomica extends PaginaBitacola {
    private float demanda;
    private float potencia;
    private float demandaSatisfeta;
    private float beneficis;
    private float penalitzacio;
    private float cost;
    private float guanys;

    public PaginaEconomica(int dia, float demanda, float potencia, float demandaSatisfeta, float beneficis, float penalitzacio, float cost, float guanys) {
        super(dia);
        this.demanda = demanda;
        this.potencia = potencia;
        this.demandaSatisfeta = demandaSatisfeta;
        this.beneficis = beneficis;
        this.penalitzacio = penalitzacio;
        this.cost = cost;
        this.guanys = guanys;
    }

    public float getDemanda() {
        return demanda;
    }

    public float getPotencia() {
        return potencia;
    }

    public float getDemandaSatisfeta() {
        return demandaSatisfeta;
    }

    public float getBeneficis() {
        return beneficis;
    }

    public float getPenalitzacio() {
        return penalitzacio;
    }

    public float getCost() {
        return cost;
    }

    public float getGuanys() {
        return guanys;
    }

    @Override
    public String toString() {
        return "# Pàgina Econòmica\n" +
                "-Dia: " + getDia() +
                "\n-Demanda de Potència: " + demanda +
                "\n-Potència Generada: " + potencia +
                "\n-Demanda de Potència Satisfeta: " + demandaSatisfeta + " %" +
                "\n-Beneficis=" + beneficis + " Unitats Econòmiques" +
                "\n-Penalització Excés Producció: " + penalitzacio + " Unitats Econòmiques" +
                "\n-Cost Operatiu: " + cost + " Unitats Econòmiques" +
                "\n-Guanys acumulats: " + guanys + " Unitats Econòmiques";
    }
}
