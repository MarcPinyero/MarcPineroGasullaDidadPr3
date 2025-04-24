/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.vista;

import prog2.adaptador.Adaptador;


import java.util.Scanner;

/**
 *
 * @author Daniel Ortiz
 */
public class CentralUB {
    private Adaptador adaptador;

    public final static float DEMANDA_MAX = 1800;
    public final static float DEMANDA_MIN = 250;
    public final static float VAR_NORM_MEAN = 1000;
    public final static float VAR_NORM_STD = 800;
    public final static long VAR_NORM_SEED = 123;
    
    /** Generador aleatori de la demanda de potència **/
    private VariableNormal variableNormal;
    
    /** Demanda de potència del dia actual **/
    private float demandaPotencia;

    static private enum OpcionsMenuPrincipal {MENU_PRINCIPAL_OPCIO1,MENU_PRINCIPAL_OPCIO2,MENU_PRINCIPAL_OPCIO3,
        MENU_PRINCIPAL_OPCIO4,MENU_PRINCIPAL_OPCIO5,MENU_PRINCIPAL_OPCIO6,MENU_PRINCIPAL_OPCIO7,MENU_PRINCIPAL_OPCIO8,
        MENU_PRINCIPAL_OPCIO9,MENU_PRINCIPAL_OPCIO10,MENU_PRINCIPAL_SORTIR,}

    static private enum OpcionsSubmenu1 {MENU_S1_OPCIO1,MENU_S1_OPCIO2,MENU_S1_SORTIR};

    static private enum OpcionsSubmenu2 {MENU_S2_OPCIO1,MENU_S2_OPCIO2, MENU_S2_OPCIO3, MENU_S2_SORTIR};

    static private enum OpcionsSubmenu3 {MENU_S3_OPCIO1,MENU_S3_OPCIO2,MENU_S3_OPCIO3,MENU_S3_OPCIO4,MENU_S3_OPCIO5,MENU_S3_SORTIR};


    static private String[] descMenuPrincipal={"Gestió Barres de Control",
            "Gestió Reactor",
            "Gestió Sistema Refrigeració",
            "Mostrar Estat Central",
            "Mostrar Bitàcola",
            "Mostrar Incidències",
            "Obtenir Demanda Satisfeta amb Configuració Actual",
            "Finalitzar Dia",
            "Guardar Dades",
            "Carrega Dades",
            "Sortir"};

    static private String[] menu2={"Obtenir Inserció Barres",
            "Establir Inserció Barres",
            "Sortir"};

    static private String[] menu3={"Activar Reactorl",
            "Desactivar Reactor",
            "Mostrar Estat",
            "Sortir"};

    static private String[] menu4={"Activar Totes les Bombes",
            "Desactivar Totes les Bombes",
            "Activar Bomba",
            "Desactivar Bomba",
            "Mostrar Estat",
            "Sortir"};


    
    /* Constructor*/
    public CentralUB() {
        variableNormal = new VariableNormal(VAR_NORM_MEAN, VAR_NORM_STD, VAR_NORM_SEED);
        demandaPotencia = generaDemandaPotencia();
        
        // Afegir codi adicional si fos necessari:
        adaptador = new Adaptador();

    }
    
    public void gestioCentralUB() {


        // Completar
        // Creem un objecte per llegir des del teclat
        Scanner sc=new Scanner(System.in);

        // Creem l'objecte per al menú. Li passem com a primer parà metre el nom del menú
        prog2.vista.Menu<OpcionsMenuPrincipal> menu=new prog2.vista.Menu<OpcionsMenuPrincipal>("Menu Principal",OpcionsMenuPrincipal.values());

        // Assignem la descripció de les opcions
        menu.setDescripcions(descMenuPrincipal);

        // Obtenim una opció des del menú i fem les accions pertinents
        OpcionsMenuPrincipal opcio = null;

        do {
            // Mostrar missatge inicial
            System.out.println("Benvingut a la planta PWR de la UB");
            System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");

            // Mostrem les opcions del menú
            menu.mostrarMenu();

            // Demanem una opcio
            opcio=menu.getOpcio(sc);

            // Fem les accions necessÃ ries
            switch(opcio) {
                case MENU_PRINCIPAL_OPCIO1:
                    gestioMenuSecundari1(sc);
                    break;

                case MENU_PRINCIPAL_OPCIO2:
                    gestioMenuSecundari2(sc);
                    break;

                case MENU_PRINCIPAL_OPCIO3:
                    gestioMenuSecundari3(sc);
                    break;

                case MENU_PRINCIPAL_OPCIO4:
                    adaptador.mostrarEstat();
                    break;

                case MENU_PRINCIPAL_OPCIO5:
                    adaptador.mostrarBitacola();
                    break;

                case MENU_PRINCIPAL_OPCIO6:
                    adaptador.mostrarIncidencies();
                    break;

                case MENU_PRINCIPAL_OPCIO7:
                    adaptador.mostrarPercentatge(demandaPotencia);
                    break;

                case MENU_PRINCIPAL_OPCIO8:
                    finalitzaDia();
                    break;

                case MENU_PRINCIPAL_OPCIO9:
                    adaptador.guardaDades("Dades.txt");
                    break;

                case MENU_PRINCIPAL_OPCIO10:
                    adaptador.carregaDades("Dades.txt");
                    break;

                case MENU_PRINCIPAL_SORTIR:
                    System.out.println("Fins aviat!");
                    break;
            }

        } while(opcio!=OpcionsMenuPrincipal.MENU_PRINCIPAL_SORTIR);
    }

