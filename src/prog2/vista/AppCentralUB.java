package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppCentralUB extends JFrame {
    public final static float DEMANDA_MAX = 1800;
    public final static float DEMANDA_MIN = 250;
    public final static float VAR_NORM_MEAN = 1000;
    public final static float VAR_NORM_STD = 800;
    public final static long VAR_NORM_SEED = 123;

    /** Generador aleatori de la demanda de potència **/
    private VariableNormal variableNormal;

    /** Demanda de potència del dia actual **/
    private float demandaPotencia;
    private static Adaptador adaptador;
    private JPanel appCentralUB;
    private JButton btnGestioComponentsCentral;
    private JButton btnInfo;
    private JButton btnFinalitzarDia;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppCentralUB appCentralUB = new AppCentralUB();
            appCentralUB.setVisible(true);
        });
    }

    public AppCentralUB() {
        setTitle("Gestió Central UB");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(appCentralUB);
        setSize(500,400);
        setLocationRelativeTo(null);
        adaptador = new Adaptador();
        variableNormal = new VariableNormal(VAR_NORM_MEAN, VAR_NORM_STD, VAR_NORM_SEED);
        demandaPotencia = generaDemandaPotencia();
        btnGestioComponentsCentral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioComponentsCentral gestioComponents = new FrmGestioComponentsCentral(AppCentralUB.this);
                gestioComponents.setVisible(true);


            }
        });
        btnInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmVisualitzarInformacio informacio = new FrmVisualitzarInformacio(AppCentralUB.this);
                informacio.setVisible(true);
            }
        });
        btnFinalitzarDia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaptador.finalitzaDia(demandaPotencia);
                demandaPotencia = generaDemandaPotencia();


            }
        });
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

    static Adaptador getAdaptador() {
        return adaptador;
    }
}
