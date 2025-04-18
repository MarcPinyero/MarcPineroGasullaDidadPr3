package prog2.adaptador;

import prog2.model.*;

import java.util.List;

public class Adaptador {
    private Dades dades;

    public Adaptador() {
        dades = new Dades();
    }

    public float getInsercio(){
        return dades.getInsercioBarres();

    }

    public void setInsercio(float insercio){
        dades.setInsercioBarres(insercio);
    }

    public void activaReactor(){
        dades.activaReactor();
    }

    public void desactivaReactor(){
        dades.desactivaReactor();
    }

    public void mostraReactor(){
        Reactor reactor = dades.mostraReactor();
        System.out.println("Activat: " + reactor.getActivat() + "\nTemperatura: " + reactor.getTemp());

    }

    public void activaRefrigeracio(){
        SistemaRefrigeracio refri = dades.mostraSistemaRefrigeracio();
        refri.activa();

    }

    public void desactivaRefrigeracio(){
        SistemaRefrigeracio refri = dades.mostraSistemaRefrigeracio();
        refri.desactiva();

    }

    public void activaBomba(int id){
        dades.activaBomba(id);
    }

    public void desactivaBomba(int id){
        dades.desactivaBomba(id);
    }

    public void mostraRefrigeracio(){
        SistemaRefrigeracio refri = dades.mostraSistemaRefrigeracio();
        for (BombaRefrigerant bomba : refri.getBombas()){
            System.out.println("ID: " + bomba.getId() + "Activat: " + bomba.getActivat() + "Capacitat: " + bomba.getCapacitat() + "Cost operatiu: " + bomba.getCostOperatiu() + "Fora de servei: " + bomba.getForaDeServei());
        }
    }

    public void mostrarEstat(){
        PaginaEstat estat = dades.mostraEstat();
        System.out.println(estat.toString());
    }

    public void mostrarBitacola(){
        Bitacola bitacola = dades.mostraBitacola();
        System.out.println(bitacola.toString());
    }

    public void mostrarIncidencies(){
        List<PaginaIncidencies> incidencies = dades.mostraIncidencies();

        StringBuilder resultat = new StringBuilder();
        for (PaginaIncidencies pagina : incidencies) {
            resultat.append(pagina.toString()).append("\n");
        }

        System.out.println(resultat.toString());
    }

    public void mostrarPercentatge(float demanda){
        System.out.println("Demanda d'avui: " + demanda + "Potencia generada: " + dades.calculaPotencia() + "Percentatge de satisfacció: " + (int) ((dades.calculaPotencia()/demanda) * 100) + " %");
    }

    public String finalitzaDia(float demanda){
        Bitacola bitacola = dades.finalitzaDia(demanda);
        return bitacola.toString();


    }





}
