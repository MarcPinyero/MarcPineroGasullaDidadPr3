package prog2.adaptador;

import prog2.model.*;
import prog2.vista.CentralUBException;

import java.io.*;
import java.util.List;
/**
 * @author Marc Piñero i Dídac Gasulla
 *
 * Aquesta classe s'utilitza per a intervenir entre vista i les dades del model. té un atribut de tipus dades
 * i diversos mètodes per a interactuar amb aquestes dades.
 */

public class Adaptador {
    private Dades dades;

    public Adaptador() {
        dades = new Dades();
    }

    public float getInsercio(){
        return dades.getInsercioBarres();

    }

    public void setInsercio(float insercio) throws CentralUBException {
        dades.setInsercioBarres(insercio);
    }

    public void activaReactor() throws CentralUBException{
        dades.activaReactor();
    }

    public void desactivaReactor(){
        dades.desactivaReactor();
    }

    public void mostraReactor(){
        Reactor reactor = dades.mostraReactor();
        System.out.println("Activat: " + reactor.getActivat() + "\nTemperatura: " + reactor.getTemp());

    }

    public void activaRefrigeracio() throws CentralUBException{
        SistemaRefrigeracio refri = dades.mostraSistemaRefrigeracio();
        refri.activa();

    }

    public void desactivaRefrigeracio(){
        SistemaRefrigeracio refri = dades.mostraSistemaRefrigeracio();
        refri.desactiva();

    }

    public void activaBomba(int id) throws CentralUBException{
        dades.activaBomba(id);
    }

    public void desactivaBomba(int id){
        dades.desactivaBomba(id);
    }

    public SistemaRefrigeracio mostraRefrigeracio(){
        return dades.mostraSistemaRefrigeracio();

    }

    public String  mostrarEstat(){
        PaginaEstat estat = dades.mostraEstat();
        return estat.toString();
    }

    public String mostrarBitacola(){
        Bitacola bitacola = dades.mostraBitacola();
        return bitacola.toString();
    }

    public String mostrarIncidencies(){
        List<PaginaIncidencies> incidencies = dades.mostraIncidencies();

        StringBuilder resultat = new StringBuilder();
        for (PaginaIncidencies pagina : incidencies) {
            resultat.append(pagina.toString()).append("\n");
        }

        return resultat.toString();
    }

    public void mostrarPercentatge(float demanda){
        System.out.println("Demanda d'avui: " + demanda + "Potencia generada: " + dades.calculaPotencia() + "Percentatge de satisfacció: " + (int) ((dades.calculaPotencia()/demanda) * 100) + " %");
    }

    public String finalitzaDia(float demanda){
        Bitacola bitacola = dades.finalitzaDia(demanda);
        return bitacola.toString();


    }
    /**
     * Aquest mètode fa servir un bloc de codi try catch per a guardar les dades de la central en un fitxer.
     * I llença les excepcions corresponents.
     */
    public void guardaDades(String camiDesti) throws CentralUBException{
        File file = new File(camiDesti);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        //Escribim les dades al fitxer.
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(this.dades);
        }
        catch (IOException e) {
            throw new CentralUBException(e.getMessage());
        }

        finally {
            try {
                if (fos!= null)
                    fos.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            try {
                if (oos != null)
                    oos.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }
    /**
     * Aquest mètode també fa servir una estructura de codi try catch, però en aquest cas per a llegir un
     *  fitxer i carregar les dades a la central.
     */
    public void carregaDades(String camiOrigen)throws CentralUBException{
        Dades dades1 = null;
        FileInputStream fin = null;
        ObjectInputStream ois = null;
        try {
            fin = new FileInputStream(camiOrigen);
            ois = new ObjectInputStream(fin);
            dades = (Dades) ois.readObject();

        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        catch (ClassNotFoundException e) {
            throw new CentralUBException("No s'ha trobat la classe dades al fitxer.");
        }
        finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            try {
                if (ois != null)
                    ois.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }



    }





}