    public void gestioMenuSecundari1(Scanner sc){

        // Creem l'objecte per al menú. Li passem com a primer parÃ metre el nom del menú
        Menu<OpcionsSubmenu1> menu=new Menu<OpcionsSubmenu1>("Menu Secundari",OpcionsSubmenu1.values());

        // Assignem la descripció de les opcions
        menu.setDescripcions(menu2);

        // Obtenim una opció des del menú i fem les accions pertinents
        OpcionsSubmenu1 opcio = null;
        do {
            // Mostrem les opcions del menú
            menu.mostrarMenu();

            // Demanem una opcio
            opcio=menu.getOpcio(sc);

            // Fem les accions necessàries
            switch(opcio) {
                case MENU_S1_OPCIO1:
                   System.out.println("La inserció de les barres es del " + adaptador.getInsercio() + " %");
                    break;
                case MENU_S1_OPCIO2:
                    System.out.println("Digues l'inserció de les barres: ");
                    float insercio = sc.nextFloat();
                    try {
                        adaptador.setInsercio(insercio);

                    } catch (CentralUBException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case MENU_S1_SORTIR:
                    break;
            }

        } while(opcio!=OpcionsSubmenu1.MENU_S1_SORTIR);
    }

    public void gestioMenuSecundari2(Scanner sc){

        // Creem l'objecte per al menú. Li passem com a primer parÃ metre el nom del menú
        Menu<OpcionsSubmenu2> menu=new Menu<OpcionsSubmenu2>("Menu Secundari",OpcionsSubmenu2.values());

        // Assignem la descripció de les opcions
        menu.setDescripcions(menu3);

        // Obtenim una opció des del menú i fem les accions pertinents
        OpcionsSubmenu2 opcio = null;
        do {
            // Mostrem les opcions del menú
            menu.mostrarMenu();

            // Demanem una opcio
            opcio=menu.getOpcio(sc);

            // Fem les accions necessàries
            switch(opcio) {
                case MENU_S2_OPCIO1:
                    try {
                        adaptador.activaReactor();

                    } catch (CentralUBException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case MENU_S2_OPCIO2:
                    adaptador.desactivaReactor();
                    break;

                case MENU_S2_OPCIO3:
                    adaptador.mostraReactor();
                    break;

                case MENU_S2_SORTIR:
                    break;
            }

        } while(opcio!=OpcionsSubmenu2.MENU_S2_SORTIR);
    }

    public void gestioMenuSecundari3(Scanner sc) {
        //Para lo de las bombas
        int id;

        // Creem l'objecte per al menú. Li passem com a primer parÃ metre el nom del menú
        Menu<OpcionsSubmenu3> menu = new Menu<OpcionsSubmenu3>("Menu Secundari", OpcionsSubmenu3.values());

        // Assignem la descripció de les opcions
        menu.setDescripcions(menu4);

        // Obtenim una opció des del menú i fem les accions pertinents
        OpcionsSubmenu3 opcio = null;
        do {
            // Mostrem les opcions del menú
            menu.mostrarMenu();

            // Demanem una opcio
            opcio = menu.getOpcio(sc);

            // Fem les accions necessàries
            switch (opcio) {
                case MENU_S3_OPCIO1:
                    try {
                        adaptador.activaRefrigeracio();
                    } catch (CentralUBException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case MENU_S3_OPCIO2:
                    adaptador.desactivaRefrigeracio();
                    break;

                case MENU_S3_OPCIO3:
                    System.out.println("ID de la bomba a activar.");
                    id = sc.nextInt();
                    try {
                        adaptador.activaBomba(id);

                    } catch (CentralUBException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case MENU_S3_OPCIO4:
                    System.out.println("ID de la bomba a desactivar.");
                    id = sc.nextInt();
                    adaptador.desactivaBomba(id);
                    break;

                case MENU_S3_OPCIO5:
                    adaptador.mostraRefrigeracio();

                case MENU_S3_SORTIR:
                    break;
            }

        } while (opcio != OpcionsSubmenu3.MENU_S3_SORTIR);
    }

    private float generaDemandaPotencia(){
        float valor = Math.round(variableNormal.seguentValor());
        if (valor > DEMANDA_MAX)
            return DEMANDA_MAX;
        else
        if (valor < DEMANDA_MIN)
            return DEMANDA_MIN;
        else
            return valor;
    }
    
    private void finalitzaDia() {
        // Finalitzar dia i imprimir informacio de la central
        String info = new String();
        info = adaptador.finalitzaDia(demandaPotencia);
        System.out.println(info);
        System.out.println("Dia finalitzat\n");
        
        // Generar i mostrar nova demanda de potencia
        demandaPotencia = generaDemandaPotencia();
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");
    }
}


//FALTAN LAS EXCEPCIONES Y EL CARGAR GUARDAR
