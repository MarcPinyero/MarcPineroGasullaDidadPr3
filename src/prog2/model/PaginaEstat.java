package prog2.model;/**
 * @author Marc Piñero i Dídac Gasulla
 *
 * Aquesta classe també hereta de pagina bitacola i conté 5 floats, que representen les barres, els reactors
 * la refrigeració, el generador i la turbina. Finalment els getters i setters i el toString per a imprimir
 * el contingut de la pàgina.
 */

public class PaginaEstat extends PaginaBitacola{
    private float OutBarres;
    private float OutReactor;
    private float OutRefrigeracio;
    private float OutGenerador;
    private float OutTurbina;

    public PaginaEstat(int dia, float outBarres, float outReactor, float outRefrigeracio, float outGenerador, float outTurbina) {
        super(dia);
        OutBarres = outBarres;
        OutReactor = outReactor;
        OutRefrigeracio = outRefrigeracio;
        OutGenerador = outGenerador;
        OutTurbina = outTurbina;
    }

    public float getOutBarres() {
        return OutBarres;
    }

    public float getOutReactor() {
        return OutReactor;
    }

    public float getOutRefrigeracio() {
        return OutRefrigeracio;
    }

    public float getOutGenerador() {
        return OutGenerador;
    }

    public float getOutTurbina() {
        return OutTurbina;
    }

    @Override
    public String toString() {
        return "# PaginaEstat" +
                "\n- Dia: " + getDia() +
                "\n- OutBarres=" + OutBarres + " %" +
                "\n- Output Reactor: " + OutReactor + " Graus" +
                "\n- Output Sistema de Refrigeració: " + OutRefrigeracio + " Graus" +
                "\n- Output Generador de Vapor: " + OutGenerador + " Graus" +
                "\n- Output Turbina: " + OutTurbina + " Unitats de Potència";
    }
}
